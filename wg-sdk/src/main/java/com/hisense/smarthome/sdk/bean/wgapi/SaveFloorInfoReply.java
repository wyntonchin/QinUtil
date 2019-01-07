
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class SaveFloorInfoReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -7160219406142957269L;
    private long floorId;// 楼层id

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

}
