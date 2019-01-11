package com.hismart.easylink.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hisense.hitv.account.HiServiceImpl;
import com.hisense.hitv.account.LoginActivity;
import com.hisense.hitv.account.R;
import com.hisense.hitv.account.pool.PriorityExecutor;
import com.hisense.hitv.account.pool.PriorityRunnable;
import com.hisense.hitv.account.pool.ThreadPoolProxyFactory;
import com.hisense.hitv.hicloud.bean.account.AppCodeReply;
import com.hisense.hitv.hicloud.bean.account.ThirdAccountOauthLoginReplay;
import com.hisense.hitv.hicloud.util.Constants;
import com.hisense.hitv.hicloud.util.DeviceConfig;
import com.hisense.hitv.hicloud.util.Params;
import com.hismart.base.BaseConstant;
import com.hismart.base.LogUtil;
import com.hismart.base.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import static com.hisense.hitv.hicloud.util.Params.THIRD_PLATFORMID;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    ExecutorService mExecutorService = new PriorityExecutor(4, false);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_entry);

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, BaseConstant.WECHAT_APP_ID, false);

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，
        // 如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部
        // 通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "api.handleIntent:" + Log.getStackTraceString(e));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                //goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                //goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            default:
                break;
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        Log.d(TAG, "receive response");
        Log.d(TAG, "type is:" + resp.getType());
        Log.d(TAG, "result is:" + resp.errStr);
        Log.d(TAG, "result code is:" + resp.errCode);
        Log.d(TAG, "result str:" + resp.toString());
        Toast.makeText(this, "baseresp.getType = " + resp.getType(), Toast.LENGTH_SHORT).show();

        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    doWeChatLoginJu(resp);
                    //result = R.string.errcode_success;
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    //result = R.string.errcode_cancel;
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    //result = R.string.errcode_deny;
                    break;
                case BaseResp.ErrCode.ERR_UNSUPPORT:
                    //result = R.string.errcode_unsupported;
                    break;
                default:
                    //result = R.string.errcode_unknown;
                    break;
            }

        } else if(resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX){
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    ToastUtil.showLongToast(R.string.hi_account_wechat_share_success);
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                case BaseResp.ErrCode.ERR_UNSUPPORT:
                default:
                    ToastUtil.showLongToast(R.string.hi_account_wechat_share_fail);
                    break;
            }
        }else {
            ToastUtil.showLongToast("微信错误");
        }

        finish();
        //Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
    }


    void doWeChatLoginJu(BaseResp resp){
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "weixin onResp currentThread:" + Thread.currentThread().getName());
                //先认证APP,必须使用线程，否则直接返回认证失败
                AppCodeReply appCodeReply = HiServiceImpl.obtain().appAuth(BaseConstant.APP_KEY, BaseConstant.APP_SECRET);
                if (appCodeReply != null && appCodeReply.getReply() == 0) {
                    LogUtil.i(TAG, "weixin appCodeReply :" + appCodeReply.getReply());
                    SendAuth.Resp authResp = (SendAuth.Resp) resp;
                    HashMap<String, String> authMap = new HashMap<>(4);
                    authMap.put(Params.THIRD_CALLBACK, "code=" + authResp.code + "&appid=" + BaseConstant.WECHAT_APP_ID);
                    authMap.put(Params.THIRD_PLATFORMID, String.valueOf(LoginActivity.ID_WECHAT));
                    authMap.put(Params.APPCODE, appCodeReply.getCode());
                    authMap.put(Params.DEVICEID, DeviceConfig.getDeviceId(WXEntryActivity.this.getApplicationContext()));
                    LogUtil.i(TAG, "weixin thirdReplay 11111111111");
                    ThirdAccountOauthLoginReplay thirdReplay = HiServiceImpl.obtain().thirdAccountOauthLogin(authMap);
                    LogUtil.i(TAG, "weixin thirdReplay 22222222222");
                    if (thirdReplay != null && thirdReplay.getSignonReplyInfo() != null) {
                        LogUtil.e(TAG, "doweixinLogin getCustomerId:" + thirdReplay.getSignonReplyInfo().getCustomerId());
                        runOnUiThread(() -> ToastUtil.showShortToast("登录成功"));
                    } else {
                        LogUtil.e(TAG, "doweixinLogin thirdReplay :" + thirdReplay);
                        runOnUiThread(() -> ToastUtil.showShortToast("登录失败"));
                    }
                } else {
                    LogUtil.e(TAG, "weixin appCodeReply error:" + appCodeReply.getReply());
                    runOnUiThread(() -> ToastUtil.showShortToast("应用认证失败"));
                }
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);

    }



/*	if (arg0.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
		SendAuth.Resp resp = (SendAuth.Resp) arg0;
		if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
			Log.d(TAG, "code is:" + resp.code);
			ShowDialog(WAIT_DAILOG);
			mAccountManager.OAuthLogin("code=" + resp.code + "&appid="
					+ Status.APP_ID, LoginActivity.ID_WECHAT);
		} else {
			if (resp.errStr != null) {
				ToastUtil.makeText(this, getString(R.string.login_failed) + ":" + resp.errStr,
						Toast.LENGTH_SHORT).show();
			} else {
				ToastUtil.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
			}
			finish();
		}
	} else if (arg0.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
		SendMessageToWX.Resp resp = (SendMessageToWX.Resp) arg0;
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				UtilsHelper.onShowToast(mContext, R.string.info_share_ok);
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				Log.d(TAG, "User cancel");
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				Log.d(TAG, "Auth denied");
			default:
				UtilsHelper.onShowToast(mContext, R.string.info_share_error);
				break;
		}
		finish();
	}*/

}