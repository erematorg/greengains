package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzox;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmq  reason: invalid package */
public abstract class zzmq<SerializationT extends zzox> {
    private final zzxv zza;
    private final Class<SerializationT> zzb;

    public static <SerializationT extends zzox> zzmq<SerializationT> zza(zzms<SerializationT> zzms, zzxv zzxv, Class<SerializationT> cls) {
        return new zzmp(zzxv, cls, zzms);
    }

    public abstract zzbu zza(SerializationT serializationt, @Nullable zzcn zzcn) throws GeneralSecurityException;

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zzmq(zzxv zzxv, Class<SerializationT> cls) {
        this.zza = zzxv;
        this.zzb = cls;
    }

    public final zzxv zza() {
        return this.zza;
    }
}
