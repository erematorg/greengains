package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwd  reason: invalid package */
final class zzwd extends ThreadLocal<Cipher> {
    private static Cipher zza() {
        try {
            return zzwv.zza.zza("AES/ECB/NOPADDING");
        } catch (GeneralSecurityException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public final /* synthetic */ Object initialValue() {
        return zza();
    }
}
