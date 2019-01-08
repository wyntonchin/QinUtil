package com.android.peter.notificationdemo;

import android.app.Application;

import com.hismart.base.ModuleApplication;

/**
 * Created by  peter on 2018/7/11.
 */

public class CustomApplication extends ModuleApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannels.createAllNotificationChannels(this);
    }
}
