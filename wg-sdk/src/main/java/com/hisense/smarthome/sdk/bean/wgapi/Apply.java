
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Apply implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8169593361477740710L;
    private long applyId;// number 申请Id
    private String applyCustomerId;// string 申请人Id
    private String applyCustomerName;// string 申请人名称
    private long homeId;// number 家庭Id
    private String homeName;// string 家庭名称

    public long getApplyId() {
        return applyId;
    }

    public String getApplyCustomerId() {
        return applyCustomerId;
    }

    public String getApplyCustomerName() {
        return applyCustomerName;
    }

    public long getHomeId() {
        return homeId;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }

    public void setApplyCustomerId(String applyCustomerId) {
        this.applyCustomerId = applyCustomerId;
    }

    public void setApplyCustomerName(String applyCustomerName) {
        this.applyCustomerName = applyCustomerName;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

}
