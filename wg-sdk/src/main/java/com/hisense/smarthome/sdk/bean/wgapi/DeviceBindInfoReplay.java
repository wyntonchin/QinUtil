
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceBindInfoReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 7906610389061511398L;
    private int bindFlag;// number 0:未绑定；1：已绑定
    private String bindDeviceId;// string 绑定设备的ID
    private String bindDeviceName;// string 绑定设备的名称
    private int isVirtualDevice; // int 虚拟设备 0:否；1：是
    private long bindHomeId;// number 已经绑定的家庭Id
    private String bindHomeName;// 已经绑定的家庭名称
    private List<Device> bindDeviceList;// 绑定的设备列表

    public int getBindFlag() {
        return bindFlag;
    }

    public String getBindDeviceId() {
        return bindDeviceId;
    }

    public String getBindDeviceName() {
        return bindDeviceName;
    }

    public void setBindFlag(int bindFlag) {
        this.bindFlag = bindFlag;
    }

    public void setBindDeviceId(String bindDeviceId) {
        this.bindDeviceId = bindDeviceId;
    }

    public void setBindDeviceName(String bindDeviceName) {
        this.bindDeviceName = bindDeviceName;
    }

    public int getIsVirtualDevice() {
        return isVirtualDevice;
    }

    public void setIsVirtualDevice(int isVirtualDevice) {
        this.isVirtualDevice = isVirtualDevice;
    }

    public long getBindHomeId() {
        return bindHomeId;
    }

    public String getBindHomeName() {
        return bindHomeName;
    }

    public List<Device> getBindDeviceList() {
        return bindDeviceList;
    }

    public void setBindHomeId(long bindHomeId) {
        this.bindHomeId = bindHomeId;
    }

    public void setBindHomeName(String bindHomeName) {
        this.bindHomeName = bindHomeName;
    }

    public void setBindDeviceList(List<Device> bindDeviceList) {
        this.bindDeviceList = bindDeviceList;
    }

}
