package com.wj.android.http;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;

/**
 * 作者：wangwnejie on 2017/9/28 13:42
 * 邮箱：wang20080990@163.com
 */

public class XRetrofit {
    private static GlobalConfig sGlobalConfig;
    private static volatile Retrofit.Builder sRetrofitBuilder;

    public static GlobalConfig init() {
        sGlobalConfig = GlobalConfig.getInstance();
        return sGlobalConfig;
    }

    protected static GlobalConfig getGlobalConfig() {
        return sGlobalConfig;
    }

    protected static Retrofit.Builder getRetrofitBuilder() {
        if (sRetrofitBuilder == null) {
            synchronized (XRetrofit.class) {
                if (sRetrofitBuilder == null) {
                    sRetrofitBuilder = new Retrofit.Builder();
                }
            }
        }
        return sRetrofitBuilder;
    }

    private static Retrofit getRetrofit(String baseUrl) {
        getRetrofitBuilder().baseUrl(baseUrl);
        if (sGlobalConfig != null) {
            getRetrofitBuilder().client(sGlobalConfig.getOkHttpClientBuilder().build());
        }
        return getRetrofitBuilder().build();
    }

    public static <T> T create(Class<T> service, String baseUrl) {
        return getRetrofit(baseUrl).create(service);
    }

    public static void get(String url, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().get(url, retrofitCallback);
    }

    public static void get(String url, Map<String,String> params, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().get(url, params, retrofitCallback);
    }

    public static void post(String url, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().post(url, retrofitCallback);
    }

    public static void post(String url, Map<String,String> params, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().post(url, params, retrofitCallback);
    }

    public static void postBody(String url, String content, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().postBody(url, content, retrofitCallback);
    }

    public static void put(String url, Map<String, String> params, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().put(url, params, retrofitCallback);
    }

    public static void patch(String url, Map<String, String> params, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().patch(url, params, retrofitCallback);
    }

    public static void delete(String url, Map<String, String> params, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().delete(url, params, retrofitCallback);
    }

    public static void postWithHeader(String url, Map<String, String> headerMap, Map<String, String> params, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().postWithHeader(url, headerMap, params, retrofitCallback);
    }

    public static void upload(String url, Map<String, String> params, List<MultipartBody.Part> parts, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().upload(url, params, parts, retrofitCallback);
    }

    public static void download(String url, RetrofitCallback retrofitCallback) {
        RetrofitHttpManager.getInstance().download(url, retrofitCallback);
    }
}
