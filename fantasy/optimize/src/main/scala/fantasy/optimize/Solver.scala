package fantasy.optimize

import scala.util.Try

/**
 * A mixed integer linear program solver.
 */
trait Solver {

  /**
   * Returns the optimal [[Solution]] to the [[Problem]].
   *
   * @param problem Optimization problem.
   * @return Optimal [[Solution]].
   */
  def solve(problem: Problem): Solution

}
