
package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class Msg implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3343540555707928824L;
    private boolean shouldArrirved;// 是否必达（必须保证APP收到）消息
    private long formatId;// number 消息格式ID，具体见消息格式约定
    private int msgType;// number 消息类型：1：消息中心消息，2：状态消息
    private String content;// 消息内容:json，格式为formatId对应的格式
    private boolean forceRemind;// 是否强制提醒。
    private long expireTime;// number 消息过期时间，内容为13位的timestamp。
    private long startTime;// number 消息生效时间，内容为13位的timestamp。
    private long customerMsgId;// number 用户消息ID。据此来对消息进行排序排重
    private long msgId;// number 消息ID。仅用来实现上报日志的功能，业务上不需要处理该ID。
    private int msgLevel;// 消息级别：1：告警消息2：设备操控消息/家庭成员内部消息3：运营消息
    private int msgOwnerType;// 消息属主类型：1：用户消息2：设备消息3：系统消息
    private String msgOwnerId;// 消息属主Id，不同属主类型对应不同类型的属主Id：
                              // msgOwnerType=1 发出消息的customerId
                              // msgOwnerType=2
                              // 消息对应的wifiId+设备Id（以*符号隔开，格式例如：wifiid*deviceId）
                              // msgOwnerType=3 1：运营编排2：系统产生
    private String msgTypeCode;// string 消息类型编码
                               // 新成员加入家庭：home_joinhome
                               // 删除家庭成员：home_delmember
                               // 管理员权限转移：home_movepermission
                               // 删除家庭：home_delhome
                               // 成员退出家庭：home_memberquit
                               // 删除家中设备：home_deldevice
                               // 申请加入家庭：home_applyjoin

    public boolean isShouldArrirved() {
        return shouldArrirved;
    }

    public long getFormatId() {
        return formatId;
    }

    public int getMsgType() {
        return msgType;
    }

    public String getContent() {
        return content;
    }

    public boolean isForceRemind() {
        return forceRemind;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public long getCustomerMsgId() {
        return customerMsgId;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setShouldArrirved(boolean shouldArrirved) {
        this.shouldArrirved = shouldArrirved;
    }

    public void setFormatId(long formatId) {
        this.formatId = formatId;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setForceRemind(boolean forceRemind) {
        this.forceRemind = forceRemind;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public void setCustomerMsgId(long customerMsgId) {
        this.customerMsgId = customerMsgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public int getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(int msgLevel) {
        this.msgLevel = msgLevel;
    }

    public int getMsgOwnerType() {
        return msgOwnerType;
    }

    public void setMsgOwnerType(int msgOwnerType) {
        this.msgOwnerType = msgOwnerType;
    }

    public String getMsgOwnerId() {
        return msgOwnerId;
    }

    public void setMsgOwnerId(String msgOwnerId) {
        this.msgOwnerId = msgOwnerId;
    }

    public String getMsgTypeCode() {
        return msgTypeCode;
    }

    public void setMsgTypeCode(String msgTypeCode) {
        this.msgTypeCode = msgTypeCode;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

}
