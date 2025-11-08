package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.Provider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwu  reason: invalid package */
final class zzwu<JcePrimitiveT> implements zzxa<JcePrimitiveT> {
    private final zzxd<JcePrimitiveT> zza;

    public final JcePrimitiveT zza(String str) throws GeneralSecurityException {
        Exception exc = null;
        for (Provider zza2 : zzwv.zza(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL")) {
            try {
                return this.zza.zza(str, zza2);
            } catch (Exception e3) {
                if (exc == null) {
                    exc = e3;
                }
            }
        }
        return this.zza.zza(str, (Provider) null);
    }

    private zzwu(zzxd<JcePrimitiveT> zzxd) {
        this.zza = zzxd;
    }
}
