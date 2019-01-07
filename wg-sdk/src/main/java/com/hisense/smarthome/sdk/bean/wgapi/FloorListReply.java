
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class FloorListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -7125878006332228051L;
    private List<Floor> floorList;// 楼层列表

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

}
