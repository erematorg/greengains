package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznj  reason: invalid package */
public final class zznj {
    private static final zznj zza = new zznj();
    private final Map<Class<? extends zzci>, zznm<? extends zzci>> zzb = new HashMap();

    public static zznj zza() {
        return zza;
    }

    public final synchronized <ParametersT extends zzci> void zza(zznm<ParametersT> zznm, Class<ParametersT> cls) throws GeneralSecurityException {
        try {
            zznm zznm2 = this.zzb.get(cls);
            if (zznm2 != null) {
                if (!zznm2.equals(zznm)) {
                    throw new GeneralSecurityException("Different key creator for parameters class already inserted");
                }
            }
            this.zzb.put(cls, zznm);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
