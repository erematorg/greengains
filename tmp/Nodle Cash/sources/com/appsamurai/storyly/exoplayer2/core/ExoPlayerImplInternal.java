package com.appsamurai.storyly.exoplayer2.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.DefaultMediaClock;
import com.appsamurai.storyly.exoplayer2.core.MediaSourceList;
import com.appsamurai.storyly.exoplayer2.core.PlayerMessage;
import com.appsamurai.storyly.exoplayer2.core.Renderer;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;
import com.appsamurai.storyly.exoplayer2.core.metadata.MetadataRenderer;
import com.appsamurai.storyly.exoplayer2.core.source.BehindLiveWindowException;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.SampleStream;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.text.TextRenderer;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceException;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSourceList.MediaSourceListInfoRefreshListener, DefaultMediaClock.PlaybackParametersListener, PlayerMessage.Sender {
    private static final int ACTIVE_INTERVAL_MS = 10;
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_ADD_MEDIA_SOURCES = 18;
    private static final int MSG_ATTEMPT_RENDERER_ERROR_RECOVERY = 25;
    private static final int MSG_DO_SOME_WORK = 2;
    private static final int MSG_MOVE_MEDIA_SOURCES = 19;
    private static final int MSG_PERIOD_PREPARED = 8;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PLAYLIST_UPDATE_REQUESTED = 22;
    private static final int MSG_PREPARE = 0;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_REMOVE_MEDIA_SOURCES = 20;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_FOREGROUND_MODE = 13;
    private static final int MSG_SET_MEDIA_SOURCES = 17;
    private static final int MSG_SET_OFFLOAD_SCHEDULING_ENABLED = 24;
    private static final int MSG_SET_PAUSE_AT_END_OF_WINDOW = 23;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_REPEAT_MODE = 11;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 12;
    private static final int MSG_SET_SHUFFLE_ORDER = 21;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 9;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 10;
    private static final long PLAYBACK_STUCK_AFTER_MS = 4000;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private boolean deliverPendingMessageAtStartPositionRequired;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private int enabledRendererCount;
    private boolean foregroundMode;
    /* access modifiers changed from: private */
    public final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private boolean isRebuffering;
    private final LivePlaybackSpeedControl livePlaybackSpeedControl;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private final MediaSourceList mediaSourceList;
    private int nextPendingMessageIndexHint;
    private boolean offloadSchedulingEnabled;
    private boolean pauseAtEndOfWindow;
    @Nullable
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private boolean pendingPauseAtEndOfPeriod;
    @Nullable
    private ExoPlaybackException pendingRecoverableRendererError;
    private final Timeline.Period period;
    private PlaybackInfo playbackInfo;
    private PlaybackInfoUpdate playbackInfoUpdate;
    private final PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private final Looper playbackLooper;
    private long playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
    private final MediaPeriodQueue queue;
    private final long releaseTimeoutMs;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private final Set<Renderer> renderersToReset;
    private int repeatMode;
    /* access modifiers changed from: private */
    public boolean requestForRendererSleep;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private long setForegroundModeTimeoutMs;
    private boolean shouldContinueLoading;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;

    public static final class MediaSourceListUpdateMessage {
        /* access modifiers changed from: private */
        public final List<MediaSourceList.MediaSourceHolder> mediaSourceHolders;
        /* access modifiers changed from: private */
        public final long positionUs;
        /* access modifiers changed from: private */
        public final ShuffleOrder shuffleOrder;
        /* access modifiers changed from: private */
        public final int windowIndex;

        private MediaSourceListUpdateMessage(List<MediaSourceList.MediaSourceHolder> list, ShuffleOrder shuffleOrder2, int i3, long j2) {
            this.mediaSourceHolders = list;
            this.shuffleOrder = shuffleOrder2;
            this.windowIndex = i3;
            this.positionUs = j2;
        }
    }

    public static class MoveMediaItemsMessage {
        public final int fromIndex;
        public final int newFromIndex;
        public final ShuffleOrder shuffleOrder;
        public final int toIndex;

        public MoveMediaItemsMessage(int i3, int i4, int i5, ShuffleOrder shuffleOrder2) {
            this.fromIndex = i3;
            this.toIndex = i4;
            this.newFromIndex = i5;
            this.shuffleOrder = shuffleOrder2;
        }
    }

    public static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        @Nullable
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i3, long j2, Object obj) {
            this.resolvedPeriodIndex = i3;
            this.resolvedPeriodTimeUs = j2;
            this.resolvedPeriodUid = obj;
        }

        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            Object obj = this.resolvedPeriodUid;
            if ((obj == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                return obj != null ? -1 : 1;
            }
            if (obj == null) {
                return 0;
            }
            int i3 = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
            if (i3 != 0) {
                return i3;
            }
            return Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
        }
    }

    public static final class PlaybackInfoUpdate {
        public int discontinuityReason;
        /* access modifiers changed from: private */
        public boolean hasPendingChange;
        public boolean hasPlayWhenReadyChangeReason;
        public int operationAcks;
        public int playWhenReadyChangeReason;
        public PlaybackInfo playbackInfo;
        public boolean positionDiscontinuity;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo2) {
            this.playbackInfo = playbackInfo2;
        }

        public void incrementPendingOperationAcks(int i3) {
            this.hasPendingChange |= i3 > 0;
            this.operationAcks += i3;
        }

        public void setPlayWhenReadyChangeReason(int i3) {
            this.hasPendingChange = true;
            this.hasPlayWhenReadyChangeReason = true;
            this.playWhenReadyChangeReason = i3;
        }

        public void setPlaybackInfo(PlaybackInfo playbackInfo2) {
            this.hasPendingChange |= this.playbackInfo != playbackInfo2;
            this.playbackInfo = playbackInfo2;
        }

        public void setPositionDiscontinuity(int i3) {
            boolean z2 = true;
            if (!this.positionDiscontinuity || this.discontinuityReason == 5) {
                this.hasPendingChange = true;
                this.positionDiscontinuity = true;
                this.discontinuityReason = i3;
                return;
            }
            if (i3 != 5) {
                z2 = false;
            }
            Assertions.checkArgument(z2);
        }
    }

    public interface PlaybackInfoUpdateListener {
        void onPlaybackInfoUpdate(PlaybackInfoUpdate playbackInfoUpdate);
    }

    public static final class PositionUpdateForPlaylistChange {
        public final boolean endPlayback;
        public final boolean forceBufferingState;
        public final MediaSource.MediaPeriodId periodId;
        public final long periodPositionUs;
        public final long requestedContentPositionUs;
        public final boolean setTargetLiveOffset;

        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, boolean z2, boolean z3, boolean z4) {
            this.periodId = mediaPeriodId;
            this.periodPositionUs = j2;
            this.requestedContentPositionUs = j3;
            this.forceBufferingState = z2;
            this.endPlayback = z3;
            this.setTargetLiveOffset = z4;
        }
    }

    public static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline2, int i3, long j2) {
            this.timeline = timeline2;
            this.windowIndex = i3;
            this.windowPositionUs = j2;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector2, TrackSelectorResult trackSelectorResult, LoadControl loadControl2, BandwidthMeter bandwidthMeter2, int i3, boolean z2, AnalyticsCollector analyticsCollector, SeekParameters seekParameters2, LivePlaybackSpeedControl livePlaybackSpeedControl2, long j2, boolean z3, Looper looper, Clock clock2, PlaybackInfoUpdateListener playbackInfoUpdateListener2, PlayerId playerId) {
        Renderer[] rendererArr2 = rendererArr;
        BandwidthMeter bandwidthMeter3 = bandwidthMeter2;
        AnalyticsCollector analyticsCollector2 = analyticsCollector;
        long j3 = j2;
        Clock clock3 = clock2;
        PlayerId playerId2 = playerId;
        this.playbackInfoUpdateListener = playbackInfoUpdateListener2;
        this.renderers = rendererArr2;
        this.trackSelector = trackSelector2;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl2;
        this.bandwidthMeter = bandwidthMeter3;
        this.repeatMode = i3;
        this.shuffleModeEnabled = z2;
        this.seekParameters = seekParameters2;
        this.livePlaybackSpeedControl = livePlaybackSpeedControl2;
        this.releaseTimeoutMs = j3;
        this.setForegroundModeTimeoutMs = j3;
        this.pauseAtEndOfWindow = z3;
        this.clock = clock3;
        this.backBufferDurationUs = loadControl2.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl2.retainBackBufferFromKeyframe();
        PlaybackInfo createDummy = PlaybackInfo.createDummy(trackSelectorResult);
        this.playbackInfo = createDummy;
        this.playbackInfoUpdate = new PlaybackInfoUpdate(createDummy);
        this.rendererCapabilities = new RendererCapabilities[rendererArr2.length];
        for (int i4 = 0; i4 < rendererArr2.length; i4++) {
            rendererArr2[i4].init(i4, playerId2);
            this.rendererCapabilities[i4] = rendererArr2[i4].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock3);
        this.pendingMessages = new ArrayList<>();
        this.renderersToReset = Sets.newIdentityHashSet();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector2.init(this, bandwidthMeter3);
        this.deliverPendingMessageAtStartPositionRequired = true;
        Handler handler2 = new Handler(looper);
        this.queue = new MediaPeriodQueue(analyticsCollector2, handler2);
        this.mediaSourceList = new MediaSourceList(this, analyticsCollector2, handler2, playerId2);
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:Playback", -16);
        this.internalPlaybackThread = handlerThread;
        handlerThread.start();
        Looper looper2 = handlerThread.getLooper();
        this.playbackLooper = looper2;
        this.handler = clock3.createHandler(looper2, this);
    }

    private void addMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage, int i3) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        MediaSourceList mediaSourceList2 = this.mediaSourceList;
        if (i3 == -1) {
            i3 = mediaSourceList2.getSize();
        }
        handleMediaSourceListInfoRefreshed(mediaSourceList2.addMediaSources(i3, mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    private void attemptRendererErrorRecovery() throws ExoPlaybackException {
        seekToCurrentPosition(true);
    }

    private void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (!playerMessage.isCanceled()) {
            try {
                playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
            } finally {
                playerMessage.markAsProcessed(true);
            }
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        if (isRendererEnabled(renderer)) {
            this.mediaClock.onRendererDisabled(renderer);
            ensureStopped(renderer);
            renderer.disable();
            this.enabledRendererCount--;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doSomeWork() throws com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException, java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            com.appsamurai.storyly.exoplayer2.common.util.Clock r1 = r0.clock
            long r1 = r1.uptimeMillis()
            r16.updatePeriods()
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r5 = 1
            if (r3 == r5) goto L_0x01ef
            r6 = 4
            if (r3 != r6) goto L_0x0017
            goto L_0x01ef
        L_0x0017:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r3 = r0.queue
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r3 = r3.getPlayingPeriod()
            r7 = 10
            if (r3 != 0) goto L_0x0025
            r0.scheduleNextWork(r1, r7)
            return
        L_0x0025:
            java.lang.String r9 = "doSomeWork"
            com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.beginSection(r9)
            r16.updatePlaybackPositions()
            boolean r9 = r3.prepared
            r10 = 1000(0x3e8, double:4.94E-321)
            r12 = 0
            if (r9 == 0) goto L_0x00a4
            long r13 = android.os.SystemClock.elapsedRealtime()
            long r13 = r13 * r10
            com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod r9 = r3.mediaPeriod
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r15 = r0.playbackInfo
            long r10 = r15.positionUs
            long r7 = r0.backBufferDurationUs
            long r10 = r10 - r7
            boolean r7 = r0.retainBackBufferFromKeyframe
            r9.discardBuffer(r10, r7)
            r8 = r5
            r9 = r8
            r7 = r12
        L_0x004a:
            com.appsamurai.storyly.exoplayer2.core.Renderer[] r10 = r0.renderers
            int r11 = r10.length
            if (r7 >= r11) goto L_0x00ab
            r10 = r10[r7]
            boolean r11 = isRendererEnabled(r10)
            if (r11 != 0) goto L_0x0058
            goto L_0x00a0
        L_0x0058:
            long r4 = r0.rendererPositionUs
            r10.render(r4, r13)
            if (r8 == 0) goto L_0x0067
            boolean r4 = r10.isEnded()
            if (r4 == 0) goto L_0x0067
            r8 = 1
            goto L_0x0068
        L_0x0067:
            r8 = r12
        L_0x0068:
            com.appsamurai.storyly.exoplayer2.core.source.SampleStream[] r4 = r3.sampleStreams
            r4 = r4[r7]
            com.appsamurai.storyly.exoplayer2.core.source.SampleStream r5 = r10.getStream()
            if (r4 == r5) goto L_0x0074
            r4 = 1
            goto L_0x0075
        L_0x0074:
            r4 = r12
        L_0x0075:
            if (r4 != 0) goto L_0x007f
            boolean r5 = r10.hasReadStreamToEnd()
            if (r5 == 0) goto L_0x007f
            r5 = 1
            goto L_0x0080
        L_0x007f:
            r5 = r12
        L_0x0080:
            if (r4 != 0) goto L_0x0093
            if (r5 != 0) goto L_0x0093
            boolean r4 = r10.isReady()
            if (r4 != 0) goto L_0x0093
            boolean r4 = r10.isEnded()
            if (r4 == 0) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r4 = r12
            goto L_0x0094
        L_0x0093:
            r4 = 1
        L_0x0094:
            if (r9 == 0) goto L_0x009a
            if (r4 == 0) goto L_0x009a
            r9 = 1
            goto L_0x009b
        L_0x009a:
            r9 = r12
        L_0x009b:
            if (r4 != 0) goto L_0x00a0
            r10.maybeThrowStreamError()
        L_0x00a0:
            int r7 = r7 + 1
            r5 = 1
            goto L_0x004a
        L_0x00a4:
            com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod r4 = r3.mediaPeriod
            r4.maybeThrowPrepareError()
            r8 = 1
            r9 = 1
        L_0x00ab:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r4 = r3.info
            long r4 = r4.durationUs
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r8 == 0) goto L_0x00c8
            boolean r7 = r3.prepared
            if (r7 == 0) goto L_0x00c8
            int r7 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x00c6
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r7 = r0.playbackInfo
            long r7 = r7.positionUs
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 > 0) goto L_0x00c8
        L_0x00c6:
            r4 = 1
            goto L_0x00c9
        L_0x00c8:
            r4 = r12
        L_0x00c9:
            if (r4 == 0) goto L_0x00d9
            boolean r5 = r0.pendingPauseAtEndOfPeriod
            if (r5 == 0) goto L_0x00d9
            r0.pendingPauseAtEndOfPeriod = r12
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r5 = r0.playbackInfo
            int r5 = r5.playbackSuppressionReason
            r7 = 5
            r0.setPlayWhenReadyInternal(r12, r5, r12, r7)
        L_0x00d9:
            r5 = 3
            if (r4 == 0) goto L_0x00e9
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r4 = r3.info
            boolean r4 = r4.isFinal
            if (r4 == 0) goto L_0x00e9
            r0.setState(r6)
            r16.stopRenderers()
            goto L_0x0132
        L_0x00e9:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r4 = r0.playbackInfo
            int r4 = r4.playbackState
            r7 = 2
            if (r4 != r7) goto L_0x0106
            boolean r4 = r0.shouldTransitionToReadyState(r9)
            if (r4 == 0) goto L_0x0106
            r0.setState(r5)
            r4 = 0
            r0.pendingRecoverableRendererError = r4
            boolean r4 = r16.shouldPlayWhenReady()
            if (r4 == 0) goto L_0x0132
            r16.startRenderers()
            goto L_0x0132
        L_0x0106:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r4 = r0.playbackInfo
            int r4 = r4.playbackState
            if (r4 != r5) goto L_0x0132
            int r4 = r0.enabledRendererCount
            if (r4 != 0) goto L_0x0117
            boolean r4 = r16.isTimelineReady()
            if (r4 == 0) goto L_0x0119
            goto L_0x0132
        L_0x0117:
            if (r9 != 0) goto L_0x0132
        L_0x0119:
            boolean r4 = r16.shouldPlayWhenReady()
            r0.isRebuffering = r4
            r4 = 2
            r0.setState(r4)
            boolean r4 = r0.isRebuffering
            if (r4 == 0) goto L_0x012f
            r16.notifyTrackSelectionRebuffer()
            com.appsamurai.storyly.exoplayer2.core.LivePlaybackSpeedControl r4 = r0.livePlaybackSpeedControl
            r4.notifyRebuffer()
        L_0x012f:
            r16.stopRenderers()
        L_0x0132:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r4 = r0.playbackInfo
            int r4 = r4.playbackState
            r7 = 2
            if (r4 != r7) goto L_0x019b
            r4 = r12
        L_0x013a:
            com.appsamurai.storyly.exoplayer2.core.Renderer[] r7 = r0.renderers
            int r8 = r7.length
            if (r4 >= r8) goto L_0x015f
            r7 = r7[r4]
            boolean r7 = isRendererEnabled(r7)
            if (r7 == 0) goto L_0x015c
            com.appsamurai.storyly.exoplayer2.core.Renderer[] r7 = r0.renderers
            r7 = r7[r4]
            com.appsamurai.storyly.exoplayer2.core.source.SampleStream r7 = r7.getStream()
            com.appsamurai.storyly.exoplayer2.core.source.SampleStream[] r8 = r3.sampleStreams
            r8 = r8[r4]
            if (r7 != r8) goto L_0x015c
            com.appsamurai.storyly.exoplayer2.core.Renderer[] r7 = r0.renderers
            r7 = r7[r4]
            r7.maybeThrowStreamError()
        L_0x015c:
            int r4 = r4 + 1
            goto L_0x013a
        L_0x015f:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r0.playbackInfo
            boolean r4 = r3.isLoading
            if (r4 != 0) goto L_0x019b
            long r3 = r3.totalBufferedDurationUs
            r7 = 500000(0x7a120, double:2.47033E-318)
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x019b
            boolean r3 = r16.isLoadingPossible()
            if (r3 == 0) goto L_0x019b
            long r3 = r0.playbackMaybeBecameStuckAtMs
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 != 0) goto L_0x0183
            com.appsamurai.storyly.exoplayer2.common.util.Clock r3 = r0.clock
            long r3 = r3.elapsedRealtime()
            r0.playbackMaybeBecameStuckAtMs = r3
            goto L_0x019d
        L_0x0183:
            com.appsamurai.storyly.exoplayer2.common.util.Clock r3 = r0.clock
            long r3 = r3.elapsedRealtime()
            long r7 = r0.playbackMaybeBecameStuckAtMs
            long r3 = r3 - r7
            r7 = 4000(0xfa0, double:1.9763E-320)
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x0193
            goto L_0x019d
        L_0x0193:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Playback stuck buffering and not loading"
            r0.<init>(r1)
            throw r0
        L_0x019b:
            r0.playbackMaybeBecameStuckAtMs = r13
        L_0x019d:
            boolean r3 = r0.offloadSchedulingEnabled
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r4 = r0.playbackInfo
            boolean r7 = r4.offloadSchedulingEnabled
            if (r3 == r7) goto L_0x01ab
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r4.copyWithOffloadSchedulingEnabled(r3)
            r0.playbackInfo = r3
        L_0x01ab:
            boolean r3 = r16.shouldPlayWhenReady()
            if (r3 == 0) goto L_0x01bb
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 == r5) goto L_0x01b8
            goto L_0x01bb
        L_0x01b8:
            r3 = 10
            goto L_0x01c3
        L_0x01bb:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r4 = 2
            if (r3 != r4) goto L_0x01ca
            goto L_0x01b8
        L_0x01c3:
            boolean r1 = r0.maybeScheduleWakeup(r1, r3)
            r2 = 1
            r1 = r1 ^ r2
            goto L_0x01dd
        L_0x01ca:
            int r4 = r0.enabledRendererCount
            if (r4 == 0) goto L_0x01d6
            if (r3 == r6) goto L_0x01d6
            r3 = 1000(0x3e8, double:4.94E-321)
            r0.scheduleNextWork(r1, r3)
            goto L_0x01dc
        L_0x01d6:
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper r1 = r0.handler
            r2 = 2
            r1.removeMessages(r2)
        L_0x01dc:
            r1 = r12
        L_0x01dd:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r2 = r0.playbackInfo
            boolean r3 = r2.sleepingForOffload
            if (r3 == r1) goto L_0x01e9
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r2.copyWithSleepingForOffload(r1)
            r0.playbackInfo = r1
        L_0x01e9:
            r0.requestForRendererSleep = r12
            com.appsamurai.storyly.exoplayer2.common.util.TraceUtil.endSection()
            return
        L_0x01ef:
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper r0 = r0.handler
            r1 = 2
            r0.removeMessages(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.doSomeWork():void");
    }

    private void enableRenderer(int i3, boolean z2) throws ExoPlaybackException {
        Renderer renderer = this.renderers[i3];
        if (!isRendererEnabled(renderer)) {
            MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
            boolean z3 = readingPeriod == this.queue.getPlayingPeriod();
            TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i3];
            Format[] formats = getFormats(trackSelectorResult.selections[i3]);
            boolean z4 = shouldPlayWhenReady() && this.playbackInfo.playbackState == 3;
            boolean z5 = !z2 && z4;
            this.enabledRendererCount++;
            this.renderersToReset.add(renderer);
            renderer.enable(rendererConfiguration, formats, readingPeriod.sampleStreams[i3], this.rendererPositionUs, z5, z3, readingPeriod.getStartPositionRendererTime(), readingPeriod.getRendererOffset());
            renderer.handleMessage(11, new Renderer.WakeupListener() {
                public void onSleep() {
                    boolean unused = ExoPlayerImplInternal.this.requestForRendererSleep = true;
                }

                public void onWakeup() {
                    ExoPlayerImplInternal.this.handler.sendEmptyMessage(2);
                }
            });
            this.mediaClock.onRendererEnabled(renderer);
            if (z4) {
                renderer.start();
            }
        }
    }

    private void enableRenderers() throws ExoPlaybackException {
        enableRenderers(new boolean[this.renderers.length]);
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private ImmutableList<Metadata> extractMetadataFromTrackSelectionArray(ExoTrackSelection[] exoTrackSelectionArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        boolean z2 = false;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                Metadata metadata = exoTrackSelection.getFormat(0).metadata;
                if (metadata == null) {
                    builder.add((Object) new Metadata(new Metadata.Entry[0]));
                } else {
                    builder.add((Object) metadata);
                    z2 = true;
                }
            }
        }
        return z2 ? builder.build() : ImmutableList.of();
    }

    private long getCurrentLiveOffsetUs() {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        return getLiveOffsetUs(playbackInfo2.timeline, playbackInfo2.periodId.periodUid, playbackInfo2.positionUs);
    }

    private static Format[] getFormats(ExoTrackSelection exoTrackSelection) {
        int length = exoTrackSelection != null ? exoTrackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i3 = 0; i3 < length; i3++) {
            formatArr[i3] = exoTrackSelection.getFormat(i3);
        }
        return formatArr;
    }

    private long getLiveOffsetUs(Timeline timeline, Object obj, long j2) {
        timeline.getWindow(timeline.getPeriodByUid(obj, this.period).windowIndex, this.window);
        Timeline.Window window2 = this.window;
        if (window2.windowStartTimeMs != C.TIME_UNSET && window2.isLive()) {
            Timeline.Window window3 = this.window;
            if (window3.isDynamic) {
                return Util.msToUs(window3.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - (this.period.getPositionInWindowUs() + j2);
            }
        }
        return C.TIME_UNSET;
    }

    private long getMaxRendererReadPositionUs() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod == null) {
            return 0;
        }
        long rendererOffset = readingPeriod.getRendererOffset();
        if (!readingPeriod.prepared) {
            return rendererOffset;
        }
        int i3 = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i3 >= rendererArr.length) {
                return rendererOffset;
            }
            if (isRendererEnabled(rendererArr[i3]) && this.renderers[i3].getStream() == readingPeriod.sampleStreams[i3]) {
                long readingPositionUs = this.renderers[i3].getReadingPositionUs();
                if (readingPositionUs == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                rendererOffset = Math.max(readingPositionUs, rendererOffset);
            }
            i3++;
        }
    }

    private Pair<MediaSource.MediaPeriodId, Long> getPlaceholderFirstMediaPeriodPositionUs(Timeline timeline) {
        long j2 = 0;
        if (timeline.isEmpty()) {
            return Pair.create(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L);
        }
        Timeline timeline2 = timeline;
        Pair<Object, Long> periodPositionUs = timeline2.getPeriodPositionUs(this.window, this.period, timeline.getFirstWindowIndex(this.shuffleModeEnabled), C.TIME_UNSET);
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline, periodPositionUs.first, 0);
        long longValue = ((Long) periodPositionUs.second).longValue();
        if (resolveMediaPeriodIdForAdsAfterPeriodPositionChange.isAd()) {
            timeline.getPeriodByUid(resolveMediaPeriodIdForAdsAfterPeriodPositionChange.periodUid, this.period);
            if (resolveMediaPeriodIdForAdsAfterPeriodPositionChange.adIndexInAdGroup == this.period.getFirstAdIndexToPlay(resolveMediaPeriodIdForAdsAfterPeriodPositionChange.adGroupIndex)) {
                j2 = this.period.getAdResumePositionUs();
            }
            longValue = j2;
        }
        return Pair.create(resolveMediaPeriodIdForAdsAfterPeriodPositionChange, Long.valueOf(longValue));
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    private void handleIoException(IOException iOException, int i3) {
        ExoPlaybackException createForSource = ExoPlaybackException.createForSource(iOException, i3);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            createForSource = createForSource.copyWithMediaPeriodId(playingPeriod.info.id);
        }
        Log.e(TAG, "Playback error", createForSource);
        stopInternal(false, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(createForSource);
    }

    private void handleLoadingMediaPeriodChanged(boolean z2) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaSource.MediaPeriodId mediaPeriodId = loadingPeriod == null ? this.playbackInfo.periodId : loadingPeriod.info.id;
        boolean equals = this.playbackInfo.loadingMediaPeriodId.equals(mediaPeriodId);
        if (!equals) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(mediaPeriodId);
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        playbackInfo2.bufferedPositionUs = loadingPeriod == null ? playbackInfo2.positionUs : loadingPeriod.getBufferedPositionUs();
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((!equals || z2) && loadingPeriod != null && loadingPeriod.prepared) {
            updateLoadControlTrackSelection(loadingPeriod.getTrackGroups(), loadingPeriod.getTrackSelectorResult());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x015e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleMediaSourceListInfoRefreshed(com.appsamurai.storyly.exoplayer2.common.Timeline r25, boolean r26) throws com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException {
        /*
            r24 = this;
            r11 = r24
            r12 = r25
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r2 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$SeekPosition r3 = r11.pendingInitialSeekPosition
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r4 = r11.queue
            int r5 = r11.repeatMode
            boolean r6 = r11.shuffleModeEnabled
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r7 = r11.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r8 = r11.period
            r1 = r25
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r7 = resolvePositionForPlaylistChange(r1, r2, r3, r4, r5, r6, r7, r8)
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r8 = r7.periodId
            long r9 = r7.requestedContentPositionUs
            boolean r0 = r7.forceBufferingState
            long r13 = r7.periodPositionUs
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r1 = r1.periodId
            boolean r1 = r1.equals(r8)
            r15 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0038
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            long r1 = r1.positionUs
            int r1 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0035
            goto L_0x0038
        L_0x0035:
            r16 = r5
            goto L_0x003a
        L_0x0038:
            r16 = r15
        L_0x003a:
            r6 = 0
            r17 = 3
            r18 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r4 = 4
            boolean r1 = r7.endPlayback     // Catch:{ all -> 0x0051 }
            if (r1 == 0) goto L_0x005a
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo     // Catch:{ all -> 0x0051 }
            int r1 = r1.playbackState     // Catch:{ all -> 0x0051 }
            if (r1 == r15) goto L_0x0057
            r11.setState(r4)     // Catch:{ all -> 0x0051 }
            goto L_0x0057
        L_0x0051:
            r0 = move-exception
            r20 = r4
            r15 = r5
            goto L_0x0133
        L_0x0057:
            r11.resetInternal(r5, r5, r5, r15)     // Catch:{ all -> 0x0051 }
        L_0x005a:
            if (r16 != 0) goto L_0x0084
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r1 = r11.queue     // Catch:{ all -> 0x007f }
            long r3 = r11.rendererPositionUs     // Catch:{ all -> 0x007a }
            long r21 = r24.getMaxRendererReadPositionUs()     // Catch:{ all -> 0x007a }
            r2 = r25
            r15 = -1
            r20 = 4
            r15 = r5
            r5 = r21
            boolean r0 = r1.updateQueuedPeriods(r2, r3, r5)     // Catch:{ all -> 0x0076 }
            if (r0 != 0) goto L_0x00b6
            r11.seekToCurrentPosition(r15)     // Catch:{ all -> 0x0076 }
            goto L_0x00b6
        L_0x0076:
            r0 = move-exception
        L_0x0077:
            r6 = 0
            goto L_0x0133
        L_0x007a:
            r0 = move-exception
            r15 = r5
            r20 = 4
            goto L_0x0077
        L_0x007f:
            r0 = move-exception
            r20 = r4
            r15 = r5
            goto L_0x0077
        L_0x0084:
            r20 = r4
            r15 = r5
            boolean r1 = r25.isEmpty()     // Catch:{ all -> 0x0076 }
            if (r1 != 0) goto L_0x00b6
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r1 = r11.queue     // Catch:{ all -> 0x0076 }
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r1 = r1.getPlayingPeriod()     // Catch:{ all -> 0x0076 }
        L_0x0093:
            if (r1 == 0) goto L_0x00b1
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r2 = r1.info     // Catch:{ all -> 0x0076 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r2 = r2.id     // Catch:{ all -> 0x0076 }
            boolean r2 = r2.equals(r8)     // Catch:{ all -> 0x0076 }
            if (r2 == 0) goto L_0x00ac
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r2 = r11.queue     // Catch:{ all -> 0x0076 }
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r3 = r1.info     // Catch:{ all -> 0x0076 }
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodInfo r2 = r2.getUpdatedMediaPeriodInfo(r12, r3)     // Catch:{ all -> 0x0076 }
            r1.info = r2     // Catch:{ all -> 0x0076 }
            r1.updateClipping()     // Catch:{ all -> 0x0076 }
        L_0x00ac:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r1 = r1.getNext()     // Catch:{ all -> 0x0076 }
            goto L_0x0093
        L_0x00b1:
            long r0 = r11.seekToPeriodPosition(r8, r13, r0)     // Catch:{ all -> 0x0076 }
            r13 = r0
        L_0x00b6:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r4 = r0.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r5 = r0.periodId
            boolean r0 = r7.setTargetLiveOffset
            if (r0 == 0) goto L_0x00c2
            r6 = r13
            goto L_0x00c4
        L_0x00c2:
            r6 = r18
        L_0x00c4:
            r1 = r24
            r2 = r25
            r3 = r8
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6)
            if (r16 != 0) goto L_0x00d6
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r11.playbackInfo
            long r0 = r0.requestedContentPositionUs
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x0114
        L_0x00d6:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r1 = r0.periodId
            java.lang.Object r1 = r1.periodUid
            com.appsamurai.storyly.exoplayer2.common.Timeline r0 = r0.timeline
            if (r16 == 0) goto L_0x00f5
            if (r26 == 0) goto L_0x00f5
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x00f5
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r2 = r11.period
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r0.getPeriodByUid(r1, r2)
            boolean r0 = r0.isPlaceholder
            if (r0 != 0) goto L_0x00f5
            r23 = 1
            goto L_0x00f7
        L_0x00f5:
            r23 = r15
        L_0x00f7:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r11.playbackInfo
            long r5 = r0.discontinuityStartPositionUs
            int r0 = r12.getIndexOfPeriod(r1)
            r1 = -1
            if (r0 != r1) goto L_0x0104
            r17 = r20
        L_0x0104:
            r1 = r24
            r2 = r8
            r3 = r13
            r7 = r5
            r5 = r9
            r9 = r23
            r10 = r17
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r0
        L_0x0114:
            r24.resetPendingPauseAtEndOfPeriod()
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r0 = r0.timeline
            r11.resolvePendingMessagePositions(r12, r0)
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r0.copyWithTimeline(r12)
            r11.playbackInfo = r0
            boolean r0 = r25.isEmpty()
            if (r0 != 0) goto L_0x012f
            r6 = 0
            r11.pendingInitialSeekPosition = r6
        L_0x012f:
            r11.handleLoadingMediaPeriodChanged(r15)
            return
        L_0x0133:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r4 = r1.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r5 = r1.periodId
            boolean r1 = r7.setTargetLiveOffset
            if (r1 == 0) goto L_0x013f
            r18 = r13
        L_0x013f:
            r1 = r24
            r2 = r25
            r3 = r8
            r15 = r6
            r6 = r18
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6)
            if (r16 != 0) goto L_0x0154
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            long r1 = r1.requestedContentPositionUs
            int r1 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0192
        L_0x0154:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r2 = r1.periodId
            java.lang.Object r2 = r2.periodUid
            com.appsamurai.storyly.exoplayer2.common.Timeline r1 = r1.timeline
            if (r16 == 0) goto L_0x0173
            if (r26 == 0) goto L_0x0173
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L_0x0173
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r3 = r11.period
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r1 = r1.getPeriodByUid(r2, r3)
            boolean r1 = r1.isPlaceholder
            if (r1 != 0) goto L_0x0173
            r23 = 1
            goto L_0x0175
        L_0x0173:
            r23 = 0
        L_0x0175:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            long r5 = r1.discontinuityStartPositionUs
            int r1 = r12.getIndexOfPeriod(r2)
            r2 = -1
            if (r1 != r2) goto L_0x0182
            r17 = r20
        L_0x0182:
            r1 = r24
            r2 = r8
            r3 = r13
            r7 = r5
            r5 = r9
            r9 = r23
            r10 = r17
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r1
        L_0x0192:
            r24.resetPendingPauseAtEndOfPeriod()
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r1 = r1.timeline
            r11.resolvePendingMessagePositions(r12, r1)
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r1.copyWithTimeline(r12)
            r11.playbackInfo = r1
            boolean r1 = r25.isEmpty()
            if (r1 != 0) goto L_0x01ac
            r11.pendingInitialSeekPosition = r15
        L_0x01ac:
            r1 = 0
            r11.handleLoadingMediaPeriodChanged(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.handleMediaSourceListInfoRefreshed(com.appsamurai.storyly.exoplayer2.common.Timeline, boolean):void");
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            loadingPeriod.handlePrepared(this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.timeline);
            updateLoadControlTrackSelection(loadingPeriod.getTrackGroups(), loadingPeriod.getTrackSelectorResult());
            if (loadingPeriod == this.queue.getPlayingPeriod()) {
                resetRendererPosition(loadingPeriod.info.startPositionUs);
                enableRenderers();
                PlaybackInfo playbackInfo2 = this.playbackInfo;
                MediaSource.MediaPeriodId mediaPeriodId = playbackInfo2.periodId;
                long j2 = loadingPeriod.info.startPositionUs;
                this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, j2, playbackInfo2.requestedContentPositionUs, j2, false, 5);
            }
            maybeContinueLoading();
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, boolean z2) throws ExoPlaybackException {
        handlePlaybackParameters(playbackParameters, playbackParameters.speed, true, z2);
    }

    @CheckResult
    private PlaybackInfo handlePositionDiscontinuity(MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3, long j4, boolean z2, int i3) {
        ImmutableList<Metadata> immutableList;
        TrackSelectorResult trackSelectorResult;
        TrackGroupArray trackGroupArray;
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        long j5 = j3;
        this.deliverPendingMessageAtStartPositionRequired = this.deliverPendingMessageAtStartPositionRequired || j2 != this.playbackInfo.positionUs || !mediaPeriodId.equals(this.playbackInfo.periodId);
        resetPendingPauseAtEndOfPeriod();
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        TrackGroupArray trackGroupArray2 = playbackInfo2.trackGroups;
        TrackSelectorResult trackSelectorResult2 = playbackInfo2.trackSelectorResult;
        List<Metadata> list = playbackInfo2.staticMetadata;
        if (this.mediaSourceList.isPrepared()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            TrackGroupArray trackGroups = playingPeriod == null ? TrackGroupArray.EMPTY : playingPeriod.getTrackGroups();
            TrackSelectorResult trackSelectorResult3 = playingPeriod == null ? this.emptyTrackSelectorResult : playingPeriod.getTrackSelectorResult();
            ImmutableList<Metadata> extractMetadataFromTrackSelectionArray = extractMetadataFromTrackSelectionArray(trackSelectorResult3.selections);
            if (playingPeriod != null) {
                MediaPeriodInfo mediaPeriodInfo = playingPeriod.info;
                if (mediaPeriodInfo.requestedContentPositionUs != j5) {
                    playingPeriod.info = mediaPeriodInfo.copyWithRequestedContentPositionUs(j5);
                }
            }
            trackGroupArray = trackGroups;
            trackSelectorResult = trackSelectorResult3;
            immutableList = extractMetadataFromTrackSelectionArray;
        } else if (!mediaPeriodId.equals(this.playbackInfo.periodId)) {
            trackGroupArray = TrackGroupArray.EMPTY;
            trackSelectorResult = this.emptyTrackSelectorResult;
            immutableList = ImmutableList.of();
        } else {
            immutableList = list;
            trackGroupArray = trackGroupArray2;
            trackSelectorResult = trackSelectorResult2;
        }
        if (z2) {
            this.playbackInfoUpdate.setPositionDiscontinuity(i3);
        }
        return this.playbackInfo.copyWithNewPosition(mediaPeriodId, j2, j3, j4, getTotalBufferedDurationUs(), trackGroupArray, trackSelectorResult, immutableList);
    }

    private boolean hasReachedServerSideInsertedAdsTransition(Renderer renderer, MediaPeriodHolder mediaPeriodHolder) {
        MediaPeriodHolder next = mediaPeriodHolder.getNext();
        return mediaPeriodHolder.info.isFollowedByTransitionToSameStream && next.prepared && ((renderer instanceof TextRenderer) || (renderer instanceof MetadataRenderer) || renderer.getReadingPositionUs() >= next.getStartPositionRendererTime());
    }

    private boolean hasReadingPeriodFinishedReading() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (!readingPeriod.prepared) {
            return false;
        }
        int i3 = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i3 >= rendererArr.length) {
                return true;
            }
            Renderer renderer = rendererArr[i3];
            SampleStream sampleStream = readingPeriod.sampleStreams[i3];
            if (renderer.getStream() != sampleStream || (sampleStream != null && !renderer.hasReadStreamToEnd() && !hasReachedServerSideInsertedAdsTransition(renderer, readingPeriod))) {
                return false;
            }
            i3++;
        }
        return false;
    }

    private static boolean isIgnorableServerSideAdInsertionPeriodChange(boolean z2, MediaSource.MediaPeriodId mediaPeriodId, long j2, MediaSource.MediaPeriodId mediaPeriodId2, Timeline.Period period2, long j3) {
        if (z2 || j2 != j3 || !mediaPeriodId.periodUid.equals(mediaPeriodId2.periodUid)) {
            return false;
        }
        return (!mediaPeriodId.isAd() || !period2.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex)) ? mediaPeriodId2.isAd() && period2.isServerSideInsertedAdGroup(mediaPeriodId2.adGroupIndex) : (period2.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 4 || period2.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 2) ? false : true;
    }

    private boolean isLoadingPossible() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        return (loadingPeriod == null || loadingPeriod.getNextLoadPositionUs() == Long.MIN_VALUE) ? false : true;
    }

    private static boolean isRendererEnabled(Renderer renderer) {
        return renderer.getState() != 0;
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j2 = playingPeriod.info.durationUs;
        return playingPeriod.prepared && (j2 == C.TIME_UNSET || this.playbackInfo.positionUs < j2 || !shouldPlayWhenReady());
    }

    private static boolean isUsingPlaceholderPeriod(PlaybackInfo playbackInfo2, Timeline.Period period2) {
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo2.periodId;
        Timeline timeline = playbackInfo2.timeline;
        return timeline.isEmpty() || timeline.getPeriodByUid(mediaPeriodId.periodUid, period2).isPlaceholder;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$release$0() {
        return Boolean.valueOf(this.released);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendMessageToTargetThread$1(PlayerMessage playerMessage) {
        try {
            deliverMessage(playerMessage);
        } catch (ExoPlaybackException e3) {
            Log.e(TAG, "Unexpected error delivering message on external thread.", e3);
            throw new RuntimeException(e3);
        }
    }

    private void maybeContinueLoading() {
        boolean shouldContinueLoading2 = shouldContinueLoading();
        this.shouldContinueLoading = shouldContinueLoading2;
        if (shouldContinueLoading2) {
            this.queue.getLoadingPeriod().continueLoading(this.rendererPositionUs);
        }
        updateIsLoading();
    }

    private void maybeNotifyPlaybackInfoChanged() {
        this.playbackInfoUpdate.setPlaybackInfo(this.playbackInfo);
        if (this.playbackInfoUpdate.hasPendingChange) {
            this.playbackInfoUpdateListener.onPlaybackInfoUpdate(this.playbackInfoUpdate);
            this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        }
    }

    private boolean maybeScheduleWakeup(long j2, long j3) {
        if (this.offloadSchedulingEnabled && this.requestForRendererSleep) {
            return false;
        }
        scheduleNextWork(j2, j3);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0078 A[EDGE_INSN: B:72:0x0078->B:25:0x0078 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeTriggerPendingMessages(long r9, long r11) throws com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException {
        /*
            r8 = this;
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r0 = r8.pendingMessages
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00fc
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r8.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r0.isAd()
            if (r0 == 0) goto L_0x0014
            goto L_0x00fc
        L_0x0014:
            boolean r0 = r8.deliverPendingMessageAtStartPositionRequired
            if (r0 == 0) goto L_0x001e
            r0 = 1
            long r9 = r9 - r0
            r0 = 0
            r8.deliverPendingMessageAtStartPositionRequired = r0
        L_0x001e:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r8.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r1 = r0.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r0 = r0.periodId
            java.lang.Object r0 = r0.periodUid
            int r0 = r1.getIndexOfPeriod(r0)
            int r1 = r8.nextPendingMessageIndexHint
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r2 = r8.pendingMessages
            int r2 = r2.size()
            int r1 = java.lang.Math.min(r1, r2)
            r2 = 0
            if (r1 <= 0) goto L_0x0044
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r4 = r1 + -1
            java.lang.Object r3 = r3.get(r4)
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo r3 = (com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0045
        L_0x0044:
            r3 = r2
        L_0x0045:
            if (r3 == 0) goto L_0x0067
            int r4 = r3.resolvedPeriodIndex
            if (r4 > r0) goto L_0x0053
            if (r4 != r0) goto L_0x0067
            long r3 = r3.resolvedPeriodTimeUs
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x0067
        L_0x0053:
            int r3 = r1 + -1
            if (r3 <= 0) goto L_0x0062
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r4 = r8.pendingMessages
            int r1 = r1 + -2
            java.lang.Object r1 = r4.get(r1)
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x0063
        L_0x0062:
            r1 = r2
        L_0x0063:
            r7 = r3
            r3 = r1
            r1 = r7
            goto L_0x0045
        L_0x0067:
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0078
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo r3 = (com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0079
        L_0x0078:
            r3 = r2
        L_0x0079:
            if (r3 == 0) goto L_0x009e
            java.lang.Object r4 = r3.resolvedPeriodUid
            if (r4 == 0) goto L_0x009e
            int r4 = r3.resolvedPeriodIndex
            if (r4 < r0) goto L_0x008b
            if (r4 != r0) goto L_0x009e
            long r4 = r3.resolvedPeriodTimeUs
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 > 0) goto L_0x009e
        L_0x008b:
            int r1 = r1 + 1
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0078
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo r3 = (com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0079
        L_0x009e:
            if (r3 == 0) goto L_0x00fa
            java.lang.Object r4 = r3.resolvedPeriodUid
            if (r4 == 0) goto L_0x00fa
            int r4 = r3.resolvedPeriodIndex
            if (r4 != r0) goto L_0x00fa
            long r4 = r3.resolvedPeriodTimeUs
            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x00fa
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 > 0) goto L_0x00fa
            com.appsamurai.storyly.exoplayer2.core.PlayerMessage r4 = r3.message     // Catch:{ all -> 0x00e3 }
            r8.sendMessageToTarget(r4)     // Catch:{ all -> 0x00e3 }
            com.appsamurai.storyly.exoplayer2.core.PlayerMessage r4 = r3.message
            boolean r4 = r4.getDeleteAfterDelivery()
            if (r4 != 0) goto L_0x00cb
            com.appsamurai.storyly.exoplayer2.core.PlayerMessage r3 = r3.message
            boolean r3 = r3.isCanceled()
            if (r3 == 0) goto L_0x00c8
            goto L_0x00cb
        L_0x00c8:
            int r1 = r1 + 1
            goto L_0x00d0
        L_0x00cb:
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            r3.remove(r1)
        L_0x00d0:
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x00e1
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r3 = r8.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo r3 = (com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x009e
        L_0x00e1:
            r3 = r2
            goto L_0x009e
        L_0x00e3:
            r9 = move-exception
            com.appsamurai.storyly.exoplayer2.core.PlayerMessage r10 = r3.message
            boolean r10 = r10.getDeleteAfterDelivery()
            if (r10 != 0) goto L_0x00f4
            com.appsamurai.storyly.exoplayer2.core.PlayerMessage r10 = r3.message
            boolean r10 = r10.isCanceled()
            if (r10 == 0) goto L_0x00f9
        L_0x00f4:
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PendingMessageInfo> r8 = r8.pendingMessages
            r8.remove(r1)
        L_0x00f9:
            throw r9
        L_0x00fa:
            r8.nextPendingMessageIndexHint = r1
        L_0x00fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private void maybeUpdateLoadingPeriod() throws ExoPlaybackException {
        MediaPeriodInfo nextMediaPeriodInfo;
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod() && (nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo)) != null) {
            MediaPeriodHolder enqueueNextMediaPeriodHolder = this.queue.enqueueNextMediaPeriodHolder(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSourceList, nextMediaPeriodInfo, this.emptyTrackSelectorResult);
            enqueueNextMediaPeriodHolder.mediaPeriod.prepare(this, nextMediaPeriodInfo.startPositionUs);
            if (this.queue.getPlayingPeriod() == enqueueNextMediaPeriodHolder) {
                resetRendererPosition(nextMediaPeriodInfo.startPositionUs);
            }
            handleLoadingMediaPeriodChanged(false);
        }
        if (this.shouldContinueLoading) {
            this.shouldContinueLoading = isLoadingPossible();
            updateIsLoading();
            return;
        }
        maybeContinueLoading();
    }

    private void maybeUpdatePlayingPeriod() throws ExoPlaybackException {
        boolean z2;
        boolean z3 = false;
        while (shouldAdvancePlayingPeriod()) {
            if (z3) {
                maybeNotifyPlaybackInfoChanged();
            }
            MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.advancePlayingPeriod());
            if (this.playbackInfo.periodId.periodUid.equals(mediaPeriodHolder.info.id.periodUid)) {
                MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
                if (mediaPeriodId.adGroupIndex == -1) {
                    MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodHolder.info.id;
                    if (mediaPeriodId2.adGroupIndex == -1 && mediaPeriodId.nextAdGroupIndex != mediaPeriodId2.nextAdGroupIndex) {
                        z2 = true;
                        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder.info;
                        MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodInfo.id;
                        long j2 = mediaPeriodInfo.startPositionUs;
                        this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId3, j2, mediaPeriodInfo.requestedContentPositionUs, j2, !z2, 0);
                        resetPendingPauseAtEndOfPeriod();
                        updatePlaybackPositions();
                        z3 = true;
                    }
                }
            }
            z2 = false;
            MediaPeriodInfo mediaPeriodInfo2 = mediaPeriodHolder.info;
            MediaSource.MediaPeriodId mediaPeriodId32 = mediaPeriodInfo2.id;
            long j22 = mediaPeriodInfo2.startPositionUs;
            this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId32, j22, mediaPeriodInfo2.requestedContentPositionUs, j22, !z2, 0);
            resetPendingPauseAtEndOfPeriod();
            updatePlaybackPositions();
            z3 = true;
        }
    }

    private void maybeUpdateReadingPeriod() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null) {
            int i3 = 0;
            if (readingPeriod.getNext() == null || this.pendingPauseAtEndOfPeriod) {
                if (readingPeriod.info.isFinal || this.pendingPauseAtEndOfPeriod) {
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i3 < rendererArr.length) {
                            Renderer renderer = rendererArr[i3];
                            SampleStream sampleStream = readingPeriod.sampleStreams[i3];
                            if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                                long j2 = readingPeriod.info.durationUs;
                                setCurrentStreamFinal(renderer, (j2 == C.TIME_UNSET || j2 == Long.MIN_VALUE) ? -9223372036854775807L : readingPeriod.getRendererOffset() + readingPeriod.info.durationUs);
                            }
                            i3++;
                        } else {
                            return;
                        }
                    }
                }
            } else if (hasReadingPeriodFinishedReading()) {
                if (readingPeriod.getNext().prepared || this.rendererPositionUs >= readingPeriod.getNext().getStartPositionRendererTime()) {
                    TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
                    MediaPeriodHolder advanceReadingPeriod = this.queue.advanceReadingPeriod();
                    TrackSelectorResult trackSelectorResult2 = advanceReadingPeriod.getTrackSelectorResult();
                    Timeline timeline = this.playbackInfo.timeline;
                    updatePlaybackSpeedSettingsForNewPeriod(timeline, advanceReadingPeriod.info.id, timeline, readingPeriod.info.id, C.TIME_UNSET);
                    if (!advanceReadingPeriod.prepared || advanceReadingPeriod.mediaPeriod.readDiscontinuity() == C.TIME_UNSET) {
                        for (int i4 = 0; i4 < this.renderers.length; i4++) {
                            boolean isRendererEnabled = trackSelectorResult.isRendererEnabled(i4);
                            boolean isRendererEnabled2 = trackSelectorResult2.isRendererEnabled(i4);
                            if (isRendererEnabled && !this.renderers[i4].isCurrentStreamFinal()) {
                                boolean z2 = this.rendererCapabilities[i4].getTrackType() == -2;
                                RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i4];
                                RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i4];
                                if (!isRendererEnabled2 || !rendererConfiguration2.equals(rendererConfiguration) || z2) {
                                    setCurrentStreamFinal(this.renderers[i4], advanceReadingPeriod.getStartPositionRendererTime());
                                }
                            }
                        }
                        return;
                    }
                    setAllRendererStreamsFinal(advanceReadingPeriod.getStartPositionRendererTime());
                }
            }
        }
    }

    private void maybeUpdateReadingRenderers() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null && this.queue.getPlayingPeriod() != readingPeriod && !readingPeriod.allRenderersInCorrectState && replaceStreamsOrDisableRendererForTransition()) {
            enableRenderers();
        }
    }

    private void mediaSourceListUpdateRequestedInternal() throws ExoPlaybackException {
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.createTimeline(), true);
    }

    private void moveMediaItemsInternal(MoveMediaItemsMessage moveMediaItemsMessage) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.moveMediaSourceRange(moveMediaItemsMessage.fromIndex, moveMediaItemsMessage.toIndex, moveMediaItemsMessage.newFromIndex, moveMediaItemsMessage.shuffleOrder), false);
    }

    private void notifyTrackSelectionDiscontinuity() {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onDiscontinuity();
                }
            }
        }
    }

    private void notifyTrackSelectionPlayWhenReadyChanged(boolean z2) {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlayWhenReadyChanged(z2);
                }
            }
        }
    }

    private void notifyTrackSelectionRebuffer() {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onRebuffer();
                }
            }
        }
    }

    private void prepareInternal() {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        resetInternal(false, false, false, true);
        this.loadControl.onPrepared();
        setState(this.playbackInfo.timeline.isEmpty() ? 4 : 2);
        this.mediaSourceList.prepare(this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void releaseInternal() {
        resetInternal(true, false, true, false);
        this.loadControl.onReleased();
        setState(1);
        this.internalPlaybackThread.quit();
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void removeMediaItemsInternal(int i3, int i4, ShuffleOrder shuffleOrder) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.removeMediaSourceRange(i3, i4, shuffleOrder), false);
    }

    private boolean replaceStreamsOrDisableRendererForTransition() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
        int i3 = 0;
        boolean z2 = false;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i3 >= rendererArr.length) {
                return !z2;
            }
            Renderer renderer = rendererArr[i3];
            if (isRendererEnabled(renderer)) {
                boolean z3 = renderer.getStream() != readingPeriod.sampleStreams[i3];
                if (!trackSelectorResult.isRendererEnabled(i3) || z3) {
                    if (!renderer.isCurrentStreamFinal()) {
                        renderer.replaceStream(getFormats(trackSelectorResult.selections[i3]), readingPeriod.sampleStreams[i3], readingPeriod.getStartPositionRendererTime(), readingPeriod.getRendererOffset());
                    } else if (renderer.isEnded()) {
                        disableRenderer(renderer);
                    } else {
                        z2 = true;
                    }
                }
            }
            i3++;
        }
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        float f2 = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        boolean z2 = true;
        while (playingPeriod != null && playingPeriod.prepared) {
            TrackSelectorResult selectTracks = playingPeriod.selectTracks(f2, this.playbackInfo.timeline);
            if (!selectTracks.isEquivalent(playingPeriod.getTrackSelectorResult())) {
                if (z2) {
                    MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                    boolean removeAfter = this.queue.removeAfter(playingPeriod2);
                    boolean[] zArr = new boolean[this.renderers.length];
                    long applyTrackSelection = playingPeriod2.applyTrackSelection(selectTracks, this.playbackInfo.positionUs, removeAfter, zArr);
                    PlaybackInfo playbackInfo2 = this.playbackInfo;
                    boolean z3 = (playbackInfo2.playbackState == 4 || applyTrackSelection == playbackInfo2.positionUs) ? false : true;
                    PlaybackInfo playbackInfo3 = this.playbackInfo;
                    MediaPeriodHolder mediaPeriodHolder = playingPeriod2;
                    boolean[] zArr2 = zArr;
                    this.playbackInfo = handlePositionDiscontinuity(playbackInfo3.periodId, applyTrackSelection, playbackInfo3.requestedContentPositionUs, playbackInfo3.discontinuityStartPositionUs, z3, 5);
                    if (z3) {
                        resetRendererPosition(applyTrackSelection);
                    }
                    boolean[] zArr3 = new boolean[this.renderers.length];
                    int i3 = 0;
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i3 >= rendererArr.length) {
                            break;
                        }
                        Renderer renderer = rendererArr[i3];
                        boolean isRendererEnabled = isRendererEnabled(renderer);
                        zArr3[i3] = isRendererEnabled;
                        SampleStream sampleStream = mediaPeriodHolder.sampleStreams[i3];
                        if (isRendererEnabled) {
                            if (sampleStream != renderer.getStream()) {
                                disableRenderer(renderer);
                            } else if (zArr2[i3]) {
                                renderer.resetPosition(this.rendererPositionUs);
                            }
                        }
                        i3++;
                    }
                    enableRenderers(zArr3);
                } else {
                    this.queue.removeAfter(playingPeriod);
                    if (playingPeriod.prepared) {
                        playingPeriod.applyTrackSelection(selectTracks, Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                    }
                }
                handleLoadingMediaPeriodChanged(true);
                if (this.playbackInfo.playbackState != 4) {
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                return;
            }
            if (playingPeriod == readingPeriod) {
                z2 = false;
            }
            playingPeriod = playingPeriod.getNext();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void resetInternal(boolean r30, boolean r31, boolean r32, boolean r33) {
        /*
            r29 = this;
            r1 = r29
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper r0 = r1.handler
            r2 = 2
            r0.removeMessages(r2)
            r2 = 0
            r1.pendingRecoverableRendererError = r2
            r3 = 0
            r1.isRebuffering = r3
            com.appsamurai.storyly.exoplayer2.core.DefaultMediaClock r0 = r1.mediaClock
            r0.stop()
            r4 = 1000000000000(0xe8d4a51000, double:4.94065645841E-312)
            r1.rendererPositionUs = r4
            com.appsamurai.storyly.exoplayer2.core.Renderer[] r4 = r1.renderers
            int r5 = r4.length
            r6 = r3
        L_0x001e:
            java.lang.String r7 = "ExoPlayerImplInternal"
            if (r6 >= r5) goto L_0x0031
            r0 = r4[r6]
            r1.disableRenderer(r0)     // Catch:{ ExoPlaybackException | RuntimeException -> 0x0028 }
            goto L_0x002e
        L_0x0028:
            r0 = move-exception
            java.lang.String r8 = "Disable failed."
            com.appsamurai.storyly.exoplayer2.common.util.Log.e(r7, r8, r0)
        L_0x002e:
            int r6 = r6 + 1
            goto L_0x001e
        L_0x0031:
            if (r30 == 0) goto L_0x0051
            com.appsamurai.storyly.exoplayer2.core.Renderer[] r4 = r1.renderers
            int r5 = r4.length
            r6 = r3
        L_0x0037:
            if (r6 >= r5) goto L_0x0051
            r0 = r4[r6]
            java.util.Set<com.appsamurai.storyly.exoplayer2.core.Renderer> r8 = r1.renderersToReset
            boolean r8 = r8.remove(r0)
            if (r8 == 0) goto L_0x004e
            r0.reset()     // Catch:{ RuntimeException -> 0x0047 }
            goto L_0x004e
        L_0x0047:
            r0 = move-exception
            r8 = r0
            java.lang.String r0 = "Reset failed."
            com.appsamurai.storyly.exoplayer2.common.util.Log.e(r7, r0, r8)
        L_0x004e:
            int r6 = r6 + 1
            goto L_0x0037
        L_0x0051:
            r1.enabledRendererCount = r3
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r4 = r0.periodId
            long r5 = r0.positionUs
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r0.isAd()
            if (r0 != 0) goto L_0x0073
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r7 = r1.period
            boolean r0 = isUsingPlaceholderPeriod(r0, r7)
            if (r0 == 0) goto L_0x006e
            goto L_0x0073
        L_0x006e:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            long r7 = r0.positionUs
            goto L_0x0077
        L_0x0073:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            long r7 = r0.requestedContentPositionUs
        L_0x0077:
            if (r31 == 0) goto L_0x00a4
            r1.pendingInitialSeekPosition = r2
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r0 = r0.timeline
            android.util.Pair r0 = r1.getPlaceholderFirstMediaPeriodPositionUs(r0)
            java.lang.Object r4 = r0.first
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r4 = (com.appsamurai.storyly.exoplayer2.core.source.MediaSource.MediaPeriodId) r4
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r5 = r0.longValue()
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r4.equals(r0)
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 != 0) goto L_0x00a4
            r0 = 1
        L_0x009f:
            r17 = r4
            r25 = r5
            goto L_0x00a6
        L_0x00a4:
            r0 = r3
            goto L_0x009f
        L_0x00a6:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r4 = r1.queue
            r4.clear()
            r1.shouldContinueLoading = r3
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = new com.appsamurai.storyly.exoplayer2.core.PlaybackInfo
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r4 = r1.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r5 = r4.timeline
            int r11 = r4.playbackState
            if (r33 == 0) goto L_0x00b9
        L_0x00b7:
            r12 = r2
            goto L_0x00bc
        L_0x00b9:
            com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException r2 = r4.playbackError
            goto L_0x00b7
        L_0x00bc:
            if (r0 == 0) goto L_0x00c2
            com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray r2 = com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray.EMPTY
        L_0x00c0:
            r14 = r2
            goto L_0x00c5
        L_0x00c2:
            com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray r2 = r4.trackGroups
            goto L_0x00c0
        L_0x00c5:
            if (r0 == 0) goto L_0x00cb
            com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult r2 = r1.emptyTrackSelectorResult
        L_0x00c9:
            r15 = r2
            goto L_0x00ce
        L_0x00cb:
            com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult r2 = r4.trackSelectorResult
            goto L_0x00c9
        L_0x00ce:
            if (r0 == 0) goto L_0x00d7
            com.google.common.collect.ImmutableList r0 = com.google.common.collect.ImmutableList.of()
        L_0x00d4:
            r16 = r0
            goto L_0x00da
        L_0x00d7:
            java.util.List<com.appsamurai.storyly.exoplayer2.common.metadata.Metadata> r0 = r4.staticMetadata
            goto L_0x00d4
        L_0x00da:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.playbackInfo
            boolean r2 = r0.playWhenReady
            r18 = r2
            int r2 = r0.playbackSuppressionReason
            r19 = r2
            com.appsamurai.storyly.exoplayer2.common.PlaybackParameters r0 = r0.playbackParameters
            r20 = r0
            boolean r0 = r1.offloadSchedulingEnabled
            r27 = r0
            r28 = 0
            r13 = 0
            r23 = 0
            r4 = r3
            r6 = r17
            r9 = r25
            r21 = r25
            r4.<init>(r5, r6, r7, r9, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r23, r25, r27, r28)
            r1.playbackInfo = r3
            if (r32 == 0) goto L_0x0104
            com.appsamurai.storyly.exoplayer2.core.MediaSourceList r0 = r1.mediaSourceList
            r0.release()
        L_0x0104:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.resetInternal(boolean, boolean, boolean, boolean):void");
    }

    private void resetPendingPauseAtEndOfPeriod() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        this.pendingPauseAtEndOfPeriod = playingPeriod != null && playingPeriod.info.isLastInTimelineWindow && this.pauseAtEndOfWindow;
    }

    private void resetRendererPosition(long j2) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long rendererTime = playingPeriod == null ? j2 + MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US : playingPeriod.toRendererTime(j2);
        this.rendererPositionUs = rendererTime;
        this.mediaClock.resetPosition(rendererTime);
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.resetPosition(this.rendererPositionUs);
            }
        }
        notifyTrackSelectionDiscontinuity();
    }

    private static void resolvePendingMessageEndOfStreamPosition(Timeline timeline, PendingMessageInfo pendingMessageInfo, Timeline.Window window2, Timeline.Period period2) {
        int i3 = timeline.getWindow(timeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period2).windowIndex, window2).lastPeriodIndex;
        Object obj = timeline.getPeriod(i3, period2, true).uid;
        long j2 = period2.durationUs;
        pendingMessageInfo.setResolvedPosition(i3, j2 != C.TIME_UNSET ? j2 - 1 : Long.MAX_VALUE, obj);
    }

    private static boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo, Timeline timeline, Timeline timeline2, int i3, boolean z2, Timeline.Window window2, Timeline.Period period2) {
        PendingMessageInfo pendingMessageInfo2 = pendingMessageInfo;
        Timeline timeline3 = timeline;
        Timeline timeline4 = timeline2;
        Timeline.Window window3 = window2;
        Timeline.Period period3 = period2;
        Object obj = pendingMessageInfo2.resolvedPeriodUid;
        if (obj == null) {
            Pair<Object, Long> resolveSeekPositionUs = resolveSeekPositionUs(timeline, new SeekPosition(pendingMessageInfo2.message.getTimeline(), pendingMessageInfo2.message.getMediaItemIndex(), pendingMessageInfo2.message.getPositionMs() == Long.MIN_VALUE ? C.TIME_UNSET : Util.msToUs(pendingMessageInfo2.message.getPositionMs())), false, i3, z2, window2, period2);
            if (resolveSeekPositionUs == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(timeline3.getIndexOfPeriod(resolveSeekPositionUs.first), ((Long) resolveSeekPositionUs.second).longValue(), resolveSeekPositionUs.first);
            if (pendingMessageInfo2.message.getPositionMs() == Long.MIN_VALUE) {
                resolvePendingMessageEndOfStreamPosition(timeline3, pendingMessageInfo, window3, period3);
            }
            return true;
        }
        int indexOfPeriod = timeline3.getIndexOfPeriod(obj);
        if (indexOfPeriod == -1) {
            return false;
        }
        if (pendingMessageInfo2.message.getPositionMs() == Long.MIN_VALUE) {
            resolvePendingMessageEndOfStreamPosition(timeline3, pendingMessageInfo, window3, period3);
            return true;
        }
        pendingMessageInfo2.resolvedPeriodIndex = indexOfPeriod;
        timeline4.getPeriodByUid(pendingMessageInfo2.resolvedPeriodUid, period3);
        if (period3.isPlaceholder && timeline4.getWindow(period3.windowIndex, window3).firstPeriodIndex == timeline4.getIndexOfPeriod(pendingMessageInfo2.resolvedPeriodUid)) {
            Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window2, period2, timeline3.getPeriodByUid(pendingMessageInfo2.resolvedPeriodUid, period3).windowIndex, period2.getPositionInWindowUs() + pendingMessageInfo2.resolvedPeriodTimeUs);
            pendingMessageInfo.setResolvedPosition(timeline3.getIndexOfPeriod(periodPositionUs.first), ((Long) periodPositionUs.second).longValue(), periodPositionUs.first);
        }
        return true;
    }

    private void resolvePendingMessagePositions(Timeline timeline, Timeline timeline2) {
        if (!timeline.isEmpty() || !timeline2.isEmpty()) {
            for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
                if (!resolvePendingMessagePosition(this.pendingMessages.get(size), timeline, timeline2, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                    this.pendingMessages.get(size).message.markAsProcessed(false);
                    this.pendingMessages.remove(size);
                }
            }
            Collections.sort(this.pendingMessages);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.PositionUpdateForPlaylistChange resolvePositionForPlaylistChange(com.appsamurai.storyly.exoplayer2.common.Timeline r30, com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r31, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.SeekPosition r32, com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r33, int r34, boolean r35, com.appsamurai.storyly.exoplayer2.common.Timeline.Window r36, com.appsamurai.storyly.exoplayer2.common.Timeline.Period r37) {
        /*
            r7 = r30
            r8 = r31
            r9 = r32
            r10 = r35
            r11 = r37
            boolean r0 = r30.isEmpty()
            if (r0 == 0) goto L_0x0025
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r0 = new com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PositionUpdateForPlaylistChange
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r2 = com.appsamurai.storyly.exoplayer2.core.PlaybackInfo.getDummyPeriodForEmptyTimeline()
            r8 = 1
            r9 = 0
            r3 = 0
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7 = 0
            r1 = r0
            r1.<init>(r2, r3, r5, r7, r8, r9)
            return r0
        L_0x0025:
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r14 = r8.periodId
            java.lang.Object r12 = r14.periodUid
            boolean r13 = isUsingPlaceholderPeriod(r8, r11)
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r0 = r8.periodId
            boolean r0 = r0.isAd()
            if (r0 != 0) goto L_0x003c
            if (r13 == 0) goto L_0x0038
            goto L_0x003c
        L_0x0038:
            long r0 = r8.positionUs
        L_0x003a:
            r15 = r0
            goto L_0x003f
        L_0x003c:
            long r0 = r8.requestedContentPositionUs
            goto L_0x003a
        L_0x003f:
            r17 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = -1
            r19 = 0
            r20 = 1
            if (r9 == 0) goto L_0x00a5
            r2 = 1
            r0 = r30
            r1 = r32
            r3 = r34
            r4 = r35
            r5 = r36
            r21 = r14
            r14 = r6
            r6 = r37
            android.util.Pair r0 = resolveSeekPositionUs(r0, r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x006d
            int r0 = r7.getFirstWindowIndex(r10)
            r6 = r0
            r0 = r15
            r2 = r19
            r3 = r2
            r4 = r20
            goto L_0x0098
        L_0x006d:
            long r1 = r9.windowPositionUs
            int r1 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r1 != 0) goto L_0x007f
            java.lang.Object r0 = r0.first
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r7.getPeriodByUid(r0, r11)
            int r6 = r0.windowIndex
            r0 = r15
            r2 = r19
            goto L_0x008c
        L_0x007f:
            java.lang.Object r12 = r0.first
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r6 = r14
            r2 = r20
        L_0x008c:
            int r3 = r8.playbackState
            r4 = 4
            if (r3 != r4) goto L_0x0094
            r3 = r20
            goto L_0x0096
        L_0x0094:
            r3 = r19
        L_0x0096:
            r4 = r19
        L_0x0098:
            r9 = r36
            r29 = r2
            r27 = r3
            r28 = r4
            r3 = r6
        L_0x00a1:
            r6 = r21
            goto L_0x0154
        L_0x00a5:
            r21 = r14
            r14 = r6
            com.appsamurai.storyly.exoplayer2.common.Timeline r0 = r8.timeline
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x00bf
            int r0 = r7.getFirstWindowIndex(r10)
        L_0x00b4:
            r9 = r36
            r3 = r0
            r0 = r15
            r27 = r19
            r28 = r27
            r29 = r28
            goto L_0x00a1
        L_0x00bf:
            int r0 = r7.getIndexOfPeriod(r12)
            if (r0 != r14) goto L_0x00f2
            com.appsamurai.storyly.exoplayer2.common.Timeline r5 = r8.timeline
            r0 = r36
            r1 = r37
            r2 = r34
            r3 = r35
            r4 = r12
            r6 = r30
            java.lang.Object r0 = resolveSubsequentPeriod(r0, r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x00df
            int r0 = r7.getFirstWindowIndex(r10)
            r4 = r20
            goto L_0x00e7
        L_0x00df:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r7.getPeriodByUid(r0, r11)
            int r0 = r0.windowIndex
            r4 = r19
        L_0x00e7:
            r9 = r36
            r3 = r0
            r28 = r4
            r0 = r15
            r27 = r19
            r29 = r27
            goto L_0x00a1
        L_0x00f2:
            int r0 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r0 != 0) goto L_0x00fd
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r7.getPeriodByUid(r12, r11)
            int r0 = r0.windowIndex
            goto L_0x00b4
        L_0x00fd:
            if (r13 == 0) goto L_0x0148
            com.appsamurai.storyly.exoplayer2.common.Timeline r0 = r8.timeline
            r6 = r21
            java.lang.Object r1 = r6.periodUid
            r0.getPeriodByUid(r1, r11)
            com.appsamurai.storyly.exoplayer2.common.Timeline r0 = r8.timeline
            int r1 = r11.windowIndex
            r9 = r36
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r0 = r0.getWindow(r1, r9)
            int r0 = r0.firstPeriodIndex
            com.appsamurai.storyly.exoplayer2.common.Timeline r1 = r8.timeline
            java.lang.Object r2 = r6.periodUid
            int r1 = r1.getIndexOfPeriod(r2)
            if (r0 != r1) goto L_0x013f
            long r0 = r37.getPositionInWindowUs()
            long r4 = r0 + r15
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r0 = r7.getPeriodByUid(r12, r11)
            int r3 = r0.windowIndex
            r0 = r30
            r1 = r36
            r2 = r37
            android.util.Pair r0 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            java.lang.Object r12 = r0.first
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            goto L_0x0140
        L_0x013f:
            r0 = r15
        L_0x0140:
            r3 = r14
            r27 = r19
            r28 = r27
            r29 = r20
            goto L_0x0154
        L_0x0148:
            r9 = r36
            r6 = r21
            r3 = r14
            r0 = r15
            r27 = r19
            r28 = r27
            r29 = r28
        L_0x0154:
            if (r3 == r14) goto L_0x0174
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0 = r30
            r1 = r36
            r2 = r37
            android.util.Pair r0 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            java.lang.Object r12 = r0.first
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r2 = r33
            r25 = r17
            goto L_0x0178
        L_0x0174:
            r2 = r33
            r25 = r0
        L_0x0178:
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r2 = r2.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r7, r12, r0)
            int r3 = r2.nextAdGroupIndex
            if (r3 == r14) goto L_0x018a
            int r4 = r6.nextAdGroupIndex
            if (r4 == r14) goto L_0x0187
            if (r3 < r4) goto L_0x0187
            goto L_0x018a
        L_0x0187:
            r3 = r19
            goto L_0x018c
        L_0x018a:
            r3 = r20
        L_0x018c:
            java.lang.Object r4 = r6.periodUid
            boolean r4 = r4.equals(r12)
            if (r4 == 0) goto L_0x01a3
            boolean r4 = r6.isAd()
            if (r4 != 0) goto L_0x01a3
            boolean r4 = r2.isAd()
            if (r4 != 0) goto L_0x01a3
            if (r3 == 0) goto L_0x01a3
            goto L_0x01a5
        L_0x01a3:
            r20 = r19
        L_0x01a5:
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r17 = r7.getPeriodByUid(r12, r11)
            r12 = r13
            r13 = r6
            r3 = r6
            r14 = r15
            r16 = r2
            r18 = r25
            boolean r4 = isIgnorableServerSideAdInsertionPeriodChange(r12, r13, r14, r16, r17, r18)
            if (r20 != 0) goto L_0x01b9
            if (r4 == 0) goto L_0x01ba
        L_0x01b9:
            r2 = r3
        L_0x01ba:
            boolean r4 = r2.isAd()
            if (r4 == 0) goto L_0x01c8
            boolean r0 = r2.equals(r3)
            if (r0 == 0) goto L_0x01cb
            long r0 = r8.positionUs
        L_0x01c8:
            r23 = r0
            goto L_0x01e2
        L_0x01cb:
            java.lang.Object r0 = r2.periodUid
            r7.getPeriodByUid(r0, r11)
            int r0 = r2.adIndexInAdGroup
            int r1 = r2.adGroupIndex
            int r1 = r11.getFirstAdIndexToPlay(r1)
            if (r0 != r1) goto L_0x01df
            long r0 = r37.getAdResumePositionUs()
            goto L_0x01c8
        L_0x01df:
            r0 = 0
            goto L_0x01c8
        L_0x01e2:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r0 = new com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PositionUpdateForPlaylistChange
            r21 = r0
            r22 = r2
            r21.<init>(r22, r23, r25, r27, r28, r29)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.resolvePositionForPlaylistChange(com.appsamurai.storyly.exoplayer2.common.Timeline, com.appsamurai.storyly.exoplayer2.core.PlaybackInfo, com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$SeekPosition, com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue, int, boolean, com.appsamurai.storyly.exoplayer2.common.Timeline$Window, com.appsamurai.storyly.exoplayer2.common.Timeline$Period):com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PositionUpdateForPlaylistChange");
    }

    @Nullable
    private static Pair<Object, Long> resolveSeekPositionUs(Timeline timeline, SeekPosition seekPosition, boolean z2, int i3, boolean z3, Timeline.Window window2, Timeline.Period period2) {
        Object resolveSubsequentPeriod;
        Timeline timeline2 = timeline;
        SeekPosition seekPosition2 = seekPosition;
        Timeline.Period period3 = period2;
        Timeline timeline3 = seekPosition2.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        Timeline timeline4 = timeline3.isEmpty() ? timeline2 : timeline3;
        try {
            Pair<Object, Long> periodPositionUs = timeline4.getPeriodPositionUs(window2, period2, seekPosition2.windowIndex, seekPosition2.windowPositionUs);
            if (timeline.equals(timeline4)) {
                return periodPositionUs;
            }
            if (timeline.getIndexOfPeriod(periodPositionUs.first) == -1) {
                Timeline.Window window3 = window2;
                if (z2 && (resolveSubsequentPeriod = resolveSubsequentPeriod(window2, period2, i3, z3, periodPositionUs.first, timeline4, timeline)) != null) {
                    return timeline.getPeriodPositionUs(window2, period2, timeline.getPeriodByUid(resolveSubsequentPeriod, period3).windowIndex, C.TIME_UNSET);
                }
                return null;
            } else if (!timeline4.getPeriodByUid(periodPositionUs.first, period3).isPlaceholder || timeline4.getWindow(period3.windowIndex, window2).firstPeriodIndex != timeline4.getIndexOfPeriod(periodPositionUs.first)) {
                return periodPositionUs;
            } else {
                return timeline.getPeriodPositionUs(window2, period2, timeline.getPeriodByUid(periodPositionUs.first, period3).windowIndex, seekPosition2.windowPositionUs);
            }
        } catch (IndexOutOfBoundsException unused) {
        }
    }

    @Nullable
    public static Object resolveSubsequentPeriod(Timeline.Window window2, Timeline.Period period2, int i3, boolean z2, Object obj, Timeline timeline, Timeline timeline2) {
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int i4 = indexOfPeriod;
        int i5 = -1;
        for (int i6 = 0; i6 < periodCount && i5 == -1; i6++) {
            i4 = timeline.getNextPeriodIndex(i4, period2, window2, i3, z2);
            if (i4 == -1) {
                break;
            }
            i5 = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i4));
        }
        if (i5 == -1) {
            return null;
        }
        return timeline2.getUidOfPeriod(i5);
    }

    private void scheduleNextWork(long j2, long j3) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, j2 + j3);
    }

    private void seekToCurrentPosition(boolean z2) throws ExoPlaybackException {
        MediaSource.MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long seekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true, false);
        if (seekToPeriodPosition != this.playbackInfo.positionUs) {
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, seekToPeriodPosition, playbackInfo2.requestedContentPositionUs, playbackInfo2.discontinuityStartPositionUs, z2, 5);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ac A[Catch:{ all -> 0x00af }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b3 A[Catch:{ all -> 0x00af }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void seekToInternal(com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.SeekPosition r19) throws com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException {
        /*
            r18 = this;
            r11 = r18
            r0 = r19
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$PlaybackInfoUpdate r1 = r11.playbackInfoUpdate
            r8 = 1
            r1.incrementPendingOperationAcks(r8)
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r1 = r1.timeline
            int r4 = r11.repeatMode
            boolean r5 = r11.shuffleModeEnabled
            com.appsamurai.storyly.exoplayer2.common.Timeline$Window r6 = r11.window
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r7 = r11.period
            r3 = 1
            r2 = r19
            android.util.Pair r1 = resolveSeekPositionUs(r1, r2, r3, r4, r5, r6, r7)
            r2 = 0
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = 0
            if (r1 != 0) goto L_0x004c
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r7 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r7 = r7.timeline
            android.util.Pair r7 = r11.getPlaceholderFirstMediaPeriodPositionUs(r7)
            java.lang.Object r9 = r7.first
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r9 = (com.appsamurai.storyly.exoplayer2.core.source.MediaSource.MediaPeriodId) r9
            java.lang.Object r7 = r7.second
            java.lang.Long r7 = (java.lang.Long) r7
            long r12 = r7.longValue()
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r7 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r7 = r7.timeline
            boolean r7 = r7.isEmpty()
            r7 = r7 ^ r8
            r10 = r7
            r16 = r4
        L_0x0047:
            r4 = r12
            r12 = r16
            goto L_0x00a2
        L_0x004c:
            java.lang.Object r7 = r1.first
            java.lang.Object r9 = r1.second
            java.lang.Long r9 = (java.lang.Long) r9
            long r12 = r9.longValue()
            long r9 = r0.windowPositionUs
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 != 0) goto L_0x005e
            r9 = r4
            goto L_0x005f
        L_0x005e:
            r9 = r12
        L_0x005f:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r14 = r11.queue
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r15 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r15 = r15.timeline
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r7 = r14.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r15, r7, r12)
            boolean r14 = r7.isAd()
            if (r14 == 0) goto L_0x0094
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r4 = r11.playbackInfo
            com.appsamurai.storyly.exoplayer2.common.Timeline r4 = r4.timeline
            java.lang.Object r5 = r7.periodUid
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r12 = r11.period
            r4.getPeriodByUid(r5, r12)
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r4 = r11.period
            int r5 = r7.adGroupIndex
            int r4 = r4.getFirstAdIndexToPlay(r5)
            int r5 = r7.adIndexInAdGroup
            if (r4 != r5) goto L_0x008e
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r4 = r11.period
            long r4 = r4.getAdResumePositionUs()
            r12 = r4
            goto L_0x008f
        L_0x008e:
            r12 = r2
        L_0x008f:
            r4 = r12
            r12 = r9
            r9 = r7
            r10 = r8
            goto L_0x00a2
        L_0x0094:
            long r14 = r0.windowPositionUs
            int r4 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x009c
            r4 = r8
            goto L_0x009d
        L_0x009c:
            r4 = r6
        L_0x009d:
            r16 = r9
            r10 = r4
            r9 = r7
            goto L_0x0047
        L_0x00a2:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r7 = r11.playbackInfo     // Catch:{ all -> 0x00af }
            com.appsamurai.storyly.exoplayer2.common.Timeline r7 = r7.timeline     // Catch:{ all -> 0x00af }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x00af }
            if (r7 == 0) goto L_0x00b3
            r11.pendingInitialSeekPosition = r0     // Catch:{ all -> 0x00af }
            goto L_0x00c2
        L_0x00af:
            r0 = move-exception
            r7 = r4
            goto L_0x014b
        L_0x00b3:
            r0 = 4
            if (r1 != 0) goto L_0x00c5
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo     // Catch:{ all -> 0x00af }
            int r1 = r1.playbackState     // Catch:{ all -> 0x00af }
            if (r1 == r8) goto L_0x00bf
            r11.setState(r0)     // Catch:{ all -> 0x00af }
        L_0x00bf:
            r11.resetInternal(r6, r8, r6, r8)     // Catch:{ all -> 0x00af }
        L_0x00c2:
            r7 = r4
            goto L_0x0139
        L_0x00c5:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r11.playbackInfo     // Catch:{ all -> 0x00af }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r1 = r1.periodId     // Catch:{ all -> 0x00af }
            boolean r1 = r9.equals(r1)     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0114
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r1 = r11.queue     // Catch:{ all -> 0x00af }
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r1 = r1.getPlayingPeriod()     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x00e8
            boolean r7 = r1.prepared     // Catch:{ all -> 0x00af }
            if (r7 == 0) goto L_0x00e8
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x00e8
            com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod r1 = r1.mediaPeriod     // Catch:{ all -> 0x00af }
            com.appsamurai.storyly.exoplayer2.core.SeekParameters r2 = r11.seekParameters     // Catch:{ all -> 0x00af }
            long r1 = r1.getAdjustedSeekPositionUs(r4, r2)     // Catch:{ all -> 0x00af }
            goto L_0x00e9
        L_0x00e8:
            r1 = r4
        L_0x00e9:
            long r14 = com.appsamurai.storyly.exoplayer2.common.util.Util.usToMs(r1)     // Catch:{ all -> 0x00af }
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r11.playbackInfo     // Catch:{ all -> 0x00af }
            long r6 = r3.positionUs     // Catch:{ all -> 0x00af }
            long r6 = com.appsamurai.storyly.exoplayer2.common.util.Util.usToMs(r6)     // Catch:{ all -> 0x00af }
            int r3 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x0115
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r11.playbackInfo     // Catch:{ all -> 0x00af }
            int r6 = r3.playbackState     // Catch:{ all -> 0x00af }
            r7 = 2
            if (r6 == r7) goto L_0x0103
            r7 = 3
            if (r6 != r7) goto L_0x0115
        L_0x0103:
            long r7 = r3.positionUs     // Catch:{ all -> 0x00af }
            r0 = 2
            r1 = r18
            r2 = r9
            r3 = r7
            r5 = r12
            r9 = r10
            r10 = r0
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r0
            return
        L_0x0114:
            r1 = r4
        L_0x0115:
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r3 = r11.playbackInfo     // Catch:{ all -> 0x00af }
            int r3 = r3.playbackState     // Catch:{ all -> 0x00af }
            if (r3 != r0) goto L_0x011d
            r0 = r8
            goto L_0x011e
        L_0x011d:
            r0 = 0
        L_0x011e:
            long r14 = r11.seekToPeriodPosition(r9, r1, r0)     // Catch:{ all -> 0x00af }
            int r0 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x0127
            goto L_0x0128
        L_0x0127:
            r8 = 0
        L_0x0128:
            r8 = r8 | r10
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r11.playbackInfo     // Catch:{ all -> 0x0148 }
            com.appsamurai.storyly.exoplayer2.common.Timeline r4 = r0.timeline     // Catch:{ all -> 0x0148 }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$MediaPeriodId r5 = r0.periodId     // Catch:{ all -> 0x0148 }
            r1 = r18
            r2 = r4
            r3 = r9
            r6 = r12
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0148 }
            r10 = r8
            r7 = r14
        L_0x0139:
            r0 = 2
            r1 = r18
            r2 = r9
            r3 = r7
            r5 = r12
            r9 = r10
            r10 = r0
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r0
            return
        L_0x0148:
            r0 = move-exception
            r10 = r8
            r7 = r14
        L_0x014b:
            r14 = 2
            r1 = r18
            r2 = r9
            r3 = r7
            r5 = r12
            r9 = r10
            r10 = r14
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r1 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r1
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.seekToInternal(com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal$SeekPosition):void");
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j2, boolean z2) throws ExoPlaybackException {
        return seekToPeriodPosition(mediaPeriodId, j2, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod(), z2);
    }

    private void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getPositionMs() == C.TIME_UNSET) {
            sendMessageToTarget(playerMessage);
        } else if (this.playbackInfo.timeline.isEmpty()) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
        } else {
            PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
            Timeline timeline = this.playbackInfo.timeline;
            if (resolvePendingMessagePosition(pendingMessageInfo, timeline, timeline, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                this.pendingMessages.add(pendingMessageInfo);
                Collections.sort(this.pendingMessages);
                return;
            }
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getLooper() == this.playbackLooper) {
            deliverMessage(playerMessage);
            int i3 = this.playbackInfo.playbackState;
            if (i3 == 3 || i3 == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(PlayerMessage playerMessage) {
        Looper looper = playerMessage.getLooper();
        if (!looper.getThread().isAlive()) {
            Log.w("TAG", "Trying to send message on a dead thread.");
            playerMessage.markAsProcessed(false);
            return;
        }
        this.clock.createHandler(looper, (Handler.Callback) null).post(new s(this, playerMessage, 1));
    }

    private void setAllRendererStreamsFinal(long j2) {
        for (Renderer renderer : this.renderers) {
            if (renderer.getStream() != null) {
                setCurrentStreamFinal(renderer, j2);
            }
        }
    }

    private void setCurrentStreamFinal(Renderer renderer, long j2) {
        renderer.setCurrentStreamFinal();
        if (renderer instanceof TextRenderer) {
            ((TextRenderer) renderer).setFinalStreamEndPositionUs(j2);
        }
    }

    private void setForegroundModeInternal(boolean z2, @Nullable AtomicBoolean atomicBoolean) {
        if (this.foregroundMode != z2) {
            this.foregroundMode = z2;
            if (!z2) {
                for (Renderer renderer : this.renderers) {
                    if (!isRendererEnabled(renderer) && this.renderersToReset.remove(renderer)) {
                        renderer.reset();
                    }
                }
            }
        }
        if (atomicBoolean != null) {
            synchronized (this) {
                atomicBoolean.set(true);
                notifyAll();
            }
        }
    }

    private void setMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        if (mediaSourceListUpdateMessage.windowIndex != -1) {
            this.pendingInitialSeekPosition = new SeekPosition(new PlaylistTimeline(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), mediaSourceListUpdateMessage.windowIndex, mediaSourceListUpdateMessage.positionUs);
        }
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setMediaSources(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    private void setOffloadSchedulingEnabledInternal(boolean z2) {
        if (z2 != this.offloadSchedulingEnabled) {
            this.offloadSchedulingEnabled = z2;
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            int i3 = playbackInfo2.playbackState;
            if (z2 || i3 == 4 || i3 == 1) {
                this.playbackInfo = playbackInfo2.copyWithOffloadSchedulingEnabled(z2);
            } else {
                this.handler.sendEmptyMessage(2);
            }
        }
    }

    private void setPauseAtEndOfWindowInternal(boolean z2) throws ExoPlaybackException {
        this.pauseAtEndOfWindow = z2;
        resetPendingPauseAtEndOfPeriod();
        if (this.pendingPauseAtEndOfPeriod && this.queue.getReadingPeriod() != this.queue.getPlayingPeriod()) {
            seekToCurrentPosition(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void setPlayWhenReadyInternal(boolean z2, int i3, boolean z3, int i4) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(z3 ? 1 : 0);
        this.playbackInfoUpdate.setPlayWhenReadyChangeReason(i4);
        this.playbackInfo = this.playbackInfo.copyWithPlayWhenReady(z2, i3);
        this.isRebuffering = false;
        notifyTrackSelectionPlayWhenReadyChanged(z2);
        if (!shouldPlayWhenReady()) {
            stopRenderers();
            updatePlaybackPositions();
            return;
        }
        int i5 = this.playbackInfo.playbackState;
        if (i5 == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (i5 == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        this.mediaClock.setPlaybackParameters(playbackParameters);
        handlePlaybackParameters(this.mediaClock.getPlaybackParameters(), true);
    }

    private void setRepeatModeInternal(int i3) throws ExoPlaybackException {
        this.repeatMode = i3;
        if (!this.queue.updateRepeatMode(this.playbackInfo.timeline, i3)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters2) {
        this.seekParameters = seekParameters2;
    }

    private void setShuffleModeEnabledInternal(boolean z2) throws ExoPlaybackException {
        this.shuffleModeEnabled = z2;
        if (!this.queue.updateShuffleModeEnabled(this.playbackInfo.timeline, z2)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleOrderInternal(ShuffleOrder shuffleOrder) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setShuffleOrder(shuffleOrder), false);
    }

    private void setState(int i3) {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playbackState != i3) {
            if (i3 != 2) {
                this.playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
            }
            this.playbackInfo = playbackInfo2.copyWithPlaybackState(i3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r0 = (r0 = r6.queue.getPlayingPeriod()).getNext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean shouldAdvancePlayingPeriod() {
        /*
            r6 = this;
            boolean r0 = r6.shouldPlayWhenReady()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r0 = r6.pendingPauseAtEndOfPeriod
            if (r0 == 0) goto L_0x000d
            return r1
        L_0x000d:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodQueue r0 = r6.queue
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r0 = r0.getPlayingPeriod()
            if (r0 != 0) goto L_0x0016
            return r1
        L_0x0016:
            com.appsamurai.storyly.exoplayer2.core.MediaPeriodHolder r0 = r0.getNext()
            if (r0 == 0) goto L_0x002b
            long r2 = r6.rendererPositionUs
            long r4 = r0.getStartPositionRendererTime()
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x002b
            boolean r6 = r0.allRenderersInCorrectState
            if (r6 == 0) goto L_0x002b
            r1 = 1
        L_0x002b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.shouldAdvancePlayingPeriod():boolean");
    }

    private boolean shouldContinueLoading() {
        if (!isLoadingPossible()) {
            return false;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        return this.loadControl.shouldContinueLoading(loadingPeriod == this.queue.getPlayingPeriod() ? loadingPeriod.toPeriodTime(this.rendererPositionUs) : loadingPeriod.toPeriodTime(this.rendererPositionUs) - loadingPeriod.info.startPositionUs, getTotalBufferedDurationUs(loadingPeriod.getNextLoadPositionUs()), this.mediaClock.getPlaybackParameters().speed);
    }

    private boolean shouldPlayWhenReady() {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        return playbackInfo2.playWhenReady && playbackInfo2.playbackSuppressionReason == 0;
    }

    private boolean shouldTransitionToReadyState(boolean z2) {
        if (this.enabledRendererCount == 0) {
            return isTimelineReady();
        }
        if (!z2) {
            return false;
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (!playbackInfo2.isLoading) {
            return true;
        }
        long targetLiveOffsetUs = shouldUseLivePlaybackSpeedControl(playbackInfo2.timeline, this.queue.getPlayingPeriod().info.id) ? this.livePlaybackSpeedControl.getTargetLiveOffsetUs() : C.TIME_UNSET;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        return (loadingPeriod.isFullyBuffered() && loadingPeriod.info.isFinal) || (loadingPeriod.info.id.isAd() && !loadingPeriod.prepared) || this.loadControl.shouldStartPlayback(getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.isRebuffering, targetLiveOffsetUs);
    }

    private boolean shouldUseLivePlaybackSpeedControl(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (mediaPeriodId.isAd() || timeline.isEmpty()) {
            return false;
        }
        timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
        if (!this.window.isLive()) {
            return false;
        }
        Timeline.Window window2 = this.window;
        return window2.isDynamic && window2.windowStartTimeMs != C.TIME_UNSET;
    }

    private void startRenderers() throws ExoPlaybackException {
        this.isRebuffering = false;
        this.mediaClock.start();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.start();
            }
        }
    }

    private void stopInternal(boolean z2, boolean z3) {
        resetInternal(z2 || !this.foregroundMode, false, true, false);
        this.playbackInfoUpdate.incrementPendingOperationAcks(z3 ? 1 : 0);
        this.loadControl.onStopped();
        setState(1);
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                ensureStopped(renderer);
            }
        }
    }

    private void updateIsLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        boolean z2 = this.shouldContinueLoading || (loadingPeriod != null && loadingPeriod.mediaPeriod.isLoading());
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (z2 != playbackInfo2.isLoading) {
            this.playbackInfo = playbackInfo2.copyWithIsLoading(z2);
        }
    }

    private void updateLoadControlTrackSelection(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackGroupArray, trackSelectorResult.selections);
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        if (!this.playbackInfo.timeline.isEmpty() && this.mediaSourceList.isPrepared()) {
            maybeUpdateLoadingPeriod();
            maybeUpdateReadingPeriod();
            maybeUpdateReadingRenderers();
            maybeUpdatePlayingPeriod();
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            long readDiscontinuity = playingPeriod.prepared ? playingPeriod.mediaPeriod.readDiscontinuity() : -9223372036854775807L;
            if (readDiscontinuity != C.TIME_UNSET) {
                resetRendererPosition(readDiscontinuity);
                if (readDiscontinuity != this.playbackInfo.positionUs) {
                    PlaybackInfo playbackInfo2 = this.playbackInfo;
                    this.playbackInfo = handlePositionDiscontinuity(playbackInfo2.periodId, readDiscontinuity, playbackInfo2.requestedContentPositionUs, readDiscontinuity, true, 5);
                }
            } else {
                long syncAndGetPositionUs = this.mediaClock.syncAndGetPositionUs(playingPeriod != this.queue.getReadingPeriod());
                this.rendererPositionUs = syncAndGetPositionUs;
                long periodTime = playingPeriod.toPeriodTime(syncAndGetPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                this.playbackInfo.positionUs = periodTime;
            }
            this.playbackInfo.bufferedPositionUs = this.queue.getLoadingPeriod().getBufferedPositionUs();
            this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
            PlaybackInfo playbackInfo3 = this.playbackInfo;
            if (playbackInfo3.playWhenReady && playbackInfo3.playbackState == 3 && shouldUseLivePlaybackSpeedControl(playbackInfo3.timeline, playbackInfo3.periodId) && this.playbackInfo.playbackParameters.speed == 1.0f) {
                float adjustedPlaybackSpeed = this.livePlaybackSpeedControl.getAdjustedPlaybackSpeed(getCurrentLiveOffsetUs(), getTotalBufferedDurationUs());
                if (this.mediaClock.getPlaybackParameters().speed != adjustedPlaybackSpeed) {
                    this.mediaClock.setPlaybackParameters(this.playbackInfo.playbackParameters.withSpeed(adjustedPlaybackSpeed));
                    handlePlaybackParameters(this.playbackInfo.playbackParameters, this.mediaClock.getPlaybackParameters().speed, false, false);
                }
            }
        }
    }

    private void updatePlaybackSpeedSettingsForNewPeriod(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId2, long j2) {
        if (!shouldUseLivePlaybackSpeedControl(timeline, mediaPeriodId)) {
            PlaybackParameters playbackParameters = mediaPeriodId.isAd() ? PlaybackParameters.DEFAULT : this.playbackInfo.playbackParameters;
            if (!this.mediaClock.getPlaybackParameters().equals(playbackParameters)) {
                this.mediaClock.setPlaybackParameters(playbackParameters);
                return;
            }
            return;
        }
        timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
        this.livePlaybackSpeedControl.setLiveConfiguration((MediaItem.LiveConfiguration) Util.castNonNull(this.window.liveConfiguration));
        if (j2 != C.TIME_UNSET) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(getLiveOffsetUs(timeline, mediaPeriodId.periodUid, j2));
            return;
        }
        if (!Util.areEqual(!timeline2.isEmpty() ? timeline2.getWindow(timeline2.getPeriodByUid(mediaPeriodId2.periodUid, this.period).windowIndex, this.window).uid : null, this.window.uid)) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(C.TIME_UNSET);
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float f2) {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlaybackSpeed(f2);
                }
            }
        }
    }

    private synchronized void waitUninterruptibly(Supplier<Boolean> supplier, long j2) {
        long elapsedRealtime = this.clock.elapsedRealtime() + j2;
        boolean z2 = false;
        while (!supplier.get().booleanValue() && j2 > 0) {
            try {
                this.clock.onThreadBlocked();
                wait(j2);
            } catch (InterruptedException unused) {
                z2 = true;
            }
            j2 = elapsedRealtime - this.clock.elapsedRealtime();
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
    }

    public void addMediaSources(int i3, List<MediaSourceList.MediaSourceHolder> list, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(18, i3, 0, new MediaSourceListUpdateMessage(list, shuffleOrder, -1, C.TIME_UNSET)).sendToTarget();
    }

    public void experimentalSetForegroundModeTimeoutMs(long j2) {
        this.setForegroundModeTimeoutMs = j2;
    }

    public void experimentalSetOffloadSchedulingEnabled(boolean z2) {
        this.handler.obtainMessage(24, z2 ? 1 : 0, 0).sendToTarget();
    }

    public Looper getPlaybackLooper() {
        return this.playbackLooper;
    }

    public boolean handleMessage(Message message) {
        MediaPeriodHolder readingPeriod;
        int i3;
        int i4 = 1000;
        try {
            switch (message.what) {
                case 0:
                    prepareInternal();
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0, message.arg2, true, 1);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message.obj);
                    break;
                case 6:
                    stopInternal(false, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 10:
                    reselectTracksInternal();
                    break;
                case 11:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 12:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 13:
                    setForegroundModeInternal(message.arg1 != 0, (AtomicBoolean) message.obj);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) message.obj, false);
                    break;
                case 17:
                    setMediaItemsInternal((MediaSourceListUpdateMessage) message.obj);
                    break;
                case 18:
                    addMediaItemsInternal((MediaSourceListUpdateMessage) message.obj, message.arg1);
                    break;
                case 19:
                    moveMediaItemsInternal((MoveMediaItemsMessage) message.obj);
                    break;
                case 20:
                    removeMediaItemsInternal(message.arg1, message.arg2, (ShuffleOrder) message.obj);
                    break;
                case 21:
                    setShuffleOrderInternal((ShuffleOrder) message.obj);
                    break;
                case 22:
                    mediaSourceListUpdateRequestedInternal();
                    break;
                case 23:
                    setPauseAtEndOfWindowInternal(message.arg1 != 0);
                    break;
                case 24:
                    setOffloadSchedulingEnabledInternal(message.arg1 == 1);
                    break;
                case 25:
                    attemptRendererErrorRecovery();
                    break;
                default:
                    return false;
            }
        } catch (ExoPlaybackException e3) {
            e = e3;
            if (e.type == 1 && (readingPeriod = this.queue.getReadingPeriod()) != null) {
                e = e.copyWithMediaPeriodId(readingPeriod.info.id);
            }
            if (!e.isRecoverable || this.pendingRecoverableRendererError != null) {
                ExoPlaybackException exoPlaybackException = this.pendingRecoverableRendererError;
                if (exoPlaybackException != null) {
                    exoPlaybackException.addSuppressed(e);
                    e = this.pendingRecoverableRendererError;
                }
                Log.e(TAG, "Playback error", e);
                stopInternal(true, false);
                this.playbackInfo = this.playbackInfo.copyWithPlaybackError(e);
            } else {
                Log.w(TAG, "Recoverable renderer error", e);
                this.pendingRecoverableRendererError = e;
                HandlerWrapper handlerWrapper = this.handler;
                handlerWrapper.sendMessageAtFrontOfQueue(handlerWrapper.obtainMessage(25, e));
            }
        } catch (DrmSession.DrmSessionException e4) {
            handleIoException(e4, e4.errorCode);
        } catch (ParserException e5) {
            int i5 = e5.dataType;
            if (i5 == 1) {
                i3 = e5.contentIsMalformed ? 3001 : 3003;
            } else {
                if (i5 == 4) {
                    i3 = e5.contentIsMalformed ? 3002 : 3004;
                }
                handleIoException(e5, i4);
            }
            i4 = i3;
            handleIoException(e5, i4);
        } catch (DataSourceException e6) {
            handleIoException(e6, e6.reason);
        } catch (BehindLiveWindowException e7) {
            handleIoException(e7, 1002);
        } catch (IOException e8) {
            handleIoException(e8, 2000);
        } catch (RuntimeException e9) {
            if ((e9 instanceof IllegalStateException) || (e9 instanceof IllegalArgumentException)) {
                i4 = 1004;
            }
            ExoPlaybackException createForUnexpected = ExoPlaybackException.createForUnexpected(e9, i4);
            Log.e(TAG, "Playback error", createForUnexpected);
            stopInternal(true, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(createForUnexpected);
        }
        maybeNotifyPlaybackInfoChanged();
        return true;
    }

    public void moveMediaSources(int i3, int i4, int i5, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(19, new MoveMediaItemsMessage(i3, i4, i5, shuffleOrder)).sendToTarget();
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(16, playbackParameters).sendToTarget();
    }

    public void onPlaylistUpdateRequested() {
        this.handler.sendEmptyMessage(22);
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(8, mediaPeriod).sendToTarget();
    }

    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    public void prepare() {
        this.handler.obtainMessage(0).sendToTarget();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean release() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.released     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0025
            android.os.HandlerThread r0 = r3.internalPlaybackThread     // Catch:{ all -> 0x0023 }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x000e
            goto L_0x0025
        L_0x000e:
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper r0 = r3.handler     // Catch:{ all -> 0x0023 }
            r1 = 7
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x0023 }
            com.appsamurai.storyly.exoplayer2.core.x r0 = new com.appsamurai.storyly.exoplayer2.core.x     // Catch:{ all -> 0x0023 }
            r1 = 0
            r0.<init>(r3, r1)     // Catch:{ all -> 0x0023 }
            long r1 = r3.releaseTimeoutMs     // Catch:{ all -> 0x0023 }
            r3.waitUninterruptibly(r0, r1)     // Catch:{ all -> 0x0023 }
            boolean r0 = r3.released     // Catch:{ all -> 0x0023 }
            monitor-exit(r3)
            return r0
        L_0x0023:
            r0 = move-exception
            goto L_0x0028
        L_0x0025:
            monitor-exit(r3)
            r3 = 1
            return r3
        L_0x0028:
            monitor-exit(r3)     // Catch:{ all -> 0x0023 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.release():boolean");
    }

    public void removeMediaSources(int i3, int i4, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(20, i3, i4, shuffleOrder).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i3, long j2) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i3, j2)).sendToTarget();
    }

    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (!this.released) {
            if (this.internalPlaybackThread.isAlive()) {
                this.handler.obtainMessage(14, playerMessage).sendToTarget();
                return;
            }
        }
        Log.w(TAG, "Ignoring messages sent after release.");
        playerMessage.markAsProcessed(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean setForegroundMode(boolean r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.released     // Catch:{ all -> 0x001f }
            r1 = 1
            if (r0 != 0) goto L_0x0040
            android.os.HandlerThread r0 = r3.internalPlaybackThread     // Catch:{ all -> 0x001f }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x000f
            goto L_0x0040
        L_0x000f:
            r0 = 13
            r2 = 0
            if (r4 == 0) goto L_0x0021
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper r4 = r3.handler     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper$Message r4 = r4.obtainMessage(r0, r1, r2)     // Catch:{ all -> 0x001f }
            r4.sendToTarget()     // Catch:{ all -> 0x001f }
            monitor-exit(r3)
            return r1
        L_0x001f:
            r4 = move-exception
            goto L_0x0042
        L_0x0021:
            java.util.concurrent.atomic.AtomicBoolean r4 = new java.util.concurrent.atomic.AtomicBoolean     // Catch:{ all -> 0x001f }
            r4.<init>()     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper r1 = r3.handler     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper$Message r0 = r1.obtainMessage(r0, r2, r2, r4)     // Catch:{ all -> 0x001f }
            r0.sendToTarget()     // Catch:{ all -> 0x001f }
            com.appsamurai.storyly.exoplayer2.core.x r0 = new com.appsamurai.storyly.exoplayer2.core.x     // Catch:{ all -> 0x001f }
            r1 = 1
            r0.<init>(r4, r1)     // Catch:{ all -> 0x001f }
            long r1 = r3.setForegroundModeTimeoutMs     // Catch:{ all -> 0x001f }
            r3.waitUninterruptibly(r0, r1)     // Catch:{ all -> 0x001f }
            boolean r4 = r4.get()     // Catch:{ all -> 0x001f }
            monitor-exit(r3)
            return r4
        L_0x0040:
            monitor-exit(r3)
            return r1
        L_0x0042:
            monitor-exit(r3)     // Catch:{ all -> 0x001f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal.setForegroundMode(boolean):boolean");
    }

    public void setMediaSources(List<MediaSourceList.MediaSourceHolder> list, int i3, long j2, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(17, new MediaSourceListUpdateMessage(list, shuffleOrder, i3, j2)).sendToTarget();
    }

    public void setPauseAtEndOfWindow(boolean z2) {
        this.handler.obtainMessage(23, z2 ? 1 : 0, 0).sendToTarget();
    }

    public void setPlayWhenReady(boolean z2, int i3) {
        this.handler.obtainMessage(1, z2 ? 1 : 0, i3).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setRepeatMode(int i3) {
        this.handler.obtainMessage(11, i3, 0).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters2) {
        this.handler.obtainMessage(5, seekParameters2).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z2) {
        this.handler.obtainMessage(12, z2 ? 1 : 0, 0).sendToTarget();
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(21, shuffleOrder).sendToTarget();
    }

    public void stop() {
        this.handler.obtainMessage(6).sendToTarget();
    }

    private void enableRenderers(boolean[] zArr) throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
        for (int i3 = 0; i3 < this.renderers.length; i3++) {
            if (!trackSelectorResult.isRendererEnabled(i3) && this.renderersToReset.remove(this.renderers[i3])) {
                this.renderers[i3].reset();
            }
        }
        for (int i4 = 0; i4 < this.renderers.length; i4++) {
            if (trackSelectorResult.isRendererEnabled(i4)) {
                enableRenderer(i4, zArr[i4]);
            }
        }
        readingPeriod.allRenderersInCorrectState = true;
    }

    private long getTotalBufferedDurationUs(long j2) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            return 0;
        }
        return Math.max(0, j2 - loadingPeriod.toPeriodTime(this.rendererPositionUs));
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, float f2, boolean z2, boolean z3) throws ExoPlaybackException {
        if (z2) {
            if (z3) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(1);
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
        }
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setPlaybackSpeed(f2, playbackParameters.speed);
            }
        }
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j2, boolean z2, boolean z3) throws ExoPlaybackException {
        stopRenderers();
        this.isRebuffering = false;
        if (z3 || this.playbackInfo.playbackState == 3) {
            setState(2);
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolder = playingPeriod;
        while (mediaPeriodHolder != null && !mediaPeriodId.equals(mediaPeriodHolder.info.id)) {
            mediaPeriodHolder = mediaPeriodHolder.getNext();
        }
        if (z2 || playingPeriod != mediaPeriodHolder || (mediaPeriodHolder != null && mediaPeriodHolder.toRendererTime(j2) < 0)) {
            for (Renderer disableRenderer : this.renderers) {
                disableRenderer(disableRenderer);
            }
            if (mediaPeriodHolder != null) {
                while (this.queue.getPlayingPeriod() != mediaPeriodHolder) {
                    this.queue.advancePlayingPeriod();
                }
                this.queue.removeAfter(mediaPeriodHolder);
                mediaPeriodHolder.setRendererOffset(MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US);
                enableRenderers();
            }
        }
        if (mediaPeriodHolder != null) {
            this.queue.removeAfter(mediaPeriodHolder);
            if (!mediaPeriodHolder.prepared) {
                mediaPeriodHolder.info = mediaPeriodHolder.info.copyWithStartPositionUs(j2);
            } else if (mediaPeriodHolder.hasEnabledTracks) {
                j2 = mediaPeriodHolder.mediaPeriod.seekToUs(j2);
                mediaPeriodHolder.mediaPeriod.discardBuffer(j2 - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }
            resetRendererPosition(j2);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(j2);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j2;
    }
}
