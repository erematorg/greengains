package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;
import org.apache.commons.text.StringSubstitutor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeg  reason: invalid package */
final class zzaeg extends zzafx {
    @Nullable
    private final String zza;
    @Nullable
    private final String zzb;

    public zzaeg(@Nullable String str, @Nullable String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzafx) {
            zzafx zzafx = (zzafx) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzafx.zzb()) : zzafx.zzb() == null) {
                String str2 = this.zzb;
                return str2 != null ? str2.equals(zzafx.zza()) : zzafx.zza() == null;
            }
        }
    }

    public final int hashCode() {
        String str = this.zza;
        int i3 = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.zzb;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode ^ i3;
    }

    public final String toString() {
        return C0118y.g("RecaptchaEnforcementState{provider=", this.zza, ", enforcementState=", this.zzb, StringSubstitutor.DEFAULT_VAR_END);
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }

    @Nullable
    public final String zzb() {
        return this.zza;
    }
}
