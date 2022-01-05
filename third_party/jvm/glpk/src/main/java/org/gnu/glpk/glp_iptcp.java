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
public class glp_iptcp {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected glp_iptcp(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(glp_iptcp obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        GLPKJNI.delete_glp_iptcp(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setMsg_lev(int value) {
    GLPKJNI.glp_iptcp_msg_lev_set(swigCPtr, this, value);
  }

  public int getMsg_lev() {
    return GLPKJNI.glp_iptcp_msg_lev_get(swigCPtr, this);
  }

  public void setOrd_alg(int value) {
    GLPKJNI.glp_iptcp_ord_alg_set(swigCPtr, this, value);
  }

  public int getOrd_alg() {
    return GLPKJNI.glp_iptcp_ord_alg_get(swigCPtr, this);
  }

  public void setFoo_bar(SWIGTYPE_p_double value) {
    GLPKJNI.glp_iptcp_foo_bar_set(swigCPtr, this, SWIGTYPE_p_double.getCPtr(value));
  }

  public SWIGTYPE_p_double getFoo_bar() {
    long cPtr = GLPKJNI.glp_iptcp_foo_bar_get(swigCPtr, this);
    return (cPtr == 0) ? null : new SWIGTYPE_p_double(cPtr, false);
  }

  public glp_iptcp() {
    this(GLPKJNI.new_glp_iptcp(), true);
  }

}
