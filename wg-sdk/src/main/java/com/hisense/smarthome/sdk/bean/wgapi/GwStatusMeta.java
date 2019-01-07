package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class GwStatusMeta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2171192557123323003L;
	
	private String deviceSubTypeCode;
	private List<StatusMeta> statusMetaList;
	public String getDeviceSubTypeCode() {
		return deviceSubTypeCode;
	}
	public void setDeviceSubTypeCode(String deviceSubTypeCode) {
		this.deviceSubTypeCode = deviceSubTypeCode;
	}
	public List<StatusMeta> getStatusMetaList() {
		return statusMetaList;
	}
	public void setStatusMetaList(List<StatusMeta> statusMetaList) {
		this.statusMetaList = statusMetaList;
	}
	
	

}
