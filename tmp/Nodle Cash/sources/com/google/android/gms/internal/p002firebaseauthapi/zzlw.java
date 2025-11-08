package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlw  reason: invalid package */
final class zzlw implements zzlk {
    private final zzxv zza;
    private final zzxv zzb;

    private zzlw(byte[] bArr, byte[] bArr2) {
        this.zza = zzxv.zza(bArr);
        this.zzb = zzxv.zza(bArr2);
    }

    public static zzlw zza(byte[] bArr, byte[] bArr2, zzwq zzwq) throws GeneralSecurityException {
        zzwr.zza(zzwr.zza(zzwq, zzwt.UNCOMPRESSED, bArr2), zzwr.zza(zzwq, bArr));
        return new zzlw(bArr, bArr2);
    }

    public final zzxv zzb() {
        return this.zzb;
    }

    public final zzxv zza() {
        return this.zza;
    }
}
