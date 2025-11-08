package com.sun.jna.ptr;

import androidx.camera.camera2.internal.C0118y;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

public abstract class ByReference extends PointerType {
    public ByReference(int i3) {
        setPointer(new Memory((long) i3));
    }

    public String toString() {
        try {
            Object invoke = getClass().getMethod("getValue", (Class[]) null).invoke(this, (Object[]) null);
            return invoke == null ? String.format("null@0x%x", new Object[]{Long.valueOf(Pointer.nativeValue(getPointer()))}) : String.format("%s@0x%x=%s", new Object[]{invoke.getClass().getSimpleName(), Long.valueOf(Pointer.nativeValue(getPointer())), invoke});
        } catch (Exception e3) {
            return C0118y.f("ByReference Contract violated - ", getClass().getName(), "#getValue raised exception: ", e3.getMessage());
        }
    }
}
