
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Cmd implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 567111196121014002L;
    private int cmdId;// 指令标识
    private int cmdParm;// 指令参数
    private int cmdOrder;// 指令执行顺序，从1开始，值小的先执行，在一个TaskItem里不可重复

    private String deviceId;// 是 设备ID
    private String wifiId;// 是 string 通信模块ID
    private int delayTime;//延时时间（s），默认为0
    
    public int getCmdId() {
        return cmdId;
    }

    public int getCmdParm() {
        return cmdParm;
    }

    public int getCmdOrder() {
        return cmdOrder;
    }

    public void setCmdId(int cmdId) {
        this.cmdId = cmdId;
    }

    public void setCmdParm(int cmdParm) {
        this.cmdParm = cmdParm;
    }

    public void setCmdOrder(int cmdOrder) {
        this.cmdOrder = cmdOrder;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getWifiId() {
        return wifiId;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}
    
}
