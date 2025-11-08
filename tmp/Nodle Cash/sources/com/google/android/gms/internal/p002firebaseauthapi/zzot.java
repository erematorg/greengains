package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzot  reason: invalid package */
public final class zzot implements zzox {
    private final zzxv zza;
    private final zzvc zzb;

    private zzot(zzvc zzvc, zzxv zzxv) {
        this.zzb = zzvc;
        this.zza = zzxv;
    }

    public static zzot zza(zzvc zzvc) throws GeneralSecurityException {
        return new zzot(zzvc, zzph.zza(zzvc.zzf()));
    }

    public static zzot zzb(zzvc zzvc) {
        return new zzot(zzvc, zzph.zzb(zzvc.zzf()));
    }

    public final zzvc zza() {
        return this.zzb;
    }

    public final zzxv zzb() {
        return this.zza;
    }
}
