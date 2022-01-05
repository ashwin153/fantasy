package fantasy.optimize

import fantasy.optimize.Expression._

import org.scalatest._

trait SolverTest extends fixture.FunSuite with Matchers {

  type FixtureParam = Solver

  test("Solvers should solve linear programs.") { solver =>
    val x = Real("x")
    val y = Real("y")

    val problem = Maximize(
      x + 2 * y + 1,
      4 * x + 1 <= 3,
      y <= 4
    )

    solver.solve(problem) shouldEqual Solution(9.5, Map(x -> 0.5, y -> 4))
  }

  test("Solvers should solve integer linear programs.") { solver =>
    val x = Integer("y")
    val y = Binary("z")

    val problem = Minimize(
      x + y,
      2 * x >= 2.5,
      y >= 0
    )

    solver.solve(problem) shouldEqual Solution(2, Map(x -> 2, y -> 0))
  }

  test("Solvers should solve mixed integer linear programs.") { solver =>
    val x = Real("x")
    val y = Integer("y")
    val z = Binary("z")

    val problem = Maximize(
      x + y + z,
      x <= 2.5,
      y <= 4.5
    )

    solver.solve(problem) shouldEqual Solution(7.5, Map(x -> 2.5, y -> 4, z -> 1))
  }

}
