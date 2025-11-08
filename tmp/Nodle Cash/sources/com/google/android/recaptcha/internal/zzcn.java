package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class zzcn implements zzdd {
    @NotNull
    public static final zzcn zza = new zzcn();

    private zzcn() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        if (zzpqArr.length == 2) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != Objects.nonNull(zza2)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Object zza3 = zzcj.zzc().zza(zzpqArr[1]);
                if (true != (zza3 instanceof Integer)) {
                    zza3 = null;
                }
                Integer num = (Integer) zza3;
                if (num != null) {
                    int intValue = num.intValue();
                    try {
                        zzcj.zzc().zzf(i3, zza2 instanceof String ? String.valueOf(((String) zza2).charAt(intValue)) : zza2 instanceof List ? ((List) zza2).get(intValue) : Array.get(zza2, intValue));
                    } catch (Exception e3) {
                        if (e3 instanceof ArrayIndexOutOfBoundsException) {
                            throw new zzae(4, 22, e3);
                        }
                        throw new zzae(4, 23, e3);
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
