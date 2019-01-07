
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class RepairTaskAreaReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 7128562985624832623L;
    private List<CrmArea> areas;// 地区列表

    public List<CrmArea> getAreas() {
        return areas;
    }

    public void setAreas(List<CrmArea> areas) {
        this.areas = areas;
    }
}
