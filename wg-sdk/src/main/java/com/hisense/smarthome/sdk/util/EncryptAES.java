
package com.hisense.smarthome.sdk.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES通用加解密. CBC mode with PKCS#1 v1.5 padding.
 * 
 * @author wang
 */
public class EncryptAES {
    protected static final String D_A_K = "D5B6D8584F94B432";
    protected static final String D_A_V = "205681D89D731A8F";
    private Cipher enc;
    private Cipher dec;
    private SecretKeySpec keySpec;
    private IvParameterSpec ivSpec;

    /**
     * 构造函数.
     */
    public EncryptAES() {
    }

    /**
     * init the AES key. the key must be 128, 192, or 256 bits.
     * 
     * @param key the AES key.
     * @param keyoff the AES key offset.
     * @param keylen the AES key length, the key length must be 16 bytes because
     *            SunJCE only support 16 bytes key.
     * @param iv the IV for CBC, the length of iv must be 16 bytes.
     * @param ivoff the iv offset.
     */
    public void init(byte[] key, int keyoff, int keylen, byte[] iv, int ivoff) {
        keySpec = new SecretKeySpec(key, keyoff, keylen, "AES");
        ivSpec = new IvParameterSpec(iv, ivoff, 16);
    }

    /**
     * init the AES key. the key must be 16 bytes, because SunJCE only support
     * 16 bytes key..
     * 
     * @param key the AES key.
     * @param iv the iv for CBC, iv must be 16 bytes length.
     */
    public void init(byte[] key, byte[] iv) {
        keySpec = new SecretKeySpec(key, "AES");
        ivSpec = new IvParameterSpec(iv);
    }

    /**
     * get the maximal cipher data length after encrypted.
     * 
     * @param len the plain data length.
     * @return the cipher data length.
     */
    public int getCipherLen(int len) {
        // for PKCS#1 v1.5 padding
        // max padding BLOCK_SIZE=16.
        int pad = len % 16;
        if (0 == pad) {
            return len + 16;
        }
        return len - pad + 16;
    }

    /**
     * encrypt the input data to output data. the input data length must be the
     * times of 16 bytes. and the output data length is equals to the input
     * data.
     * 
     * @param indata the input data.
     * @param inoff the input data offset.
     * @param inlen the input data length.
     * @param outdata the output data.
     * @param outoff the output data offset.
     */
    protected void encrypt(byte[] indata, int inoff, int inlen, byte[] outdata,
            int outoff) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, ShortBufferException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        initEncryptor();
        enc.doFinal(indata, inoff, inlen, outdata, outoff);
    }

    /**
     * encrypt the input data to output data.
     * 
     * @param indata the input data.
     * @param inoff the input data offset.
     * @param inlen the input data length.
     * @return the output encrypted data.
     */
    protected byte[] encrypt(byte[] indata, int inoff, int inlen)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, ShortBufferException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        initEncryptor();
        return enc.doFinal(indata, inoff, inlen);
    }

    /**
     * encrypt the input data to output data.
     * 
     * @param indata the input data.
     * @return the output data.
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] encrypt(byte[] indata) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException {
        initEncryptor();
        return enc.doFinal(indata);
    }

    /**
     * the maximal plain data length after decrypted.
     * 
     * @param len the cipher data length that will be decrypted.
     * @return the maximal plain data length.
     */
    public int getPlainLen(int len) {
        // for PKCS#1 v1.5 padding
        // len always be times of BLOCK_SIZE=16.
        return len;
    }

    /**
     * decrypt the input data to output data.
     * 
     * @param indata the input data.
     * @param inoff the input data offset.
     * @param inlen the input data length.
     * @param outdata the output data.
     * @param outoff the output data offset.
     */
    protected void decrypt(byte[] indata, int inoff, int inlen, byte[] outdata,
            int outoff) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, ShortBufferException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        initDecryptor();
        dec.doFinal(indata, inoff, inlen, outdata, outoff);
    }

    /**
     * decrypt the input data to output data.
     * 
     * @param indata the input data.
     * @param inoff the input data offset.
     * @param inlen the input data length.
     * @return the output decrypted data.
     */
    protected byte[] decrypt(byte[] indata, int inoff, int inlen)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, ShortBufferException,
            InvalidAlgorithmParameterException {
        initDecryptor();
        return dec.doFinal(indata, inoff, inlen);
    }

    /**
     * decrypt the input data to output data.
     * 
     * @param indata the input cipher data.
     * @return the output plain data.
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    protected byte[] decrypt(byte[] indata) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException,
            BadPaddingException {
        initDecryptor();
        return dec.doFinal(indata);
    }

    private void initEncryptor() throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        if (null == enc) {
            enc = Cipher.getInstance("AES/CBC/PKCS5Padding");
            enc.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        }
    }

    private void initDecryptor() throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        if (null == dec) {
            dec = Cipher.getInstance("AES/CBC/PKCS5Padding");
            dec.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, ShortBufferException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException {
        System.out.println("=========AES/CBC/PKCS5Padding=========");
        // key
        byte[] key = "D5B6D8584F94B432".getBytes("UTF-8");
        dump("key", key);
        // iv
        byte[] iv = "205681D89D731A8F".getBytes("UTF-8");
        dump("iv", iv);

        byte[] indata = "asdfa57945ojfasdfg**)9af".getBytes("UTF-8");
        dump("indata", indata);

        EncryptAES aes = new EncryptAES();
        aes.init(key, iv);
        byte[] outdata = aes.encrypt(indata);
        dump("outdata", outdata);

        String outString = ByteUtils.byteArr2Str(outdata, 16);
        outString = outString.toUpperCase();
        System.out.println(outString);
        byte[] aa = ByteUtils.string2ByteArray(outString);
        byte[] indata1 = aes.decrypt(aa);
        dump("indata1", indata1);
        System.out.println(new String(indata1));
    }

    public static void dump(String label, byte[] data) {
        String hex_str = "";
        try {
            hex_str = ByteUtils.byteArr2Str(data, 16);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(label + "=" + hex_str.toUpperCase());
    }
}
