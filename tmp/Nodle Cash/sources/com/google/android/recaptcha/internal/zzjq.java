package com.google.android.recaptcha.internal;

import java.util.List;

final class zzjq extends zzjs {
    public /* synthetic */ zzjq(zzjp zzjp) {
        super((zzjr) null);
    }

    public final List zza(Object obj, long j2) {
        zzjb zzjb = (zzjb) zzlv.zzf(obj, j2);
        if (zzjb.zzc()) {
            return zzjb;
        }
        int size = zzjb.size();
        zzjb zzd = zzjb.zzd(size == 0 ? 10 : size + size);
        zzlv.zzs(obj, j2, zzd);
        return zzd;
    }

    public final void zzb(Object obj, long j2) {
        ((zzjb) zzlv.zzf(obj, j2)).zzb();
    }

    public final void zzc(Object obj, Object obj2, long j2) {
        zzjb zzjb = (zzjb) zzlv.zzf(obj, j2);
        zzjb zzjb2 = (zzjb) zzlv.zzf(obj2, j2);
        int size = zzjb.size();
        int size2 = zzjb2.size();
        if (size > 0 && size2 > 0) {
            if (!zzjb.zzc()) {
                zzjb = zzjb.zzd(size2 + size);
            }
            zzjb.addAll(zzjb2);
        }
        if (size > 0) {
            zzjb2 = zzjb;
        }
        zzlv.zzs(obj, j2, zzjb2);
    }

    private zzjq() {
        super((zzjr) null);
    }
}
