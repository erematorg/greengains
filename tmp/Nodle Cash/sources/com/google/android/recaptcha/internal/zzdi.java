package com.google.android.recaptcha.internal;

import java.util.Arrays;
import java.util.Objects;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class zzdi implements zzdd {
    @NotNull
    public static final zzdi zza = new zzdi();

    private zzdi() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        int length = zzpqArr.length;
        if (length >= 2) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != Objects.nonNull(zza2)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Class<?> cls = zza2 instanceof Class ? (Class) zza2 : zza2.getClass();
                Object zza3 = zzcj.zzc().zza(zzpqArr[1]);
                if (true != (zza3 instanceof String)) {
                    zza3 = null;
                }
                String str = (String) zza3;
                if (str != null) {
                    String zza4 = zzcj.zzh().zza(str);
                    if (!Intrinsics.areEqual((Object) zza4, (Object) "forName")) {
                        Class[] zzg = zzcj.zzc().zzg(ArraysKt.toList((T[]) zzpqArr).subList(2, length));
                        try {
                            zzcj.zzc().zzf(i3, cls.getMethod(zza4, (Class[]) Arrays.copyOf(zzg, zzg.length)));
                        } catch (Exception e3) {
                            throw new zzae(6, 13, e3);
                        }
                    } else {
                        throw new zzae(6, 48, (Throwable) null);
                    }
                } else {
                    throw new zzae(4, 5, (Throwable) null);
                }
            } else {
                throw new zzae(4, 5, (Throwable) null);
            }
        } else {
            throw new zzae(4, 3, (Throwable) null);
        }
    }
}
