package com.hismart.base;

import android.app.ActivityManager;
import android.app.Application;

public abstract class BaseApplication extends Application {
    public static final String TAG = "BaseApplication";

    public abstract void initMoudleApp(Application application);
    public abstract void initMoudleData(Application application);


}
