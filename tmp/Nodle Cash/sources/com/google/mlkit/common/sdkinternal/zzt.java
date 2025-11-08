package com.google.mlkit.common.sdkinternal;

public final /* synthetic */ class zzt implements Runnable {
    public final /* synthetic */ TaskQueue zza;
    public final /* synthetic */ Runnable zzb;

    public /* synthetic */ zzt(TaskQueue taskQueue, Runnable runnable) {
        this.zza = taskQueue;
        this.zzb = runnable;
    }

    public final void run() {
        zzx zzx = new zzx(this.zza, (zzw) null);
        try {
            this.zzb.run();
            zzx.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
