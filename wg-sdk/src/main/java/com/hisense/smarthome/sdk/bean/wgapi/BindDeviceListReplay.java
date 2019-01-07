
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class BindDeviceListReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -1195798107223177668L;
    private int deviceCount;// number 绑定设备的数量
    private List<Device> deviceList;// 绑定设备的列表

    public int getDeviceCount() {
        return deviceCount;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

}
