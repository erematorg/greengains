package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.Nullable;

public final class zzms {
    @Nullable
    private static zzmr zza;

    public static synchronized zzmj zza(zzme zzme) {
        zzmj zzmj;
        synchronized (zzms.class) {
            try {
                if (zza == null) {
                    zza = new zzmr((zzmq) null);
                }
                zzmj = (zzmj) zza.get(zzme);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzmj;
    }

    public static synchronized zzmj zzb(String str) {
        zzmj zza2;
        synchronized (zzms.class) {
            zza2 = zza(zzme.zzd("vision-common").zzd());
        }
        return zza2;
    }
}
