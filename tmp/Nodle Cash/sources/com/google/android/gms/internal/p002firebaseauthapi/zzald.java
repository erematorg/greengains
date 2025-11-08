package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzald  reason: invalid package */
final class zzald {
    private static final zzald zza = new zzald();
    private final zzalg zzb = new zzakd();
    private final ConcurrentMap<Class<?>, zzalh<?>> zzc = new ConcurrentHashMap();

    private zzald() {
    }

    public static zzald zza() {
        return zza;
    }

    public final <T> zzalh<T> zza(Class<T> cls) {
        zzajh.zza(cls, "messageType");
        zzalh<T> zzalh = this.zzc.get(cls);
        if (zzalh != null) {
            return zzalh;
        }
        zzalh<T> zza2 = this.zzb.zza(cls);
        zzajh.zza(cls, "messageType");
        zzajh.zza(zza2, "schema");
        zzalh<T> putIfAbsent = this.zzc.putIfAbsent(cls, zza2);
        return putIfAbsent != null ? putIfAbsent : zza2;
    }

    public final <T> zzalh<T> zza(T t2) {
        return zza(t2.getClass());
    }
}
