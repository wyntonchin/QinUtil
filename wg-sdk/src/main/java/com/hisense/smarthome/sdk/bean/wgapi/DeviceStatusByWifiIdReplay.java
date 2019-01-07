
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class DeviceStatusByWifiIdReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -740024929984497902L;
    private String deviceStatus;// 描述设备状态的字符串

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

}
