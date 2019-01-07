
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class UpdateSceneReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 2029992618605057736L;
    private int sceneId;// 场景Id

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }
}
