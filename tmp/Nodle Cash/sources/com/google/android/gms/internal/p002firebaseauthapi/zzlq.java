package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlq  reason: invalid package */
public final class zzlq implements zzbp {
    private final zzbp zza;
    private final byte[] zzb;

    private zzlq(zzbp zzbp, byte[] bArr) {
        this.zza = zzbp;
        this.zzb = bArr;
    }

    public static zzbp zza(zzna zzna) throws GeneralSecurityException {
        byte[] bArr;
        zzou zza2 = zzna.zza(zzbr.zza());
        zzbp zzbp = (zzbp) zzco.zza((zzuw) ((zzaje) zzuw.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zzf()), zzbp.class);
        zzvs zzc = zza2.zzc();
        int i3 = zzlp.zza[zzc.ordinal()];
        if (i3 == 1) {
            bArr = zznt.zza.zzb();
        } else if (i3 == 2 || i3 == 3) {
            bArr = zznt.zza(zzna.zza().intValue()).zzb();
        } else if (i3 == 4) {
            bArr = zznt.zzb(zzna.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type ".concat(String.valueOf(zzc)));
        }
        return new zzlq(zzbp, bArr);
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzb;
        if (bArr3.length == 0) {
            return this.zza.zza(bArr, bArr2);
        }
        if (zzph.zza(bArr3, bArr)) {
            return this.zza.zza(Arrays.copyOfRange(bArr, this.zzb.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
    }
}
