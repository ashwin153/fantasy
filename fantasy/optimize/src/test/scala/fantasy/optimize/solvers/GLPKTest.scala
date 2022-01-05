package fantasy.optimize.solvers

import org.scalatest.Outcome

import fantasy.optimize.SolverTest

class GLPKTest extends SolverTest {

  override protected def withFixture(test: OneArgTest): Outcome = {
    test(GLPK)
  }

}
