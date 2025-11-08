package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
import javax.crypto.Cipher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhc  reason: invalid package */
final class zzhc extends ThreadLocal<Cipher> {
    @Nullable
    private static Cipher zza() {
        try {
            Cipher zza = zzwv.zza.zza("ChaCha20-Poly1305");
            if (!zzhd.zzb(zza)) {
                return null;
            }
            return zza;
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    @Nullable
    public final /* synthetic */ Object initialValue() {
        return zza();
    }
}
