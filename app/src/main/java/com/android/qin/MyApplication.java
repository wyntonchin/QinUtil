package com.android.qin;

import android.app.Application;

import com.android.qin.util.AppUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.init(this.getApplicationContext());
    }
}
