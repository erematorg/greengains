package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdl;

final class zzi implements Runnable {
    private final /* synthetic */ zzdl zza;
    private final /* synthetic */ zzbh zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    public zzi(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdl zzdl, zzbh zzbh, String str) {
        this.zza = zzdl;
        this.zzb = zzbh;
        this.zzc = str;
        this.zzd = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzd.zza.zzr().zza(this.zza, this.zzb, this.zzc);
    }
}
