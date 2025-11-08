package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaei  reason: invalid package */
final class zzaei extends zzagb {
    private String zza;
    private String zzb;
    private String zzc;
    private zzafb zzd;
    private String zze;

    public final zzagb zza(String str) {
        if (str != null) {
            this.zze = str;
            return this;
        }
        throw new NullPointerException("Null idToken");
    }

    public final zzagb zzb(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null providerId");
    }

    public final zzagb zzc(@Nullable String str) {
        this.zzb = str;
        return this;
    }

    public final zzagb zzd(String str) {
        if (str != null) {
            this.zzc = str;
            return this;
        }
        throw new NullPointerException("Null token");
    }

    public final zzagb zza(zzafb zzafb) {
        if (zzafb != null) {
            this.zzd = zzafb;
            return this;
        }
        throw new NullPointerException("Null tokenType");
    }

    public final zzafy zza() {
        String str;
        zzafb zzafb;
        String str2;
        String str3 = this.zza;
        if (str3 != null && (str = this.zzc) != null && (zzafb = this.zzd) != null && (str2 = this.zze) != null) {
            return new zzaej(str3, this.zzb, str, zzafb, str2);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" providerId");
        }
        if (this.zzc == null) {
            sb.append(" token");
        }
        if (this.zzd == null) {
            sb.append(" tokenType");
        }
        if (this.zze == null) {
            sb.append(" idToken");
        }
        throw new IllegalStateException("Missing required properties:".concat(String.valueOf(sb)));
    }
}
