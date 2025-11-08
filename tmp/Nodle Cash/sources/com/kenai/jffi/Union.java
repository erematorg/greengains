package com.kenai.jffi;

import java.util.Arrays;

public final class Union extends Aggregate {
    private final Type[] fields;

    public Union(Type... typeArr) {
        super(Foreign.getInstance(), Foreign.getInstance().newStruct(Type.nativeHandles(typeArr), true));
        this.fields = (Type[]) typeArr.clone();
    }

    public static Union newUnion(Type... typeArr) {
        return new Union(typeArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Union.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        return Arrays.equals(this.fields, ((Union) obj).fields);
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Type[] typeArr = this.fields;
        return hashCode + (typeArr != null ? Arrays.hashCode(typeArr) : 0);
    }
}
