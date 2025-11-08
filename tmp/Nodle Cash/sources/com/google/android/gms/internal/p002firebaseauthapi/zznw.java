package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzox;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznw  reason: invalid package */
public abstract class zznw<SerializationT extends zzox> {
    private final zzxv zza;
    private final Class<SerializationT> zzb;

    public static <SerializationT extends zzox> zznw<SerializationT> zza(zzny<SerializationT> zzny, zzxv zzxv, Class<SerializationT> cls) {
        return new zznv(zzxv, cls, zzny);
    }

    public abstract zzci zza(SerializationT serializationt) throws GeneralSecurityException;

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zznw(zzxv zzxv, Class<SerializationT> cls) {
        this.zza = zzxv;
        this.zzb = cls;
    }

    public final zzxv zza() {
        return this.zza;
    }
}
