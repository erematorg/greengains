package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzna  reason: invalid package */
public final class zzna extends zzbu {
    private final zzou zza;

    public zzna(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        zza(zzou, zzcn);
        this.zza = zzou;
    }

    public final zzou zza(@Nullable zzcn zzcn) throws GeneralSecurityException {
        zza(this.zza, zzcn);
        return this.zza;
    }

    @Nullable
    public final Integer zza() {
        return this.zza.zze();
    }

    private static void zza(zzou zzou, @Nullable zzcn zzcn) throws GeneralSecurityException {
        int i3 = zzmz.zza[zzou.zza().ordinal()];
        if (i3 == 1 || i3 == 2) {
            zzcn.zza(zzcn);
        }
    }
}
