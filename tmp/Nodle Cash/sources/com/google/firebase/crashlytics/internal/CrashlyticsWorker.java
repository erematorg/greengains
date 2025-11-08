package com.google.firebase.crashlytics.internal;

import A1.C0219a;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.processing.g;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class CrashlyticsWorker {
    private final ExecutorService executor;
    private Task<?> tail = Tasks.forResult(null);
    private final Object tailLock = new Object();

    public CrashlyticsWorker(ExecutorService executorService) {
        this.executor = executorService;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$await$5() {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Task lambda$submitTask$4(Callable callable, Task task) throws Exception {
        return (Task) callable.call();
    }

    @VisibleForTesting
    public void await() throws ExecutionException, InterruptedException {
        Tasks.await(submit((Runnable) new g(3)));
    }

    public ExecutorService getExecutor() {
        return this.executor;
    }

    public <T> Task<T> submit(Callable<T> callable) {
        Task<TContinuationResult> continueWith;
        synchronized (this.tailLock) {
            try {
                if (this.tail.isCanceled()) {
                    this.tail = this.tail.continueWithTask(this.executor, new a(1));
                }
                continueWith = this.tail.continueWith(this.executor, new b(callable, 0));
                this.tail = continueWith;
            } catch (Throwable th) {
                throw th;
            }
        }
        return continueWith;
    }

    public <T> Task<T> submitTask(Callable<Task<T>> callable) {
        Task<TContinuationResult> continueWithTask;
        synchronized (this.tailLock) {
            continueWithTask = this.tail.continueWithTask(this.executor, new b(callable, 1));
            this.tail = continueWithTask;
        }
        return continueWithTask;
    }

    public Task<Void> submit(Runnable runnable) {
        Task<TContinuationResult> continueWith;
        synchronized (this.tailLock) {
            try {
                if (this.tail.isCanceled()) {
                    this.tail = this.tail.continueWithTask(this.executor, new a(0));
                }
                continueWith = this.tail.continueWith(this.executor, new C0219a(runnable, 23));
                this.tail = continueWith;
            } catch (Throwable th) {
                throw th;
            }
        }
        return continueWith;
    }
}
