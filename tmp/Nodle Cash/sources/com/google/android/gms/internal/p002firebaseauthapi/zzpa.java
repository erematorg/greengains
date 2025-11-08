package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpa  reason: invalid package */
public final class zzpa {
    /* access modifiers changed from: private */
    public final Map<zzpe, zzmu<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<zzpc, zzmq<?>> zzb;
    /* access modifiers changed from: private */
    public final Map<zzpe, zzoa<?, ?>> zzc;
    /* access modifiers changed from: private */
    public final Map<zzpc, zznw<?>> zzd;

    private zzpa(zzoz zzoz) {
        this.zza = new HashMap(zzoz.zza);
        this.zzb = new HashMap(zzoz.zzb);
        this.zzc = new HashMap(zzoz.zzc);
        this.zzd = new HashMap(zzoz.zzd);
    }

    public final <SerializationT extends zzox> zzbu zza(SerializationT serializationt, @Nullable zzcn zzcn) throws GeneralSecurityException {
        zzpc zzpc = new zzpc(serializationt.getClass(), serializationt.zzb());
        if (this.zzb.containsKey(zzpc)) {
            return this.zzb.get(zzpc).zza(serializationt, zzcn);
        }
        throw new GeneralSecurityException(a.l("No Key Parser for requested key type ", String.valueOf(zzpc), " available"));
    }

    public final <SerializationT extends zzox> boolean zzb(SerializationT serializationt) {
        return this.zzb.containsKey(new zzpc(serializationt.getClass(), serializationt.zzb()));
    }

    public final <SerializationT extends zzox> boolean zzc(SerializationT serializationt) {
        return this.zzd.containsKey(new zzpc(serializationt.getClass(), serializationt.zzb()));
    }

    public final <SerializationT extends zzox> zzci zza(SerializationT serializationt) throws GeneralSecurityException {
        zzpc zzpc = new zzpc(serializationt.getClass(), serializationt.zzb());
        if (this.zzd.containsKey(zzpc)) {
            return this.zzd.get(zzpc).zza(serializationt);
        }
        throw new GeneralSecurityException(a.l("No Parameters Parser for requested key type ", String.valueOf(zzpc), " available"));
    }

    public final <KeyT extends zzbu, SerializationT extends zzox> SerializationT zza(KeyT keyt, Class<SerializationT> cls, @Nullable zzcn zzcn) throws GeneralSecurityException {
        zzpe zzpe = new zzpe(keyt.getClass(), cls);
        if (this.zza.containsKey(zzpe)) {
            return this.zza.get(zzpe).zza(keyt, zzcn);
        }
        throw new GeneralSecurityException(a.l("No Key serializer for ", String.valueOf(zzpe), " available"));
    }

    public final <ParametersT extends zzci, SerializationT extends zzox> SerializationT zza(ParametersT parameterst, Class<SerializationT> cls) throws GeneralSecurityException {
        zzpe zzpe = new zzpe(parameterst.getClass(), cls);
        if (this.zzc.containsKey(zzpe)) {
            return this.zzc.get(zzpe).zza(parameterst);
        }
        throw new GeneralSecurityException(a.l("No Key Format serializer for ", String.valueOf(zzpe), " available"));
    }
}
