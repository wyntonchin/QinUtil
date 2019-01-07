
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceLogicStatusListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 7622302150350722747L;

    private List<DeviceStatus> deviceStatusList;// 逻辑状态列表

    public List<DeviceStatus> getDeviceStatusList() {
        return deviceStatusList;
    }

    public void setDeviceStatusList(List<DeviceStatus> deviceStatusList) {
        this.deviceStatusList = deviceStatusList;
    }

}
