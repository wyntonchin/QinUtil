
package com.hisense.smarthome.sdk.util;

/**
 * bytable means a class can convert from/to byte array, which is usually used
 * to send to network
 * 
 * @author liying1
 */

public interface Bytable {
    /**
     * get the bytes to present this class
     * 
     * @return byte[]
     * @throws Exception
     */
    public byte[] toBytes();

    /**
     * reInit this class using bytes. This method should reset the members
     * 
     * @param bss
     * @throws Exception
     */
    public void fromBytes(byte[] bss);

    /**
     * byte size
     * 
     * @return int
     * @throws Exception
     */
    public int sizeOf();
}
