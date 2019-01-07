package com.hisense.smarthome.sdk.bean.wgapi;
import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

/**
 * Created by Hisense on 2017/10/17.
 */

public class SimpleSceneListReply extends BaseInfo {
    private static final long serialVersionUID = -2852385091608023294L;
    private List<SimpleSceneTemplate> simpleSceneTemplateList;// 简易场景模板列表

    public List<SimpleSceneTemplate> getSimpleSceneTemplateList() {
        return simpleSceneTemplateList;
    }

    public void setSimpleSceneTemplateList(List<SimpleSceneTemplate> simpleSceneTemplateList) {
        this.simpleSceneTemplateList = simpleSceneTemplateList;
    }
}
