package com.google.firebase.messaging;

import androidx.annotation.GuardedBy;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

class RequestDeduplicator {
    private final Executor executor;
    @GuardedBy("this")
    private final Map<String, Task<String>> getTokenRequests = new ArrayMap();

    public interface GetTokenRequest {
        Task<String> start();
    }

    public RequestDeduplicator(Executor executor2) {
        this.executor = executor2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrStartGetTokenRequest$0(String str, Task task) throws Exception {
        synchronized (this) {
            this.getTokenRequests.remove(str);
        }
        return task;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.google.android.gms.tasks.Task<java.lang.String> getOrStartGetTokenRequest(java.lang.String r5, com.google.firebase.messaging.RequestDeduplicator.GetTokenRequest r6) {
        /*
            r4 = this;
            java.lang.String r0 = "Making new request for: "
            java.lang.String r1 = "Joining ongoing request for: "
            monitor-enter(r4)
            java.util.Map<java.lang.String, com.google.android.gms.tasks.Task<java.lang.String>> r2 = r4.getTokenRequests     // Catch:{ all -> 0x002a }
            java.lang.Object r2 = r2.get(r5)     // Catch:{ all -> 0x002a }
            com.google.android.gms.tasks.Task r2 = (com.google.android.gms.tasks.Task) r2     // Catch:{ all -> 0x002a }
            r3 = 3
            if (r2 == 0) goto L_0x002e
            java.lang.String r6 = "FirebaseMessaging"
            boolean r6 = android.util.Log.isLoggable(r6, r3)     // Catch:{ all -> 0x002a }
            if (r6 == 0) goto L_0x002c
            java.lang.String r6 = "FirebaseMessaging"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x002a }
            r0.<init>(r1)     // Catch:{ all -> 0x002a }
            r0.append(r5)     // Catch:{ all -> 0x002a }
            java.lang.String r5 = r0.toString()     // Catch:{ all -> 0x002a }
            android.util.Log.d(r6, r5)     // Catch:{ all -> 0x002a }
            goto L_0x002c
        L_0x002a:
            r5 = move-exception
            goto L_0x005d
        L_0x002c:
            monitor-exit(r4)
            return r2
        L_0x002e:
            java.lang.String r1 = "FirebaseMessaging"
            boolean r1 = android.util.Log.isLoggable(r1, r3)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0047
            java.lang.String r1 = "FirebaseMessaging"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x002a }
            r2.<init>(r0)     // Catch:{ all -> 0x002a }
            r2.append(r5)     // Catch:{ all -> 0x002a }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x002a }
            android.util.Log.d(r1, r0)     // Catch:{ all -> 0x002a }
        L_0x0047:
            com.google.android.gms.tasks.Task r6 = r6.start()     // Catch:{ all -> 0x002a }
            java.util.concurrent.Executor r0 = r4.executor     // Catch:{ all -> 0x002a }
            com.google.firebase.messaging.n r1 = new com.google.firebase.messaging.n     // Catch:{ all -> 0x002a }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x002a }
            com.google.android.gms.tasks.Task r6 = r6.continueWithTask(r0, r1)     // Catch:{ all -> 0x002a }
            java.util.Map<java.lang.String, com.google.android.gms.tasks.Task<java.lang.String>> r0 = r4.getTokenRequests     // Catch:{ all -> 0x002a }
            r0.put(r5, r6)     // Catch:{ all -> 0x002a }
            monitor-exit(r4)
            return r6
        L_0x005d:
            monitor-exit(r4)     // Catch:{ all -> 0x002a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.RequestDeduplicator.getOrStartGetTokenRequest(java.lang.String, com.google.firebase.messaging.RequestDeduplicator$GetTokenRequest):com.google.android.gms.tasks.Task");
    }
}
