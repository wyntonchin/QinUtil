
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Room implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1125956576714367342L;
    private int roomId;// 房间ID
    private String roomName;// 房间名称
    private String roomDesc;// 房间描述
    private long homeId;// 房间所属的家庭ID
    private int homeName;// 房间所属的家庭名称
    private long floorId;// 楼层ID
    private String roomImgUrl;// 房间图片地址
    private String roomImgFlag;//房间图片标记

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public long getHomeId() {
        return homeId;
    }

    public int getHomeName() {
        return homeName;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }

    public void setHomeName(int homeName) {
        this.homeName = homeName;
    }

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

    public String getRoomImgUrl() {
        return roomImgUrl;
    }

    public void setRoomImgUrl(String roomImgUrl) {
        this.roomImgUrl = roomImgUrl;
    }

    public String getRoomImgFlag() {
        return roomImgFlag;
    }

    public void setRoomImgFlag(String roomImgFlag) {
        this.roomImgFlag = roomImgFlag;
    }

}
