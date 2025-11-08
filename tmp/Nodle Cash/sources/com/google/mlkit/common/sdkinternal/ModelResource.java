package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzrr;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
public abstract class ModelResource {
    @NonNull
    @KeepForSdk
    protected final TaskQueue taskQueue;
    private final AtomicInteger zza;
    private final AtomicBoolean zzb;

    public ModelResource() {
        this.zza = new AtomicInteger(0);
        this.zzb = new AtomicBoolean(false);
        this.taskQueue = new TaskQueue();
    }

    @NonNull
    @KeepForSdk
    public <T> Task<T> callAfterLoad(@NonNull Executor executor, @NonNull Callable<T> callable, @NonNull CancellationToken cancellationToken) {
        Preconditions.checkState(this.zza.get() > 0);
        if (cancellationToken.isCancellationRequested()) {
            return Tasks.forCanceled();
        }
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        this.taskQueue.submit(new zzm(executor, cancellationToken, cancellationTokenSource, taskCompletionSource), new zzn(this, cancellationToken, cancellationTokenSource, callable, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    @KeepForSdk
    public boolean isLoaded() {
        return this.zzb.get();
    }

    @WorkerThread
    @VisibleForTesting
    @KeepForSdk
    public abstract void load() throws MlKitException;

    @KeepForSdk
    public void pin() {
        this.zza.incrementAndGet();
    }

    @WorkerThread
    @KeepForSdk
    public abstract void release();

    @KeepForSdk
    public void unpin(@NonNull Executor executor) {
        unpinWithTask(executor);
    }

    @NonNull
    @KeepForSdk
    public Task<Void> unpinWithTask(@NonNull Executor executor) {
        Preconditions.checkState(this.zza.get() > 0);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.taskQueue.submit(executor, new zzl(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public final /* synthetic */ void zza(CancellationToken cancellationToken, CancellationTokenSource cancellationTokenSource, Callable callable, TaskCompletionSource taskCompletionSource) {
        if (cancellationToken.isCancellationRequested()) {
            cancellationTokenSource.cancel();
            return;
        }
        try {
            if (!this.zzb.get()) {
                load();
                this.zzb.set(true);
            }
            if (cancellationToken.isCancellationRequested()) {
                cancellationTokenSource.cancel();
                return;
            }
            Object call = callable.call();
            if (cancellationToken.isCancellationRequested()) {
                cancellationTokenSource.cancel();
            } else {
                taskCompletionSource.setResult(call);
            }
        } catch (RuntimeException e3) {
            throw new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e3);
        } catch (Exception e4) {
            if (cancellationToken.isCancellationRequested()) {
                cancellationTokenSource.cancel();
            } else {
                taskCompletionSource.setException(e4);
            }
        }
    }

    public final /* synthetic */ void zzb(TaskCompletionSource taskCompletionSource) {
        int decrementAndGet = this.zza.decrementAndGet();
        Preconditions.checkState(decrementAndGet >= 0);
        if (decrementAndGet == 0) {
            release();
            this.zzb.set(false);
        }
        zzrr.zza();
        taskCompletionSource.setResult(null);
    }

    @KeepForSdk
    public ModelResource(@NonNull TaskQueue taskQueue2) {
        this.zza = new AtomicInteger(0);
        this.zzb = new AtomicBoolean(false);
        this.taskQueue = taskQueue2;
    }
}
