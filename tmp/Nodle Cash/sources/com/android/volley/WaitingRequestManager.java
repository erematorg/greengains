package com.android.volley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.Cache;
import com.android.volley.Request;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

class WaitingRequestManager implements Request.NetworkRequestCompleteListener {
    @Nullable
    private final CacheDispatcher mCacheDispatcher;
    @Nullable
    private final BlockingQueue<Request<?>> mNetworkQueue;
    @Nullable
    private final RequestQueue mRequestQueue;
    private final ResponseDelivery mResponseDelivery;
    private final Map<String, List<Request<?>>> mWaitingRequests;

    public WaitingRequestManager(@NonNull RequestQueue requestQueue) {
        this.mWaitingRequests = new HashMap();
        this.mRequestQueue = requestQueue;
        this.mResponseDelivery = requestQueue.getResponseDelivery();
        this.mCacheDispatcher = null;
        this.mNetworkQueue = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean maybeAddToWaitingRequests(com.android.volley.Request<?> r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = r4.getCacheKey()     // Catch:{ all -> 0x001d }
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r1 = r3.mWaitingRequests     // Catch:{ all -> 0x001d }
            boolean r1 = r1.containsKey(r0)     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x003d
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r1 = r3.mWaitingRequests     // Catch:{ all -> 0x001d }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x001d }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x001d }
            if (r1 != 0) goto L_0x001f
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x001d }
            r1.<init>()     // Catch:{ all -> 0x001d }
            goto L_0x001f
        L_0x001d:
            r4 = move-exception
            goto L_0x0056
        L_0x001f:
            java.lang.String r2 = "waiting-for-response"
            r4.addMarker(r2)     // Catch:{ all -> 0x001d }
            r1.add(r4)     // Catch:{ all -> 0x001d }
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r4 = r3.mWaitingRequests     // Catch:{ all -> 0x001d }
            r4.put(r0, r1)     // Catch:{ all -> 0x001d }
            boolean r4 = com.android.volley.VolleyLog.DEBUG     // Catch:{ all -> 0x001d }
            if (r4 == 0) goto L_0x003a
            java.lang.String r4 = "Request for cacheKey=%s is in flight, putting on hold."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x001d }
            com.android.volley.VolleyLog.d(r4, r0)     // Catch:{ all -> 0x001d }
        L_0x003a:
            monitor-exit(r3)
            r3 = 1
            return r3
        L_0x003d:
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r1 = r3.mWaitingRequests     // Catch:{ all -> 0x001d }
            r2 = 0
            r1.put(r0, r2)     // Catch:{ all -> 0x001d }
            r4.setNetworkRequestCompleteListener(r3)     // Catch:{ all -> 0x001d }
            boolean r4 = com.android.volley.VolleyLog.DEBUG     // Catch:{ all -> 0x001d }
            if (r4 == 0) goto L_0x0053
            java.lang.String r4 = "new request, sending to network %s"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch:{ all -> 0x001d }
            com.android.volley.VolleyLog.d(r4, r0)     // Catch:{ all -> 0x001d }
        L_0x0053:
            monitor-exit(r3)
            r3 = 0
            return r3
        L_0x0056:
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.WaitingRequestManager.maybeAddToWaitingRequests(com.android.volley.Request):boolean");
    }

    public synchronized void onNoUsableResponseReceived(Request<?> request) {
        BlockingQueue<Request<?>> blockingQueue;
        try {
            String cacheKey = request.getCacheKey();
            List remove = this.mWaitingRequests.remove(cacheKey);
            if (remove != null && !remove.isEmpty()) {
                if (VolleyLog.DEBUG) {
                    VolleyLog.v("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(remove.size()), cacheKey);
                }
                Request request2 = (Request) remove.remove(0);
                this.mWaitingRequests.put(cacheKey, remove);
                request2.setNetworkRequestCompleteListener(this);
                RequestQueue requestQueue = this.mRequestQueue;
                if (requestQueue != null) {
                    requestQueue.sendRequestOverNetwork(request2);
                } else if (!(this.mCacheDispatcher == null || (blockingQueue = this.mNetworkQueue) == null)) {
                    blockingQueue.put(request2);
                }
            }
        } catch (InterruptedException e3) {
            VolleyLog.e("Couldn't add request to queue. %s", e3.toString());
            Thread.currentThread().interrupt();
            this.mCacheDispatcher.quit();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void onResponseReceived(Request<?> request, Response<?> response) {
        List<Request> remove;
        Cache.Entry entry = response.cacheEntry;
        if (entry == null || entry.isExpired()) {
            onNoUsableResponseReceived(request);
            return;
        }
        String cacheKey = request.getCacheKey();
        synchronized (this) {
            remove = this.mWaitingRequests.remove(cacheKey);
        }
        if (remove != null) {
            if (VolleyLog.DEBUG) {
                VolleyLog.v("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), cacheKey);
            }
            for (Request postResponse : remove) {
                this.mResponseDelivery.postResponse(postResponse, response);
            }
        }
    }

    public WaitingRequestManager(@NonNull CacheDispatcher cacheDispatcher, @NonNull BlockingQueue<Request<?>> blockingQueue, ResponseDelivery responseDelivery) {
        this.mWaitingRequests = new HashMap();
        this.mRequestQueue = null;
        this.mResponseDelivery = responseDelivery;
        this.mCacheDispatcher = cacheDispatcher;
        this.mNetworkQueue = blockingQueue;
    }
}
