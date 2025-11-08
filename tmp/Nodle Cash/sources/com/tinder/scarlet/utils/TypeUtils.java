package com.tinder.scarlet.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006*\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\u0001¨\u0006\t"}, d2 = {"getParameterUpperBound", "Ljava/lang/reflect/Type;", "Ljava/lang/reflect/ParameterizedType;", "index", "", "getRawType", "Ljava/lang/Class;", "hasUnresolvableType", "", "scarlet-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
@JvmName(name = "TypeUtils")
public final class TypeUtils {
    @NotNull
    public static final Type getParameterUpperBound(@NotNull ParameterizedType parameterizedType, int i3) {
        Intrinsics.checkNotNullParameter(parameterizedType, "<this>");
        Type parameterUpperBound = Utils.getParameterUpperBound(i3, parameterizedType);
        Intrinsics.checkNotNullExpressionValue(parameterUpperBound, "getParameterUpperBound(index, this)");
        return parameterUpperBound;
    }

    @NotNull
    public static final Class<?> getRawType(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "<this>");
        Class<?> rawType = Utils.getRawType(type);
        Intrinsics.checkNotNullExpressionValue(rawType, "getRawType(this)");
        return rawType;
    }

    public static final boolean hasUnresolvableType(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "<this>");
        return Utils.hasUnresolvableType(type);
    }
}
