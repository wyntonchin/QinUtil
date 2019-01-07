
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceFunctionListReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 1674728486492644079L;
    private String deviceName;// 设备名称
    private int functionCount;// 功能列表数量
    private List<Function> functionList;// 功能列表
    private String originalData;// 原始数据

    public String getDeviceName() {
        return deviceName;
    }

    public int getFunctionCount() {
        return functionCount;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setFunctionCount(int functionCount) {
        this.functionCount = functionCount;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

}
