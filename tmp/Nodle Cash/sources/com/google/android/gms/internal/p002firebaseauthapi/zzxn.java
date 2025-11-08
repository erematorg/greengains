package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxn  reason: invalid package */
public final class zzxn implements zzru {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_NOT_FIPS;
    private final SecretKey zzb;
    private byte[] zzc;
    private byte[] zzd;

    public zzxn(byte[] bArr) throws GeneralSecurityException {
        zzxq.zza(bArr.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
        this.zzb = secretKeySpec;
        Cipher zza2 = zza();
        zza2.init(1, secretKeySpec);
        byte[] zzb2 = zzra.zzb(zza2.doFinal(new byte[16]));
        this.zzc = zzb2;
        this.zzd = zzra.zzb(zzb2);
    }

    private static Cipher zza() throws GeneralSecurityException {
        if (zza.zza()) {
            return zzwv.zza.zza("AES/ECB/NoPadding");
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }

    public final byte[] zza(byte[] bArr, int i3) throws GeneralSecurityException {
        byte[] bArr2;
        if (i3 <= 16) {
            Cipher zza2 = zza();
            zza2.init(1, this.zzb);
            int max = Math.max(1, (int) Math.ceil(((double) bArr.length) / 16.0d));
            if ((max << 4) == bArr.length) {
                bArr2 = zzwh.zza(bArr, (max - 1) << 4, this.zzc, 0, 16);
            } else {
                bArr2 = zzwh.zza(zzra.zza(Arrays.copyOfRange(bArr, (max - 1) << 4, bArr.length)), this.zzd);
            }
            byte[] bArr3 = new byte[16];
            for (int i4 = 0; i4 < max - 1; i4++) {
                bArr3 = zza2.doFinal(zzwh.zza(bArr3, 0, bArr, i4 << 4, 16));
            }
            return Arrays.copyOf(zza2.doFinal(zzwh.zza(bArr2, bArr3)), i3);
        }
        throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
    }
}
