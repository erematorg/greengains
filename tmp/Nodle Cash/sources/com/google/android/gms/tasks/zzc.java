package com.google.android.gms.tasks;

final class zzc implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzd zzb;

    public zzc(zzd zzd, Task task) {
        this.zzb = zzd;
        this.zza = task;
    }

    public final void run() {
        if (this.zza.isCanceled()) {
            this.zzb.zzc.zzc();
            return;
        }
        try {
            this.zzb.zzc.zzb(this.zzb.zzb.then(this.zza));
        } catch (RuntimeExecutionException e3) {
            if (e3.getCause() instanceof Exception) {
                this.zzb.zzc.zza((Exception) e3.getCause());
            } else {
                this.zzb.zzc.zza(e3);
            }
        } catch (Exception e4) {
            this.zzb.zzc.zza(e4);
        }
    }
}
