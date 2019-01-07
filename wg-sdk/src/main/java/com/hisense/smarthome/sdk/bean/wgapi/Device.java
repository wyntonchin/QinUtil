
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Device implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6602839888098565444L;
    private String wifiId;// string wifi模块的ID
    private String wgDeviceId;// string 白电设备的ID
    private String areaName;// string 所属地区
    private String deviceNickName;// string 设备昵称
    private String deviceTypeName;// string 设备类型名称(如：空调、冰箱等)
    private String deviceCode;// string 设备类型
    private String deviceName;// 设备类型名称
    private String brandName;// 品牌名称
    private String brandCode;// 品牌代码
    private String deviceTypeCode;// 设备类型代码
    private String extInfo;// json 设备类型扩展信息,{ “washerType”:”” }目前只有
                           // washerType，对应的value：” 0”：滚筒 “1”：波轮
    private int isVirtualDevice;// 虚拟设备 0:否；1：是
    private String bindDeviceId;// 绑定设备的ID
    private String bindDeviceName;// 绑定设备的名称
    private String barCode;// 设备的条码
    private String deviceId;// string 设备的ID
    private int status;// number 状态：1：正常2：屏蔽
    private long homeId;// 家庭Id
    private String homeName;// 家庭名称
    private String bindTime;// 绑定时间（格式:yyyy-mm-dd hh:mm:ss）
    private String wifiModuleType;// string 通信模块类别：1-wif模块2-智能网关3-中央空调总控
    private String mutiDevice;// string 0 – 一对一的通信模块1-一拖多的通信模块
    private String deviceSubTypeCode;// string 智能网关下的设备子编码，当
                                     // deviceTypeCode为010时，使用该字段标识具体的智能网关下的设备。
    private String displayStatusList;// 设备类别要显示的状态key列表[1,2,3,5,……]
    private String displayCmtList;// list<Integer>
                                  // 设备类别要显示的指令key列表例如：[1,2,3,5,……]
    private int roomId;// 设备所属房间Id
    private String roomName;// 设备所属房间名称
    private long exeTime;// 场景执行时间utc

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getWifiId() {
        return wifiId;
    }

    public String getWgDeviceId() {
        return wgDeviceId;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getDeviceNickName() {
        return deviceNickName;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public void setWgDeviceId(String wgDeviceId) {
        this.wgDeviceId = wgDeviceId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setDeviceNickName(String deviceNickName) {
        this.deviceNickName = deviceNickName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getIsVirtualDevice() {
        return isVirtualDevice;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setIsVirtualDevice(int isVirtualDevice) {
        this.isVirtualDevice = isVirtualDevice;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getBindDeviceId() {
        return bindDeviceId;
    }

    public void setBindDeviceId(String bindDeviceId) {
        this.bindDeviceId = bindDeviceId;
    }

    public String getBindDeviceName() {
        return bindDeviceName;
    }

    public void setBindDeviceName(String bindDeviceName) {
        this.bindDeviceName = bindDeviceName;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getStatus() {
        return status;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getBindTime() {
        return bindTime;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime;
    }

    public String getWifiModuleType() {
        return wifiModuleType;
    }

    public void setWifiModuleType(String wifiModuleType) {
        this.wifiModuleType = wifiModuleType;
    }

    public String getMutiDevice() {
        return mutiDevice;
    }

    public void setMutiDevice(String mutiDevice) {
        this.mutiDevice = mutiDevice;
    }

    public String getDeviceSubTypeCode() {
        return deviceSubTypeCode;
    }

    public void setDeviceSubTypeCode(String deviceSubTypeCode) {
        this.deviceSubTypeCode = deviceSubTypeCode;
    }

    public String getDisplayStatusList() {
        return displayStatusList;
    }

    public void setDisplayStatusList(String displayStatusList) {
        this.displayStatusList = displayStatusList;
    }

    public String getDisplayCmtList() {
        return displayCmtList;
    }

    public void setDisplayCmtList(String displayCmtList) {
        this.displayCmtList = displayCmtList;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public long getExeTime() {
        return exeTime;
    }

    public void setExeTime(long exeTime) {
        this.exeTime = exeTime;
    }

}
