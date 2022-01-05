package fantasy.optimize

/**
 * A linear constraint.
 */
trait Constraint {

  /**
   * Returns the left-hand side of the constraint.
   *
   * @return Left-hand side.
   */
  def lhs: Expression

  /**
   * Returns the right-hand side of the constraint.
   *
   * @return Right-hand side.
   */
  def rhs: Expression

}

/**
 * An equality constraint. Constrains the value of `lhs` to be exactly `rhs`.
 *
 * @param lhs Left-hand side.
 * @param rhs Right-hand side.
 */
case class Equals(lhs: Expression, rhs: Expression) extends Constraint

/**
 * A bounding constraint. Constrains the value of `lhs` to be less than or equal to `rhs`.
 *
 * @param lhs Left-hand side.
 * @param rhs Right-hand side.
 */
case class LessThanOrEquals(lhs: Expression, rhs: Expression) extends Constraint
