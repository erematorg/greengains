package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class zzda implements zzdd {
    @NotNull
    public static final zzda zza = new zzda();

    private zzda() {
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
                        if (zza2 instanceof String) {
                            zza2 = zzcj.zzh().zza((String) zza2);
                        }
                        zzcj.zzc().zzf(i3, Array.newInstance(zzci.zza(zza2), intValue));
                    } catch (Exception e3) {
                        throw new zzae(6, 21, e3);
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
