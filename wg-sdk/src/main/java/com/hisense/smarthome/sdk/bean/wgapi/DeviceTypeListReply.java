
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceTypeListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 4504778511734251277L;
    private List<DeviceType> deviceTypeList;// 设备列表列表
    private String originalData;// 原始数据
    private int currentVersion;//列表版本号，如果不是最新版本号或者为0时强制获取最新列表；如果已是最新版本，则不下发列表
    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
    }

    public void setDeviceTypeList(List<DeviceType> deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

	public int getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(int currentVersion) {
		this.currentVersion = currentVersion;
	}
    
}
