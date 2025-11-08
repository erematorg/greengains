package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.constraintlayout.core.state.b;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacn  reason: invalid package */
public class zzacn {
    final zzacv zza;
    private final String zzb;

    public zzacn(String str, zzacv zzacv) {
        this.zzb = str;
        this.zza = zzacv;
    }

    public final String zza(String str, String str2) {
        return b.m(this.zzb, str, "?key=", str2);
    }
}
