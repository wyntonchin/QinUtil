
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class WGCustomer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5398784976977866848L;
    private String platformId;// 平台id: 0：海信 1：微信
    private String customerId;// 客户id
    private String nickName;// 用户昵称
    private String headImgUrl;// 用户头像Url
    private long bindTime;// 绑定时间

    public String getPlatformId() {
        return platformId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public long getBindTime() {
        return bindTime;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public void setBindTime(long bindTime) {
        this.bindTime = bindTime;
    }

}
