package com.hisense.smarthome.sdk.bean.wgapi;

import java.util.List;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

/*
 * Auther:CaoXiuxia
 */
public class GwCmdMetaReply extends BaseInfo{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5067545296797990088L;
	private List<GwCmdMeta> gwcmdMetaList;
	private String originalData;// 原始数据
	public List<GwCmdMeta> getGwcmdMetaList() {
		return gwcmdMetaList;
	}

	public void setGwcmdMetaList(List<GwCmdMeta> gwcmdMetaList) {
		this.gwcmdMetaList = gwcmdMetaList;
	}

	public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
	
}
