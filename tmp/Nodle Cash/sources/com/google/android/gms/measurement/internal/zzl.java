package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdl;

final class zzl implements Runnable {
    private final /* synthetic */ zzdl zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ AppMeasurementDynamiteService zze;

    public zzl(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdl zzdl, String str, String str2, boolean z2) {
        this.zza = zzdl;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = z2;
        this.zze = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zze.zza.zzr().zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
