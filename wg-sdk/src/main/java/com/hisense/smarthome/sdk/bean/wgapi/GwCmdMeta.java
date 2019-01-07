package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class GwCmdMeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5712774837989000263L;
	private String deviceSubTypeCode;
	private List<CmdMeta> cmdMetaList;
	public String getDeviceSubTypeCode() {
		return deviceSubTypeCode;
	}
	public void setDeviceSubTypeCode(String deviceSubTypeCode) {
		this.deviceSubTypeCode = deviceSubTypeCode;
	}
	public List<CmdMeta> getCmdMetaList() {
		return cmdMetaList;
	}
	public void setCmdMetaList(List<CmdMeta> cmdMetaList) {
		this.cmdMetaList = cmdMetaList;
	} 
	
}
