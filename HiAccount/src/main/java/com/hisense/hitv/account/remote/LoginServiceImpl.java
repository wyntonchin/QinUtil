package com.hisense.hitv.account.remote;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hisense.hitv.account.TokenManager;
import com.hisense.hitv.user.UserInfoManager;
import com.hismart.base.router.IRouteLoginService;
import com.hismart.base.router.InfoCallback;
import com.hismart.base.router.RouterPath;

/**
 * 实现接口
 */
@Route(path = RouterPath.HIACCOUNT_SERVICE_LOGIN, name = "login服务")
public class LoginServiceImpl implements IRouteLoginService {

    @Override
    public boolean isLogin() {
        //有token并且不需要刷新，则返回true
        return !TextUtils.isEmpty(TokenManager.getInstance().getToken()) && !(TokenManager.getInstance().isTokenNeedRefresh());
    }

    @Override
    public void refreshToken(InfoCallback<String> callback) {
        TokenManager.getInstance().refreshNewToken(callback);
    }

    @Override
    public String getToken(){
        return TokenManager.getInstance().getToken();
    }

    @Override
    public void refreshUserInfo() {
        UserInfoManager.getInstance().refreshUserInfo();
    }

    @Override
    public void addTokenListener(TokenListener tokenListener) {
        TokenManager.getInstance().addTokenListener(tokenListener);
    }

    @Override
    public void removeTokenListener(TokenListener tokenListener) {
        TokenManager.getInstance().removeTokenListener(tokenListener);
    }

    @Override
    public void removeAllTokenListener() {
        TokenManager.getInstance().removeAllTokenListener();
    }

    @Override
    public void init(Context context) {

    }
}
