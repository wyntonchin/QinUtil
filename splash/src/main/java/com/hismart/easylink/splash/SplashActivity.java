package com.hismart.easylink.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hismart.base.AppUtil;
import com.hismart.base.LogUtil;
import com.hismart.base.ToastUtil;
import com.hismart.base.router.IRouteLoginService;
import com.hismart.base.router.InfoCallback;
import com.hismart.base.router.RouterPath;

@Route(path = RouterPath.SPLAHS_SPLASH)
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LogUtil.i(TAG,"onCreate");

        checkLogin();
    }


    private void checkLogin(){
        IRouteLoginService loginService =   (IRouteLoginService) ARouter.getInstance().build(RouterPath.HIACCOUNT_SERVICE_LOGIN).navigation();
        LogUtil.i(TAG,"onCreate old token="+loginService.getToken());
        if(loginService.isLogin()){
            runOnUiThread(() -> ToastUtil.showShortToast("登录状态正常"));
            //ARouter.getInstance().build(RouterPath.HIACCOUNT_ACTIVITY_LOGIN).navigation();
            //finish();
        }else {
            if(AppUtil.isNetworkConnected()){
                loginService.refreshToken(new InfoCallback<String>() {
                    @Override
                    public void onSuccess(String info) {
                        runOnUiThread(() -> ToastUtil.showShortToast("刷新token成功"));
                        LogUtil.i(TAG,"goto onSuccess new token="+loginService.getToken());
                    }

                    @Override
                    public void onError(int code, String message) {
                        ARouter.getInstance().build(RouterPath.HIACCOUNT_ACTIVITY_LOGIN).navigation();
                        LogUtil.i(TAG,"goto onError");
                    }
                });
            }else {
                runOnUiThread(() -> ToastUtil.showShortToast("请检查网络"));
            }
            LogUtil.i(TAG,"goto MainActivity");
        }
    }
}
