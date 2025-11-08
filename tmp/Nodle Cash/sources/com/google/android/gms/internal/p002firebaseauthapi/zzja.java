package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzja  reason: invalid package */
final class zzja implements zzbq {
    private final zzoo<zzbq> zza;
    private final zzro zzb;
    private final zzro zzc;

    public zzja(zzoo<zzbq> zzoo) {
        this.zza = zzoo;
        if (zzoo.zzf()) {
            zzrp zzb2 = zznl.zza().zzb();
            zzrr zza2 = zzne.zza(zzoo);
            this.zzb = zzb2.zza(zza2, "daead", "encrypt");
            this.zzc = zzb2.zza(zza2, "daead", "decrypt");
            return;
        }
        zzro zzro = zzne.zza;
        this.zzb = zzro;
        this.zzc = zzro;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzop next : this.zza.zza(Arrays.copyOf(bArr, 5))) {
                try {
                    byte[] zza2 = ((zzbq) next.zzd()).zza(bArr, bArr2);
                    this.zzc.zza(next.zza(), (long) bArr.length);
                    return zza2;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzop next2 : this.zza.zza(zzbo.zza)) {
            try {
                byte[] zza3 = ((zzbq) next2.zzd()).zza(bArr, bArr2);
                this.zzc.zza(next2.zza(), (long) bArr.length);
                return zza3;
            } catch (GeneralSecurityException unused2) {
            }
        }
        this.zzc.zza();
        throw new GeneralSecurityException("decryption failed");
    }
}
