
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class HomeListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -5152472550288426875L;

    private List<Home> homeList;// 家庭列表
    private String originalData;// 原始数据

    public List<Home> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<Home> homeList) {
        this.homeList = homeList;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

}
