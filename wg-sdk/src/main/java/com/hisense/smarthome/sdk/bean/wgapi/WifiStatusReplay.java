
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class WifiStatusReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 6179512687282436989L;
    private String deviceStatus;// 描述设备状态的字符串1：在线2：离线

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
