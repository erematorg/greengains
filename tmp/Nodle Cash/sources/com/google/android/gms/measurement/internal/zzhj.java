package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzr;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzhj implements Callable {
    private /* synthetic */ zzhg zza;

    public /* synthetic */ zzhj(zzhg zzhg) {
        this.zza = zzhg;
    }

    public final Object call() {
        return new zzr(this.zza.zzb);
    }
}
