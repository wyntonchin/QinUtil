
package com.hisense.smarthome.sdk.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.hisense.smarthome.sdk.bean.global.HiSDKInfo;
import com.hisense.smarthome.sdk.http.HiCloudKey;
import com.hisense.smarthome.sdk.util.Constants;
import com.hisense.smarthome.sdk.util.Params;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public abstract class HiCloudService {
    private HiSDKInfo hiSDKInfo;
    private StringBuilder defaultParameter = new StringBuilder();
    private String encode;
    public int rawOffset = TimeZone.getDefault().getRawOffset()/1000;
    protected Context context;

    protected HiCloudService(HiSDKInfo info) {
        this.hiSDKInfo = info;
        init();
    }

    protected void init() {
    	
    }

    public void refresh(HiSDKInfo info) {
        this.hiSDKInfo = info;
        init();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    protected abstract String assembleUrl(String action);

    protected abstract String assembleUrl(String action, Map<String, String> map);

    /**
     * 签名相关：由于签名信息中携带特殊字符，需按以下规则进行decode </br>
     * 
     * @param xmlTag
     * @return
     */
    protected String decodeXMLTag(String xmlTag) {
        String decode = null;
        try {
            decode = xmlTag.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">")
                    .replace("&apos;", "\'").replace("&quot;", "\"").replace("+", " ")
                    .replace("%26", "&");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decode;
    }

    public HiSDKInfo getHiSDKInfo() {
        return hiSDKInfo;
    }

    public void setHiSDKInfo(HiSDKInfo hiSDKInfo) {
        this.hiSDKInfo = hiSDKInfo;
    }

    public StringBuilder getDefaultParameter() {
        return defaultParameter;
    }

    public void setDefaultParameter(StringBuilder defaultParameter) {
        this.defaultParameter = defaultParameter;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    /**
     * 后处理方法，一般用于API从Server端获得数据后，对数据进行处理后返回，默认是不做任何处理
     * 
     * @param data 待处理的数据
     * @param dataType 数据类型 0: xml，1：JSON
     * @return 处理后的数据
     * @since 5.1
     */
    protected String postExecute(String data, int dataType) {
        return data;
    }

    /**
     * 对数据进行解压缩
     * 
     * @param data 包含有压缩数据的数据
     * @param dataType 数据类型 0: xml，1：JSON
     * @return 解压缩以后的数据
     */
    protected String deComData(String data, int dataType) {
        if (data == null || data.equals("")) {
            return data;
        }
        // 对压缩数据先进行Base64解码
        byte[] deCodedData = null;
        try {
            deCodedData = Base64.decode(data, Base64.DEFAULT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 开始对解码后的压缩数据进行GZip解压缩
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream outputStream = null;
        String deCompressedData = "";
        try {
            gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(deCodedData));
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int len = 0;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            byte[] out = outputStream.toByteArray();
            // 获得解压缩的文本数据
            deCompressedData = new String(out, Constants.ENCODE);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e(Constants.LOGTAG, "error in decompress data : " + ex.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                    outputStream = null;
                }
                if (gzipInputStream != null) {
                    gzipInputStream.close();
                    gzipInputStream = null;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e(Constants.LOGTAG, "error in decompress data : " + ex.getMessage());
            }

        }
        return deCompressedData;

    }

    /**
     * 对数据进行解压缩
     * 
     * @param data 包含有压缩数据的数据
     * @param dataType 数据类型 0: xml，1：JSON
     * @return 解压缩以后的数据
     */
    protected String deCompressData(String data, int dataType) {
        if (data == null || data.equals("")) {
            return data;
        }
        // 获取需要解压缩处理的数据
        String compressedData = getCompressedData(data, Constants.COMPRESSCONTENTTAG, dataType);
        if (compressedData == null || compressedData.equals("")) {
            // 如果没有需要解压缩处理的数据，直接返回原始xml
            return data;
        }
        // 替换掉原始xml中的压缩数据
        String tempResult = data.replace(compressedData, "");
        // 对压缩数据先进行Base64解码
        byte[] deCodedData = Base64.decode(compressedData, Base64.DEFAULT);
        // 开始对解码后的压缩数据进行GZip解压缩
        GZIPInputStream gzipInputStream = null;
        ByteArrayOutputStream outputStream = null;
        String deCompressedData = "";
        try {
            gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(deCodedData));
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int len = 0;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            byte[] out = outputStream.toByteArray();
            // 获得解压缩的文本数据
            deCompressedData = new String(out, Constants.ENCODE);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e(Constants.LOGTAG, "error in decompress data : " + ex.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                    outputStream = null;
                }
                if (gzipInputStream != null) {
                    gzipInputStream.close();
                    gzipInputStream = null;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e(Constants.LOGTAG, "error in decompress data : " + ex.getMessage());
            }

        }
        String result = tempResult.replace(
                "<" + Constants.COMPRESSCONTENTTAG + "></" + Constants.COMPRESSCONTENTTAG + ">",
                deCompressedData);
        return result;

    }

    /**
     * 从xml文本中获得需要解压缩处理的数据
     * 
     * @param data 包含有压缩数据的数据
     * @param tag 压缩数据所在的数据节点的名称
     * @param dataType 数据类型 0: xml，1：JSON
     * @return 需要解压缩的数据
     * @since 5.1
     */
    private String getCompressedData(String data, String tag, int dataType) {
        if (data == null || data.equals("")) {
            return data;
        }
        String originData = data;
        String result = "";
        String matchString = "<" + tag + ">(.*?)</" + tag + ">";
        Pattern pat = Pattern.compile(matchString);
        Matcher m = pat.matcher(originData);
        while (m.find()) {
            result = m.group(1);
        }

        return result;
    }

    public static String getSignData(Map<String, String> params) {
        StringBuffer content = new StringBuffer();
        // 按照key做排序
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            if (!TextUtils.isEmpty(value)) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            }
        }
        return content.toString();
    }

    public static String getSignUrl(Map<String, String> map, StringBuilder builder) {
        if (map != null) {
            map.remove(Params.SIGN);
            try {
                for (String key : map.keySet()) {
                    String value = map.get(key);
                    value = value == null ? ""
                            : java.net.URLEncoder.encode(value, Constants.ENCODE);
                    builder.append(key).append("=").append(value).append("&");
                }
                String signContent = getSignData(map);
                String sign = null;
                try {
                    sign = HiCloudKey.encypt(signContent, HiCloudKey.getPublicKey());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                builder.append(Params.SIGN).append("=")
                        .append(java.net.URLEncoder.encode(sign, Constants.ENCODE));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
