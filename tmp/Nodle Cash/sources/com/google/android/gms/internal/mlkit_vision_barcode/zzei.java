package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

final class zzei implements Runnable {
    final Future zza;
    final zzeh zzb;

    public zzei(Future future, zzeh zzeh) {
        this.zza = future;
        this.zzb = zzeh;
    }

    public final void run() {
        boolean z2;
        Object obj;
        Throwable zza2 = zzey.zza((zzex) this.zza);
        if (zza2 == null) {
            try {
                Future future = this.zza;
                if (future.isDone()) {
                    z2 = false;
                    while (true) {
                        obj = future.get();
                        break;
                    }
                    if (z2) {
                        Thread.currentThread().interrupt();
                    }
                    this.zzb.zzb(obj);
                    return;
                }
                throw new IllegalStateException(zzba.zzb("Future was expected to be done: %s", future));
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (ExecutionException e3) {
                this.zzb.zza(e3.getCause());
            } catch (Throwable th) {
                this.zzb.zza(th);
            }
        } else {
            this.zzb.zza(zza2);
        }
    }

    public final String toString() {
        zzav zza2 = zzaw.zza(this);
        zza2.zza(this.zzb);
        return zza2.toString();
    }
}
