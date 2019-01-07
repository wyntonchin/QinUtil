package com.hisense.smarthome.sdk.http;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.hisense.smarthome.sdk.network.AuthFailureError;
import com.hisense.smarthome.sdk.network.Request.Method;
import com.hisense.smarthome.sdk.network.RequestQueue;
import com.hisense.smarthome.sdk.network.TimeoutError;
import com.hisense.smarthome.sdk.network.VolleyError;
import com.hisense.smarthome.sdk.network.toolbox.RequestFuture;
import com.hisense.smarthome.sdk.network.toolbox.StringRequest;
import com.hisense.smarthome.sdk.util.Constants;
import com.hisense.smarthome.sdk.util.Params;
import com.hisense.smarthome.sdk.util.SDKUtil;

import org.apache.http.HttpStatus;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HttpHandler {
    private final static String TAG = "HttpHandler";
    private final static String requestErrorXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>";
    ;
    private final static String requestErrorJson = "{\"response\":{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\",\"resultcode\":1,\"errorcode\":\"000000\",\"errordesc\":\"networkError\"}}";
    private final static String requestDefultErrorDesc = "networkError";
    private final static String response304 = "{\"response\":{\"resultCode\":304}}";
    private static RequestQueue requestQueue = null;
    private static PublicKey publicKey = HiCloudKey.getPublicKey();

    /**
     * @param url            地址
     * @param verifyFlag     是否校验签名
     * @param dateType       json or xml
     * @param jsonVerifyType json格式的延签的方式
     * @return
     */
    public static String httpUrlGetString(String url, boolean verifyFlag, int dateType,
                                          int jsonVerifyType) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            // mException =new VolleyError("do network on mainThread");
            return returnException(dateType, "do network on mainThread");
        }
        if (TextUtils.isEmpty(url)) {
            return returnException(dateType, "url is null");
        }
        String result = null;
        SDKUtil.LogD(TAG, "httpGetUrl=" + url);
        RequestFuture<String> requestFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(SDKUtil.toUTF_8(url), requestFuture,
                requestFuture) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept-Encoding", "gzip");
                return headers;
            }

        };
        stringRequest.setShouldCache(false);
        requestQueue = CustomRequestQueue.getRequestQueue();
        if (requestQueue == null) {
            return result;
        }
        requestQueue.add(stringRequest);
        try {
            result = requestFuture.get();
            SDKUtil.LogD(TAG, "requestFuture.get()=" + result);
            if (response304.equals(result)) {
                return response304;
            }
        } catch (InterruptedException e) {
            Log.e(TAG, SDKUtil.getStackTrace(e));
            return returnException(dateType, "InterruptedException" + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, SDKUtil.getStackTrace(e));
            if (e != null && e.getCause() != null && e.getCause() instanceof TimeoutError) {
                return returnException(dateType, "TimeoutError:" + e.getCause().getMessage());
            }
            if (e != null && e.getCause() != null && e.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) e.getCause();
                if (volleyError.networkResponse != null) {
                    Log.e(TAG,
                            "networkResponse.statusCode=" + volleyError.networkResponse.statusCode);
                    if (volleyError.networkResponse.statusCode == HttpStatus.SC_NOT_MODIFIED) {
                        return response304;
                    } else {
                        return returnException(dateType,
                                "networkResponse != null:"
                                        + volleyError.networkResponse.statusCode);
                    }
                } else {
                    return returnException(dateType,
                            "networkResponse == null:" + e.getMessage());
                }
            } else {
                return returnException(dateType, "ExecutionException:" + e.getMessage());
            }
        } catch (AssertionError error) {
            Log.e(TAG, error.getMessage());
            return returnException(dateType, "AssertionError:" + error.getMessage());
        }
        if (verifyFlag && !SDKUtil.isEmpty(result)) {
            try {
                if (Constants.DATATYPE_XML == dateType) {
                    result = HiCloudKey.verifySignature(result, publicKey);
                } else {// JSON 验签
                    if (jsonVerifyType == 2) {
                        result = HiCloudKey.verifySignatureJson2(result, publicKey);
                    } else {
                        result = HiCloudKey.verifySignatureJson(result, publicKey);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, SDKUtil.getStackTrace(e));
                return returnSignErrorException(dateType, e.getMessage());
            }
        }
        SDKUtil.LogD(TAG, result);
        return result;
    }

    public static String httpGetString(String url, String encode, boolean verifyFlag) {
        return httpUrlGetString(url, verifyFlag, Constants.DATATYPE_XML, 1);
    }

    public static String httpGetString(String url, String encode, boolean verifyFlag,
                                       String howToEncode) {
        return httpUrlGetString(url, verifyFlag, Constants.DATATYPE_XML, 1);
        /*
         * if ("1".equals(howToEncode)) {// EntityUtils.toString有时候不好使 result =
         * SDKUtil.InputStreamTOString(entity.getContent(), encode); } else {
         * result = EntityUtils.toString(entity, encode); }
         */
    }

    /**
     * 此接口由于目前默认没有重试那么跟原接口一致
     */
    public static String httpGetString(String url, String encode, boolean verifyFlag,
                                       int dateType) {
        return httpUrlGetString(url, verifyFlag, dateType, 1);
    }


    /**
     * 聚好用一个接口签名的参数与别人不一致
     */
    public static String httpGetString2(String url, String encode, boolean verifyFlag,
                                        int dateType) {
        return httpUrlGetString(url, verifyFlag, dateType, 2);
    }

    /**
     * 此接口由于目前默认没有重试那么跟原接口一致
     */
    public static String httpGetStringRetryOnce(String url, String encode, boolean verifyFlag) {
        return httpUrlGetString(url, verifyFlag, Constants.DATATYPE_XML, 1);
    }

    public static String httpGetString(String url, String encode) {
        return httpGetString(url, encode, true);
    }

    public static String httpGetString(String url, String encode, int dateType) {
        return httpGetString(url, encode, true, dateType);
    }


    /**
     * @param url            地址
     * @param verifyFlag     是否校验签名
     * @param dateType       json or xml
     * @param jsonVerifyType json格式的延签的方式
     * @return
     */
    public static String httpUrlPostString(String url, boolean verifyFlag,
                                           final Map<String, String> mapNative,
                                           int dateType, int jsonVerifyType) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            // mException =new VolleyError("do network on mainThread");
            return returnException(dateType, "do network on mainThread");
        }
        if (TextUtils.isEmpty(url)) {
            return returnException(dateType, "url is null");
        }
        String result = null;
        SDKUtil.LogD(TAG, "httpPostUrl=" + url);
        RequestFuture<String> requestFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(Method.POST, SDKUtil.toUTF_8(url),
                requestFuture,
                requestFuture) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept-Encoding", "gzip");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = mapNative;
                if (map != null) {
                    map.remove(Params.SIGN);
                    // 按照key做排序
                    StringBuilder content = new StringBuilder();
                    List<String> keys = new ArrayList<>(map.keySet());
                    Collections.sort(keys);
                    for (int i = 0; i < keys.size(); i++) {
                        String key = keys.get(i);
                        String value = map.get(key);
                        if (!TextUtils.isEmpty(value)) {
                            content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
                        }
                    }
                    String sign = null;
                    try {
                        sign = HiCloudKey.encypt(content.toString(), HiCloudKey.getPublicKey());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    map.put(Params.SIGN, sign);
                    content.append("&").append(Params.SIGN).append("=").append(sign);
                }
                return map;
            }
        };
        stringRequest.setShouldCache(false);
        requestQueue = CustomRequestQueue.getRequestQueue();
        if (requestQueue == null) {
            return null;
        }
        requestQueue.add(stringRequest);
        try {
            result = requestFuture.get();
            if (response304.equals(result)) {
                return response304;
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException:" + SDKUtil.getStackTrace(e));
            return returnException(dateType, "InterruptedException:" + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, SDKUtil.getStackTrace(e));
            if (e != null && e.getCause() != null && e.getCause() instanceof TimeoutError) {
                return returnException(dateType, "TimeoutError:" + e.getCause().getMessage());
            }
            if (e != null && e.getCause() != null && e.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) e.getCause();
                if (volleyError.networkResponse != null) {
                    Log.e(TAG,
                            "networkResponse.statusCode=" + volleyError.networkResponse.statusCode);
                    if (volleyError.networkResponse.statusCode == HttpStatus.SC_NOT_MODIFIED) {
                        return response304;
                    } else {
                        return returnException(dateType,
                                "networkResponse != null:"
                                        + volleyError.networkResponse.statusCode);
                    }
                } else {
                    return returnException(dateType,
                            "networkResponse == null:" + e.getMessage());
                }
            } else {
                return returnException(dateType, "ExecutionException:" + e.getMessage());
            }
        } catch (AssertionError error) {
            Log.e(TAG, error.getMessage());
            return returnException(dateType, "AssertionError:" + error.getMessage());
        }
        if (verifyFlag && !SDKUtil.isEmpty(result)) {
            try {
                if (Constants.DATATYPE_XML == dateType) {
                    result = HiCloudKey.verifySignature(result, publicKey);
                } else {// JSON 验签
                    if (jsonVerifyType == 2) {
                        result = HiCloudKey.verifySignatureJson2(result, publicKey);
                    } else {
                        result = HiCloudKey.verifySignatureJson(result, publicKey);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, SDKUtil.getStackTrace(e));
                return returnSignErrorException(dateType, e.getMessage());
            }
        }
        SDKUtil.LogD(TAG, result);
        return result;
    }

    public static String httpPostString(String url, String encode, Map<String, String> map,
                                        boolean verifyFlag) {

        return httpUrlPostString(url, verifyFlag, map, Constants.DATATYPE_XML, 1);
    }

    public static String httpPostStringRetryOnce(String url, String encode, Map<String, String> map,
                                                 boolean verifyFlag) {
        return httpUrlPostString(url, verifyFlag, map, Constants.DATATYPE_XML, 1);
    }

    public static String httpPostString(String url, String encode, Map<String, String> map,
                                        boolean verifyFlag,
                                        String howToEncode) {
        return httpUrlPostString(url, verifyFlag, map, Constants.DATATYPE_XML, 1);
        /*
         * if ("1".equals(howToEncode)) {// EntityUtils.toString有时候不好使 result =
         * SDKUtil.InputStreamTOString(entity.getContent(), encode); } else {
         * result = EntityUtils.toString(entity, encode); }
         */
    }

    public static String httpPostString(String url, String encode, Map<String, String> map,
                                        boolean verifyFlag,
                                        int dateType) {
        return httpUrlPostString(url, verifyFlag, map, dateType, 1);
    }

    public static String httpPostString2(String url, String encode, Map<String, String> map,
                                         boolean verifyFlag,
                                         int dateType) {
        return httpUrlPostString(url, verifyFlag, map, dateType, 2);
    }

    public static String httpPostString(String url, String encode, Map<String, String> map) {
        return httpPostString(url, encode, map, true);
    }

    public static String httpPostString(String url, String encode, Map<String, String> map,
                                        int dateType) {
        return httpPostString(url, encode, map, true, dateType);
    }

    private static String returnSignErrorException(int dateType, String exception) {
        String requestError = null;
        if (Constants.DATATYPE_XML == dateType) {
            requestError = HiCloudKey.signErrorXml.replace("signatureError",
                    SDKUtil.toUTF_8(exception));
        } else {
            requestError = HiCloudKey.signErrorXml.replace("signatureError",
                    SDKUtil.toUTF_8(exception));
        }
        SDKUtil.LogD(TAG, requestError);
        return requestError;
    }

    private static String returnException(int dateType, String exception) {
        String requestError = null;
        if (Constants.DATATYPE_XML == dateType) {
            requestError = requestErrorXml.replace(requestDefultErrorDesc,
                    SDKUtil.toUTF_8(exception));
        } else {
            requestError = requestErrorJson.replace(requestDefultErrorDesc,
                    SDKUtil.toUTF_8(exception));
        }
        SDKUtil.LogD(TAG, requestError);
        return requestError;
    }
}
