package com.hismart.base.router;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface RemoteLoginService extends IProvider {
    boolean isLogin();

    void refreshToken(InfoCallback<String> s);



}