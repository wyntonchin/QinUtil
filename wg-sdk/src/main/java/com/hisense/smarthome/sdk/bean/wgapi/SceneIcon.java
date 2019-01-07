
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class SceneIcon implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1476367741881203946L;
    private long sceneIconId;// number 场景图标ID
    private String sceneIconName;// string 场景图标名称
    private String sceneIconDesc;// string 场景图标描述
    private String sceneIconUrl;// string 场景图标URL

    public long getSceneIconId() {
        return sceneIconId;
    }

    public void setSceneIconId(long sceneIconId) {
        this.sceneIconId = sceneIconId;
    }

    public String getSceneIconName() {
        return sceneIconName;
    }

    public void setSceneIconName(String sceneIconName) {
        this.sceneIconName = sceneIconName;
    }

    public String getSceneIconDesc() {
        return sceneIconDesc;
    }

    public void setSceneIconDesc(String sceneIconDesc) {
        this.sceneIconDesc = sceneIconDesc;
    }

    public String getSceneIconUrl() {
        return sceneIconUrl;
    }

    public void setSceneIconUrl(String sceneIconUrl) {
        this.sceneIconUrl = sceneIconUrl;
    }

}
