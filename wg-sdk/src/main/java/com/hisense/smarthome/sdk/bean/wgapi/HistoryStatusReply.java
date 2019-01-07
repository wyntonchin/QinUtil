
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class HistoryStatusReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 8949667665927447532L;
    private String deviceStatusList;

    public String getDeviceStatusList() {
        return deviceStatusList;
    }

    public void setDeviceStatusList(String deviceStatusList) {
        this.deviceStatusList = deviceStatusList;
    }

}
