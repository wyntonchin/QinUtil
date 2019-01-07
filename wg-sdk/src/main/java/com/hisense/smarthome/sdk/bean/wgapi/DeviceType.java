package com.hisense.smarthome.sdk.bean.wgapi;

import java.io.Serializable;

public class DeviceType implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2415658653760204173L;

    private String deviceTypeCode;// 设备类别code设备类别名称，当
    // deviceTypeCode为010时，标识智能网关下设备的名称
    private String deviceSubTypeCode;// 智能网关下的设备子编码，当
    // deviceTypeCode为010时，使用该字段标识具体的智能网关下的设备。
    private String deviceTypeName;// 设备类别名称
    private String deviceTypeIcon;// 设备类别图标url
    private String deviceTypePic;// 设备类别图片url
    private String attentionUrl;// 注意事项URL
    private String faqUrl;// 常见问题URL
    private String deviceUseType;// 设备使用类型：1-白电2-环境设备3-家居设备4-安防设备
    private String uiModulePath;// UI模板的路径
    private String devLibPath;// 解析库的路径
    private String devLibExtParamType;// 解析库扩展参数类型1-无扩展参数2-设备条码3-设备Id
    // private List<StatusMeta> displayStatusList;// 设备类别要显示的状态列表
    private int deviceIDNumber;// 设备识别码
    private int onoffStatus;// 开关机状态
    private long lastUpdateTime;// 设备类别最新更新时间（10位的时间戳，精确到秒）例如：1475049681
    private long cmdUpdateTime;// 控制指令集更新时间（10位的时间戳，精确到秒）例如：1475049681
    private long statusUpdateTime;// 状态集更新时间（10位的时间戳，精确到秒）例如：1475049681
    private int stateKey;// 状态位序号
    private int cmdKey;// 操作指令key
    private String nameOn;// On状态显示名称
    private int stateOn;// On状态对应值
    private int cmdOn;// 实现On状态发送指令值
    private String nameOff;// Off状态显示名称
    private int stateOff;// Off状态对应值
    private int cmdOff;// 实现Off状态发送指令值
    private long uiModuleUpdateVersion; //手工指定的版本号，从1开始，有改动+1
    private long devLibUpdateVersion; //手工指定的版本号，从1开始，有改动+1

    public long getUiModuleUpdateVersion() {
        return uiModuleUpdateVersion;
    }

    public void setUiModuleUpdateVersion(long uiModuleUpdateVersion) {
        this.uiModuleUpdateVersion = uiModuleUpdateVersion;
    }

    public long getDevLibUpdateVersion() {
        return devLibUpdateVersion;
    }

    public void setDevLibUpdateVersion(long devLibUpdateVersion) {
        this.devLibUpdateVersion = devLibUpdateVersion;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceTypeIcon() {
        return deviceTypeIcon;
    }

    public void setDeviceTypeIcon(String deviceTypeIcon) {
        this.deviceTypeIcon = deviceTypeIcon;
    }

    public String getDeviceTypePic() {
        return deviceTypePic;
    }

    public void setDeviceTypePic(String deviceTypePic) {
        this.deviceTypePic = deviceTypePic;
    }

    /*
     * public List<StatusMeta> getDisplayStatusList() { return
     * displayStatusList; }
     */

    public String getDeviceUseType() {
        return deviceUseType;
    }

    public void setDeviceUseType(String deviceUseType) {
        this.deviceUseType = deviceUseType;
    }

    public String getUiModulePath() {
        return uiModulePath;
    }

    public void setUiModulePath(String uiModulePath) {
        this.uiModulePath = uiModulePath;
    }

    public String getDevLibPath() {
        return devLibPath;
    }

    public void setDevLibPath(String devLibPath) {
        this.devLibPath = devLibPath;
    }

    public String getDevLibExtParamType() {
        return devLibExtParamType;
    }

    public void setDevLibExtParamType(String devLibExtParamType) {
        this.devLibExtParamType = devLibExtParamType;
    }

    /*
     * public void setDisplayStatusList(List<StatusMeta> displayStatusList) {
     * this.displayStatusList = displayStatusList; }
     */

    public String getDeviceSubTypeCode() {
        return deviceSubTypeCode;
    }

    public void setDeviceSubTypeCode(String deviceSubTypeCode) {
        this.deviceSubTypeCode = deviceSubTypeCode;
    }

    public String getAttentionUrl() {
        return attentionUrl;
    }

    public void setAttentionUrl(String attentionUrl) {
        this.attentionUrl = attentionUrl;
    }

    public String getFaqUrl() {
        return faqUrl;
    }

    public void setFaqUrl(String faqUrl) {
        this.faqUrl = faqUrl;
    }

    public int getStateKey() {
        return stateKey;
    }

    public void setStateKey(int stateKey) {
        this.stateKey = stateKey;
    }

    public int getCmdKey() {
        return cmdKey;
    }

    public void setCmdKey(int cmdKey) {
        this.cmdKey = cmdKey;
    }

    public String getNameOn() {
        return nameOn;
    }

    public void setNameOn(String nameOn) {
        this.nameOn = nameOn;
    }

    public int getStateOn() {
        return stateOn;
    }

    public void setStateOn(int stateOn) {
        this.stateOn = stateOn;
    }

    public int getCmdOn() {
        return cmdOn;
    }

    public void setCmdOn(int cmdOn) {
        this.cmdOn = cmdOn;
    }

    public String getNameOff() {
        return nameOff;
    }

    public void setNameOff(String nameOff) {
        this.nameOff = nameOff;
    }

    public int getStateOff() {
        return stateOff;
    }

    public void setStateOff(int stateOff) {
        this.stateOff = stateOff;
    }

    public int getCmdOff() {
        return cmdOff;
    }

    public void setCmdOff(int cmdOff) {
        this.cmdOff = cmdOff;
    }

    public int getDeviceIDNumber() {
        return deviceIDNumber;
    }

    public void setDeviceIDNumber(int deviceIDNumber) {
        this.deviceIDNumber = deviceIDNumber;
    }

    public int getOnoffStatus() {
        return onoffStatus;
    }

    public void setOnoffStatus(int onoffStatus) {
        this.onoffStatus = onoffStatus;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getCmdUpdateTime() {
        return cmdUpdateTime;
    }

    public void setCmdUpdateTime(long cmdUpdateTime) {
        this.cmdUpdateTime = cmdUpdateTime;
    }

    public long getStatusUpdateTime() {
        return statusUpdateTime;
    }

    public void setStatusUpdateTime(long statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

}
