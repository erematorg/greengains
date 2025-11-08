package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.EmailAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyf  reason: invalid package */
public final class zzyf {
    private final EmailAuthCredential zza;
    @Nullable
    private final String zzb;

    public zzyf(EmailAuthCredential emailAuthCredential, @Nullable String str) {
        this.zza = emailAuthCredential;
        this.zzb = str;
    }

    public final EmailAuthCredential zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }
}
