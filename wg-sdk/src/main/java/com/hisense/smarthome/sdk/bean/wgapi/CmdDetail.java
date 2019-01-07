
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class CmdDetail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7926410276926908306L;
    private int cmdId;// number 指令标识
    private int cmdParam;// number 指令参数

    public int getCmdId() {
        return cmdId;
    }

    public int getCmdParam() {
        return cmdParam;
    }

    public void setCmdId(int cmdId) {
        this.cmdId = cmdId;
    }

    public void setCmdParam(int cmdParam) {
        this.cmdParam = cmdParam;
    }

}
