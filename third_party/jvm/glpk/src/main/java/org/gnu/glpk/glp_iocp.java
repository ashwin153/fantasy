/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.gnu.glpk;


/**
 * Wrapper class for pointer generated by SWIG.
 * <p>Please, refer to doc/glpk-java.pdf of the GLPK for Java distribution
 * and to doc/glpk.pdf of the GLPK source distribution
 * for details. You can download the GLPK source distribution from
 * <a href="ftp://ftp.gnu.org/gnu/glpk">ftp://ftp.gnu.org/gnu/glpk</a>.
 */
public class glp_iocp {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected glp_iocp(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(glp_iocp obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        GLPKJNI.delete_glp_iocp(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setMsg_lev(int value) {
    GLPKJNI.glp_iocp_msg_lev_set(swigCPtr, this, value);
  }

  public int getMsg_lev() {
    return GLPKJNI.glp_iocp_msg_lev_get(swigCPtr, this);
  }

  public void setBr_tech(int value) {
    GLPKJNI.glp_iocp_br_tech_set(swigCPtr, this, value);
  }

  public int getBr_tech() {
    return GLPKJNI.glp_iocp_br_tech_get(swigCPtr, this);
  }

  public void setBt_tech(int value) {
    GLPKJNI.glp_iocp_bt_tech_set(swigCPtr, this, value);
  }

  public int getBt_tech() {
    return GLPKJNI.glp_iocp_bt_tech_get(swigCPtr, this);
  }

  public void setTol_int(double value) {
    GLPKJNI.glp_iocp_tol_int_set(swigCPtr, this, value);
  }

  public double getTol_int() {
    return GLPKJNI.glp_iocp_tol_int_get(swigCPtr, this);
  }

  public void setTol_obj(double value) {
    GLPKJNI.glp_iocp_tol_obj_set(swigCPtr, this, value);
  }

  public double getTol_obj() {
    return GLPKJNI.glp_iocp_tol_obj_get(swigCPtr, this);
  }

  public void setTm_lim(int value) {
    GLPKJNI.glp_iocp_tm_lim_set(swigCPtr, this, value);
  }

  public int getTm_lim() {
    return GLPKJNI.glp_iocp_tm_lim_get(swigCPtr, this);
  }

  public void setOut_frq(int value) {
    GLPKJNI.glp_iocp_out_frq_set(swigCPtr, this, value);
  }

  public int getOut_frq() {
    return GLPKJNI.glp_iocp_out_frq_get(swigCPtr, this);
  }

  public void setOut_dly(int value) {
    GLPKJNI.glp_iocp_out_dly_set(swigCPtr, this, value);
  }

  public int getOut_dly() {
    return GLPKJNI.glp_iocp_out_dly_get(swigCPtr, this);
  }

  public void setCb_func(SWIGTYPE_p_f_p_struct_glp_tree_p_void__void value) {
    GLPKJNI.glp_iocp_cb_func_set(swigCPtr, this, SWIGTYPE_p_f_p_struct_glp_tree_p_void__void.getCPtr(value));
  }

  public SWIGTYPE_p_f_p_struct_glp_tree_p_void__void getCb_func() {
    long cPtr = GLPKJNI.glp_iocp_cb_func_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_f_p_struct_glp_tree_p_void__void(cPtr, false);
  }

  public void setCb_info(SWIGTYPE_p_void value) {
    GLPKJNI.glp_iocp_cb_info_set(swigCPtr, this, SWIGTYPE_p_void.getCPtr(value));
  }

  public SWIGTYPE_p_void getCb_info() {
    long cPtr = GLPKJNI.glp_iocp_cb_info_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_void(cPtr, false);
  }

  public void setCb_size(int value) {
    GLPKJNI.glp_iocp_cb_size_set(swigCPtr, this, value);
  }

  public int getCb_size() {
    return GLPKJNI.glp_iocp_cb_size_get(swigCPtr, this);
  }

  public void setPp_tech(int value) {
    GLPKJNI.glp_iocp_pp_tech_set(swigCPtr, this, value);
  }

  public int getPp_tech() {
    return GLPKJNI.glp_iocp_pp_tech_get(swigCPtr, this);
  }

  public void setMip_gap(double value) {
    GLPKJNI.glp_iocp_mip_gap_set(swigCPtr, this, value);
  }

  public double getMip_gap() {
    return GLPKJNI.glp_iocp_mip_gap_get(swigCPtr, this);
  }

  public void setMir_cuts(int value) {
    GLPKJNI.glp_iocp_mir_cuts_set(swigCPtr, this, value);
  }

  public int getMir_cuts() {
    return GLPKJNI.glp_iocp_mir_cuts_get(swigCPtr, this);
  }

  public void setGmi_cuts(int value) {
    GLPKJNI.glp_iocp_gmi_cuts_set(swigCPtr, this, value);
  }

  public int getGmi_cuts() {
    return GLPKJNI.glp_iocp_gmi_cuts_get(swigCPtr, this);
  }

  public void setCov_cuts(int value) {
    GLPKJNI.glp_iocp_cov_cuts_set(swigCPtr, this, value);
  }

  public int getCov_cuts() {
    return GLPKJNI.glp_iocp_cov_cuts_get(swigCPtr, this);
  }

  public void setClq_cuts(int value) {
    GLPKJNI.glp_iocp_clq_cuts_set(swigCPtr, this, value);
  }

  public int getClq_cuts() {
    return GLPKJNI.glp_iocp_clq_cuts_get(swigCPtr, this);
  }

  public void setPresolve(int value) {
    GLPKJNI.glp_iocp_presolve_set(swigCPtr, this, value);
  }

  public int getPresolve() {
    return GLPKJNI.glp_iocp_presolve_get(swigCPtr, this);
  }

  public void setBinarize(int value) {
    GLPKJNI.glp_iocp_binarize_set(swigCPtr, this, value);
  }

  public int getBinarize() {
    return GLPKJNI.glp_iocp_binarize_get(swigCPtr, this);
  }

  public void setFp_heur(int value) {
    GLPKJNI.glp_iocp_fp_heur_set(swigCPtr, this, value);
  }

  public int getFp_heur() {
    return GLPKJNI.glp_iocp_fp_heur_get(swigCPtr, this);
  }

  public void setPs_heur(int value) {
    GLPKJNI.glp_iocp_ps_heur_set(swigCPtr, this, value);
  }

  public int getPs_heur() {
    return GLPKJNI.glp_iocp_ps_heur_get(swigCPtr, this);
  }

  public void setPs_tm_lim(int value) {
    GLPKJNI.glp_iocp_ps_tm_lim_set(swigCPtr, this, value);
  }

  public int getPs_tm_lim() {
    return GLPKJNI.glp_iocp_ps_tm_lim_get(swigCPtr, this);
  }

  public void setSr_heur(int value) {
    GLPKJNI.glp_iocp_sr_heur_set(swigCPtr, this, value);
  }

  public int getSr_heur() {
    return GLPKJNI.glp_iocp_sr_heur_get(swigCPtr, this);
  }

  public void setUse_sol(int value) {
    GLPKJNI.glp_iocp_use_sol_set(swigCPtr, this, value);
  }

  public int getUse_sol() {
    return GLPKJNI.glp_iocp_use_sol_get(swigCPtr, this);
  }

  public void setSave_sol(String value) {
    GLPKJNI.glp_iocp_save_sol_set(swigCPtr, this, value);
  }

  public String getSave_sol() {
    return GLPKJNI.glp_iocp_save_sol_get(swigCPtr, this);
  }

  public void setAlien(int value) {
    GLPKJNI.glp_iocp_alien_set(swigCPtr, this, value);
  }

  public int getAlien() {
    return GLPKJNI.glp_iocp_alien_get(swigCPtr, this);
  }

  public void setFlip(int value) {
    GLPKJNI.glp_iocp_flip_set(swigCPtr, this, value);
  }

  public int getFlip() {
    return GLPKJNI.glp_iocp_flip_get(swigCPtr, this);
  }

  public void setFoo_bar(SWIGTYPE_p_double value) {
    GLPKJNI.glp_iocp_foo_bar_set(swigCPtr, this, SWIGTYPE_p_double.getCPtr(value));
  }

  public SWIGTYPE_p_double getFoo_bar() {
    long cPtr = GLPKJNI.glp_iocp_foo_bar_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_double(cPtr, false);
  }

  public glp_iocp() {
    this(GLPKJNI.new_glp_iocp(), true);
  }

}
