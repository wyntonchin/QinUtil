package com.wj.android.http;

import android.util.Log;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/8/4.
 */

public class RetrofitHttpManager {

    private static final String TAG = "RetrofitHttpManager";
    private volatile static RetrofitHttpManager sInstance;
    private ApiService mApiService;

    private RetrofitHttpManager() {
        initRetrofit();
    }

    public static RetrofitHttpManager getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitHttpManager.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitHttpManager();
                }
            }
        }
        return sInstance;
    }

    private void initRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(GlobalConfig.DEFAULT_BASE_URL);
        Log.e(TAG,"initRetrofit111 ");
        XRetrofit.init();
        if (XRetrofit.getGlobalConfig() != null) {
            builder.client(XRetrofit.getGlobalConfig().getOkHttpClientBuilder().build());
        }
        mApiService = builder.build().create(ApiService.class);
    }

    private void execute(Call<ResponseBody> call, final RetrofitCallback retrofitCallback){
        retrofitCallback.onStart(call);
        Log.i(TAG,"execute call.request :"+call.request());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (!response.isSuccessful()) {
                        onFailure(call, new Exception(String.format("%s : %d", "request failed, response's code is", response.code())));
                        return;
                    }
                    if (response.body() == null) {
                        onFailure(call, new Exception("service return data empty"));
                        return;
                    }
                    retrofitCallback.onResponse(call,response.body());
                    retrofitCallback.onFinish(call);

                } catch (Exception e) {
                    onFailure(call, e);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG,"onFailure t :"+t.getMessage());
                retrofitCallback.onFailure(call, t);
                retrofitCallback.onFinish(call);
            }
        });
    }

    public void get(String url, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.get(url);
        execute(call, retrofitCallback);
    }

    public void get(String url, Map<String,String> params, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.get(url, params);
        execute(call, retrofitCallback);
    }

    public void post(String url, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.post(url);
        execute(call, retrofitCallback);
    }

    public void post(String url, Map<String,String> params, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.post(url, params);
        execute(call, retrofitCallback);
    }

    public void postBody(String url, String content, RetrofitCallback retrofitCallback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), content);
        Call<ResponseBody> call = mApiService.postBody(url,requestBody);
        execute(call, retrofitCallback);
    }

    public void put(String url, Map<String, String> params, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.put(url, params);
        execute(call, retrofitCallback);
    }

    public void patch(String url, Map<String, String> params, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.patch(url, params);
        execute(call, retrofitCallback);
    }

    public void delete(String url, Map<String, String> params, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.delete(url, params);
        execute(call, retrofitCallback);
    }

    public void postWithHeader(String url, Map<String, String> headerMap, Map<String, String> params, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.postWithHeader(url, headerMap, params);
        execute(call, retrofitCallback);
    }

    public void upload(String url, Map<String, String> params, List<MultipartBody.Part> parts, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.upload(url, params, parts);
        execute(call, retrofitCallback);
    }

    public void download(String url, RetrofitCallback retrofitCallback) {
        Call<ResponseBody> call = mApiService.download(url);
        execute(call, retrofitCallback);
    }

}
