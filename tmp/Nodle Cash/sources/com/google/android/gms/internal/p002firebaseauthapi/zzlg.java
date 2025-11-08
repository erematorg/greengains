package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjv;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlg  reason: invalid package */
public final class zzlg implements zzbp {
    private static final byte[] zza = new byte[0];
    private final zzlk zzb;
    private final zzlh zzc;
    private final zzli zzd;
    private final zzle zze;
    private final int zzf;
    private final byte[] zzg;

    private zzlg(zzlk zzlk, zzlh zzlh, zzli zzli, zzle zzle, int i3, zzxv zzxv) {
        this.zzb = zzlk;
        this.zzc = zzlh;
        this.zzd = zzli;
        this.zze = zzle;
        this.zzf = i3;
        this.zzg = zzxv.zzb();
    }

    public static zzbp zza(zzka zzka) throws GeneralSecurityException {
        int i3;
        zzwq zzwq;
        zzlk zza2;
        zzjv zzc2 = zzka.zzc();
        zzlh zza3 = zzlf.zza(zzc2.zze());
        zzli zza4 = zzlf.zza(zzc2.zzd());
        zzle zza5 = zzlf.zza(zzc2.zzb());
        zzjv.zzd zze2 = zzc2.zze();
        zzjv.zzd zzd2 = zzjv.zzd.zzd;
        if (zze2.equals(zzd2)) {
            i3 = 32;
        } else if (zze2.equals(zzjv.zzd.zza)) {
            i3 = 65;
        } else if (zze2.equals(zzjv.zzd.zzb)) {
            i3 = 97;
        } else if (zze2.equals(zzjv.zzd.zzc)) {
            i3 = 133;
        } else {
            throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
        int i4 = i3;
        zzjv.zzd zze3 = zzka.zzc().zze();
        if (zze3.equals(zzd2)) {
            zza2 = zzly.zza(zzka.zze().zza(zzbr.zza()));
        } else {
            zzjv.zzd zzd3 = zzjv.zzd.zza;
            if (zze3.equals(zzd3) || zze3.equals(zzjv.zzd.zzb) || zze3.equals(zzjv.zzd.zzc)) {
                byte[] zza6 = zzka.zze().zza(zzbr.zza());
                byte[] zzb2 = ((zzki) ((zzkr) zzka.zzb())).zzd().zzb();
                if (zze3.equals(zzd3)) {
                    zzwq = zzwq.NIST_P256;
                } else if (zze3.equals(zzjv.zzd.zzb)) {
                    zzwq = zzwq.NIST_P384;
                } else if (zze3.equals(zzjv.zzd.zzc)) {
                    zzwq = zzwq.NIST_P521;
                } else {
                    throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
                }
                zza2 = zzlw.zza(zza6, zzb2, zzwq);
            } else {
                throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
            }
        }
        return new zzlg(zza2, zza3, zza4, zza5, i4, zzka.zzg());
    }

    private final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i3 = this.zzf;
        if (length >= i3) {
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr3 = bArr2;
            byte[] copyOf = Arrays.copyOf(bArr, i3);
            return zzld.zza(copyOf, this.zzb, this.zzc, this.zzd, this.zze, bArr3).zza(Arrays.copyOfRange(bArr, this.zzf, bArr.length), zza);
        }
        throw new GeneralSecurityException("Ciphertext is too short.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzg;
        if (bArr3.length == 0) {
            return zzb(bArr, bArr2);
        }
        if (zzph.zza(bArr3, bArr)) {
            return zzb(Arrays.copyOfRange(bArr, this.zzg.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Invalid ciphertext (output prefix mismatch)");
    }
}
