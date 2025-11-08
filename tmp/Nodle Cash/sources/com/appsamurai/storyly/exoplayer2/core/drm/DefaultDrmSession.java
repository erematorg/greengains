package com.appsamurai.storyly.exoplayer2.core.drm;

import android.annotation.SuppressLint;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Consumer;
import com.appsamurai.storyly.exoplayer2.common.util.CopyOnWriteMultiset;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoConfig;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

@RequiresApi(18)
class DefaultDrmSession implements DrmSession {
    private static final int MAX_LICENSE_DURATION_TO_RENEW_SECONDS = 60;
    private static final int MSG_KEYS = 1;
    private static final int MSG_PROVISION = 0;
    private static final String TAG = "DefaultDrmSession";
    final MediaDrmCallback callback;
    @Nullable
    private CryptoConfig cryptoConfig;
    @Nullable
    private ExoMediaDrm.KeyRequest currentKeyRequest;
    @Nullable
    private ExoMediaDrm.ProvisionRequest currentProvisionRequest;
    private final CopyOnWriteMultiset<DrmSessionEventListener.EventDispatcher> eventDispatchers;
    private final boolean isPlaceholderSession;
    private final HashMap<String, String> keyRequestParameters;
    @Nullable
    private DrmSession.DrmSessionException lastException;
    /* access modifiers changed from: private */
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final ExoMediaDrm mediaDrm;
    private final int mode;
    private byte[] offlineLicenseKeySetId;
    private final boolean playClearSamplesWithoutKeys;
    private final PlayerId playerId;
    private final ProvisioningManager provisioningManager;
    private int referenceCount;
    private final ReferenceCountListener referenceCountListener;
    @Nullable
    private RequestHandler requestHandler;
    @Nullable
    private HandlerThread requestHandlerThread;
    final ResponseHandler responseHandler;
    @Nullable
    public final List<DrmInitData.SchemeData> schemeDatas;
    @Nullable
    private byte[] sessionId;
    private int state;
    final UUID uuid;

    public interface ProvisioningManager {
        void onProvisionCompleted();

        void onProvisionError(Exception exc, boolean z2);

        void provisionRequired(DefaultDrmSession defaultDrmSession);
    }

    public interface ReferenceCountListener {
        void onReferenceCountDecremented(DefaultDrmSession defaultDrmSession, int i3);

        void onReferenceCountIncremented(DefaultDrmSession defaultDrmSession, int i3);
    }

    @SuppressLint({"HandlerLeak"})
    public class RequestHandler extends Handler {
        @GuardedBy("this")
        private boolean isReleased;

        public RequestHandler(Looper looper) {
            super(looper);
        }

        private boolean maybeRetryRequest(Message message, MediaDrmCallbackException mediaDrmCallbackException) {
            MediaDrmCallbackException mediaDrmCallbackException2 = mediaDrmCallbackException;
            RequestTask requestTask = (RequestTask) message.obj;
            if (!requestTask.allowRetry) {
                return false;
            }
            int i3 = requestTask.errorCount + 1;
            requestTask.errorCount = i3;
            if (i3 > DefaultDrmSession.this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(3)) {
                return false;
            }
            long retryDelayMsFor = DefaultDrmSession.this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(new LoadEventInfo(requestTask.taskId, mediaDrmCallbackException2.dataSpec, mediaDrmCallbackException2.uriAfterRedirects, mediaDrmCallbackException2.responseHeaders, SystemClock.elapsedRealtime(), SystemClock.elapsedRealtime() - requestTask.startTimeMs, mediaDrmCallbackException2.bytesLoaded), new MediaLoadData(3), mediaDrmCallbackException.getCause() instanceof IOException ? (IOException) mediaDrmCallbackException.getCause() : new UnexpectedDrmSessionException(mediaDrmCallbackException.getCause()), requestTask.errorCount));
            if (retryDelayMsFor == C.TIME_UNSET) {
                return false;
            }
            synchronized (this) {
                try {
                    if (this.isReleased) {
                        return false;
                    }
                    sendMessageDelayed(Message.obtain(message), retryDelayMsFor);
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.appsamurai.storyly.exoplayer2.core.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.appsamurai.storyly.exoplayer2.core.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: com.appsamurai.storyly.exoplayer2.core.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: byte[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: byte[]} */
        /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, java.lang.Exception] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r6.obj
                com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession$RequestTask r0 = (com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession.RequestTask) r0
                int r1 = r6.what     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                if (r1 == 0) goto L_0x0024
                r2 = 1
                if (r1 != r2) goto L_0x001e
                com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession r1 = com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                com.appsamurai.storyly.exoplayer2.core.drm.MediaDrmCallback r2 = r1.callback     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                java.util.UUID r1 = r1.uuid     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                java.lang.Object r3 = r0.request     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm$KeyRequest r3 = (com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm.KeyRequest) r3     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                byte[] r1 = r2.executeKeyRequest(r1, r3)     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                goto L_0x0042
            L_0x001a:
                r1 = move-exception
                goto L_0x0033
            L_0x001c:
                r1 = move-exception
                goto L_0x003b
            L_0x001e:
                java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                r1.<init>()     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                throw r1     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
            L_0x0024:
                com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession r1 = com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                com.appsamurai.storyly.exoplayer2.core.drm.MediaDrmCallback r2 = r1.callback     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                java.util.UUID r1 = r1.uuid     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                java.lang.Object r3 = r0.request     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm$ProvisionRequest r3 = (com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm.ProvisionRequest) r3     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                byte[] r1 = r2.executeProvisionRequest(r1, r3)     // Catch:{ MediaDrmCallbackException -> 0x001c, Exception -> 0x001a }
                goto L_0x0042
            L_0x0033:
                java.lang.String r2 = "DefaultDrmSession"
                java.lang.String r3 = "Key/provisioning request produced an unexpected exception. Not retrying."
                com.appsamurai.storyly.exoplayer2.common.util.Log.w(r2, r3, r1)
                goto L_0x0042
            L_0x003b:
                boolean r2 = r5.maybeRetryRequest(r6, r1)
                if (r2 == 0) goto L_0x0042
                return
            L_0x0042:
                com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession r2 = com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession.this
                com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy r2 = r2.loadErrorHandlingPolicy
                long r3 = r0.taskId
                r2.onLoadTaskConcluded(r3)
                monitor-enter(r5)
                boolean r2 = r5.isReleased     // Catch:{ all -> 0x0066 }
                if (r2 != 0) goto L_0x0068
                com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession r2 = com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession.this     // Catch:{ all -> 0x0066 }
                com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession$ResponseHandler r2 = r2.responseHandler     // Catch:{ all -> 0x0066 }
                int r6 = r6.what     // Catch:{ all -> 0x0066 }
                java.lang.Object r0 = r0.request     // Catch:{ all -> 0x0066 }
                android.util.Pair r0 = android.util.Pair.create(r0, r1)     // Catch:{ all -> 0x0066 }
                android.os.Message r6 = r2.obtainMessage(r6, r0)     // Catch:{ all -> 0x0066 }
                r6.sendToTarget()     // Catch:{ all -> 0x0066 }
                goto L_0x0068
            L_0x0066:
                r6 = move-exception
                goto L_0x006a
            L_0x0068:
                monitor-exit(r5)     // Catch:{ all -> 0x0066 }
                return
            L_0x006a:
                monitor-exit(r5)     // Catch:{ all -> 0x0066 }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession.RequestHandler.handleMessage(android.os.Message):void");
        }

        public void post(int i3, Object obj, boolean z2) {
            obtainMessage(i3, new RequestTask(LoadEventInfo.getNewId(), z2, SystemClock.elapsedRealtime(), obj)).sendToTarget();
        }

        public synchronized void release() {
            removeCallbacksAndMessages((Object) null);
            this.isReleased = true;
        }
    }

    public static final class RequestTask {
        public final boolean allowRetry;
        public int errorCount;
        public final Object request;
        public final long startTimeMs;
        public final long taskId;

        public RequestTask(long j2, boolean z2, long j3, Object obj) {
            this.taskId = j2;
            this.allowRetry = z2;
            this.startTimeMs = j3;
            this.request = obj;
        }
    }

    @SuppressLint({"HandlerLeak"})
    public class ResponseHandler extends Handler {
        public ResponseHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Pair pair = (Pair) message.obj;
            Object obj = pair.first;
            Object obj2 = pair.second;
            int i3 = message.what;
            if (i3 == 0) {
                DefaultDrmSession.this.onProvisionResponse(obj, obj2);
            } else if (i3 == 1) {
                DefaultDrmSession.this.onKeyResponse(obj, obj2);
            }
        }
    }

    public static final class UnexpectedDrmSessionException extends IOException {
        public UnexpectedDrmSessionException(@Nullable Throwable th) {
            super(th);
        }
    }

    public DefaultDrmSession(UUID uuid2, ExoMediaDrm exoMediaDrm, ProvisioningManager provisioningManager2, ReferenceCountListener referenceCountListener2, @Nullable List<DrmInitData.SchemeData> list, int i3, boolean z2, boolean z3, @Nullable byte[] bArr, HashMap<String, String> hashMap, MediaDrmCallback mediaDrmCallback, Looper looper, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, PlayerId playerId2) {
        if (i3 == 1 || i3 == 3) {
            Assertions.checkNotNull(bArr);
        }
        this.uuid = uuid2;
        this.provisioningManager = provisioningManager2;
        this.referenceCountListener = referenceCountListener2;
        this.mediaDrm = exoMediaDrm;
        this.mode = i3;
        this.playClearSamplesWithoutKeys = z2;
        this.isPlaceholderSession = z3;
        if (bArr != null) {
            this.offlineLicenseKeySetId = bArr;
            this.schemeDatas = null;
        } else {
            this.schemeDatas = Collections.unmodifiableList((List) Assertions.checkNotNull(list));
        }
        this.keyRequestParameters = hashMap;
        this.callback = mediaDrmCallback;
        this.eventDispatchers = new CopyOnWriteMultiset<>();
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.playerId = playerId2;
        this.state = 2;
        this.responseHandler = new ResponseHandler(looper);
    }

    private void dispatchEvent(Consumer<DrmSessionEventListener.EventDispatcher> consumer) {
        for (DrmSessionEventListener.EventDispatcher accept : this.eventDispatchers.elementSet()) {
            consumer.accept(accept);
        }
    }

    @RequiresNonNull({"sessionId"})
    private void doLicense(boolean z2) {
        if (!this.isPlaceholderSession) {
            byte[] bArr = (byte[]) Util.castNonNull(this.sessionId);
            int i3 = this.mode;
            if (i3 == 0 || i3 == 1) {
                if (this.offlineLicenseKeySetId == null) {
                    postKeyRequest(bArr, 1, z2);
                } else if (this.state == 4 || restoreKeys()) {
                    long licenseDurationRemainingSec = getLicenseDurationRemainingSec();
                    if (this.mode == 0 && licenseDurationRemainingSec <= 60) {
                        Log.d(TAG, "Offline license has expired or will expire soon. Remaining seconds: " + licenseDurationRemainingSec);
                        postKeyRequest(bArr, 2, z2);
                    } else if (licenseDurationRemainingSec <= 0) {
                        onError(new KeysExpiredException(), 2);
                    } else {
                        this.state = 4;
                        dispatchEvent(new b(3));
                    }
                }
            } else if (i3 != 2) {
                if (i3 == 3) {
                    Assertions.checkNotNull(this.offlineLicenseKeySetId);
                    Assertions.checkNotNull(this.sessionId);
                    postKeyRequest(this.offlineLicenseKeySetId, 3, z2);
                }
            } else if (this.offlineLicenseKeySetId == null || restoreKeys()) {
                postKeyRequest(bArr, 2, z2);
            }
        }
    }

    private long getLicenseDurationRemainingSec() {
        if (!C.WIDEVINE_UUID.equals(this.uuid)) {
            return Long.MAX_VALUE;
        }
        Pair pair = (Pair) Assertions.checkNotNull(WidevineUtil.getLicenseDurationRemainingSec(this));
        return Math.min(((Long) pair.first).longValue(), ((Long) pair.second).longValue());
    }

    @EnsuresNonNullIf(expression = {"sessionId"}, result = true)
    private boolean isOpen() {
        int i3 = this.state;
        return i3 == 3 || i3 == 4;
    }

    private void onError(Exception exc, int i3) {
        this.lastException = new DrmSession.DrmSessionException(exc, DrmUtil.getErrorCodeForMediaDrmException(exc, i3));
        Log.e(TAG, "DRM session error", exc);
        dispatchEvent(new a(exc));
        if (this.state != 4) {
            this.state = 1;
        }
    }

    /* access modifiers changed from: private */
    public void onKeyResponse(Object obj, Object obj2) {
        if (obj == this.currentKeyRequest && isOpen()) {
            this.currentKeyRequest = null;
            if (obj2 instanceof Exception) {
                onKeysError((Exception) obj2, false);
                return;
            }
            try {
                byte[] bArr = (byte[]) obj2;
                if (this.mode == 3) {
                    this.mediaDrm.provideKeyResponse((byte[]) Util.castNonNull(this.offlineLicenseKeySetId), bArr);
                    dispatchEvent(new b(1));
                    return;
                }
                byte[] provideKeyResponse = this.mediaDrm.provideKeyResponse(this.sessionId, bArr);
                int i3 = this.mode;
                if (!((i3 != 2 && (i3 != 0 || this.offlineLicenseKeySetId == null)) || provideKeyResponse == null || provideKeyResponse.length == 0)) {
                    this.offlineLicenseKeySetId = provideKeyResponse;
                }
                this.state = 4;
                dispatchEvent(new b(2));
            } catch (Exception e3) {
                onKeysError(e3, true);
            }
        }
    }

    private void onKeysError(Exception exc, boolean z2) {
        if (exc instanceof NotProvisionedException) {
            this.provisioningManager.provisionRequired(this);
        } else {
            onError(exc, z2 ? 1 : 2);
        }
    }

    private void onKeysRequired() {
        if (this.mode == 0 && this.state == 4) {
            Util.castNonNull(this.sessionId);
            doLicense(false);
        }
    }

    /* access modifiers changed from: private */
    public void onProvisionResponse(Object obj, Object obj2) {
        if (obj != this.currentProvisionRequest) {
            return;
        }
        if (this.state == 2 || isOpen()) {
            this.currentProvisionRequest = null;
            if (obj2 instanceof Exception) {
                this.provisioningManager.onProvisionError((Exception) obj2, false);
                return;
            }
            try {
                this.mediaDrm.provideProvisionResponse((byte[]) obj2);
                this.provisioningManager.onProvisionCompleted();
            } catch (Exception e3) {
                this.provisioningManager.onProvisionError(e3, true);
            }
        }
    }

    @EnsuresNonNullIf(expression = {"sessionId"}, result = true)
    private boolean openInternal() {
        if (isOpen()) {
            return true;
        }
        try {
            byte[] openSession = this.mediaDrm.openSession();
            this.sessionId = openSession;
            this.mediaDrm.setPlayerIdForSession(openSession, this.playerId);
            this.cryptoConfig = this.mediaDrm.createCryptoConfig(this.sessionId);
            this.state = 3;
            dispatchEvent(new b(0));
            Assertions.checkNotNull(this.sessionId);
            return true;
        } catch (NotProvisionedException unused) {
            this.provisioningManager.provisionRequired(this);
            return false;
        } catch (Exception e3) {
            onError(e3, 1);
            return false;
        }
    }

    private void postKeyRequest(byte[] bArr, int i3, boolean z2) {
        try {
            this.currentKeyRequest = this.mediaDrm.getKeyRequest(bArr, this.schemeDatas, i3, this.keyRequestParameters);
            ((RequestHandler) Util.castNonNull(this.requestHandler)).post(1, Assertions.checkNotNull(this.currentKeyRequest), z2);
        } catch (Exception e3) {
            onKeysError(e3, true);
        }
    }

    @RequiresNonNull({"sessionId", "offlineLicenseKeySetId"})
    private boolean restoreKeys() {
        try {
            this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);
            return true;
        } catch (Exception e3) {
            onError(e3, 1);
            return false;
        }
    }

    public void acquire(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        boolean z2 = false;
        if (this.referenceCount < 0) {
            Log.e(TAG, "Session reference count less than zero: " + this.referenceCount);
            this.referenceCount = 0;
        }
        if (eventDispatcher != null) {
            this.eventDispatchers.add(eventDispatcher);
        }
        int i3 = this.referenceCount + 1;
        this.referenceCount = i3;
        if (i3 == 1) {
            if (this.state == 2) {
                z2 = true;
            }
            Assertions.checkState(z2);
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DrmRequestHandler");
            this.requestHandlerThread = handlerThread;
            handlerThread.start();
            this.requestHandler = new RequestHandler(this.requestHandlerThread.getLooper());
            if (openInternal()) {
                doLicense(true);
            }
        } else if (eventDispatcher != null && isOpen() && this.eventDispatchers.count(eventDispatcher) == 1) {
            eventDispatcher.drmSessionAcquired(this.state);
        }
        this.referenceCountListener.onReferenceCountIncremented(this, this.referenceCount);
    }

    @Nullable
    public final CryptoConfig getCryptoConfig() {
        return this.cryptoConfig;
    }

    @Nullable
    public final DrmSession.DrmSessionException getError() {
        if (this.state == 1) {
            return this.lastException;
        }
        return null;
    }

    @Nullable
    public byte[] getOfflineLicenseKeySetId() {
        return this.offlineLicenseKeySetId;
    }

    public final UUID getSchemeUuid() {
        return this.uuid;
    }

    public final int getState() {
        return this.state;
    }

    public boolean hasSessionId(byte[] bArr) {
        return Arrays.equals(this.sessionId, bArr);
    }

    public void onMediaDrmEvent(int i3) {
        if (i3 == 2) {
            onKeysRequired();
        }
    }

    public void onProvisionCompleted() {
        if (openInternal()) {
            doLicense(true);
        }
    }

    public void onProvisionError(Exception exc, boolean z2) {
        onError(exc, z2 ? 1 : 3);
    }

    public boolean playClearSamplesWithoutKeys() {
        return this.playClearSamplesWithoutKeys;
    }

    public void provision() {
        this.currentProvisionRequest = this.mediaDrm.getProvisionRequest();
        ((RequestHandler) Util.castNonNull(this.requestHandler)).post(0, Assertions.checkNotNull(this.currentProvisionRequest), true);
    }

    @Nullable
    public Map<String, String> queryKeyStatus() {
        byte[] bArr = this.sessionId;
        if (bArr == null) {
            return null;
        }
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    public void release(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        int i3 = this.referenceCount;
        if (i3 <= 0) {
            Log.e(TAG, "release() called on a session that's already fully released.");
            return;
        }
        int i4 = i3 - 1;
        this.referenceCount = i4;
        if (i4 == 0) {
            this.state = 0;
            ((ResponseHandler) Util.castNonNull(this.responseHandler)).removeCallbacksAndMessages((Object) null);
            ((RequestHandler) Util.castNonNull(this.requestHandler)).release();
            this.requestHandler = null;
            ((HandlerThread) Util.castNonNull(this.requestHandlerThread)).quit();
            this.requestHandlerThread = null;
            this.cryptoConfig = null;
            this.lastException = null;
            this.currentKeyRequest = null;
            this.currentProvisionRequest = null;
            byte[] bArr = this.sessionId;
            if (bArr != null) {
                this.mediaDrm.closeSession(bArr);
                this.sessionId = null;
            }
        }
        if (eventDispatcher != null) {
            this.eventDispatchers.remove(eventDispatcher);
            if (this.eventDispatchers.count(eventDispatcher) == 0) {
                eventDispatcher.drmSessionReleased();
            }
        }
        this.referenceCountListener.onReferenceCountDecremented(this, this.referenceCount);
    }

    public boolean requiresSecureDecoder(String str) {
        return this.mediaDrm.requiresSecureDecoder((byte[]) Assertions.checkStateNotNull(this.sessionId), str);
    }
}
