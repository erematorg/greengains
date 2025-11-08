package com.appsamurai.storyly.exoplayer2.common;

import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.audio.AudioAttributes;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import com.appsamurai.storyly.exoplayer2.common.trackselection.TrackSelectionParameters;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import java.util.List;

public class ForwardingPlayer implements Player {
    private final Player player;

    public static final class ForwardingListener implements Player.Listener {
        private final ForwardingPlayer forwardingPlayer;
        private final Player.Listener listener;

        public ForwardingListener(ForwardingPlayer forwardingPlayer2, Player.Listener listener2) {
            this.forwardingPlayer = forwardingPlayer2;
            this.listener = listener2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForwardingListener)) {
                return false;
            }
            ForwardingListener forwardingListener = (ForwardingListener) obj;
            if (!this.forwardingPlayer.equals(forwardingListener.forwardingPlayer)) {
                return false;
            }
            return this.listener.equals(forwardingListener.listener);
        }

        public int hashCode() {
            return this.listener.hashCode() + (this.forwardingPlayer.hashCode() * 31);
        }

        public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            this.listener.onAudioAttributesChanged(audioAttributes);
        }

        public void onAudioSessionIdChanged(int i3) {
            this.listener.onAudioSessionIdChanged(i3);
        }

        public void onAvailableCommandsChanged(Player.Commands commands) {
            this.listener.onAvailableCommandsChanged(commands);
        }

        public void onCues(List<Cue> list) {
            this.listener.onCues(list);
        }

        public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            this.listener.onDeviceInfoChanged(deviceInfo);
        }

        public void onDeviceVolumeChanged(int i3, boolean z2) {
            this.listener.onDeviceVolumeChanged(i3, z2);
        }

        public void onEvents(Player player, Player.Events events) {
            this.listener.onEvents(this.forwardingPlayer, events);
        }

        public void onIsLoadingChanged(boolean z2) {
            this.listener.onIsLoadingChanged(z2);
        }

        public void onIsPlayingChanged(boolean z2) {
            this.listener.onIsPlayingChanged(z2);
        }

        public void onLoadingChanged(boolean z2) {
            this.listener.onIsLoadingChanged(z2);
        }

        public void onMaxSeekToPreviousPositionChanged(long j2) {
            this.listener.onMaxSeekToPreviousPositionChanged(j2);
        }

        public void onMediaItemTransition(@Nullable MediaItem mediaItem, int i3) {
            this.listener.onMediaItemTransition(mediaItem, i3);
        }

        public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            this.listener.onMediaMetadataChanged(mediaMetadata);
        }

        public void onMetadata(Metadata metadata) {
            this.listener.onMetadata(metadata);
        }

        public void onPlayWhenReadyChanged(boolean z2, int i3) {
            this.listener.onPlayWhenReadyChanged(z2, i3);
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            this.listener.onPlaybackParametersChanged(playbackParameters);
        }

        public void onPlaybackStateChanged(int i3) {
            this.listener.onPlaybackStateChanged(i3);
        }

        public void onPlaybackSuppressionReasonChanged(int i3) {
            this.listener.onPlaybackSuppressionReasonChanged(i3);
        }

        public void onPlayerError(PlaybackException playbackException) {
            this.listener.onPlayerError(playbackException);
        }

        public void onPlayerErrorChanged(@Nullable PlaybackException playbackException) {
            this.listener.onPlayerErrorChanged(playbackException);
        }

        public void onPlayerStateChanged(boolean z2, int i3) {
            this.listener.onPlayerStateChanged(z2, i3);
        }

        public void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            this.listener.onPlaylistMetadataChanged(mediaMetadata);
        }

        public void onPositionDiscontinuity(int i3) {
            this.listener.onPositionDiscontinuity(i3);
        }

        public void onRenderedFirstFrame() {
            this.listener.onRenderedFirstFrame();
        }

        public void onRepeatModeChanged(int i3) {
            this.listener.onRepeatModeChanged(i3);
        }

        public void onSeekBackIncrementChanged(long j2) {
            this.listener.onSeekBackIncrementChanged(j2);
        }

        public void onSeekForwardIncrementChanged(long j2) {
            this.listener.onSeekForwardIncrementChanged(j2);
        }

        public void onSeekProcessed() {
            this.listener.onSeekProcessed();
        }

        public void onShuffleModeEnabledChanged(boolean z2) {
            this.listener.onShuffleModeEnabledChanged(z2);
        }

        public void onSkipSilenceEnabledChanged(boolean z2) {
            this.listener.onSkipSilenceEnabledChanged(z2);
        }

        public void onSurfaceSizeChanged(int i3, int i4) {
            this.listener.onSurfaceSizeChanged(i3, i4);
        }

        public void onTimelineChanged(Timeline timeline, int i3) {
            this.listener.onTimelineChanged(timeline, i3);
        }

        public void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            this.listener.onTrackSelectionParametersChanged(trackSelectionParameters);
        }

        public void onTracksChanged(Tracks tracks) {
            this.listener.onTracksChanged(tracks);
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            this.listener.onVideoSizeChanged(videoSize);
        }

        public void onVolumeChanged(float f2) {
            this.listener.onVolumeChanged(f2);
        }

        public void onCues(CueGroup cueGroup) {
            this.listener.onCues(cueGroup);
        }

        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i3) {
            this.listener.onPositionDiscontinuity(positionInfo, positionInfo2, i3);
        }
    }

    public ForwardingPlayer(Player player2) {
        this.player = player2;
    }

    public void addListener(Player.Listener listener) {
        this.player.addListener(new ForwardingListener(this, listener));
    }

    public void addMediaItem(MediaItem mediaItem) {
        this.player.addMediaItem(mediaItem);
    }

    public void addMediaItems(List<MediaItem> list) {
        this.player.addMediaItems(list);
    }

    public boolean canAdvertiseSession() {
        return this.player.canAdvertiseSession();
    }

    public void clearMediaItems() {
        this.player.clearMediaItems();
    }

    public void clearVideoSurface() {
        this.player.clearVideoSurface();
    }

    public void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {
        this.player.clearVideoSurfaceHolder(surfaceHolder);
    }

    public void clearVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        this.player.clearVideoSurfaceView(surfaceView);
    }

    public void clearVideoTextureView(@Nullable TextureView textureView) {
        this.player.clearVideoTextureView(textureView);
    }

    public void decreaseDeviceVolume() {
        this.player.decreaseDeviceVolume();
    }

    public Looper getApplicationLooper() {
        return this.player.getApplicationLooper();
    }

    public AudioAttributes getAudioAttributes() {
        return this.player.getAudioAttributes();
    }

    public Player.Commands getAvailableCommands() {
        return this.player.getAvailableCommands();
    }

    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    public long getBufferedPosition() {
        return this.player.getBufferedPosition();
    }

    public long getContentBufferedPosition() {
        return this.player.getContentBufferedPosition();
    }

    public long getContentDuration() {
        return this.player.getContentDuration();
    }

    public long getContentPosition() {
        return this.player.getContentPosition();
    }

    public int getCurrentAdGroupIndex() {
        return this.player.getCurrentAdGroupIndex();
    }

    public int getCurrentAdIndexInAdGroup() {
        return this.player.getCurrentAdIndexInAdGroup();
    }

    public CueGroup getCurrentCues() {
        return this.player.getCurrentCues();
    }

    public long getCurrentLiveOffset() {
        return this.player.getCurrentLiveOffset();
    }

    @Nullable
    public Object getCurrentManifest() {
        return this.player.getCurrentManifest();
    }

    @Nullable
    public MediaItem getCurrentMediaItem() {
        return this.player.getCurrentMediaItem();
    }

    public int getCurrentMediaItemIndex() {
        return this.player.getCurrentMediaItemIndex();
    }

    public int getCurrentPeriodIndex() {
        return this.player.getCurrentPeriodIndex();
    }

    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    public Timeline getCurrentTimeline() {
        return this.player.getCurrentTimeline();
    }

    public Tracks getCurrentTracks() {
        return this.player.getCurrentTracks();
    }

    @Deprecated
    public int getCurrentWindowIndex() {
        return this.player.getCurrentWindowIndex();
    }

    public DeviceInfo getDeviceInfo() {
        return this.player.getDeviceInfo();
    }

    public int getDeviceVolume() {
        return this.player.getDeviceVolume();
    }

    public long getDuration() {
        return this.player.getDuration();
    }

    public long getMaxSeekToPreviousPosition() {
        return this.player.getMaxSeekToPreviousPosition();
    }

    public MediaItem getMediaItemAt(int i3) {
        return this.player.getMediaItemAt(i3);
    }

    public int getMediaItemCount() {
        return this.player.getMediaItemCount();
    }

    public MediaMetadata getMediaMetadata() {
        return this.player.getMediaMetadata();
    }

    public int getNextMediaItemIndex() {
        return this.player.getNextMediaItemIndex();
    }

    @Deprecated
    public int getNextWindowIndex() {
        return this.player.getNextWindowIndex();
    }

    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.player.getPlaybackParameters();
    }

    public int getPlaybackState() {
        return this.player.getPlaybackState();
    }

    public int getPlaybackSuppressionReason() {
        return this.player.getPlaybackSuppressionReason();
    }

    @Nullable
    public PlaybackException getPlayerError() {
        return this.player.getPlayerError();
    }

    public MediaMetadata getPlaylistMetadata() {
        return this.player.getPlaylistMetadata();
    }

    public int getPreviousMediaItemIndex() {
        return this.player.getPreviousMediaItemIndex();
    }

    @Deprecated
    public int getPreviousWindowIndex() {
        return this.player.getPreviousWindowIndex();
    }

    public int getRepeatMode() {
        return this.player.getRepeatMode();
    }

    public long getSeekBackIncrement() {
        return this.player.getSeekBackIncrement();
    }

    public long getSeekForwardIncrement() {
        return this.player.getSeekForwardIncrement();
    }

    public boolean getShuffleModeEnabled() {
        return this.player.getShuffleModeEnabled();
    }

    public long getTotalBufferedDuration() {
        return this.player.getTotalBufferedDuration();
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        return this.player.getTrackSelectionParameters();
    }

    public VideoSize getVideoSize() {
        return this.player.getVideoSize();
    }

    public float getVolume() {
        return this.player.getVolume();
    }

    public Player getWrappedPlayer() {
        return this.player;
    }

    @Deprecated
    public boolean hasNext() {
        return this.player.hasNext();
    }

    public boolean hasNextMediaItem() {
        return this.player.hasNextMediaItem();
    }

    @Deprecated
    public boolean hasNextWindow() {
        return this.player.hasNextWindow();
    }

    @Deprecated
    public boolean hasPrevious() {
        return this.player.hasPrevious();
    }

    public boolean hasPreviousMediaItem() {
        return this.player.hasPreviousMediaItem();
    }

    @Deprecated
    public boolean hasPreviousWindow() {
        return this.player.hasPreviousWindow();
    }

    public void increaseDeviceVolume() {
        this.player.increaseDeviceVolume();
    }

    public boolean isCommandAvailable(int i3) {
        return this.player.isCommandAvailable(i3);
    }

    public boolean isCurrentMediaItemDynamic() {
        return this.player.isCurrentMediaItemDynamic();
    }

    public boolean isCurrentMediaItemLive() {
        return this.player.isCurrentMediaItemLive();
    }

    public boolean isCurrentMediaItemSeekable() {
        return this.player.isCurrentMediaItemSeekable();
    }

    @Deprecated
    public boolean isCurrentWindowDynamic() {
        return this.player.isCurrentWindowDynamic();
    }

    @Deprecated
    public boolean isCurrentWindowLive() {
        return this.player.isCurrentWindowLive();
    }

    @Deprecated
    public boolean isCurrentWindowSeekable() {
        return this.player.isCurrentWindowSeekable();
    }

    public boolean isDeviceMuted() {
        return this.player.isDeviceMuted();
    }

    public boolean isLoading() {
        return this.player.isLoading();
    }

    public boolean isPlaying() {
        return this.player.isPlaying();
    }

    public boolean isPlayingAd() {
        return this.player.isPlayingAd();
    }

    public void moveMediaItem(int i3, int i4) {
        this.player.moveMediaItem(i3, i4);
    }

    public void moveMediaItems(int i3, int i4, int i5) {
        this.player.moveMediaItems(i3, i4, i5);
    }

    @Deprecated
    public void next() {
        this.player.next();
    }

    public void pause() {
        this.player.pause();
    }

    public void play() {
        this.player.play();
    }

    public void prepare() {
        this.player.prepare();
    }

    @Deprecated
    public void previous() {
        this.player.previous();
    }

    public void release() {
        this.player.release();
    }

    public void removeListener(Player.Listener listener) {
        this.player.removeListener(new ForwardingListener(this, listener));
    }

    public void removeMediaItem(int i3) {
        this.player.removeMediaItem(i3);
    }

    public void removeMediaItems(int i3, int i4) {
        this.player.removeMediaItems(i3, i4);
    }

    public void seekBack() {
        this.player.seekBack();
    }

    public void seekForward() {
        this.player.seekForward();
    }

    public void seekTo(long j2) {
        this.player.seekTo(j2);
    }

    public void seekToDefaultPosition() {
        this.player.seekToDefaultPosition();
    }

    public void seekToNext() {
        this.player.seekToNext();
    }

    public void seekToNextMediaItem() {
        this.player.seekToNextMediaItem();
    }

    @Deprecated
    public void seekToNextWindow() {
        this.player.seekToNextWindow();
    }

    public void seekToPrevious() {
        this.player.seekToPrevious();
    }

    public void seekToPreviousMediaItem() {
        this.player.seekToPreviousMediaItem();
    }

    @Deprecated
    public void seekToPreviousWindow() {
        this.player.seekToPreviousWindow();
    }

    public void setDeviceMuted(boolean z2) {
        this.player.setDeviceMuted(z2);
    }

    public void setDeviceVolume(int i3) {
        this.player.setDeviceVolume(i3);
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.player.setMediaItem(mediaItem);
    }

    public void setMediaItems(List<MediaItem> list) {
        this.player.setMediaItems(list);
    }

    public void setPlayWhenReady(boolean z2) {
        this.player.setPlayWhenReady(z2);
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.player.setPlaybackParameters(playbackParameters);
    }

    public void setPlaybackSpeed(float f2) {
        this.player.setPlaybackSpeed(f2);
    }

    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        this.player.setPlaylistMetadata(mediaMetadata);
    }

    public void setRepeatMode(int i3) {
        this.player.setRepeatMode(i3);
    }

    public void setShuffleModeEnabled(boolean z2) {
        this.player.setShuffleModeEnabled(z2);
    }

    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        this.player.setTrackSelectionParameters(trackSelectionParameters);
    }

    public void setVideoSurface(@Nullable Surface surface) {
        this.player.setVideoSurface(surface);
    }

    public void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {
        this.player.setVideoSurfaceHolder(surfaceHolder);
    }

    public void setVideoSurfaceView(@Nullable SurfaceView surfaceView) {
        this.player.setVideoSurfaceView(surfaceView);
    }

    public void setVideoTextureView(@Nullable TextureView textureView) {
        this.player.setVideoTextureView(textureView);
    }

    public void setVolume(float f2) {
        this.player.setVolume(f2);
    }

    public void stop() {
        this.player.stop();
    }

    public void addMediaItem(int i3, MediaItem mediaItem) {
        this.player.addMediaItem(i3, mediaItem);
    }

    public void addMediaItems(int i3, List<MediaItem> list) {
        this.player.addMediaItems(i3, list);
    }

    public void clearVideoSurface(@Nullable Surface surface) {
        this.player.clearVideoSurface(surface);
    }

    public void seekTo(int i3, long j2) {
        this.player.seekTo(i3, j2);
    }

    public void seekToDefaultPosition(int i3) {
        this.player.seekToDefaultPosition(i3);
    }

    public void setMediaItem(MediaItem mediaItem, long j2) {
        this.player.setMediaItem(mediaItem, j2);
    }

    public void setMediaItems(List<MediaItem> list, boolean z2) {
        this.player.setMediaItems(list, z2);
    }

    @Deprecated
    public void stop(boolean z2) {
        this.player.stop(z2);
    }

    public void setMediaItem(MediaItem mediaItem, boolean z2) {
        this.player.setMediaItem(mediaItem, z2);
    }

    public void setMediaItems(List<MediaItem> list, int i3, long j2) {
        this.player.setMediaItems(list, i3, j2);
    }
}
