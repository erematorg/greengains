package com.sun.jna.ptr;

import com.sun.jna.Pointer;

public class ShortByReference extends ByReference {
    public ShortByReference() {
        this(0);
    }

    public short getValue() {
        return getPointer().getShort(0);
    }

    public void setValue(short s3) {
        getPointer().setShort(0, s3);
    }

    public String toString() {
        return String.format("short@0x%1$x=0x%2$x (%2$d)", new Object[]{Long.valueOf(Pointer.nativeValue(getPointer())), Short.valueOf(getValue())});
    }

    public ShortByReference(short s3) {
        super(2);
        setValue(s3);
    }
}
