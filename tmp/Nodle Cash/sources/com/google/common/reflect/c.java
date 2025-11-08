package com.google.common.reflect;

import com.google.common.base.Function;
import com.google.common.reflect.Types;
import java.lang.reflect.Type;

public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Types.JavaVersion f6935a;

    public /* synthetic */ c(Types.JavaVersion javaVersion) {
        this.f6935a = javaVersion;
    }

    public final Object apply(Object obj) {
        return this.f6935a.typeName((Type) obj);
    }
}
