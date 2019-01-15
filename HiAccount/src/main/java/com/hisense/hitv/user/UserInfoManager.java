package com.hisense.hitv.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.hisense.hitv.account.AccountSpUtil;
import com.hisense.hitv.account.HiServiceImpl;
import com.hisense.hitv.account.TokenManager;
import com.hisense.hitv.account.pool.PriorityRunnable;
import com.hisense.hitv.account.pool.ThreadPoolProxyFactory;
import com.hisense.hitv.hicloud.bean.account.CustomerInfo;
import com.hismart.base.AppUtil;
import com.hismart.base.BaseConstant;
import com.hismart.base.LogUtil;
import com.wj.android.http.RetrofitCallback;
import com.wj.android.http.XRetrofit;
/*
import com.wj.android.http.RetrofitCallback;
import com.wj.android.http.XRetrofit;
*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

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
                    LogUtil.d(TAG, "customerInfo.getPicUrl():" + customerInfo.getPicUrl());
                    Bitmap bitmap = null;
                    try {
                        bitmap = Glide.with(AppUtil.getApp())
                                .load(customerInfo.getPicUrl())
                                .asBitmap()
                                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get();
                    } catch (InterruptedException|ExecutionException e) {
                        e.printStackTrace();
                    }

                    setPic(bitmap);
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
        AccountSpUtil.setString(USER_EMAIL, email);
    }

    void setAddress(String address) {
        AccountSpUtil.setString(USER_ADDRESS, address);
    }

    void setMobile(String mobile) {
        AccountSpUtil.setString(USER_MOBILEPHONE, mobile);
    }

    void setNickname(String nickname) {
        AccountSpUtil.setString(USER_NICKNAME, nickname);
    }

    void setGender(int gender) {
        AccountSpUtil.setInt(USER_GENDER, gender);
    }

    String getUserName() {
        return AccountSpUtil.getString(USER_NAME);
    }

    long getBirthday() {
        return AccountSpUtil.getLong(USER_BIRTHDAY);
    }

    String getEmail() {
        return AccountSpUtil.getString(USER_EMAIL);
    }

    String getAddress() {
        return AccountSpUtil.getString(USER_ADDRESS);
    }

    String getMobile() {
        return AccountSpUtil.getString(USER_MOBILEPHONE);
    }

    String getNickname() {
        return  AccountSpUtil.getString(USER_NICKNAME);
    }

    int getGender() {
        return AccountSpUtil.getInt(USER_GENDER);
    }

    void setPic(Bitmap bitmap) {
        saveUserPicToSDCard(bitmap,USER_PIC_DIR,USER_PIC_NAME);
    }
    Bitmap getPic() {
        return BitmapFactory.decodeFile(USER_PIC_PATH);
    }

    private static final String USER_PIC_DIR = AppUtil.getApp().getExternalFilesDir(null).getAbsolutePath() + "/" + "image" + "/";
    private static final String USER_PIC_NAME = "photo.png";
    private static final String USER_PIC_PATH = USER_PIC_DIR + USER_PIC_NAME;

    private static void saveUserPicToSDCard(Bitmap bitmap, String dir, String imagename) {

        File path = new File(dir);
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(dir + imagename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void uploadUserPhoto() {
        //http://api.hismarttv.com/cam/user/upload_profile
        String getAccessUrl = BaseConstant.PHOTO_URI + "/cam/user/upload_profile";
        Map<String, String> params = new HashMap<>(4);
        params.put("format", "1");
        params.put("languageId", "0");
        params.put("profileName", "photo");
        params.put("accessToken", TokenManager.getInstance().getToken());
        List<MultipartBody.Part> parts = new ArrayList<>();

        File file = new File(USER_PIC_PATH);
        RequestBody requestFile =  RequestBody.create(MultipartBody.FORM, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("pic",file.getName(),requestFile);

        parts.add(part);
        XRetrofit.upload(getAccessUrl, params, parts, new RetrofitCallback() {
            @Override
            public void onResponse(Call<ResponseBody> call, ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    Log.e(TAG, "upload onResponse ="+result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "upload onFailure ="+t.getMessage());
            }
        });
    }

}
