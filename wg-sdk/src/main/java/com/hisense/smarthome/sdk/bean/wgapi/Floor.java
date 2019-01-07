
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class Floor implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4513902554932457062L;
    private long floorId;// 楼层ID
    private String floorName;// 楼层名称
    private String floorDesc;// 楼层描述
    private int floorType;// number 楼层类型1- 默认楼层2- 普通楼层
    private List<Room> roomList;// 该楼层包含的房间列表

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorDesc() {
        return floorDesc;
    }

    public void setFloorDesc(String floorDesc) {
        this.floorDesc = floorDesc;
    }

    public int getFloorType() {
        return floorType;
    }

    public void setFloorType(int floorType) {
        this.floorType = floorType;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

}
