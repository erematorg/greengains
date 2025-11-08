package com.sun.jna.ptr;

import com.sun.jna.Pointer;

public class ByteByReference extends ByReference {
    public ByteByReference() {
        this((byte) 0);
    }

    public byte getValue() {
        return getPointer().getByte(0);
    }

    public void setValue(byte b3) {
        getPointer().setByte(0, b3);
    }

    public String toString() {
        return String.format("byte@0x%1$x=0x%2$x (%2$d)", new Object[]{Long.valueOf(Pointer.nativeValue(getPointer())), Byte.valueOf(getValue())});
    }

    public ByteByReference(byte b3) {
        super(1);
        setValue(b3);
    }
}
