package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhv  reason: invalid package */
public final class zzhv implements zzbh {
    private final zzbh zza;
    private final byte[] zzb;

    private zzhv(zzbh zzbh, byte[] bArr) {
        this.zza = zzbh;
        if (bArr.length == 0 || bArr.length == 5) {
            this.zzb = bArr;
            return;
        }
        throw new IllegalArgumentException("identifier has an invalid length");
    }

    public static zzbh zza(zzna zzna) throws GeneralSecurityException {
        byte[] bArr;
        zzou zza2 = zzna.zza(zzbr.zza());
        zzoy.zza();
        zzbh zzbh = (zzbh) zzoy.zza((zzuw) ((zzaje) zzuw.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zzf()), zzbh.class);
        zzvs zzc = zza2.zzc();
        int i3 = zzhu.zza[zzc.ordinal()];
        if (i3 == 1) {
            bArr = zznt.zza.zzb();
        } else if (i3 == 2 || i3 == 3) {
            bArr = zznt.zza(zzna.zza().intValue()).zzb();
        } else if (i3 == 4) {
            bArr = zznt.zzb(zzna.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type ".concat(String.valueOf(zzc)));
        }
        return new zzhv(zzbh, bArr);
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzb;
        return bArr3.length == 0 ? this.zza.zzb(bArr, bArr2) : zzwh.zza(bArr3, this.zza.zzb(bArr, bArr2));
    }

    public static zzbh zza(zzbh zzbh, zzxv zzxv) {
        return new zzhv(zzbh, zzxv.zzb());
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzb;
        if (bArr3.length == 0) {
            return this.zza.zza(bArr, bArr2);
        }
        if (zzph.zza(bArr3, bArr)) {
            return this.zza.zza(Arrays.copyOfRange(bArr, 5, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("wrong prefix");
    }
}
