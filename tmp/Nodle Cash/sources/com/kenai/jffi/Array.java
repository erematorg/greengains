package com.kenai.jffi;

public final class Array extends Aggregate {
    private final Type elementType;
    private final int length;

    public Array(Type type, int i3) {
        super(Foreign.getInstance(), Foreign.getInstance().newArray(type.handle(), i3));
        this.elementType = type;
        this.length = i3;
    }

    public static Array newArray(Type type, int i3) {
        return new Array(type, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Array.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        Array array = (Array) obj;
        if (this.length != array.length) {
            return false;
        }
        Type type = this.elementType;
        return type == null ? array.elementType == null : type.equals(array.elementType);
    }

    public final Type getElementType() {
        return this.elementType;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Type type = this.elementType;
        return ((hashCode + (type != null ? type.hashCode() : 0)) * 31) + this.length;
    }

    public final int length() {
        return this.length;
    }
}
