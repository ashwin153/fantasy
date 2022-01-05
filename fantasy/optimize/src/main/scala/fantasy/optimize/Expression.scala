package fantasy.optimize

/**
 * A linear expression.
 */
trait Expression {

  /**
   * Returns the constant term of the [[Expression]].
   *
   * @return Scalar value.
   */
  def value: Double

  /**
   * Returns the coefficients of each [[Variable]] in the [[Expression]].
   *
   * @return Coefficients of each [[Variable]].
   */
  def coefficients: Map[Variable[_], Double]

  /**
   * Returns the collection of [[Variable]]s in the [[Expression]].
   *
   * @return [[Variable]]s in the [[Expression]].
   */
  def variables: Set[Variable[_]] = coefficients.keySet

}

object Expression {

  /**
   * Implicit operations that can be performed on an [[Expression]].
   *
   * @param x Left-hand side.
   */
  implicit class Ops(x: Expression) {

    /**
     * Returns the negation of `x`.
     *
     * @return Negation of `x`.
     */
    def unary_-(): Expression = new Expression {

      override val value: Double = {
        -x.value
      }

      override val coefficients: Map[Variable[_], Double] = {
        x.coefficients.mapValues(-_)
      }

    }

    /**
     * Returns the sum of `x` and `y`.
     *
     * @param y Right-hand side.
     * @return Sum of `x` and `y`.
     */
    def +(y: Expression): Expression = new Expression {

      override val value: Double = {
        x.value + y.value
      }

      override val coefficients: Map[Variable[_], Double] = {
        x.coefficients ++ (y.coefficients map {
          case (k, v) => k -> (v + x.coefficients.getOrElse(k, 0.0))
        })
      }

    }

    /**
     * Returns the difference of `x` and `y`.
     *
     * @param y Right-hand side.
     * @return Difference of `x` and `y`.
     */
    def -(y: Expression): Expression = {
      x + (-y)
    }

    /**
     * Returns the product of `x` and `y`.
     *
     * @param y Right-hand side.
     * @return Product of `x` and `y`.
     */
    def *(y: Constant): Expression = new Expression {

      override val value: Double = {
        x.value * y.value
      }

      override val coefficients: Map[Variable[_], Double] = (x, y) match {
        case (Constant(0), _) | (_, Constant(0)) => Map.empty
        case _ => x.coefficients.mapValues(_ * y.value)
      }

    }

    /**
     * Returns the quotient of `x` and `y`.
     *
     * @param y Right-hand side.
     * @return Quotient of `x` and `y`.
     */
    def /(y: Constant): Expression = {
      x * (1 / y.value)
    }

    /**
     * Returns a [[Equals]] constraint that requires `x` to be exactly `y`.
     *
     * @param y Right-hand side.
     * @return [[Constraint]] that requires `x` to be exactly `y`.
     */
    def ===(y: Expression): Constraint = {
      Equals(x, y)
    }

    /**
     * Returns a [[LessThanOrEquals]] constraint that requires `x` to be less than or equal to `y`.
     *
     * @param y Right-hand side.
     * @return [[Constraint]] that requires `x` to be less than or equal to `y`.
     */
    def <=(y: Expression): Constraint = {
      LessThanOrEquals(x, y)
    }

    /**
     * Returns a [[LessThanOrEquals]] constraint that requires `y` to be less than or equal to `x`.
     *
     * @param y Right-hand side.
     * @return [[Constraint]] that requires `y` to be less than or equal to `x`.
     */
    def >=(y: Expression): Constraint = {
      LessThanOrEquals(y, x)
    }

  }

}
