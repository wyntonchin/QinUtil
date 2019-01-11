package com.hisense.hitv.account;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hisense.hitv.account.pool.PriorityExecutor;
import com.hisense.hitv.account.pool.PriorityRunnable;
import com.hisense.hitv.account.pool.ThreadPoolProxyFactory;
import com.hisense.hitv.hicloud.bean.account.AppCodeReply;
import com.hisense.hitv.hicloud.bean.account.AppCodeSSO;
import com.hisense.hitv.hicloud.bean.account.BlogInfo;
import com.hisense.hitv.hicloud.bean.account.BlogStatusReply;
import com.hisense.hitv.hicloud.bean.account.GetUriReply;
import com.hisense.hitv.hicloud.bean.account.SignonReplyInfo;
import com.hisense.hitv.hicloud.bean.global.HiSDKInfo;
import com.hisense.hitv.hicloud.factory.HiCloudServiceFactory;
import com.hisense.hitv.hicloud.service.HiCloudAccountService;
import com.hisense.hitv.hicloud.util.DeviceConfig;
import com.hisense.hitv.hicloud.util.Params;
import com.hismart.base.BaseConstant;
import com.hismart.base.BaseToolbarCompatActivity;
import com.hismart.base.LogUtil;
import com.hismart.base.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;

public class LoginActivity extends BaseToolbarCompatActivity implements OAuthLoginActivity.AuthListener {

    private final static String TAG = "LoginActivity";

    public final static int ID_BLOG_SINA = 1003;
    public final static int ID_QQ = 1002;
    public final static int ID_WECHAT = 1100;

    private EditText ed_username;
    private EditText ed_password;
    private TextView tx_findpwd;
    private TextView tx_register;

    private ImageView btn_wechat_login;
    private ImageView btn_sinablog_login;
    private Button btn_login;

    private ImageButton btn_pwd_dis;

    private IWXAPI api = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeftButtonIsBack(false);
        setMiddleTitle(R.string.title_activity_login);
        setContentView(R.layout.activity_login);
        findViews();
        checkPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, "需要获取手机状态");
    }

    private void findViews() {

        ed_username = findViewById(R.id.username);
        ed_password = findViewById(R.id.password);
        ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        tx_findpwd = findViewById(R.id.forget_password);
        tx_register = findViewById(R.id.register_now);
        tx_register.setOnClickListener(v -> {
            LogUtil.d(TAG, "Prepare to register");
            Intent it_register;
            it_register = new Intent(this.getApplicationContext(), RegisterMobileActivity.class);
            LoginActivity.this.startActivity(it_register);
        });
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> {
            doLogin();
        });

        btn_pwd_dis = findViewById(R.id.btn_pwd_display);
        btn_wechat_login = findViewById(R.id.img_wechat);
        btn_wechat_login.setOnClickListener(v -> {
            doWeChatLogin();
        });
        btn_sinablog_login = findViewById(R.id.img_sina_blog);
        btn_sinablog_login.setOnClickListener(v -> {
            startWeiboEntry();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void processMessage(Message msg) {

    }

    void doLogin() {
        if (ed_username.getText() == null || ed_username.getText().toString().isEmpty()) {
            ToastUtil.showShortToast(R.string.hi_account_please_input_phone_number);
            return;
        }
        showProgressDialog(false);
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "btn_login:" + Thread.currentThread().getName());
                //先认证APP
                AppCodeReply appCodeReply = HiServiceImpl.obtain().appAuth(BaseConstant.APP_KEY, BaseConstant.APP_SECRET);
                if (appCodeReply != null && appCodeReply.getReply() == 0) {
                    //认证账号
                    SignonReplyInfo sigonReply = HiServiceImpl.obtain().login(ed_username.getText().toString(), ed_password.getText().toString(),
                            HiServiceImpl.obtain().getDeviceId(LoginActivity.this), appCodeReply.getCode());
                    if (sigonReply != null && sigonReply.getToken() != null) {
                        LogUtil.e(TAG, "doLogin getCustomerId:" + sigonReply.getCustomerId());
                        runOnUiThread(() -> ToastUtil.showShortToast("登录成功"));
                    } else {
                        runOnUiThread(() -> ToastUtil.showShortToast("登录失败"));
                    }
                } else {
                    runOnUiThread(() -> ToastUtil.showShortToast("应用认证失败"));
                }
                runOnUiThread(() -> dismissProgressDialog());
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);
    }


    void doWeChatLogin() {
        LogUtil.w(TAG, "doWeChatLogin");
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, BaseConstant.WECHAT_APP_ID, true);
        if (!api.isWXAppInstalled()) {
            ToastUtil.showShortToast("请先安装微信客户端");
            return;
        }
        // 将应用的appId注册到微信
        if (api.registerApp(BaseConstant.WECHAT_APP_ID)) {
            LogUtil.i(TAG, "Register app to wechat successfully");
        } else {
            ToastUtil.showShortToast(R.string.hi_account_wechat_register_app_failed);
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "HisenseControl";
        api.sendReq(req);
    }

    void startWeiboEntry() {
        Intent intent = new Intent(LoginActivity.this,WeiboEntry.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(String url) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError() {

    }
}