
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class SleepCurve implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6023758840013928720L;
    private String curveCode;// string 睡眠曲线标识
    private String curveName;// string 睡眠曲线名称
    private int curveType;// number 睡眠曲线类型：1：预置2：自定义
    private int runFalg;// number 运行标识：1：运行2：停止

    public String getCurveCode() {
        return curveCode;
    }

    public String getCurveName() {
        return curveName;
    }

    public int getCurveType() {
        return curveType;
    }

    public int getRunFalg() {
        return runFalg;
    }

    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode;
    }

    public void setCurveName(String curveName) {
        this.curveName = curveName;
    }

    public void setCurveType(int curveType) {
        this.curveType = curveType;
    }

    public void setRunFalg(int runFalg) {
        this.runFalg = runFalg;
    }

}
