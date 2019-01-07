
package com.hisense.smarthome.sdk.util;

public abstract class SizedNumber implements Bytable {
    /**
     * change the bytes to c standard order. low byte first
     * 
     * @return
     */
    // @Override
    // public abstract byte[] toBytes();
    // @Override
    // public abstract void fromBytes(byte[]bs);
    public abstract void fromBytes(byte[] bs, int from);

    public abstract long getValue();

    public abstract void setValue(long value);

    // @Override
    // public abstract int sizeOf();
    /**
     * type maps from one type to another
     * 
     * @param num
     */
    public void setValue(SizedNumber num) {
        this.setValue(num.getValue());
    }

    public String toString() {
        return String.valueOf(this.getValue());
    }

    public byte[] htonl() {
        byte[] bs = this.toBytes();
        int len = bs.length;
        byte[] ret = new byte[len];
        for (int i = 0; i < len; i++) {
            ret[i] = bs[len - i - 1];
        }
        return ret;
    }

}
