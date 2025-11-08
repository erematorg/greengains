package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzae extends Exception {
    @Nullable
    private final Throwable zza;
    @NotNull
    private final zzpg zzb;
    @NotNull
    private final int zzc;
    @NotNull
    private final int zzd;

    public zzae(@NotNull int i3, @NotNull int i4, @Nullable Throwable th) {
        this.zzc = i3;
        this.zzd = i4;
        this.zza = th;
        zzpg zzf = zzph.zzf();
        zzf.zze(i4);
        zzf.zzp(i3);
        this.zzb = zzf;
    }

    @Nullable
    public final Throwable getCause() {
        return this.zza;
    }

    @NotNull
    public final zzpg zza() {
        return this.zzb;
    }

    @NotNull
    public final int zzb() {
        return this.zzd;
    }
}
