package com.android.qin;

import android.app.Application;

import com.android.qin.util.AppUtil;
import com.android.qin.util.LogUtil;
import com.hismart.base.ModuleApplication;

public class MyApplication extends ModuleApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("qwd","123");
        //AppUtil.init(this.getApplicationContext());
    }
}
