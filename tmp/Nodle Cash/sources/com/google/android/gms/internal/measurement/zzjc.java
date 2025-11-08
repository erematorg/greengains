package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;

public final class zzjc {
    private final boolean zza;

    public zzjc(zzjf zzjf) {
        Preconditions.checkNotNull(zzjf, "BuildInfo must be non-null");
        this.zza = !zzjf.zza();
    }

    public final boolean zza(String str) {
        Preconditions.checkNotNull(str, "flagName must not be null");
        if (!this.zza) {
            return true;
        }
        return zzje.zza.get().containsValue(str);
    }
}
