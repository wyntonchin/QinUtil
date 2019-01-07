
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceStatusMetaReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 2859317693957326937L;
    private int count;// 设备的指令列表个数
    private List<StatusMeta> statusMetaList;// 状态列表
    private String originalData;// 原始数据
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<StatusMeta> getStatusMetaList() {
        return statusMetaList;
    }

    public void setStatusMetaList(List<StatusMeta> statusMetaList) {
        this.statusMetaList = statusMetaList;
    }

	public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
    
}
