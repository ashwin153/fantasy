package fantasy.optimize

/**
 * An unknown value in an [[Expression]].
 */
sealed trait Variable[+T] extends Expression {

  /**
   * Returns a unique identifier for the [[Variable]].
   *
   * @return Unique identifier.
   */
  def key: T

  override val value: Double = 0

  override val coefficients: Map[Variable[_], Double] = Map(this -> 1)

}

/**
 * A real-valued variable in ℝ.
 *
 * @param key Unique identifier.
 */
case class Real[T](key: T) extends Variable[T]

/**
 * A integer-valued variable in ℤ.
 *
 * @param key Unique identifier.
 */
case class Integer[T](key: T) extends Variable[T]

/**
 * A binary-valued variable in {0, 1}.
 *
 * @param key Unique identifier.
 */
case class Binary[T](key: T) extends Variable[T]
