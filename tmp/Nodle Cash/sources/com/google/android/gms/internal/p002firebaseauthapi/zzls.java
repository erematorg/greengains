package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzls  reason: invalid package */
public final class zzls implements zzbs {
    private final zzbs zza;
    private final byte[] zzb;

    private zzls(zzbs zzbs, byte[] bArr) {
        this.zza = zzbs;
        this.zzb = bArr;
    }

    public static zzbs zza(zzna zzna) throws GeneralSecurityException {
        byte[] bArr;
        zzou zza2 = zzna.zza(zzbr.zza());
        zzbs zzbs = (zzbs) zzco.zza((zzuw) ((zzaje) zzuw.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zzf()), zzbs.class);
        zzvs zzc = zza2.zzc();
        int i3 = zzlr.zza[zzc.ordinal()];
        if (i3 == 1) {
            bArr = zznt.zza.zzb();
        } else if (i3 == 2 || i3 == 3) {
            bArr = zznt.zza(zzna.zza().intValue()).zzb();
        } else if (i3 == 4) {
            bArr = zznt.zzb(zzna.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type ".concat(String.valueOf(zzc)));
        }
        return new zzls(zzbs, bArr);
    }
}
