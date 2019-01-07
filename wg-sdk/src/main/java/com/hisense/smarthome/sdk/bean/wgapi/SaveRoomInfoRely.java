
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class SaveRoomInfoRely extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 2942180990079094798L;
    private int roomId;// 房间id

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

}
