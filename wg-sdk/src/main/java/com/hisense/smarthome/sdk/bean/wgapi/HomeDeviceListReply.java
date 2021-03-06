
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class HomeDeviceListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 9146542934591700206L;
    private List<Device> deviceList;// 设备列表
    private String originalData;// 原始数据
    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }
    
}
