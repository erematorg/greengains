package com.google.firebase.auth.internal;

import androidx.annotation.Nullable;
import com.google.firebase.auth.FirebaseAuthSettings;

public final class zzz extends FirebaseAuthSettings {
    private String zza;
    private String zzb;
    private boolean zzc = false;
    private boolean zzd = false;

    public final void forceRecaptchaFlowForTesting(boolean z2) {
        this.zzd = z2;
    }

    public final void setAppVerificationDisabledForTesting(boolean z2) {
        this.zzc = z2;
    }

    public final void setAutoRetrievedSmsCodeForPhoneNumber(@Nullable String str, @Nullable String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Nullable
    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zzd;
    }

    public final boolean zzd() {
        return (this.zza == null || this.zzb == null) ? false : true;
    }

    public final boolean zze() {
        return this.zzc;
    }
}
