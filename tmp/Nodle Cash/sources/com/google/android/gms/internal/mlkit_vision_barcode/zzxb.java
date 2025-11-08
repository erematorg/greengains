package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;

public final class zzxb {
    @Nullable
    private static zzxb zza;

    private zzxb() {
    }

    public static synchronized zzxb zza() {
        zzxb zzxb;
        synchronized (zzxb.class) {
            try {
                if (zza == null) {
                    zza = new zzxb();
                }
                zzxb = zza;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzxb;
    }
}
