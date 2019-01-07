
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class HistoryDeviceListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -4979648071824394433L;
    private List<Device> deviceList;// 设备列表

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
