package com.google.android.gms.measurement.internal;

import java.util.concurrent.Executor;

final class zzjw implements Executor {
    private final /* synthetic */ zzjk zza;

    public zzjw(zzjk zzjk) {
        this.zza = zzjk;
    }

    public final void execute(Runnable runnable) {
        this.zza.zzl().zzb(runnable);
    }
}
