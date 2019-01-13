package com.hismart.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;

public class ModuleApplication extends Application {
    public static final String TAG = "MyApplication";
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //qin 多进程会导致应用application被多次初始化
        if (TextUtils.equals(getCurrentProcessName(this), getPackageName())) {
            mContext = this;
            //AppUtil 初始化（方便全局获取ApplicationContext,RefWatcher）
            AppUtil.init(this);

            if (BuildConfig.DEBUG) {
                //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
                ARouter.openDebug();
                ARouter.openLog();
            }
            ARouter.init(this);
        }
    }

    public static Context getAppContext() {
        return mContext.getApplicationContext();
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
