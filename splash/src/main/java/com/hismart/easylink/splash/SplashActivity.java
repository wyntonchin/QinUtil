package com.hismart.easylink.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hismart.base.LogUtil;
import com.hismart.base.router.RemoteLoginService;
import com.hismart.base.router.RouterPath;

@Route(path = "/splash/splash")
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LogUtil.i(TAG,"onCreate");

        RemoteLoginService loginService =   (RemoteLoginService) ARouter.getInstance().build(RouterPath.HIACCOUNT_SERVICE_LOGIN_).navigation();
        loginService.isLogin();

        if(loginService.isLogin()){

            ARouter.getInstance().build(RouterPath.HIACCOUNT_ACTIVITY_LOGIN).navigation();

            finish();

        }
    }
}
