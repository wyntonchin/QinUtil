
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class AllFloorRoomListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 117318729532801124L;
    private List<Floor> floorList;// 楼层列表
    private String originalData;// 原始数据
    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

	public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
    
}
