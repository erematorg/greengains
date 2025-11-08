package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwn  reason: invalid package */
final class zzwn implements zzwj {
    private final /* synthetic */ zzdg zza;

    public zzwn(zzdg zzdg) {
        this.zza = zzdg;
    }

    public final int zza() {
        return this.zza.zzc() + this.zza.zzb();
    }

    public final zzlx zza(byte[] bArr) throws GeneralSecurityException {
        return new zzlx(zzws.zza(zzcz.zzb().zza(this.zza).zza(zzxw.zza(Arrays.copyOfRange(bArr, 0, this.zza.zzb()), zzbr.zza())).zzb(zzxw.zza(Arrays.copyOfRange(bArr, this.zza.zzb(), this.zza.zzc() + this.zza.zzb()), zzbr.zza())).zza()));
    }
}
