
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class TimedTask implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6308692797370129788L;
    private String wifiId;// wifi模块的ID
    private int available;// 时间表是否可用，0：不可用（不调度执行），1：可用
    private int taskMode;// 时间表调度模式，1：执行一次；2：周期性执行（天的周期）
    private String lastUpdateTime;// tring 时间表最后更新日期，“2015-06-20 10:30:00”格式字符串
    private int customerid;// 上传时间表的用户标识
    private List<TaskItem> taskItemList;// 定时任务项列表

    public String getWifiId() {
        return wifiId;
    }

    public int getAvailable() {
        return available;
    }

    public int getTaskMode() {
        return taskMode;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public int getCustomerid() {
        return customerid;
    }

    public List<TaskItem> getTaskItemList() {
        return taskItemList;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setTaskMode(int taskMode) {
        this.taskMode = taskMode;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public void setTaskItemList(List<TaskItem> taskItemList) {
        this.taskItemList = taskItemList;
    }

}
