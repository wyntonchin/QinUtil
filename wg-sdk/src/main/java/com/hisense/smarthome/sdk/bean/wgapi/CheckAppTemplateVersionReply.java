
package com.hisense.smarthome.sdk.bean.wgapi;

import com.hisense.smarthome.sdk.bean.global.BaseInfo;

public class CheckAppTemplateVersionReply extends BaseInfo {

    /**
     * 
     */
    private static final long serialVersionUID = 3188403716131973412L;
    private int upgradeFlag;// 升级标识0：不升级；1：普通升级；2：强制升级
    private String latestVersion;// 最新版本号
    private String upgradeUrl;// 包下载地址
    private long filesize;// Int 包大小，单位：字节
    private String checksum;// string 校验码
    private String relatedinfo;// string 升级相关信息
    private String deviceLibVersion;// string 模板对应的解析库版本

    public int getUpgradeFlag() {
        return upgradeFlag;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    public long getFilesize() {
        return filesize;
    }

    public String getChecksum() {
        return checksum;
    }

    public String getRelatedinfo() {
        return relatedinfo;
    }

    public String getDeviceLibVersion() {
        return deviceLibVersion;
    }

    public void setUpgradeFlag(int upgradeFlag) {
        this.upgradeFlag = upgradeFlag;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public void setRelatedinfo(String relatedinfo) {
        this.relatedinfo = relatedinfo;
    }

    public void setDeviceLibVersion(String deviceLibVersion) {
        this.deviceLibVersion = deviceLibVersion;
    }

}
