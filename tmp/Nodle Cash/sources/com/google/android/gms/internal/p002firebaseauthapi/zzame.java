package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzame  reason: invalid package */
public final class zzame extends RuntimeException {
    private final List<String> zza = null;

    public zzame(zzakp zzakp) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzajk zza() {
        return new zzajk(getMessage());
    }
}
