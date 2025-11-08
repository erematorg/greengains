package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzmz {
    private static final zzmz zza = new zzmz();
    private final zznc zzb = new zzlz();
    private final ConcurrentMap<Class<?>, zznd<?>> zzc = new ConcurrentHashMap();

    private zzmz() {
    }

    public static zzmz zza() {
        return zza;
    }

    public final <T> zznd<T> zza(Class<T> cls) {
        zzle.zza(cls, "messageType");
        zznd<T> zznd = this.zzc.get(cls);
        if (zznd != null) {
            return zznd;
        }
        zznd<T> zza2 = this.zzb.zza(cls);
        zzle.zza(cls, "messageType");
        zzle.zza(zza2, "schema");
        zznd<T> putIfAbsent = this.zzc.putIfAbsent(cls, zza2);
        return putIfAbsent != null ? putIfAbsent : zza2;
    }

    public final <T> zznd<T> zza(T t2) {
        return zza(t2.getClass());
    }
}
