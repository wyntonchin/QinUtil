package com.hisense.hitv.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hisense.hitv.account.pool.PriorityRunnable;
import com.hisense.hitv.account.pool.ThreadPoolProxyFactory;
import com.hisense.hitv.hicloud.bean.account.CustomerInfo;
import com.hismart.base.LogUtil;
import com.wj.android.http.RetrofitCallback;
import com.wj.android.http.XRetrofit;

import okhttp3.ResponseBody;

public class UserInfoManager {
    private static final String TAG = "UserInfoManager";

    private static class SingleTon {
        private static UserInfoManager INSTANCE = new UserInfoManager();
    }

    public static UserInfoManager getInstance() {
        return SingleTon.INSTANCE;
    }

    public void refreshUserInfo() {
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "refreshUserInfo:" + Thread.currentThread().getName());
                CustomerInfo customerInfo = HiServiceImpl.obtain().getCustomerInfo(TokenManager.getInstance().getToken());
                if (customerInfo != null && customerInfo.getReply() == 0) {
                    LogUtil.d(TAG, "refreshUserInfo getCustomerInfo success!!!");
                    LogUtil.d(TAG, "customerInfo.getName:" + customerInfo.getLoginName());
                    LogUtil.d(TAG, "customerInfo.getNickName:" + customerInfo.getNickName());
                    LogUtil.d(TAG, "customerInfo.getPhone:" + customerInfo.getMobilePhone());
                    LogUtil.d(TAG, "customerInfo.localtion:" + customerInfo.getAddress());
                    LogUtil.d(TAG, "customerInfo.sex is:" + customerInfo.getGender());
                    LogUtil.d(TAG, "customerInfo.email is:" + customerInfo.getEmail());
                    LogUtil.d(TAG, "customerInfo.birthday is:" + customerInfo.getBirth());
                    saveUserInfo(customerInfo);

                    XRetrofit.download(customerInfo.getPicUrl(), new RetrofitCallback() {
                        @Override
                        public void onResponse(retrofit2.Call<ResponseBody> call, ResponseBody responseBody) {
                            Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
                            //savePicUrl();
                        }

                        @Override
                        public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);
    }


    private final static String USER_NAME = "user_name";
    private final static String USER_EMAIL = "user_email";
    private final static String USER_GENDER = "user_gender";
    private final static String USER_BIRTHDAY = "user_birthday";
    private final static String USER_ADDRESS = "user_address";
    private final static String USER_MOBILEPHONE = "user_mobile";
    private final static String USER_NICKNAME = "user_nickname";


    void saveUserInfo(CustomerInfo customerInfo) {
        setUserName(customerInfo.getLoginName());
        setBirthday(customerInfo.getBirth());
        setEmail(customerInfo.getEmail());
        setAddress(customerInfo.getAddress());
        setMobile(customerInfo.getMobilePhone());
        setNickname(customerInfo.getNickName());
        setGender(customerInfo.getGender());
    }

    void setUserName(String name) {
        AccountSpUtil.setString(USER_NAME, name);
    }

    void setBirthday(long birthday) {
        AccountSpUtil.setLong(USER_BIRTHDAY, birthday);
    }

    void setEmail(String email) {
        AccountSpUtil.setString(USER_EMAIL,email);
    }

    void setAddress(String address) {
        AccountSpUtil.setString(USER_ADDRESS, address);
    }

    void setMobile(String mobile) {
        AccountSpUtil.setString(USER_MOBILEPHONE,mobile);
    }

    void setNickname(String nickname) {
        AccountSpUtil.setString(USER_NICKNAME,nickname);
    }

    void setGender(int gender) {
        AccountSpUtil.setInt(USER_GENDER, gender);
    }

    void savePicUrl(Bitmap bitmap) {

    }

}
