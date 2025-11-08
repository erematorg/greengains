package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzie  reason: invalid package */
final class zzie extends ThreadLocal<Cipher> {
    private static Cipher zza() {
        try {
            Cipher zza = zzwv.zza.zza("AES/GCM-SIV/NoPadding");
            if (!zzif.zzb(zza)) {
                return null;
            }
            return zza;
        } catch (GeneralSecurityException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public final /* synthetic */ Object initialValue() {
        return zza();
    }
}
