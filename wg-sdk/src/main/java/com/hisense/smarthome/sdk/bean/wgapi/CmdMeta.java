
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class CmdMeta implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7345951040393929750L;
    private String cmdName;// 指令名称
    private String cmdDesc;// 指令描述
    private String cmdCode;// 指令编码
    private int cmdValue;// 实际的指令值，空调库中的宏定义
    private int cmdParm;// 指令参数
    private int taskFlag;// 指令是否能够用于定时任务，1：可以用于定时任务；0：不能用于定时任务
    private String parmType;// 指令参数的类型，1：布尔型；2：枚举型；3：数字型
    private int cmdId;// number 指令标识
    private List<CmdParmMeta> cmdParmMetaList;// 指令参数列表
    private String statusValueList;// 此指令操作后影响的状态，可能影响多个状态，影响多个状态时，多个状态间用逗号分隔，比如“3,5,7,9”
    private String statusParmList;// 此指令操作后影响的状态参数值列表，JSON格式[{"statusValue":3,"statusParmValue":[1,2]},{"statusValue":5,"statusParmValue":[1,2]}]

    public String getCmdName() {
        return cmdName;
    }

    public String getCmdDesc() {
        return cmdDesc;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public int getCmdValue() {
        return cmdValue;
    }

    public int getCmdParm() {
        return cmdParm;
    }

    public int getTaskFlag() {
        return taskFlag;
    }

    public String getParmType() {
        return parmType;
    }

    public List<CmdParmMeta> getCmdParmMetaList() {
        return cmdParmMetaList;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public void setCmdDesc(String cmdDesc) {
        this.cmdDesc = cmdDesc;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public void setCmdValue(int cmdValue) {
        this.cmdValue = cmdValue;
    }

    public void setCmdParm(int cmdParm) {
        this.cmdParm = cmdParm;
    }

    public void setTaskFlag(int taskFlag) {
        this.taskFlag = taskFlag;
    }

    public void setParmType(String parmType) {
        this.parmType = parmType;
    }

    public void setCmdParmMetaList(List<CmdParmMeta> cmdParmMetaList) {
        this.cmdParmMetaList = cmdParmMetaList;
    }

    public int getCmdId() {
        return cmdId;
    }

    public void setCmdId(int cmdId) {
        this.cmdId = cmdId;
    }

    public String getStatusValueList() {
        return statusValueList;
    }

    public void setStatusValueList(String statusValueList) {
        this.statusValueList = statusValueList;
    }

    public String getStatusParmList() {
        return statusParmList;
    }

    public void setStatusParmList(String statusParmList) {
        this.statusParmList = statusParmList;
    }

}
