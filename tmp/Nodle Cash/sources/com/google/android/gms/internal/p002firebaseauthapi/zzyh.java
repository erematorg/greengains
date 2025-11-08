package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.PhoneMultiFactorInfo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyh  reason: invalid package */
public final class zzyh {
    private PhoneMultiFactorInfo zza;
    private final String zzb;
    @Nullable
    private final String zzc;
    private final long zzd;
    private final boolean zze;
    private final boolean zzf;
    @Nullable
    private final String zzg;
    @Nullable
    private final String zzh;
    private final boolean zzi;

    public zzyh(PhoneMultiFactorInfo phoneMultiFactorInfo, String str, @Nullable String str2, long j2, boolean z2, boolean z3, @Nullable String str3, @Nullable String str4, boolean z4) {
        this.zza = phoneMultiFactorInfo;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = j2;
        this.zze = z2;
        this.zzf = z3;
        this.zzg = str3;
        this.zzh = str4;
        this.zzi = z4;
    }

    public final long zza() {
        return this.zzd;
    }

    public final PhoneMultiFactorInfo zzb() {
        return this.zza;
    }

    @Nullable
    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzb;
    }

    @Nullable
    public final String zze() {
        return this.zzh;
    }

    @Nullable
    public final String zzf() {
        return this.zzg;
    }

    public final boolean zzg() {
        return this.zze;
    }

    public final boolean zzh() {
        return this.zzi;
    }
}
