
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Fault implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7441334073927290344L;
    private long id;// 故障类型ID
    private String name;// 故障类型名称
    private String desc;// 故障类型描述

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
