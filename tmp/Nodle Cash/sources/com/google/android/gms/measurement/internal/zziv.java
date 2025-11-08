package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zziv implements Callable<List<zzom>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzia zzb;

    public zziv(zzia zzia, String str) {
        this.zza = str;
        this.zzb = zzia;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzr();
        return this.zzb.zza.zzf().zzl(this.zza);
    }
}
