package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzcf extends zzce {
    @NotNull
    private final Function2 zza;
    @NotNull
    private final String zzb;

    public zzcf(@NotNull Function2 function2, @NotNull String str, @Nullable Object obj) {
        super(obj);
        this.zza = function2;
        this.zzb = str;
    }

    public final boolean zza(@NotNull Object obj, @NotNull Method method, @Nullable Object[] objArr) {
        ArrayList arrayList;
        if (!Intrinsics.areEqual((Object) method.getName(), (Object) this.zzb)) {
            return false;
        }
        zzpi zzf = zzpl.zzf();
        if (objArr != null) {
            ArrayList arrayList2 = new ArrayList(objArr.length);
            for (Object obj2 : objArr) {
                zzpj zzf2 = zzpk.zzf();
                zzf2.zzv(obj2.toString());
                arrayList2.add((zzpk) zzf2.zzj());
            }
            arrayList = arrayList2;
        } else {
            arrayList = CollectionsKt.emptyList();
        }
        zzf.zzd(arrayList);
        Function2 function2 = this.zza;
        byte[] zzd = ((zzpl) zzf.zzj()).zzd();
        function2.invoke(objArr, zzfy.zzh().zzi(zzd, 0, zzd.length));
        return true;
    }
}
