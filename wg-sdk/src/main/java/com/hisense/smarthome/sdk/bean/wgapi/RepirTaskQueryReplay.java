
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class RepirTaskQueryReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -2723634068885803511L;
    private List<Task> tasks;// 工单列表

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
