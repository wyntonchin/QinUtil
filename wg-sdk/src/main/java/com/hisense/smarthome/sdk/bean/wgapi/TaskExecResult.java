
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class TaskExecResult implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6270072452359039300L;
    private String wifiId;// wifi模块的ID
    private String deviceId;// 空调条码
    private String actualExecuteDate;// 任务实际执行时间
    private int cmdValue;// 实际执行指令值
    private int cmdParm;// 指令参数
    private int resultCode;// 执行结果，1：正常下发指令；2：指令状态互斥，未执行；9：处理错误
    private String resultMsg;// 执行结果描述

    public String getWifiId() {
        return wifiId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getActualExecuteDate() {
        return actualExecuteDate;
    }

    public int getCmdValue() {
        return cmdValue;
    }

    public int getCmdParm() {
        return cmdParm;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setActualExecuteDate(String actualExecuteDate) {
        this.actualExecuteDate = actualExecuteDate;
    }

    public void setCmdValue(int cmdValue) {
        this.cmdValue = cmdValue;
    }

    public void setCmdParm(int cmdParm) {
        this.cmdParm = cmdParm;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
