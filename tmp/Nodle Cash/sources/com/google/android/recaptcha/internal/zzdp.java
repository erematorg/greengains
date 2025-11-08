package com.google.android.recaptcha.internal;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;
import kotlin.collections.ArraysKt;
import org.jetbrains.annotations.NotNull;

public final class zzdp implements zzdd {
    @NotNull
    public static final zzdp zza = new zzdp();

    private zzdp() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        int length = zzpqArr.length;
        if (length != 0) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != Objects.nonNull(zza2)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Constructor<?> constructor = zza2 instanceof Constructor ? (Constructor) zza2 : zza2.getClass().getConstructor((Class[]) null);
                Object[] zzh = zzcj.zzc().zzh(ArraysKt.toList((T[]) zzpqArr).subList(1, length));
                try {
                    zzcj.zzc().zzf(i3, constructor.newInstance(Arrays.copyOf(zzh, zzh.length)));
                } catch (Exception e3) {
                    throw new zzae(6, 14, e3);
                }
            } else {
                throw new zzae(4, 5, (Throwable) null);
            }
        } else {
            throw new zzae(4, 3, (Throwable) null);
        }
    }
}
