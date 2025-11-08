package com.google.android.gms.measurement.internal;

public final /* synthetic */ class zzju implements Runnable {
    private /* synthetic */ zzjk zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzju(zzjk zzjk, String str) {
        this.zza = zzjk;
        this.zzb = str;
    }

    public final void run() {
        this.zza.zzb(this.zzb);
    }
}
