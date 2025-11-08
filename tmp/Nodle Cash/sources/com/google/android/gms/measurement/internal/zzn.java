package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdl;

final class zzn implements Runnable {
    private final /* synthetic */ zzdl zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    public zzn(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdl zzdl, String str, String str2) {
        this.zza = zzdl;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzd.zza.zzr().zza(this.zza, this.zzb, this.zzc);
    }
}
