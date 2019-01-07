
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class FunctionParameter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1804812644796420300L;
    private String functionParameterValue;// string 功能项参数值
    private String functionParameterName;// string 功能项参数名称
    private String functionParameterDesc;// string 功能项参数描述

    public String getFunctionParameterValue() {
        return functionParameterValue;
    }

    public String getFunctionParameterName() {
        return functionParameterName;
    }

    public String getFunctionParameterDesc() {
        return functionParameterDesc;
    }

    public void setFunctionParameterValue(String functionParameterValue) {
        this.functionParameterValue = functionParameterValue;
    }

    public void setFunctionParameterName(String functionParameterName) {
        this.functionParameterName = functionParameterName;
    }

    public void setFunctionParameterDesc(String functionParameterDesc) {
        this.functionParameterDesc = functionParameterDesc;
    }

}
