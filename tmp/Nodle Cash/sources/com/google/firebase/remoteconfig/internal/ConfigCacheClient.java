package com.google.firebase.remoteconfig.internal;

import U1.b;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.a;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@AnyThread
public class ConfigCacheClient {
    private static final Executor DIRECT_EXECUTOR = new a(2);
    static final long DISK_READ_TIMEOUT_IN_SECONDS = 5;
    @GuardedBy("ConfigCacheClient.class")
    private static final Map<String, ConfigCacheClient> clientInstances = new HashMap();
    @GuardedBy("this")
    @Nullable
    private Task<ConfigContainer> cachedContainerTask = null;
    private final Executor executor;
    private final ConfigStorageClient storageClient;

    public static class AwaitListener<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {
        private final CountDownLatch latch;

        private AwaitListener() {
            this.latch = new CountDownLatch(1);
        }

        public void await() throws InterruptedException {
            this.latch.await();
        }

        public void onCanceled() {
            this.latch.countDown();
        }

        public void onFailure(@NonNull Exception exc) {
            this.latch.countDown();
        }

        public void onSuccess(TResult tresult) {
            this.latch.countDown();
        }

        public boolean await(long j2, TimeUnit timeUnit) throws InterruptedException {
            return this.latch.await(j2, timeUnit);
        }
    }

    private ConfigCacheClient(Executor executor2, ConfigStorageClient configStorageClient) {
        this.executor = executor2;
        this.storageClient = configStorageClient;
    }

    private static <TResult> TResult await(Task<TResult> task, long j2, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        AwaitListener awaitListener = new AwaitListener();
        Executor executor2 = DIRECT_EXECUTOR;
        task.addOnSuccessListener(executor2, (OnSuccessListener<? super TResult>) awaitListener);
        task.addOnFailureListener(executor2, (OnFailureListener) awaitListener);
        task.addOnCanceledListener(executor2, (OnCanceledListener) awaitListener);
        if (!awaitListener.await(j2, timeUnit)) {
            throw new TimeoutException("Task await timed out.");
        } else if (task.isSuccessful()) {
            return task.getResult();
        } else {
            throw new ExecutionException(task.getException());
        }
    }

    @VisibleForTesting
    public static synchronized void clearInstancesForTest() {
        synchronized (ConfigCacheClient.class) {
            clientInstances.clear();
        }
    }

    public static synchronized ConfigCacheClient getInstance(Executor executor2, ConfigStorageClient configStorageClient) {
        ConfigCacheClient configCacheClient;
        synchronized (ConfigCacheClient.class) {
            try {
                String fileName = configStorageClient.getFileName();
                Map<String, ConfigCacheClient> map = clientInstances;
                if (!map.containsKey(fileName)) {
                    map.put(fileName, new ConfigCacheClient(executor2, configStorageClient));
                }
                configCacheClient = map.get(fileName);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return configCacheClient;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Void lambda$put$0(ConfigContainer configContainer) throws Exception {
        return this.storageClient.write(configContainer);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$put$1(boolean z2, ConfigContainer configContainer, Void voidR) throws Exception {
        if (z2) {
            updateInMemoryConfigContainer(configContainer);
        }
        return Tasks.forResult(configContainer);
    }

    private synchronized void updateInMemoryConfigContainer(ConfigContainer configContainer) {
        this.cachedContainerTask = Tasks.forResult(configContainer);
    }

    public void clear() {
        synchronized (this) {
            this.cachedContainerTask = Tasks.forResult(null);
        }
        this.storageClient.clear();
    }

    public synchronized Task<ConfigContainer> get() {
        try {
            Task<ConfigContainer> task = this.cachedContainerTask;
            if (task != null) {
                if (task.isComplete() && !this.cachedContainerTask.isSuccessful()) {
                }
            }
            Executor executor2 = this.executor;
            ConfigStorageClient configStorageClient = this.storageClient;
            Objects.requireNonNull(configStorageClient);
            this.cachedContainerTask = Tasks.call(executor2, new androidx.work.impl.utils.a(configStorageClient, 4));
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.cachedContainerTask;
    }

    @Nullable
    public ConfigContainer getBlocking() {
        return getBlocking(5);
    }

    @VisibleForTesting
    @Nullable
    public synchronized Task<ConfigContainer> getCachedContainerTask() {
        return this.cachedContainerTask;
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer) {
        return put(configContainer, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        return (com.google.firebase.remoteconfig.internal.ConfigContainer) await(get(), r2, java.util.concurrent.TimeUnit.SECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        android.util.Log.d(com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG, "Reading from storage file failed.", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        return null;
     */
    @androidx.annotation.VisibleForTesting
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.remoteconfig.internal.ConfigContainer getBlocking(long r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            com.google.android.gms.tasks.Task<com.google.firebase.remoteconfig.internal.ConfigContainer> r0 = r1.cachedContainerTask     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0017
            boolean r0 = r0.isSuccessful()     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.tasks.Task<com.google.firebase.remoteconfig.internal.ConfigContainer> r2 = r1.cachedContainerTask     // Catch:{ all -> 0x0015 }
            java.lang.Object r2 = r2.getResult()     // Catch:{ all -> 0x0015 }
            com.google.firebase.remoteconfig.internal.ConfigContainer r2 = (com.google.firebase.remoteconfig.internal.ConfigContainer) r2     // Catch:{ all -> 0x0015 }
            monitor-exit(r1)     // Catch:{ all -> 0x0015 }
            return r2
        L_0x0015:
            r2 = move-exception
            goto L_0x002f
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x0015 }
            com.google.android.gms.tasks.Task r1 = r1.get()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0025 }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0025 }
            java.lang.Object r1 = await(r1, r2, r0)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0025 }
            com.google.firebase.remoteconfig.internal.ConfigContainer r1 = (com.google.firebase.remoteconfig.internal.ConfigContainer) r1     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0025 }
            return r1
        L_0x0025:
            r1 = move-exception
            java.lang.String r2 = "FirebaseRemoteConfig"
            java.lang.String r3 = "Reading from storage file failed."
            android.util.Log.d(r2, r3, r1)
            r1 = 0
            return r1
        L_0x002f:
            monitor-exit(r1)     // Catch:{ all -> 0x0015 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.ConfigCacheClient.getBlocking(long):com.google.firebase.remoteconfig.internal.ConfigContainer");
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer, boolean z2) {
        return Tasks.call(this.executor, new b(this, configContainer, 3)).onSuccessTask(this.executor, new androidx.navigation.ui.b((Object) this, (Object) configContainer, z2));
    }
}
