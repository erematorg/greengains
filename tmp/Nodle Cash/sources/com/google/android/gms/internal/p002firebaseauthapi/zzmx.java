package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmx  reason: invalid package */
final class zzmx<P> extends zzmy<P> implements zzcj<P> {
    public zzmx(String str, Class<P> cls, zzaky<? extends zzakp> zzaky) {
        super(str, cls, zzuw.zzb.ASYMMETRIC_PRIVATE, zzaky);
    }

    public final zzuw zzc(zzaho zzaho) throws GeneralSecurityException {
        zzbu zza = zzns.zza().zza(zzou.zza(this.zza, zzaho, this.zzb, zzvs.RAW, (Integer) null), zzbr.zza());
        if (zza instanceof zzck) {
            zzou zzou = (zzou) zzns.zza().zza(((zzck) zza).zzb(), zzou.class, zzbr.zza());
            return (zzuw) ((zzaje) zzuw.zza().zza(zzou.zzf()).zza(zzou.zzd()).zza(zzou.zza()).zzf());
        }
        throw new GeneralSecurityException("Key not private key");
    }
}
