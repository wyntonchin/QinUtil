package com.hisense.hitv.account;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hisense.hitv.account.pool.PriorityRunnable;
import com.hisense.hitv.account.pool.ThreadPoolProxyFactory;
import com.hisense.hitv.hicloud.bean.account.AppCodeReply;
import com.hisense.hitv.hicloud.bean.account.AppCodeSSO;
import com.hisense.hitv.hicloud.bean.account.GetUriReply;
import com.hisense.hitv.hicloud.bean.account.ThirdAccountOauthLoginReplay;
import com.hisense.hitv.hicloud.util.DeviceConfig;
import com.hisense.hitv.hicloud.util.Params;
import com.hismart.base.BaseConstant;
import com.hismart.base.LogUtil;
import com.hismart.base.ToastUtil;
import com.hismart.easylink.wxapi.WXEntryActivity;

import java.util.HashMap;

public class WeiboEntry extends Activity implements OAuthLoginActivity.AuthListener {
    private final static String TAG = "WeiboEntry";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_entry);
        doWeiboLogin();
    }


    private static final String oAuthCallBack = "weibo4android://OAuthSettingActivity";
    void doWeiboLogin() {
        LogUtil.w(TAG, "doWeiboLogin");
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                //根据应用获取云端配置的appCodeSSO
                AppCodeSSO appCodeSSO = HiServiceImpl.obtain().appSSOAuth(BaseConstant.APP_KEY, BaseConstant.APP_SECRET, DeviceConfig.getDeviceId(WeiboEntry.this));
                String tokenSSO = appCodeSSO.getToken();
                LogUtil.w(TAG, "doWeiboLogin tokenSSO:" + tokenSSO + "; code : " + appCodeSSO.getCode());
                if(tokenSSO != null){
                    //获取登录微博的url
                    GetUriReply uriReply = HiServiceImpl.obtain().getUri(tokenSSO, LoginActivity.ID_BLOG_SINA, oAuthCallBack);
                    LogUtil.w(TAG, "doWeiboLogin GetUriReply = " + uriReply.getUri());
                    OAuthLoginActivity.authorizeActivity(WeiboEntry.this, uriReply.getUri(), WeiboEntry.this, LoginActivity.ID_BLOG_SINA);
                }
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);
    }

    @Override
    public void onSuccess(String url) {
        LogUtil.e(TAG,"onSuccess");
        finish();

/*        LogUtil.d(TAG, "onSuccess, " + verifyCode);
        ShowDialog(WAIT_DAILOG);
        mAccountManager.OAuthLogin(verifyCode, targetID);

        HashMap<String, String> authMap = new HashMap<>(4);
        authMap.put(Params.THIRD_CALLBACK, "code=" + authResp.code + "&appid=" + BaseConstant.WECHAT_APP_ID);
        authMap.put(Params.THIRD_PLATFORMID, String.valueOf(LoginActivity.ID_WECHAT));
        authMap.put(Params.APPCODE, appCodeReply.getCode());
        authMap.put(Params.DEVICEID, DeviceConfig.getDeviceId(WXEntryActivity.this.getApplicationContext()));
        LogUtil.i(TAG, "weibo thirdReplay 11111111111");
        ThirdAccountOauthLoginReplay thirdReplay = HiServiceImpl.obtain().thirdAccountOauthLogin(authMap);*/


        String verifyCode = Uri.parse(url).getQuery();
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "weibo onResp currentThread:" + Thread.currentThread().getName());
                //先认证APP,必须使用线程，否则直接返回认证失败
                AppCodeReply appCodeReply = HiServiceImpl.obtain().appAuth(BaseConstant.APP_KEY, BaseConstant.APP_SECRET);
                if (appCodeReply != null && appCodeReply.getReply() == 0) {
                    LogUtil.i(TAG, "weibo appCodeReply :" + appCodeReply.getReply());
                    HashMap<String, String> authMap = new HashMap<>(4);
                    authMap.put(Params.THIRD_CALLBACK, verifyCode);
                    authMap.put(Params.THIRD_PLATFORMID, String.valueOf(LoginActivity.ID_BLOG_SINA));
                    authMap.put(Params.APPCODE, appCodeReply.getCode());
                    authMap.put(Params.DEVICEID, DeviceConfig.getDeviceId(WeiboEntry.this.getApplicationContext()));
                    LogUtil.i(TAG, "weibo thirdReplay 11111111111");
                    ThirdAccountOauthLoginReplay thirdReplay = HiServiceImpl.obtain().thirdAccountOauthLogin(authMap);
                    LogUtil.i(TAG, "weibo thirdReplay 22222222222");
                    if (thirdReplay != null && thirdReplay.getSignonReplyInfo() != null) {
                        LogUtil.e(TAG, "doweiboLogin getCustomerId:" + thirdReplay.getSignonReplyInfo().getCustomerId());
                        runOnUiThread(() -> ToastUtil.showShortToast("登录成功"));
                    } else {
                        LogUtil.e(TAG, "doweiboLogin getCustomerId:" + thirdReplay.getThirdAccessToken());
                        runOnUiThread(() -> ToastUtil.showShortToast("登录失败"));
                    }
                } else {
                    LogUtil.e(TAG, "weibo appCodeReply error:" + appCodeReply.getReply());
                    runOnUiThread(() -> ToastUtil.showShortToast("应用认证失败"));
                }
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);



    }

    @Override
    public void onCancel() {
        LogUtil.e(TAG,"onCancel");
        finish();

    }

    @Override
    public void onError() {
        LogUtil.e(TAG,"onError");
        finish();
    }


    /*获取支持的blog列表，这些接口目前没啥用
                LogUtil.w(TAG, "doWeiboLogin run:" + Thread.currentThread().getName());
                HiSDKInfo info = new HiSDKInfo();
                info.setDomainName("bas.wg.hismarttv.com");
                info.setToken(tokenSSO);
                HiCloudAccountService service = HiCloudServiceFactory
                        .getHiCloudAccountService(info);
                BlogStatusReply blogListReply = service.getBinders();

                if (blogListReply != null && blogListReply.getReply() == 0) {
                    List<BlogInfo> blogs = blogListReply.getBlogList();
                    LogUtil.w(TAG, "doWeiboLogin run:blogs.size = " + blogs.size());
                    if (BuildConfig.DEBUG) {
                        for (BlogInfo blogInfo : blogs) {
                            LogUtil.d(TAG, "BlogInfo name:" + blogInfo.getBlogName());
                        }
                    }

                } else if ((blogListReply != null) && blogListReply.getReply() != 0) {
                    LogUtil.w(TAG, "doWeiboLogin run:bloglist = " + blogListReply.getReply());
                } else {
                    LogUtil.w(TAG, "doWeiboLogin run:bloglist error");
                }*/
}
