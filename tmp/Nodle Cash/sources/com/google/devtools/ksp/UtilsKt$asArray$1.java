package com.google.devtools.ksp;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "result", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$asArray$1 extends Lambda implements Function1<Object, Object> {
    final /* synthetic */ Method $method;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UtilsKt$asArray$1(Method method) {
        super(1);
        this.$method = method;
    }

    @NotNull
    public final Object invoke(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "result");
        Class<?> componentType = this.$method.getReturnType().getComponentType();
        Intrinsics.checkNotNullExpressionValue(componentType, "getComponentType(...)");
        Object access$asEnum = UtilsKt.asEnum(obj, componentType);
        Intrinsics.checkNotNullExpressionValue(access$asEnum, "access$asEnum(...)");
        return access$asEnum;
    }
}
