package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoz  reason: invalid package */
public final class zzoz {
    /* access modifiers changed from: private */
    public final Map<zzpe, zzmu<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<zzpc, zzmq<?>> zzb;
    /* access modifiers changed from: private */
    public final Map<zzpe, zzoa<?, ?>> zzc;
    /* access modifiers changed from: private */
    public final Map<zzpc, zznw<?>> zzd;

    public zzoz() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public final <SerializationT extends zzox> zzoz zza(zzmq<SerializationT> zzmq) throws GeneralSecurityException {
        zzpc zzpc = new zzpc(zzmq.zzb(), zzmq.zza());
        if (this.zzb.containsKey(zzpc)) {
            zzmq zzmq2 = this.zzb.get(zzpc);
            if (!zzmq2.equals(zzmq) || !zzmq.equals(zzmq2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(String.valueOf(zzpc)));
            }
        } else {
            this.zzb.put(zzpc, zzmq);
        }
        return this;
    }

    public zzoz(zzpa zzpa) {
        this.zza = new HashMap(zzpa.zza);
        this.zzb = new HashMap(zzpa.zzb);
        this.zzc = new HashMap(zzpa.zzc);
        this.zzd = new HashMap(zzpa.zzd);
    }

    public final <KeyT extends zzbu, SerializationT extends zzox> zzoz zza(zzmu<KeyT, SerializationT> zzmu) throws GeneralSecurityException {
        zzpe zzpe = new zzpe(zzmu.zza(), zzmu.zzb());
        if (this.zza.containsKey(zzpe)) {
            zzmu zzmu2 = this.zza.get(zzpe);
            if (!zzmu2.equals(zzmu) || !zzmu.equals(zzmu2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(String.valueOf(zzpe)));
            }
        } else {
            this.zza.put(zzpe, zzmu);
        }
        return this;
    }

    public final <SerializationT extends zzox> zzoz zza(zznw<SerializationT> zznw) throws GeneralSecurityException {
        zzpc zzpc = new zzpc(zznw.zzb(), zznw.zza());
        if (this.zzd.containsKey(zzpc)) {
            zznw zznw2 = this.zzd.get(zzpc);
            if (!zznw2.equals(zznw) || !zznw.equals(zznw2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(String.valueOf(zzpc)));
            }
        } else {
            this.zzd.put(zzpc, zznw);
        }
        return this;
    }

    public final <ParametersT extends zzci, SerializationT extends zzox> zzoz zza(zzoa<ParametersT, SerializationT> zzoa) throws GeneralSecurityException {
        zzpe zzpe = new zzpe(zzoa.zza(), zzoa.zzb());
        if (this.zzc.containsKey(zzpe)) {
            zzoa zzoa2 = this.zzc.get(zzpe);
            if (!zzoa2.equals(zzoa) || !zzoa.equals(zzoa2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(String.valueOf(zzpe)));
            }
        } else {
            this.zzc.put(zzpe, zzoa);
        }
        return this;
    }

    public final zzpa zza() {
        return new zzpa(this);
    }
}
