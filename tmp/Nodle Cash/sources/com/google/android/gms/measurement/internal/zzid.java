package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzid implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzia zzb;

    public zzid(zzia zzia, zzp zzp) {
        this.zza = zzp;
        this.zzb = zzia;
    }

    public final void run() {
        this.zzb.zza.zzr();
        zznv zza2 = this.zzb.zza;
        zzp zzp = this.zza;
        zza2.zzl().zzt();
        zza2.zzs();
        Preconditions.checkNotEmpty(zzp.zza);
        zza2.zza(zzp);
    }
}
