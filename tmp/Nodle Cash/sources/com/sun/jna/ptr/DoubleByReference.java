package com.sun.jna.ptr;

import com.sun.jna.Pointer;

public class DoubleByReference extends ByReference {
    public DoubleByReference() {
        this(0.0d);
    }

    public double getValue() {
        return getPointer().getDouble(0);
    }

    public void setValue(double d2) {
        getPointer().setDouble(0, d2);
    }

    public String toString() {
        return String.format("double@0x%x=%s", new Object[]{Long.valueOf(Pointer.nativeValue(getPointer())), Double.valueOf(getValue())});
    }

    public DoubleByReference(double d2) {
        super(8);
        setValue(d2);
    }
}
