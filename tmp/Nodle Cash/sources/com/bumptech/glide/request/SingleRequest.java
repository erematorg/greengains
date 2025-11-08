package com.bumptech.glide.request;

import A.a;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.DrawableRes;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.GlideTrace;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback {
    private static final String GLIDE_TAG = "Glide";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);
    private static final String TAG = "GlideRequest";
    private final TransitionFactory<? super R> animationFactory;
    private final Executor callbackExecutor;
    private final Context context;
    private int cookie;
    private volatile Engine engine;
    @GuardedBy("requestLock")
    @Nullable
    private Drawable errorDrawable;
    @GuardedBy("requestLock")
    @Nullable
    private Drawable fallbackDrawable;
    private final GlideContext glideContext;
    @GuardedBy("requestLock")
    private int height;
    @GuardedBy("requestLock")
    private boolean isCallingCallbacks;
    @GuardedBy("requestLock")
    private Engine.LoadStatus loadStatus;
    @Nullable
    private final Object model;
    private final int overrideHeight;
    private final int overrideWidth;
    @GuardedBy("requestLock")
    @Nullable
    private Drawable placeholderDrawable;
    private final Priority priority;
    private final RequestCoordinator requestCoordinator;
    @Nullable
    private final List<RequestListener<R>> requestListeners;
    private final Object requestLock;
    private final BaseRequestOptions<?> requestOptions;
    @Nullable
    private RuntimeException requestOrigin;
    @GuardedBy("requestLock")
    private Resource<R> resource;
    @GuardedBy("requestLock")
    private long startTime;
    private final StateVerifier stateVerifier;
    @GuardedBy("requestLock")
    private Status status;
    @Nullable
    private final String tag;
    private final Target<R> target;
    @Nullable
    private final RequestListener<R> targetListener;
    private final Class<R> transcodeClass;
    @GuardedBy("requestLock")
    private int width;

    public enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    private SingleRequest(Context context2, GlideContext glideContext2, @NonNull Object obj, @Nullable Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i3, int i4, Priority priority2, Target<R> target2, @Nullable RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory, Executor executor) {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
        this.requestLock = obj;
        this.context = context2;
        this.glideContext = glideContext2;
        this.model = obj2;
        this.transcodeClass = cls;
        this.requestOptions = baseRequestOptions;
        this.overrideWidth = i3;
        this.overrideHeight = i4;
        this.priority = priority2;
        this.target = target2;
        this.targetListener = requestListener;
        this.requestListeners = list;
        this.requestCoordinator = requestCoordinator2;
        this.engine = engine2;
        this.animationFactory = transitionFactory;
        this.callbackExecutor = executor;
        this.status = Status.PENDING;
        if (this.requestOrigin == null && glideContext2.getExperiments().isEnabled(GlideBuilder.LogRequestOrigins.class)) {
            this.requestOrigin = new RuntimeException("Glide request origin trace");
        }
    }

    @GuardedBy("requestLock")
    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    @GuardedBy("requestLock")
    private boolean canNotifyCleared() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyCleared(this);
    }

    @GuardedBy("requestLock")
    private boolean canNotifyStatusChanged() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyStatusChanged(this);
    }

    @GuardedBy("requestLock")
    private boolean canSetResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canSetImage(this);
    }

    @GuardedBy("requestLock")
    private void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        Engine.LoadStatus loadStatus2 = this.loadStatus;
        if (loadStatus2 != null) {
            loadStatus2.cancel();
            this.loadStatus = null;
        }
    }

    private void experimentalNotifyRequestStarted(Object obj) {
        List<RequestListener<R>> list = this.requestListeners;
        if (list != null) {
            for (RequestListener next : list) {
                if (next instanceof ExperimentalRequestListener) {
                    ((ExperimentalRequestListener) next).onRequestStarted(obj);
                }
            }
        }
    }

    @GuardedBy("requestLock")
    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            Drawable errorPlaceholder = this.requestOptions.getErrorPlaceholder();
            this.errorDrawable = errorPlaceholder;
            if (errorPlaceholder == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    @GuardedBy("requestLock")
    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            Drawable fallbackDrawable2 = this.requestOptions.getFallbackDrawable();
            this.fallbackDrawable = fallbackDrawable2;
            if (fallbackDrawable2 == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    @GuardedBy("requestLock")
    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            Drawable placeholderDrawable2 = this.requestOptions.getPlaceholderDrawable();
            this.placeholderDrawable = placeholderDrawable2;
            if (placeholderDrawable2 == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    @GuardedBy("requestLock")
    private boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || !requestCoordinator2.getRoot().isAnyResourceSet();
    }

    @GuardedBy("requestLock")
    private Drawable loadDrawable(@DrawableRes int i3) {
        return DrawableDecoderCompat.getDrawable(this.context, i3, this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme());
    }

    private void logV(String str) {
        StringBuilder q2 = a.q(str, " this: ");
        q2.append(this.tag);
        Log.v(TAG, q2.toString());
    }

    private static int maybeApplySizeMultiplier(int i3, float f2) {
        return i3 == Integer.MIN_VALUE ? i3 : Math.round(f2 * ((float) i3));
    }

    @GuardedBy("requestLock")
    private void notifyRequestCoordinatorLoadFailed() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestFailed(this);
        }
    }

    @GuardedBy("requestLock")
    private void notifyRequestCoordinatorLoadSucceeded() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestSuccess(this);
        }
    }

    public static <R> SingleRequest<R> obtain(Context context2, GlideContext glideContext2, Object obj, Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i3, int i4, Priority priority2, Target<R> target2, RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory, Executor executor) {
        return new SingleRequest(context2, glideContext2, obj, obj2, cls, baseRequestOptions, i3, i4, priority2, target2, requestListener, list, requestCoordinator2, engine2, transitionFactory, executor);
    }

    @GuardedBy("requestLock")
    private void setErrorPlaceholder() {
        if (canNotifyStatusChanged()) {
            Drawable fallbackDrawable2 = this.model == null ? getFallbackDrawable() : null;
            if (fallbackDrawable2 == null) {
                fallbackDrawable2 = getErrorDrawable();
            }
            if (fallbackDrawable2 == null) {
                fallbackDrawable2 = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(fallbackDrawable2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void begin() {
        /*
            r6 = this;
            java.lang.String r0 = "finished run method in "
            java.lang.Object r1 = r6.requestLock
            monitor-enter(r1)
            r6.assertNotCallingCallbacks()     // Catch:{ all -> 0x002a }
            com.bumptech.glide.util.pool.StateVerifier r2 = r6.stateVerifier     // Catch:{ all -> 0x002a }
            r2.throwIfRecycled()     // Catch:{ all -> 0x002a }
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x002a }
            r6.startTime = r2     // Catch:{ all -> 0x002a }
            java.lang.Object r2 = r6.model     // Catch:{ all -> 0x002a }
            if (r2 != 0) goto L_0x0042
            int r0 = r6.overrideWidth     // Catch:{ all -> 0x002a }
            int r2 = r6.overrideHeight     // Catch:{ all -> 0x002a }
            boolean r0 = com.bumptech.glide.util.Util.isValidDimensions(r0, r2)     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x002d
            int r0 = r6.overrideWidth     // Catch:{ all -> 0x002a }
            r6.width = r0     // Catch:{ all -> 0x002a }
            int r0 = r6.overrideHeight     // Catch:{ all -> 0x002a }
            r6.height = r0     // Catch:{ all -> 0x002a }
            goto L_0x002d
        L_0x002a:
            r6 = move-exception
            goto L_0x00b4
        L_0x002d:
            android.graphics.drawable.Drawable r0 = r6.getFallbackDrawable()     // Catch:{ all -> 0x002a }
            if (r0 != 0) goto L_0x0035
            r0 = 5
            goto L_0x0036
        L_0x0035:
            r0 = 3
        L_0x0036:
            com.bumptech.glide.load.engine.GlideException r2 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x002a }
            java.lang.String r3 = "Received null model"
            r2.<init>(r3)     // Catch:{ all -> 0x002a }
            r6.onLoadFailed(r2, r0)     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return
        L_0x0042:
            com.bumptech.glide.request.SingleRequest$Status r3 = r6.status     // Catch:{ all -> 0x002a }
            com.bumptech.glide.request.SingleRequest$Status r4 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x002a }
            if (r3 == r4) goto L_0x00ac
            com.bumptech.glide.request.SingleRequest$Status r5 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x002a }
            if (r3 != r5) goto L_0x0056
            com.bumptech.glide.load.engine.Resource<R> r0 = r6.resource     // Catch:{ all -> 0x002a }
            com.bumptech.glide.load.DataSource r2 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x002a }
            r3 = 0
            r6.onResourceReady(r0, r2, r3)     // Catch:{ all -> 0x002a }
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return
        L_0x0056:
            r6.experimentalNotifyRequestStarted(r2)     // Catch:{ all -> 0x002a }
            java.lang.String r2 = "GlideRequest"
            int r2 = com.bumptech.glide.util.pool.GlideTrace.beginSectionAsync(r2)     // Catch:{ all -> 0x002a }
            r6.cookie = r2     // Catch:{ all -> 0x002a }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x002a }
            r6.status = r2     // Catch:{ all -> 0x002a }
            int r3 = r6.overrideWidth     // Catch:{ all -> 0x002a }
            int r5 = r6.overrideHeight     // Catch:{ all -> 0x002a }
            boolean r3 = com.bumptech.glide.util.Util.isValidDimensions(r3, r5)     // Catch:{ all -> 0x002a }
            if (r3 == 0) goto L_0x0077
            int r3 = r6.overrideWidth     // Catch:{ all -> 0x002a }
            int r5 = r6.overrideHeight     // Catch:{ all -> 0x002a }
            r6.onSizeReady(r3, r5)     // Catch:{ all -> 0x002a }
            goto L_0x007c
        L_0x0077:
            com.bumptech.glide.request.target.Target<R> r3 = r6.target     // Catch:{ all -> 0x002a }
            r3.getSize(r6)     // Catch:{ all -> 0x002a }
        L_0x007c:
            com.bumptech.glide.request.SingleRequest$Status r3 = r6.status     // Catch:{ all -> 0x002a }
            if (r3 == r4) goto L_0x0082
            if (r3 != r2) goto L_0x0091
        L_0x0082:
            boolean r2 = r6.canNotifyStatusChanged()     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0091
            com.bumptech.glide.request.target.Target<R> r2 = r6.target     // Catch:{ all -> 0x002a }
            android.graphics.drawable.Drawable r3 = r6.getPlaceholderDrawable()     // Catch:{ all -> 0x002a }
            r2.onLoadStarted(r3)     // Catch:{ all -> 0x002a }
        L_0x0091:
            boolean r2 = IS_VERBOSE_LOGGABLE     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x00aa
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x002a }
            r2.<init>(r0)     // Catch:{ all -> 0x002a }
            long r3 = r6.startTime     // Catch:{ all -> 0x002a }
            double r3 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch:{ all -> 0x002a }
            r2.append(r3)     // Catch:{ all -> 0x002a }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x002a }
            r6.logV(r0)     // Catch:{ all -> 0x002a }
        L_0x00aa:
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            return
        L_0x00ac:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x002a }
            java.lang.String r0 = "Cannot restart a running request"
            r6.<init>(r0)     // Catch:{ all -> 0x002a }
            throw r6     // Catch:{ all -> 0x002a }
        L_0x00b4:
            monitor-exit(r1)     // Catch:{ all -> 0x002a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.begin():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        r5.engine.release(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.requestLock
            monitor-enter(r0)
            r5.assertNotCallingCallbacks()     // Catch:{ all -> 0x0013 }
            com.bumptech.glide.util.pool.StateVerifier r1 = r5.stateVerifier     // Catch:{ all -> 0x0013 }
            r1.throwIfRecycled()     // Catch:{ all -> 0x0013 }
            com.bumptech.glide.request.SingleRequest$Status r1 = r5.status     // Catch:{ all -> 0x0013 }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.CLEARED     // Catch:{ all -> 0x0013 }
            if (r1 != r2) goto L_0x0015
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return
        L_0x0013:
            r5 = move-exception
            goto L_0x0042
        L_0x0015:
            r5.cancel()     // Catch:{ all -> 0x0013 }
            com.bumptech.glide.load.engine.Resource<R> r1 = r5.resource     // Catch:{ all -> 0x0013 }
            r3 = 0
            if (r1 == 0) goto L_0x0020
            r5.resource = r3     // Catch:{ all -> 0x0013 }
            goto L_0x0021
        L_0x0020:
            r1 = r3
        L_0x0021:
            boolean r3 = r5.canNotifyCleared()     // Catch:{ all -> 0x0013 }
            if (r3 == 0) goto L_0x0030
            com.bumptech.glide.request.target.Target<R> r3 = r5.target     // Catch:{ all -> 0x0013 }
            android.graphics.drawable.Drawable r4 = r5.getPlaceholderDrawable()     // Catch:{ all -> 0x0013 }
            r3.onLoadCleared(r4)     // Catch:{ all -> 0x0013 }
        L_0x0030:
            java.lang.String r3 = "GlideRequest"
            int r4 = r5.cookie     // Catch:{ all -> 0x0013 }
            com.bumptech.glide.util.pool.GlideTrace.endSectionAsync(r3, r4)     // Catch:{ all -> 0x0013 }
            r5.status = r2     // Catch:{ all -> 0x0013 }
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            if (r1 == 0) goto L_0x0041
            com.bumptech.glide.load.engine.Engine r5 = r5.engine
            r5.release(r1)
        L_0x0041:
            return
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.clear():void");
    }

    public Object getLock() {
        this.stateVerifier.throwIfRecycled();
        return this.requestLock;
    }

    public boolean isAnyResourceSet() {
        boolean z2;
        synchronized (this.requestLock) {
            z2 = this.status == Status.COMPLETE;
        }
        return z2;
    }

    public boolean isCleared() {
        boolean z2;
        synchronized (this.requestLock) {
            z2 = this.status == Status.CLEARED;
        }
        return z2;
    }

    public boolean isComplete() {
        boolean z2;
        synchronized (this.requestLock) {
            z2 = this.status == Status.COMPLETE;
        }
        return z2;
    }

    public boolean isEquivalentTo(Request request) {
        int i3;
        int i4;
        Object obj;
        Class<R> cls;
        BaseRequestOptions<?> baseRequestOptions;
        Priority priority2;
        int size;
        int i5;
        int i6;
        Object obj2;
        Class<R> cls2;
        BaseRequestOptions<?> baseRequestOptions2;
        Priority priority3;
        int size2;
        if (!(request instanceof SingleRequest)) {
            return false;
        }
        synchronized (this.requestLock) {
            try {
                i3 = this.overrideWidth;
                i4 = this.overrideHeight;
                obj = this.model;
                cls = this.transcodeClass;
                baseRequestOptions = this.requestOptions;
                priority2 = this.priority;
                List<RequestListener<R>> list = this.requestListeners;
                size = list != null ? list.size() : 0;
            } finally {
                while (true) {
                }
            }
        }
        SingleRequest singleRequest = (SingleRequest) request;
        synchronized (singleRequest.requestLock) {
            try {
                i5 = singleRequest.overrideWidth;
                i6 = singleRequest.overrideHeight;
                obj2 = singleRequest.model;
                cls2 = singleRequest.transcodeClass;
                baseRequestOptions2 = singleRequest.requestOptions;
                priority3 = singleRequest.priority;
                List<RequestListener<R>> list2 = singleRequest.requestListeners;
                size2 = list2 != null ? list2.size() : 0;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return i3 == i5 && i4 == i6 && Util.bothModelsNullEquivalentOrEquals(obj, obj2) && cls.equals(cls2) && Util.bothBaseRequestOptionsNullEquivalentOrEquals(baseRequestOptions, baseRequestOptions2) && priority2 == priority3 && size == size2;
    }

    public boolean isRunning() {
        boolean z2;
        synchronized (this.requestLock) {
            try {
                Status status2 = this.status;
                if (status2 != Status.RUNNING) {
                    if (status2 != Status.WAITING_FOR_SIZE) {
                        z2 = false;
                    }
                }
                z2 = true;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public void onLoadFailed(GlideException glideException) {
        onLoadFailed(glideException, 5);
    }

    public void onResourceReady(Resource<?> resource2, DataSource dataSource, boolean z2) {
        String str;
        this.stateVerifier.throwIfRecycled();
        Resource<?> resource3 = null;
        try {
            synchronized (this.requestLock) {
                try {
                    this.loadStatus = null;
                    if (resource2 == null) {
                        onLoadFailed(new GlideException("Expected to receive a Resource<R> with an object of " + this.transcodeClass + " inside, but instead got null."));
                        return;
                    }
                    Object obj = resource2.get();
                    if (obj != null) {
                        if (this.transcodeClass.isAssignableFrom(obj.getClass())) {
                            if (!canSetResource()) {
                                try {
                                    this.resource = null;
                                    this.status = Status.COMPLETE;
                                    GlideTrace.endSectionAsync(TAG, this.cookie);
                                    this.engine.release(resource2);
                                    return;
                                } catch (Throwable th) {
                                    resource3 = resource2;
                                    th = th;
                                    throw th;
                                }
                            } else {
                                onResourceReady(resource2, obj, dataSource, z2);
                                return;
                            }
                        }
                    }
                    this.resource = null;
                    StringBuilder sb = new StringBuilder("Expected to receive an object of ");
                    sb.append(this.transcodeClass);
                    sb.append(" but instead got ");
                    sb.append(obj != null ? obj.getClass() : "");
                    sb.append("{");
                    sb.append(obj);
                    sb.append("} inside Resource{");
                    sb.append(resource2);
                    sb.append("}.");
                    if (obj != null) {
                        str = "";
                    } else {
                        str = " To indicate failure return a null Resource object, rather than a Resource object containing null data.";
                    }
                    sb.append(str);
                    onLoadFailed(new GlideException(sb.toString()));
                    this.engine.release(resource2);
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        } catch (Throwable th3) {
            if (resource3 != null) {
                this.engine.release(resource3);
            }
            throw th3;
        }
    }

    public void onSizeReady(int i3, int i4) {
        Object obj;
        this.stateVerifier.throwIfRecycled();
        Object obj2 = this.requestLock;
        synchronized (obj2) {
            try {
                boolean z2 = IS_VERBOSE_LOGGABLE;
                if (z2) {
                    logV("Got onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
                }
                if (this.status == Status.WAITING_FOR_SIZE) {
                    Status status2 = Status.RUNNING;
                    this.status = status2;
                    float sizeMultiplier = this.requestOptions.getSizeMultiplier();
                    this.width = maybeApplySizeMultiplier(i3, sizeMultiplier);
                    this.height = maybeApplySizeMultiplier(i4, sizeMultiplier);
                    if (z2) {
                        logV("finished setup for calling load in " + LogTime.getElapsedMillis(this.startTime));
                    }
                    Engine engine2 = this.engine;
                    GlideContext glideContext2 = this.glideContext;
                    Object obj3 = this.model;
                    Key signature = this.requestOptions.getSignature();
                    int i5 = this.width;
                    int i6 = this.height;
                    Class<?> resourceClass = this.requestOptions.getResourceClass();
                    Class<R> cls = this.transcodeClass;
                    Priority priority2 = this.priority;
                    DiskCacheStrategy diskCacheStrategy = this.requestOptions.getDiskCacheStrategy();
                    Map<Class<?>, Transformation<?>> transformations = this.requestOptions.getTransformations();
                    boolean isTransformationRequired = this.requestOptions.isTransformationRequired();
                    Status status3 = status2;
                    boolean isScaleOnlyOrNoTransform = this.requestOptions.isScaleOnlyOrNoTransform();
                    String str = "finished onSizeReady in ";
                    Options options = this.requestOptions.getOptions();
                    Status status4 = status3;
                    obj = obj2;
                    String str2 = str;
                    try {
                        this.loadStatus = engine2.load(glideContext2, obj3, signature, i5, i6, resourceClass, cls, priority2, diskCacheStrategy, transformations, isTransformationRequired, isScaleOnlyOrNoTransform, options, this.requestOptions.isMemoryCacheable(), this.requestOptions.getUseUnlimitedSourceGeneratorsPool(), this.requestOptions.getUseAnimationPool(), this.requestOptions.getOnlyRetrieveFromCache(), this, this.callbackExecutor);
                        if (this.status != status4) {
                            this.loadStatus = null;
                        }
                        if (z2) {
                            logV(str2 + LogTime.getElapsedMillis(this.startTime));
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                obj = obj2;
                throw th;
            }
        }
    }

    public void pause() {
        synchronized (this.requestLock) {
            try {
                if (isRunning()) {
                    clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String toString() {
        Object obj;
        Class<R> cls;
        synchronized (this.requestLock) {
            obj = this.model;
            cls = this.transcodeClass;
        }
        return super.toString() + "[model=" + obj + ", transcodeClass=" + cls + "]";
    }

    private void onLoadFailed(GlideException glideException, int i3) {
        boolean z2;
        this.stateVerifier.throwIfRecycled();
        synchronized (this.requestLock) {
            try {
                glideException.setOrigin(this.requestOrigin);
                int logLevel = this.glideContext.getLogLevel();
                if (logLevel <= i3) {
                    Log.w(GLIDE_TAG, "Load failed for [" + this.model + "] with dimensions [" + this.width + "x" + this.height + "]", glideException);
                    if (logLevel <= 4) {
                        glideException.logRootCauses(GLIDE_TAG);
                    }
                }
                this.loadStatus = null;
                this.status = Status.FAILED;
                notifyRequestCoordinatorLoadFailed();
                boolean z3 = true;
                this.isCallingCallbacks = true;
                List<RequestListener<R>> list = this.requestListeners;
                if (list != null) {
                    z2 = false;
                    for (RequestListener<R> onLoadFailed : list) {
                        z2 |= onLoadFailed.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
                    }
                } else {
                    z2 = false;
                }
                RequestListener<R> requestListener = this.targetListener;
                if (requestListener == null || !requestListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource())) {
                    z3 = false;
                }
                if (!z2 && !z3) {
                    setErrorPlaceholder();
                }
                this.isCallingCallbacks = false;
                GlideTrace.endSectionAsync(TAG, this.cookie);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d1 A[Catch:{ all -> 0x00b3 }] */
    @androidx.annotation.GuardedBy("requestLock")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onResourceReady(com.bumptech.glide.load.engine.Resource<R> r16, R r17, com.bumptech.glide.load.DataSource r18, boolean r19) {
        /*
            r15 = this;
            r1 = r15
            r0 = r18
            boolean r9 = r15.isFirstReadyResource()
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE
            r1.status = r2
            r2 = r16
            r1.resource = r2
            com.bumptech.glide.GlideContext r2 = r1.glideContext
            int r2 = r2.getLogLevel()
            r3 = 3
            if (r2 > r3) goto L_0x006d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Finished loading "
            r2.<init>(r3)
            java.lang.Class r3 = r17.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r2.append(r3)
            java.lang.String r3 = " from "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = " for "
            r2.append(r3)
            java.lang.Object r3 = r1.model
            r2.append(r3)
            java.lang.String r3 = " with size ["
            r2.append(r3)
            int r3 = r1.width
            r2.append(r3)
            java.lang.String r3 = "x"
            r2.append(r3)
            int r3 = r1.height
            r2.append(r3)
            java.lang.String r3 = "] in "
            r2.append(r3)
            long r3 = r1.startTime
            double r3 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)
            r2.append(r3)
            java.lang.String r3 = " ms"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "Glide"
            android.util.Log.d(r3, r2)
        L_0x006d:
            r15.notifyRequestCoordinatorLoadSucceeded()
            r10 = 1
            r1.isCallingCallbacks = r10
            r11 = 0
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r2 = r1.requestListeners     // Catch:{ all -> 0x00b3 }
            if (r2 == 0) goto L_0x00b7
            java.util.Iterator r12 = r2.iterator()     // Catch:{ all -> 0x00b3 }
            r8 = r11
        L_0x007d:
            boolean r2 = r12.hasNext()     // Catch:{ all -> 0x00b3 }
            if (r2 == 0) goto L_0x00b8
            java.lang.Object r2 = r12.next()     // Catch:{ all -> 0x00b3 }
            r13 = r2
            com.bumptech.glide.request.RequestListener r13 = (com.bumptech.glide.request.RequestListener) r13     // Catch:{ all -> 0x00b3 }
            java.lang.Object r4 = r1.model     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.request.target.Target<R> r5 = r1.target     // Catch:{ all -> 0x00b3 }
            r2 = r13
            r3 = r17
            r6 = r18
            r7 = r9
            boolean r2 = r2.onResourceReady(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00b3 }
            r14 = r8 | r2
            boolean r2 = r13 instanceof com.bumptech.glide.request.ExperimentalRequestListener     // Catch:{ all -> 0x00b3 }
            if (r2 == 0) goto L_0x00b5
            r2 = r13
            com.bumptech.glide.request.ExperimentalRequestListener r2 = (com.bumptech.glide.request.ExperimentalRequestListener) r2     // Catch:{ all -> 0x00b3 }
            java.lang.Object r4 = r1.model     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.request.target.Target<R> r5 = r1.target     // Catch:{ all -> 0x00b3 }
            r3 = r17
            r6 = r18
            r7 = r9
            r8 = r19
            boolean r2 = r2.onResourceReady(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00b3 }
            r2 = r2 | r14
            r8 = r2
            goto L_0x007d
        L_0x00b3:
            r0 = move-exception
            goto L_0x00e8
        L_0x00b5:
            r8 = r14
            goto L_0x007d
        L_0x00b7:
            r8 = r11
        L_0x00b8:
            com.bumptech.glide.request.RequestListener<R> r2 = r1.targetListener     // Catch:{ all -> 0x00b3 }
            if (r2 == 0) goto L_0x00cc
            java.lang.Object r4 = r1.model     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.request.target.Target<R> r5 = r1.target     // Catch:{ all -> 0x00b3 }
            r3 = r17
            r6 = r18
            r7 = r9
            boolean r2 = r2.onResourceReady(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00b3 }
            if (r2 == 0) goto L_0x00cc
            goto L_0x00cd
        L_0x00cc:
            r10 = r11
        L_0x00cd:
            r2 = r8 | r10
            if (r2 != 0) goto L_0x00de
            com.bumptech.glide.request.transition.TransitionFactory<? super R> r2 = r1.animationFactory     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.request.transition.Transition r0 = r2.build(r0, r9)     // Catch:{ all -> 0x00b3 }
            com.bumptech.glide.request.target.Target<R> r2 = r1.target     // Catch:{ all -> 0x00b3 }
            r3 = r17
            r2.onResourceReady(r3, r0)     // Catch:{ all -> 0x00b3 }
        L_0x00de:
            r1.isCallingCallbacks = r11
            java.lang.String r0 = "GlideRequest"
            int r1 = r1.cookie
            com.bumptech.glide.util.pool.GlideTrace.endSectionAsync(r0, r1)
            return
        L_0x00e8:
            r1.isCallingCallbacks = r11
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, java.lang.Object, com.bumptech.glide.load.DataSource, boolean):void");
    }
}
