package fantasy.optimize

/**
 * An optimization problem.
 */
sealed trait Problem {

  /**
   * Returns the function to optimize.
   *
   * @return Function to optimize.
   */
  def objective: Expression

  /**
   * Returns the optimization constraints that bound the search for an optimal solution.
   *
   * @return Optimization constraints.
   */
  def subjectTo: Seq[Constraint]

}

/**
 * A maximization [[Problem]].
 *
 * @param objective Function to maximize.
 * @param subjectTo Optimization constraints.
 */
case class Maximize(objective: Expression, subjectTo: Seq[Constraint]) extends Problem

object Maximize {

  /**
   * Returns a [[Problem]] that maximizes the `objective` subject to the specified [[Constraint]]s.
   *
   * @param objective Function to maximize.
   * @param subjectTo Optimization constraints.
   * @return [[Maximize]] problem.
   */
  def apply(objective: Expression, subjectTo: Constraint*)(implicit x: DummyImplicit): Maximize = {
    Maximize(objective, subjectTo)
  }

}

/**
 * A minimization [[Problem]].
 *
 * @param objective Function to minimize.
 * @param subjectTo Optimization constraints.
 */
case class Minimize(objective: Expression, subjectTo: Seq[Constraint]) extends Problem

object Minimize {

  /**
   * Returns a [[Problem]] that minimizes the `objective` subject to the specified [[Constraint]]s.
   *
   * @param objective Function to minimize.
   * @param subjectTo Optimization constraints.
   * @return [[Minimize]] problem.
   */
  def apply(objective: Expression, subjectTo: Constraint*)(implicit x: DummyImplicit): Minimize = {
    Minimize(objective, subjectTo)
  }

}
