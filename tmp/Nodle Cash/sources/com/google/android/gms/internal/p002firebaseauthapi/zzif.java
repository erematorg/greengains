package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzif  reason: invalid package */
public final class zzif implements zzbh {
    private static final byte[] zza = zzxl.zza("7a806c");
    private static final byte[] zzb = zzxl.zza("46bb91c3c5");
    private static final byte[] zzc = zzxl.zza("36864200e0eaf5284d884a0e77d31646");
    private static final byte[] zzd = zzxl.zza("bae8e37fc83441b16034566b");
    private static final byte[] zze = zzxl.zza("af60eb711bd85bc1e4d3e0a462e074eea428a8");
    private static final ThreadLocal<Cipher> zzf = new zzie();
    private final SecretKey zzg;
    private final byte[] zzh;

    private zzif(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        this.zzh = bArr2;
        zzxq.zza(bArr.length);
        this.zzg = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
    }

    /* access modifiers changed from: private */
    public static boolean zzb(Cipher cipher) {
        try {
            byte[] bArr = zzd;
            cipher.init(2, new SecretKeySpec(zzc, BouncyCastleKeyManagementRepository.AES), zza(bArr, 0, bArr.length));
            cipher.updateAAD(zzb);
            byte[] bArr2 = zze;
            return MessageDigest.isEqual(cipher.doFinal(bArr2, 0, bArr2.length), zza);
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    private final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Cipher zza2 = zza();
        if (bArr.length >= 28) {
            zza2.init(2, this.zzg, zza(bArr, 0, 12));
            if (!(bArr2 == null || bArr2.length == 0)) {
                zza2.updateAAD(bArr2);
            }
            return zza2.doFinal(bArr, 12, bArr.length - 12);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    public static zzbh zza(zzed zzed) throws GeneralSecurityException {
        return new zzif(zzed.zze().zza(zzbr.zza()), zzed.zzd().zzb());
    }

    private static AlgorithmParameterSpec zza(byte[] bArr, int i3, int i4) throws GeneralSecurityException {
        return new GCMParameterSpec(128, bArr, 0, i4);
    }

    private static Cipher zza() throws GeneralSecurityException {
        Cipher cipher = zzf.get();
        if (cipher != null) {
            return cipher;
        }
        throw new GeneralSecurityException("AES GCM SIV cipher is not available or is invalid.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzh;
        if (bArr3.length == 0) {
            return zzc(bArr, bArr2);
        }
        if (zzph.zza(bArr3, bArr)) {
            return zzc(Arrays.copyOfRange(bArr, this.zzh.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Cipher zza2 = zza();
        if (bArr.length <= 2147483619) {
            byte[] bArr3 = new byte[(bArr.length + 28)];
            byte[] zza3 = zzow.zza(12);
            System.arraycopy(zza3, 0, bArr3, 0, 12);
            zza2.init(1, this.zzg, zza(zza3, 0, zza3.length));
            if (!(bArr2 == null || bArr2.length == 0)) {
                zza2.updateAAD(bArr2);
            }
            int doFinal = zza2.doFinal(bArr, 0, bArr.length, bArr3, 12);
            if (doFinal == bArr.length + 16) {
                byte[] bArr4 = this.zzh;
                if (bArr4.length == 0) {
                    return bArr3;
                }
                return zzwh.zza(bArr4, bArr3);
            }
            throw new GeneralSecurityException(C0118y.c(doFinal - bArr.length, "encryption failed; GCM tag must be 16 bytes, but got only ", " bytes"));
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
