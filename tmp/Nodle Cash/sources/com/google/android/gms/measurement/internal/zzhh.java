package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzm;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzhh implements Callable {
    private /* synthetic */ zzhg zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzhh(zzhg zzhg, String str) {
        this.zza = zzhg;
        this.zzb = str;
    }

    public final Object call() {
        return new zzm("internal.remoteConfig", new zzho(this.zza, this.zzb));
    }
}
