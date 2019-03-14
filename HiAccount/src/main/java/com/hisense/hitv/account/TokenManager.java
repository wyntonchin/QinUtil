package com.hisense.hitv.account;

import android.text.TextUtils;
import android.util.Log;

import com.hisense.hitv.account.pool.PriorityRunnable;
import com.hisense.hitv.account.pool.ThreadPoolProxyFactory;
import com.hisense.hitv.hicloud.bean.account.SignonReplyInfo;
import com.hismart.base.BaseConstant;
import com.hismart.base.LogUtil;
import com.hismart.base.router.IRouteLoginService;
import com.hismart.base.router.InfoCallback;

import java.util.HashSet;
import java.util.Set;

/**
 * @author qinwendong
 * @date 2019/1/14
 * descrption:
 */
public class TokenManager {
    private static final String TAG = "TokenManager";

    private static class SingleTon{
        private static TokenManager INSTANCE = new TokenManager();
    }

    public static TokenManager getInstance(){
        return SingleTon.INSTANCE;
    }

    private Set<IRouteLoginService.TokenListener> mTokenListeners = new HashSet<>();


    private float TOKEN_CONSUME_TIME_PERCENT = 0.9f;


    public void refreshNewToken(InfoCallback<String> callback) {
        LogUtil.d(TAG, "refreshNewToken");
        if(isRefreshTokenHasExpire()){
            callback.onError(888888, "refreshToken过期");
            return;
        }

        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "refreshNewToken:" + Thread.currentThread().getName());
                SignonReplyInfo sigonReply = HiServiceImpl.obtain().refreshToken(BaseConstant.APP_KEY);
                if (sigonReply != null && sigonReply.getReply() == 0) {
                    callback.onSuccess(sigonReply.getDesc());
                    notifyTokenUpdate(sigonReply.getToken());
                    LogUtil.e(TAG, "refreshNewToken success:customerId =" + sigonReply.getCustomerId());
                } else if(sigonReply != null ){
                    callback.onError(Integer.valueOf(sigonReply.getError().getErrorCode()), sigonReply.getError().getErrorName());
                    LogUtil.e(TAG, "refreshNewToken errorcode:"+sigonReply.getError().getErrorCode());
                }else {
                    LogUtil.e(TAG, "refreshNewToken unknow error");
                }
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);
    }

    public boolean isTokenNeedRefresh() {
        LogUtil.d(TAG, "---isTokenNeedRefresh--");
        if (TextUtils.isEmpty(getToken())) {
            return true;
        }
        long createTime = getTokenCreateTime();
        long expireDuration = getTokenExpireDuration();
        long currentTime = System.currentTimeMillis() / 1000;
        LogUtil.d(TAG, "---isTokenNeedRefresh--createTime=" + createTime + "--expireDuration=" + expireDuration + "--currentTime=" + currentTime);
        long consume = currentTime - createTime;

        if(createTime <= 0 || expireDuration <= 0){
            return true;
        }

        if (consume >= expireDuration * TOKEN_CONSUME_TIME_PERCENT) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * refreshtoken是否已经过期
     *
     * @return
     */
    public boolean isRefreshTokenHasExpire() {
        LogUtil.d(TAG, "isRefreshTokenHasExpire");
        if (TextUtils.isEmpty(getRefreshToken())) {
            return true;
        }
        long createTime = getRefreshTokenCreateTime();
        long expireDuration = getRefreshTokenExpireDuration();
        long currentTime = System.currentTimeMillis() / 1000;

        long consume = currentTime - createTime;
        if(createTime <= 0 || expireDuration <= 0){
            return true;
        }
        if (consume > expireDuration) {
            return true;
        }else {
            return false;
        }

    }

    public String getToken() {
        return AccountSpUtil.getString(HiServiceImpl.TOKEN);
    }

    long getTokenCreateTime() {
        return AccountSpUtil.getLong(HiServiceImpl.TOKEN_CREATE);
    }

    long getTokenExpireDuration() {
        return AccountSpUtil.getLong(HiServiceImpl.TOKEN_EXPIRE);

    }

    String getRefreshToken() {
        return AccountSpUtil.getString(HiServiceImpl.REFRESH_TOKEN);
    }

    long getRefreshTokenCreateTime() {
        return AccountSpUtil.getLong(HiServiceImpl.REFRESH_TOKEN_CREATE);
    }

    long getRefreshTokenExpireDuration() {
        return AccountSpUtil.getLong(HiServiceImpl.REFRESH_TOKEN_EXPIRE);
    }

    void setToken(String token) {
        AccountSpUtil.setString(HiServiceImpl.TOKEN, token);
    }

    void setTokenCreateTime(long time) {
        AccountSpUtil.setLong(HiServiceImpl.TOKEN_CREATE, time);
    }

    void setTokenExpireDuration(long duration) {
        AccountSpUtil.setLong(HiServiceImpl.TOKEN_EXPIRE, duration);

    }

    void setRefreshToken(String refreshToken) {
        AccountSpUtil.setString(HiServiceImpl.REFRESH_TOKEN, refreshToken);
    }

    void setRefreshTokenCreateTime(long time) {
        AccountSpUtil.setLong(HiServiceImpl.REFRESH_TOKEN_CREATE, time);
    }

    void setRefreshTokenExpireDuration(long duration) {
        AccountSpUtil.setLong(HiServiceImpl.REFRESH_TOKEN_EXPIRE, duration);
    }


    private void notifyTokenUpdate(String token){
        Log.d(TAG,"notify token: "+token);
        synchronized(mTokenListeners){
            for(IRouteLoginService.TokenListener l : mTokenListeners){
                l.onUpdateToken(token);
            }
        }

    }

    public void addTokenListener(IRouteLoginService.TokenListener tokenListener) {
        synchronized (mTokenListeners){
            mTokenListeners.add(tokenListener);
        }
    }

    public void removeTokenListener(IRouteLoginService.TokenListener tokenListener) {
        synchronized (mTokenListeners) {
            mTokenListeners.remove(tokenListener);
        }
    }

    public void removeAllTokenListener() {
        synchronized (mTokenListeners) {
            mTokenListeners.clear();
        }
    }


}
