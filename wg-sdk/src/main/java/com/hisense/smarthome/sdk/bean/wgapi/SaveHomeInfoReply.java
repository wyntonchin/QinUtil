
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class SaveHomeInfoReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 1991434583108556956L;
    private long homeId;// 家庭id
    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }
    
}
