package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.AppMeasurementDynamiteService;

final class zzk implements Runnable {
    private final /* synthetic */ AppMeasurementDynamiteService.zzb zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    public zzk(AppMeasurementDynamiteService appMeasurementDynamiteService, AppMeasurementDynamiteService.zzb zzb2) {
        this.zza = zzb2;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzp().zza((zzjg) this.zza);
    }
}
