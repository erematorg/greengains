package com.google.firebase.messaging;

import android.content.Context;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class TopicsSubscriber {
    static final String ERROR_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    private static final long MIN_DELAY_SEC = 30;
    private static final long RPC_TIMEOUT_SEC = 30;
    private final Context context;
    private final FirebaseMessaging firebaseMessaging;
    private final Metadata metadata;
    @GuardedBy("pendingOperations")
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations = new ArrayMap();
    private final GmsRpc rpc;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    @GuardedBy("this")
    private boolean syncScheduledOrRunning = false;

    private TopicsSubscriber(FirebaseMessaging firebaseMessaging2, Metadata metadata2, TopicsStore topicsStore, GmsRpc gmsRpc, Context context2, @NonNull ScheduledExecutorService scheduledExecutorService) {
        this.firebaseMessaging = firebaseMessaging2;
        this.metadata = metadata2;
        this.store = topicsStore;
        this.rpc = gmsRpc;
        this.context = context2;
        this.syncExecutor = scheduledExecutorService;
    }

    private void addToPendingOperations(TopicOperation topicOperation, TaskCompletionSource<Void> taskCompletionSource) {
        ArrayDeque arrayDeque;
        synchronized (this.pendingOperations) {
            try {
                String serialize = topicOperation.serialize();
                if (this.pendingOperations.containsKey(serialize)) {
                    arrayDeque = this.pendingOperations.get(serialize);
                } else {
                    ArrayDeque arrayDeque2 = new ArrayDeque();
                    this.pendingOperations.put(serialize, arrayDeque2);
                    arrayDeque = arrayDeque2;
                }
                arrayDeque.add(taskCompletionSource);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @WorkerThread
    private static <T> void awaitTask(Task<T> task) throws IOException {
        try {
            Tasks.await(task, 30, TimeUnit.SECONDS);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e3);
            }
        } catch (InterruptedException | TimeoutException e4) {
            throw new IOException(ERROR_SERVICE_NOT_AVAILABLE, e4);
        }
    }

    @WorkerThread
    private void blockingSubscribeToTopic(String str) throws IOException {
        awaitTask(this.rpc.subscribeToTopic(this.firebaseMessaging.blockingGetToken(), str));
    }

    @WorkerThread
    private void blockingUnsubscribeFromTopic(String str) throws IOException {
        awaitTask(this.rpc.unsubscribeFromTopic(this.firebaseMessaging.blockingGetToken(), str));
    }

    @VisibleForTesting
    public static Task<TopicsSubscriber> createInstance(FirebaseMessaging firebaseMessaging2, Metadata metadata2, GmsRpc gmsRpc, Context context2, @NonNull ScheduledExecutorService scheduledExecutorService) {
        return Tasks.call(scheduledExecutorService, new p(context2, scheduledExecutorService, firebaseMessaging2, metadata2, gmsRpc));
    }

    public static boolean isDebugLogEnabled() {
        return Log.isLoggable(Constants.TAG, 3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TopicsSubscriber lambda$createInstance$0(Context context2, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging2, Metadata metadata2, GmsRpc gmsRpc) throws Exception {
        return new TopicsSubscriber(firebaseMessaging2, metadata2, TopicsStore.getInstance(context2, scheduledExecutorService), gmsRpc, context2, scheduledExecutorService);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void markCompletePendingOperation(com.google.firebase.messaging.TopicOperation r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r0 = r4.pendingOperations
            monitor-enter(r0)
            java.lang.String r5 = r5.serialize()     // Catch:{ all -> 0x0011 }
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r1 = r4.pendingOperations     // Catch:{ all -> 0x0011 }
            boolean r1 = r1.containsKey(r5)     // Catch:{ all -> 0x0011 }
            if (r1 != 0) goto L_0x0013
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            return
        L_0x0011:
            r4 = move-exception
            goto L_0x0034
        L_0x0013:
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r1 = r4.pendingOperations     // Catch:{ all -> 0x0011 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0011 }
            java.util.ArrayDeque r1 = (java.util.ArrayDeque) r1     // Catch:{ all -> 0x0011 }
            java.lang.Object r2 = r1.poll()     // Catch:{ all -> 0x0011 }
            com.google.android.gms.tasks.TaskCompletionSource r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2     // Catch:{ all -> 0x0011 }
            if (r2 == 0) goto L_0x0027
            r3 = 0
            r2.setResult(r3)     // Catch:{ all -> 0x0011 }
        L_0x0027:
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0011 }
            if (r1 == 0) goto L_0x0032
            java.util.Map<java.lang.String, java.util.ArrayDeque<com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r4 = r4.pendingOperations     // Catch:{ all -> 0x0011 }
            r4.remove(r5)     // Catch:{ all -> 0x0011 }
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            return
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.markCompletePendingOperation(com.google.firebase.messaging.TopicOperation):void");
    }

    private void startSync() {
        if (!isSyncScheduledOrRunning()) {
            syncWithDelaySecondsInternal(0);
        }
    }

    @VisibleForTesting
    public TopicsStore getStore() {
        return this.store;
    }

    public boolean hasPendingOperation() {
        return this.store.getNextTopicOperation() != null;
    }

    public synchronized boolean isSyncScheduledOrRunning() {
        return this.syncScheduledOrRunning;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078 A[Catch:{ IOException -> 0x0025 }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean performTopicOperation(com.google.firebase.messaging.TopicOperation r10) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "Unknown topic operation"
            java.lang.String r2 = "Subscribe to topic: "
            java.lang.String r3 = "Unsubscribe from topic: "
            r4 = 0
            java.lang.String r5 = r10.getOperation()     // Catch:{ IOException -> 0x0025 }
            int r6 = r5.hashCode()     // Catch:{ IOException -> 0x0025 }
            r7 = 83
            r8 = 1
            if (r6 == r7) goto L_0x0028
            r7 = 85
            if (r6 == r7) goto L_0x001b
            goto L_0x0032
        L_0x001b:
            java.lang.String r6 = "U"
            boolean r5 = r5.equals(r6)     // Catch:{ IOException -> 0x0025 }
            if (r5 == 0) goto L_0x0032
            r5 = r8
            goto L_0x0033
        L_0x0025:
            r9 = move-exception
            goto L_0x009c
        L_0x0028:
            java.lang.String r6 = "S"
            boolean r5 = r5.equals(r6)     // Catch:{ IOException -> 0x0025 }
            if (r5 == 0) goto L_0x0032
            r5 = r4
            goto L_0x0033
        L_0x0032:
            r5 = -1
        L_0x0033:
            java.lang.String r6 = " succeeded."
            if (r5 == 0) goto L_0x0078
            if (r5 == r8) goto L_0x0054
            boolean r9 = isDebugLogEnabled()     // Catch:{ IOException -> 0x0025 }
            if (r9 == 0) goto L_0x009b
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0025 }
            r9.<init>(r1)     // Catch:{ IOException -> 0x0025 }
            r9.append(r10)     // Catch:{ IOException -> 0x0025 }
            java.lang.String r10 = "."
            r9.append(r10)     // Catch:{ IOException -> 0x0025 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x0025 }
            android.util.Log.d(r0, r9)     // Catch:{ IOException -> 0x0025 }
            goto L_0x009b
        L_0x0054:
            java.lang.String r1 = r10.getTopic()     // Catch:{ IOException -> 0x0025 }
            r9.blockingUnsubscribeFromTopic(r1)     // Catch:{ IOException -> 0x0025 }
            boolean r9 = isDebugLogEnabled()     // Catch:{ IOException -> 0x0025 }
            if (r9 == 0) goto L_0x009b
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0025 }
            r9.<init>(r3)     // Catch:{ IOException -> 0x0025 }
            java.lang.String r10 = r10.getTopic()     // Catch:{ IOException -> 0x0025 }
            r9.append(r10)     // Catch:{ IOException -> 0x0025 }
            r9.append(r6)     // Catch:{ IOException -> 0x0025 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x0025 }
            android.util.Log.d(r0, r9)     // Catch:{ IOException -> 0x0025 }
            goto L_0x009b
        L_0x0078:
            java.lang.String r1 = r10.getTopic()     // Catch:{ IOException -> 0x0025 }
            r9.blockingSubscribeToTopic(r1)     // Catch:{ IOException -> 0x0025 }
            boolean r9 = isDebugLogEnabled()     // Catch:{ IOException -> 0x0025 }
            if (r9 == 0) goto L_0x009b
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0025 }
            r9.<init>(r2)     // Catch:{ IOException -> 0x0025 }
            java.lang.String r10 = r10.getTopic()     // Catch:{ IOException -> 0x0025 }
            r9.append(r10)     // Catch:{ IOException -> 0x0025 }
            r9.append(r6)     // Catch:{ IOException -> 0x0025 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x0025 }
            android.util.Log.d(r0, r9)     // Catch:{ IOException -> 0x0025 }
        L_0x009b:
            return r8
        L_0x009c:
            java.lang.String r10 = "SERVICE_NOT_AVAILABLE"
            java.lang.String r1 = r9.getMessage()
            boolean r10 = r10.equals(r1)
            if (r10 != 0) goto L_0x00ce
            java.lang.String r10 = "INTERNAL_SERVER_ERROR"
            java.lang.String r1 = r9.getMessage()
            boolean r10 = r10.equals(r1)
            if (r10 != 0) goto L_0x00ce
            java.lang.String r10 = "TOO_MANY_SUBSCRIBERS"
            java.lang.String r1 = r9.getMessage()
            boolean r10 = r10.equals(r1)
            if (r10 == 0) goto L_0x00c1
            goto L_0x00ce
        L_0x00c1:
            java.lang.String r10 = r9.getMessage()
            if (r10 != 0) goto L_0x00cd
            java.lang.String r9 = "Topic operation failed without exception message. Will retry Topic operation."
            android.util.Log.e(r0, r9)
            return r4
        L_0x00cd:
            throw r9
        L_0x00ce:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r1 = "Topic operation failed: "
            r10.<init>(r1)
            java.lang.String r9 = r9.getMessage()
            r10.append(r9)
            java.lang.String r9 = ". Will retry Topic operation."
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            android.util.Log.e(r0, r9)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.performTopicOperation(com.google.firebase.messaging.TopicOperation):boolean");
    }

    public void scheduleSyncTaskWithDelaySeconds(Runnable runnable, long j2) {
        this.syncExecutor.schedule(runnable, j2, TimeUnit.SECONDS);
    }

    @VisibleForTesting
    public Task<Void> scheduleTopicOperation(TopicOperation topicOperation) {
        this.store.addTopicOperation(topicOperation);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        addToPendingOperations(topicOperation, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    public synchronized void setSyncScheduledOrRunning(boolean z2) {
        this.syncScheduledOrRunning = z2;
    }

    public void startTopicsSyncIfNecessary() {
        if (hasPendingOperation()) {
            startSync();
        }
    }

    public Task<Void> subscribeToTopic(String str) {
        Task<Void> scheduleTopicOperation = scheduleTopicOperation(TopicOperation.subscribe(str));
        startTopicsSyncIfNecessary();
        return scheduleTopicOperation;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        if (performTopicOperation(r0) != false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        return false;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean syncTopics() throws java.io.IOException {
        /*
            r2 = this;
        L_0x0000:
            monitor-enter(r2)
            com.google.firebase.messaging.TopicsStore r0 = r2.store     // Catch:{ all -> 0x0017 }
            com.google.firebase.messaging.TopicOperation r0 = r0.getNextTopicOperation()     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x001c
            boolean r0 = isDebugLogEnabled()     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0019
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x0017 }
            goto L_0x0019
        L_0x0017:
            r0 = move-exception
            goto L_0x002e
        L_0x0019:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            r2 = 1
            return r2
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            boolean r1 = r2.performTopicOperation(r0)
            if (r1 != 0) goto L_0x0025
            r2 = 0
            return r2
        L_0x0025:
            com.google.firebase.messaging.TopicsStore r1 = r2.store
            r1.removeTopicOperation(r0)
            r2.markCompletePendingOperation(r0)
            goto L_0x0000
        L_0x002e:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.TopicsSubscriber.syncTopics():boolean");
    }

    public void syncWithDelaySecondsInternal(long j2) {
        scheduleSyncTaskWithDelaySeconds(new TopicsSyncTask(this, this.context, this.metadata, Math.min(Math.max(30, 2 * j2), MAX_DELAY_SEC)), j2);
        setSyncScheduledOrRunning(true);
    }

    public Task<Void> unsubscribeFromTopic(String str) {
        Task<Void> scheduleTopicOperation = scheduleTopicOperation(TopicOperation.unsubscribe(str));
        startTopicsSyncIfNecessary();
        return scheduleTopicOperation;
    }
}
