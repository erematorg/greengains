package com.google.android.gms.internal.measurement;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

public final class zzhp {
    @GuardedBy("GservicesDelegateSupplier.class")
    @Nullable
    private static zzhs zza;

    public static synchronized zzhs zza() {
        zzhs zzhs;
        synchronized (zzhp.class) {
            try {
                if (zza == null) {
                    zza(new zzhr());
                }
                zzhs = zza;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzhs;
    }

    private static synchronized void zza(zzhs zzhs) {
        synchronized (zzhp.class) {
            if (zza == null) {
                zza = zzhs;
            } else {
                throw new IllegalStateException("init() already called");
            }
        }
    }
}
