package fantasy

import fantasy.optimize.Expression._

import scala.language.implicitConversions

package object optimize {

  /**
   * Returns the sum of the `terms`.
   *
   * @param terms [[Expression]]s to add.
   * @return Sum of the `terms`.
   */
  def sum(terms: Seq[Expression]): Expression = {
    terms.foldLeft[Expression](0)(_ + _)
  }

  /**
   * Returns the dot product of `x` and `y`.
   *
   * @param x Left-hand side.
   * @param y Right-hand side.
   * @return Dot product of `x` and `y`.
   */
  def dot(x: Seq[Expression], y: Seq[Constant]): Expression = {
    require(x.size == y.size, x + " and " + y + " must be the same size.")
    sum(x.zip(y) map { case (a, b) => a * b })
  }

  /**
   * Returns the dot product of `x` and `y`.
   *
   * @param x Left-hand side.
   * @param y Right-hand side.
   * @return Dot product of `x` and `y`.
   */
  def dot(x: Seq[Constant], y: Seq[Expression])(implicit dummy: DummyImplicit): Expression = {
    dot(y, x)
  }

  /**
   * Implicitly converts any [[Boolean]] value into a [[Constant]].
   *
   * @param x [[Boolean]] to convert.
   * @return Converted [[Constant]].
   */
  implicit def constant(x: Boolean): Constant = {
    Constant(if (x) 1 else 0)
  }

  /**
   * Implicitly converts any [[Numeric]] value into a [[Constant]].
   *
   * @param x [[Numeric]] to convert.
   * @return Converted [[Constant]].
   */
  implicit def constant[T](x: T)(implicit numeric: Numeric[T]): Constant = {
    Constant(numeric.toDouble(x))
  }

  /**
   * Implicitly converts any collection of [[Boolean]] values into a a collection of [[Constant]]s.
   *
   * @param x Collection of [[Boolean]]s to convert.
   * @return Converted collection of [[Constant]]s.
   */
  implicit def constant(x: Seq[Boolean]): Seq[Constant] = {
    x.map(constant)
  }

  /**
   * Implicitly converts any collection of [[Numeric]] values into a a collection of [[Constant]]s.
   *
   * @param x Collection of [[Numeric]]s to convert.
   * @return Converted collection of [[Constant]]s.
   */
  implicit def constant[T](x: Seq[T])(implicit numeric: Numeric[T]): Seq[Constant] = {
    x.map(constant(_)(numeric))
  }

  /**
   * Implicitly permits [[Constant.Ops]] to be performed on any [[Numeric]] value.
   *
   * @param x [[Numeric]] to convert.
   * @return Converted [[Constant.Ops]].
   */
  implicit def constantOps[T](x: T)(implicit numeric: Numeric[T]): Constant.Ops = {
    Constant.Ops(numeric.toDouble(x))
  }

}
