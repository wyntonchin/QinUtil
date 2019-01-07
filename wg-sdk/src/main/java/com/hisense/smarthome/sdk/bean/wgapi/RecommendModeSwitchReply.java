
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class RecommendModeSwitchReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 7714264523948019600L;
    private String switchFlag;

    public String getSwitchFlag() {
        return switchFlag;
    }

    public void setSwitchFlag(String switchFlag) {
        this.switchFlag = switchFlag;
    }

}
