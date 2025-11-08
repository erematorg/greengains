package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzx;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzhk implements Callable {
    private /* synthetic */ zzhg zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzhk(zzhg zzhg, String str) {
        this.zza = zzhg;
        this.zzb = str;
    }

    public final Object call() {
        return new zzx("internal.appMetadata", new zzhi(this.zza, this.zzb));
    }
}
