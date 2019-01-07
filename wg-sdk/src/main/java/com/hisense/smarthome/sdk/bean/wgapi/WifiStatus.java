package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

/*
 * Ather:caoxiuxia
 */
public class WifiStatus implements Serializable {
    private static final long serialVersionUID = -3305642106674032147L;
    private String wifiId;
    private int status = 0;//1,在线；2，离线

    public String getWifiId() {
        return wifiId;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public int getWifiStatus() {
        return status;
    }

    public void setWifiStatus(int wifiStatus) {
        this.status = wifiStatus;
    }

}
