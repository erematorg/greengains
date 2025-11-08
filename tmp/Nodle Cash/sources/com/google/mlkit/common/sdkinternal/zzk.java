package com.google.mlkit.common.sdkinternal;

public final /* synthetic */ class zzk implements Runnable {
    public final /* synthetic */ Runnable zza;

    public /* synthetic */ zzk(Runnable runnable) {
        this.zza = runnable;
    }

    public final void run() {
        MlKitThreadPool.zzd(this.zza);
    }
}
