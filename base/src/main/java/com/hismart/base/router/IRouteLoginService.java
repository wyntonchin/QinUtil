package com.hismart.base.router;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IRouteLoginService extends IProvider {
    boolean isLogin();

    void refreshToken(InfoCallback<String> s);

    String getToken();

    void refreshUserInfo();

}