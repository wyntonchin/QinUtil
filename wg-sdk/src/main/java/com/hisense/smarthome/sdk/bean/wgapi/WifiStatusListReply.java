package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;


public class WifiStatusListReply extends BaseInfo {

    private static final long serialVersionUID = 4139234204605064939L;
    //private String wifiStatusList;
    private List<WifiStatus> wifiListStatus; //通信模块状态列表列表

    public List<WifiStatus> getWifiStatusList() {
        return wifiListStatus;
    }

    public void setWifiStatusList(List<WifiStatus> wifiStatusList) {
        this.wifiListStatus = wifiStatusList;
    }
    /*public String getWifiStatusList() {
        return wifiStatusList;
	}
	public void setWifiStatusList(String wifiStatusList) {
		this.wifiStatusList = wifiStatusList;
	}*/


}
