package com.google.devtools.ksp;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$createInvocationHandler$1$value$3 extends Lambda implements Function0<Object> {
    final /* synthetic */ Method $method;
    final /* synthetic */ Object $result;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UtilsKt$createInvocationHandler$1$value$3(Object obj, Method method) {
        super(0);
        this.$result = obj;
        this.$method = method;
    }

    public final Object invoke() {
        Object obj = this.$result;
        Intrinsics.checkNotNullExpressionValue(obj, "$result");
        Class<?> returnType = this.$method.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
        return UtilsKt.asEnum(obj, returnType);
    }
}
