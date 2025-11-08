package com.sun.jna.ptr;

import com.sun.jna.Pointer;

public class LongByReference extends ByReference {
    public LongByReference() {
        this(0);
    }

    public long getValue() {
        return getPointer().getLong(0);
    }

    public void setValue(long j2) {
        getPointer().setLong(0, j2);
    }

    public String toString() {
        return String.format("long@0x%1$x=0x%2$x (%2$d)", new Object[]{Long.valueOf(Pointer.nativeValue(getPointer())), Long.valueOf(getValue())});
    }

    public LongByReference(long j2) {
        super(8);
        setValue(j2);
    }
}
