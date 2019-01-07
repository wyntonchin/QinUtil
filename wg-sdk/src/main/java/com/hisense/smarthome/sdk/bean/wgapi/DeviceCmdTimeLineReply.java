
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class DeviceCmdTimeLineReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -3116375906443651979L;

    private List<Cmd2> cmdList;// 指令执行或预约执行的列表

    public List<Cmd2> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Cmd2> cmdList) {
        this.cmdList = cmdList;
    }

}
