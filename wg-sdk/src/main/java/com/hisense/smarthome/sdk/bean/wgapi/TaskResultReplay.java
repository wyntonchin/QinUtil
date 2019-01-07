
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class TaskResultReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = -349622059073662502L;
    private int count;// 定时任务执行结果的数量
    private List<TaskExecResult> taskExecResultList;// 定时任务执行结果

    public int getCount() {
        return count;
    }

    public List<TaskExecResult> getTaskExecResultList() {
        return taskExecResultList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTaskExecResultList(List<TaskExecResult> taskExecResultList) {
        this.taskExecResultList = taskExecResultList;
    }

}
