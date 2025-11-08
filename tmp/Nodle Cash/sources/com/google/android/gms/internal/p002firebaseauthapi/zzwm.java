package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwm  reason: invalid package */
final class zzwm implements zzwj {
    private final /* synthetic */ zzis zza;

    public zzwm(zzis zzis) {
        this.zza = zzis;
    }

    public final int zza() {
        return this.zza.zzb();
    }

    public final zzlx zza(byte[] bArr) throws GeneralSecurityException {
        return new zzlx(zzwe.zza(zzil.zzb().zza(this.zza).zza(zzxw.zza(bArr, zzbr.zza())).zza()));
    }
}
