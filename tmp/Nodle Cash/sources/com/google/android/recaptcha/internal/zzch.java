package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzch extends zzce {
    @NotNull
    private final zzcg zza;
    @NotNull
    private final String zzb;

    public zzch(@NotNull zzcg zzcg, @NotNull String str, @Nullable Object obj) {
        super(obj);
        this.zza = zzcg;
        this.zzb = str;
    }

    public final boolean zza(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr) {
        List list;
        if (!Intrinsics.areEqual((Object) method.getName(), (Object) this.zzb)) {
            return false;
        }
        zzcg zzcg = this.zza;
        if (objArr == null || (list = ArraysKt.asList((T[]) objArr)) == null) {
            list = CollectionsKt.emptyList();
        }
        zzcg.zzb(list);
        return true;
    }
}
