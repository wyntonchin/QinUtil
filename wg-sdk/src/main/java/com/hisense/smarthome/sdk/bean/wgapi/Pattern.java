
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class Pattern implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1735583055504327412L;
    private String patternCode;// 模式编码
    private String patternName;// string 模式名称
    private String patternDesc;// string 模式描述
    private int defaultRunTime;// 默认运行时间（分钟）
    private List<Function> functionList;// 功能列表

    public String getPatternCode() {
        return patternCode;
    }

    public String getPatternName() {
        return patternName;
    }

    public String getPatternDesc() {
        return patternDesc;
    }

    public int getDefaultRunTime() {
        return defaultRunTime;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setPatternCode(String patternCode) {
        this.patternCode = patternCode;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public void setPatternDesc(String patternDesc) {
        this.patternDesc = patternDesc;
    }

    public void setDefaultRunTime(int defaultRunTime) {
        this.defaultRunTime = defaultRunTime;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

}
