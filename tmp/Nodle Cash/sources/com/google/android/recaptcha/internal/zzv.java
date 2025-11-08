package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;

public final class zzv {
    @NotNull
    public static final zzv zza = new zzv();
    @NotNull
    private static final ConcurrentHashMap zzb = new ConcurrentHashMap();

    private zzv() {
    }

    public static final void zza(int i3, long j2) {
        ConcurrentHashMap concurrentHashMap = zzb;
        Integer valueOf = Integer.valueOf(i3);
        Object obj = concurrentHashMap.get(valueOf);
        if (obj == null) {
            obj = new zzu();
        }
        zzu zzu = (zzu) obj;
        zzu.zzg(zzu.zzb() + 1);
        zzu.zzf(zzu.zzd() + j2);
        zzu.zze(Math.max(j2, zzu.zzc()));
        concurrentHashMap.put(valueOf, zzu);
    }
}
