package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;

public final class zzsv {
    @Nullable
    private static zzsv zza;

    private zzsv() {
    }

    public static synchronized zzsv zza() {
        zzsv zzsv;
        synchronized (zzsv.class) {
            try {
                if (zza == null) {
                    zza = new zzsv();
                }
                zzsv = zza;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzsv;
    }

    public static void zzb() {
        zzsu.zza();
    }
}
