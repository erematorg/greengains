package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznl  reason: invalid package */
public final class zznl {
    private static final zznl zza = new zznl();
    private static final zzno zzb = new zzno();
    private final AtomicReference<zzrp> zzc = new AtomicReference<>();

    public static zznl zza() {
        return zza;
    }

    public final zzrp zzb() {
        zzrp zzrp = this.zzc.get();
        return zzrp == null ? zzb : zzrp;
    }
}
