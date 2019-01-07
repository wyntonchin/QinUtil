package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

/**
 * Created by Hisense on 2017/10/17.
 */

public class ConditionSimple implements Serializable {
    private static final long serialVersionUID = -5713326167713147251L;
    private String deviceTypeCode;//设备类别代码
    private String deviceSubTypeCode;//设备子类别代码，当设备为网关下设备时下发此字段

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceSubTypeCode() {
        return deviceSubTypeCode;
    }

    public void setDeviceSubTypeCode(String deviceSubTypeCode) {
        this.deviceSubTypeCode = deviceSubTypeCode;
    }
}
