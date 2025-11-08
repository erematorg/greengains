package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwf  reason: invalid package */
public final class zzwf implements zzbh {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final SecretKey zzb;
    private final byte[] zzc;

    private zzwf(byte[] bArr, zzxv zzxv) throws GeneralSecurityException {
        if (zza.zza()) {
            zzxq.zza(bArr.length);
            this.zzb = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
            this.zzc = zzxv.zzb();
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }

    public static zzbh zza(zzds zzds) throws GeneralSecurityException {
        if (zzds.zzc().zzb() != 12) {
            throw new GeneralSecurityException(a.k("Expected IV Size 12, got ", zzds.zzc().zzb()));
        } else if (zzds.zzc().zzd() == 16) {
            return new zzwf(zzds.zze().zza(zzbr.zza()), zzds.zzd());
        } else {
            throw new GeneralSecurityException(a.k("Expected tag Size 16, got ", zzds.zzc().zzd()));
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            byte[] zza2 = zzow.zza(12);
            AlgorithmParameterSpec zza3 = zzhk.zza(zza2);
            Cipher zza4 = zzhk.zza();
            zza4.init(1, this.zzb, zza3);
            if (!(bArr2 == null || bArr2.length == 0)) {
                zza4.updateAAD(bArr2);
            }
            int outputSize = zza4.getOutputSize(bArr.length);
            byte[] bArr3 = this.zzc;
            if (outputSize <= 2147483635 - bArr3.length) {
                byte[] copyOf = Arrays.copyOf(bArr3, bArr3.length + 12 + outputSize);
                System.arraycopy(zza2, 0, copyOf, this.zzc.length, 12);
                if (zza4.doFinal(bArr, 0, bArr.length, copyOf, this.zzc.length + 12) == outputSize) {
                    return copyOf;
                }
                throw new GeneralSecurityException("not enough data written");
            }
            throw new GeneralSecurityException("plaintext too long");
        }
        throw new NullPointerException("plaintext is null");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr3 = this.zzc;
            if (length < bArr3.length + 28) {
                throw new GeneralSecurityException("ciphertext too short");
            } else if (zzph.zza(bArr3, bArr)) {
                byte[] bArr4 = new byte[12];
                System.arraycopy(bArr, this.zzc.length, bArr4, 0, 12);
                AlgorithmParameterSpec zza2 = zzhk.zza(bArr4);
                Cipher zza3 = zzhk.zza();
                zza3.init(2, this.zzb, zza2);
                if (!(bArr2 == null || bArr2.length == 0)) {
                    zza3.updateAAD(bArr2);
                }
                byte[] bArr5 = this.zzc;
                return zza3.doFinal(bArr, bArr5.length + 12, (bArr.length - bArr5.length) - 12);
            } else {
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
        } else {
            throw new NullPointerException("ciphertext is null");
        }
    }
}
