package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkm  reason: invalid package */
final class zzkm implements zzbp {
    private final zzoo<zzbp> zza;
    private final zzro zzb;

    public zzkm(zzoo<zzbp> zzoo) {
        this.zza = zzoo;
        if (zzoo.zzf()) {
            this.zzb = zznl.zza().zzb().zza(zzne.zza(zzoo), "hybrid_decrypt", "decrypt");
        } else {
            this.zzb = zzne.zza;
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzop next : this.zza.zza(Arrays.copyOfRange(bArr, 0, 5))) {
                try {
                    byte[] zza2 = ((zzbp) next.zzd()).zza(bArr, bArr2);
                    this.zzb.zza(next.zza(), (long) bArr.length);
                    return zza2;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzop next2 : this.zza.zza(zzbo.zza)) {
            try {
                byte[] zza3 = ((zzbp) next2.zzd()).zza(bArr, bArr2);
                this.zzb.zza(next2.zza(), (long) bArr.length);
                return zza3;
            } catch (GeneralSecurityException unused2) {
            }
        }
        this.zzb.zza();
        throw new GeneralSecurityException("decryption failed");
    }
}
