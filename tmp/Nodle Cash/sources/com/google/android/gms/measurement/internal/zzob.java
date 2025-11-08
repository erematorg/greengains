package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

final class zzob implements Callable<String> {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zznv zzb;

    public zzob(zznv zznv, zzp zzp) {
        this.zza = zzp;
        this.zzb = zznv;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (!this.zzb.zzb((String) Preconditions.checkNotNull(this.zza.zza)).zzj() || !zzjc.zzb(this.zza.zzt).zzj()) {
            this.zzb.zzj().zzp().zza("Analytics storage consent denied. Returning null app instance id");
            return null;
        }
        zzh zza2 = this.zzb.zza(this.zza);
        if (zza2 != null) {
            return zza2.zzad();
        }
        this.zzb.zzj().zzu().zza("App info was null when attempting to get app instance id");
        return null;
    }
}
