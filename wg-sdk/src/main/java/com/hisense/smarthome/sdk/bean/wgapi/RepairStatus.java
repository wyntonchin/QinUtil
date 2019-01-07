
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class RepairStatus implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1264331984777109222L;

    private int status;// 状态值
    private String name;// 状态名称
    private int order;// 状态的排序值

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
