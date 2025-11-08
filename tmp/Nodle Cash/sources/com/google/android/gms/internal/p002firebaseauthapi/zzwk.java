package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwk  reason: invalid package */
final class zzwk implements zzwj {
    private final /* synthetic */ zzdz zza;

    public zzwk(zzdz zzdz) {
        this.zza = zzdz;
    }

    public final int zza() {
        return this.zza.zzc();
    }

    public final zzlx zza(byte[] bArr) throws GeneralSecurityException {
        return new zzlx(zzwf.zza(zzds.zzb().zza(this.zza).zza(zzxw.zza(bArr, zzbr.zza())).zza()));
    }
}
