package com.google.firebase.auth.internal;

import androidx.annotation.Nullable;

final class zzj extends zzf {
    private String zza;
    private String zzb;

    public final zzf zza(@Nullable String str) {
        this.zzb = str;
        return this;
    }

    public final zzf zzb(@Nullable String str) {
        this.zza = str;
        return this;
    }

    public final zzg zza() {
        return new zzk(this.zza, this.zzb);
    }
}
