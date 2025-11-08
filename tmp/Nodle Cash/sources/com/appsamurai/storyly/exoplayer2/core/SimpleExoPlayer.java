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
import com.appsamurai.storyly.exoplayer2.common.BasePlayer;
import com.appsamurai.storyly.exoplayer2.common.DeviceInfo;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.MediaMetadata;
import com.appsamurai.storyly.exoplayer2.common.PlaybackParameters;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.Tracks;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.audio.AuxEffectInfo;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionParameters;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable;
import com.appsamurai.storyly.exoplayer2.common.util.PriorityTaskManager;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.exoplayer2.core.PlayerMessage;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsCollector;
import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.source.DefaultMediaSourceFactory;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectionArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import com.appsamurai.storyly.exoplayer2.core.video.VideoFrameMetadataListener;
import com.appsamurai.storyly.exoplayer2.core.video.spherical.CameraMotionListener;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import java.util.List;

@Deprecated
public class SimpleExoPlayer extends BasePlayer implements ExoPlayer, ExoPlayer.AudioComponent, ExoPlayer.VideoComponent, ExoPlayer.TextComponent, ExoPlayer.DeviceComponent {
    private final ConditionVariable constructorFinished;
    private final ExoPlayerImpl player;

    @Deprecated
    public SimpleExoPlayer(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, MediaSource.Factory factory, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector, boolean z2, Clock clock, Looper looper) {
        this(new ExoPlayer.Builder(context, renderersFactory, factory, trackSelector, loadControl, bandwidthMeter, analyticsCollector).setUseLazyPreparation(z2).setClock(clock).setLooper(looper));
    }

    private void blockUntilConstructorFinished() {
        this.constructorFinished.blockUninterruptible();
    }

    public void addAnalyticsListener(AnalyticsListener analyticsListener) {
        blockUntilConstructorFinished();
        this.player.addAnalyticsListener(analyticsListener);
    }

    public void addAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        blockUntilConstructorFinished();
        this.player.addAudioOffloadListener(audioOffloadListener);
    }

    public void addListener(Player.Listener listener) {
        blockUntilConstructorFinished();
        this.player.addListener(listener);
    }

    public void addMediaItems(int i3, List<MediaItem> list) {
        blockUntilConstructorFinished();
        this.player.addMediaItems(i3, list);
    }

    public void addMediaSource(MediaSource mediaSource) {
        blockUntilConstructorFinished();
        this.player.addMediaSource(mediaSource);
    }

    public void addMediaSources(List<MediaSource> list) {
        blockUntilConstructorFinished();
        this.player.addMediaSources(list);
    }

    public void clearAuxEffectInfo() {
        blockUntilConstructorFinished();
        this.player.clearAuxEffectInfo();
    }

    public void clearCameraMotionListener(CameraMotionListener cameraMotionListener) {
        blockUntilConstructorFinished();
        this.player.clearCameraMotionListener(cameraMotionListener);
    }

    public void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        blockUntilConstructorFinished();
        this.player.clearVideoFrameMetadataListener(videoFrameMetadataListener);
    }

    public void clearVideoSurface() {
        blockUntilConstructorFinished();
        this.player.clearVideoSurface();
    }

    public void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {
        blockUntilConstructorFinished();
        this.player.clearVideoSurfaceHolder(surfaceHolder);
    }

    public void clearVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        blockUntilConstructorFinished();
        this.player.clearVideoSurfaceView(surfaceView);
    }

    public void clearVideoTextureView(@Nullable TextureView textureView) {
        blockUntilConstructorFinished();
        this.player.clearVideoTextureView(textureView);
    }

    public PlayerMessage createMessage(PlayerMessage.Target target) {
        blockUntilConstructorFinished();
        return this.player.createMessage(target);
    }

    public void decreaseDeviceVolume() {
        blockUntilConstructorFinished();
        this.player.decreaseDeviceVolume();
    }

    public boolean experimentalIsSleepingForOffload() {
        blockUntilConstructorFinished();
        return this.player.experimentalIsSleepingForOffload();
    }

    public void experimentalSetOffloadSchedulingEnabled(boolean z2) {
        blockUntilConstructorFinished();
        this.player.experimentalSetOffloadSchedulingEnabled(z2);
    }

    public AnalyticsCollector getAnalyticsCollector() {
        blockUntilConstructorFinished();
        return this.player.getAnalyticsCollector();
    }

    public Looper getApplicationLooper() {
        blockUntilConstructorFinished();
        return this.player.getApplicationLooper();
    }

    public AudioAttributes getAudioAttributes() {
        blockUntilConstructorFinished();
        return this.player.getAudioAttributes();
    }

    @Deprecated
    @Nullable
    public ExoPlayer.AudioComponent getAudioComponent() {
        return this;
    }

    @Nullable
    public DecoderCounters getAudioDecoderCounters() {
        blockUntilConstructorFinished();
        return this.player.getAudioDecoderCounters();
    }

    @Nullable
    public Format getAudioFormat() {
        blockUntilConstructorFinished();
        return this.player.getAudioFormat();
    }

    public int getAudioSessionId() {
        blockUntilConstructorFinished();
        return this.player.getAudioSessionId();
    }

    public Player.Commands getAvailableCommands() {
        blockUntilConstructorFinished();
        return this.player.getAvailableCommands();
    }

    public long getBufferedPosition() {
        blockUntilConstructorFinished();
        return this.player.getBufferedPosition();
    }

    public Clock getClock() {
        blockUntilConstructorFinished();
        return this.player.getClock();
    }

    public long getContentBufferedPosition() {
        blockUntilConstructorFinished();
        return this.player.getContentBufferedPosition();
    }

    public long getContentPosition() {
        blockUntilConstructorFinished();
        return this.player.getContentPosition();
    }

    public int getCurrentAdGroupIndex() {
        blockUntilConstructorFinished();
        return this.player.getCurrentAdGroupIndex();
    }

    public int getCurrentAdIndexInAdGroup() {
        blockUntilConstructorFinished();
        return this.player.getCurrentAdIndexInAdGroup();
    }

    public CueGroup getCurrentCues() {
        blockUntilConstructorFinished();
        return this.player.getCurrentCues();
    }

    public int getCurrentMediaItemIndex() {
        blockUntilConstructorFinished();
        return this.player.getCurrentMediaItemIndex();
    }

    public int getCurrentPeriodIndex() {
        blockUntilConstructorFinished();
        return this.player.getCurrentPeriodIndex();
    }

    public long getCurrentPosition() {
        blockUntilConstructorFinished();
        return this.player.getCurrentPosition();
    }

    public Timeline getCurrentTimeline() {
        blockUntilConstructorFinished();
        return this.player.getCurrentTimeline();
    }

    @Deprecated
    public TrackGroupArray getCurrentTrackGroups() {
        blockUntilConstructorFinished();
        return this.player.getCurrentTrackGroups();
    }

    @Deprecated
    public TrackSelectionArray getCurrentTrackSelections() {
        blockUntilConstructorFinished();
        return this.player.getCurrentTrackSelections();
    }

    public Tracks getCurrentTracks() {
        blockUntilConstructorFinished();
        return this.player.getCurrentTracks();
    }

    @Deprecated
    @Nullable
    public ExoPlayer.DeviceComponent getDeviceComponent() {
        return this;
    }

    public DeviceInfo getDeviceInfo() {
        blockUntilConstructorFinished();
        return this.player.getDeviceInfo();
    }

    public int getDeviceVolume() {
        blockUntilConstructorFinished();
        return this.player.getDeviceVolume();
    }

    public long getDuration() {
        blockUntilConstructorFinished();
        return this.player.getDuration();
    }

    public long getMaxSeekToPreviousPosition() {
        blockUntilConstructorFinished();
        return this.player.getMaxSeekToPreviousPosition();
    }

    public MediaMetadata getMediaMetadata() {
        blockUntilConstructorFinished();
        return this.player.getMediaMetadata();
    }

    public boolean getPauseAtEndOfMediaItems() {
        blockUntilConstructorFinished();
        return this.player.getPauseAtEndOfMediaItems();
    }

    public boolean getPlayWhenReady() {
        blockUntilConstructorFinished();
        return this.player.getPlayWhenReady();
    }

    public Looper getPlaybackLooper() {
        blockUntilConstructorFinished();
        return this.player.getPlaybackLooper();
    }

    public PlaybackParameters getPlaybackParameters() {
        blockUntilConstructorFinished();
        return this.player.getPlaybackParameters();
    }

    public int getPlaybackState() {
        blockUntilConstructorFinished();
        return this.player.getPlaybackState();
    }

    public int getPlaybackSuppressionReason() {
        blockUntilConstructorFinished();
        return this.player.getPlaybackSuppressionReason();
    }

    public MediaMetadata getPlaylistMetadata() {
        blockUntilConstructorFinished();
        return this.player.getPlaylistMetadata();
    }

    public Renderer getRenderer(int i3) {
        blockUntilConstructorFinished();
        return this.player.getRenderer(i3);
    }

    public int getRendererCount() {
        blockUntilConstructorFinished();
        return this.player.getRendererCount();
    }

    public int getRendererType(int i3) {
        blockUntilConstructorFinished();
        return this.player.getRendererType(i3);
    }

    public int getRepeatMode() {
        blockUntilConstructorFinished();
        return this.player.getRepeatMode();
    }

    public long getSeekBackIncrement() {
        blockUntilConstructorFinished();
        return this.player.getSeekBackIncrement();
    }

    public long getSeekForwardIncrement() {
        blockUntilConstructorFinished();
        return this.player.getSeekForwardIncrement();
    }

    public SeekParameters getSeekParameters() {
        blockUntilConstructorFinished();
        return this.player.getSeekParameters();
    }

    public boolean getShuffleModeEnabled() {
        blockUntilConstructorFinished();
        return this.player.getShuffleModeEnabled();
    }

    public boolean getSkipSilenceEnabled() {
        blockUntilConstructorFinished();
        return this.player.getSkipSilenceEnabled();
    }

    @Deprecated
    @Nullable
    public ExoPlayer.TextComponent getTextComponent() {
        return this;
    }

    public long getTotalBufferedDuration() {
        blockUntilConstructorFinished();
        return this.player.getTotalBufferedDuration();
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        blockUntilConstructorFinished();
        return this.player.getTrackSelectionParameters();
    }

    public TrackSelector getTrackSelector() {
        blockUntilConstructorFinished();
        return this.player.getTrackSelector();
    }

    public int getVideoChangeFrameRateStrategy() {
        blockUntilConstructorFinished();
        return this.player.getVideoChangeFrameRateStrategy();
    }

    @Deprecated
    @Nullable
    public ExoPlayer.VideoComponent getVideoComponent() {
        return this;
    }

    @Nullable
    public DecoderCounters getVideoDecoderCounters() {
        blockUntilConstructorFinished();
        return this.player.getVideoDecoderCounters();
    }

    @Nullable
    public Format getVideoFormat() {
        blockUntilConstructorFinished();
        return this.player.getVideoFormat();
    }

    public int getVideoScalingMode() {
        blockUntilConstructorFinished();
        return this.player.getVideoScalingMode();
    }

    public VideoSize getVideoSize() {
        blockUntilConstructorFinished();
        return this.player.getVideoSize();
    }

    public float getVolume() {
        blockUntilConstructorFinished();
        return this.player.getVolume();
    }

    public void increaseDeviceVolume() {
        blockUntilConstructorFinished();
        this.player.increaseDeviceVolume();
    }

    public boolean isDeviceMuted() {
        blockUntilConstructorFinished();
        return this.player.isDeviceMuted();
    }

    public boolean isLoading() {
        blockUntilConstructorFinished();
        return this.player.isLoading();
    }

    public boolean isPlayingAd() {
        blockUntilConstructorFinished();
        return this.player.isPlayingAd();
    }

    public void moveMediaItems(int i3, int i4, int i5) {
        blockUntilConstructorFinished();
        this.player.moveMediaItems(i3, i4, i5);
    }

    public void prepare() {
        blockUntilConstructorFinished();
        this.player.prepare();
    }

    public void release() {
        blockUntilConstructorFinished();
        this.player.release();
    }

    public void removeAnalyticsListener(AnalyticsListener analyticsListener) {
        blockUntilConstructorFinished();
        this.player.removeAnalyticsListener(analyticsListener);
    }

    public void removeAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        blockUntilConstructorFinished();
        this.player.removeAudioOffloadListener(audioOffloadListener);
    }

    public void removeListener(Player.Listener listener) {
        blockUntilConstructorFinished();
        this.player.removeListener(listener);
    }

    public void removeMediaItems(int i3, int i4) {
        blockUntilConstructorFinished();
        this.player.removeMediaItems(i3, i4);
    }

    @Deprecated
    public void retry() {
        blockUntilConstructorFinished();
        this.player.retry();
    }

    public void seekTo(int i3, long j2) {
        blockUntilConstructorFinished();
        this.player.seekTo(i3, j2);
    }

    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z2) {
        blockUntilConstructorFinished();
        this.player.setAudioAttributes(audioAttributes, z2);
    }

    public void setAudioSessionId(int i3) {
        blockUntilConstructorFinished();
        this.player.setAudioSessionId(i3);
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        blockUntilConstructorFinished();
        this.player.setAuxEffectInfo(auxEffectInfo);
    }

    public void setCameraMotionListener(CameraMotionListener cameraMotionListener) {
        blockUntilConstructorFinished();
        this.player.setCameraMotionListener(cameraMotionListener);
    }

    public void setDeviceMuted(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setDeviceMuted(z2);
    }

    public void setDeviceVolume(int i3) {
        blockUntilConstructorFinished();
        this.player.setDeviceVolume(i3);
    }

    public void setForegroundMode(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setForegroundMode(z2);
    }

    public void setHandleAudioBecomingNoisy(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setHandleAudioBecomingNoisy(z2);
    }

    @Deprecated
    public void setHandleWakeLock(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setHandleWakeLock(z2);
    }

    public void setMediaItems(List<MediaItem> list, boolean z2) {
        blockUntilConstructorFinished();
        this.player.setMediaItems(list, z2);
    }

    public void setMediaSource(MediaSource mediaSource) {
        blockUntilConstructorFinished();
        this.player.setMediaSource(mediaSource);
    }

    public void setMediaSources(List<MediaSource> list) {
        blockUntilConstructorFinished();
        this.player.setMediaSources(list);
    }

    public void setPauseAtEndOfMediaItems(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setPauseAtEndOfMediaItems(z2);
    }

    public void setPlayWhenReady(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setPlayWhenReady(z2);
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        blockUntilConstructorFinished();
        this.player.setPlaybackParameters(playbackParameters);
    }

    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        blockUntilConstructorFinished();
        this.player.setPlaylistMetadata(mediaMetadata);
    }

    public void setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager) {
        blockUntilConstructorFinished();
        this.player.setPriorityTaskManager(priorityTaskManager);
    }

    public void setRepeatMode(int i3) {
        blockUntilConstructorFinished();
        this.player.setRepeatMode(i3);
    }

    public void setSeekParameters(@Nullable SeekParameters seekParameters) {
        blockUntilConstructorFinished();
        this.player.setSeekParameters(seekParameters);
    }

    public void setShuffleModeEnabled(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setShuffleModeEnabled(z2);
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder) {
        blockUntilConstructorFinished();
        this.player.setShuffleOrder(shuffleOrder);
    }

    public void setSkipSilenceEnabled(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setSkipSilenceEnabled(z2);
    }

    public void setThrowsWhenUsingWrongThread(boolean z2) {
        blockUntilConstructorFinished();
        this.player.setThrowsWhenUsingWrongThread(z2);
    }

    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        blockUntilConstructorFinished();
        this.player.setTrackSelectionParameters(trackSelectionParameters);
    }

    public void setVideoChangeFrameRateStrategy(int i3) {
        blockUntilConstructorFinished();
        this.player.setVideoChangeFrameRateStrategy(i3);
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        blockUntilConstructorFinished();
        this.player.setVideoFrameMetadataListener(videoFrameMetadataListener);
    }

    public void setVideoScalingMode(int i3) {
        blockUntilConstructorFinished();
        this.player.setVideoScalingMode(i3);
    }

    public void setVideoSurface(@Nullable Surface surface) {
        blockUntilConstructorFinished();
        this.player.setVideoSurface(surface);
    }

    public void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {
        blockUntilConstructorFinished();
        this.player.setVideoSurfaceHolder(surfaceHolder);
    }

    public void setVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        blockUntilConstructorFinished();
        this.player.setVideoSurfaceView(surfaceView);
    }

    public void setVideoTextureView(@Nullable TextureView textureView) {
        blockUntilConstructorFinished();
        this.player.setVideoTextureView(textureView);
    }

    public void setVolume(float f2) {
        blockUntilConstructorFinished();
        this.player.setVolume(f2);
    }

    public void setWakeMode(int i3) {
        blockUntilConstructorFinished();
        this.player.setWakeMode(i3);
    }

    public void stop() {
        blockUntilConstructorFinished();
        this.player.stop();
    }

    @Deprecated
    public static final class Builder {
        /* access modifiers changed from: private */
        public final ExoPlayer.Builder wrappedBuilder;

        @Deprecated
        public Builder(Context context) {
            this.wrappedBuilder = new ExoPlayer.Builder(context);
        }

        @Deprecated
        public SimpleExoPlayer build() {
            return this.wrappedBuilder.buildSimpleExoPlayer();
        }

        @Deprecated
        public Builder experimentalSetForegroundModeTimeoutMs(long j2) {
            this.wrappedBuilder.experimentalSetForegroundModeTimeoutMs(j2);
            return this;
        }

        @Deprecated
        public Builder setAnalyticsCollector(AnalyticsCollector analyticsCollector) {
            this.wrappedBuilder.setAnalyticsCollector(analyticsCollector);
            return this;
        }

        @Deprecated
        public Builder setAudioAttributes(AudioAttributes audioAttributes, boolean z2) {
            this.wrappedBuilder.setAudioAttributes(audioAttributes, z2);
            return this;
        }

        @Deprecated
        public Builder setBandwidthMeter(BandwidthMeter bandwidthMeter) {
            this.wrappedBuilder.setBandwidthMeter(bandwidthMeter);
            return this;
        }

        @VisibleForTesting
        @Deprecated
        public Builder setClock(Clock clock) {
            this.wrappedBuilder.setClock(clock);
            return this;
        }

        @Deprecated
        public Builder setDetachSurfaceTimeoutMs(long j2) {
            this.wrappedBuilder.setDetachSurfaceTimeoutMs(j2);
            return this;
        }

        @Deprecated
        public Builder setHandleAudioBecomingNoisy(boolean z2) {
            this.wrappedBuilder.setHandleAudioBecomingNoisy(z2);
            return this;
        }

        @Deprecated
        public Builder setLivePlaybackSpeedControl(LivePlaybackSpeedControl livePlaybackSpeedControl) {
            this.wrappedBuilder.setLivePlaybackSpeedControl(livePlaybackSpeedControl);
            return this;
        }

        @Deprecated
        public Builder setLoadControl(LoadControl loadControl) {
            this.wrappedBuilder.setLoadControl(loadControl);
            return this;
        }

        @Deprecated
        public Builder setLooper(Looper looper) {
            this.wrappedBuilder.setLooper(looper);
            return this;
        }

        @Deprecated
        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            this.wrappedBuilder.setMediaSourceFactory(factory);
            return this;
        }

        @Deprecated
        public Builder setPauseAtEndOfMediaItems(boolean z2) {
            this.wrappedBuilder.setPauseAtEndOfMediaItems(z2);
            return this;
        }

        @Deprecated
        public Builder setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager) {
            this.wrappedBuilder.setPriorityTaskManager(priorityTaskManager);
            return this;
        }

        @Deprecated
        public Builder setReleaseTimeoutMs(long j2) {
            this.wrappedBuilder.setReleaseTimeoutMs(j2);
            return this;
        }

        @Deprecated
        public Builder setSeekBackIncrementMs(@IntRange(from = 1) long j2) {
            this.wrappedBuilder.setSeekBackIncrementMs(j2);
            return this;
        }

        @Deprecated
        public Builder setSeekForwardIncrementMs(@IntRange(from = 1) long j2) {
            this.wrappedBuilder.setSeekForwardIncrementMs(j2);
            return this;
        }

        @Deprecated
        public Builder setSeekParameters(SeekParameters seekParameters) {
            this.wrappedBuilder.setSeekParameters(seekParameters);
            return this;
        }

        @Deprecated
        public Builder setSkipSilenceEnabled(boolean z2) {
            this.wrappedBuilder.setSkipSilenceEnabled(z2);
            return this;
        }

        @Deprecated
        public Builder setTrackSelector(TrackSelector trackSelector) {
            this.wrappedBuilder.setTrackSelector(trackSelector);
            return this;
        }

        @Deprecated
        public Builder setUseLazyPreparation(boolean z2) {
            this.wrappedBuilder.setUseLazyPreparation(z2);
            return this;
        }

        @Deprecated
        public Builder setVideoChangeFrameRateStrategy(int i3) {
            this.wrappedBuilder.setVideoChangeFrameRateStrategy(i3);
            return this;
        }

        @Deprecated
        public Builder setVideoScalingMode(int i3) {
            this.wrappedBuilder.setVideoScalingMode(i3);
            return this;
        }

        @Deprecated
        public Builder setWakeMode(int i3) {
            this.wrappedBuilder.setWakeMode(i3);
            return this;
        }

        @Deprecated
        public Builder(Context context, RenderersFactory renderersFactory) {
            this.wrappedBuilder = new ExoPlayer.Builder(context, renderersFactory);
        }

        @Deprecated
        public Builder(Context context, ExtractorsFactory extractorsFactory) {
            this.wrappedBuilder = new ExoPlayer.Builder(context, (MediaSource.Factory) new DefaultMediaSourceFactory(context, extractorsFactory));
        }

        @Deprecated
        public Builder(Context context, RenderersFactory renderersFactory, ExtractorsFactory extractorsFactory) {
            this.wrappedBuilder = new ExoPlayer.Builder(context, renderersFactory, (MediaSource.Factory) new DefaultMediaSourceFactory(context, extractorsFactory));
        }

        @Deprecated
        public Builder(Context context, RenderersFactory renderersFactory, TrackSelector trackSelector, MediaSource.Factory factory, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector) {
            this.wrappedBuilder = new ExoPlayer.Builder(context, renderersFactory, factory, trackSelector, loadControl, bandwidthMeter, analyticsCollector);
        }
    }

    @Nullable
    public ExoPlaybackException getPlayerError() {
        blockUntilConstructorFinished();
        return this.player.getPlayerError();
    }

    public void addMediaSource(int i3, MediaSource mediaSource) {
        blockUntilConstructorFinished();
        this.player.addMediaSource(i3, mediaSource);
    }

    public void addMediaSources(int i3, List<MediaSource> list) {
        blockUntilConstructorFinished();
        this.player.addMediaSources(i3, list);
    }

    public void clearVideoSurface(@Nullable Surface surface) {
        blockUntilConstructorFinished();
        this.player.clearVideoSurface(surface);
    }

    @Deprecated
    public void prepare(MediaSource mediaSource) {
        blockUntilConstructorFinished();
        this.player.prepare(mediaSource);
    }

    public void setMediaItems(List<MediaItem> list, int i3, long j2) {
        blockUntilConstructorFinished();
        this.player.setMediaItems(list, i3, j2);
    }

    public void setMediaSource(MediaSource mediaSource, boolean z2) {
        blockUntilConstructorFinished();
        this.player.setMediaSource(mediaSource, z2);
    }

    public void setMediaSources(List<MediaSource> list, boolean z2) {
        blockUntilConstructorFinished();
        this.player.setMediaSources(list, z2);
    }

    @Deprecated
    public void stop(boolean z2) {
        blockUntilConstructorFinished();
        this.player.stop(z2);
    }

    @Deprecated
    public void prepare(MediaSource mediaSource, boolean z2, boolean z3) {
        blockUntilConstructorFinished();
        this.player.prepare(mediaSource, z2, z3);
    }

    public void setMediaSource(MediaSource mediaSource, long j2) {
        blockUntilConstructorFinished();
        this.player.setMediaSource(mediaSource, j2);
    }

    public void setMediaSources(List<MediaSource> list, int i3, long j2) {
        blockUntilConstructorFinished();
        this.player.setMediaSources(list, i3, j2);
    }

    public SimpleExoPlayer(Builder builder) {
        this(builder.wrappedBuilder);
    }

    public SimpleExoPlayer(ExoPlayer.Builder builder) {
        ConditionVariable conditionVariable = new ConditionVariable();
        this.constructorFinished = conditionVariable;
        try {
            this.player = new ExoPlayerImpl(builder, this);
            conditionVariable.open();
        } catch (Throwable th) {
            this.constructorFinished.open();
            throw th;
        }
    }
}
