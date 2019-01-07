
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class HomeQrCodeSessionKeyReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 4370262505767504917L;
    private String sessionKey;// 标识二维码有效期用的sessionkey

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
