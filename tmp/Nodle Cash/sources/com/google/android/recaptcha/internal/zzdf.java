package com.google.android.recaptcha.internal;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class zzdf implements zzdd {
    @NotNull
    public static final zzdf zza = new zzdf();

    private zzdf() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        if (zzpqArr.length == 1) {
            Object zza2 = zzcj.zzc().zza(zzpqArr[0]);
            if (true != Objects.nonNull(zza2)) {
                zza2 = null;
            }
            if (zza2 != null) {
                try {
                    if (zza2 instanceof String) {
                        zza2 = zzcj.zzh().zza((String) zza2);
                    }
                    zzcj.zzc().zzf(i3, zzci.zza(zza2));
                } catch (zzae e3) {
                    throw e3;
                } catch (Exception e4) {
                    throw new zzae(6, 8, e4);
                }
            } else {
                throw new zzae(4, 5, (Throwable) null);
            }
        } else {
            throw new zzae(4, 3, (Throwable) null);
        }
    }
}
