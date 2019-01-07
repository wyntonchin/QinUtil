
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class CmdMetaReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -9028912505416342748L;
    private int count;// 设备的指令列表个数
    private List<CmdMeta> cmdMetaList;// 指令列表
    private String originalData;// 原始数据
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CmdMeta> getCmdMetaList() {
        return cmdMetaList;
    }

    public void setCmdMetaList(List<CmdMeta> cmdMetaList) {
        this.cmdMetaList = cmdMetaList;
    }

	public String getOriginalData() {
		return originalData;
	}

	public void setOriginalData(String originalData) {
		this.originalData = originalData;
	}
    
}
