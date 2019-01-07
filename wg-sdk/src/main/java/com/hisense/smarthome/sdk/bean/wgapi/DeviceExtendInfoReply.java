
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class DeviceExtendInfoReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 2674311302679668329L;
    private int airCleanFlag;// number 是否具备净化功能：1：具备，0：不具备
    private String airCleanBuyUrl;// string 净化设备购买网址

    public int getAirCleanFlag() {
        return airCleanFlag;
    }

    public String getAirCleanBuyUrl() {
        return airCleanBuyUrl;
    }

    public void setAirCleanFlag(int airCleanFlag) {
        this.airCleanFlag = airCleanFlag;
    }

    public void setAirCleanBuyUrl(String airCleanBuyUrl) {
        this.airCleanBuyUrl = airCleanBuyUrl;
    }

}
