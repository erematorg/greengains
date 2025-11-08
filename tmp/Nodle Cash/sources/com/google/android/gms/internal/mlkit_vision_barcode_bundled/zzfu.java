package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzfu {
    public static final /* synthetic */ int zza = 0;
    private static final zzfu zzb = new zzfu();
    private final zzgf zzc = new zzfe();
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    private zzfu() {
    }

    public static zzfu zza() {
        return zzb;
    }

    public final zzge zzb(Class cls) {
        zzep.zzc(cls, "messageType");
        zzge zzge = (zzge) this.zzd.get(cls);
        if (zzge == null) {
            zzge = this.zzc.zza(cls);
            zzep.zzc(cls, "messageType");
            zzge zzge2 = (zzge) this.zzd.putIfAbsent(cls, zzge);
            return zzge2 == null ? zzge : zzge2;
        }
    }
}
