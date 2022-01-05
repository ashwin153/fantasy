package fantasy.optimize
package solvers

import org.gnu.glpk.GLPK._
import org.gnu.glpk.{GLPKConstants, glp_iocp}

/**
 * A GLPK solver. https://www.gnu.org/software/glpk/
 */
object GLPK extends Solver {

  override def solve(problem: Problem): Solution = {
    // Initialize the GLPK problem. GLPK represents a problem as a sparse matrix in which variables
    // correspond to columns and constraints correspond to rows. Determine each of the variables and
    // constraints in the problem and map them to an index in this matrix. Indices are one-based.
    val glpk = glp_create_prob()
    glp_term_hook(null, null)

    val variables = problem.subjectTo
      .foldLeft(problem.objective.variables)((v, c) => v ++ c.lhs.variables ++ c.rhs.variables)
      .zipWithIndex
      .toMap
      .mapValues(_ + 1)

    val constraints = problem.subjectTo
      .zipWithIndex
      .toMap
      .mapValues(_ + 1)

    glp_add_cols(glpk, variables.size)
    glp_add_rows(glpk, constraints.size)

    // Initialize each column in the problem matrix. GLPK columns correspond either to continuous
    // variables (CV), integral variables (IV), or binary variables (BV) and they can be unbounded
    // (FR), lower bounded (LO), upper bounded (UP), double bounded (DB), or fixed (FX).
    variables foreach { case (x, col) =>
      x match {
        case Real(_) =>
          glp_set_col_kind(glpk, col, GLPKConstants.GLP_CV)
          glp_set_col_bnds(glpk, col, GLPKConstants.GLP_FR, 0, 0)
        case Integer(_) =>
          glp_set_col_kind(glpk, col, GLPKConstants.GLP_IV)
          glp_set_col_bnds(glpk, col, GLPKConstants.GLP_FR, 0, 0)
        case Binary(_) =>
          glp_set_col_kind(glpk, col, GLPKConstants.GLP_BV)
          glp_set_col_bnds(glpk, col, GLPKConstants.GLP_DB, 0, 1)
      }
    }

    // Initialize each row in the problem matrix. GLPK expects every constraint to be in the form
    // x - y <= b - a, and it designates the left-hand side of this equation as a row and the
    // right-hand side as its bounds. GLPK uses two arrays to encode a row; the value array to
    // stores the coefficient of a particular variable in a constraint and the index array to store
    // the index of the variable that the coefficient corresponds to.
    constraints foreach { case (constraint, row) =>
      constraint match {
        case Equals(lhs, rhs) =>
          glp_set_row_bnds(glpk, row, GLPKConstants.GLP_FX, rhs.value - lhs.value, 0)
        case LessThanOrEquals(lhs, rhs) =>
          glp_set_row_bnds(glpk, row, GLPKConstants.GLP_UP, 0, rhs.value - lhs.value)
      }

      val coeff = (constraint.lhs - constraint.rhs).coefficients
      val index = new_intArray(coeff.size + 1)
      val value = new_doubleArray(coeff.size + 1)

      coeff.zipWithIndex foreach { case ((x, a), i) =>
        intArray_setitem(index, i + 1, variables(x))
        doubleArray_setitem(value, i + 1, a)
      }

      glp_set_mat_row(glpk, row, coeff.size, index, value)
      delete_intArray(index)
      delete_doubleArray(value)
    }

    // Initialize the the objective function. GLPK represents an objective function as a dense,
    // coefficient vector that is optimized in either the maximal (MAX) or minimal (MIN) direction.
    glp_set_obj_coef(glpk, 0, problem.objective.value)
    variables foreach { case (x, col) =>
      glp_set_obj_coef(glpk, col, problem.objective.coefficients.getOrElse(x, 0.0))
    }

    problem match {
      case Maximize(_, _) =>
        glp_set_obj_dir(glpk, GLPKConstants.GLP_MAX)
      case Minimize(_, _) =>
        glp_set_obj_dir(glpk, GLPKConstants.GLP_MIN)
    }

    // Presolve the problem to remove redundant variables and constraints and reduce problem
    // complexity the problem, and then solve the problem to determine the optimal solution. Garbage
    // collect the GLPK problem after solving it to avoid leaking memory.
    val iocp = new glp_iocp()
    glp_init_iocp(iocp)
    iocp.setMsg_lev(GLPKConstants.GLP_MSG_ALL)
    iocp.setPresolve(GLPKConstants.GLP_ON)
    glp_intopt(glpk, iocp)

    val solution = Solution(glp_mip_obj_val(glpk), variables.mapValues(glp_mip_col_val(glpk, _)))
    // TODO: glp_delete_prob segfaults on Linux.
    // glp_delete_prob(glpk)
    solution
  }

}
