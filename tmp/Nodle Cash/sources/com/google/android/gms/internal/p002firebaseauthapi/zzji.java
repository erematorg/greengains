package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzji  reason: invalid package */
public final class zzji implements zzbq {
    private final zzbq zza;
    private final zzvs zzb;
    private final byte[] zzc;

    private zzji(zzbq zzbq, zzvs zzvs, byte[] bArr) {
        this.zza = zzbq;
        this.zzb = zzvs;
        this.zzc = bArr;
    }

    public static zzbq zza(zzna zzna) throws GeneralSecurityException {
        byte[] bArr;
        zzou zza2 = zzna.zza(zzbr.zza());
        zzoy.zza();
        zzbq zzbq = (zzbq) zzoy.zza((zzuw) ((zzaje) zzuw.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zzf()), zzbq.class);
        zzvs zzc2 = zza2.zzc();
        int i3 = zzjl.zza[zzc2.ordinal()];
        if (i3 == 1) {
            bArr = zznt.zza.zzb();
        } else if (i3 == 2 || i3 == 3) {
            bArr = zznt.zza(zzna.zza().intValue()).zzb();
        } else if (i3 == 4) {
            bArr = zznt.zzb(zzna.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException(a.k("unknown output prefix type ", zzc2.zza()));
        }
        return new zzji(zzbq, zzc2, bArr);
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.zzb == zzvs.RAW) {
            return this.zza.zza(bArr, bArr2);
        }
        if (zzph.zza(this.zzc, bArr)) {
            return this.zza.zza(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("wrong prefix");
    }
}
