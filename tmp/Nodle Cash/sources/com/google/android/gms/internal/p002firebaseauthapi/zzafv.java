package com.google.android.gms.internal.p002firebaseauthapi;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafv  reason: invalid package */
public final class zzafv {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;

    public zzafv() {
    }

    @Nullable
    public final Uri zza() {
        if (!TextUtils.isEmpty(this.zzc)) {
            return Uri.parse(this.zzc);
        }
        return null;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final String zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zza;
    }

    public final String zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zzd;
    }

    @Nullable
    public final String zzg() {
        return this.zze;
    }

    public zzafv(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = null;
        this.zzf = str6;
        this.zzg = str7;
    }

    public final void zza(String str) {
        this.zze = str;
    }
}
