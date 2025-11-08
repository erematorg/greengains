package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.AsyncCache;
import com.android.volley.AsyncNetwork;
import com.android.volley.Cache;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncRequestQueue extends RequestQueue {
    private static final int DEFAULT_BLOCKING_THREAD_POOL_SIZE = 4;
    /* access modifiers changed from: private */
    @Nullable
    public final AsyncCache mAsyncCache;
    /* access modifiers changed from: private */
    public ExecutorService mBlockingExecutor;
    private final Object mCacheInitializationLock;
    private ExecutorFactory mExecutorFactory;
    private volatile boolean mIsCacheInitialized;
    /* access modifiers changed from: private */
    public final AsyncNetwork mNetwork;
    /* access modifiers changed from: private */
    public ExecutorService mNonBlockingExecutor;
    private ScheduledExecutorService mNonBlockingScheduledExecutor;
    private final List<Request<?>> mRequestsAwaitingCacheInitialization;
    /* access modifiers changed from: private */
    public final WaitingRequestManager mWaitingRequestManager;

    public static class Builder {
        @Nullable
        private AsyncCache mAsyncCache = null;
        @Nullable
        private Cache mCache = null;
        @Nullable
        private ExecutorFactory mExecutorFactory = null;
        private final AsyncNetwork mNetwork;
        @Nullable
        private ResponseDelivery mResponseDelivery = null;

        public Builder(AsyncNetwork asyncNetwork) {
            if (asyncNetwork != null) {
                this.mNetwork = asyncNetwork;
                return;
            }
            throw new IllegalArgumentException("Network cannot be null");
        }

        private ExecutorFactory getDefaultExecutorFactory() {
            return new ExecutorFactory() {
                private ThreadPoolExecutor getNewThreadPoolExecutor(int i3, String str, BlockingQueue<Runnable> blockingQueue) {
                    return new ThreadPoolExecutor(0, i3, 60, TimeUnit.SECONDS, blockingQueue, getThreadFactory(str));
                }

                private ThreadFactory getThreadFactory(final String str) {
                    return new ThreadFactory() {
                        public Thread newThread(@NonNull Runnable runnable) {
                            Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
                            newThread.setName("Volley-" + str);
                            return newThread;
                        }
                    };
                }

                public ExecutorService createBlockingExecutor(BlockingQueue<Runnable> blockingQueue) {
                    return getNewThreadPoolExecutor(4, "BlockingExecutor", blockingQueue);
                }

                public ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> blockingQueue) {
                    return getNewThreadPoolExecutor(1, "Non-BlockingExecutor", blockingQueue);
                }

                public ScheduledExecutorService createNonBlockingScheduledExecutor() {
                    return new ScheduledThreadPoolExecutor(0, getThreadFactory("ScheduledExecutor"));
                }
            };
        }

        public AsyncRequestQueue build() {
            Cache cache = this.mCache;
            if (cache == null && this.mAsyncCache == null) {
                throw new IllegalArgumentException("You must set one of the cache objects");
            }
            if (cache == null) {
                this.mCache = new ThrowingCache();
            }
            if (this.mResponseDelivery == null) {
                this.mResponseDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
            }
            if (this.mExecutorFactory == null) {
                this.mExecutorFactory = getDefaultExecutorFactory();
            }
            return new AsyncRequestQueue(this.mCache, this.mNetwork, this.mAsyncCache, this.mResponseDelivery, this.mExecutorFactory);
        }

        public Builder setAsyncCache(AsyncCache asyncCache) {
            this.mAsyncCache = asyncCache;
            return this;
        }

        public Builder setCache(Cache cache) {
            this.mCache = cache;
            return this;
        }

        public Builder setExecutorFactory(ExecutorFactory executorFactory) {
            this.mExecutorFactory = executorFactory;
            return this;
        }

        public Builder setResponseDelivery(ResponseDelivery responseDelivery) {
            this.mResponseDelivery = responseDelivery;
            return this;
        }
    }

    public class CacheParseTask<T> extends RequestTask<T> {
        Cache.Entry entry;
        long startTimeMillis;

        public CacheParseTask(Request<T> request, Cache.Entry entry2, long j2) {
            super(request);
            this.entry = entry2;
            this.startTimeMillis = j2;
        }

        public void run() {
            this.mRequest.addMarker("cache-hit");
            Request<T> request = this.mRequest;
            Cache.Entry entry2 = this.entry;
            Response<T> parseNetworkResponse = request.parseNetworkResponse(new NetworkResponse(200, entry2.data, false, 0, entry2.allResponseHeaders));
            this.mRequest.addMarker("cache-hit-parsed");
            if (!this.entry.refreshNeeded(this.startTimeMillis)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, parseNetworkResponse);
                return;
            }
            this.mRequest.addMarker("cache-hit-refresh-needed");
            this.mRequest.setCacheEntry(this.entry);
            parseNetworkResponse.intermediate = true;
            if (!AsyncRequestQueue.this.mWaitingRequestManager.maybeAddToWaitingRequests(this.mRequest)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, parseNetworkResponse, new Runnable() {
                    public void run() {
                        CacheParseTask cacheParseTask = CacheParseTask.this;
                        AsyncRequestQueue.this.sendRequestOverNetwork(cacheParseTask.mRequest);
                    }
                });
            } else {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, parseNetworkResponse);
            }
        }
    }

    public class CachePutTask<T> extends RequestTask<T> {
        Response<?> response;

        public CachePutTask(Request<T> request, Response<?> response2) {
            super(request);
            this.response = response2;
        }

        public void run() {
            if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mAsyncCache.put(this.mRequest.getCacheKey(), this.response.cacheEntry, new AsyncCache.OnWriteCompleteCallback() {
                    public void onWriteComplete() {
                        CachePutTask cachePutTask = CachePutTask.this;
                        AsyncRequestQueue.this.finishRequest(cachePutTask.mRequest, cachePutTask.response, true);
                    }
                });
                return;
            }
            AsyncRequestQueue.this.getCache().put(this.mRequest.getCacheKey(), this.response.cacheEntry);
            AsyncRequestQueue.this.finishRequest(this.mRequest, this.response, true);
        }
    }

    public class CacheTask<T> extends RequestTask<T> {
        public CacheTask(Request<T> request) {
            super(request);
        }

        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("cache-discard-canceled");
                return;
            }
            this.mRequest.addMarker("cache-queue-take");
            if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mAsyncCache.get(this.mRequest.getCacheKey(), new AsyncCache.OnGetCompleteCallback() {
                    public void onGetComplete(Cache.Entry entry) {
                        CacheTask cacheTask = CacheTask.this;
                        AsyncRequestQueue.this.handleEntry(entry, cacheTask.mRequest);
                    }
                });
                return;
            }
            AsyncRequestQueue.this.handleEntry(AsyncRequestQueue.this.getCache().get(this.mRequest.getCacheKey()), this.mRequest);
        }
    }

    public static abstract class ExecutorFactory {
        public abstract ExecutorService createBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ScheduledExecutorService createNonBlockingScheduledExecutor();
    }

    public class NetworkParseTask<T> extends RequestTask<T> {
        NetworkResponse networkResponse;

        public NetworkParseTask(Request<T> request, NetworkResponse networkResponse2) {
            super(request);
            this.networkResponse = networkResponse2;
        }

        public void run() {
            Response<T> parseNetworkResponse = this.mRequest.parseNetworkResponse(this.networkResponse);
            this.mRequest.addMarker("network-parse-complete");
            if (!this.mRequest.shouldCache() || parseNetworkResponse.cacheEntry == null) {
                AsyncRequestQueue.this.finishRequest(this.mRequest, parseNetworkResponse, false);
            } else if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mNonBlockingExecutor.execute(new CachePutTask(this.mRequest, parseNetworkResponse));
            } else {
                AsyncRequestQueue.this.mBlockingExecutor.execute(new CachePutTask(this.mRequest, parseNetworkResponse));
            }
        }
    }

    public class NetworkTask<T> extends RequestTask<T> {
        public NetworkTask(Request<T> request) {
            super(request);
        }

        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("network-discard-cancelled");
                this.mRequest.notifyListenerResponseNotUsable();
                return;
            }
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            this.mRequest.addMarker("network-queue-take");
            AsyncRequestQueue.this.mNetwork.performRequest(this.mRequest, new AsyncNetwork.OnRequestComplete() {
                public void onError(VolleyError volleyError) {
                    volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    ExecutorService access$500 = AsyncRequestQueue.this.mBlockingExecutor;
                    NetworkTask networkTask = NetworkTask.this;
                    access$500.execute(new ParseErrorTask(networkTask.mRequest, volleyError));
                }

                public void onSuccess(NetworkResponse networkResponse) {
                    NetworkTask.this.mRequest.addMarker("network-http-complete");
                    if (!networkResponse.notModified || !NetworkTask.this.mRequest.hasHadResponseDelivered()) {
                        ExecutorService access$500 = AsyncRequestQueue.this.mBlockingExecutor;
                        NetworkTask networkTask = NetworkTask.this;
                        access$500.execute(new NetworkParseTask(networkTask.mRequest, networkResponse));
                        return;
                    }
                    NetworkTask.this.mRequest.finish("not-modified");
                    NetworkTask.this.mRequest.notifyListenerResponseNotUsable();
                }
            });
        }
    }

    public class ParseErrorTask<T> extends RequestTask<T> {
        VolleyError volleyError;

        public ParseErrorTask(Request<T> request, VolleyError volleyError2) {
            super(request);
            this.volleyError = volleyError2;
        }

        public void run() {
            AsyncRequestQueue.this.getResponseDelivery().postError(this.mRequest, this.mRequest.parseNetworkError(this.volleyError));
            this.mRequest.notifyListenerResponseNotUsable();
        }
    }

    public static class ThrowingCache implements Cache {
        private ThrowingCache() {
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Cache.Entry get(String str) {
            throw new UnsupportedOperationException();
        }

        public void initialize() {
            throw new UnsupportedOperationException();
        }

        public void invalidate(String str, boolean z2) {
            throw new UnsupportedOperationException();
        }

        public void put(String str, Cache.Entry entry) {
            throw new UnsupportedOperationException();
        }

        public void remove(String str) {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: private */
    public void finishRequest(Request<?> request, Response<?> response, boolean z2) {
        if (z2) {
            request.addMarker("network-cache-written");
        }
        request.markDelivered();
        getResponseDelivery().postResponse(request, response);
        request.notifyListenerResponseReceived(response);
    }

    private static PriorityBlockingQueue<Runnable> getBlockingQueue() {
        return new PriorityBlockingQueue<>(11, new Comparator<Runnable>() {
            public int compare(Runnable runnable, Runnable runnable2) {
                if (!(runnable instanceof RequestTask)) {
                    return runnable2 instanceof RequestTask ? -1 : 0;
                }
                if (runnable2 instanceof RequestTask) {
                    return ((RequestTask) runnable).compareTo((RequestTask) runnable2);
                }
                return 1;
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleEntry(Cache.Entry entry, Request<?> request) {
        if (entry == null) {
            request.addMarker("cache-miss");
            if (!this.mWaitingRequestManager.maybeAddToWaitingRequests(request)) {
                sendRequestOverNetwork(request);
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (entry.isExpired(currentTimeMillis)) {
            request.addMarker("cache-hit-expired");
            request.setCacheEntry(entry);
            if (!this.mWaitingRequestManager.maybeAddToWaitingRequests(request)) {
                sendRequestOverNetwork(request);
                return;
            }
            return;
        }
        this.mBlockingExecutor.execute(new CacheParseTask(request, entry, currentTimeMillis));
    }

    /* access modifiers changed from: private */
    public void onCacheInitializationComplete() {
        ArrayList arrayList;
        synchronized (this.mCacheInitializationLock) {
            arrayList = new ArrayList(this.mRequestsAwaitingCacheInitialization);
            this.mRequestsAwaitingCacheInitialization.clear();
            this.mIsCacheInitialized = true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            beginRequest((Request) it.next());
        }
    }

    public <T> void beginRequest(Request<T> request) {
        if (!this.mIsCacheInitialized) {
            synchronized (this.mCacheInitializationLock) {
                try {
                    if (!this.mIsCacheInitialized) {
                        this.mRequestsAwaitingCacheInitialization.add(request);
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (!request.shouldCache()) {
            sendRequestOverNetwork(request);
        } else if (this.mAsyncCache != null) {
            this.mNonBlockingExecutor.execute(new CacheTask(request));
        } else {
            this.mBlockingExecutor.execute(new CacheTask(request));
        }
    }

    public <T> void sendRequestOverNetwork(Request<T> request) {
        this.mNonBlockingExecutor.execute(new NetworkTask(request));
    }

    public void start() {
        stop();
        this.mNonBlockingExecutor = this.mExecutorFactory.createNonBlockingExecutor(getBlockingQueue());
        this.mBlockingExecutor = this.mExecutorFactory.createBlockingExecutor(getBlockingQueue());
        this.mNonBlockingScheduledExecutor = this.mExecutorFactory.createNonBlockingScheduledExecutor();
        this.mNetwork.setBlockingExecutor(this.mBlockingExecutor);
        this.mNetwork.setNonBlockingExecutor(this.mNonBlockingExecutor);
        this.mNetwork.setNonBlockingScheduledExecutor(this.mNonBlockingScheduledExecutor);
        if (this.mAsyncCache != null) {
            this.mNonBlockingExecutor.execute(new Runnable() {
                public void run() {
                    AsyncRequestQueue.this.mAsyncCache.initialize(new AsyncCache.OnWriteCompleteCallback() {
                        public void onWriteComplete() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            });
        } else {
            this.mBlockingExecutor.execute(new Runnable() {
                public void run() {
                    AsyncRequestQueue.this.getCache().initialize();
                    AsyncRequestQueue.this.mNonBlockingExecutor.execute(new Runnable() {
                        public void run() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            });
        }
    }

    public void stop() {
        ExecutorService executorService = this.mNonBlockingExecutor;
        if (executorService != null) {
            executorService.shutdownNow();
            this.mNonBlockingExecutor = null;
        }
        ExecutorService executorService2 = this.mBlockingExecutor;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            this.mBlockingExecutor = null;
        }
        ScheduledExecutorService scheduledExecutorService = this.mNonBlockingScheduledExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mNonBlockingScheduledExecutor = null;
        }
    }

    private AsyncRequestQueue(Cache cache, AsyncNetwork asyncNetwork, @Nullable AsyncCache asyncCache, ResponseDelivery responseDelivery, ExecutorFactory executorFactory) {
        super(cache, asyncNetwork, 0, responseDelivery);
        this.mWaitingRequestManager = new WaitingRequestManager(this);
        this.mRequestsAwaitingCacheInitialization = new ArrayList();
        this.mIsCacheInitialized = false;
        this.mCacheInitializationLock = new Object[0];
        this.mAsyncCache = asyncCache;
        this.mNetwork = asyncNetwork;
        this.mExecutorFactory = executorFactory;
    }
}
