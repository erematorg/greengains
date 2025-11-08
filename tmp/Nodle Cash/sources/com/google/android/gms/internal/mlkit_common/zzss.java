package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;

public final class zzss {
    @Nullable
    private static zzsr zza;

    public static synchronized zzsh zza(zzsb zzsb) {
        zzsh zzsh;
        synchronized (zzss.class) {
            try {
                if (zza == null) {
                    zza = new zzsr((zzsq) null);
                }
                zzsh = (zzsh) zza.get(zzsb);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzsh;
    }

    public static synchronized zzsh zzb(String str) {
        zzsh zza2;
        synchronized (zzss.class) {
            zza2 = zza(zzsb.zzd("common").zzd());
        }
        return zza2;
    }
}
