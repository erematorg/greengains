package com.appsamurai.storyly.exoplayer2.core;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioTrack;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.BasePlayer;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.DeviceInfo;
import com.appsamurai.storyly.exoplayer2.common.ExoPlayerLibraryInfo;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.IllegalSeekPositionException;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.MediaMetadata;
import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.audio.AuxEffectInfo;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionParameters;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable;
import com.appsamurai.storyly.exoplayer2.common.util.FlagSet;
import com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper;
import com.appsamurai.storyly.exoplayer2.common.util.ListenerSet;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.AudioBecomingNoisyManager;
import com.appsamurai.storyly.exoplayer2.core.AudioFocusManager;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal;
import com.appsamurai.storyly.exoplayer2.core.MediaSourceList;
import com.appsamurai.storyly.exoplayer2.core.PlayerMessage;
import com.appsamurai.storyly.exoplayer2.core.StreamVolumeManager;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.analytics.MediaMetricsListener;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;
import com.appsamurai.storyly.exoplayer2.core.metadata.MetadataOutput;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.text.TextOutput;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectionArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import com.appsamurai.storyly.exoplayer2.core.video.VideoDecoderOutputBufferRenderer;
import com.appsamurai.storyly.exoplayer2.core.video.VideoFrameMetadataListener;
import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;
import com.appsamurai.storyly.exoplayer2.core.video.spherical.CameraMotionListener;
import com.appsamurai.storyly.exoplayer2.core.video.spherical.SphericalGLSurfaceView;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

final class ExoPlayerImpl extends BasePlayer implements ExoPlayer, ExoPlayer.AudioComponent, ExoPlayer.VideoComponent, ExoPlayer.TextComponent, ExoPlayer.DeviceComponent {
    private static final String TAG = "ExoPlayerImpl";
    /* access modifiers changed from: private */
    public final AnalyticsCollector analyticsCollector;
    private final Context applicationContext;
    private final Looper applicationLooper;
    private AudioAttributes audioAttributes;
    private final AudioBecomingNoisyManager audioBecomingNoisyManager;
    /* access modifiers changed from: private */
    @Nullable
    public DecoderCounters audioDecoderCounters;
    private final AudioFocusManager audioFocusManager;
    /* access modifiers changed from: private */
    @Nullable
    public Format audioFormat;
    private final CopyOnWriteArraySet<ExoPlayer.AudioOffloadListener> audioOffloadListeners;
    private int audioSessionId;
    private Player.Commands availableCommands;
    private final BandwidthMeter bandwidthMeter;
    @Nullable
    private CameraMotionListener cameraMotionListener;
    private final Clock clock;
    private final ComponentListener componentListener;
    private final ConditionVariable constructorFinished;
    /* access modifiers changed from: private */
    public CueGroup currentCueGroup;
    private final long detachSurfaceTimeoutMs;
    /* access modifiers changed from: private */
    public DeviceInfo deviceInfo;
    final TrackSelectorResult emptyTrackSelectorResult;
    private boolean foregroundMode;
    private final FrameMetadataListener frameMetadataListener;
    private boolean hasNotifiedFullWrongThreadWarning;
    private final ExoPlayerImplInternal internalPlayer;
    private boolean isPriorityTaskManagerRegistered;
    @Nullable
    private AudioTrack keepSessionIdAudioTrack;
    /* access modifiers changed from: private */
    public final ListenerSet<Player.Listener> listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    /* access modifiers changed from: private */
    public MediaMetadata mediaMetadata;
    private final MediaSource.Factory mediaSourceFactory;
    private final List<MediaSourceHolderSnapshot> mediaSourceHolderSnapshots;
    @Nullable
    private Surface ownedSurface;
    private boolean pauseAtEndOfMediaItems;
    private boolean pendingDiscontinuity;
    private int pendingDiscontinuityReason;
    private int pendingOperationAcks;
    private int pendingPlayWhenReadyChangeReason;
    private final Timeline.Period period;
    final Player.Commands permanentAvailableCommands;
    private PlaybackInfo playbackInfo;
    private final HandlerWrapper playbackInfoUpdateHandler;
    private final ExoPlayerImplInternal.PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private boolean playerReleased;
    private MediaMetadata playlistMetadata;
    @Nullable
    private PriorityTaskManager priorityTaskManager;
    private final Renderer[] renderers;
    private int repeatMode;
    private final long seekBackIncrementMs;
    private final long seekForwardIncrementMs;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private ShuffleOrder shuffleOrder;
    /* access modifiers changed from: private */
    public boolean skipSilenceEnabled;
    @Nullable
    private SphericalGLSurfaceView sphericalGLSurfaceView;
    /* access modifiers changed from: private */
    public MediaMetadata staticAndDynamicMediaMetadata;
    /* access modifiers changed from: private */
    public final StreamVolumeManager streamVolumeManager;
    private int surfaceHeight;
    @Nullable
    private SurfaceHolder surfaceHolder;
    /* access modifiers changed from: private */
    public boolean surfaceHolderSurfaceIsVideoOutput;
    private int surfaceWidth;
    @Nullable
    private TextureView textureView;
    private boolean throwsWhenUsingWrongThread;
    private final TrackSelector trackSelector;
    private final boolean useLazyPreparation;
    private int videoChangeFrameRateStrategy;
    /* access modifiers changed from: private */
    @Nullable
    public DecoderCounters videoDecoderCounters;
    /* access modifiers changed from: private */
    @Nullable
    public Format videoFormat;
    @Nullable
    private VideoFrameMetadataListener videoFrameMetadataListener;
    /* access modifiers changed from: private */
    @Nullable
    public Object videoOutput;
    private int videoScalingMode;
    /* access modifiers changed from: private */
    public VideoSize videoSize;
    private float volume;
    private final WakeLockManager wakeLockManager;
    private final WifiLockManager wifiLockManager;
    private final Player wrappingPlayer;

    @RequiresApi(31)
    public static final class Api31 {
        private Api31() {
        }

        @DoNotInline
        public static PlayerId registerMediaMetricsListener(Context context, ExoPlayerImpl exoPlayerImpl, boolean z2) {
            MediaMetricsListener create = MediaMetricsListener.create(context);
            if (create == null) {
                Log.w(ExoPlayerImpl.TAG, "MediaMetricsService unavailable.");
                return new PlayerId(LogSessionId.LOG_SESSION_ID_NONE);
            }
            if (z2) {
                exoPlayerImpl.addAnalyticsListener(create);
            }
            return new PlayerId(create.getLogSessionId());
        }
    }

    public final class ComponentListener implements VideoRendererEventListener, AudioRendererEventListener, TextOutput, MetadataOutput, SurfaceHolder.Callback, TextureView.SurfaceTextureListener, SphericalGLSurfaceView.VideoSurfaceListener, AudioFocusManager.PlayerControl, AudioBecomingNoisyManager.EventListener, StreamVolumeManager.Listener, ExoPlayer.AudioOffloadListener {
        private ComponentListener() {
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onMetadata$4(Player.Listener listener) {
            listener.onMediaMetadataChanged(ExoPlayerImpl.this.mediaMetadata);
        }

        public void executePlayerCommand(int i3) {
            boolean playWhenReady = ExoPlayerImpl.this.getPlayWhenReady();
            ExoPlayerImpl.this.updatePlayWhenReady(playWhenReady, i3, ExoPlayerImpl.getPlayWhenReadyChangeReason(playWhenReady, i3));
        }

        public void onAudioBecomingNoisy() {
            ExoPlayerImpl.this.updatePlayWhenReady(false, -1, 3);
        }

        public void onAudioCodecError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onAudioCodecError(exc);
        }

        public void onAudioDecoderInitialized(String str, long j2, long j3) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDecoderInitialized(str, j2, j3);
        }

        public void onAudioDecoderReleased(String str) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDecoderReleased(str);
        }

        public void onAudioDisabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDisabled(decoderCounters);
            Format unused = ExoPlayerImpl.this.audioFormat = null;
            DecoderCounters unused2 = ExoPlayerImpl.this.audioDecoderCounters = null;
        }

        public void onAudioEnabled(DecoderCounters decoderCounters) {
            DecoderCounters unused = ExoPlayerImpl.this.audioDecoderCounters = decoderCounters;
            ExoPlayerImpl.this.analyticsCollector.onAudioEnabled(decoderCounters);
        }

        public void onAudioInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
            Format unused = ExoPlayerImpl.this.audioFormat = format;
            ExoPlayerImpl.this.analyticsCollector.onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void onAudioPositionAdvancing(long j2) {
            ExoPlayerImpl.this.analyticsCollector.onAudioPositionAdvancing(j2);
        }

        public void onAudioSinkError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onAudioSinkError(exc);
        }

        public void onAudioUnderrun(int i3, long j2, long j3) {
            ExoPlayerImpl.this.analyticsCollector.onAudioUnderrun(i3, j2, j3);
        }

        public void onCues(List<Cue> list) {
            ExoPlayerImpl.this.listeners.sendEvent(27, new m(list, 5));
        }

        public void onDroppedFrames(int i3, long j2) {
            ExoPlayerImpl.this.analyticsCollector.onDroppedFrames(i3, j2);
        }

        public void onExperimentalSleepingForOffloadChanged(boolean z2) {
            ExoPlayerImpl.this.updateWakeAndWifiLock();
        }

        public void onMetadata(Metadata metadata) {
            ExoPlayerImpl exoPlayerImpl = ExoPlayerImpl.this;
            MediaMetadata unused = exoPlayerImpl.staticAndDynamicMediaMetadata = exoPlayerImpl.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(metadata).build();
            MediaMetadata access$1400 = ExoPlayerImpl.this.buildUpdatedMediaMetadata();
            if (!access$1400.equals(ExoPlayerImpl.this.mediaMetadata)) {
                MediaMetadata unused2 = ExoPlayerImpl.this.mediaMetadata = access$1400;
                ExoPlayerImpl.this.listeners.queueEvent(14, new m(this, 7));
            }
            ExoPlayerImpl.this.listeners.queueEvent(28, new m(metadata, 8));
            ExoPlayerImpl.this.listeners.flushEvents();
        }

        public void onRenderedFirstFrame(Object obj, long j2) {
            ExoPlayerImpl.this.analyticsCollector.onRenderedFirstFrame(obj, j2);
            if (ExoPlayerImpl.this.videoOutput == obj) {
                ExoPlayerImpl.this.listeners.sendEvent(26, new r(4));
            }
        }

        public void onSkipSilenceEnabledChanged(boolean z2) {
            if (ExoPlayerImpl.this.skipSilenceEnabled != z2) {
                boolean unused = ExoPlayerImpl.this.skipSilenceEnabled = z2;
                ExoPlayerImpl.this.listeners.sendEvent(23, new n(z2, 2));
            }
        }

        public void onStreamTypeChanged(int i3) {
            DeviceInfo access$2400 = ExoPlayerImpl.createDeviceInfo(ExoPlayerImpl.this.streamVolumeManager);
            if (!access$2400.equals(ExoPlayerImpl.this.deviceInfo)) {
                DeviceInfo unused = ExoPlayerImpl.this.deviceInfo = access$2400;
                ExoPlayerImpl.this.listeners.sendEvent(29, new m(access$2400, 6));
            }
        }

        public void onStreamVolumeChanged(int i3, boolean z2) {
            ExoPlayerImpl.this.listeners.sendEvent(30, new w(i3, z2));
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i3, int i4) {
            ExoPlayerImpl.this.setSurfaceTextureInternal(surfaceTexture);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i3, i4);
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
            return true;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i3, int i4) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i3, i4);
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        public void onVideoCodecError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onVideoCodecError(exc);
        }

        public void onVideoDecoderInitialized(String str, long j2, long j3) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDecoderInitialized(str, j2, j3);
        }

        public void onVideoDecoderReleased(String str) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDecoderReleased(str);
        }

        public void onVideoDisabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDisabled(decoderCounters);
            Format unused = ExoPlayerImpl.this.videoFormat = null;
            DecoderCounters unused2 = ExoPlayerImpl.this.videoDecoderCounters = null;
        }

        public void onVideoEnabled(DecoderCounters decoderCounters) {
            DecoderCounters unused = ExoPlayerImpl.this.videoDecoderCounters = decoderCounters;
            ExoPlayerImpl.this.analyticsCollector.onVideoEnabled(decoderCounters);
        }

        public void onVideoFrameProcessingOffset(long j2, int i3) {
            ExoPlayerImpl.this.analyticsCollector.onVideoFrameProcessingOffset(j2, i3);
        }

        public void onVideoInputFormatChanged(Format format, @Nullable DecoderReuseEvaluation decoderReuseEvaluation) {
            Format unused = ExoPlayerImpl.this.videoFormat = format;
            ExoPlayerImpl.this.analyticsCollector.onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            VideoSize unused = ExoPlayerImpl.this.videoSize = videoSize;
            ExoPlayerImpl.this.listeners.sendEvent(25, new m(videoSize, 3));
        }

        public void onVideoSurfaceCreated(Surface surface) {
            ExoPlayerImpl.this.setVideoOutputInternal(surface);
        }

        public void onVideoSurfaceDestroyed(Surface surface) {
            ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
        }

        public void setVolumeMultiplier(float f2) {
            ExoPlayerImpl.this.sendVolumeToRenderers();
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i3, int i4, int i5) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i4, i5);
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal(surfaceHolder.getSurface());
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
            }
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
        }

        public void onCues(CueGroup cueGroup) {
            CueGroup unused = ExoPlayerImpl.this.currentCueGroup = cueGroup;
            ExoPlayerImpl.this.listeners.sendEvent(27, new m(cueGroup, 4));
        }
    }

    public static final class FrameMetadataListener implements VideoFrameMetadataListener, CameraMotionListener, PlayerMessage.Target {
        public static final int MSG_SET_CAMERA_MOTION_LISTENER = 8;
        public static final int MSG_SET_SPHERICAL_SURFACE_VIEW = 10000;
        public static final int MSG_SET_VIDEO_FRAME_METADATA_LISTENER = 7;
        @Nullable
        private CameraMotionListener cameraMotionListener;
        @Nullable
        private CameraMotionListener internalCameraMotionListener;
        @Nullable
        private VideoFrameMetadataListener internalVideoFrameMetadataListener;
        @Nullable
        private VideoFrameMetadataListener videoFrameMetadataListener;

        private FrameMetadataListener() {
        }

        public void handleMessage(int i3, @Nullable Object obj) {
            if (i3 == 7) {
                this.videoFrameMetadataListener = (VideoFrameMetadataListener) obj;
            } else if (i3 == 8) {
                this.cameraMotionListener = (CameraMotionListener) obj;
            } else if (i3 == 10000) {
                SphericalGLSurfaceView sphericalGLSurfaceView = (SphericalGLSurfaceView) obj;
                if (sphericalGLSurfaceView == null) {
                    this.internalVideoFrameMetadataListener = null;
                    this.internalCameraMotionListener = null;
                    return;
                }
                this.internalVideoFrameMetadataListener = sphericalGLSurfaceView.getVideoFrameMetadataListener();
                this.internalCameraMotionListener = sphericalGLSurfaceView.getCameraMotionListener();
            }
        }

        public void onCameraMotion(long j2, float[] fArr) {
            CameraMotionListener cameraMotionListener2 = this.internalCameraMotionListener;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.onCameraMotion(j2, fArr);
            }
            CameraMotionListener cameraMotionListener3 = this.cameraMotionListener;
            if (cameraMotionListener3 != null) {
                cameraMotionListener3.onCameraMotion(j2, fArr);
            }
        }

        public void onCameraMotionReset() {
            CameraMotionListener cameraMotionListener2 = this.internalCameraMotionListener;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.onCameraMotionReset();
            }
            CameraMotionListener cameraMotionListener3 = this.cameraMotionListener;
            if (cameraMotionListener3 != null) {
                cameraMotionListener3.onCameraMotionReset();
            }
        }

        public void onVideoFrameAboutToBeRendered(long j2, long j3, Format format, @Nullable MediaFormat mediaFormat) {
            VideoFrameMetadataListener videoFrameMetadataListener2 = this.internalVideoFrameMetadataListener;
            if (videoFrameMetadataListener2 != null) {
                videoFrameMetadataListener2.onVideoFrameAboutToBeRendered(j2, j3, format, mediaFormat);
            }
            VideoFrameMetadataListener videoFrameMetadataListener3 = this.videoFrameMetadataListener;
            if (videoFrameMetadataListener3 != null) {
                videoFrameMetadataListener3.onVideoFrameAboutToBeRendered(j2, j3, format, mediaFormat);
            }
        }
    }

    public static final class MediaSourceHolderSnapshot implements MediaSourceInfoHolder {
        /* access modifiers changed from: private */
        public Timeline timeline;
        private final Object uid;

        public MediaSourceHolderSnapshot(Object obj, Timeline timeline2) {
            this.uid = obj;
            this.timeline = timeline2;
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public Object getUid() {
            return this.uid;
        }
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.exoplayer");
    }

    /* JADX WARNING: type inference failed for: r39v0, types: [com.appsamurai.storyly.exoplayer2.common.Player] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.annotation.SuppressLint({"HandlerLeak"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExoPlayerImpl(com.appsamurai.storyly.exoplayer2.core.ExoPlayer.Builder r38, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.common.Player r39) {
        /*
            r37 = this;
            r1 = r37
            r0 = r38
            r6 = 2
            java.lang.String r7 = " [ExoPlayerLib/2.18.0] ["
            java.lang.String r8 = "Init "
            r37.<init>()
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r9 = new com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable
            r9.<init>()
            r1.constructorFinished = r9
            java.lang.String r10 = "ExoPlayerImpl"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x017b }
            r11.<init>(r8)     // Catch:{ all -> 0x017b }
            int r8 = java.lang.System.identityHashCode(r37)     // Catch:{ all -> 0x017b }
            java.lang.String r8 = java.lang.Integer.toHexString(r8)     // Catch:{ all -> 0x017b }
            r11.append(r8)     // Catch:{ all -> 0x017b }
            r11.append(r7)     // Catch:{ all -> 0x017b }
            java.lang.String r7 = com.appsamurai.storyly.exoplayer2.common.util.Util.DEVICE_DEBUG_INFO     // Catch:{ all -> 0x017b }
            r11.append(r7)     // Catch:{ all -> 0x017b }
            java.lang.String r7 = "]"
            r11.append(r7)     // Catch:{ all -> 0x017b }
            java.lang.String r7 = r11.toString()     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.util.Log.i(r10, r7)     // Catch:{ all -> 0x017b }
            android.content.Context r7 = r0.context     // Catch:{ all -> 0x017b }
            android.content.Context r7 = r7.getApplicationContext()     // Catch:{ all -> 0x017b }
            r1.applicationContext = r7     // Catch:{ all -> 0x017b }
            com.google.common.base.Function<com.appsamurai.storyly.exoplayer2.common.util.Clock, com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector> r8 = r0.analyticsCollectorFunction     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.util.Clock r10 = r0.clock     // Catch:{ all -> 0x017b }
            java.lang.Object r8 = r8.apply(r10)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector r8 = (com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector) r8     // Catch:{ all -> 0x017b }
            r1.analyticsCollector = r8     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager r10 = r0.priorityTaskManager     // Catch:{ all -> 0x017b }
            r1.priorityTaskManager = r10     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes r10 = r0.audioAttributes     // Catch:{ all -> 0x017b }
            r1.audioAttributes = r10     // Catch:{ all -> 0x017b }
            int r10 = r0.videoScalingMode     // Catch:{ all -> 0x017b }
            r1.videoScalingMode = r10     // Catch:{ all -> 0x017b }
            int r10 = r0.videoChangeFrameRateStrategy     // Catch:{ all -> 0x017b }
            r1.videoChangeFrameRateStrategy = r10     // Catch:{ all -> 0x017b }
            boolean r10 = r0.skipSilenceEnabled     // Catch:{ all -> 0x017b }
            r1.skipSilenceEnabled = r10     // Catch:{ all -> 0x017b }
            long r10 = r0.detachSurfaceTimeoutMs     // Catch:{ all -> 0x017b }
            r1.detachSurfaceTimeoutMs = r10     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImpl$ComponentListener r11 = new com.appsamurai.storyly.exoplayer2.core.ExoPlayerImpl$ComponentListener     // Catch:{ all -> 0x017b }
            r10 = 0
            r11.<init>()     // Catch:{ all -> 0x017b }
            r1.componentListener = r11     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImpl$FrameMetadataListener r15 = new com.appsamurai.storyly.exoplayer2.core.ExoPlayerImpl$FrameMetadataListener     // Catch:{ all -> 0x017b }
            r15.<init>()     // Catch:{ all -> 0x017b }
            r1.frameMetadataListener = r15     // Catch:{ all -> 0x017b }
            android.os.Handler r14 = new android.os.Handler     // Catch:{ all -> 0x017b }
            android.os.Looper r12 = r0.looper     // Catch:{ all -> 0x017b }
            r14.<init>(r12)     // Catch:{ all -> 0x017b }
            com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.RenderersFactory> r12 = r0.renderersFactorySupplier     // Catch:{ all -> 0x017b }
            java.lang.Object r12 = r12.get()     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.RenderersFactory r12 = (com.appsamurai.storyly.exoplayer2.core.RenderersFactory) r12     // Catch:{ all -> 0x017b }
            r13 = r14
            r28 = r14
            r14 = r11
            r29 = r15
            r15 = r11
            r16 = r11
            r17 = r11
            com.appsamurai.storyly.exoplayer2.core.Renderer[] r12 = r12.createRenderers(r13, r14, r15, r16, r17)     // Catch:{ all -> 0x017b }
            r1.renderers = r12     // Catch:{ all -> 0x017b }
            int r13 = r12.length     // Catch:{ all -> 0x017b }
            r15 = 0
            if (r13 <= 0) goto L_0x009a
            r13 = 1
            goto L_0x009b
        L_0x009a:
            r13 = r15
        L_0x009b:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r13)     // Catch:{ all -> 0x017b }
            com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector> r13 = r0.trackSelectorSupplier     // Catch:{ all -> 0x017b }
            java.lang.Object r13 = r13.get()     // Catch:{ all -> 0x017b }
            r14 = r13
            com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector r14 = (com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector) r14     // Catch:{ all -> 0x017b }
            r1.trackSelector = r14     // Catch:{ all -> 0x017b }
            com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.source.MediaSource$Factory> r13 = r0.mediaSourceFactorySupplier     // Catch:{ all -> 0x017b }
            java.lang.Object r13 = r13.get()     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.source.MediaSource$Factory r13 = (com.appsamurai.storyly.exoplayer2.core.source.MediaSource.Factory) r13     // Catch:{ all -> 0x017b }
            r1.mediaSourceFactory = r13     // Catch:{ all -> 0x017b }
            com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter> r13 = r0.bandwidthMeterSupplier     // Catch:{ all -> 0x017b }
            java.lang.Object r13 = r13.get()     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter r13 = (com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter) r13     // Catch:{ all -> 0x017b }
            r1.bandwidthMeter = r13     // Catch:{ all -> 0x017b }
            boolean r3 = r0.useLazyPreparation     // Catch:{ all -> 0x017b }
            r1.useLazyPreparation = r3     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.SeekParameters r3 = r0.seekParameters     // Catch:{ all -> 0x017b }
            r1.seekParameters = r3     // Catch:{ all -> 0x017b }
            long r2 = r0.seekBackIncrementMs     // Catch:{ all -> 0x017b }
            r1.seekBackIncrementMs = r2     // Catch:{ all -> 0x017b }
            long r2 = r0.seekForwardIncrementMs     // Catch:{ all -> 0x017b }
            r1.seekForwardIncrementMs = r2     // Catch:{ all -> 0x017b }
            boolean r2 = r0.pauseAtEndOfMediaItems     // Catch:{ all -> 0x017b }
            r1.pauseAtEndOfMediaItems = r2     // Catch:{ all -> 0x017b }
            android.os.Looper r2 = r0.looper     // Catch:{ all -> 0x017b }
            r1.applicationLooper = r2     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.util.Clock r3 = r0.clock     // Catch:{ all -> 0x017b }
            r1.clock = r3     // Catch:{ all -> 0x017b }
            if (r39 != 0) goto L_0x00dd
            r5 = r1
            goto L_0x00df
        L_0x00dd:
            r5 = r39
        L_0x00df:
            r1.wrappingPlayer = r5     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.util.ListenerSet r4 = new com.appsamurai.storyly.exoplayer2.common.util.ListenerSet     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.q r10 = new com.appsamurai.storyly.exoplayer2.core.q     // Catch:{ all -> 0x017b }
            r10.<init>(r1, r6)     // Catch:{ all -> 0x017b }
            r4.<init>(r2, r3, r10)     // Catch:{ all -> 0x017b }
            r1.listeners = r4     // Catch:{ all -> 0x017b }
            java.util.concurrent.CopyOnWriteArraySet r4 = new java.util.concurrent.CopyOnWriteArraySet     // Catch:{ all -> 0x017b }
            r4.<init>()     // Catch:{ all -> 0x017b }
            r1.audioOffloadListeners = r4     // Catch:{ all -> 0x017b }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x017b }
            r4.<init>()     // Catch:{ all -> 0x017b }
            r1.mediaSourceHolderSnapshots = r4     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder$DefaultShuffleOrder r4 = new com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder$DefaultShuffleOrder     // Catch:{ all -> 0x017b }
            r4.<init>(r15)     // Catch:{ all -> 0x017b }
            r1.shuffleOrder = r4     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult r4 = new com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult     // Catch:{ all -> 0x017b }
            int r10 = r12.length     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.RendererConfiguration[] r10 = new com.appsamurai.storyly.exoplayer2.core.RendererConfiguration[r10]     // Catch:{ all -> 0x017b }
            int r15 = r12.length     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[] r15 = new com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[r15]     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Tracks r6 = com.appsamurai.storyly.exoplayer2.common.Tracks.EMPTY     // Catch:{ all -> 0x017b }
            r19 = r11
            r11 = 0
            r4.<init>(r10, r15, r6, r11)     // Catch:{ all -> 0x017b }
            r1.emptyTrackSelectorResult = r4     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Timeline$Period r6 = new com.appsamurai.storyly.exoplayer2.common.Timeline$Period     // Catch:{ all -> 0x017b }
            r6.<init>()     // Catch:{ all -> 0x017b }
            r1.period = r6     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder r6 = new com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder     // Catch:{ all -> 0x017b }
            r6.<init>()     // Catch:{ all -> 0x017b }
            r10 = 21
            int[] r11 = new int[r10]     // Catch:{ all -> 0x017b }
            r11 = {1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 20, 30, 21, 22, 23, 24, 25, 26, 27, 28, 31} // fill-array     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder r6 = r6.addAll((int[]) r11)     // Catch:{ all -> 0x017b }
            boolean r10 = r14.isSetParametersSupported()     // Catch:{ all -> 0x017b }
            r11 = 29
            com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder r6 = r6.addIf(r11, r10)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Player$Commands r6 = r6.build()     // Catch:{ all -> 0x017b }
            r1.permanentAvailableCommands = r6     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder r10 = new com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder     // Catch:{ all -> 0x017b }
            r10.<init>()     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder r6 = r10.addAll((com.appsamurai.storyly.exoplayer2.common.Player.Commands) r6)     // Catch:{ all -> 0x017b }
            r15 = 4
            com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder r6 = r6.add(r15)     // Catch:{ all -> 0x017b }
            r11 = 10
            com.appsamurai.storyly.exoplayer2.common.Player$Commands$Builder r6 = r6.add(r11)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.Player$Commands r6 = r6.build()     // Catch:{ all -> 0x017b }
            r1.availableCommands = r6     // Catch:{ all -> 0x017b }
            r6 = 0
            com.appsamurai.storyly.exoplayer2.common.util.HandlerWrapper r10 = r3.createHandler(r2, r6)     // Catch:{ all -> 0x017b }
            r1.playbackInfoUpdateHandler = r10     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.q r10 = new com.appsamurai.storyly.exoplayer2.core.q     // Catch:{ all -> 0x017b }
            r6 = 3
            r10.<init>(r1, r6)     // Catch:{ all -> 0x017b }
            r1.playbackInfoUpdateListener = r10     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.PlaybackInfo r6 = com.appsamurai.storyly.exoplayer2.core.PlaybackInfo.createDummy(r4)     // Catch:{ all -> 0x017b }
            r1.playbackInfo = r6     // Catch:{ all -> 0x017b }
            r8.setPlayer(r5, r2)     // Catch:{ all -> 0x017b }
            int r5 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT     // Catch:{ all -> 0x017b }
            r6 = 31
            if (r5 >= r6) goto L_0x017e
            com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId r6 = new com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId     // Catch:{ all -> 0x017b }
            r6.<init>()     // Catch:{ all -> 0x017b }
        L_0x0178:
            r27 = r6
            goto L_0x0185
        L_0x017b:
            r0 = move-exception
            goto L_0x02db
        L_0x017e:
            boolean r6 = r0.usePlatformDiagnostics     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId r6 = com.appsamurai.storyly.exoplayer2.core.ExoPlayerImpl.Api31.registerMediaMetricsListener(r7, r1, r6)     // Catch:{ all -> 0x017b }
            goto L_0x0178
        L_0x0185:
            com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal r6 = new com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal     // Catch:{ all -> 0x017b }
            com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.LoadControl> r11 = r0.loadControlSupplier     // Catch:{ all -> 0x017b }
            java.lang.Object r11 = r11.get()     // Catch:{ all -> 0x017b }
            r16 = r11
            com.appsamurai.storyly.exoplayer2.core.LoadControl r16 = (com.appsamurai.storyly.exoplayer2.core.LoadControl) r16     // Catch:{ all -> 0x017b }
            int r11 = r1.repeatMode     // Catch:{ all -> 0x017b }
            r30 = r9
            boolean r9 = r1.shuffleModeEnabled     // Catch:{ all -> 0x017b }
            r31 = r7
            com.appsamurai.storyly.exoplayer2.core.SeekParameters r7 = r1.seekParameters     // Catch:{ all -> 0x017b }
            r32 = r5
            com.appsamurai.storyly.exoplayer2.core.LivePlaybackSpeedControl r5 = r0.livePlaybackSpeedControl     // Catch:{ all -> 0x017b }
            r33 = r2
            r25 = r3
            long r2 = r0.releaseTimeoutMs     // Catch:{ all -> 0x017b }
            boolean r0 = r1.pauseAtEndOfMediaItems     // Catch:{ all -> 0x017b }
            r26 = r10
            r34 = 0
            r10 = r6
            r17 = r11
            r35 = r19
            r11 = r12
            r12 = r14
            r39 = r13
            r13 = r4
            r4 = r14
            r14 = r16
            r36 = r4
            r4 = 0
            r15 = r39
            r16 = r17
            r17 = r9
            r18 = r8
            r19 = r7
            r20 = r5
            r21 = r2
            r23 = r0
            r24 = r33
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r23, r24, r25, r26, r27)     // Catch:{ all -> 0x017b }
            r1.internalPlayer = r6     // Catch:{ all -> 0x017b }
            r0 = 1065353216(0x3f800000, float:1.0)
            r1.volume = r0     // Catch:{ all -> 0x017b }
            r1.repeatMode = r4     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.MediaMetadata r0 = com.appsamurai.storyly.exoplayer2.common.MediaMetadata.EMPTY     // Catch:{ all -> 0x017b }
            r1.mediaMetadata = r0     // Catch:{ all -> 0x017b }
            r1.playlistMetadata = r0     // Catch:{ all -> 0x017b }
            r1.staticAndDynamicMediaMetadata = r0     // Catch:{ all -> 0x017b }
            r0 = -1
            r1.maskingWindowIndex = r0     // Catch:{ all -> 0x017b }
            r0 = r32
            r2 = 21
            if (r0 >= r2) goto L_0x01f0
            int r0 = r1.initializeKeepSessionIdAudioTrack(r4)     // Catch:{ all -> 0x017b }
            r1.audioSessionId = r0     // Catch:{ all -> 0x017b }
            goto L_0x01f6
        L_0x01f0:
            int r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.generateAudioSessionIdV21(r31)     // Catch:{ all -> 0x017b }
            r1.audioSessionId = r0     // Catch:{ all -> 0x017b }
        L_0x01f6:
            com.appsamurai.storyly.exoplayer2.common.text.CueGroup r0 = com.appsamurai.storyly.exoplayer2.common.text.CueGroup.EMPTY     // Catch:{ all -> 0x017b }
            r1.currentCueGroup = r0     // Catch:{ all -> 0x017b }
            r0 = 1
            r1.throwsWhenUsingWrongThread = r0     // Catch:{ all -> 0x017b }
            r1.addListener(r8)     // Catch:{ all -> 0x017b }
            android.os.Handler r0 = new android.os.Handler     // Catch:{ all -> 0x017b }
            r2 = r33
            r0.<init>(r2)     // Catch:{ all -> 0x017b }
            r13 = r39
            r13.addEventListener(r0, r8)     // Catch:{ all -> 0x017b }
            r0 = r35
            r1.addAudioOffloadListener(r0)     // Catch:{ all -> 0x017b }
            r2 = r38
            long r7 = r2.foregroundModeTimeoutMs     // Catch:{ all -> 0x017b }
            r9 = 0
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 <= 0) goto L_0x021e
            r6.experimentalSetForegroundModeTimeoutMs(r7)     // Catch:{ all -> 0x017b }
        L_0x021e:
            com.appsamurai.storyly.exoplayer2.core.AudioBecomingNoisyManager r3 = new com.appsamurai.storyly.exoplayer2.core.AudioBecomingNoisyManager     // Catch:{ all -> 0x017b }
            android.content.Context r5 = r2.context     // Catch:{ all -> 0x017b }
            r6 = r28
            r3.<init>(r5, r6, r0)     // Catch:{ all -> 0x017b }
            r1.audioBecomingNoisyManager = r3     // Catch:{ all -> 0x017b }
            boolean r5 = r2.handleAudioBecomingNoisy     // Catch:{ all -> 0x017b }
            r3.setEnabled(r5)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.AudioFocusManager r3 = new com.appsamurai.storyly.exoplayer2.core.AudioFocusManager     // Catch:{ all -> 0x017b }
            android.content.Context r5 = r2.context     // Catch:{ all -> 0x017b }
            r3.<init>(r5, r6, r0)     // Catch:{ all -> 0x017b }
            r1.audioFocusManager = r3     // Catch:{ all -> 0x017b }
            boolean r5 = r2.handleAudioFocus     // Catch:{ all -> 0x017b }
            if (r5 == 0) goto L_0x023e
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes r10 = r1.audioAttributes     // Catch:{ all -> 0x017b }
            goto L_0x0240
        L_0x023e:
            r10 = r34
        L_0x0240:
            r3.setAudioAttributes(r10)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.StreamVolumeManager r3 = new com.appsamurai.storyly.exoplayer2.core.StreamVolumeManager     // Catch:{ all -> 0x017b }
            android.content.Context r5 = r2.context     // Catch:{ all -> 0x017b }
            r3.<init>(r5, r6, r0)     // Catch:{ all -> 0x017b }
            r1.streamVolumeManager = r3     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes r0 = r1.audioAttributes     // Catch:{ all -> 0x017b }
            int r0 = r0.usage     // Catch:{ all -> 0x017b }
            int r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.getStreamTypeForAudioUsage(r0)     // Catch:{ all -> 0x017b }
            r3.setStreamType(r0)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.WakeLockManager r0 = new com.appsamurai.storyly.exoplayer2.core.WakeLockManager     // Catch:{ all -> 0x017b }
            android.content.Context r5 = r2.context     // Catch:{ all -> 0x017b }
            r0.<init>(r5)     // Catch:{ all -> 0x017b }
            r1.wakeLockManager = r0     // Catch:{ all -> 0x017b }
            int r5 = r2.wakeMode     // Catch:{ all -> 0x017b }
            if (r5 == 0) goto L_0x0266
            r15 = 1
            goto L_0x0267
        L_0x0266:
            r15 = r4
        L_0x0267:
            r0.setEnabled(r15)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.core.WifiLockManager r0 = new com.appsamurai.storyly.exoplayer2.core.WifiLockManager     // Catch:{ all -> 0x017b }
            android.content.Context r5 = r2.context     // Catch:{ all -> 0x017b }
            r0.<init>(r5)     // Catch:{ all -> 0x017b }
            r1.wifiLockManager = r0     // Catch:{ all -> 0x017b }
            int r2 = r2.wakeMode     // Catch:{ all -> 0x017b }
            r5 = 2
            if (r2 != r5) goto L_0x027a
            r15 = 1
            goto L_0x027b
        L_0x027a:
            r15 = r4
        L_0x027b:
            r0.setEnabled(r15)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.DeviceInfo r0 = createDeviceInfo(r3)     // Catch:{ all -> 0x017b }
            r1.deviceInfo = r0     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.video.VideoSize r0 = com.appsamurai.storyly.exoplayer2.common.video.VideoSize.UNKNOWN     // Catch:{ all -> 0x017b }
            r1.videoSize = r0     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes r0 = r1.audioAttributes     // Catch:{ all -> 0x017b }
            r13 = r36
            r13.setAudioAttributes(r0)     // Catch:{ all -> 0x017b }
            int r0 = r1.audioSessionId     // Catch:{ all -> 0x017b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x017b }
            r2 = 10
            r3 = 1
            r1.sendRendererMessage(r3, r2, r0)     // Catch:{ all -> 0x017b }
            int r0 = r1.audioSessionId     // Catch:{ all -> 0x017b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x017b }
            r4 = 2
            r1.sendRendererMessage(r4, r2, r0)     // Catch:{ all -> 0x017b }
            com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes r0 = r1.audioAttributes     // Catch:{ all -> 0x017b }
            r2 = 3
            r1.sendRendererMessage(r3, r2, r0)     // Catch:{ all -> 0x017b }
            int r0 = r1.videoScalingMode     // Catch:{ all -> 0x017b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x017b }
            r2 = 4
            r1.sendRendererMessage(r4, r2, r0)     // Catch:{ all -> 0x017b }
            int r0 = r1.videoChangeFrameRateStrategy     // Catch:{ all -> 0x017b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x017b }
            r2 = 5
            r1.sendRendererMessage(r4, r2, r0)     // Catch:{ all -> 0x017b }
            boolean r0 = r1.skipSilenceEnabled     // Catch:{ all -> 0x017b }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x017b }
            r2 = 9
            r3 = 1
            r1.sendRendererMessage(r3, r2, r0)     // Catch:{ all -> 0x017b }
            r0 = 7
            r2 = r29
            r1.sendRendererMessage(r4, r0, r2)     // Catch:{ all -> 0x017b }
            r0 = 6
            r3 = 8
            r1.sendRendererMessage(r0, r3, r2)     // Catch:{ all -> 0x017b }
            r30.open()
            return
        L_0x02db:
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r1 = r1.constructorFinished
            r1.open()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.ExoPlayerImpl.<init>(com.appsamurai.storyly.exoplayer2.core.ExoPlayer$Builder, com.appsamurai.storyly.exoplayer2.common.Player):void");
    }

    private List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders(int i3, List<MediaSource> list) {
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < list.size(); i4++) {
            MediaSourceList.MediaSourceHolder mediaSourceHolder = new MediaSourceList.MediaSourceHolder(list.get(i4), this.useLazyPreparation);
            arrayList.add(mediaSourceHolder);
            this.mediaSourceHolderSnapshots.add(i4 + i3, new MediaSourceHolderSnapshot(mediaSourceHolder.uid, mediaSourceHolder.mediaSource.getTimeline()));
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(i3, arrayList.size());
        return arrayList;
    }

    /* access modifiers changed from: private */
    public MediaMetadata buildUpdatedMediaMetadata() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return this.staticAndDynamicMediaMetadata;
        }
        return this.staticAndDynamicMediaMetadata.buildUpon().populate(currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem.mediaMetadata).build();
    }

    /* access modifiers changed from: private */
    public static DeviceInfo createDeviceInfo(StreamVolumeManager streamVolumeManager2) {
        return new DeviceInfo(0, streamVolumeManager2.getMinVolume(), streamVolumeManager2.getMaxVolume());
    }

    private Timeline createMaskingTimeline() {
        return new PlaylistTimeline(this.mediaSourceHolderSnapshots, this.shuffleOrder);
    }

    private List<MediaSource> createMediaSources(List<MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            arrayList.add(this.mediaSourceFactory.createMediaSource(list.get(i3)));
        }
        return arrayList;
    }

    private PlayerMessage createMessageInternal(PlayerMessage.Target target) {
        int currentWindowIndexInternal = getCurrentWindowIndexInternal();
        ExoPlayerImplInternal exoPlayerImplInternal = this.internalPlayer;
        Timeline timeline = this.playbackInfo.timeline;
        if (currentWindowIndexInternal == -1) {
            currentWindowIndexInternal = 0;
        }
        return new PlayerMessage(exoPlayerImplInternal, target, timeline, currentWindowIndexInternal, this.clock, exoPlayerImplInternal.getPlaybackLooper());
    }

    private Pair<Boolean, Integer> evaluateMediaItemTransitionReason(PlaybackInfo playbackInfo2, PlaybackInfo playbackInfo3, boolean z2, int i3, boolean z3) {
        Timeline timeline = playbackInfo3.timeline;
        Timeline timeline2 = playbackInfo2.timeline;
        if (timeline2.isEmpty() && timeline.isEmpty()) {
            return new Pair<>(Boolean.FALSE, -1);
        }
        int i4 = 3;
        if (timeline2.isEmpty() != timeline.isEmpty()) {
            return new Pair<>(Boolean.TRUE, 3);
        }
        if (timeline.getWindow(timeline.getPeriodByUid(playbackInfo3.periodId.periodUid, this.period).windowIndex, this.window).uid.equals(timeline2.getWindow(timeline2.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period).windowIndex, this.window).uid)) {
            return (!z2 || i3 != 0 || playbackInfo3.periodId.windowSequenceNumber >= playbackInfo2.periodId.windowSequenceNumber) ? new Pair<>(Boolean.FALSE, -1) : new Pair<>(Boolean.TRUE, 0);
        }
        if (z2 && i3 == 0) {
            i4 = 1;
        } else if (z2 && i3 == 1) {
            i4 = 2;
        } else if (!z3) {
            throw new IllegalStateException();
        }
        return new Pair<>(Boolean.TRUE, Integer.valueOf(i4));
    }

    private long getCurrentPositionUsInternal(PlaybackInfo playbackInfo2) {
        return playbackInfo2.timeline.isEmpty() ? Util.msToUs(this.maskingWindowPositionMs) : playbackInfo2.periodId.isAd() ? playbackInfo2.positionUs : periodPositionUsToWindowPositionUs(playbackInfo2.timeline, playbackInfo2.periodId, playbackInfo2.positionUs);
    }

    private int getCurrentWindowIndexInternal() {
        if (this.playbackInfo.timeline.isEmpty()) {
            return this.maskingWindowIndex;
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        return playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period).windowIndex;
    }

    @Nullable
    private Pair<Object, Long> getPeriodPositionUsAfterTimelineChanged(Timeline timeline, Timeline timeline2) {
        long contentPosition = getContentPosition();
        int i3 = -1;
        if (timeline.isEmpty() || timeline2.isEmpty()) {
            boolean z2 = !timeline.isEmpty() && timeline2.isEmpty();
            if (!z2) {
                i3 = getCurrentWindowIndexInternal();
            }
            if (z2) {
                contentPosition = -9223372036854775807L;
            }
            return maskWindowPositionMsOrGetPeriodPositionUs(timeline2, i3, contentPosition);
        }
        Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(this.window, this.period, getCurrentMediaItemIndex(), Util.msToUs(contentPosition));
        Object obj = ((Pair) Util.castNonNull(periodPositionUs)).first;
        if (timeline2.getIndexOfPeriod(obj) != -1) {
            return periodPositionUs;
        }
        Object resolveSubsequentPeriod = ExoPlayerImplInternal.resolveSubsequentPeriod(this.window, this.period, this.repeatMode, this.shuffleModeEnabled, obj, timeline, timeline2);
        if (resolveSubsequentPeriod == null) {
            return maskWindowPositionMsOrGetPeriodPositionUs(timeline2, -1, C.TIME_UNSET);
        }
        timeline2.getPeriodByUid(resolveSubsequentPeriod, this.period);
        int i4 = this.period.windowIndex;
        return maskWindowPositionMsOrGetPeriodPositionUs(timeline2, i4, timeline2.getWindow(i4, this.window).getDefaultPositionMs());
    }

    /* access modifiers changed from: private */
    public static int getPlayWhenReadyChangeReason(boolean z2, int i3) {
        return (!z2 || i3 == 1) ? 1 : 2;
    }

    private Player.PositionInfo getPositionInfo(long j2) {
        int i3;
        Object obj;
        MediaItem mediaItem;
        Object obj2;
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        if (!this.playbackInfo.timeline.isEmpty()) {
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            Object obj3 = playbackInfo2.periodId.periodUid;
            playbackInfo2.timeline.getPeriodByUid(obj3, this.period);
            i3 = this.playbackInfo.timeline.getIndexOfPeriod(obj3);
            obj = obj3;
            obj2 = this.playbackInfo.timeline.getWindow(currentMediaItemIndex, this.window).uid;
            mediaItem = this.window.mediaItem;
        } else {
            mediaItem = null;
            obj = null;
            i3 = -1;
            obj2 = null;
        }
        long usToMs = Util.usToMs(j2);
        long usToMs2 = this.playbackInfo.periodId.isAd() ? Util.usToMs(getRequestedContentPositionUs(this.playbackInfo)) : usToMs;
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        return new Player.PositionInfo(obj2, currentMediaItemIndex, mediaItem, obj, i3, usToMs, usToMs2, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
    }

    private Player.PositionInfo getPreviousPositionInfo(int i3, PlaybackInfo playbackInfo2, int i4) {
        int i5;
        Object obj;
        MediaItem mediaItem;
        int i6;
        Object obj2;
        long j2;
        long j3;
        PlaybackInfo playbackInfo3 = playbackInfo2;
        Timeline.Period period2 = new Timeline.Period();
        if (!playbackInfo3.timeline.isEmpty()) {
            Object obj3 = playbackInfo3.periodId.periodUid;
            playbackInfo3.timeline.getPeriodByUid(obj3, period2);
            int i7 = period2.windowIndex;
            int indexOfPeriod = playbackInfo3.timeline.getIndexOfPeriod(obj3);
            Object obj4 = playbackInfo3.timeline.getWindow(i7, this.window).uid;
            mediaItem = this.window.mediaItem;
            obj = obj3;
            i5 = indexOfPeriod;
            obj2 = obj4;
            i6 = i7;
        } else {
            i6 = i4;
            obj2 = null;
            mediaItem = null;
            obj = null;
            i5 = -1;
        }
        if (i3 == 0) {
            if (playbackInfo3.periodId.isAd()) {
                MediaSource.MediaPeriodId mediaPeriodId = playbackInfo3.periodId;
                j3 = period2.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
                j2 = getRequestedContentPositionUs(playbackInfo2);
                long usToMs = Util.usToMs(j3);
                long usToMs2 = Util.usToMs(j2);
                MediaSource.MediaPeriodId mediaPeriodId2 = playbackInfo3.periodId;
                return new Player.PositionInfo(obj2, i6, mediaItem, obj, i5, usToMs, usToMs2, mediaPeriodId2.adGroupIndex, mediaPeriodId2.adIndexInAdGroup);
            }
            j3 = playbackInfo3.periodId.nextAdGroupIndex != -1 ? getRequestedContentPositionUs(this.playbackInfo) : period2.positionInWindowUs + period2.durationUs;
        } else if (playbackInfo3.periodId.isAd()) {
            j3 = playbackInfo3.positionUs;
            j2 = getRequestedContentPositionUs(playbackInfo2);
            long usToMs3 = Util.usToMs(j3);
            long usToMs22 = Util.usToMs(j2);
            MediaSource.MediaPeriodId mediaPeriodId22 = playbackInfo3.periodId;
            return new Player.PositionInfo(obj2, i6, mediaItem, obj, i5, usToMs3, usToMs22, mediaPeriodId22.adGroupIndex, mediaPeriodId22.adIndexInAdGroup);
        } else {
            j3 = period2.positionInWindowUs + playbackInfo3.positionUs;
        }
        j2 = j3;
        long usToMs32 = Util.usToMs(j3);
        long usToMs222 = Util.usToMs(j2);
        MediaSource.MediaPeriodId mediaPeriodId222 = playbackInfo3.periodId;
        return new Player.PositionInfo(obj2, i6, mediaItem, obj, i5, usToMs32, usToMs222, mediaPeriodId222.adGroupIndex, mediaPeriodId222.adIndexInAdGroup);
    }

    private static long getRequestedContentPositionUs(PlaybackInfo playbackInfo2) {
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period2 = new Timeline.Period();
        playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, period2);
        return playbackInfo2.requestedContentPositionUs == C.TIME_UNSET ? playbackInfo2.timeline.getWindow(period2.windowIndex, window).getDefaultPositionUs() : period2.getPositionInWindowUs() + playbackInfo2.requestedContentPositionUs;
    }

    /* access modifiers changed from: private */
    /* renamed from: handlePlaybackInfo */
    public void lambda$new$1(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        long j2;
        boolean z2;
        long j3;
        int i3 = this.pendingOperationAcks - playbackInfoUpdate.operationAcks;
        this.pendingOperationAcks = i3;
        boolean z3 = true;
        if (playbackInfoUpdate.positionDiscontinuity) {
            this.pendingDiscontinuityReason = playbackInfoUpdate.discontinuityReason;
            this.pendingDiscontinuity = true;
        }
        if (playbackInfoUpdate.hasPlayWhenReadyChangeReason) {
            this.pendingPlayWhenReadyChangeReason = playbackInfoUpdate.playWhenReadyChangeReason;
        }
        if (i3 == 0) {
            Timeline timeline = playbackInfoUpdate.playbackInfo.timeline;
            if (!this.playbackInfo.timeline.isEmpty() && timeline.isEmpty()) {
                this.maskingWindowIndex = -1;
                this.maskingWindowPositionMs = 0;
                this.maskingPeriodIndex = 0;
            }
            if (!timeline.isEmpty()) {
                List<Timeline> childTimelines = ((PlaylistTimeline) timeline).getChildTimelines();
                Assertions.checkState(childTimelines.size() == this.mediaSourceHolderSnapshots.size());
                for (int i4 = 0; i4 < childTimelines.size(); i4++) {
                    Timeline unused = this.mediaSourceHolderSnapshots.get(i4).timeline = childTimelines.get(i4);
                }
            }
            if (this.pendingDiscontinuity) {
                if (playbackInfoUpdate.playbackInfo.periodId.equals(this.playbackInfo.periodId) && playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs == this.playbackInfo.positionUs) {
                    z3 = false;
                }
                if (z3) {
                    if (timeline.isEmpty() || playbackInfoUpdate.playbackInfo.periodId.isAd()) {
                        j3 = playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs;
                    } else {
                        PlaybackInfo playbackInfo2 = playbackInfoUpdate.playbackInfo;
                        j3 = periodPositionUsToWindowPositionUs(timeline, playbackInfo2.periodId, playbackInfo2.discontinuityStartPositionUs);
                    }
                    j2 = j3;
                } else {
                    j2 = -9223372036854775807L;
                }
                z2 = z3;
            } else {
                j2 = -9223372036854775807L;
                z2 = false;
            }
            this.pendingDiscontinuity = false;
            updatePlaybackInfo(playbackInfoUpdate.playbackInfo, 1, this.pendingPlayWhenReadyChangeReason, false, z2, this.pendingDiscontinuityReason, j2, -1);
        }
    }

    private int initializeKeepSessionIdAudioTrack(int i3) {
        AudioTrack audioTrack = this.keepSessionIdAudioTrack;
        if (!(audioTrack == null || audioTrack.getAudioSessionId() == i3)) {
            this.keepSessionIdAudioTrack.release();
            this.keepSessionIdAudioTrack = null;
        }
        if (this.keepSessionIdAudioTrack == null) {
            this.keepSessionIdAudioTrack = new AudioTrack(3, SerializerCache.DEFAULT_MAX_CACHED, 4, 2, 2, 0, i3);
        }
        return this.keepSessionIdAudioTrack.getAudioSessionId();
    }

    private static boolean isPlaying(PlaybackInfo playbackInfo2) {
        return playbackInfo2.playbackState == 3 && playbackInfo2.playWhenReady && playbackInfo2.playbackSuppressionReason == 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(this.wrappingPlayer, new Player.Events(flagSet));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        this.playbackInfoUpdateHandler.post(new s(this, playbackInfoUpdate, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaylistMetadata$7(Player.Listener listener) {
        listener.onPlaylistMetadataChanged(this.playlistMetadata);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAvailableCommands$26(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.availableCommands);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$13(int i3, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(i3);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, i3);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$19(PlaybackInfo playbackInfo2, Player.Listener listener) {
        listener.onLoadingChanged(playbackInfo2.isLoading);
        listener.onIsLoadingChanged(playbackInfo2.isLoading);
    }

    private PlaybackInfo maskTimelineAndPosition(PlaybackInfo playbackInfo2, Timeline timeline, @Nullable Pair<Object, Long> pair) {
        int i3;
        Timeline timeline2 = timeline;
        Pair<Object, Long> pair2 = pair;
        Assertions.checkArgument(timeline.isEmpty() || pair2 != null);
        Timeline timeline3 = playbackInfo2.timeline;
        PlaybackInfo copyWithTimeline = playbackInfo2.copyWithTimeline(timeline);
        if (timeline.isEmpty()) {
            MediaSource.MediaPeriodId dummyPeriodForEmptyTimeline = PlaybackInfo.getDummyPeriodForEmptyTimeline();
            long msToUs = Util.msToUs(this.maskingWindowPositionMs);
            PlaybackInfo copyWithLoadingMediaPeriodId = copyWithTimeline.copyWithNewPosition(dummyPeriodForEmptyTimeline, msToUs, msToUs, msToUs, 0, TrackGroupArray.EMPTY, this.emptyTrackSelectorResult, ImmutableList.of()).copyWithLoadingMediaPeriodId(dummyPeriodForEmptyTimeline);
            copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
            return copyWithLoadingMediaPeriodId;
        }
        Object obj = copyWithTimeline.periodId.periodUid;
        boolean equals = obj.equals(((Pair) Util.castNonNull(pair)).first);
        MediaSource.MediaPeriodId mediaPeriodId = !equals ? new MediaSource.MediaPeriodId(pair2.first) : copyWithTimeline.periodId;
        long longValue = ((Long) pair2.second).longValue();
        long msToUs2 = Util.msToUs(getContentPosition());
        if (!timeline3.isEmpty()) {
            msToUs2 -= timeline3.getPeriodByUid(obj, this.period).getPositionInWindowUs();
        }
        if (!equals || longValue < msToUs2) {
            MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
            Assertions.checkState(!mediaPeriodId2.isAd());
            PlaybackInfo copyWithLoadingMediaPeriodId2 = copyWithTimeline.copyWithNewPosition(mediaPeriodId2, longValue, longValue, longValue, 0, !equals ? TrackGroupArray.EMPTY : copyWithTimeline.trackGroups, !equals ? this.emptyTrackSelectorResult : copyWithTimeline.trackSelectorResult, !equals ? ImmutableList.of() : copyWithTimeline.staticMetadata).copyWithLoadingMediaPeriodId(mediaPeriodId2);
            copyWithLoadingMediaPeriodId2.bufferedPositionUs = longValue;
            return copyWithLoadingMediaPeriodId2;
        } else if (i3 == 0) {
            int indexOfPeriod = timeline2.getIndexOfPeriod(copyWithTimeline.loadingMediaPeriodId.periodUid);
            if (indexOfPeriod != -1 && timeline2.getPeriod(indexOfPeriod, this.period).windowIndex == timeline2.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex) {
                return copyWithTimeline;
            }
            timeline2.getPeriodByUid(mediaPeriodId.periodUid, this.period);
            long adDurationUs = mediaPeriodId.isAd() ? this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) : this.period.durationUs;
            PlaybackInfo copyWithLoadingMediaPeriodId3 = copyWithTimeline.copyWithNewPosition(mediaPeriodId, copyWithTimeline.positionUs, copyWithTimeline.positionUs, copyWithTimeline.discontinuityStartPositionUs, adDurationUs - copyWithTimeline.positionUs, copyWithTimeline.trackGroups, copyWithTimeline.trackSelectorResult, copyWithTimeline.staticMetadata).copyWithLoadingMediaPeriodId(mediaPeriodId);
            copyWithLoadingMediaPeriodId3.bufferedPositionUs = adDurationUs;
            return copyWithLoadingMediaPeriodId3;
        } else {
            MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId;
            Assertions.checkState(!mediaPeriodId3.isAd());
            long max = Math.max(0, copyWithTimeline.totalBufferedDurationUs - (longValue - msToUs2));
            long j2 = copyWithTimeline.bufferedPositionUs;
            if (copyWithTimeline.loadingMediaPeriodId.equals(copyWithTimeline.periodId)) {
                j2 = longValue + max;
            }
            PlaybackInfo copyWithNewPosition = copyWithTimeline.copyWithNewPosition(mediaPeriodId3, longValue, longValue, longValue, max, copyWithTimeline.trackGroups, copyWithTimeline.trackSelectorResult, copyWithTimeline.staticMetadata);
            copyWithNewPosition.bufferedPositionUs = j2;
            return copyWithNewPosition;
        }
    }

    @Nullable
    private Pair<Object, Long> maskWindowPositionMsOrGetPeriodPositionUs(Timeline timeline, int i3, long j2) {
        if (timeline.isEmpty()) {
            this.maskingWindowIndex = i3;
            if (j2 == C.TIME_UNSET) {
                j2 = 0;
            }
            this.maskingWindowPositionMs = j2;
            this.maskingPeriodIndex = 0;
            return null;
        }
        if (i3 == -1 || i3 >= timeline.getWindowCount()) {
            i3 = timeline.getFirstWindowIndex(this.shuffleModeEnabled);
            j2 = timeline.getWindow(i3, this.window).getDefaultPositionMs();
        }
        return timeline.getPeriodPositionUs(this.window, this.period, i3, Util.msToUs(j2));
    }

    /* access modifiers changed from: private */
    public void maybeNotifySurfaceSizeChanged(int i3, int i4) {
        if (i3 != this.surfaceWidth || i4 != this.surfaceHeight) {
            this.surfaceWidth = i3;
            this.surfaceHeight = i4;
            this.listeners.sendEvent(24, new p(i3, i4));
        }
    }

    private long periodPositionUsToWindowPositionUs(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2) {
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return this.period.getPositionInWindowUs() + j2;
    }

    private PlaybackInfo removeMediaItemsInternal(int i3, int i4) {
        Assertions.checkArgument(i3 >= 0 && i4 >= i3 && i4 <= this.mediaSourceHolderSnapshots.size());
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        Timeline currentTimeline = getCurrentTimeline();
        int size = this.mediaSourceHolderSnapshots.size();
        this.pendingOperationAcks++;
        removeMediaSourceHolders(i3, i4);
        Timeline createMaskingTimeline = createMaskingTimeline();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, createMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(currentTimeline, createMaskingTimeline));
        int i5 = maskTimelineAndPosition.playbackState;
        if (i5 != 1 && i5 != 4 && i3 < i4 && i4 == size && currentMediaItemIndex >= maskTimelineAndPosition.timeline.getWindowCount()) {
            maskTimelineAndPosition = maskTimelineAndPosition.copyWithPlaybackState(4);
        }
        this.internalPlayer.removeMediaSources(i3, i4, this.shuffleOrder);
        return maskTimelineAndPosition;
    }

    private void removeMediaSourceHolders(int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            this.mediaSourceHolderSnapshots.remove(i5);
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i3, i4);
    }

    private void removeSurfaceCallbacks() {
        if (this.sphericalGLSurfaceView != null) {
            createMessageInternal(this.frameMetadataListener).setType(10000).setPayload((Object) null).send();
            this.sphericalGLSurfaceView.removeVideoSurfaceListener(this.componentListener);
            this.sphericalGLSurfaceView = null;
        }
        TextureView textureView2 = this.textureView;
        if (textureView2 != null) {
            if (textureView2.getSurfaceTextureListener() != this.componentListener) {
                Log.w(TAG, "SurfaceTextureListener already unset or replaced.");
            } else {
                this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
            }
            this.textureView = null;
        }
        SurfaceHolder surfaceHolder2 = this.surfaceHolder;
        if (surfaceHolder2 != null) {
            surfaceHolder2.removeCallback(this.componentListener);
            this.surfaceHolder = null;
        }
    }

    private void sendRendererMessage(int i3, int i4, @Nullable Object obj) {
        for (Renderer renderer : this.renderers) {
            if (renderer.getTrackType() == i3) {
                createMessageInternal(renderer).setType(i4).setPayload(obj).send();
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendVolumeToRenderers() {
        sendRendererMessage(1, 2, Float.valueOf(this.volume * this.audioFocusManager.getVolumeMultiplier()));
    }

    private void setMediaSourcesInternal(List<MediaSource> list, int i3, long j2, boolean z2) {
        int i4 = i3;
        int currentWindowIndexInternal = getCurrentWindowIndexInternal();
        long currentPosition = getCurrentPosition();
        this.pendingOperationAcks++;
        if (!this.mediaSourceHolderSnapshots.isEmpty()) {
            removeMediaSourceHolders(0, this.mediaSourceHolderSnapshots.size());
        }
        List<MediaSource> list2 = list;
        List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders = addMediaSourceHolders(0, list);
        Timeline createMaskingTimeline = createMaskingTimeline();
        if (createMaskingTimeline.isEmpty() || i4 < createMaskingTimeline.getWindowCount()) {
            long j3 = j2;
            if (z2) {
                i4 = createMaskingTimeline.getFirstWindowIndex(this.shuffleModeEnabled);
                j3 = -9223372036854775807L;
            } else if (i4 == -1) {
                i4 = currentWindowIndexInternal;
                j3 = currentPosition;
            }
            PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, createMaskingTimeline, maskWindowPositionMsOrGetPeriodPositionUs(createMaskingTimeline, i4, j3));
            int i5 = maskTimelineAndPosition.playbackState;
            if (!(i4 == -1 || i5 == 1)) {
                i5 = (createMaskingTimeline.isEmpty() || i4 >= createMaskingTimeline.getWindowCount()) ? 4 : 2;
            }
            PlaybackInfo copyWithPlaybackState = maskTimelineAndPosition.copyWithPlaybackState(i5);
            this.internalPlayer.setMediaSources(addMediaSourceHolders, i4, Util.msToUs(j3), this.shuffleOrder);
            updatePlaybackInfo(copyWithPlaybackState, 0, 1, false, !this.playbackInfo.periodId.periodUid.equals(copyWithPlaybackState.periodId.periodUid) && !this.playbackInfo.timeline.isEmpty(), 4, getCurrentPositionUsInternal(copyWithPlaybackState), -1);
            return;
        }
        throw new IllegalSeekPositionException(createMaskingTimeline, i4, j2);
    }

    private void setNonVideoOutputSurfaceHolderInternal(SurfaceHolder surfaceHolder2) {
        this.surfaceHolderSurfaceIsVideoOutput = false;
        this.surfaceHolder = surfaceHolder2;
        surfaceHolder2.addCallback(this.componentListener);
        Surface surface = this.surfaceHolder.getSurface();
        if (surface == null || !surface.isValid()) {
            maybeNotifySurfaceSizeChanged(0, 0);
            return;
        }
        Rect surfaceFrame = this.surfaceHolder.getSurfaceFrame();
        maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
    }

    /* access modifiers changed from: private */
    public void setSurfaceTextureInternal(SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);
        setVideoOutputInternal(surface);
        this.ownedSurface = surface;
    }

    /* access modifiers changed from: private */
    public void setVideoOutputInternal(@Nullable Object obj) {
        boolean z2;
        ArrayList arrayList = new ArrayList();
        Renderer[] rendererArr = this.renderers;
        int length = rendererArr.length;
        int i3 = 0;
        while (true) {
            z2 = true;
            if (i3 >= length) {
                break;
            }
            Renderer renderer = rendererArr[i3];
            if (renderer.getTrackType() == 2) {
                arrayList.add(createMessageInternal(renderer).setType(1).setPayload(obj).send());
            }
            i3++;
        }
        Object obj2 = this.videoOutput;
        if (obj2 == null || obj2 == obj) {
            z2 = false;
        } else {
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((PlayerMessage) it.next()).blockUntilDelivered(this.detachSurfaceTimeoutMs);
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (TimeoutException unused2) {
            }
            z2 = false;
            Object obj3 = this.videoOutput;
            Surface surface = this.ownedSurface;
            if (obj3 == surface) {
                surface.release();
                this.ownedSurface = null;
            }
        }
        this.videoOutput = obj;
        if (z2) {
            stopInternal(false, ExoPlaybackException.createForUnexpected(new ExoTimeoutException(3), 1003));
        }
    }

    private void stopInternal(boolean z2, @Nullable ExoPlaybackException exoPlaybackException) {
        PlaybackInfo playbackInfo2;
        if (z2) {
            playbackInfo2 = removeMediaItemsInternal(0, this.mediaSourceHolderSnapshots.size()).copyWithPlaybackError((ExoPlaybackException) null);
        } else {
            PlaybackInfo playbackInfo3 = this.playbackInfo;
            playbackInfo2 = playbackInfo3.copyWithLoadingMediaPeriodId(playbackInfo3.periodId);
            playbackInfo2.bufferedPositionUs = playbackInfo2.positionUs;
            playbackInfo2.totalBufferedDurationUs = 0;
        }
        PlaybackInfo copyWithPlaybackState = playbackInfo2.copyWithPlaybackState(1);
        if (exoPlaybackException != null) {
            copyWithPlaybackState = copyWithPlaybackState.copyWithPlaybackError(exoPlaybackException);
        }
        PlaybackInfo playbackInfo4 = copyWithPlaybackState;
        this.pendingOperationAcks++;
        this.internalPlayer.stop();
        updatePlaybackInfo(playbackInfo4, 0, 1, false, playbackInfo4.timeline.isEmpty() && !this.playbackInfo.timeline.isEmpty(), 4, getCurrentPositionUsInternal(playbackInfo4), -1);
    }

    private void updateAvailableCommands() {
        Player.Commands commands = this.availableCommands;
        Player.Commands availableCommands2 = Util.getAvailableCommands(this.wrappingPlayer, this.permanentAvailableCommands);
        this.availableCommands = availableCommands2;
        if (!availableCommands2.equals(commands)) {
            this.listeners.queueEvent(13, new q(this, 1));
        }
    }

    /* access modifiers changed from: private */
    public void updatePlayWhenReady(boolean z2, int i3, int i4) {
        int i5 = 0;
        boolean z3 = z2 && i3 != -1;
        if (z3 && i3 != 1) {
            i5 = 1;
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playWhenReady != z3 || playbackInfo2.playbackSuppressionReason != i5) {
            this.pendingOperationAcks++;
            PlaybackInfo copyWithPlayWhenReady = playbackInfo2.copyWithPlayWhenReady(z3, i5);
            this.internalPlayer.setPlayWhenReady(z3, i5);
            updatePlaybackInfo(copyWithPlayWhenReady, 0, i4, false, false, 5, C.TIME_UNSET, -1);
        }
    }

    private void updatePlaybackInfo(PlaybackInfo playbackInfo2, int i3, int i4, boolean z2, boolean z3, int i5, long j2, int i6) {
        PlaybackInfo playbackInfo3 = playbackInfo2;
        int i7 = i5;
        PlaybackInfo playbackInfo4 = this.playbackInfo;
        this.playbackInfo = playbackInfo3;
        Pair<Boolean, Integer> evaluateMediaItemTransitionReason = evaluateMediaItemTransitionReason(playbackInfo2, playbackInfo4, z3, i5, !playbackInfo4.timeline.equals(playbackInfo3.timeline));
        boolean booleanValue = ((Boolean) evaluateMediaItemTransitionReason.first).booleanValue();
        int intValue = ((Integer) evaluateMediaItemTransitionReason.second).intValue();
        MediaMetadata mediaMetadata2 = this.mediaMetadata;
        MediaItem mediaItem = null;
        if (booleanValue) {
            if (!playbackInfo3.timeline.isEmpty()) {
                mediaItem = playbackInfo3.timeline.getWindow(playbackInfo3.timeline.getPeriodByUid(playbackInfo3.periodId.periodUid, this.period).windowIndex, this.window).mediaItem;
            }
            this.staticAndDynamicMediaMetadata = MediaMetadata.EMPTY;
        }
        if (booleanValue || !playbackInfo4.staticMetadata.equals(playbackInfo3.staticMetadata)) {
            this.staticAndDynamicMediaMetadata = this.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(playbackInfo3.staticMetadata).build();
            mediaMetadata2 = buildUpdatedMediaMetadata();
        }
        boolean equals = mediaMetadata2.equals(this.mediaMetadata);
        this.mediaMetadata = mediaMetadata2;
        boolean z4 = playbackInfo4.playWhenReady != playbackInfo3.playWhenReady;
        boolean z5 = playbackInfo4.playbackState != playbackInfo3.playbackState;
        if (z5 || z4) {
            updateWakeAndWifiLock();
        }
        boolean z6 = playbackInfo4.isLoading;
        boolean z7 = playbackInfo3.isLoading;
        boolean z8 = z6 != z7;
        if (z8) {
            updatePriorityTaskManagerForIsLoadingChange(z7);
        }
        if (!playbackInfo4.timeline.equals(playbackInfo3.timeline)) {
            this.listeners.queueEvent(0, new t(playbackInfo3, i3, 0));
        }
        if (z3) {
            this.listeners.queueEvent(11, new u(getPreviousPositionInfo(i7, playbackInfo4, i6), getPositionInfo(j2), i7));
        }
        if (booleanValue) {
            this.listeners.queueEvent(1, new t(mediaItem, intValue, 2));
        }
        if (playbackInfo4.playbackError != playbackInfo3.playbackError) {
            this.listeners.queueEvent(10, new l(playbackInfo3, 0));
            if (playbackInfo3.playbackError != null) {
                this.listeners.queueEvent(10, new l(playbackInfo3, 1));
            }
        }
        TrackSelectorResult trackSelectorResult = playbackInfo4.trackSelectorResult;
        TrackSelectorResult trackSelectorResult2 = playbackInfo3.trackSelectorResult;
        if (trackSelectorResult != trackSelectorResult2) {
            this.trackSelector.onSelectionActivated(trackSelectorResult2.info);
            this.listeners.queueEvent(2, new l(playbackInfo3, 2));
        }
        if (!equals) {
            this.listeners.queueEvent(14, new m(this.mediaMetadata, 0));
        }
        if (z8) {
            this.listeners.queueEvent(3, new l(playbackInfo3, 3));
        }
        if (z5 || z4) {
            this.listeners.queueEvent(-1, new l(playbackInfo3, 4));
        }
        if (z5) {
            this.listeners.queueEvent(4, new l(playbackInfo3, 5));
        }
        if (z4) {
            this.listeners.queueEvent(5, new t(playbackInfo3, i4, 1));
        }
        if (playbackInfo4.playbackSuppressionReason != playbackInfo3.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new l(playbackInfo3, 6));
        }
        if (isPlaying(playbackInfo4) != isPlaying(playbackInfo2)) {
            this.listeners.queueEvent(7, new l(playbackInfo3, 7));
        }
        if (!playbackInfo4.playbackParameters.equals(playbackInfo3.playbackParameters)) {
            this.listeners.queueEvent(12, new l(playbackInfo3, 8));
        }
        if (z2) {
            this.listeners.queueEvent(-1, new r(3));
        }
        updateAvailableCommands();
        this.listeners.flushEvents();
        if (playbackInfo4.offloadSchedulingEnabled != playbackInfo3.offloadSchedulingEnabled) {
            Iterator<ExoPlayer.AudioOffloadListener> it = this.audioOffloadListeners.iterator();
            while (it.hasNext()) {
                it.next().onExperimentalOffloadSchedulingEnabledChanged(playbackInfo3.offloadSchedulingEnabled);
            }
        }
        if (playbackInfo4.sleepingForOffload != playbackInfo3.sleepingForOffload) {
            Iterator<ExoPlayer.AudioOffloadListener> it2 = this.audioOffloadListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onExperimentalSleepingForOffloadChanged(playbackInfo3.sleepingForOffload);
            }
        }
    }

    private void updatePriorityTaskManagerForIsLoadingChange(boolean z2) {
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        if (priorityTaskManager2 == null) {
            return;
        }
        if (z2 && !this.isPriorityTaskManagerRegistered) {
            priorityTaskManager2.add(0);
            this.isPriorityTaskManagerRegistered = true;
        } else if (!z2 && this.isPriorityTaskManagerRegistered) {
            priorityTaskManager2.remove(0);
            this.isPriorityTaskManagerRegistered = false;
        }
    }

    /* access modifiers changed from: private */
    public void updateWakeAndWifiLock() {
        int playbackState = getPlaybackState();
        boolean z2 = true;
        if (playbackState != 1) {
            if (playbackState == 2 || playbackState == 3) {
                boolean experimentalIsSleepingForOffload = experimentalIsSleepingForOffload();
                WakeLockManager wakeLockManager2 = this.wakeLockManager;
                if (!getPlayWhenReady() || experimentalIsSleepingForOffload) {
                    z2 = false;
                }
                wakeLockManager2.setStayAwake(z2);
                this.wifiLockManager.setStayAwake(getPlayWhenReady());
                return;
            } else if (playbackState != 4) {
                throw new IllegalStateException();
            }
        }
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
    }

    private void verifyApplicationThread() {
        this.constructorFinished.blockUninterruptible();
        if (Thread.currentThread() != getApplicationLooper().getThread()) {
            String formatInvariant = Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://exoplayer.dev/issues/player-accessed-on-wrong-thread", Thread.currentThread().getName(), getApplicationLooper().getThread().getName());
            if (!this.throwsWhenUsingWrongThread) {
                Log.w(TAG, formatInvariant, this.hasNotifiedFullWrongThreadWarning ? null : new IllegalStateException());
                this.hasNotifiedFullWrongThreadWarning = true;
                return;
            }
            throw new IllegalStateException(formatInvariant);
        }
    }

    public void addAnalyticsListener(AnalyticsListener analyticsListener) {
        Assertions.checkNotNull(analyticsListener);
        this.analyticsCollector.addListener(analyticsListener);
    }

    public void addAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        this.audioOffloadListeners.add(audioOffloadListener);
    }

    public void addListener(Player.Listener listener) {
        Assertions.checkNotNull(listener);
        this.listeners.add(listener);
    }

    public void addMediaItems(int i3, List<MediaItem> list) {
        verifyApplicationThread();
        addMediaSources(Math.min(i3, this.mediaSourceHolderSnapshots.size()), createMediaSources(list));
    }

    public void addMediaSource(MediaSource mediaSource) {
        verifyApplicationThread();
        addMediaSources(Collections.singletonList(mediaSource));
    }

    public void addMediaSources(List<MediaSource> list) {
        verifyApplicationThread();
        addMediaSources(this.mediaSourceHolderSnapshots.size(), list);
    }

    public void clearAuxEffectInfo() {
        verifyApplicationThread();
        setAuxEffectInfo(new AuxEffectInfo(0, 0.0f));
    }

    public void clearCameraMotionListener(CameraMotionListener cameraMotionListener2) {
        verifyApplicationThread();
        if (this.cameraMotionListener == cameraMotionListener2) {
            createMessageInternal(this.frameMetadataListener).setType(8).setPayload((Object) null).send();
        }
    }

    public void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener2) {
        verifyApplicationThread();
        if (this.videoFrameMetadataListener == videoFrameMetadataListener2) {
            createMessageInternal(this.frameMetadataListener).setType(7).setPayload((Object) null).send();
        }
    }

    public void clearVideoSurface() {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal((Object) null);
        maybeNotifySurfaceSizeChanged(0, 0);
    }

    public void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder2) {
        verifyApplicationThread();
        if (surfaceHolder2 != null && surfaceHolder2 == this.surfaceHolder) {
            clearVideoSurface();
        }
    }

    public void clearVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        verifyApplicationThread();
        clearVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
    }

    public void clearVideoTextureView(@Nullable TextureView textureView2) {
        verifyApplicationThread();
        if (textureView2 != null && textureView2 == this.textureView) {
            clearVideoSurface();
        }
    }

    public PlayerMessage createMessage(PlayerMessage.Target target) {
        verifyApplicationThread();
        return createMessageInternal(target);
    }

    public void decreaseDeviceVolume() {
        verifyApplicationThread();
        this.streamVolumeManager.decreaseVolume();
    }

    public boolean experimentalIsSleepingForOffload() {
        verifyApplicationThread();
        return this.playbackInfo.sleepingForOffload;
    }

    public void experimentalSetOffloadSchedulingEnabled(boolean z2) {
        verifyApplicationThread();
        this.internalPlayer.experimentalSetOffloadSchedulingEnabled(z2);
    }

    public AnalyticsCollector getAnalyticsCollector() {
        verifyApplicationThread();
        return this.analyticsCollector;
    }

    public Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    public AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        return this.audioAttributes;
    }

    @Deprecated
    public ExoPlayer.AudioComponent getAudioComponent() {
        verifyApplicationThread();
        return this;
    }

    @Nullable
    public DecoderCounters getAudioDecoderCounters() {
        verifyApplicationThread();
        return this.audioDecoderCounters;
    }

    @Nullable
    public Format getAudioFormat() {
        verifyApplicationThread();
        return this.audioFormat;
    }

    public int getAudioSessionId() {
        verifyApplicationThread();
        return this.audioSessionId;
    }

    public Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        return this.availableCommands;
    }

    public long getBufferedPosition() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getContentBufferedPosition();
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        return playbackInfo2.loadingMediaPeriodId.equals(playbackInfo2.periodId) ? Util.usToMs(this.playbackInfo.bufferedPositionUs) : getDuration();
    }

    public Clock getClock() {
        return this.clock;
    }

    public long getContentBufferedPosition() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty()) {
            return this.maskingWindowPositionMs;
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.loadingMediaPeriodId.windowSequenceNumber != playbackInfo2.periodId.windowSequenceNumber) {
            return playbackInfo2.timeline.getWindow(getCurrentMediaItemIndex(), this.window).getDurationMs();
        }
        long j2 = playbackInfo2.bufferedPositionUs;
        if (this.playbackInfo.loadingMediaPeriodId.isAd()) {
            PlaybackInfo playbackInfo3 = this.playbackInfo;
            Timeline.Period periodByUid = playbackInfo3.timeline.getPeriodByUid(playbackInfo3.loadingMediaPeriodId.periodUid, this.period);
            long adGroupTimeUs = periodByUid.getAdGroupTimeUs(this.playbackInfo.loadingMediaPeriodId.adGroupIndex);
            j2 = adGroupTimeUs == Long.MIN_VALUE ? periodByUid.durationUs : adGroupTimeUs;
        }
        PlaybackInfo playbackInfo4 = this.playbackInfo;
        return Util.usToMs(periodPositionUsToWindowPositionUs(playbackInfo4.timeline, playbackInfo4.loadingMediaPeriodId, j2));
    }

    public long getContentPosition() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getCurrentPosition();
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period);
        PlaybackInfo playbackInfo3 = this.playbackInfo;
        return playbackInfo3.requestedContentPositionUs == C.TIME_UNSET ? playbackInfo3.timeline.getWindow(getCurrentMediaItemIndex(), this.window).getDefaultPositionMs() : this.period.getPositionInWindowMs() + Util.usToMs(this.playbackInfo.requestedContentPositionUs);
    }

    public int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adGroupIndex;
        }
        return -1;
    }

    public int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adIndexInAdGroup;
        }
        return -1;
    }

    public CueGroup getCurrentCues() {
        verifyApplicationThread();
        return this.currentCueGroup;
    }

    public int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        int currentWindowIndexInternal = getCurrentWindowIndexInternal();
        if (currentWindowIndexInternal == -1) {
            return 0;
        }
        return currentWindowIndexInternal;
    }

    public int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty()) {
            return this.maskingPeriodIndex;
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        return playbackInfo2.timeline.getIndexOfPeriod(playbackInfo2.periodId.periodUid);
    }

    public long getCurrentPosition() {
        verifyApplicationThread();
        return Util.usToMs(getCurrentPositionUsInternal(this.playbackInfo));
    }

    public Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return this.playbackInfo.timeline;
    }

    public TrackGroupArray getCurrentTrackGroups() {
        verifyApplicationThread();
        return this.playbackInfo.trackGroups;
    }

    public TrackSelectionArray getCurrentTrackSelections() {
        verifyApplicationThread();
        return new TrackSelectionArray(this.playbackInfo.trackSelectorResult.selections);
    }

    public Tracks getCurrentTracks() {
        verifyApplicationThread();
        return this.playbackInfo.trackSelectorResult.tracks;
    }

    @Deprecated
    public ExoPlayer.DeviceComponent getDeviceComponent() {
        verifyApplicationThread();
        return this;
    }

    public DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        return this.deviceInfo;
    }

    public int getDeviceVolume() {
        verifyApplicationThread();
        return this.streamVolumeManager.getVolume();
    }

    public long getDuration() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo2.periodId;
        playbackInfo2.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return Util.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
    }

    public long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        return C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
    }

    public MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return this.mediaMetadata;
    }

    public boolean getPauseAtEndOfMediaItems() {
        verifyApplicationThread();
        return this.pauseAtEndOfMediaItems;
    }

    public boolean getPlayWhenReady() {
        verifyApplicationThread();
        return this.playbackInfo.playWhenReady;
    }

    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    public PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return this.playbackInfo.playbackParameters;
    }

    public int getPlaybackState() {
        verifyApplicationThread();
        return this.playbackInfo.playbackState;
    }

    public int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        return this.playbackInfo.playbackSuppressionReason;
    }

    public MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return this.playlistMetadata;
    }

    public Renderer getRenderer(int i3) {
        verifyApplicationThread();
        return this.renderers[i3];
    }

    public int getRendererCount() {
        verifyApplicationThread();
        return this.renderers.length;
    }

    public int getRendererType(int i3) {
        verifyApplicationThread();
        return this.renderers[i3].getTrackType();
    }

    public int getRepeatMode() {
        verifyApplicationThread();
        return this.repeatMode;
    }

    public long getSeekBackIncrement() {
        verifyApplicationThread();
        return this.seekBackIncrementMs;
    }

    public long getSeekForwardIncrement() {
        verifyApplicationThread();
        return this.seekForwardIncrementMs;
    }

    public SeekParameters getSeekParameters() {
        verifyApplicationThread();
        return this.seekParameters;
    }

    public boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return this.shuffleModeEnabled;
    }

    public boolean getSkipSilenceEnabled() {
        verifyApplicationThread();
        return this.skipSilenceEnabled;
    }

    @Deprecated
    public ExoPlayer.TextComponent getTextComponent() {
        verifyApplicationThread();
        return this;
    }

    public long getTotalBufferedDuration() {
        verifyApplicationThread();
        return Util.usToMs(this.playbackInfo.totalBufferedDurationUs);
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        return this.trackSelector.getParameters();
    }

    public TrackSelector getTrackSelector() {
        verifyApplicationThread();
        return this.trackSelector;
    }

    public int getVideoChangeFrameRateStrategy() {
        verifyApplicationThread();
        return this.videoChangeFrameRateStrategy;
    }

    @Deprecated
    public ExoPlayer.VideoComponent getVideoComponent() {
        verifyApplicationThread();
        return this;
    }

    @Nullable
    public DecoderCounters getVideoDecoderCounters() {
        verifyApplicationThread();
        return this.videoDecoderCounters;
    }

    @Nullable
    public Format getVideoFormat() {
        verifyApplicationThread();
        return this.videoFormat;
    }

    public int getVideoScalingMode() {
        verifyApplicationThread();
        return this.videoScalingMode;
    }

    public VideoSize getVideoSize() {
        verifyApplicationThread();
        return this.videoSize;
    }

    public float getVolume() {
        verifyApplicationThread();
        return this.volume;
    }

    public void increaseDeviceVolume() {
        verifyApplicationThread();
        this.streamVolumeManager.increaseVolume();
    }

    public boolean isDeviceMuted() {
        verifyApplicationThread();
        return this.streamVolumeManager.isMuted();
    }

    public boolean isLoading() {
        verifyApplicationThread();
        return this.playbackInfo.isLoading;
    }

    public boolean isPlayingAd() {
        verifyApplicationThread();
        return this.playbackInfo.periodId.isAd();
    }

    public void moveMediaItems(int i3, int i4, int i5) {
        verifyApplicationThread();
        Assertions.checkArgument(i3 >= 0 && i3 <= i4 && i4 <= this.mediaSourceHolderSnapshots.size() && i5 >= 0);
        Timeline currentTimeline = getCurrentTimeline();
        this.pendingOperationAcks++;
        int min = Math.min(i5, this.mediaSourceHolderSnapshots.size() - (i4 - i3));
        Util.moveItems(this.mediaSourceHolderSnapshots, i3, i4, min);
        Timeline createMaskingTimeline = createMaskingTimeline();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, createMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(currentTimeline, createMaskingTimeline));
        this.internalPlayer.moveMediaSources(i3, i4, min, this.shuffleOrder);
        updatePlaybackInfo(maskTimelineAndPosition, 0, 1, false, false, 5, C.TIME_UNSET, -1);
    }

    public void prepare() {
        verifyApplicationThread();
        boolean playWhenReady = getPlayWhenReady();
        int i3 = 2;
        int updateAudioFocus = this.audioFocusManager.updateAudioFocus(playWhenReady, 2);
        updatePlayWhenReady(playWhenReady, updateAudioFocus, getPlayWhenReadyChangeReason(playWhenReady, updateAudioFocus));
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (playbackInfo2.playbackState == 1) {
            PlaybackInfo copyWithPlaybackError = playbackInfo2.copyWithPlaybackError((ExoPlaybackException) null);
            if (copyWithPlaybackError.timeline.isEmpty()) {
                i3 = 4;
            }
            PlaybackInfo copyWithPlaybackState = copyWithPlaybackError.copyWithPlaybackState(i3);
            this.pendingOperationAcks++;
            this.internalPlayer.prepare();
            updatePlaybackInfo(copyWithPlaybackState, 1, 1, false, false, 5, C.TIME_UNSET, -1);
        }
    }

    public void release() {
        AudioTrack audioTrack;
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [ExoPlayerLib/2.18.0] [" + Util.DEVICE_DEBUG_INFO + "] [" + ExoPlayerLibraryInfo.registeredModules() + "]");
        verifyApplicationThread();
        if (Util.SDK_INT < 21 && (audioTrack = this.keepSessionIdAudioTrack) != null) {
            audioTrack.release();
            this.keepSessionIdAudioTrack = null;
        }
        this.audioBecomingNoisyManager.setEnabled(false);
        this.streamVolumeManager.release();
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
        this.audioFocusManager.release();
        if (!this.internalPlayer.release()) {
            this.listeners.sendEvent(10, new r(0));
        }
        this.listeners.release();
        this.playbackInfoUpdateHandler.removeCallbacksAndMessages((Object) null);
        this.bandwidthMeter.removeEventListener(this.analyticsCollector);
        PlaybackInfo copyWithPlaybackState = this.playbackInfo.copyWithPlaybackState(1);
        this.playbackInfo = copyWithPlaybackState;
        PlaybackInfo copyWithLoadingMediaPeriodId = copyWithPlaybackState.copyWithLoadingMediaPeriodId(copyWithPlaybackState.periodId);
        this.playbackInfo = copyWithLoadingMediaPeriodId;
        copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
        this.playbackInfo.totalBufferedDurationUs = 0;
        this.analyticsCollector.release();
        this.trackSelector.release();
        removeSurfaceCallbacks();
        Surface surface = this.ownedSurface;
        if (surface != null) {
            surface.release();
            this.ownedSurface = null;
        }
        if (this.isPriorityTaskManagerRegistered) {
            ((PriorityTaskManager) Assertions.checkNotNull(this.priorityTaskManager)).remove(0);
            this.isPriorityTaskManagerRegistered = false;
        }
        this.currentCueGroup = CueGroup.EMPTY;
        this.playerReleased = true;
    }

    public void removeAnalyticsListener(AnalyticsListener analyticsListener) {
        this.analyticsCollector.removeListener(analyticsListener);
    }

    public void removeAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        this.audioOffloadListeners.remove(audioOffloadListener);
    }

    public void removeListener(Player.Listener listener) {
        Assertions.checkNotNull(listener);
        this.listeners.remove(listener);
    }

    public void removeMediaItems(int i3, int i4) {
        verifyApplicationThread();
        PlaybackInfo removeMediaItemsInternal = removeMediaItemsInternal(i3, Math.min(i4, this.mediaSourceHolderSnapshots.size()));
        updatePlaybackInfo(removeMediaItemsInternal, 0, 1, false, !removeMediaItemsInternal.periodId.periodUid.equals(this.playbackInfo.periodId.periodUid), 4, getCurrentPositionUsInternal(removeMediaItemsInternal), -1);
    }

    @Deprecated
    public void retry() {
        verifyApplicationThread();
        prepare();
    }

    public void seekTo(int i3, long j2) {
        verifyApplicationThread();
        this.analyticsCollector.notifySeekStarted();
        Timeline timeline = this.playbackInfo.timeline;
        if (i3 < 0 || (!timeline.isEmpty() && i3 >= timeline.getWindowCount())) {
            throw new IllegalSeekPositionException(timeline, i3, j2);
        }
        int i4 = 1;
        this.pendingOperationAcks++;
        if (isPlayingAd()) {
            Log.w(TAG, "seekTo ignored because an ad is playing");
            ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate = new ExoPlayerImplInternal.PlaybackInfoUpdate(this.playbackInfo);
            playbackInfoUpdate.incrementPendingOperationAcks(1);
            this.playbackInfoUpdateListener.onPlaybackInfoUpdate(playbackInfoUpdate);
            return;
        }
        if (getPlaybackState() != 1) {
            i4 = 2;
        }
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo.copyWithPlaybackState(i4), timeline, maskWindowPositionMsOrGetPeriodPositionUs(timeline, i3, j2));
        this.internalPlayer.seekTo(timeline, i3, Util.msToUs(j2));
        updatePlaybackInfo(maskTimelineAndPosition, 0, 1, true, true, 1, getCurrentPositionUsInternal(maskTimelineAndPosition), currentMediaItemIndex);
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2, boolean z2) {
        verifyApplicationThread();
        if (!this.playerReleased) {
            if (!Util.areEqual(this.audioAttributes, audioAttributes2)) {
                this.audioAttributes = audioAttributes2;
                sendRendererMessage(1, 3, audioAttributes2);
                this.streamVolumeManager.setStreamType(Util.getStreamTypeForAudioUsage(audioAttributes2.usage));
                this.listeners.queueEvent(20, new m(audioAttributes2, 1));
            }
            this.audioFocusManager.setAudioAttributes(z2 ? audioAttributes2 : null);
            this.trackSelector.setAudioAttributes(audioAttributes2);
            boolean playWhenReady = getPlayWhenReady();
            int updateAudioFocus = this.audioFocusManager.updateAudioFocus(playWhenReady, getPlaybackState());
            updatePlayWhenReady(playWhenReady, updateAudioFocus, getPlayWhenReadyChangeReason(playWhenReady, updateAudioFocus));
            this.listeners.flushEvents();
        }
    }

    public void setAudioSessionId(int i3) {
        verifyApplicationThread();
        if (this.audioSessionId != i3) {
            if (i3 == 0) {
                i3 = Util.SDK_INT < 21 ? initializeKeepSessionIdAudioTrack(0) : Util.generateAudioSessionIdV21(this.applicationContext);
            } else if (Util.SDK_INT < 21) {
                initializeKeepSessionIdAudioTrack(i3);
            }
            this.audioSessionId = i3;
            sendRendererMessage(1, 10, Integer.valueOf(i3));
            sendRendererMessage(2, 10, Integer.valueOf(i3));
            this.listeners.sendEvent(21, new k(i3, 1));
        }
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        verifyApplicationThread();
        sendRendererMessage(1, 6, auxEffectInfo);
    }

    public void setCameraMotionListener(CameraMotionListener cameraMotionListener2) {
        verifyApplicationThread();
        this.cameraMotionListener = cameraMotionListener2;
        createMessageInternal(this.frameMetadataListener).setType(8).setPayload(cameraMotionListener2).send();
    }

    public void setDeviceMuted(boolean z2) {
        verifyApplicationThread();
        this.streamVolumeManager.setMuted(z2);
    }

    public void setDeviceVolume(int i3) {
        verifyApplicationThread();
        this.streamVolumeManager.setVolume(i3);
    }

    public void setForegroundMode(boolean z2) {
        verifyApplicationThread();
        if (this.foregroundMode != z2) {
            this.foregroundMode = z2;
            if (!this.internalPlayer.setForegroundMode(z2)) {
                stopInternal(false, ExoPlaybackException.createForUnexpected(new ExoTimeoutException(2), 1003));
            }
        }
    }

    public void setHandleAudioBecomingNoisy(boolean z2) {
        verifyApplicationThread();
        if (!this.playerReleased) {
            this.audioBecomingNoisyManager.setEnabled(z2);
        }
    }

    public void setHandleWakeLock(boolean z2) {
        verifyApplicationThread();
        setWakeMode(z2 ? 1 : 0);
    }

    public void setMediaItems(List<MediaItem> list, boolean z2) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(list), z2);
    }

    public void setMediaSource(MediaSource mediaSource) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource));
    }

    public void setMediaSources(List<MediaSource> list) {
        verifyApplicationThread();
        setMediaSources(list, true);
    }

    public void setPauseAtEndOfMediaItems(boolean z2) {
        verifyApplicationThread();
        if (this.pauseAtEndOfMediaItems != z2) {
            this.pauseAtEndOfMediaItems = z2;
            this.internalPlayer.setPauseAtEndOfWindow(z2);
        }
    }

    public void setPlayWhenReady(boolean z2) {
        verifyApplicationThread();
        int updateAudioFocus = this.audioFocusManager.updateAudioFocus(z2, getPlaybackState());
        updatePlayWhenReady(z2, updateAudioFocus, getPlayWhenReadyChangeReason(z2, updateAudioFocus));
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThread();
        if (playbackParameters == null) {
            playbackParameters = PlaybackParameters.DEFAULT;
        }
        if (!this.playbackInfo.playbackParameters.equals(playbackParameters)) {
            PlaybackInfo copyWithPlaybackParameters = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
            this.pendingOperationAcks++;
            this.internalPlayer.setPlaybackParameters(playbackParameters);
            updatePlaybackInfo(copyWithPlaybackParameters, 0, 1, false, false, 5, C.TIME_UNSET, -1);
        }
    }

    public void setPlaylistMetadata(MediaMetadata mediaMetadata2) {
        verifyApplicationThread();
        Assertions.checkNotNull(mediaMetadata2);
        if (!mediaMetadata2.equals(this.playlistMetadata)) {
            this.playlistMetadata = mediaMetadata2;
            this.listeners.sendEvent(15, new q(this, 0));
        }
    }

    public void setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager2) {
        verifyApplicationThread();
        if (!Util.areEqual(this.priorityTaskManager, priorityTaskManager2)) {
            if (this.isPriorityTaskManagerRegistered) {
                ((PriorityTaskManager) Assertions.checkNotNull(this.priorityTaskManager)).remove(0);
            }
            if (priorityTaskManager2 == null || !isLoading()) {
                this.isPriorityTaskManagerRegistered = false;
            } else {
                priorityTaskManager2.add(0);
                this.isPriorityTaskManagerRegistered = true;
            }
            this.priorityTaskManager = priorityTaskManager2;
        }
    }

    public void setRepeatMode(int i3) {
        verifyApplicationThread();
        if (this.repeatMode != i3) {
            this.repeatMode = i3;
            this.internalPlayer.setRepeatMode(i3);
            this.listeners.queueEvent(8, new k(i3, 0));
            updateAvailableCommands();
            this.listeners.flushEvents();
        }
    }

    public void setSeekParameters(@Nullable SeekParameters seekParameters2) {
        verifyApplicationThread();
        if (seekParameters2 == null) {
            seekParameters2 = SeekParameters.DEFAULT;
        }
        if (!this.seekParameters.equals(seekParameters2)) {
            this.seekParameters = seekParameters2;
            this.internalPlayer.setSeekParameters(seekParameters2);
        }
    }

    public void setShuffleModeEnabled(boolean z2) {
        verifyApplicationThread();
        if (this.shuffleModeEnabled != z2) {
            this.shuffleModeEnabled = z2;
            this.internalPlayer.setShuffleModeEnabled(z2);
            this.listeners.queueEvent(9, new n(z2, 1));
            updateAvailableCommands();
            this.listeners.flushEvents();
        }
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder2) {
        verifyApplicationThread();
        Timeline createMaskingTimeline = createMaskingTimeline();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, createMaskingTimeline, maskWindowPositionMsOrGetPeriodPositionUs(createMaskingTimeline, getCurrentMediaItemIndex(), getCurrentPosition()));
        this.pendingOperationAcks++;
        this.shuffleOrder = shuffleOrder2;
        this.internalPlayer.setShuffleOrder(shuffleOrder2);
        updatePlaybackInfo(maskTimelineAndPosition, 0, 1, false, false, 5, C.TIME_UNSET, -1);
    }

    public void setSkipSilenceEnabled(boolean z2) {
        verifyApplicationThread();
        if (this.skipSilenceEnabled != z2) {
            this.skipSilenceEnabled = z2;
            sendRendererMessage(1, 9, Boolean.valueOf(z2));
            this.listeners.sendEvent(23, new n(z2, 0));
        }
    }

    public void setThrowsWhenUsingWrongThread(boolean z2) {
        this.throwsWhenUsingWrongThread = z2;
    }

    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThread();
        if (this.trackSelector.isSetParametersSupported() && !trackSelectionParameters.equals(this.trackSelector.getParameters())) {
            this.trackSelector.setParameters(trackSelectionParameters);
            this.listeners.sendEvent(19, new m(trackSelectionParameters, 2));
        }
    }

    public void setVideoChangeFrameRateStrategy(int i3) {
        verifyApplicationThread();
        if (this.videoChangeFrameRateStrategy != i3) {
            this.videoChangeFrameRateStrategy = i3;
            sendRendererMessage(2, 5, Integer.valueOf(i3));
        }
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener2) {
        verifyApplicationThread();
        this.videoFrameMetadataListener = videoFrameMetadataListener2;
        createMessageInternal(this.frameMetadataListener).setType(7).setPayload(videoFrameMetadataListener2).send();
    }

    public void setVideoScalingMode(int i3) {
        verifyApplicationThread();
        this.videoScalingMode = i3;
        sendRendererMessage(2, 4, Integer.valueOf(i3));
    }

    public void setVideoSurface(@Nullable Surface surface) {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal(surface);
        int i3 = surface == null ? 0 : -1;
        maybeNotifySurfaceSizeChanged(i3, i3);
    }

    public void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder2) {
        verifyApplicationThread();
        if (surfaceHolder2 == null) {
            clearVideoSurface();
            return;
        }
        removeSurfaceCallbacks();
        this.surfaceHolderSurfaceIsVideoOutput = true;
        this.surfaceHolder = surfaceHolder2;
        surfaceHolder2.addCallback(this.componentListener);
        Surface surface = surfaceHolder2.getSurface();
        if (surface == null || !surface.isValid()) {
            setVideoOutputInternal((Object) null);
            maybeNotifySurfaceSizeChanged(0, 0);
            return;
        }
        setVideoOutputInternal(surface);
        Rect surfaceFrame = surfaceHolder2.getSurfaceFrame();
        maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
    }

    public void setVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        verifyApplicationThread();
        if (surfaceView instanceof VideoDecoderOutputBufferRenderer) {
            removeSurfaceCallbacks();
            setVideoOutputInternal(surfaceView);
            setNonVideoOutputSurfaceHolderInternal(surfaceView.getHolder());
        } else if (surfaceView instanceof SphericalGLSurfaceView) {
            removeSurfaceCallbacks();
            this.sphericalGLSurfaceView = (SphericalGLSurfaceView) surfaceView;
            createMessageInternal(this.frameMetadataListener).setType(10000).setPayload(this.sphericalGLSurfaceView).send();
            this.sphericalGLSurfaceView.addVideoSurfaceListener(this.componentListener);
            setVideoOutputInternal(this.sphericalGLSurfaceView.getVideoSurface());
            setNonVideoOutputSurfaceHolderInternal(surfaceView.getHolder());
        } else {
            setVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
        }
    }

    public void setVideoTextureView(@Nullable TextureView textureView2) {
        verifyApplicationThread();
        if (textureView2 == null) {
            clearVideoSurface();
            return;
        }
        removeSurfaceCallbacks();
        this.textureView = textureView2;
        if (textureView2.getSurfaceTextureListener() != null) {
            Log.w(TAG, "Replacing existing SurfaceTextureListener.");
        }
        textureView2.setSurfaceTextureListener(this.componentListener);
        SurfaceTexture surfaceTexture = textureView2.isAvailable() ? textureView2.getSurfaceTexture() : null;
        if (surfaceTexture == null) {
            setVideoOutputInternal((Object) null);
            maybeNotifySurfaceSizeChanged(0, 0);
            return;
        }
        setSurfaceTextureInternal(surfaceTexture);
        maybeNotifySurfaceSizeChanged(textureView2.getWidth(), textureView2.getHeight());
    }

    public void setVolume(float f2) {
        verifyApplicationThread();
        float constrainValue = Util.constrainValue(f2, 0.0f, 1.0f);
        if (this.volume != constrainValue) {
            this.volume = constrainValue;
            sendVolumeToRenderers();
            this.listeners.sendEvent(22, new o(constrainValue));
        }
    }

    public void setWakeMode(int i3) {
        verifyApplicationThread();
        if (i3 == 0) {
            this.wakeLockManager.setEnabled(false);
            this.wifiLockManager.setEnabled(false);
        } else if (i3 == 1) {
            this.wakeLockManager.setEnabled(true);
            this.wifiLockManager.setEnabled(false);
        } else if (i3 == 2) {
            this.wakeLockManager.setEnabled(true);
            this.wifiLockManager.setEnabled(true);
        }
    }

    public void stop() {
        verifyApplicationThread();
        stop(false);
    }

    @Nullable
    public ExoPlaybackException getPlayerError() {
        verifyApplicationThread();
        return this.playbackInfo.playbackError;
    }

    public void addMediaSource(int i3, MediaSource mediaSource) {
        verifyApplicationThread();
        addMediaSources(i3, Collections.singletonList(mediaSource));
    }

    public void addMediaSources(int i3, List<MediaSource> list) {
        verifyApplicationThread();
        Assertions.checkArgument(i3 >= 0);
        Timeline currentTimeline = getCurrentTimeline();
        this.pendingOperationAcks++;
        List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders = addMediaSourceHolders(i3, list);
        Timeline createMaskingTimeline = createMaskingTimeline();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, createMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(currentTimeline, createMaskingTimeline));
        this.internalPlayer.addMediaSources(i3, addMediaSourceHolders, this.shuffleOrder);
        updatePlaybackInfo(maskTimelineAndPosition, 0, 1, false, false, 5, C.TIME_UNSET, -1);
    }

    public void setMediaItems(List<MediaItem> list, int i3, long j2) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(list), i3, j2);
    }

    public void setMediaSource(MediaSource mediaSource, long j2) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource), 0, j2);
    }

    public void setMediaSources(List<MediaSource> list, boolean z2) {
        verifyApplicationThread();
        setMediaSourcesInternal(list, -1, C.TIME_UNSET, z2);
    }

    public void stop(boolean z2) {
        verifyApplicationThread();
        this.audioFocusManager.updateAudioFocus(getPlayWhenReady(), 1);
        stopInternal(z2, (ExoPlaybackException) null);
        this.currentCueGroup = CueGroup.EMPTY;
    }

    public void clearVideoSurface(@Nullable Surface surface) {
        verifyApplicationThread();
        if (surface != null && surface == this.videoOutput) {
            clearVideoSurface();
        }
    }

    public void setMediaSources(List<MediaSource> list, int i3, long j2) {
        verifyApplicationThread();
        setMediaSourcesInternal(list, i3, j2, false);
    }

    public void setMediaSource(MediaSource mediaSource, boolean z2) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource), z2);
    }

    @Deprecated
    public void prepare(MediaSource mediaSource) {
        verifyApplicationThread();
        setMediaSource(mediaSource);
        prepare();
    }

    @Deprecated
    public void prepare(MediaSource mediaSource, boolean z2, boolean z3) {
        verifyApplicationThread();
        setMediaSource(mediaSource, z2);
        prepare();
    }
}
