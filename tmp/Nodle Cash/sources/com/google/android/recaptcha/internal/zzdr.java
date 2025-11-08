package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;

public final class zzdr implements zzdd {
    @NotNull
    public static final zzdr zza = new zzdr();

    private zzdr() {
    }

    public final void zza(int i3, @NotNull zzcj zzcj, @NotNull zzpq... zzpqArr) throws zzae {
        if (zzpqArr.length == 1) {
            zzcj.zzc().zzf(i3, zzcj.zzc().zza(zzpqArr[0]));
            return;
        }
        throw new zzae(4, 3, (Throwable) null);
    }
}
