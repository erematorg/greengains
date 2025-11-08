package com.appsamurai.storyly.exoplayer2.common;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.util.Collections;
import java.util.List;

public abstract class BasePlayer implements Player {
    protected final Timeline.Window window = new Timeline.Window();

    private int getRepeatModeForNavigation() {
        int repeatMode = getRepeatMode();
        if (repeatMode == 1) {
            return 0;
        }
        return repeatMode;
    }

    private void seekToOffset(long j2) {
        long currentPosition = getCurrentPosition() + j2;
        long duration = getDuration();
        if (duration != C.TIME_UNSET) {
            currentPosition = Math.min(currentPosition, duration);
        }
        seekTo(Math.max(currentPosition, 0));
    }

    public final void addMediaItem(int i3, MediaItem mediaItem) {
        addMediaItems(i3, Collections.singletonList(mediaItem));
    }

    public final void addMediaItems(List<MediaItem> list) {
        addMediaItems(Integer.MAX_VALUE, list);
    }

    public final boolean canAdvertiseSession() {
        return true;
    }

    public final void clearMediaItems() {
        removeMediaItems(0, Integer.MAX_VALUE);
    }

    public final int getBufferedPercentage() {
        long bufferedPosition = getBufferedPosition();
        long duration = getDuration();
        if (bufferedPosition == C.TIME_UNSET || duration == C.TIME_UNSET) {
            return 0;
        }
        if (duration == 0) {
            return 100;
        }
        return Util.constrainValue((int) ((bufferedPosition * 100) / duration), 0, 100);
    }

    public final long getContentDuration() {
        Timeline currentTimeline = getCurrentTimeline();
        return currentTimeline.isEmpty() ? C.TIME_UNSET : currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).getDurationMs();
    }

    public final long getCurrentLiveOffset() {
        Timeline currentTimeline = getCurrentTimeline();
        return (!currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).windowStartTimeMs != C.TIME_UNSET) ? (this.window.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - getContentPosition() : C.TIME_UNSET;
    }

    @Nullable
    public final Object getCurrentManifest() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return null;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).manifest;
    }

    @Nullable
    public final MediaItem getCurrentMediaItem() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return null;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem;
    }

    @Deprecated
    public final int getCurrentWindowIndex() {
        return getCurrentMediaItemIndex();
    }

    public final MediaItem getMediaItemAt(int i3) {
        return getCurrentTimeline().getWindow(i3, this.window).mediaItem;
    }

    public final int getMediaItemCount() {
        return getCurrentTimeline().getWindowCount();
    }

    public final int getNextMediaItemIndex() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -1;
        }
        return currentTimeline.getNextWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    @Deprecated
    public final int getNextWindowIndex() {
        return getNextMediaItemIndex();
    }

    public final int getPreviousMediaItemIndex() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -1;
        }
        return currentTimeline.getPreviousWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    @Deprecated
    public final int getPreviousWindowIndex() {
        return getPreviousMediaItemIndex();
    }

    @Deprecated
    public final boolean hasNext() {
        return hasNextMediaItem();
    }

    public final boolean hasNextMediaItem() {
        return getNextMediaItemIndex() != -1;
    }

    @Deprecated
    public final boolean hasNextWindow() {
        return hasNextMediaItem();
    }

    @Deprecated
    public final boolean hasPrevious() {
        return hasPreviousMediaItem();
    }

    public final boolean hasPreviousMediaItem() {
        return getPreviousMediaItemIndex() != -1;
    }

    @Deprecated
    public final boolean hasPreviousWindow() {
        return hasPreviousMediaItem();
    }

    public final boolean isCommandAvailable(int i3) {
        return getAvailableCommands().contains(i3);
    }

    public final boolean isCurrentMediaItemDynamic() {
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isDynamic;
    }

    public final boolean isCurrentMediaItemLive() {
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isLive();
    }

    public final boolean isCurrentMediaItemSeekable() {
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isSeekable;
    }

    @Deprecated
    public final boolean isCurrentWindowDynamic() {
        return isCurrentMediaItemDynamic();
    }

    @Deprecated
    public final boolean isCurrentWindowLive() {
        return isCurrentMediaItemLive();
    }

    @Deprecated
    public final boolean isCurrentWindowSeekable() {
        return isCurrentMediaItemSeekable();
    }

    public final boolean isPlaying() {
        return getPlaybackState() == 3 && getPlayWhenReady() && getPlaybackSuppressionReason() == 0;
    }

    public final void moveMediaItem(int i3, int i4) {
        if (i3 != i4) {
            moveMediaItems(i3, i3 + 1, i4);
        }
    }

    @Deprecated
    public final void next() {
        seekToNextMediaItem();
    }

    public final void pause() {
        setPlayWhenReady(false);
    }

    public final void play() {
        setPlayWhenReady(true);
    }

    @Deprecated
    public final void previous() {
        seekToPreviousMediaItem();
    }

    public final void removeMediaItem(int i3) {
        removeMediaItems(i3, i3 + 1);
    }

    public final void seekBack() {
        seekToOffset(-getSeekBackIncrement());
    }

    public final void seekForward() {
        seekToOffset(getSeekForwardIncrement());
    }

    public final void seekTo(long j2) {
        seekTo(getCurrentMediaItemIndex(), j2);
    }

    public final void seekToDefaultPosition() {
        seekToDefaultPosition(getCurrentMediaItemIndex());
    }

    public final void seekToNext() {
        if (!getCurrentTimeline().isEmpty() && !isPlayingAd()) {
            if (hasNextMediaItem()) {
                seekToNextMediaItem();
            } else if (isCurrentMediaItemLive() && isCurrentMediaItemDynamic()) {
                seekToDefaultPosition();
            }
        }
    }

    public final void seekToNextMediaItem() {
        int nextMediaItemIndex = getNextMediaItemIndex();
        if (nextMediaItemIndex != -1) {
            seekToDefaultPosition(nextMediaItemIndex);
        }
    }

    @Deprecated
    public final void seekToNextWindow() {
        seekToNextMediaItem();
    }

    public final void seekToPrevious() {
        if (!getCurrentTimeline().isEmpty() && !isPlayingAd()) {
            boolean hasPreviousMediaItem = hasPreviousMediaItem();
            if (!isCurrentMediaItemLive() || isCurrentMediaItemSeekable()) {
                if (!hasPreviousMediaItem || getCurrentPosition() > getMaxSeekToPreviousPosition()) {
                    seekTo(0);
                } else {
                    seekToPreviousMediaItem();
                }
            } else if (hasPreviousMediaItem) {
                seekToPreviousMediaItem();
            }
        }
    }

    public final void seekToPreviousMediaItem() {
        int previousMediaItemIndex = getPreviousMediaItemIndex();
        if (previousMediaItemIndex != -1) {
            seekToDefaultPosition(previousMediaItemIndex);
        }
    }

    @Deprecated
    public final void seekToPreviousWindow() {
        seekToPreviousMediaItem();
    }

    public final void setMediaItem(MediaItem mediaItem) {
        setMediaItems(Collections.singletonList(mediaItem));
    }

    public final void setMediaItems(List<MediaItem> list) {
        setMediaItems(list, true);
    }

    public final void setPlaybackSpeed(float f2) {
        setPlaybackParameters(getPlaybackParameters().withSpeed(f2));
    }

    public final void addMediaItem(MediaItem mediaItem) {
        addMediaItems(Collections.singletonList(mediaItem));
    }

    public final void seekToDefaultPosition(int i3) {
        seekTo(i3, C.TIME_UNSET);
    }

    public final void setMediaItem(MediaItem mediaItem, long j2) {
        setMediaItems(Collections.singletonList(mediaItem), 0, j2);
    }

    public final void setMediaItem(MediaItem mediaItem, boolean z2) {
        setMediaItems(Collections.singletonList(mediaItem), z2);
    }
}
