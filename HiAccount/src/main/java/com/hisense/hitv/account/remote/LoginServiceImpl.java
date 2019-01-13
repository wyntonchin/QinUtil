package com.hisense.hitv.account.remote;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hismart.base.router.IRouteLoginService;
import com.hismart.base.router.InfoCallback;
import com.hismart.base.router.RouterPath;

/**
 * 实现接口
 */
@Route(path = RouterPath.HIACCOUNT_SERVICE_LOGIN_, name = "login服务")
public class LoginServiceImpl implements IRouteLoginService {

    @Override
    public boolean isLogin() {
        return true;
    }

    @Override
    public void refreshToken(InfoCallback<String> s) {

    }

    @Override
    public void init(Context context) {

    }
}
