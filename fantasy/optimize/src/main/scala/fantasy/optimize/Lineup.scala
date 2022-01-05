package fantasy.optimize

/**
 * A player in a [[Lineup]].
 *
 * @param name Name of the player.
 * @param position Position that the player plays.
 * @param salary Amount of money the player costs.
 * @param points Number of points the player is projected to score.
 */
case class Player(name: String, position: String, points: Double, salary: Int)

/**
 * A collection of [[Constraint]]s on the composition of a [[Lineup]].
 */
trait Rule {

  /**
   * Apply the rule to the `selected` players.
   *
   * @param selected Selected players.
   * @return Corresponding [[Constraint]]s.
   */
  def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint]

}

object Rule {

  /**
   * A [[Rule]] that requires the number of selected players to be equal to the `total`.
   *
   * @param total Number of selected players.
   */
  case class Count(total: Int) extends Rule {
    require(total >= 0, "Total must be a non-negative number.")

    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      Seq(dot(selected, Seq.fill(selected.size)(1)) === total)
    }
  }

  /**
   * A [[Rule]] that requires players be selected at most `exposure * |lineups|` times.
   *
   * @param lineups Previously selected lineups.
   * @param exposure Maximum percentage of lineups that a player can be selected in.
   */
  case class Exposure(lineups: Seq[Lineup], exposure: Double) extends Rule {
    require(exposure >= 0.0 && exposure <= 1.0, "Exposure must be a percentage in [0, 1].")

    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      selected
        .map(p => lineups.count(_.players.exists(_.name == p.key.name)))
        .zipWithIndex
        .map { case (count, i) => Seq.tabulate(selected.size)(j => if (i == j) count else 0) }
        .map(dot(selected, _) <= lineups.size * exposure)
    }
  }

  /**
   * A [[Rule]] that requires players to be selected at most `total` times.
   *
   * @param lineups Previously selected lineups.
   * @param total Maximum number of times a player can be selected.
   */
  case class Occurrences(lineups: Seq[Lineup], total: Int) extends Rule {
    require(total >= 0, "Total must be a non-negative number.")

    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      selected
        .map(player => lineups.count(_.players.exists(_.name == player.key.name)))
        .zipWithIndex
        .map { case (count, i) => Seq.tabulate(selected.size)(j => if (i == j) count else 0) }
        .map(dot(selected, _) <= total)
    }
  }

  /**
   * A [[Rule]] that requires a certain number of players at each position.
   *
   * @param players Number of players at each position.
   */
  case class Roster(players: Map[String, Int]) extends Rule {
    require(players.values.min >= 0, "Number of players at each position must be non-negative.")

    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      val unknown = (players.keySet diff selected.map(_.key.position).toSet).map(_ -> 0)
      (players ++ unknown) map { case (position, total) =>
        dot(selected, selected.map(_.key.position == position)) === total
      }
    }
  }

  /**
   * A [[Rule]] that requires the total salary to be at most the `cap`.
   *
   * @param cap Maximum total salary.
   */
  case class SalaryCap(cap: Double) extends Rule {
    require(cap >= 0, "Cap must be non-negative.")

    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      val salary = dot(selected, selected.map(_.key.salary))
      Seq(salary <= cap)
    }
  }

  /**
   * A [[Rule]] that requires the total salary to be at least the `floor`.
   *
   * @param floor Minimum total salary.
   */
  case class SalaryFloor(floor: Double) extends Rule {
    require(floor >= 0, "Floor must be non-negative.")

    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      val salary = dot(selected, selected.map(_.key.salary))
      Seq(salary >= floor)
    }
  }

  /**
   * A [[Rule]] that requires selected lineups to be unique.
   *
   * @param lineups Previously selected lineups.
   */
  case class UniqueLineups(lineups: Seq[Lineup]) extends Rule {
    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      lineups
        .map(lineup => lineup.players.map(p => p.name))
        .map(team => dot(selected, selected.map(p => team.contains(p.key.name))) <= team.size - 1)
    }
  }

  /**
   * A [[Rule]] that requires selected players to be unique.
   */
  object UniquePlayers extends Rule {
    override def constraints(selected: Seq[Binary[Player]]): Iterable[Constraint] = {
      selected
        .map(_.key.name)
        .toSet[String]
        .map(name => selected.map(p => p.key.name == name))
        .map(dot(selected, _) <= 1)
    }
  }

}

/**
 * A collection of [[Player]]s.
 *
 * @param players [[Player]]s in the lineup.
 */
case class Lineup(players: Seq[Player])

object Lineup {

  /**
   * Returns the optimal lineup of `players` that satisfies the specified `rules`.
   *
   * @param solver Optimizer.
   * @param players Selectable players.
   * @param rules Selection criteria.
   * @return Optimal [[Lineup]].
   */
  def optimal(solver: Solver, players: Seq[Player], rules: Seq[Rule]): Lineup = {
    // Convert each player into a binary variable that indicates whether or not they are selected.
    val selected = players.map(Binary.apply)

    // Maximize expected points subject to the rules.
    val objective = dot(selected, players.map(_.points))
    val constraints = rules.flatMap(_.constraints(selected))
    val problem = Maximize(objective, constraints)

    // Solve the optimization problem and return the optimal lineup.
    val solution = solver.solve(problem)
    Lineup(selected.zipWithIndex collect { case (p, i) if solution.value(p) => players(i) })
  }

}
