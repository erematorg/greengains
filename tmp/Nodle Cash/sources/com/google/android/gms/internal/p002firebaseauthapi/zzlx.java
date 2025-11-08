package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlx  reason: invalid package */
public final class zzlx {
    private final zzbh zza;
    private final zzbq zzb;

    public zzlx(zzbh zzbh) {
        this.zza = zzbh;
        this.zzb = null;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzbh zzbh = this.zza;
        return zzbh != null ? zzbh.zza(bArr, bArr2) : this.zzb.zza(bArr, bArr2);
    }

    public zzlx(zzbq zzbq) {
        this.zza = null;
        this.zzb = zzbq;
    }
}
