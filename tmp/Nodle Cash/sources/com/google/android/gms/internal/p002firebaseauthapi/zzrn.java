package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrn  reason: invalid package */
public final class zzrn {
    private HashMap<String, String> zza = new HashMap<>();

    public final zzrk zza() {
        if (this.zza != null) {
            zzrk zzrk = new zzrk(Collections.unmodifiableMap(this.zza));
            this.zza = null;
            return zzrk;
        }
        throw new IllegalStateException("cannot call build() twice");
    }
}
