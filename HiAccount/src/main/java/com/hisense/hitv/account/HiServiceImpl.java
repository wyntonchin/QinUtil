package com.hisense.hitv.account;

import android.content.Context;
import android.text.TextUtils;

import com.hisense.hitv.hicloud.bean.account.AppCodeReply;
import com.hisense.hitv.hicloud.bean.account.AppCodeSSO;
import com.hisense.hitv.hicloud.bean.account.CustomerInfo;
import com.hisense.hitv.hicloud.bean.account.GetUriReply;
import com.hisense.hitv.hicloud.bean.account.ReplyInfo;
import com.hisense.hitv.hicloud.bean.account.SignonReplyInfo;
import com.hisense.hitv.hicloud.bean.account.ThirdAccountOauthLoginReplay;
import com.hisense.hitv.hicloud.bean.global.HiSDKInfo;
import com.hisense.hitv.hicloud.factory.HiCloudServiceFactory;
import com.hisense.hitv.hicloud.service.HiCloudAccountService;
import com.hisense.hitv.hicloud.util.DeviceConfig;
import com.hisense.hitv.hicloud.util.Params;

import java.util.HashMap;

/**
 * Created by Victor on 2018/5/29. (ง •̀_•́)ง
 */
public class HiServiceImpl implements HiService {
    private static final HiServiceImpl INSTANCE = new HiServiceImpl();

    private static final String TOKEN = "HiServiceToken";
    private static final String TOKEN_CREATE = "HiServiceTokenCreate";

    private static final String TOKEN_EXPIRE = "HiServiceTokenExpire";
    private static final String TOKEN_REFRESH = "HiServiceTokenRefresh";

    private static final String DEFAULT_WG_HITV_IP = "bas.wg.hismarttv.com";

    private String mIp = DEFAULT_WG_HITV_IP;

    private HiServiceImpl() {
    }

    public static HiServiceImpl obtain() {
        return INSTANCE;
    }

    public void setIp(String ip) {
        mIp = ip;
    }

    private HiCloudAccountService getService() {
        return getService(null);
    }

    private HiCloudAccountService getService(String token) {

        HiSDKInfo info = new HiSDKInfo();
        info.setDomainName(mIp);
        info.setToken(token);
        return HiCloudServiceFactory.getHiCloudAccountService(info);
    }

    @Override
    public AppCodeReply appAuth(final String appKey, final String appSecret) {
        HashMap<String, String> map = new HashMap<>();
        map.put(Params.APPKEY, appKey);
        map.put(Params.APPSECRET, appSecret);
        return getService().appAuth(map);
    }

    @Override
    public AppCodeSSO appSSOAuth(final String appKey, final String appSecret, final String deviceId) {
        HashMap<String, String> map = new HashMap<>();
        map.put(Params.APPKEY, appKey);
        map.put(Params.APPSECRET, appSecret);
        map.put(Params.DEVICEID, deviceId);
        AppCodeSSO reply = getService().appAuthSSO(map);
        if (reply.getReply() != 2) {
            String appCodeSSO = reply.getCode();
            map = new HashMap<>(3);
            map.put(Params.APPCODE, appCodeSSO);
            map.put(Params.LOGINNAME, "");
            map.put(Params.DEVICEID, deviceId);
            SignonReplyInfo signonReply = getService().signon(map);
            if (signonReply != null) {
                reply.setToken(signonReply.getToken());
            }
        }
        return reply;
    }

    @Override
    public GetUriReply getUri(HashMap<String, String> map) {
        return getService().getUri(map);
    }

    //@Override
    public GetUriReply getUri(String tokenSSO, String blogId, String callBackPath) {
        HashMap<String, String> map = new HashMap<>(3);
        map.put(Params.ACCESSTOKEN, tokenSSO); //TODO
        map.put(Params.BLOGID, blogId);
        map.put(Params.CALLBACKPATH, callBackPath);
        return getService().getUri(map);
    }


    @Override
    public SignonReplyInfo login(String loginName, String password, String deviceId, String appCode) {
        HashMap<String, String> map = new HashMap<>();
        map.put(Params.DEVICEID, deviceId);
        map.put(Params.LOGINNAME, loginName);
        map.put(Params.PASSWORD, password);
        map.put(Params.APPCODE, appCode);
        SignonReplyInfo reply = getService().signon(map);
        saveToken(reply);
        return reply;
    }

    @Override
    public ReplyInfo logout(String token, String deviceId) {
        HashMap<String, String> map = new HashMap<>();
        map.put(Params.DEVICEID, deviceId);
        ReplyInfo reply = getService(token).logout(map);
        if (reply != null && reply.getReply() == 0) {
            clearToken();
        }
        return reply;
    }

    @Override
    public CustomerInfo getCustomerInfo(String token) {
        return getService(token).getCustomerInfo();
    }

    @Override
    public String getDeviceId(Context context) {
        return DeviceConfig.getDeviceId(context);
    }

    @Override
    public ReplyInfo updateCustomerInfo(String token, HashMap<String, String> map) {
        return getService(token).updateCustomerInfo(map);
    }

    @Override
    public ReplyInfo modifyPassword(String token, String oldPwd, String newPwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("oldPwd", oldPwd);
        map.put("newPwd", newPwd);
        return getService(token).modifyPassword(map);
    }

    @Override
    public SignonReplyInfo refreshToken(String appKey) {
        HashMap<String, String> map = new HashMap<>();
        map.put("appKey", appKey);
        map.put("refreshToken", AccountSpUtil.getString(TOKEN_REFRESH));
        SignonReplyInfo reply = getService().refreshToken2(map);
        saveToken(reply);
        return reply;
    }

    @Override
    public SignonReplyInfo register(HashMap<String, String> map) {
        return getService().register(map);
    }

    @Override
    public ReplyInfo sendCaptcha(String tokenSSO, String phone) {
        return getService().sendMobileAuthCode(tokenSSO, phone);
    }

    @Override
    public ReplyInfo validateCaptcha(String tokenSSO, String phone, String captcha) {
        return getService().checkMobileAuthCode(tokenSSO, phone, captcha);
    }

    @Override
    public ReplyInfo findPasswordSendCaptcha(String loginName, String appCode, String tokenSSO) {
        HashMap<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("appCode", appCode);
        return getService(tokenSSO).findPassswordCode(map);
    }

    @Override
    public ReplyInfo findPasswordByCaptcha(String loginName, String captcha, String newPassword, String tokenSSO) {
        HashMap<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("checkCode", captcha);
        map.put("newPwd", newPassword);
        return getService(tokenSSO).findPassswordByCode(map);
    }

    @Override
    public boolean isTokenExpire() {
        long tokenTimeCreate = AccountSpUtil.getLong(TOKEN_CREATE);
        long tokenTimeExpire = AccountSpUtil.getLong(TOKEN_EXPIRE);
        long diff = System.currentTimeMillis() / 1000 - tokenTimeCreate;
        return TextUtils.isEmpty(getToken()) || (tokenTimeCreate > 0 && tokenTimeExpire > 0 && diff > 0 && diff > (tokenTimeExpire * 0.9f));
    }

    @Override
    public String getToken() {
        return AccountSpUtil.getString(TOKEN);
    }

    @Override
    public ThirdAccountOauthLoginReplay thirdAccountOauthLogin(HashMap<String, String> map) {
        ThirdAccountOauthLoginReplay oauthLoginReplay = getService().thirdAccountOauthLogin(map);
        SignonReplyInfo  reply= oauthLoginReplay.getSignonReplyInfo();
        if(reply != null){
            saveToken(reply);
        }
        return oauthLoginReplay;
    }

/*    private void saveOauthToken(ThirdAccountOauthLoginReplay reply) {
        AccountSpUtil.setString(TOKEN, reply.getThirdAccessToken());
        AccountSpUtil.setString(TOKEN_REFRESH, reply.getRefreshToken());
        AccountSpUtil.setLong(TOKEN_CREATE, reply.getTokenCreateTime());
        AccountSpUtil.setLong(TOKEN_EXPIRE, reply.getTokenExpireTime());
    }*/

    private void saveToken(SignonReplyInfo reply) {
        AccountSpUtil.setString(TOKEN, reply.getToken());
        AccountSpUtil.setString(TOKEN_REFRESH, reply.getRefreshToken());
        AccountSpUtil.setLong(TOKEN_CREATE, reply.getTokenCreateTime());
        AccountSpUtil.setLong(TOKEN_EXPIRE, reply.getTokenExpireTime());
    }

    private void clearToken() {
        AccountSpUtil.remove(TOKEN);
        AccountSpUtil.remove(TOKEN_REFRESH);
        AccountSpUtil.remove(TOKEN_CREATE);
        AccountSpUtil.remove(TOKEN_EXPIRE);
    }
}
