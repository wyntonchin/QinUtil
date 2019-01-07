
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class ConditionManulSceneListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 3936621801391225871L;
    private List<Scene> sceneList;// 场景列表

    public List<Scene> getSceneList() {
        return sceneList;
    }

    public void setSceneList(List<Scene> sceneList) {
        this.sceneList = sceneList;
    }
}
