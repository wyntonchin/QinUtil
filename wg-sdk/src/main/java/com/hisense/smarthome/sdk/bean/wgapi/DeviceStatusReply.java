
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceStatusReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 175991749820742639L;
    private List<DeviceStatus> deviceStatusList;// 描述设备状态的字符串列表

    public List<DeviceStatus> getDeviceStatusList() {
        return deviceStatusList;
    }

    public void setDeviceStatusList(List<DeviceStatus> deviceStatusList) {
        this.deviceStatusList = deviceStatusList;
    }

}
