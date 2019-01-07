
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Condition implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7438769343413390506L;
    private String deviceId;// 是 string 设备ID
    private String wifiId;// 是 string 通信模块ID
    private int statusValue;// 是 number 状态指令值
    private String OperateType;// 是 string 操作类型：1 - 等于(=)2 - 不等于(<>)3 - 大于(>) 4
                               // - 小于(<)5 - 大于等于(>=)6 - 小于等于(<=)
    private int statusParamValue;// 是 number 状态参数值

    public String getDeviceId() {
        return deviceId;
    }

    public String getWifiId() {
        return wifiId;
    }

    public int getStatusValue() {
        return statusValue;
    }

    public String getOperateType() {
        return OperateType;
    }

    public int getStatusParamValue() {
        return statusParamValue;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public void setStatusValue(int statusValue) {
        this.statusValue = statusValue;
    }

    public void setOperateType(String operateType) {
        OperateType = operateType;
    }

    public void setStatusParamValue(int statusParamValue) {
        this.statusParamValue = statusParamValue;
    }

}
