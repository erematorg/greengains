package com.google.android.recaptcha.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class zzce implements InvocationHandler {
    @Nullable
    private final Object zza;

    public zzce(@Nullable Object obj) {
        this.zza = obj;
    }

    @NotNull
    public final Object invoke(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr) {
        Object obj2;
        if (Intrinsics.areEqual((Object) method.getName(), (Object) "toString") && method.getParameterTypes().length == 0) {
            return "Proxy@".concat(String.valueOf(Integer.toHexString(obj.hashCode())));
        }
        if (Intrinsics.areEqual((Object) method.getName(), (Object) "hashCode") && method.getParameterTypes().length == 0) {
            return Integer.valueOf(System.identityHashCode(obj));
        }
        if (Intrinsics.areEqual((Object) method.getName(), (Object) "equals") && method.getParameterTypes().length != 0) {
            boolean z2 = false;
            if (!(objArr == null || objArr.length == 0)) {
                Object obj3 = objArr[0];
                if ((obj3 != null ? obj3.hashCode() : 0) == obj.hashCode()) {
                    z2 = true;
                }
            }
            return Boolean.valueOf(z2);
        } else if (!zza(obj, method, objArr)) {
            return Unit.INSTANCE;
        } else {
            if ((this.zza != null || !Intrinsics.areEqual((Object) method.getReturnType(), (Object) Void.TYPE)) && ((obj2 = this.zza) == null || !Intrinsics.areEqual((Object) zzgd.zza(obj2.getClass()), (Object) zzgd.zza(method.getReturnType())))) {
                Object obj4 = this.zza;
                Class<?> returnType = method.getReturnType();
                throw new IllegalArgumentException(obj4 + " cannot be returned from method with return type " + returnType);
            }
            Object obj5 = this.zza;
            return obj5 == null ? Unit.INSTANCE : obj5;
        }
    }

    public abstract boolean zza(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr);
}
