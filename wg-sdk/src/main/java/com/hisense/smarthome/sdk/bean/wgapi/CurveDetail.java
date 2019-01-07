
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class CurveDetail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8194916467323722723L;
    private int order;// number 睡眠曲线的顺序号
    private List<CmdDetail> cmdList;// 睡眠曲线各个点的指令列表

    public int getOrder() {
        return order;
    }

    public List<CmdDetail> getCmdList() {
        return cmdList;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setCmdList(List<CmdDetail> cmdList) {
        this.cmdList = cmdList;
    }

}
