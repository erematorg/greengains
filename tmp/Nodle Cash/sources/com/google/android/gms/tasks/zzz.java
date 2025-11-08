package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzz implements Runnable {
    final /* synthetic */ zzw zza;
    final /* synthetic */ Callable zzb;

    public zzz(zzw zzw, Callable callable) {
        this.zza = zzw;
        this.zzb = callable;
    }

    public final void run() {
        try {
            this.zza.zzb(this.zzb.call());
        } catch (Exception e3) {
            this.zza.zza(e3);
        } catch (Throwable th) {
            this.zza.zza(new RuntimeException(th));
        }
    }
}
