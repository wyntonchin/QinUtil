
package com.hisense.smarthome.sdk.bean.global;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.text.TextUtils;

import com.hisense.smarthome.sdk.factory.HiCloudServiceFactory;
import com.hisense.smarthome.sdk.util.Constants;
import com.hisense.smarthome.sdk.util.DeviceConfig;
import com.hisense.smarthome.sdk.util.SDKUtil;

/**
 * 一般情况下获取HiCloudSDK的服务实例所必需传入的参数，该类中提供了一系列使用HiCloudSDk各服务所需的基础配置项</br>
 * 对于HiCloudSDK中的一个具体服务来说，并非所有的配置项都是必需的，对于不同的服务，必需的配置项可能也不同</br>
 * 获取服务实例所需要的具体的配置项请见各服务定义特定的要求
 * 
 * @author Merry.Zhao
 */
public class HiSDKInfo implements Serializable {
    private static final long serialVersionUID = 2139515082735082423L;
    /**
     * 域名
     */
    private String domainName = DeviceConfig.getDomainName();

    private String prefix = "";// 域名之后的prefix；

    private String ip = "";

    private String port = "";

    private String token = "";// 授权token，用以身份验证

    private String languageId = "";// 语言，默认为0; 0中文 1英语 2 法语 3韩语 4俄语 5日语 6西班牙语
                                   // 7德语 8繁体中文9阿拉伯语10波斯语11泰语
    // 12 意大利语13荷兰语14捷克语15匈牙利语 16葡萄牙语17罗马尼亚语18希腊语19 保加利亚语20 马来语 21希伯来语

    private String version = Constants.DEFAULTAPIVERSION;// OPENAPI版本号,用于兼容不同的OpenApi版本

    private String sign = "";// 签名认证，由本次请求的所有参数计算的值，保留

    private long timeStamp = 0;// 保留，时间戳 单位：秒数

    private String format = "0";// 返回数据的格式 0：XML 1：JSON

    private HashMap<String, String> actions; // 访问部分Portal所必需的action

    private String timeZone = Constants.DEFAULTTIMEZONE;// 时区 默认 8

    private String packageName = "";// 使用SDK的应用包名

    private String loginName;// 登录用户名

    private String filterFlag = "";// 0-终端有搜台数据，需要过滤节目；1-终端没有搜台数据，不需要过滤节目

    private String tvMode = ""; // 上传的值为Hicloud，用于EPG区分不同的应用访问

    private String osVersion = ""; // 操作系统版本，默认值为：2.3

    private String osType = ""; // 操作系统类型，0-HitvOS，1-Android，默认值为1

    private String machineType = ""; // 机型

    private String zipCode = ""; // 邮编

    private String countryCode = ""; // 国家码

    private int appVersionCode;// 应用版本号

    public String getDomainName() {
        if (TextUtils.isEmpty(this.domainName)) {
            setDomainName(DeviceConfig.getDomainName());
        }
        return domainName;

    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLanguageId() {
        if (SDKUtil.isEmpty(languageId)) {
            languageId = SDKUtil.getLocaleLanguage();
        }
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getVersion() {
        return version;
    }

    /*
     * 2013-04-08 Merry.Zhao 取消该方法，不再提供外部对API版本信息的控制
     */

    /*
     * public void setVersion(String version) { this.version = version; }
     */

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public HashMap<String, String> getActions() {

        if (actions == null) {
            actions = new HashMap<String, String>();
        }
        return actions;
    }

    public void setActions(HashMap<String, String> actions) {
        this.actions = actions;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public String getTvMode() {
        return tvMode;
    }

    public void setTvMode(String tvMode) {
        this.tvMode = tvMode;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(int appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

}
