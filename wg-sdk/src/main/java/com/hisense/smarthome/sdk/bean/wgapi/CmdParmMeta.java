
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class CmdParmMeta implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6891661232919544541L;
    private String parmName;// 指令参数名称
    private String parmDesc;// 指令参数描述
    private int parmValue;// 实际的参数值
    private int taskFlag;// number 指令是否可以在终端展示：1：可以展示0：不可以展示

    public String getParmName() {
        return parmName;
    }

    public String getParmDesc() {
        return parmDesc;
    }

    public int getParmValue() {
        return parmValue;
    }

    public void setParmName(String parmName) {
        this.parmName = parmName;
    }

    public void setParmDesc(String parmDesc) {
        this.parmDesc = parmDesc;
    }

    public void setParmValue(int parmValue) {
        this.parmValue = parmValue;
    }

    public int getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(int taskFlag) {
        this.taskFlag = taskFlag;
    }

}
