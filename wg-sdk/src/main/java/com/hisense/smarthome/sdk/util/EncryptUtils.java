
package com.hisense.smarthome.sdk.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类.
 * 
 * @author wang
 */
public class EncryptUtils {
    private static final String Algorithm_DESEDE = "DESede";
    public static char[] hexChar = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * AES加密.
     * 
     * @param plainText
     * @return
     */
    public static String AESEncrypt(String plainText) {
        try {
            EncryptAES aes = new EncryptAES();
            byte[] k = EncryptAES.D_A_K.getBytes("UTF-8");
            byte[] v = EncryptAES.D_A_V.getBytes("UTF-8");
            byte[] inData = plainText.getBytes("UTF-8");
            aes.init(k, v);
            byte[] outDate = aes.encrypt(inData);
            String outString = ByteUtils.byteArr2Str(outDate, 16);
            return outString.toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * AES加密.
     * 
     * @param plainText
     * @return
     */
    public static String AESEncrypt(String plainText, String D_A_K, String D_A_V) {
        try {
            EncryptAES aes = new EncryptAES();
            byte[] k = D_A_K.getBytes("UTF-8");
            byte[] v = D_A_V.getBytes("UTF-8");
            byte[] inData = plainText.getBytes("UTF-8");
            aes.init(k, v);
            byte[] outDate = aes.encrypt(inData);
            String outString = ByteUtils.byteArr2Str(outDate, 16);
            return outString.toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * AES解密.
     * 
     * @param plainText
     * @return
     */
    public static String AESDecrypt(String plainText, String D_A_K, String D_A_V) {
        try {
            EncryptAES aes = new EncryptAES();
            byte[] k = D_A_K.getBytes("UTF-8");
            byte[] v = D_A_V.getBytes("UTF-8");
            byte[] inData = ByteUtils.string2ByteArray(plainText);
            aes.init(k, v);
            byte[] outDate = aes.decrypt(inData);
            String outString = new String(outDate, "UTF-8");
            return outString;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * AES解密.
     * 
     * @param plainText
     * @return
     */
    public static String AESDecrypt(String plainText) {
        try {
            EncryptAES aes = new EncryptAES();
            byte[] k = EncryptAES.D_A_K.getBytes("UTF-8");
            byte[] v = EncryptAES.D_A_V.getBytes("UTF-8");
            byte[] inData = ByteUtils.string2ByteArray(plainText);
            aes.init(k, v);
            byte[] outDate = aes.decrypt(inData);
            String outString = new String(outDate, "UTF-8");
            return outString;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 哈希散列加密.
     * 
     * @param plainText 加密的明文.
     * @param algorithm 加密方式(MD5,SHA-1).
     * @return
     */
    public static String hashEncrypt(String plainText, String algorithm) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(plainText.getBytes());
        byte[] b = md.digest();
        StringBuilder output = new StringBuilder(32);
        for (int i = 0; i < b.length; i++) {
            String temp = Integer.toHexString(b[i] & 0xff);
            if (temp.length() < 2) {
                output.append("0");
            }
            output.append(temp);
        }
        return output.toString().toUpperCase();
    }

    /**
     * 哈希散列多次加密.
     * 
     * @param plainText 加密的明文.
     * @param algorithm 加密方式(MD5,SHA-1).
     * @param times 加密次数
     * @return
     */
    public static String hashEncryptMulti(String plainText, String algorithm,
            int times) {
        for (int i = 0; i < times; i++) {
            plainText = hashEncrypt(plainText, algorithm);
        }
        return plainText;
    }

    /**
     * 3DES加密.
     * 
     * @param keybyte
     * @param src
     * @return
     */
    public static byte[] desedeEncrypt(byte[] keybyte, byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm_DESEDE);
            Cipher c1 = Cipher.getInstance(Algorithm_DESEDE);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static String getHashOfFile(String fileName, String hashType) {
        InputStream fis;
        try {
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            MessageDigest md5 = MessageDigest.getInstance(hashType);
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            byte[] outDate = md5.digest();
            return toHexString(outDate);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }



    /**
     * @param args
     */
    public static void main(String[] args) {
        // String md5Code = hashEncrypt("要加密的字符串", "MD5");
        // System.out.println("MD5 加密后的字符串:" + md5Code);
        // String sha1Code = hashEncrypt("要加密的字符串", "SHA-1");
        // System.out.println("SHA1加密后的字符串:" + sha1Code);
        // String sha1Code1 = hashEncryptMulti("820371862861156", "SHA-1", 1);
        // System.out.println("SHA1加密后的字符串:" + sha1Code1);
        // String sha1Code1 = AESEncrypt("00112233445566778899AABBCCDDEEFF");
        // System.out.println("AES加密后的字符串:" + sha1Code1);
        // String a = AESDecrypt("AD8999CE9FBB8FB1BACA53AD252DC8B4");
        // System.out.println("AES解密后的字符串:" + a);
        try {
            System.out.println(getHashOfFile("D:\\应用商城.rar", "md5"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
