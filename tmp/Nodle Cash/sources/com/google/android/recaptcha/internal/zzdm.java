package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import org.jetbrains.annotations.NotNull;

public final class zzdm implements zzdd {
    @NotNull
    public static final zzdm zza = new zzdm();

    private zzdm() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        int length = zzpqArr.length;
        if (length != 0) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != (zza2 instanceof Method)) {
                zza2 = null;
            }
            Method method = (Method) zza2;
            if (method != null) {
                Object[] zzh = zzcj.zzc().zzh(ArraysKt.toList((T[]) zzpqArr).subList(1, length));
                try {
                    zzcj.zzc().zzf(i3, method.invoke((Object) null, Arrays.copyOf(zzh, zzh.length)));
                } catch (Exception e3) {
                    throw new zzae(6, 15, e3);
                }
            } else {
                throw new zzae(4, 5, (Throwable) null);
            }
        } else {
            throw new zzae(4, 3, (Throwable) null);
        }
    }
}
