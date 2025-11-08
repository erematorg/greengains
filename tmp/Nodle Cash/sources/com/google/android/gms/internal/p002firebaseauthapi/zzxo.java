package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzpq;
import com.google.android.gms.internal.p002firebaseauthapi.zzqf;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxo  reason: invalid package */
public final class zzxo implements zzcf {
    private static final byte[] zza = {0};
    private final zzru zzb;
    private final int zzc;
    private final byte[] zzd;
    private final byte[] zze;

    private zzxo(zzpj zzpj) throws GeneralSecurityException {
        this.zzb = new zzxn(zzpj.zze().zza(zzbr.zza()));
        this.zzc = zzpj.zzc().zzb();
        this.zzd = zzpj.zzd().zzb();
        if (zzpj.zzc().zze().equals(zzpq.zzb.zzc)) {
            byte[] bArr = zza;
            this.zze = Arrays.copyOf(bArr, bArr.length);
            return;
        }
        this.zze = new byte[0];
    }

    public static zzcf zza(zzpj zzpj) throws GeneralSecurityException {
        return new zzxo(zzpj);
    }

    public static zzcf zza(zzpw zzpw) throws GeneralSecurityException {
        return new zzxo(zzpw);
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!MessageDigest.isEqual(zza(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2 = this.zze;
        if (bArr2.length > 0) {
            return zzwh.zza(this.zzd, this.zzb.zza(zzwh.zza(bArr, bArr2), this.zzc));
        }
        return zzwh.zza(this.zzd, this.zzb.zza(bArr, this.zzc));
    }

    private zzxo(zzpw zzpw) throws GeneralSecurityException {
        this.zzb = new zzxm("HMAC".concat(String.valueOf(zzpw.zzc().zze())), new SecretKeySpec(zzpw.zze().zza(zzbr.zza()), "HMAC"));
        this.zzc = zzpw.zzc().zzb();
        this.zzd = zzpw.zzd().zzb();
        if (zzpw.zzc().zzf().equals(zzqf.zzc.zzc)) {
            byte[] bArr = zza;
            this.zze = Arrays.copyOf(bArr, bArr.length);
            return;
        }
        this.zze = new byte[0];
    }

    public zzxo(zzru zzru, int i3) throws GeneralSecurityException {
        this.zzb = zzru;
        this.zzc = i3;
        this.zzd = new byte[0];
        this.zze = new byte[0];
        if (i3 >= 10) {
            zzru.zza(new byte[0], i3);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }
}
