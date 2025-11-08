package com.google.android.gms.measurement.internal;

final class zzks implements Runnable {
    private final /* synthetic */ Boolean zza;
    private final /* synthetic */ zzjk zzb;

    public zzks(zzjk zzjk, Boolean bool) {
        this.zza = bool;
        this.zzb = zzjk;
    }

    public final void run() {
        this.zzb.zza(this.zza, true);
    }
}
