package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmy  reason: invalid package */
public class zzmy<P> implements zzbt<P> {
    final String zza;
    final zzuw.zzb zzb;
    private final Class<P> zzc;
    private final zzaky<? extends zzakp> zzd;

    public zzmy(String str, Class<P> cls, zzuw.zzb zzb2, zzaky<? extends zzakp> zzaky) {
        this.zzd = zzaky;
        this.zza = str;
        this.zzc = cls;
        this.zzb = zzb2;
    }

    public static <P> zzbt<P> zza(String str, Class<P> cls, zzuw.zzb zzb2, zzaky<? extends zzakp> zzaky) {
        return new zzmy(str, cls, zzb2, zzaky);
    }

    public final P zzb(zzaho zzaho) throws GeneralSecurityException {
        return zznp.zza().zza(zzns.zza().zza(zzou.zza(this.zza, zzaho, this.zzb, zzvs.RAW, (Integer) null), zzbr.zza()), this.zzc);
    }

    public static <P> zzcj<P> zza(String str, Class<P> cls, zzaky<? extends zzakp> zzaky) {
        return new zzmx(str, cls, zzaky);
    }

    public final zzuw zza(zzaho zzaho) throws GeneralSecurityException {
        zzou zzou = (zzou) zzns.zza().zza(zzni.zza().zza(zzns.zza().zza(zzot.zza((zzvc) ((zzaje) zzvc.zza().zza(this.zza).zza(zzaho).zza(zzvs.RAW).zzf()))), (Integer) null), zzou.class, zzbr.zza());
        return (zzuw) ((zzaje) zzuw.zza().zza(zzou.zzf()).zza(zzou.zzd()).zza(zzou.zza()).zzf());
    }

    public final String zzb() {
        return this.zza;
    }

    public final Class<P> zza() {
        return this.zzc;
    }
}
