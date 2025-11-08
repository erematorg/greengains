package com.google.gson.internal;

import java.lang.reflect.Type;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.reflect.Typed;

public final /* synthetic */ class b implements ObjectConstructor, Typed {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7184a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Type f7185b;

    public /* synthetic */ b(int i3, Type type) {
        this.f7184a = i3;
        this.f7185b = type;
    }

    public Object construct() {
        int i3 = this.f7184a;
        Type type = this.f7185b;
        switch (i3) {
            case 0:
                return ConstructorConstructor.lambda$newSpecialCollectionConstructor$5(type);
            default:
                return ConstructorConstructor.lambda$newSpecialCollectionConstructor$6(type);
        }
    }

    public Type getType() {
        return TypeUtils.lambda$wrap$0(this.f7185b);
    }
}
