package com.sun.jna.ptr;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class PointerByReference extends ByReference {
    public PointerByReference() {
        this((Pointer) null);
    }

    public Pointer getValue() {
        return getPointer().getPointer(0);
    }

    public void setValue(Pointer pointer) {
        getPointer().setPointer(0, pointer);
    }

    public PointerByReference(Pointer pointer) {
        super(Native.POINTER_SIZE);
        setValue(pointer);
    }
}
