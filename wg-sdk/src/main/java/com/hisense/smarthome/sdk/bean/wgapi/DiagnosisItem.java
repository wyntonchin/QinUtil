
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class DiagnosisItem implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4217798186256541755L;
    private String itemCode;// 诊断项编码
    private String faultType;// 故障类型(1:普通，2：严重)
    private String itemName;// 诊断项名称
    private String itemDesc;// 诊断项描述

    public String getItemCode() {
        return itemCode;
    }

    public String getFaultType() {
        return faultType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

}
