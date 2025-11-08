package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;

public final class zzgm {
    @NonNull
    public String zza;
    @NonNull
    public String zzb;
    public long zzc;
    @NonNull
    public Bundle zzd;

    public zzgm(@NonNull String str, @NonNull String str2, @Nullable Bundle bundle, long j2) {
        this.zza = str;
        this.zzb = str2;
        this.zzd = bundle == null ? new Bundle() : bundle;
        this.zzc = j2;
    }

    public final String toString() {
        String str = this.zzb;
        String str2 = this.zza;
        String valueOf = String.valueOf(this.zzd);
        StringBuilder l2 = C0118y.l("origin=", str, ",name=", str2, ",params=");
        l2.append(valueOf);
        return l2.toString();
    }

    public final zzbh zza() {
        return new zzbh(this.zza, new zzbc(new Bundle(this.zzd)), this.zzb, this.zzc);
    }

    public static zzgm zza(zzbh zzbh) {
        return new zzgm(zzbh.zza, zzbh.zzc, zzbh.zzb.zzb(), zzbh.zzd);
    }
}
