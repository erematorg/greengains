package com.google.android.recaptcha.internal;

import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public final class zzcl {
    @NotNull
    private final zzaa zza;
    @NotNull
    private final zzck zzb;
    @NotNull
    private final HashMap zzc;
    @NotNull
    private final zzcd zzd;
    @NotNull
    private final zzag zze;

    public zzcl(@NotNull zzcd zzcd, @NotNull zzag zzag, @NotNull zzaa zzaa) {
        this.zzd = zzcd;
        this.zze = zzag;
        this.zza = zzaa;
        zzck zzck = new zzck();
        this.zzb = zzck;
        HashMap hashMap = new HashMap();
        this.zzc = hashMap;
        zzck.zze(173, hashMap);
    }

    @NotNull
    public final zzaa zza() {
        return this.zza;
    }

    @NotNull
    public final zzck zzb() {
        return this.zzb;
    }

    public final void zzc() {
        this.zzb.zzd();
        this.zzb.zze(173, this.zzc);
    }

    @NotNull
    public final zzag zzd() {
        return this.zze;
    }

    @NotNull
    public final zzcd zze() {
        return this.zzd;
    }

    public final void zzf(@NotNull int i3, @NotNull Object obj) {
        this.zzc.put(Integer.valueOf(i3 - 2), obj);
    }
}
