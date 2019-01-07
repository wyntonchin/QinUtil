package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hisense on 2017/10/17.
 */

public class SimpleSceneTemplate implements Serializable {
    private static final long serialVersionUID = -2852385091608023294L;
    private int simpleSceneId;//简易场景模板ID
    private String simpleSceneName;//场景名称
    private String simpleSceneDesc;//场景描述
    private List<CmdSimple> cmdSimpleList;//场景动作设备类型列表
    private List<ConditionSimple> conditionSimpleList;//场景触发条件设备类型列表，触发条件只支持1个
    private String simpleSceneIconUrl;//场景图标URL
    private long simpleSceneIconId;//场景图标ID
    private int simpleSceneType;//场景类型    3：条件场景，1：手动场景

    public int getSimpleSceneId() {
        return simpleSceneId;
    }

    public void setSimpleSceneId(int simpleSceneId) {
        this.simpleSceneId = simpleSceneId;
    }

    public String getSimpleSceneName() {
        return simpleSceneName;
    }

    public void setSimpleSceneName(String simpleSceneName) {
        this.simpleSceneName = simpleSceneName;
    }

    public String getSimpleSceneDesc() {
        return simpleSceneDesc;
    }

    public void setSimpleSceneDesc(String simpleSceneDesc) {
        this.simpleSceneDesc = simpleSceneDesc;
    }

    public List<CmdSimple> getCmdSimpleList() {
        return cmdSimpleList;
    }

    public void setCmdSimpleList(List<CmdSimple> cmdSimpleList) {
        this.cmdSimpleList = cmdSimpleList;
    }

    public List<ConditionSimple> getConditionSimpleList() {
        return conditionSimpleList;
    }

    public void setConditionSimpleList(List<ConditionSimple> conditionSimpleList) {
        this.conditionSimpleList = conditionSimpleList;
    }

    public String getSimpleSceneIconUrl() {
        return simpleSceneIconUrl;
    }

    public void setSimpleSceneIconUrl(String simpleSceneIconUrl) {
        this.simpleSceneIconUrl = simpleSceneIconUrl;
    }

    public long getSimpleSceneIconId() {
        return simpleSceneIconId;
    }

    public void setSimpleSceneIconId(long simpleSceneIconId) {
        this.simpleSceneIconId = simpleSceneIconId;
    }

    public int getSimpleSceneType() {
        return simpleSceneType;
    }

    public void setSimpleSceneType(int simpleSceneType) {
        this.simpleSceneType = simpleSceneType;
    }
}
