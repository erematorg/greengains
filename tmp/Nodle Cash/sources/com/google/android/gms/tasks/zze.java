package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zze implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzf zzb;

    public zze(zzf zzf, Task task) {
        this.zzb = zzf;
        this.zza = task;
    }

    public final void run() {
        try {
            Task task = (Task) this.zzb.zzb.then(this.zza);
            if (task == null) {
                this.zzb.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            zzf zzf = this.zzb;
            Executor executor = TaskExecutors.zza;
            task.addOnSuccessListener(executor, zzf);
            task.addOnFailureListener(executor, (OnFailureListener) this.zzb);
            task.addOnCanceledListener(executor, (OnCanceledListener) this.zzb);
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
