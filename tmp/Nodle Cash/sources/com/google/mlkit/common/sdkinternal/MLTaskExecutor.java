package com.google.mlkit.common.sdkinternal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_common.zza;
import com.google.android.gms.internal.mlkit_common.zzaw;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@KeepForSdk
public class MLTaskExecutor {
    private static final Object zza = new Object();
    @GuardedBy("lock")
    @Nullable
    private static MLTaskExecutor zzb;
    /* access modifiers changed from: private */
    public final Handler zzc;

    private MLTaskExecutor(Looper looper) {
        this.zzc = new zza(looper);
    }

    @NonNull
    @KeepForSdk
    public static MLTaskExecutor getInstance() {
        MLTaskExecutor mLTaskExecutor;
        synchronized (zza) {
            try {
                if (zzb == null) {
                    HandlerThread handlerThread = new HandlerThread("MLHandler", 9);
                    handlerThread.start();
                    zzb = new MLTaskExecutor(handlerThread.getLooper());
                }
                mLTaskExecutor = zzb;
            } catch (Throwable th) {
                throw th;
            }
        }
        return mLTaskExecutor;
    }

    @NonNull
    @KeepForSdk
    public static Executor workerThreadExecutor() {
        return zzh.INSTANCE;
    }

    @NonNull
    @KeepForSdk
    public Handler getHandler() {
        return this.zzc;
    }

    @NonNull
    @KeepForSdk
    public <ResultT> Task<ResultT> scheduleCallable(@NonNull Callable<ResultT> callable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        scheduleRunnable(new zzf(callable, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    @KeepForSdk
    public void scheduleRunnable(@NonNull Runnable runnable) {
        workerThreadExecutor().execute(runnable);
    }

    @KeepForSdk
    public void scheduleRunnableDelayed(@NonNull Runnable runnable, long j2) {
        this.zzc.postDelayed(runnable, j2);
    }

    @NonNull
    @KeepForSdk
    public <ResultT> Task<ResultT> scheduleTaskCallable(@NonNull Callable<Task<ResultT>> callable) {
        return scheduleCallable(callable).continueWithTask(zzaw.zza(), new zzg());
    }
}
