
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class FloorRoomListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -3794559830424603537L;
    private List<Room> roomList;// 房间列表

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
