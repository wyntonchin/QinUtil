
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class ScenePresetIconListReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 5630091570457612732L;
    private List<SceneIcon> sceneIconList;// 场景图标列表

    public List<SceneIcon> getSceneIconList() {
        return sceneIconList;
    }

    public void setSceneIconList(List<SceneIcon> sceneIconList) {
        this.sceneIconList = sceneIconList;
    }

}
