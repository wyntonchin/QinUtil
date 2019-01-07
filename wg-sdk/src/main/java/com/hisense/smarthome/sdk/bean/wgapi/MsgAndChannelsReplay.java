
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

import java.util.List;

public class MsgAndChannelsReplay extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 5485077971125394567L;

    private List<String> pushChannels;// 用户需要关注的推送通道channel列表
    private List<Msg> msgList;// 消息列表，消息内容见下表

    private String pushServerIp;// 推送服务器IP地址
    private String pushServerPort;// 推送服务器端口

    public List<String> getPushChannels() {
        return pushChannels;
    }

    public List<Msg> getMsgList() {
        return msgList;
    }

    public void setPushChannels(List<String> pushChannels) {
        this.pushChannels = pushChannels;
    }

    public void setMsgList(List<Msg> msgList) {
        this.msgList = msgList;
    }

    public String getPushServerIp() {
        return pushServerIp;
    }

    public String getPushServerPort() {
        return pushServerPort;
    }

    public void setPushServerIp(String pushServerIp) {
        this.pushServerIp = pushServerIp;
    }

    public void setPushServerPort(String pushServerPort) {
        this.pushServerPort = pushServerPort;
    }

}
