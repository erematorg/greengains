package com.sun.jna.ptr;

import com.sun.jna.Pointer;

public class IntByReference extends ByReference {
    public IntByReference() {
        this(0);
    }

    public int getValue() {
        return getPointer().getInt(0);
    }

    public void setValue(int i3) {
        getPointer().setInt(0, i3);
    }

    public String toString() {
        return String.format("int@0x%1$x=0x%2$x (%2$d)", new Object[]{Long.valueOf(Pointer.nativeValue(getPointer())), Integer.valueOf(getValue())});
    }

    public IntByReference(int i3) {
        super(4);
        setValue(i3);
    }
}
