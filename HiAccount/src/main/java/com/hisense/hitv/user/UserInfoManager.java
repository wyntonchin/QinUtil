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
import com.hisense.hitv.user.retrofit.ApiException;
import com.hisense.hitv.user.retrofit.BaseInfo;
import com.hisense.hitv.user.retrofit.ErrorBean;
import com.hisense.hitv.user.retrofit.JuGsonCallBack;
import com.hismart.base.AppUtil;
import com.hismart.base.BaseConstant;
import com.hismart.base.LogUtil;

import com.hismart.base.router.InfoCallback;
import com.wj.android.http.BaseView;
import com.wj.android.http.RetrofitCallback;
import com.wj.android.http.XRetrofit;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
                    } catch (InterruptedException | ExecutionException e) {
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
        return AccountSpUtil.getString(USER_NICKNAME);
    }

    int getGender() {
        return AccountSpUtil.getInt(USER_GENDER);
    }

    void setPic(Bitmap bitmap) {
        BitmapUtil.saveUserPicToSDCard(bitmap, USER_PIC_DIR, USER_PIC_NAME);
    }

    Bitmap getPic() {
        return BitmapFactory.decodeFile(USER_PIC_PATH);
    }

    private static final String USER_PIC_DIR = AppUtil.getApp().getExternalFilesDir(null).getAbsolutePath() + "/" + "image" + "/";
    private static final String USER_PIC_NAME = "photo.png";
    private static final String USER_PIC_PATH = USER_PIC_DIR + USER_PIC_NAME;

    //{"response":{"resultCode":0}}
    private void postUserPhoto(InfoCallback infoCallback, String path) {
        String getAccessUrl = BaseConstant.ACCOUNT_URI + "/cam/user/upload_profile";
        List<MultipartBody.Part> parts = new ArrayList<>();
        MultipartBody.Part part = MultipartBody.Part.createFormData("format", "1");
        parts.add(part);
        part = MultipartBody.Part.createFormData("languageId", "0");
        parts.add(part);
        part = MultipartBody.Part.createFormData("profileName", "photo");
        parts.add(part);
        part = MultipartBody.Part.createFormData("accessToken", TokenManager.getInstance().getToken());
        parts.add(part);
        //最后注入图片路径
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MultipartBody.FORM, file);
        MultipartBody.Part part5 = MultipartBody.Part.createFormData("pic", file.getName(), requestFile);
        parts.add(part5);
        XRetrofit.upload(getAccessUrl, new HashMap<>(0), parts, new JuGsonCallBack<BaseInfo<ErrorBean>>() {
            @Override
            protected void onSuccess(BaseInfo<ErrorBean> response, BaseView baseView) {
                LogUtil.e(TAG, "upload onSuccess =" + response.getResponse().getResultCode());
                infoCallback.onSuccess(response.getResponse().getResultCode());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e(TAG, "upload onFailure =" + t.getMessage());
                ApiException apiException = (ApiException) t;
                infoCallback.onError(apiException.getErrorCode(), apiException.getMessage());
            }
        });
    }


    public void uploadUserPhoto(InfoCallback infoCallback) {
        PriorityRunnable priorityRunnable = new PriorityRunnable(PriorityRunnable.Priority.NORMAL, new Runnable() {
            @Override
            public void run() {
                LogUtil.w(TAG, "uploadUserPhoto:" + Thread.currentThread().getName());
                getPhotoPolicyAndUpload(infoCallback);
            }
        });
        ThreadPoolProxyFactory.getNormal().execute(priorityRunnable);

    }

    private void getPhotoPolicyAndUpload(InfoCallback infoCallback) {
        String policyUrl = BaseConstant.ACCOUNT_URI + "/cam/user/get_picturetype";
        Map<String, String> map = new HashMap<>(6);
        map.put("accessToken", TokenManager.getInstance().getToken());
        map.put("version", "2.0");
        map.put("sign", "");
        map.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("languageId", "0");
        map.put("sourceType", "1");

        XRetrofit.get(policyUrl, map, new RetrofitCallback() {
            @Override
            public void onResponse(Call<ResponseBody> call, ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    Log.e(TAG, "httpGetPhotoPolicy onResponse =" + result);
                    PhotoPolicy photoPolicy = parsePolicyXml(result);
                    if (photoPolicy.checkMode == 2) {
                        LogUtil.d(TAG, "Ignore photo policy");
                    } else {
                        LogUtil.d(TAG, " photo policy handle Scale");
                        BitmapUtil.saveBitmapToSDCard(getPic(), USER_PIC_DIR, USER_PIC_NAME,
                                photoPolicy.fileFormat, photoPolicy.pictureLength,
                                photoPolicy.pictureWidth, photoPolicy.pictureSize, photoPolicy.checkMode);
                    }
                    postUserPhoto(infoCallback, USER_PIC_DIR + USER_PIC_NAME);

                } catch (IOException e) {
                    e.printStackTrace();
                    infoCallback.onError(888888, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                infoCallback.onError(888888, t.getMessage());
                Log.e(TAG, "httpGetPhotoPolicy onFailure =" + t.getMessage());
            }
        });
    }

    private class PhotoPolicy {
        int pictureLength;
        int pictureWidth;
        int pictureSize;
        String fileFormat;
        int checkMode;
        int resultCode;
    }


    private PhotoPolicy parsePolicyXml(String xmlStr) {
        PhotoPolicy policy = null;
        LogUtil.d(TAG, "getPolicy is xmlStr:" + xmlStr);

        try {
            policy = new PhotoPolicy();
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(new StringReader(xmlStr));

            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                if (type == XmlPullParser.START_TAG) {
                    String name = parser.getName();

                    if (name.equalsIgnoreCase("pictureSize")) {
                        policy.pictureSize = Integer.parseInt(parser.nextText());
                        LogUtil.d(TAG, "Limitation of photo size:" + policy.pictureSize);
                    }

                    if (name.equalsIgnoreCase("pictureLength")) {
                        policy.pictureLength = Integer.parseInt(parser.nextText());
                        LogUtil.d(TAG, "Limitation of photo length:" + policy.pictureLength);
                    }

                    if (name.equalsIgnoreCase("pictureWidth")) {
                        policy.pictureWidth = Integer.parseInt(parser.nextText());
                        LogUtil.d(TAG, "Limitation of photo width:" + policy.pictureWidth);
                    }

                    if (name.equalsIgnoreCase("fileFormat")) {
                        policy.fileFormat = parser.nextText();
                        LogUtil.d(TAG, "Limitation of photo fileFormat:" + policy.fileFormat);
                    }

                    if (name.equalsIgnoreCase("checkMode")) {
                        policy.checkMode = Integer.parseInt(parser.nextText());
                        LogUtil.d(TAG, "CheckMode" + policy.checkMode);
                    }
                }

                type = parser.next();
            }

        } catch (IOException | XmlPullParserException e) {
            policy = null;
            e.printStackTrace();
        }
        return policy;
    }


/*    void postUserInfo(String nickName, String mobilePhone, int gender, long birthday, String address, String email){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("nickName", nickName);
        map.put("mobilePhone", mobilePhone);
        map.put("sex", String.valueOf(gender));
        map.put("birthday", String.valueOf(birthday));
        map.put("address", address);
        map.put("email",email);
        HiServiceImpl.obtain().updateCustomerInfo(TokenManager.getInstance().getToken(), map);
    }*/



}



    /*
    * <?xml version="1.0" encoding="utf-8"?>

<response>
  <resultCode>0</resultCode>
  <desc/>
  <pictureSize>100</pictureSize>
  <pictureLength>132</pictureLength>
  <pictureWidth>165</pictureWidth>
  <fileFormat>jpg,gif,png</fileFormat>
  <tvMode>0</tvMode>
  <checkMode>2</checkMode>
  <owner>I</owner>
  <displayFlag>1</displayFlag>
  <colorBit>24</colorBit>
  <signatureServer>k4l/ofPsgUOlGM5Zw9HlGdEMVurN8eWA3ZSKaCZR7xR+5XC8/b0ko2jCdl0xtPyPGWdD3kULt13JL90q6sR2Bw==</signatureServer>
</response>

    * */
