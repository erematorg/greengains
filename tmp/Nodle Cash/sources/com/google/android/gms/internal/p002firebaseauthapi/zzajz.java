package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajz  reason: invalid package */
final class zzajz extends zzaju {
    private static <E> zzajl<E> zzc(Object obj, long j2) {
        return (zzajl) zzamm.zze(obj, j2);
    }

    public final <L> List<L> zza(Object obj, long j2) {
        zzajl zzc = zzc(obj, j2);
        if (zzc.zzc()) {
            return zzc;
        }
        int size = zzc.size();
        zzajl zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzamm.zza(obj, j2, (Object) zza);
        return zza;
    }

    public final void zzb(Object obj, long j2) {
        zzc(obj, j2).zzb();
    }

    private zzajz() {
        super();
    }

    public final <E> void zza(Object obj, Object obj2, long j2) {
        zzajl zzc = zzc(obj, j2);
        zzajl zzc2 = zzc(obj2, j2);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzc()) {
                zzc = zzc.zza(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzamm.zza(obj, j2, (Object) zzc2);
    }
}
