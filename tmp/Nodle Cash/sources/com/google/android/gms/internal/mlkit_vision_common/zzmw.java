package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.Nullable;

public final class zzmw {
    @Nullable
    private static zzmw zza;

    private zzmw() {
    }

    public static synchronized zzmw zza() {
        zzmw zzmw;
        synchronized (zzmw.class) {
            try {
                if (zza == null) {
                    zza = new zzmw();
                }
                zzmw = zza;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzmw;
    }

    public static final boolean zzb() {
        return zzmv.zza("mlkit-dev-profiling");
    }
}
