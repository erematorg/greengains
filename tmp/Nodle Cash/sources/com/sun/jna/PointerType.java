package com.sun.jna;

import A.a;

public abstract class PointerType implements NativeMapped {
    private Pointer pointer;

    public PointerType() {
        this.pointer = Pointer.NULL;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PointerType)) {
            return false;
        }
        Pointer pointer2 = ((PointerType) obj).getPointer();
        Pointer pointer3 = this.pointer;
        return pointer3 == null ? pointer2 == null : pointer3.equals(pointer2);
    }

    public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
        if (obj == null) {
            return null;
        }
        PointerType pointerType = (PointerType) Klass.newInstance(getClass());
        pointerType.pointer = (Pointer) obj;
        return pointerType;
    }

    public Pointer getPointer() {
        return this.pointer;
    }

    public int hashCode() {
        Pointer pointer2 = this.pointer;
        if (pointer2 != null) {
            return pointer2.hashCode();
        }
        return 0;
    }

    public Class<?> nativeType() {
        return Pointer.class;
    }

    public void setPointer(Pointer pointer2) {
        this.pointer = pointer2;
    }

    public Object toNative() {
        return getPointer();
    }

    public String toString() {
        if (this.pointer == null) {
            return "NULL";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.pointer.toString());
        sb.append(" (");
        return a.n(sb, super.toString(), ")");
    }

    public PointerType(Pointer pointer2) {
        this.pointer = pointer2;
    }
}
