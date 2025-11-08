package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzri  reason: invalid package */
public final class zzri implements zzcf {
    private static final byte[] zza = {0};
    private final zzcf zzb;
    private final zzvs zzc;
    private final byte[] zzd;

    private zzri(zzcf zzcf, zzvs zzvs, byte[] bArr) {
        this.zzb = zzcf;
        this.zzc = zzvs;
        this.zzd = bArr;
    }

    public static zzcf zza(zzna zzna) throws GeneralSecurityException {
        byte[] bArr;
        zzou zza2 = zzna.zza(zzbr.zza());
        zzoy.zza();
        zzcf zzcf = (zzcf) zzoy.zza((zzuw) ((zzaje) zzuw.zza().zza(zza2.zzf()).zza(zza2.zzd()).zza(zza2.zza()).zzf()), zzcf.class);
        zzvs zzc2 = zza2.zzc();
        int i3 = zzrl.zza[zzc2.ordinal()];
        if (i3 == 1) {
            bArr = zznt.zza.zzb();
        } else if (i3 == 2 || i3 == 3) {
            bArr = zznt.zza(zzna.zza().intValue()).zzb();
        } else if (i3 == 4) {
            bArr = zznt.zzb(zzna.zza().intValue()).zzb();
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return new zzri(zzcf, zzc2, bArr);
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length >= 10) {
            if (this.zzc.equals(zzvs.LEGACY)) {
                bArr2 = zzwh.zza(bArr2, zza);
            }
            byte[] bArr3 = new byte[0];
            if (!this.zzc.equals(zzvs.RAW)) {
                byte[] copyOf = Arrays.copyOf(bArr, 5);
                bArr = Arrays.copyOfRange(bArr, 5, bArr.length);
                bArr3 = copyOf;
            }
            if (Arrays.equals(this.zzd, bArr3)) {
                this.zzb.zza(bArr, bArr2);
                return;
            }
            throw new GeneralSecurityException("wrong prefix");
        }
        throw new GeneralSecurityException("tag too short");
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        if (this.zzc.equals(zzvs.LEGACY)) {
            bArr = zzwh.zza(bArr, zza);
        }
        return zzwh.zza(this.zzd, this.zzb.zza(bArr));
    }
}
