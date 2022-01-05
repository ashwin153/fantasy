package fantasy.optimize

/**
 * An optimal solution to a [[Problem]].
 *
 * @param optimum Optimal value of the objective function.
 * @param values Optimal values of the [[Variable]]s.
 */
case class Solution(optimum: Double, values: Map[Variable[_], Double]) {

  /**
   * Returns the optimal value for the `variable`.
   *
   * @param variable [[Variable]] to lookup.
   * @return Optimal value of `variable`.
   */
  def value(variable: Variable[_]): Double = variable match {
    case r: Real[_] => value(r)
    case i: Integer[_] => value(i)
    case b: Binary[_] => value(b)
  }

  /**
   * Returns the optimal value of the `variable`.
   *
   * @param variable [[Variable]] to lookup.
   * @return Optimal value of `variable`.
   */
  def value(variable: Real[_]): Double = {
    values(variable)
  }

  /**
   * Returns the optimal value of the `variable`.
   *
   * @param variable [[Variable]] to lookup.
   * @return Optimal value of `variable`.
   */
  def value(variable: Integer[_]): Long = {
    values(variable).round
  }

  /**
   * Returns the optimal value of the `variable`.
   *
   * @param variable [[Variable]] to lookup.
   * @return Optimal value of `variable`.
   */
  def value(variable: Binary[_]): Boolean = {
    if (values(variable) < 0.5) false else true
  }

}
