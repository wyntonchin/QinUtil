
package com.hisense.smarthome.sdk.util;

/**
 * Byte相关的工具类.
 * 
 * @author wang
 */
public class ByteUtils {
    /**
     * 将byte数组转换为二/十/十六进制字符串.
     * 
     * @param arrB
     * @return
     * @throws Exception
     */
    public static String byteArr2Str(byte[] arrB, int decimal) {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, decimal));
        }
        return sb.toString();
    }

    /**
     * byte数组异或运算.
     * 
     * @param a
     * @param b
     * @return
     */
    public static byte[] byteArrayXor(byte[] ba1, byte[] ba2) {
        int ba1Length = ba1.length;
        int ba2Length = ba2.length;
        int c = ba1Length;
        int d = ba1Length;
        if (ba1Length < ba2Length) {
            c = ba2Length;
        } else {
            d = ba2Length;
        }
        byte[] ba3 = new byte[c];
        if (c == ba1Length) {
            System.arraycopy(ba1, 0, ba3, 0, ba1Length);
        } else {
            System.arraycopy(ba2, 0, ba3, 0, ba2Length);
        }
        for (int i = 0; i < d; i++) {
            ba3[i] = (byte) (ba1[i] ^ ba2[i]);
        }
        return ba3;
    }

    /**
     * 将数字字符串每隔两位转换为十六进制数组.
     * 
     * @param src
     * @return
     */
    public static byte[] string2ByteArray(String src) {
        int length = src.length();
        int a = length / 2;
        int b = length % 2;
        if (b != 0) {
            src = "0" + src;
            a++;
        }
        byte[] bt = new byte[a];
        for (int i = 0; i < a; i++) {
            String ss1 = src.substring(2 * i, 2 * (i + 1) - 1);
            String ss2 = src.substring(2 * (i + 1) - 1, 2 * (i + 1));
            bt[i] = uniteBytes(ss1, ss2);
        }
        return bt;
    }

    private static byte uniteBytes(String src0, String src1) {
        byte _b0 = Byte.decode("0x" + src0).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
