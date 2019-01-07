
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Status implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8572373396343448185L;
    private String statusName;// 状态名称
    private String statusDesc;// 状态描述
    private String statusCode;// 状态编码
    private int statusValue;// 状态指令值
    private int statusParamValue;// 状态参数值

    public String getStatusName() {
        return statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public int getStatusValue() {
        return statusValue;
    }

    public int getStatusParamValue() {
        return statusParamValue;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusValue(int statusValue) {
        this.statusValue = statusValue;
    }

    public void setStatusParamValue(int statusParamValue) {
        this.statusParamValue = statusParamValue;
    }

}
