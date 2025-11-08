package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzlu implements zzlv {
    private static <E> zzll<E> zzc(Object obj, long j2) {
        return (zzll) zzny.zze(obj, j2);
    }

    public final <L> List<L> zza(Object obj, long j2) {
        zzll zzc = zzc(obj, j2);
        if (zzc.zzc()) {
            return zzc;
        }
        int size = zzc.size();
        zzll zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzny.zza(obj, j2, (Object) zza);
        return zza;
    }

    public final void zzb(Object obj, long j2) {
        zzc(obj, j2).zzb();
    }

    public final <E> void zza(Object obj, Object obj2, long j2) {
        zzll zzc = zzc(obj, j2);
        zzll zzc2 = zzc(obj2, j2);
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
        zzny.zza(obj, j2, (Object) zzc2);
    }
}
