package com.google.android.gms.internal.auth;

final class zzfj extends zzfl {
    public /* synthetic */ zzfj(zzfi zzfi) {
        super((zzfk) null);
    }

    public final void zza(Object obj, long j2) {
        ((zzez) zzhj.zzf(obj, j2)).zzb();
    }

    public final void zzb(Object obj, Object obj2, long j2) {
        zzez zzez = (zzez) zzhj.zzf(obj, j2);
        zzez zzez2 = (zzez) zzhj.zzf(obj2, j2);
        int size = zzez.size();
        int size2 = zzez2.size();
        if (size > 0 && size2 > 0) {
            if (!zzez.zzc()) {
                zzez = zzez.zzd(size2 + size);
            }
            zzez.addAll(zzez2);
        }
        if (size > 0) {
            zzez2 = zzez;
        }
        zzhj.zzp(obj, j2, zzez2);
    }

    private zzfj() {
        super((zzfk) null);
    }
}
