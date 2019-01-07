
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class LatestExeDeviceReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -8581217712747351906L;
    private List<Device> deviceList;// 设备列表

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
