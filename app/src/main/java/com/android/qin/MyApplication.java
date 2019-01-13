package com.android.qin;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hismart.base.AppConfig;
import com.hismart.base.AppUtil;
import com.hismart.base.BaseApplication;

public class MyApplication extends BaseApplication {
    public static final String TAG = "BaseApplication";
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //qin 多进程会导致应用application被多次初始化
        if (TextUtils.equals(getCurrentProcessName(this), getPackageName())) {
            mContext = this;
            //AppUtil 初始化（方便全局获取ApplicationContext,RefWatcher）
            AppUtil.init(this);
            initRouter();
            initMoudleApp(this);
            initMoudleData(this);
        }
    }

    public static Context getAppContext() {
        return mContext.getApplicationContext();
    }

    private void initRouter() {
        if (BuildConfig.DEBUG){
            //打印日志
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
    @Override
    public void initMoudleApp(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApplication baseApp = (BaseApplication) clazz.newInstance();
                baseApp.initMoudleApp(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initMoudleData(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApplication baseApp = (BaseApplication) clazz.newInstance();
                baseApp.initMoudleData(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }



    private String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
