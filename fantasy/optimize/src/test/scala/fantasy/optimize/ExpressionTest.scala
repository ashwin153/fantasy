package fantasy.optimize

import fantasy.optimize.Expression._

import org.scalatest._

class ExpressionTest extends FunSuite with Matchers {

  test("Expressions should be additive.") {
    val x = Real("x")
    val y = Integer("y")
    val f = (1 * x) - (2 * y) - 1

    f.value shouldEqual -1
    f.coefficients should contain only (x -> 1, y -> -2)
    f.variables should contain only (x, y)
  }

  test("Expressions should be simplified.") {
    val x = Integer("x")
    val f = (1 * x) - 2 + (2 * x) + 3

    f.value shouldEqual 1
    f.coefficients should contain only (x -> 3)
    f.variables should contain only x
  }

  test("Expressions should be multiplicable by constants.") {
    val x = Integer("x")
    val f = (2 * x + 3) * 4

    f.value shouldEqual 12
    f.coefficients should contain only (x -> 8)
    f.variables should contain only x
  }

  test("Expressions should be divisible by constants.") {
    val x = Binary("x")
    val f = x / 4

    f.value shouldEqual 0
    f.coefficients should contain only (x -> 0.25)
    f.variables should contain only x
  }

}
