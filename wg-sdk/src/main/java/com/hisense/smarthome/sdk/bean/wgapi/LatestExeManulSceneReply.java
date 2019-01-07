
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class LatestExeManulSceneReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -1306829381194794117L;
    private List<Scene> sceneList;// 场景列表

    public List<Scene> getSceneList() {
        return sceneList;
    }

    public void setSceneList(List<Scene> sceneList) {
        this.sceneList = sceneList;
    }

}
