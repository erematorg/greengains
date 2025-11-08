package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwa  reason: invalid package */
public final class zzwa implements zzbh {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_NOT_FIPS;
    private static final ThreadLocal<Cipher> zzb = new zzwd();
    private static final ThreadLocal<Cipher> zzc = new zzwc();
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private final SecretKeySpec zzg;
    private final int zzh;

    private zzwa(byte[] bArr, int i3, byte[] bArr2) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        } else if (i3 == 12 || i3 == 16) {
            this.zzh = i3;
            zzxq.zza(bArr.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
            this.zzg = secretKeySpec;
            Cipher cipher = zzb.get();
            cipher.init(1, secretKeySpec);
            byte[] zza2 = zza(cipher.doFinal(new byte[16]));
            this.zzd = zza2;
            this.zze = zza(zza2);
            this.zzf = bArr2;
        } else {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
    }

    public static zzbh zza(zzdj zzdj) throws GeneralSecurityException {
        if (!zza.zza()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        } else if (zzdj.zzc().zzd() == 16) {
            return new zzwa(zzdj.zze().zza(zzbr.zza()), zzdj.zzc().zzb(), zzdj.zzd().zzb());
        } else {
            throw new GeneralSecurityException(a.k("AesEaxJce only supports 16 byte tag size, not ", zzdj.zzc().zzd()));
        }
    }

    private final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = (bArr.length - this.zzh) - 16;
        if (length >= 0) {
            Cipher cipher = zzb.get();
            cipher.init(1, this.zzg);
            byte[] zza2 = zza(cipher, 0, bArr, 0, this.zzh);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr3 = bArr2;
            byte[] zza3 = zza(cipher, 1, bArr3, 0, bArr3.length);
            byte[] zza4 = zza(cipher, 2, bArr, this.zzh, length);
            int length2 = bArr.length - 16;
            byte b3 = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                b3 = (byte) (b3 | (((bArr[length2 + i3] ^ zza3[i3]) ^ zza2[i3]) ^ zza4[i3]));
            }
            if (b3 == 0) {
                Cipher cipher2 = zzc.get();
                cipher2.init(1, this.zzg, new IvParameterSpec(zza2));
                return cipher2.doFinal(bArr, this.zzh, length);
            }
            throw new AEADBadTagException("tag mismatch");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    private static byte[] zzd(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr3[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
        }
        return bArr3;
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = bArr;
        int length = bArr3.length;
        int i3 = this.zzh;
        if (length <= 2147483631 - i3) {
            byte[] bArr4 = new byte[(bArr3.length + i3 + 16)];
            byte[] zza2 = zzow.zza(i3);
            System.arraycopy(zza2, 0, bArr4, 0, this.zzh);
            Cipher cipher = zzb.get();
            cipher.init(1, this.zzg);
            byte[] zza3 = zza(cipher, 0, zza2, 0, zza2.length);
            byte[] bArr5 = bArr2 == null ? new byte[0] : bArr2;
            byte[] zza4 = zza(cipher, 1, bArr5, 0, bArr5.length);
            Cipher cipher2 = zzc.get();
            cipher2.init(1, this.zzg, new IvParameterSpec(zza3));
            cipher2.doFinal(bArr, 0, bArr3.length, bArr4, this.zzh);
            byte[] zza5 = zza(cipher, 2, bArr4, this.zzh, bArr3.length);
            int length2 = bArr3.length + this.zzh;
            for (int i4 = 0; i4 < 16; i4++) {
                bArr4[length2 + i4] = (byte) ((zza4[i4] ^ zza3[i4]) ^ zza5[i4]);
            }
            byte[] bArr6 = this.zzf;
            return bArr6.length == 0 ? bArr4 : zzwh.zza(bArr6, bArr4);
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzf;
        if (bArr3.length == 0) {
            return zzc(bArr, bArr2);
        }
        if (zzph.zza(bArr3, bArr)) {
            return zzc(Arrays.copyOfRange(bArr, this.zzf.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
    }

    private static byte[] zza(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i3 = 0;
        while (i3 < 15) {
            int i4 = i3 + 1;
            bArr2[i3] = (byte) ((bArr[i3] << 1) ^ ((bArr[i4] & 255) >>> 7));
            i3 = i4;
        }
        bArr2[15] = (byte) (((bArr[0] >> 7) & 135) ^ (bArr[15] << 1));
        return bArr2;
    }

    private final byte[] zza(Cipher cipher, int i3, byte[] bArr, int i4, int i5) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2;
        byte[] bArr3 = new byte[16];
        bArr3[15] = (byte) i3;
        if (i5 == 0) {
            return cipher.doFinal(zzd(bArr3, this.zzd));
        }
        byte[] doFinal = cipher.doFinal(bArr3);
        int i6 = 0;
        while (i5 - i6 > 16) {
            for (int i7 = 0; i7 < 16; i7++) {
                doFinal[i7] = (byte) (doFinal[i7] ^ bArr[(i4 + i6) + i7]);
            }
            doFinal = cipher.doFinal(doFinal);
            i6 += 16;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i6 + i4, i4 + i5);
        if (copyOfRange.length == 16) {
            bArr2 = zzd(copyOfRange, this.zzd);
        } else {
            bArr2 = Arrays.copyOf(this.zze, 16);
            for (int i8 = 0; i8 < copyOfRange.length; i8++) {
                bArr2[i8] = (byte) (bArr2[i8] ^ copyOfRange[i8]);
            }
            bArr2[copyOfRange.length] = (byte) (bArr2[copyOfRange.length] ^ 128);
        }
        return cipher.doFinal(zzd(doFinal, bArr2));
    }
}
