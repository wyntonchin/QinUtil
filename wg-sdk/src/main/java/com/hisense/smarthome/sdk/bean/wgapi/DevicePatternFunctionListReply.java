
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DevicePatternFunctionListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 6697752307424136352L;

    private String deviceName;// 设备名称
    private int patternCount;// 模式列表的数量
    private List<Pattern> patternList;// 模式列表
    private String originalData;// 原始数据

    public String getDeviceName() {
        return deviceName;
    }

    public int getPatternCount() {
        return patternCount;
    }

    public List<Pattern> getPatternList() {
        return patternList;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setPatternCount(int patternCount) {
        this.patternCount = patternCount;
    }

    public void setPatternList(List<Pattern> patternList) {
        this.patternList = patternList;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

}
