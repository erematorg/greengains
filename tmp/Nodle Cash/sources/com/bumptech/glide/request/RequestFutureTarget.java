package com.bumptech.glide.request;

import A.a;
import android.graphics.drawable.Drawable;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFutureTarget<R> implements FutureTarget<R>, RequestListener<R> {
    private static final Waiter DEFAULT_WAITER = new Waiter();
    private final boolean assertBackgroundThread;
    @GuardedBy("this")
    @Nullable
    private GlideException exception;
    private final int height;
    @GuardedBy("this")
    private boolean isCancelled;
    @GuardedBy("this")
    private boolean loadFailed;
    @GuardedBy("this")
    @Nullable
    private Request request;
    @GuardedBy("this")
    @Nullable
    private R resource;
    @GuardedBy("this")
    private boolean resultReceived;
    private final Waiter waiter;
    private final int width;

    @VisibleForTesting
    public static class Waiter {
        public void notifyAll(Object obj) {
            obj.notifyAll();
        }

        public void waitForTimeout(Object obj, long j2) throws InterruptedException {
            obj.wait(j2);
        }
    }

    public RequestFutureTarget(int i3, int i4) {
        this(i3, i4, true, DEFAULT_WAITER);
    }

    private synchronized R doGet(Long l2) throws ExecutionException, InterruptedException, TimeoutException {
        try {
            if (this.assertBackgroundThread && !isDone()) {
                Util.assertBackgroundThread();
            }
            if (this.isCancelled) {
                throw new CancellationException();
            } else if (this.loadFailed) {
                throw new ExecutionException(this.exception);
            } else if (this.resultReceived) {
                return this.resource;
            } else {
                if (l2 == null) {
                    this.waiter.waitForTimeout(this, 0);
                } else if (l2.longValue() > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long longValue = l2.longValue() + currentTimeMillis;
                    while (!isDone() && currentTimeMillis < longValue) {
                        this.waiter.waitForTimeout(this, longValue - currentTimeMillis);
                        currentTimeMillis = System.currentTimeMillis();
                    }
                }
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                } else if (this.loadFailed) {
                    throw new ExecutionException(this.exception);
                } else if (this.isCancelled) {
                    throw new CancellationException();
                } else if (this.resultReceived) {
                    return this.resource;
                } else {
                    throw new TimeoutException();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        if (r1 == null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r1.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean cancel(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isDone()     // Catch:{ all -> 0x000a }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r2)     // Catch:{ all -> 0x000a }
            r2 = 0
            return r2
        L_0x000a:
            r3 = move-exception
            goto L_0x0023
        L_0x000c:
            r0 = 1
            r2.isCancelled = r0     // Catch:{ all -> 0x000a }
            com.bumptech.glide.request.RequestFutureTarget$Waiter r1 = r2.waiter     // Catch:{ all -> 0x000a }
            r1.notifyAll(r2)     // Catch:{ all -> 0x000a }
            r1 = 0
            if (r3 == 0) goto L_0x001c
            com.bumptech.glide.request.Request r3 = r2.request     // Catch:{ all -> 0x000a }
            r2.request = r1     // Catch:{ all -> 0x000a }
            r1 = r3
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x000a }
            if (r1 == 0) goto L_0x0022
            r1.clear()
        L_0x0022:
            return r0
        L_0x0023:
            monitor-exit(r2)     // Catch:{ all -> 0x000a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestFutureTarget.cancel(boolean):boolean");
    }

    public R get() throws InterruptedException, ExecutionException {
        try {
            return doGet((Long) null);
        } catch (TimeoutException e3) {
            throw new AssertionError(e3);
        }
    }

    @Nullable
    public synchronized Request getRequest() {
        return this.request;
    }

    public void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(this.width, this.height);
    }

    public synchronized boolean isCancelled() {
        return this.isCancelled;
    }

    public synchronized boolean isDone() {
        return this.isCancelled || this.resultReceived || this.loadFailed;
    }

    public void onDestroy() {
    }

    public void onLoadCleared(@Nullable Drawable drawable) {
    }

    public synchronized void onLoadFailed(@Nullable Drawable drawable) {
    }

    public void onLoadStarted(@Nullable Drawable drawable) {
    }

    public synchronized void onResourceReady(@NonNull R r2, @Nullable Transition<? super R> transition) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
    }

    public synchronized void setRequest(@Nullable Request request2) {
        this.request = request2;
    }

    public String toString() {
        Request request2;
        String str;
        String n2 = a.n(new StringBuilder(), super.toString(), "[status=");
        synchronized (this) {
            try {
                request2 = null;
                if (this.isCancelled) {
                    str = "CANCELLED";
                } else if (this.loadFailed) {
                    str = "FAILURE";
                } else if (this.resultReceived) {
                    str = "SUCCESS";
                } else {
                    str = "PENDING";
                    request2 = this.request;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (request2 == null) {
            return android.support.v4.media.session.a.n(n2, str, "]");
        }
        return n2 + str + ", request=[" + request2 + "]]";
    }

    public RequestFutureTarget(int i3, int i4, boolean z2, Waiter waiter2) {
        this.width = i3;
        this.height = i4;
        this.assertBackgroundThread = z2;
        this.waiter = waiter2;
    }

    public synchronized boolean onLoadFailed(@Nullable GlideException glideException, Object obj, @NonNull Target<R> target, boolean z2) {
        this.loadFailed = true;
        this.exception = glideException;
        this.waiter.notifyAll(this);
        return false;
    }

    public synchronized boolean onResourceReady(@NonNull R r2, @NonNull Object obj, Target<R> target, @NonNull DataSource dataSource, boolean z2) {
        this.resultReceived = true;
        this.resource = r2;
        this.waiter.notifyAll(this);
        return false;
    }

    public R get(long j2, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return doGet(Long.valueOf(timeUnit.toMillis(j2)));
    }
}
