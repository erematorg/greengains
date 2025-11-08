package com.appsamurai.storyly.exoplayer2.core.drm;

import android.annotation.SuppressLint;
import android.media.ResourceBusyException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.drm.DefaultDrmSession;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm;
import com.appsamurai.storyly.exoplayer2.core.upstream.DefaultLoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

@RequiresApi(18)
public class DefaultDrmSessionManager implements DrmSessionManager {
    public static final long DEFAULT_SESSION_KEEPALIVE_MS = 300000;
    public static final int INITIAL_DRM_REQUEST_RETRY_COUNT = 3;
    public static final int MODE_DOWNLOAD = 2;
    public static final int MODE_PLAYBACK = 0;
    public static final int MODE_QUERY = 1;
    public static final int MODE_RELEASE = 3;
    public static final String PLAYREADY_CUSTOM_DATA_KEY = "PRCustomData";
    private static final String TAG = "DefaultDrmSessionMgr";
    private final MediaDrmCallback callback;
    @Nullable
    private ExoMediaDrm exoMediaDrm;
    private final ExoMediaDrm.Provider exoMediaDrmProvider;
    /* access modifiers changed from: private */
    public final Set<DefaultDrmSession> keepaliveSessions;
    private final HashMap<String, String> keyRequestParameters;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    @Nullable
    volatile MediaDrmHandler mediaDrmHandler;
    private int mode;
    private final boolean multiSession;
    /* access modifiers changed from: private */
    @Nullable
    public DefaultDrmSession noMultiSessionDrmSession;
    @Nullable
    private byte[] offlineLicenseKeySetId;
    /* access modifiers changed from: private */
    @Nullable
    public DefaultDrmSession placeholderDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    /* access modifiers changed from: private */
    public Handler playbackHandler;
    /* access modifiers changed from: private */
    public Looper playbackLooper;
    private PlayerId playerId;
    /* access modifiers changed from: private */
    public final Set<PreacquiredSessionReference> preacquiredSessionReferences;
    /* access modifiers changed from: private */
    public int prepareCallsCount;
    /* access modifiers changed from: private */
    public final ProvisioningManagerImpl provisioningManagerImpl;
    private final ReferenceCountListenerImpl referenceCountListener;
    /* access modifiers changed from: private */
    public final long sessionKeepaliveMs;
    /* access modifiers changed from: private */
    public final List<DefaultDrmSession> sessions;
    private final int[] useDrmSessionsForClearContentTrackTypes;
    private final UUID uuid;

    public static final class Builder {
        private ExoMediaDrm.Provider exoMediaDrmProvider = FrameworkMediaDrm.DEFAULT_PROVIDER;
        private final HashMap<String, String> keyRequestParameters = new HashMap<>();
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
        private boolean multiSession;
        private boolean playClearSamplesWithoutKeys;
        private long sessionKeepaliveMs = 300000;
        private int[] useDrmSessionsForClearContentTrackTypes = new int[0];
        private UUID uuid = C.WIDEVINE_UUID;

        public DefaultDrmSessionManager build(MediaDrmCallback mediaDrmCallback) {
            return new DefaultDrmSessionManager(this.uuid, this.exoMediaDrmProvider, mediaDrmCallback, this.keyRequestParameters, this.multiSession, this.useDrmSessionsForClearContentTrackTypes, this.playClearSamplesWithoutKeys, this.loadErrorHandlingPolicy, this.sessionKeepaliveMs);
        }

        public Builder setKeyRequestParameters(@Nullable Map<String, String> map) {
            this.keyRequestParameters.clear();
            if (map != null) {
                this.keyRequestParameters.putAll(map);
            }
            return this;
        }

        public Builder setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(loadErrorHandlingPolicy2);
            return this;
        }

        public Builder setMultiSession(boolean z2) {
            this.multiSession = z2;
            return this;
        }

        public Builder setPlayClearSamplesWithoutKeys(boolean z2) {
            this.playClearSamplesWithoutKeys = z2;
            return this;
        }

        public Builder setSessionKeepaliveMs(long j2) {
            Assertions.checkArgument(j2 > 0 || j2 == C.TIME_UNSET);
            this.sessionKeepaliveMs = j2;
            return this;
        }

        public Builder setUseDrmSessionsForClearContent(int... iArr) {
            for (int i3 : iArr) {
                boolean z2 = true;
                if (!(i3 == 2 || i3 == 1)) {
                    z2 = false;
                }
                Assertions.checkArgument(z2);
            }
            this.useDrmSessionsForClearContentTrackTypes = (int[]) iArr.clone();
            return this;
        }

        public Builder setUuidAndExoMediaDrmProvider(UUID uuid2, ExoMediaDrm.Provider provider) {
            this.uuid = (UUID) Assertions.checkNotNull(uuid2);
            this.exoMediaDrmProvider = (ExoMediaDrm.Provider) Assertions.checkNotNull(provider);
            return this;
        }
    }

    public class MediaDrmEventListener implements ExoMediaDrm.OnEventListener {
        private MediaDrmEventListener() {
        }

        public void onEvent(ExoMediaDrm exoMediaDrm, @Nullable byte[] bArr, int i3, int i4, @Nullable byte[] bArr2) {
            ((MediaDrmHandler) Assertions.checkNotNull(DefaultDrmSessionManager.this.mediaDrmHandler)).obtainMessage(i3, bArr).sendToTarget();
        }
    }

    @SuppressLint({"HandlerLeak"})
    public class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            if (bArr != null) {
                for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.sessions) {
                    if (defaultDrmSession.hasSessionId(bArr)) {
                        defaultDrmSession.onMediaDrmEvent(message.what);
                        return;
                    }
                }
            }
        }
    }

    public static final class MissingSchemeDataException extends Exception {
        private MissingSchemeDataException(UUID uuid) {
            super("Media does not support uuid: " + uuid);
        }
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public class PreacquiredSessionReference implements DrmSessionManager.DrmSessionReference {
        @Nullable
        private final DrmSessionEventListener.EventDispatcher eventDispatcher;
        private boolean isReleased;
        @Nullable
        private DrmSession session;

        public PreacquiredSessionReference(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher2) {
            this.eventDispatcher = eventDispatcher2;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$acquire$0(Format format) {
            if (DefaultDrmSessionManager.this.prepareCallsCount != 0 && !this.isReleased) {
                DefaultDrmSessionManager defaultDrmSessionManager = DefaultDrmSessionManager.this;
                this.session = defaultDrmSessionManager.acquireSession((Looper) Assertions.checkNotNull(defaultDrmSessionManager.playbackLooper), this.eventDispatcher, format, false);
                DefaultDrmSessionManager.this.preacquiredSessionReferences.add(this);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$release$1() {
            if (!this.isReleased) {
                DrmSession drmSession = this.session;
                if (drmSession != null) {
                    drmSession.release(this.eventDispatcher);
                }
                DefaultDrmSessionManager.this.preacquiredSessionReferences.remove(this);
                this.isReleased = true;
            }
        }

        public void acquire(Format format) {
            ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).post(new d(this, format, 4));
        }

        public void release() {
            Util.postOrRun((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler), new c(this, 0));
        }
    }

    public class ProvisioningManagerImpl implements DefaultDrmSession.ProvisioningManager {
        @Nullable
        private DefaultDrmSession provisioningSession;
        private final Set<DefaultDrmSession> sessionsAwaitingProvisioning = new HashSet();

        public ProvisioningManagerImpl() {
        }

        public void onProvisionCompleted() {
            this.provisioningSession = null;
            ImmutableList<DefaultDrmSession> copyOf = ImmutableList.copyOf(this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            UnmodifiableIterator<DefaultDrmSession> it = copyOf.iterator();
            while (it.hasNext()) {
                it.next().onProvisionCompleted();
            }
        }

        public void onProvisionError(Exception exc, boolean z2) {
            this.provisioningSession = null;
            ImmutableList<DefaultDrmSession> copyOf = ImmutableList.copyOf(this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            UnmodifiableIterator<DefaultDrmSession> it = copyOf.iterator();
            while (it.hasNext()) {
                it.next().onProvisionError(exc, z2);
            }
        }

        public void onSessionFullyReleased(DefaultDrmSession defaultDrmSession) {
            this.sessionsAwaitingProvisioning.remove(defaultDrmSession);
            if (this.provisioningSession == defaultDrmSession) {
                this.provisioningSession = null;
                if (!this.sessionsAwaitingProvisioning.isEmpty()) {
                    DefaultDrmSession next = this.sessionsAwaitingProvisioning.iterator().next();
                    this.provisioningSession = next;
                    next.provision();
                }
            }
        }

        public void provisionRequired(DefaultDrmSession defaultDrmSession) {
            this.sessionsAwaitingProvisioning.add(defaultDrmSession);
            if (this.provisioningSession == null) {
                this.provisioningSession = defaultDrmSession;
                defaultDrmSession.provision();
            }
        }
    }

    public class ReferenceCountListenerImpl implements DefaultDrmSession.ReferenceCountListener {
        private ReferenceCountListenerImpl() {
        }

        public void onReferenceCountDecremented(DefaultDrmSession defaultDrmSession, int i3) {
            if (i3 == 1 && DefaultDrmSessionManager.this.prepareCallsCount > 0 && DefaultDrmSessionManager.this.sessionKeepaliveMs != C.TIME_UNSET) {
                DefaultDrmSessionManager.this.keepaliveSessions.add(defaultDrmSession);
                ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).postAtTime(new c(defaultDrmSession, 1), defaultDrmSession, DefaultDrmSessionManager.this.sessionKeepaliveMs + SystemClock.uptimeMillis());
            } else if (i3 == 0) {
                DefaultDrmSessionManager.this.sessions.remove(defaultDrmSession);
                if (DefaultDrmSessionManager.this.placeholderDrmSession == defaultDrmSession) {
                    DefaultDrmSession unused = DefaultDrmSessionManager.this.placeholderDrmSession = null;
                }
                if (DefaultDrmSessionManager.this.noMultiSessionDrmSession == defaultDrmSession) {
                    DefaultDrmSession unused2 = DefaultDrmSessionManager.this.noMultiSessionDrmSession = null;
                }
                DefaultDrmSessionManager.this.provisioningManagerImpl.onSessionFullyReleased(defaultDrmSession);
                if (DefaultDrmSessionManager.this.sessionKeepaliveMs != C.TIME_UNSET) {
                    ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).removeCallbacksAndMessages(defaultDrmSession);
                    DefaultDrmSessionManager.this.keepaliveSessions.remove(defaultDrmSession);
                }
            }
            DefaultDrmSessionManager.this.maybeReleaseMediaDrm();
        }

        public void onReferenceCountIncremented(DefaultDrmSession defaultDrmSession, int i3) {
            if (DefaultDrmSessionManager.this.sessionKeepaliveMs != C.TIME_UNSET) {
                DefaultDrmSessionManager.this.keepaliveSessions.remove(defaultDrmSession);
                ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).removeCallbacksAndMessages(defaultDrmSession);
            }
        }
    }

    private static boolean acquisitionFailedIndicatingResourceShortage(DrmSession drmSession) {
        return drmSession.getState() == 1 && (Util.SDK_INT < 19 || (((DrmSession.DrmSessionException) Assertions.checkNotNull(drmSession.getError())).getCause() instanceof ResourceBusyException));
    }

    private boolean canAcquireSession(DrmInitData drmInitData) {
        if (this.offlineLicenseKeySetId != null) {
            return true;
        }
        if (getSchemeDatas(drmInitData, this.uuid, true).isEmpty()) {
            if (drmInitData.schemeDataCount != 1 || !drmInitData.get(0).matches(C.COMMON_PSSH_UUID)) {
                return false;
            }
            Log.w(TAG, "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + this.uuid);
        }
        String str = drmInitData.schemeType;
        if (str == null || C.CENC_TYPE_cenc.equals(str)) {
            return true;
        }
        return C.CENC_TYPE_cbcs.equals(str) ? Util.SDK_INT >= 25 : !C.CENC_TYPE_cbc1.equals(str) && !C.CENC_TYPE_cens.equals(str);
    }

    private DefaultDrmSession createAndAcquireSession(@Nullable List<DrmInitData.SchemeData> list, boolean z2, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        Assertions.checkNotNull(this.exoMediaDrm);
        List<DrmInitData.SchemeData> list2 = list;
        DefaultDrmSession defaultDrmSession = new DefaultDrmSession(this.uuid, this.exoMediaDrm, this.provisioningManagerImpl, this.referenceCountListener, list2, this.mode, this.playClearSamplesWithoutKeys | z2, z2, this.offlineLicenseKeySetId, this.keyRequestParameters, this.callback, (Looper) Assertions.checkNotNull(this.playbackLooper), this.loadErrorHandlingPolicy, (PlayerId) Assertions.checkNotNull(this.playerId));
        defaultDrmSession.acquire(eventDispatcher);
        if (this.sessionKeepaliveMs != C.TIME_UNSET) {
            defaultDrmSession.acquire((DrmSessionEventListener.EventDispatcher) null);
        }
        return defaultDrmSession;
    }

    private DefaultDrmSession createAndAcquireSessionWithRetry(@Nullable List<DrmInitData.SchemeData> list, boolean z2, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, boolean z3) {
        DefaultDrmSession createAndAcquireSession = createAndAcquireSession(list, z2, eventDispatcher);
        if (acquisitionFailedIndicatingResourceShortage(createAndAcquireSession) && !this.keepaliveSessions.isEmpty()) {
            releaseAllKeepaliveSessions();
            undoAcquisition(createAndAcquireSession, eventDispatcher);
            createAndAcquireSession = createAndAcquireSession(list, z2, eventDispatcher);
        }
        if (!acquisitionFailedIndicatingResourceShortage(createAndAcquireSession) || !z3 || this.preacquiredSessionReferences.isEmpty()) {
            return createAndAcquireSession;
        }
        releaseAllPreacquiredSessions();
        if (!this.keepaliveSessions.isEmpty()) {
            releaseAllKeepaliveSessions();
        }
        undoAcquisition(createAndAcquireSession, eventDispatcher);
        return createAndAcquireSession(list, z2, eventDispatcher);
    }

    private static List<DrmInitData.SchemeData> getSchemeDatas(DrmInitData drmInitData, UUID uuid2, boolean z2) {
        ArrayList arrayList = new ArrayList(drmInitData.schemeDataCount);
        for (int i3 = 0; i3 < drmInitData.schemeDataCount; i3++) {
            DrmInitData.SchemeData schemeData = drmInitData.get(i3);
            if ((schemeData.matches(uuid2) || (C.CLEARKEY_UUID.equals(uuid2) && schemeData.matches(C.COMMON_PSSH_UUID))) && (schemeData.data != null || z2)) {
                arrayList.add(schemeData);
            }
        }
        return arrayList;
    }

    @EnsuresNonNull({"this.playbackLooper", "this.playbackHandler"})
    private synchronized void initPlaybackLooper(Looper looper) {
        try {
            Looper looper2 = this.playbackLooper;
            if (looper2 == null) {
                this.playbackLooper = looper;
                this.playbackHandler = new Handler(looper);
            } else {
                Assertions.checkState(looper2 == looper);
                Assertions.checkNotNull(this.playbackHandler);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    @Nullable
    private DrmSession maybeAcquirePlaceholderSession(int i3, boolean z2) {
        ExoMediaDrm exoMediaDrm2 = (ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm);
        if ((exoMediaDrm2.getCryptoType() == 2 && FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC) || Util.linearSearch(this.useDrmSessionsForClearContentTrackTypes, i3) == -1 || exoMediaDrm2.getCryptoType() == 1) {
            return null;
        }
        DefaultDrmSession defaultDrmSession = this.placeholderDrmSession;
        if (defaultDrmSession == null) {
            DefaultDrmSession createAndAcquireSessionWithRetry = createAndAcquireSessionWithRetry(ImmutableList.of(), true, (DrmSessionEventListener.EventDispatcher) null, z2);
            this.sessions.add(createAndAcquireSessionWithRetry);
            this.placeholderDrmSession = createAndAcquireSessionWithRetry;
        } else {
            defaultDrmSession.acquire((DrmSessionEventListener.EventDispatcher) null);
        }
        return this.placeholderDrmSession;
    }

    private void maybeCreateMediaDrmHandler(Looper looper) {
        if (this.mediaDrmHandler == null) {
            this.mediaDrmHandler = new MediaDrmHandler(looper);
        }
    }

    /* access modifiers changed from: private */
    public void maybeReleaseMediaDrm() {
        if (this.exoMediaDrm != null && this.prepareCallsCount == 0 && this.sessions.isEmpty() && this.preacquiredSessionReferences.isEmpty()) {
            ((ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm)).release();
            this.exoMediaDrm = null;
        }
    }

    private void releaseAllKeepaliveSessions() {
        UnmodifiableIterator<DefaultDrmSession> it = ImmutableSet.copyOf(this.keepaliveSessions).iterator();
        while (it.hasNext()) {
            it.next().release((DrmSessionEventListener.EventDispatcher) null);
        }
    }

    private void releaseAllPreacquiredSessions() {
        UnmodifiableIterator<PreacquiredSessionReference> it = ImmutableSet.copyOf(this.preacquiredSessionReferences).iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }

    private void undoAcquisition(DrmSession drmSession, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        drmSession.release(eventDispatcher);
        if (this.sessionKeepaliveMs != C.TIME_UNSET) {
            drmSession.release((DrmSessionEventListener.EventDispatcher) null);
        }
    }

    @Nullable
    public DrmSession acquireSession(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        Assertions.checkState(this.prepareCallsCount > 0);
        Assertions.checkStateNotNull(this.playbackLooper);
        return acquireSession(this.playbackLooper, eventDispatcher, format, true);
    }

    public int getCryptoType(Format format) {
        int cryptoType = ((ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm)).getCryptoType();
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData == null) {
            if (Util.linearSearch(this.useDrmSessionsForClearContentTrackTypes, MimeTypes.getTrackType(format.sampleMimeType)) != -1) {
                return cryptoType;
            }
            return 0;
        } else if (canAcquireSession(drmInitData)) {
            return cryptoType;
        } else {
            return 1;
        }
    }

    public DrmSessionManager.DrmSessionReference preacquireSession(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        Assertions.checkState(this.prepareCallsCount > 0);
        Assertions.checkStateNotNull(this.playbackLooper);
        PreacquiredSessionReference preacquiredSessionReference = new PreacquiredSessionReference(eventDispatcher);
        preacquiredSessionReference.acquire(format);
        return preacquiredSessionReference;
    }

    public final void prepare() {
        int i3 = this.prepareCallsCount;
        this.prepareCallsCount = i3 + 1;
        if (i3 == 0) {
            if (this.exoMediaDrm == null) {
                ExoMediaDrm acquireExoMediaDrm = this.exoMediaDrmProvider.acquireExoMediaDrm(this.uuid);
                this.exoMediaDrm = acquireExoMediaDrm;
                acquireExoMediaDrm.setOnEventListener(new MediaDrmEventListener());
            } else if (this.sessionKeepaliveMs != C.TIME_UNSET) {
                for (int i4 = 0; i4 < this.sessions.size(); i4++) {
                    this.sessions.get(i4).acquire((DrmSessionEventListener.EventDispatcher) null);
                }
            }
        }
    }

    public final void release() {
        int i3 = this.prepareCallsCount - 1;
        this.prepareCallsCount = i3;
        if (i3 == 0) {
            if (this.sessionKeepaliveMs != C.TIME_UNSET) {
                ArrayList arrayList = new ArrayList(this.sessions);
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    ((DefaultDrmSession) arrayList.get(i4)).release((DrmSessionEventListener.EventDispatcher) null);
                }
            }
            releaseAllPreacquiredSessions();
            maybeReleaseMediaDrm();
        }
    }

    public void setMode(int i3, @Nullable byte[] bArr) {
        Assertions.checkState(this.sessions.isEmpty());
        if (i3 == 1 || i3 == 3) {
            Assertions.checkNotNull(bArr);
        }
        this.mode = i3;
        this.offlineLicenseKeySetId = bArr;
    }

    public void setPlayer(Looper looper, PlayerId playerId2) {
        initPlaybackLooper(looper);
        this.playerId = playerId2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm exoMediaDrm2, MediaDrmCallback mediaDrmCallback, @Nullable HashMap<String, String> hashMap) {
        this(uuid2, exoMediaDrm2, mediaDrmCallback, hashMap == null ? new HashMap<>() : hashMap, false, 3);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm exoMediaDrm2, MediaDrmCallback mediaDrmCallback, @Nullable HashMap<String, String> hashMap, boolean z2) {
        this(uuid2, exoMediaDrm2, mediaDrmCallback, hashMap == null ? new HashMap<>() : hashMap, z2, 3);
    }

    /* access modifiers changed from: private */
    @Nullable
    public DrmSession acquireSession(Looper looper, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher, Format format, boolean z2) {
        List<DrmInitData.SchemeData> list;
        maybeCreateMediaDrmHandler(looper);
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData == null) {
            return maybeAcquirePlaceholderSession(MimeTypes.getTrackType(format.sampleMimeType), z2);
        }
        DefaultDrmSession defaultDrmSession = null;
        if (this.offlineLicenseKeySetId == null) {
            list = getSchemeDatas((DrmInitData) Assertions.checkNotNull(drmInitData), this.uuid, false);
            if (list.isEmpty()) {
                MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.uuid);
                Log.e(TAG, "DRM error", missingSchemeDataException);
                if (eventDispatcher != null) {
                    eventDispatcher.drmSessionManagerError(missingSchemeDataException);
                }
                return new ErrorStateDrmSession(new DrmSession.DrmSessionException(missingSchemeDataException, PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR));
            }
        } else {
            list = null;
        }
        if (this.multiSession) {
            Iterator<DefaultDrmSession> it = this.sessions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DefaultDrmSession next = it.next();
                if (Util.areEqual(next.schemeDatas, list)) {
                    defaultDrmSession = next;
                    break;
                }
            }
        } else {
            defaultDrmSession = this.noMultiSessionDrmSession;
        }
        if (defaultDrmSession == null) {
            defaultDrmSession = createAndAcquireSessionWithRetry(list, false, eventDispatcher, z2);
            if (!this.multiSession) {
                this.noMultiSessionDrmSession = defaultDrmSession;
            }
            this.sessions.add(defaultDrmSession);
        } else {
            defaultDrmSession.acquire(eventDispatcher);
        }
        return defaultDrmSession;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm exoMediaDrm2, MediaDrmCallback mediaDrmCallback, @Nullable HashMap<String, String> hashMap, boolean z2, int i3) {
        this(uuid2, new ExoMediaDrm.AppManagedProvider(exoMediaDrm2), mediaDrmCallback, hashMap == null ? new HashMap<>() : hashMap, z2, new int[0], false, new DefaultLoadErrorHandlingPolicy(i3), 300000);
        ExoMediaDrm exoMediaDrm3 = exoMediaDrm2;
    }

    private DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm.Provider provider, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, boolean z2, int[] iArr, boolean z3, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, long j2) {
        Assertions.checkNotNull(uuid2);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid2), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid2;
        this.exoMediaDrmProvider = provider;
        this.callback = mediaDrmCallback;
        this.keyRequestParameters = hashMap;
        this.multiSession = z2;
        this.useDrmSessionsForClearContentTrackTypes = iArr;
        this.playClearSamplesWithoutKeys = z3;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.provisioningManagerImpl = new ProvisioningManagerImpl();
        this.referenceCountListener = new ReferenceCountListenerImpl();
        this.mode = 0;
        this.sessions = new ArrayList();
        this.preacquiredSessionReferences = Sets.newIdentityHashSet();
        this.keepaliveSessions = Sets.newIdentityHashSet();
        this.sessionKeepaliveMs = j2;
    }
}
