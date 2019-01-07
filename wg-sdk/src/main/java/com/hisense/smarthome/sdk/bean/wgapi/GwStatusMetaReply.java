package com.hisense.smarthome.sdk.bean.wgapi;

import java.util.List;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class GwStatusMetaReply extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -967274195737683949L;
	private List<GwStatusMeta> gwstatusMetaList;
	private String originalData;// 原始数据
	public List<GwStatusMeta> getGwStatusMetaList() {
		return gwstatusMetaList;
	}

	public void setGwStatusMetaList(List<GwStatusMeta> gwStatusMetaList) {
		this.gwstatusMetaList = gwStatusMetaList;
	}

	public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
	
}
