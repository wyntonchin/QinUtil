package com.android.peter.notificationdemo;

import android.app.Application;

import com.hismart.base.BaseApplication;

/**
 * Created by  peter on 2018/7/11.
 */

public class CustomApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initMoudleApp(this);
        initMoudleData(this);
    }

    @Override
    public void initMoudleApp(Application application) {
        NotificationChannels.createAllNotificationChannels(this);
    }

    @Override
    public void initMoudleData(Application application) {

    }
}
