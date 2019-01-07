
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class JoinHomeApplyListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -9040268347642070826L;
    private List<Apply> applyList;// 申请列表

    public List<Apply> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<Apply> applyList) {
        this.applyList = applyList;
    }

}
