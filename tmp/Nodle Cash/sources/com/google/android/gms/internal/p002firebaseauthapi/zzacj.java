package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacj  reason: invalid package */
public final class zzacj {
    @Nullable
    private String zza;
    @Nullable
    private String zzb;

    private zzacj() {
    }

    public static zzacj zza(String str) {
        zzacj zzacj = new zzacj();
        zzacj.zza = str;
        return zzacj;
    }

    public static zzacj zzb(String str) {
        zzacj zzacj = new zzacj();
        zzacj.zzb = str;
        return zzacj;
    }

    @Nullable
    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }
}
