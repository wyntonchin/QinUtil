
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class DeviceStatus implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1263569424506878331L;
    private String wifiId;// 通信模块Id
    private String deviceId;// 设备id
    private String deviceStatus;// 设备原始状态
    private List<Status> statusList;// 设备逻辑状态列表
    private int statusVersion;//设备原始状态版本号
    private int cmdVersion;//设备指令版本号
    public String getWifiId() {
        return wifiId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public int getStatusVersion() {
        return statusVersion;
    }

    public void setStatusVersion(int statusVersion) {
        this.statusVersion = statusVersion;
    }

    public int getCmdVersion() {
        return cmdVersion;
    }

    public void setCmdVersion(int cmdVersion) {
        this.cmdVersion = cmdVersion;
    }
}
