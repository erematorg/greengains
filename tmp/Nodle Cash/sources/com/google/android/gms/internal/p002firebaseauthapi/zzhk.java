package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhk  reason: invalid package */
public final class zzhk {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final ThreadLocal<Cipher> zzb = new zzhn();
    private final SecretKey zzc;

    public zzhk(byte[] bArr) throws GeneralSecurityException {
        if (zza.zza()) {
            zzxq.zza(bArr.length);
            this.zzc = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }

    public static AlgorithmParameterSpec zza(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        Integer zzb2 = zzph.zzb();
        if (zzb2 == null || zzb2.intValue() > 19) {
            return new GCMParameterSpec(128, bArr, 0, length);
        }
        return new IvParameterSpec(bArr, 0, length);
    }

    public static Cipher zza() {
        return zzb.get();
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        } else if (bArr2.length >= 16) {
            AlgorithmParameterSpec zza2 = zza(bArr);
            ThreadLocal<Cipher> threadLocal = zzb;
            threadLocal.get().init(2, this.zzc, zza2);
            if (!(bArr3 == null || bArr3.length == 0)) {
                threadLocal.get().updateAAD(bArr3);
            }
            return threadLocal.get().doFinal(bArr2);
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }
}
