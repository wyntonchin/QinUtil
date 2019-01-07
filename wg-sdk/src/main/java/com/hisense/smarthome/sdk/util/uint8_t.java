
package com.hisense.smarthome.sdk.util;

public class uint8_t extends SizedNumber {
    private byte value;

    public uint8_t(long value) {
        this.setValue(value);
    }

    public static int size() {
        return 1;
    }

    @Override
    public long getValue() {
        return value & 0xFF;
    }

    public byte byteValue() {
        return (byte) (value & 0xFF);
    }

    @Override
    public void setValue(long value) {
        this.value = (byte) value;
    }

    public byte[] toBytes() {
        return new byte[] {
                this.byteValue()
        };
    }

    @Override
    public void fromBytes(byte[] bs, int from) {
        value = (byte) (bs[from] & 0xFF);
    }

    public int sizeOf() {
        return size();
    }

    public void fromBytes(byte[] bss) {
        fromBytes(bss, 0);
    }
}
