
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class Function implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2562330093700335473L;
    private String functionCode;// string 功能项编码
    private String functionName;// string 功能项名称
    private String functionDesc;// number 功能项描述
    private String functionParameterType;// 功能项参数类型,1-布尔值 2-枚举 3-数值
    private String cmdCode;// string 指令编码
    private int cmdValue;// number 指令值，空调库中的宏定义
    private List<FunctionParameter> functionParameterList;// 功能项参数值列表

    public String getFunctionCode() {
        return functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }

    public String getFunctionParameterType() {
        return functionParameterType;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public int getCmdValue() {
        return cmdValue;
    }

    public List<FunctionParameter> getFunctionParameterList() {
        return functionParameterList;
    }

    public void setFunctionParameterType(String functionParameterType) {
        this.functionParameterType = functionParameterType;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public void setCmdValue(int cmdValue) {
        this.cmdValue = cmdValue;
    }

    public void setFunctionParameterList(List<FunctionParameter> functionParameterList) {
        this.functionParameterList = functionParameterList;
    }

}
