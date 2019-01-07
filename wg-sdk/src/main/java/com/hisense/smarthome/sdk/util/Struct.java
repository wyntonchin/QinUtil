
package com.hisense.smarthome.sdk.util;

public abstract class Struct {
    /**
     * init the message . actually work like malloc in c,memory allocation
     */
    protected abstract void init();

    /**
     * copy data, char viewed as U8
     * 
     * @param src
     * @param dst
     */
    public static void arrayCopy(char[] src, U8[] dst) {
        int limit = Math.min(src.length, dst.length);
        arrayCopy(src, dst, limit);
    }

    /**
     * copy data, char viewed as U8
     * 
     * @param src
     * @param dst
     * @param limit the number of chars to copy
     */
    public static void arrayCopy(char[] src, U8[] dst, int limit) {
        for (int i = 0; i < limit; i++) {
            dst[i].setValue(src[i]);
        }
    }

    /**
     * copy data, byte viewed as U8
     * 
     * @param src
     * @param dst
     */
    public static void arrayCopy(byte[] src, U8[] dst) {
        int limit = Math.min(src.length, dst.length);
        arrayCopy(src, dst, limit);
    }

    /**
     * copy data, byte viewed as U8
     * 
     * @param src
     * @param dst
     * @param limit the number of chars to copy
     */
    public static void arrayCopy(byte[] src, U8[] dst, int limit) {
        for (int i = 0; i < limit; i++) {
            dst[i].setValue(src[i]);
        }
    }

    /**
     * copy char array
     * 
     * @param src
     * @param dst
     */
    public static void arrayCopy(char[] src, char[] dst) {
        int limit = Math.min(src.length, dst.length);
        arrayCopy(src, dst, limit);
    }

    /**
     * copy char
     * 
     * @param scr
     * @param dst
     * @param limit the number of chars to copy
     */
    public static void arrayCopy(char[] scr, char[] dst, int limit) {
        for (int i = 0; i < limit; i++) {
            dst[i] = scr[i];
        }
    }

    /**
     * copy data, char viewed as U8
     * 
     * @param src
     * @param dst
     */
    public static void arrayCopy(U8[] src, byte[] dst) {
        int limit = Math.min(src.length, dst.length);
        arrayCopy(src, dst, limit);
    }

    /**
     * copy data, U8 viewed as byte
     * 
     * @param src
     * @param dst
     * @param limit the number of chars to copy
     */
    public static void arrayCopy(U8[] src, byte[] dst, int limit) {
        for (int i = 0; i < limit; i++) {
            dst[i] = src[i].toBytes()[0];
        }
    }

    /**
     * copy data, char viewed as U8
     * 
     * @param src
     * @param dst
     */
    public static void arrayCopy(U8[] src, char[] dst) {
        int limit = Math.min(src.length, dst.length);
        arrayCopy(src, dst, limit);
    }

    /**
     * copy data, char viewed as U8
     * 
     * @param src
     * @param dst
     * @param limit the number of chars to copy
     */
    public static void arrayCopy(U8[] src, char[] dst, int limit) {
        for (int i = 0; i < limit; i++) {
            dst[i] = (char) (src[i].getValue());
        }
    }

    /**
     * copy data, char viewed as U8
     * 
     * @param src
     * @param dst
     */
    public static void arraySwitch(byte[] src, U8[] dst) {
        int limit = Math.min(src.length, dst.length);
        arraySwitch(src, dst, limit);
    }

    /**
     * copy data, U8 viewed as byte
     * 
     * @param src
     * @param dst
     * @param limit the number of chars to copy
     */
    public static void arraySwitch(byte[] src, U8[] dst, int limit) {
        for (int i = 0; i < limit; i++) {
            dst[i] = new U8(src[i]);
        }
        for (int i = limit; i < dst.length; i++) {
            dst[i] = new U8();
        }
    }

    /**
     * copy bytes view char[] as byte[]. if the index exceed cs.length(),we will
     * stop copying
     * 
     * @param cs
     * @param iPos
     * @param bs
     * @param dPos
     * @param len
     */
    public static void arrayCopy(char[] cs, int iPos, byte[] bs, int dPos, int len) {
        for (int i = 0; i < len; i++) {
            if (iPos + i >= cs.length)
                break;
            bs[dPos + i] = (byte) cs[iPos + i];
        }
    }

    /**
     * copy bytes view char[] as byte[] if the index exceed cs.length(),we will
     * stop copying
     * 
     * @param cs
     * @param iPos
     * @param bs
     * @param dPos
     * @param len
     */
    public static void arrayCopy(byte[] cs, int iPos, char[] bs, int dPos, int len) {
        for (int i = 0; i < len; i++) {
            if (iPos + i >= cs.length)
                break;
            bs[dPos + i] = (char) cs[iPos + i];
        }
    }

    /**
     * like System.arrayCopy(...) if the index exceed cs.length(),we will stop
     * copyeing
     * 
     * @param cs
     * @param iPos
     * @param bs
     * @param dPos
     * @param len
     */
    public static void arrayCopy(byte[] cs, int iPos, byte[] bs, int dPos, int len) {
        for (int i = 0; i < len; i++) {
            if (iPos + i >= cs.length)
                break;
            bs[dPos + i] = cs[iPos + i];
        }
    }

    /**
     * copy U8s to byte array
     * 
     * @param cs
     * @param iPos
     * @param bs
     * @param dPos
     * @param len
     */
    public static void arrayCopy(U8[] cs, int iPos, byte[] bs, int dPos, int len) {
        for (int i = 0; i < len; i++) {
            if (iPos + i >= cs.length)
                break;
            bs[dPos + i] = (byte) cs[iPos + i].getValue();
        }
    }

    // public char[]U32Array2CharArray(U32[]u32s){
    // char[]cs=new char[u32s.length];
    // for(int i=0;i<u32s.length;i++){
    // cs[i]=(char) u32s[i].getValue();
    // }
    // return cs;
    // }
    /**
     * copy byte array to a U8 array
     */
    public static void arrayCopy(byte[] cs, int iPos, U8[] bs, int dPos, int len) {
        for (int i = 0; i < len; i++) {
            if (iPos + i >= cs.length)
                break;
            bs[dPos + i].setValue(cs[iPos + i]);// .getValue();
        }
    }

}
