package fantasy.optimize

/**
 * A known value in an [[Expression]].
 *
 * @param value Scalar value.
 */
case class Constant(value: Double) extends Expression {

  override def coefficients: Map[Variable[_], Double] = Map.empty

}

object Constant {

  /**
   * Implicit operations that can be performed on a [[Constant]].
   *
   * @param x Left-hand side.
   */
  implicit class Ops(x: Constant) extends Expression.Ops(x) {

    /**
     * Returns the product of the `x` and `y`.
     *
     * @param y Right-hand side.
     * @return Product of `x` and `y`.
     */
    def *(y: Expression): Expression = {
      y * x
    }

  }

}
