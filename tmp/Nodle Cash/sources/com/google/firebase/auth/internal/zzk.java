package com.google.firebase.auth.internal;

import A.a;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;
import org.apache.commons.text.StringSubstitutor;

final class zzk extends zzg {
    @Nullable
    private final String zza;
    @Nullable
    private final String zzb;
    @Nullable
    private final String zzc;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzg) {
            zzg zzg = (zzg) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzg.zzc()) : zzg.zzc() == null) {
                String str2 = this.zzb;
                if (str2 != null ? str2.equals(zzg.zza()) : zzg.zza() == null) {
                    String str3 = this.zzc;
                    return str3 != null ? str3.equals(zzg.zzb()) : zzg.zzb() == null;
                }
            }
        }
    }

    public final int hashCode() {
        String str = this.zza;
        int i3 = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.zzb;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zzc;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        return hashCode2 ^ i3;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        return a.n(C0118y.l("AttestationResult{recaptchaV2Token=", str, ", playIntegrityToken=", str2, ", recaptchaEnterpriseToken="), this.zzc, StringSubstitutor.DEFAULT_VAR_END);
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }

    @Nullable
    public final String zzb() {
        return this.zzc;
    }

    @Nullable
    public final String zzc() {
        return this.zza;
    }

    private zzk(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = null;
    }
}
