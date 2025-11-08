package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.Enum;
import java.security.GeneralSecurityException;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmh  reason: invalid package */
public final class zzmh<E extends Enum<E>, O> {
    private final Map<E, O> zza;
    private final Map<O, E> zzb;

    public static <E extends Enum<E>, O> zzmk<E, O> zza() {
        return new zzmk<>();
    }

    private zzmh(Map<E, O> map, Map<O, E> map2) {
        this.zza = map;
        this.zzb = map2;
    }

    public final E zza(O o3) throws GeneralSecurityException {
        E e3 = (Enum) this.zzb.get(o3);
        if (e3 != null) {
            return e3;
        }
        throw new GeneralSecurityException("Unable to convert object enum: ".concat(String.valueOf(o3)));
    }

    public final O zza(E e3) throws GeneralSecurityException {
        O o3 = this.zza.get(e3);
        if (o3 != null) {
            return o3;
        }
        throw new GeneralSecurityException("Unable to convert proto enum: ".concat(String.valueOf(e3)));
    }
}
