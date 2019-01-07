
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class PowerConsumpitonReply extends BaseInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 776994719607426769L;
    private int queryType;// number 查询类型：1：按照周查询每天的耗电量0：按照年查询每月的耗电量
    private List<PowerDetail> queryResult;// 耗电量详细列表

    public int getQueryType() {
        return queryType;
    }

    public List<PowerDetail> getQueryResult() {
        return queryResult;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public void setQueryResult(List<PowerDetail> queryResult) {
        this.queryResult = queryResult;
    }
}
