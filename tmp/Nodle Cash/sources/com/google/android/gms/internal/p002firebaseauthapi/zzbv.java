package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbv  reason: invalid package */
public final class zzbv {
    @Nullable
    private final zzvc zza = null;
    @Nullable
    private final zzci zzb;

    private zzbv(zzci zzci) {
        this.zzb = zzci;
    }

    public static zzbv zza(zzci zzci) throws GeneralSecurityException {
        return new zzbv(zzci);
    }

    public final zzvc zza() throws GeneralSecurityException {
        zzci zzci = this.zzb;
        if (zzci instanceof zznc) {
            return ((zznc) zzci).zzb().zza();
        }
        return ((zzot) zzns.zza().zza(this.zzb, zzot.class)).zza();
    }
}
