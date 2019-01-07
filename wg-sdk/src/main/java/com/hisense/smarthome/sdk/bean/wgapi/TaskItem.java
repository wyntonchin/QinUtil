package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;
import java.util.List;

public class TaskItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 754792620653858514L;
    private String exectime;// 指令执行时间，格式为"01:15" "10:30"，15分钟一个刻度
    private List<Cmd> cmdList;// 定时任务执行指令列表
    private int devType;// 设备类型，1，空调；2，空净一体机

    public int getDevType() {
        return devType;
    }

    public void setDevType(int devType) {
        this.devType = devType;
    }

    public String getExectime() {
        return exectime;
    }

    public void setExectime(String exectime) {
        this.exectime = exectime;
    }

    public List<Cmd> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<Cmd> cmdList) {
        this.cmdList = cmdList;
    }

}
