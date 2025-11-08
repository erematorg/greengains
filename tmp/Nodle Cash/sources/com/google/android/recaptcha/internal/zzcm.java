package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class zzcm implements zzdd {
    @NotNull
    public static final zzcm zza = new zzcm();

    private zzcm() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        Object obj;
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
                    if (zza2 instanceof Integer) {
                        obj = Integer.valueOf(((Number) zza2).intValue() + intValue);
                    } else if (zza2 instanceof int[]) {
                        ArrayList arrayList = new ArrayList(r1);
                        for (int i4 : (int[]) zza2) {
                            arrayList.add(Integer.valueOf(i4 + intValue));
                        }
                        obj = arrayList.toArray(new Integer[0]);
                    } else {
                        throw new zzae(4, 5, (Throwable) null);
                    }
                    zzcj.zzc().zzf(i3, obj);
                    return;
                }
                throw new zzae(4, 5, (Throwable) null);
            }
            throw new zzae(4, 5, (Throwable) null);
        }
        throw new zzae(4, 3, (Throwable) null);
    }
}
