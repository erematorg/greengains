package com.appsamurai.storyly.exoplayer2.core;

import android.content.Context;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.DeviceInfo;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.audio.AuxEffectInfo;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.DefaultLivePlaybackSpeedControl;
import com.appsamurai.storyly.exoplayer2.core.PlayerMessage;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.source.DefaultMediaSourceFactory;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.DefaultTrackSelector;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectionArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import com.appsamurai.storyly.exoplayer2.core.video.VideoFrameMetadataListener;
import com.appsamurai.storyly.exoplayer2.core.video.spherical.CameraMotionListener;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DefaultExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import java.util.List;

public interface ExoPlayer extends Player {
    public static final long DEFAULT_DETACH_SURFACE_TIMEOUT_MS = 2000;
    public static final long DEFAULT_RELEASE_TIMEOUT_MS = 500;

    @Deprecated
    public interface AudioComponent {
        @Deprecated
        void clearAuxEffectInfo();

        @Deprecated
        AudioAttributes getAudioAttributes();

        @Deprecated
        int getAudioSessionId();

        @Deprecated
        boolean getSkipSilenceEnabled();

        @Deprecated
        float getVolume();

        @Deprecated
        void setAudioAttributes(AudioAttributes audioAttributes, boolean z2);

        @Deprecated
        void setAudioSessionId(int i3);

        @Deprecated
        void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

        @Deprecated
        void setSkipSilenceEnabled(boolean z2);

        @Deprecated
        void setVolume(float f2);
    }

    public interface AudioOffloadListener {
        void onExperimentalOffloadSchedulingEnabledChanged(boolean z2) {
        }

        void onExperimentalSleepingForOffloadChanged(boolean z2) {
        }
    }

    public static final class Builder {
        Function<Clock, AnalyticsCollector> analyticsCollectorFunction;
        AudioAttributes audioAttributes;
        Supplier<BandwidthMeter> bandwidthMeterSupplier;
        boolean buildCalled;
        Clock clock;
        final Context context;
        long detachSurfaceTimeoutMs;
        long foregroundModeTimeoutMs;
        boolean handleAudioBecomingNoisy;
        boolean handleAudioFocus;
        LivePlaybackSpeedControl livePlaybackSpeedControl;
        Supplier<LoadControl> loadControlSupplier;
        Looper looper;
        Supplier<MediaSource.Factory> mediaSourceFactorySupplier;
        boolean pauseAtEndOfMediaItems;
        @Nullable
        PriorityTaskManager priorityTaskManager;
        long releaseTimeoutMs;
        Supplier<RenderersFactory> renderersFactorySupplier;
        long seekBackIncrementMs;
        long seekForwardIncrementMs;
        SeekParameters seekParameters;
        boolean skipSilenceEnabled;
        Supplier<TrackSelector> trackSelectorSupplier;
        boolean useLazyPreparation;
        boolean usePlatformDiagnostics;
        int videoChangeFrameRateStrategy;
        int videoScalingMode;
        int wakeMode;

        public Builder(Context context2) {
            this(context2, (Supplier<RenderersFactory>) new c(context2, 3), (Supplier<MediaSource.Factory>) new c(context2, 4));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$0(Context context2) {
            return new DefaultRenderersFactory(context2);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$1(Context context2) {
            return new DefaultMediaSourceFactory(context2, (ExtractorsFactory) new DefaultExtractorsFactory());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$new$10(TrackSelector trackSelector) {
            return trackSelector;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ LoadControl lambda$new$11(LoadControl loadControl) {
            return loadControl;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ BandwidthMeter lambda$new$12(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ AnalyticsCollector lambda$new$13(AnalyticsCollector analyticsCollector, Clock clock2) {
            return analyticsCollector;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$new$14(Context context2) {
            return new DefaultTrackSelector(context2);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$2(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$3(Context context2) {
            return new DefaultMediaSourceFactory(context2, (ExtractorsFactory) new DefaultExtractorsFactory());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$4(Context context2) {
            return new DefaultRenderersFactory(context2);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$5(MediaSource.Factory factory) {
            return factory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$6(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$7(MediaSource.Factory factory) {
            return factory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$8(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$9(MediaSource.Factory factory) {
            return factory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ AnalyticsCollector lambda$setAnalyticsCollector$21(AnalyticsCollector analyticsCollector, Clock clock2) {
            return analyticsCollector;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ BandwidthMeter lambda$setBandwidthMeter$20(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ LoadControl lambda$setLoadControl$19(LoadControl loadControl) {
            return loadControl;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$setMediaSourceFactory$17(MediaSource.Factory factory) {
            return factory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$setRenderersFactory$16(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$setTrackSelector$18(TrackSelector trackSelector) {
            return trackSelector;
        }

        public ExoPlayer build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new ExoPlayerImpl(this, (Player) null);
        }

        public SimpleExoPlayer buildSimpleExoPlayer() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new SimpleExoPlayer(this);
        }

        public Builder experimentalSetForegroundModeTimeoutMs(long j2) {
            Assertions.checkState(!this.buildCalled);
            this.foregroundModeTimeoutMs = j2;
            return this;
        }

        public Builder setAnalyticsCollector(AnalyticsCollector analyticsCollector) {
            Assertions.checkState(!this.buildCalled);
            this.analyticsCollectorFunction = new h(analyticsCollector, 0);
            return this;
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes2, boolean z2) {
            Assertions.checkState(!this.buildCalled);
            this.audioAttributes = audioAttributes2;
            this.handleAudioFocus = z2;
            return this;
        }

        public Builder setBandwidthMeter(BandwidthMeter bandwidthMeter) {
            Assertions.checkState(!this.buildCalled);
            this.bandwidthMeterSupplier = new g(bandwidthMeter, 0);
            return this;
        }

        @VisibleForTesting
        public Builder setClock(Clock clock2) {
            Assertions.checkState(!this.buildCalled);
            this.clock = clock2;
            return this;
        }

        public Builder setDetachSurfaceTimeoutMs(long j2) {
            Assertions.checkState(!this.buildCalled);
            this.detachSurfaceTimeoutMs = j2;
            return this;
        }

        public Builder setHandleAudioBecomingNoisy(boolean z2) {
            Assertions.checkState(!this.buildCalled);
            this.handleAudioBecomingNoisy = z2;
            return this;
        }

        public Builder setLivePlaybackSpeedControl(LivePlaybackSpeedControl livePlaybackSpeedControl2) {
            Assertions.checkState(!this.buildCalled);
            this.livePlaybackSpeedControl = livePlaybackSpeedControl2;
            return this;
        }

        public Builder setLoadControl(LoadControl loadControl) {
            Assertions.checkState(!this.buildCalled);
            this.loadControlSupplier = new b(loadControl, 0);
            return this;
        }

        public Builder setLooper(Looper looper2) {
            Assertions.checkState(!this.buildCalled);
            this.looper = looper2;
            return this;
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            Assertions.checkState(!this.buildCalled);
            this.mediaSourceFactorySupplier = new f(factory, 1);
            return this;
        }

        public Builder setPauseAtEndOfMediaItems(boolean z2) {
            Assertions.checkState(!this.buildCalled);
            this.pauseAtEndOfMediaItems = z2;
            return this;
        }

        public Builder setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager2) {
            Assertions.checkState(!this.buildCalled);
            this.priorityTaskManager = priorityTaskManager2;
            return this;
        }

        public Builder setReleaseTimeoutMs(long j2) {
            Assertions.checkState(!this.buildCalled);
            this.releaseTimeoutMs = j2;
            return this;
        }

        public Builder setRenderersFactory(RenderersFactory renderersFactory) {
            Assertions.checkState(!this.buildCalled);
            this.renderersFactorySupplier = new i(renderersFactory, 1);
            return this;
        }

        public Builder setSeekBackIncrementMs(@IntRange(from = 1) long j2) {
            Assertions.checkArgument(j2 > 0);
            Assertions.checkState(!this.buildCalled);
            this.seekBackIncrementMs = j2;
            return this;
        }

        public Builder setSeekForwardIncrementMs(@IntRange(from = 1) long j2) {
            Assertions.checkArgument(j2 > 0);
            Assertions.checkState(!this.buildCalled);
            this.seekForwardIncrementMs = j2;
            return this;
        }

        public Builder setSeekParameters(SeekParameters seekParameters2) {
            Assertions.checkState(!this.buildCalled);
            this.seekParameters = seekParameters2;
            return this;
        }

        public Builder setSkipSilenceEnabled(boolean z2) {
            Assertions.checkState(!this.buildCalled);
            this.skipSilenceEnabled = z2;
            return this;
        }

        public Builder setTrackSelector(TrackSelector trackSelector) {
            Assertions.checkState(!this.buildCalled);
            this.trackSelectorSupplier = new j(trackSelector, 0);
            return this;
        }

        public Builder setUseLazyPreparation(boolean z2) {
            Assertions.checkState(!this.buildCalled);
            this.useLazyPreparation = z2;
            return this;
        }

        public Builder setUsePlatformDiagnostics(boolean z2) {
            Assertions.checkState(!this.buildCalled);
            this.usePlatformDiagnostics = z2;
            return this;
        }

        public Builder setVideoChangeFrameRateStrategy(int i3) {
            Assertions.checkState(!this.buildCalled);
            this.videoChangeFrameRateStrategy = i3;
            return this;
        }

        public Builder setVideoScalingMode(int i3) {
            Assertions.checkState(!this.buildCalled);
            this.videoScalingMode = i3;
            return this;
        }

        public Builder setWakeMode(int i3) {
            Assertions.checkState(!this.buildCalled);
            this.wakeMode = i3;
            return this;
        }

        public Builder(Context context2, RenderersFactory renderersFactory) {
            this(context2, (Supplier<RenderersFactory>) new i(renderersFactory, 3), (Supplier<MediaSource.Factory>) new c(context2, 5));
        }

        public Builder(Context context2, MediaSource.Factory factory) {
            this(context2, (Supplier<RenderersFactory>) new c(context2, 2), (Supplier<MediaSource.Factory>) new f(factory, 0));
        }

        public Builder(Context context2, RenderersFactory renderersFactory, MediaSource.Factory factory) {
            this(context2, (Supplier<RenderersFactory>) new i(renderersFactory, 0), (Supplier<MediaSource.Factory>) new f(factory, 2));
        }

        public Builder(Context context2, RenderersFactory renderersFactory, MediaSource.Factory factory, TrackSelector trackSelector, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector) {
            this(context2, (Supplier<RenderersFactory>) new i(renderersFactory, 2), (Supplier<MediaSource.Factory>) new f(factory, 3), (Supplier<TrackSelector>) new j(trackSelector, 1), (Supplier<LoadControl>) new b(loadControl, 1), (Supplier<BandwidthMeter>) new g(bandwidthMeter, 1), (Function<Clock, AnalyticsCollector>) new h(analyticsCollector, 1));
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.common.base.Supplier, java.lang.Object] */
        /* JADX WARNING: type inference failed for: r7v0, types: [com.google.common.base.Function, java.lang.Object] */
        private Builder(Context context2, Supplier<RenderersFactory> supplier, Supplier<MediaSource.Factory> supplier2) {
            this(context2, supplier, supplier2, (Supplier<TrackSelector>) new c(context2, 0), (Supplier<LoadControl>) new Object(), (Supplier<BandwidthMeter>) new c(context2, 1), (Function<Clock, AnalyticsCollector>) new Object());
        }

        private Builder(Context context2, Supplier<RenderersFactory> supplier, Supplier<MediaSource.Factory> supplier2, Supplier<TrackSelector> supplier3, Supplier<LoadControl> supplier4, Supplier<BandwidthMeter> supplier5, Function<Clock, AnalyticsCollector> function) {
            this.context = context2;
            this.renderersFactorySupplier = supplier;
            this.mediaSourceFactorySupplier = supplier2;
            this.trackSelectorSupplier = supplier3;
            this.loadControlSupplier = supplier4;
            this.bandwidthMeterSupplier = supplier5;
            this.analyticsCollectorFunction = function;
            this.looper = Util.getCurrentOrMainLooper();
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.wakeMode = 0;
            this.videoScalingMode = 1;
            this.videoChangeFrameRateStrategy = 0;
            this.useLazyPreparation = true;
            this.seekParameters = SeekParameters.DEFAULT;
            this.seekBackIncrementMs = 5000;
            this.seekForwardIncrementMs = 15000;
            this.livePlaybackSpeedControl = new DefaultLivePlaybackSpeedControl.Builder().build();
            this.clock = Clock.DEFAULT;
            this.releaseTimeoutMs = 500;
            this.detachSurfaceTimeoutMs = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
            this.usePlatformDiagnostics = true;
        }
    }

    @Deprecated
    public interface DeviceComponent {
        @Deprecated
        void decreaseDeviceVolume();

        @Deprecated
        DeviceInfo getDeviceInfo();

        @Deprecated
        int getDeviceVolume();

        @Deprecated
        void increaseDeviceVolume();

        @Deprecated
        boolean isDeviceMuted();

        @Deprecated
        void setDeviceMuted(boolean z2);

        @Deprecated
        void setDeviceVolume(int i3);
    }

    @Deprecated
    public interface TextComponent {
        @Deprecated
        CueGroup getCurrentCues();
    }

    @Deprecated
    public interface VideoComponent {
        @Deprecated
        void clearCameraMotionListener(CameraMotionListener cameraMotionListener);

        @Deprecated
        void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

        @Deprecated
        void clearVideoSurface();

        @Deprecated
        void clearVideoSurface(@Nullable Surface surface);

        @Deprecated
        void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder);

        @Deprecated
        void clearVideoSurfaceView(@Nullable SurfaceView surfaceView);

        @Deprecated
        void clearVideoTextureView(@Nullable TextureView textureView);

        @Deprecated
        int getVideoChangeFrameRateStrategy();

        @Deprecated
        int getVideoScalingMode();

        @Deprecated
        VideoSize getVideoSize();

        @Deprecated
        void setCameraMotionListener(CameraMotionListener cameraMotionListener);

        @Deprecated
        void setVideoChangeFrameRateStrategy(int i3);

        @Deprecated
        void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

        @Deprecated
        void setVideoScalingMode(int i3);

        @Deprecated
        void setVideoSurface(@Nullable Surface surface);

        @Deprecated
        void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder);

        @Deprecated
        void setVideoSurfaceView(@Nullable SurfaceView surfaceView);

        @Deprecated
        void setVideoTextureView(@Nullable TextureView textureView);
    }

    void addAnalyticsListener(AnalyticsListener analyticsListener);

    void addAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    void addMediaSource(int i3, MediaSource mediaSource);

    void addMediaSource(MediaSource mediaSource);

    void addMediaSources(int i3, List<MediaSource> list);

    void addMediaSources(List<MediaSource> list);

    void clearAuxEffectInfo();

    void clearCameraMotionListener(CameraMotionListener cameraMotionListener);

    void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    PlayerMessage createMessage(PlayerMessage.Target target);

    boolean experimentalIsSleepingForOffload();

    void experimentalSetOffloadSchedulingEnabled(boolean z2);

    AnalyticsCollector getAnalyticsCollector();

    @Deprecated
    @Nullable
    AudioComponent getAudioComponent();

    @Nullable
    DecoderCounters getAudioDecoderCounters();

    @Nullable
    Format getAudioFormat();

    int getAudioSessionId();

    Clock getClock();

    @Deprecated
    TrackGroupArray getCurrentTrackGroups();

    @Deprecated
    TrackSelectionArray getCurrentTrackSelections();

    @Deprecated
    @Nullable
    DeviceComponent getDeviceComponent();

    boolean getPauseAtEndOfMediaItems();

    Looper getPlaybackLooper();

    @Nullable
    ExoPlaybackException getPlayerError();

    Renderer getRenderer(int i3);

    int getRendererCount();

    int getRendererType(int i3);

    SeekParameters getSeekParameters();

    boolean getSkipSilenceEnabled();

    @Deprecated
    @Nullable
    TextComponent getTextComponent();

    @Nullable
    TrackSelector getTrackSelector();

    int getVideoChangeFrameRateStrategy();

    @Deprecated
    @Nullable
    VideoComponent getVideoComponent();

    @Nullable
    DecoderCounters getVideoDecoderCounters();

    @Nullable
    Format getVideoFormat();

    int getVideoScalingMode();

    @Deprecated
    void prepare(MediaSource mediaSource);

    @Deprecated
    void prepare(MediaSource mediaSource, boolean z2, boolean z3);

    void removeAnalyticsListener(AnalyticsListener analyticsListener);

    void removeAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    @Deprecated
    void retry();

    void setAudioAttributes(AudioAttributes audioAttributes, boolean z2);

    void setAudioSessionId(int i3);

    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    void setCameraMotionListener(CameraMotionListener cameraMotionListener);

    void setForegroundMode(boolean z2);

    void setHandleAudioBecomingNoisy(boolean z2);

    @Deprecated
    void setHandleWakeLock(boolean z2);

    void setMediaSource(MediaSource mediaSource);

    void setMediaSource(MediaSource mediaSource, long j2);

    void setMediaSource(MediaSource mediaSource, boolean z2);

    void setMediaSources(List<MediaSource> list);

    void setMediaSources(List<MediaSource> list, int i3, long j2);

    void setMediaSources(List<MediaSource> list, boolean z2);

    void setPauseAtEndOfMediaItems(boolean z2);

    void setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager);

    void setSeekParameters(@Nullable SeekParameters seekParameters);

    void setShuffleOrder(ShuffleOrder shuffleOrder);

    void setSkipSilenceEnabled(boolean z2);

    void setVideoChangeFrameRateStrategy(int i3);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    void setVideoScalingMode(int i3);

    void setWakeMode(int i3);
}
