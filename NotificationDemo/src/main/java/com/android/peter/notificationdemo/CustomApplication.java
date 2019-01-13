package com.android.peter.notificationdemo;

import com.hismart.base.BaseApplication;

/**
 * Created by  peter on 2018/7/11.
 */

public class CustomApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannels.createAllNotificationChannels(this);
    }
}
