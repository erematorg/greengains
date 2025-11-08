package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcp  reason: invalid package */
public final class zzcp {
    public static zzci zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzvc zza = zzvc.zza(bArr, zzaiq.zza());
            zzns zza2 = zzns.zza();
            zzot zza3 = zzot.zza(zza);
            if (!zza2.zzc(zza3)) {
                return new zznc(zza3);
            }
            return zza2.zza(zza3);
        } catch (IOException e3) {
            throw new GeneralSecurityException("Failed to parse proto", e3);
        }
    }

    public static byte[] zza(zzci zzci) throws GeneralSecurityException {
        if (zzci instanceof zznc) {
            return ((zznc) zzci).zzb().zza().a_();
        }
        return ((zzot) zzns.zza().zza(zzci, zzot.class)).zza().a_();
    }
}
