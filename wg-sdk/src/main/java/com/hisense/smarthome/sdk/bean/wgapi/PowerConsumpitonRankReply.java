
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class PowerConsumpitonRankReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 9033044601738192940L;
    private String rank;// 排行百分比，如：20.00%、0.01%

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
