package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvz  reason: invalid package */
public final class zzvz implements zzxk {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final ThreadLocal<Cipher> zzb = new zzwb();
    private final SecretKeySpec zzc;
    private final int zzd;
    private final int zze;

    public zzvz(byte[] bArr, int i3) throws GeneralSecurityException {
        if (zza.zza()) {
            zzxq.zza(bArr.length);
            this.zzc = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
            int blockSize = zzb.get().getBlockSize();
            this.zze = blockSize;
            if (i3 < 12 || i3 > blockSize) {
                throw new GeneralSecurityException("invalid IV size");
            }
            this.zzd = i3;
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CTR in FIPS-mode, as BoringCrypto module is not available.");
    }

    private final void zza(byte[] bArr, int i3, int i4, byte[] bArr2, int i5, byte[] bArr3, boolean z2) throws GeneralSecurityException {
        Cipher cipher = zzb.get();
        byte[] bArr4 = new byte[this.zze];
        System.arraycopy(bArr3, 0, bArr4, 0, this.zzd);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr4);
        if (z2) {
            cipher.init(1, this.zzc, ivParameterSpec);
        } else {
            cipher.init(2, this.zzc, ivParameterSpec);
        }
        if (cipher.doFinal(bArr, i3, i4, bArr2, i5) != i4) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }

    public final byte[] zzb(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i3 = this.zzd;
        if (length <= Integer.MAX_VALUE - i3) {
            byte[] bArr2 = new byte[(bArr.length + i3)];
            byte[] zza2 = zzow.zza(i3);
            System.arraycopy(zza2, 0, bArr2, 0, this.zzd);
            zza(bArr, 0, bArr.length, bArr2, this.zzd, zza2, true);
            return bArr2;
        }
        throw new GeneralSecurityException(a.k("plaintext length can not exceed ", Integer.MAX_VALUE - this.zzd));
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i3 = this.zzd;
        if (length >= i3) {
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, 0, bArr2, 0, i3);
            int length2 = bArr.length;
            int i4 = this.zzd;
            byte[] bArr3 = new byte[(length2 - i4)];
            zza(bArr, i4, bArr.length - i4, bArr3, 0, bArr2, false);
            return bArr3;
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
