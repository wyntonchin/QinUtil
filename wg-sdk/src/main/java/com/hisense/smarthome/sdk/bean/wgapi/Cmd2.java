
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Cmd2 implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7658782612829997819L;
    private String cmdTime;// 绑定时间（格式:yyyy-mm-dd hh:mm:ss）
    private String wifiid;// string 通信模块id
    private String devid;// string 设备Id
    private String devNickName;// string 设备昵称
    private String Cmd;// string
                       // 执行指令，具体包括：指令值和指令参数值，指令值和指令参数直接使用”,”分隔；多个功能标识间用“|”分隔；
                       // 例如：空调开机，并设定温度到25度，functionCode的格式为：4,1|6,25
                       // 其中4,1中的4表示开关机指令，1表示开机；6,,25中的6表示设置温度，25表示温度为25度。
    private String status;// string 0 - 指令执行成功1 – 指令未下发2 – 指令已下发但设备未返回
    private String devStatus;// string 指令执行后的原始状态
    private String creater;// string 创建此命令的用户昵称
    private int executortype;// 命令执行者类型0代表用户手动执行1代表系统端触发执行
    private String executor;// executortype为0时返回执行命令的用户昵称
                            // executortype为1时返回系统端触发指令执行的功能id。
                            // 系统端触发指令执行的功能id定义如下
                            // 0代表空调云定时
                            // 1代表空调智能推荐
                            // 2代表空调睡眠曲线
                            // 3代表场景-定时触发
                            // 4代表场景-条件触发

    public String getCmdTime() {
        return cmdTime;
    }

    public String getWifiid() {
        return wifiid;
    }

    public String getDevid() {
        return devid;
    }

    public String getDevNickName() {
        return devNickName;
    }

    public String getCmd() {
        return Cmd;
    }

    public String getStatus() {
        return status;
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setCmdTime(String cmdTime) {
        this.cmdTime = cmdTime;
    }

    public void setWifiid(String wifiid) {
        this.wifiid = wifiid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public void setDevNickName(String devNickName) {
        this.devNickName = devNickName;
    }

    public void setCmd(String cmd) {
        Cmd = cmd;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public int getExecutortype() {
        return executortype;
    }

    public void setExecutortype(int executortype) {
        this.executortype = executortype;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

}
