package com.google.mlkit.common.sdkinternal;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@KeepForSdk
public class TaskQueue {
    private final Object zza = new Object();
    @GuardedBy("lock")
    private boolean zzb;
    @GuardedBy("lock")
    private final Queue zzc = new ArrayDeque();
    /* access modifiers changed from: private */
    public final AtomicReference zzd = new AtomicReference();

    /* access modifiers changed from: private */
    public final void zzc() {
        synchronized (this.zza) {
            try {
                if (this.zzc.isEmpty()) {
                    this.zzb = false;
                    return;
                }
                zzv zzv = (zzv) this.zzc.remove();
                zzd(zzv.zza, zzv.zzb);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private final void zzd(Executor executor, Runnable runnable) {
        try {
            executor.execute(new zzt(this, runnable));
        } catch (RejectedExecutionException unused) {
            zzc();
        }
    }

    @KeepForSdk
    public void checkIsRunningOnCurrentThread() {
        Preconditions.checkState(Thread.currentThread().equals(this.zzd.get()));
    }

    @KeepForSdk
    public void submit(@NonNull Executor executor, @NonNull Runnable runnable) {
        synchronized (this.zza) {
            try {
                if (this.zzb) {
                    this.zzc.add(new zzv(executor, runnable, (zzu) null));
                    return;
                }
                this.zzb = true;
                zzd(executor, runnable);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
