
package com.hisense.smarthome.sdk.http;

import android.text.TextUtils;
import android.util.Base64;

import com.hisense.smarthome.sdk.util.SDKUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

public class HiCloudKey {
    private static PublicKey publicKey = null;
    private static PublicKey publicKey_edu = null;
    private static PrivateKey privateKey = null;
    private final static String publicKeyString = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK26F0+yIWMxpW4WW7vsrwLl8kp8isCarBGv54xOK468ZD6FbOMZAOSj8JBr0IpUzv5w+hURR6W4oINsI4o5CEMCAwEAAQ==";
    private final static String publicKeyString_edu =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC84rAAUNyDJsGC4fP81oDLNnl0\n" +
                    "AgKEGCc0YNj5b/F4yh/yFKDGpJggSiwRJaU04FvN99lb+J3LljqnjsCE+V3/s9af\n" +
                    "6kqvDFm3rhNSxvtrzPkmtU6Vulvbw8VFkAyyZ7IFUTtNyePU4KMRWqR82nMuhr5b\n" +
                    "isU0Hgx0yWqBd6/0kQIDAQAB";
    private final static String privateKeyString = "";
    public final static String signErrorXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>900001</errorCode><errorDesc>signatureError</errorDesc><error_code>900001</error_code><error_name>signatureError</error_name></response>";
    public final static String signErrorJson = "{\"response\":{\"resultCode\":1,\"errorCode\":\"900001\",\"errorDesc\":\"signatureError\",\"resultcode\":1,\"errorcode\":\"900001\",\"errordesc\":\"signatureError\"}}";

    private static PublicKey getPublic() throws Exception {
        byte[] keyBytes = Base64.decode(publicKeyString, Base64.DEFAULT);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey mPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
        return mPublicKey;
    }
    private static PublicKey getPublic_edu() throws Exception {
        byte[] keyBytes = Base64.decode(publicKeyString_edu, Base64.DEFAULT);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey mPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
        return mPublicKey;
    }
    private static PrivateKey getPrivate() throws Exception {
        byte[] keyBytes = Base64.decode(privateKeyString, Base64.DEFAULT);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey mPrivateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
        return mPrivateKey;
    }

    public static PublicKey getPublicKey() {
        if (publicKey == null) {
            synchronized (HiCloudKey.class) {
                if (publicKey == null) {
                    try {
                        publicKey = getPublic();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return publicKey;
    }
    public static PublicKey getPublicKey_edu() {
        if (publicKey_edu == null) {
            synchronized (HiCloudKey.class) {
                if (publicKey_edu == null) {
                    try {
                        publicKey_edu = getPublic_edu();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return publicKey_edu;
    }
    public static PrivateKey getPrivateKey() {
        if (privateKey == null) {
            synchronized (HiCloudKey.class) {
                if (privateKey == null) {
                    try {
                        privateKey = getPrivate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return privateKey;
    }

    public static String decypt(String str, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decBytes = cipher.doFinal(Base64.decode(str, Base64.DEFAULT));
        return getString(decBytes);
    }

    public static String encypt(String str, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        byte[] MD5Bytes = md.digest(str.getBytes());

        byte[] enBytes = cipher.doFinal(MD5Bytes);
        return Base64.encodeToString(enBytes, Base64.NO_WRAP);
    }

    public static String getString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    public static boolean verifySignature(final PublicKey mPublicKey, final String xmlString,
            final String signatureServer, final String signature) throws Exception {
        if (mPublicKey != null) {
            String md5StringXml = xmlString.replace(signatureServer, "");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            String md5Value = getString(md5.digest(md5StringXml.getBytes()));
            if (decypt(signature, mPublicKey).equals(md5Value)) {
                return true;
            }
        }
        return false;
    }

    public static String verifySignature(final String xml, final PublicKey mPublicKey) {
        String result = xml;
        String signature = null;
        String signatureServer = null;
        String matchString = "<signatureServer>(.*?)</signatureServer>";
        Pattern pat = Pattern.compile(matchString);
        Matcher m = pat.matcher(xml);
        while (m.find()) {
            signatureServer = m.group();
            signature = m.group(1);
        }

        if (signature != null) {
            try {
                if (verifySignature(mPublicKey, xml, signatureServer, signature))
                    result = xml.replace(signature, "0");
                else
                    // result = xml.replace(signature, "1");
                    result = signErrorXml;
            } catch (Exception e) {
                // result = xml.replace(signature, "1");
                result = signErrorXml;
                e.printStackTrace();
            }
        } else {
            SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + xml);
            result = signErrorXml;
        }

        return result;
    }

    public static String verifySignatureJson(final String json, final PublicKey mPublicKey) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            String signatureResult = jsonObject.optString("signatureServer");
            if (!TextUtils.isEmpty(signatureResult)) {
                String matchString = ",(\\s*)\"signatureServer\":(\\s*)\"(.*?)\"";
                Pattern pattern = Pattern.compile(matchString);
                Matcher matcher = pattern.matcher(json);
                String responseString = matcher.replaceAll("");
                if (mPublicKey != null) {
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    md5.reset();
                    String md5Value = getString(md5.digest(responseString.getBytes()));
                    if (decypt(signatureResult, mPublicKey).equals(md5Value)) {
                        return responseString;
                    } else {
                        return signErrorJson;
                    }
                } else {
                    return signErrorJson;
                }

            } else {
                SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + json);
                return signErrorJson;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return signErrorJson;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return signErrorJson;
        } catch (Exception e) {
            e.printStackTrace();
            return signErrorJson;
        }
    }

    public static String verifySignatureJson2(final String json, final PublicKey mPublicKey) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            String signatureResult = jsonObject.optString("signature");
            if (!TextUtils.isEmpty(signatureResult)) {
                String matchString = "(\\s*)\"signature\":(\\s*)\"(.*?)\",";
                Pattern pattern = Pattern.compile(matchString);
                Matcher matcher = pattern.matcher(json);
                String responseString = matcher.replaceAll("");
                if (mPublicKey != null) {
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    md5.reset();
                    String md5Value = getString(md5.digest(responseString.getBytes()));
                    if (decypt(signatureResult, mPublicKey).equals(md5Value)) {
                        return responseString;
                    } else {
                        return signErrorJson;
                    }
                } else {
                    return signErrorJson;
                }

            } else {
                SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + json);
                return signErrorJson;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return signErrorJson;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return signErrorJson;
        } catch (Exception e) {
            e.printStackTrace();
            return signErrorJson;
        }
    }
    public static String verifySignatureJson_edu(final String json, final PublicKey mPublicKey) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            String signatureResult = jsonObject.optString("signature");
            if (!TextUtils.isEmpty(signatureResult)) {
                String matchString = ",(\\s*)\"signature\":(\\s*)\"(.*?)\"";
                Pattern pattern = Pattern.compile(matchString);
                Matcher matcher = pattern.matcher(json);
                String responseString = matcher.replaceAll("");
                if (mPublicKey != null) {
                    MessageDigest md5 = MessageDigest.getInstance("MD5");
                    md5.reset();
                    String md5Value = getString(md5.digest(responseString.getBytes()));
                    if (decypt(signatureResult, mPublicKey).equals(md5Value)) {
                        return responseString;
                    } else {
                        return signErrorJson;
                    }
                } else {
                    return signErrorJson;
                }

            } else {
                SDKUtil.LogD("HttpHandler", "signatureServer=null==>" + json);
                return signErrorJson;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return signErrorJson;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return signErrorJson;
        } catch (Exception e) {
            e.printStackTrace();
            return signErrorJson;
        }
    }
}
