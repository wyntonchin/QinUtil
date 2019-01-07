
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceCmdReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -4238807884755895081L;
    private int count;// 设备的指令列表个数
    private List<CmdMeta> cmdMetaList;// 指令列表

    public int getCount() {
        return count;
    }

    public List<CmdMeta> getCmdMetaList() {
        return cmdMetaList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCmdMetaList(List<CmdMeta> cmdMetaList) {
        this.cmdMetaList = cmdMetaList;
    }

}
