
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class CrmArea implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 623440387252481072L;
    private String id;// 地区ID
    private String name;// 地区名称
    private String parentId;// 父地区ID
    private String type;// 地区类型

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setType(String type) {
        this.type = type;
    }

}
