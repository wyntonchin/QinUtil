
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class RepirTaskBasicInfoReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -4802393712199644905L;

    private List<Fault> faults;// 故障类型
    private List<RepairStatus> statusList;// 状态列表，包含先后顺序。

    public List<Fault> getFaults() {
        return faults;
    }

    public List<RepairStatus> getStatusList() {
        return statusList;
    }

    public void setFaults(List<Fault> faults) {
        this.faults = faults;
    }

    public void setStatusList(List<RepairStatus> statusList) {
        this.statusList = statusList;
    }

}
