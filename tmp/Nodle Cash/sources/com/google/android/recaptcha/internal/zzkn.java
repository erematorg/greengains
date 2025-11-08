package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzkn {
    public static final /* synthetic */ int zza = 0;
    private static final zzkn zzb = new zzkn();
    private final zzks zzc = new zzjw();
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    private zzkn() {
    }

    public static zzkn zza() {
        return zzb;
    }

    public final zzkr zzb(Class cls) {
        zzjc.zzc(cls, "messageType");
        zzkr zzkr = (zzkr) this.zzd.get(cls);
        if (zzkr == null) {
            zzkr = this.zzc.zza(cls);
            zzjc.zzc(cls, "messageType");
            zzkr zzkr2 = (zzkr) this.zzd.putIfAbsent(cls, zzkr);
            return zzkr2 == null ? zzkr : zzkr2;
        }
    }
}
