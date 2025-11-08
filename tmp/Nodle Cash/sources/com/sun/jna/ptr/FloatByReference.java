package com.sun.jna.ptr;

import com.sun.jna.Pointer;

public class FloatByReference extends ByReference {
    public FloatByReference() {
        this(0.0f);
    }

    public float getValue() {
        return getPointer().getFloat(0);
    }

    public void setValue(float f2) {
        getPointer().setFloat(0, f2);
    }

    public String toString() {
        return String.format("float@0x%x=%s", new Object[]{Long.valueOf(Pointer.nativeValue(getPointer())), Float.valueOf(getValue())});
    }

    public FloatByReference(float f2) {
        super(4);
        setValue(f2);
    }
}
