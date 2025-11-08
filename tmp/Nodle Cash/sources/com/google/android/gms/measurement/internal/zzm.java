package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdl;

final class zzm implements Runnable {
    private final /* synthetic */ zzdl zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    public zzm(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdl zzdl) {
        this.zza = zzdl;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzt().zza(this.zza, this.zzb.zza.zzab());
    }
}
