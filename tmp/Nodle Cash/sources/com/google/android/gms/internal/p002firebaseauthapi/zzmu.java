package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzbu;
import com.google.android.gms.internal.p002firebaseauthapi.zzox;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmu  reason: invalid package */
public abstract class zzmu<KeyT extends zzbu, SerializationT extends zzox> {
    private final Class<KeyT> zza;
    private final Class<SerializationT> zzb;

    public static <KeyT extends zzbu, SerializationT extends zzox> zzmu<KeyT, SerializationT> zza(zzmw<KeyT, SerializationT> zzmw, Class<KeyT> cls, Class<SerializationT> cls2) {
        return new zzmt(cls, cls2, zzmw);
    }

    public abstract SerializationT zza(KeyT keyt, @Nullable zzcn zzcn) throws GeneralSecurityException;

    public final Class<SerializationT> zzb() {
        return this.zzb;
    }

    private zzmu(Class<KeyT> cls, Class<SerializationT> cls2) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public final Class<KeyT> zza() {
        return this.zza;
    }
}
