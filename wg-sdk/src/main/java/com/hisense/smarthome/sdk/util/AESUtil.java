
package com.hisense.smarthome.sdk.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public String Encrypt(String content, String key, String ivParameter) {
        if (SDKUtil.isEmpty(content)) {
            return "";
        }
        try {
            SecretKeySpec keyBytes = new SecretKeySpec(key.getBytes(Constants.ENCODE), "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes(Constants.ENCODE));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keyBytes, iv);
            byte[] result = cipher.doFinal(content.getBytes(Constants.ENCODE));

            return SDKUtil.byte2string(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
