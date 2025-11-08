package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.AbstractConcatenatedTimeline;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public final class LoopingMediaSource extends CompositeMediaSource<Void> {
    private final Map<MediaSource.MediaPeriodId, MediaSource.MediaPeriodId> childMediaPeriodIdToMediaPeriodId;
    private final int loopCount;
    private final MaskingMediaSource maskingMediaSource;
    private final Map<MediaPeriod, MediaSource.MediaPeriodId> mediaPeriodToChildMediaPeriodId;

    public static final class InfinitelyLoopingTimeline extends ForwardingTimeline {
        public InfinitelyLoopingTimeline(Timeline timeline) {
            super(timeline);
        }

        public int getNextWindowIndex(int i3, int i4, boolean z2) {
            int nextWindowIndex = this.timeline.getNextWindowIndex(i3, i4, z2);
            return nextWindowIndex == -1 ? getFirstWindowIndex(z2) : nextWindowIndex;
        }

        public int getPreviousWindowIndex(int i3, int i4, boolean z2) {
            int previousWindowIndex = this.timeline.getPreviousWindowIndex(i3, i4, z2);
            return previousWindowIndex == -1 ? getLastWindowIndex(z2) : previousWindowIndex;
        }
    }

    public static final class LoopingTimeline extends AbstractConcatenatedTimeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LoopingTimeline(Timeline timeline, int i3) {
            super(false, new ShuffleOrder.UnshuffledShuffleOrder(i3));
            boolean z2 = false;
            this.childTimeline = timeline;
            int periodCount = timeline.getPeriodCount();
            this.childPeriodCount = periodCount;
            this.childWindowCount = timeline.getWindowCount();
            this.loopCount = i3;
            if (periodCount > 0) {
                Assertions.checkState(i3 <= Integer.MAX_VALUE / periodCount ? true : z2, "LoopingMediaSource contains too many periods");
            }
        }

        public int getChildIndexByChildUid(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }

        public int getChildIndexByPeriodIndex(int i3) {
            return i3 / this.childPeriodCount;
        }

        public int getChildIndexByWindowIndex(int i3) {
            return i3 / this.childWindowCount;
        }

        public Object getChildUidByChildIndex(int i3) {
            return Integer.valueOf(i3);
        }

        public int getFirstPeriodIndexByChildIndex(int i3) {
            return i3 * this.childPeriodCount;
        }

        public int getFirstWindowIndexByChildIndex(int i3) {
            return i3 * this.childWindowCount;
        }

        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        public Timeline getTimelineByChildIndex(int i3) {
            return this.childTimeline;
        }

        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }
    }

    public LoopingMediaSource(MediaSource mediaSource) {
        this(mediaSource, Integer.MAX_VALUE);
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        if (this.loopCount == Integer.MAX_VALUE) {
            return this.maskingMediaSource.createPeriod(mediaPeriodId, allocator, j2);
        }
        MediaSource.MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(mediaPeriodId.periodUid));
        this.childMediaPeriodIdToMediaPeriodId.put(copyWithPeriodUid, mediaPeriodId);
        MaskingMediaPeriod createPeriod = this.maskingMediaSource.createPeriod(copyWithPeriodUid, allocator, j2);
        this.mediaPeriodToChildMediaPeriodId.put(createPeriod, copyWithPeriodUid);
        return createPeriod;
    }

    @Nullable
    public Timeline getInitialTimeline() {
        return this.loopCount != Integer.MAX_VALUE ? new LoopingTimeline(this.maskingMediaSource.getTimeline(), this.loopCount) : new InfinitelyLoopingTimeline(this.maskingMediaSource.getTimeline());
    }

    public MediaItem getMediaItem() {
        return this.maskingMediaSource.getMediaItem();
    }

    public boolean isSingleWindow() {
        return false;
    }

    public void prepareSourceInternal(@Nullable TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareChildSource(null, this.maskingMediaSource);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.maskingMediaSource.releasePeriod(mediaPeriod);
        MediaSource.MediaPeriodId remove = this.mediaPeriodToChildMediaPeriodId.remove(mediaPeriod);
        if (remove != null) {
            this.childMediaPeriodIdToMediaPeriodId.remove(remove);
        }
    }

    public LoopingMediaSource(MediaSource mediaSource, int i3) {
        Assertions.checkArgument(i3 > 0);
        this.maskingMediaSource = new MaskingMediaSource(mediaSource, false);
        this.loopCount = i3;
        this.childMediaPeriodIdToMediaPeriodId = new HashMap();
        this.mediaPeriodToChildMediaPeriodId = new HashMap();
    }

    @Nullable
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void voidR, MediaSource.MediaPeriodId mediaPeriodId) {
        return this.loopCount != Integer.MAX_VALUE ? this.childMediaPeriodIdToMediaPeriodId.get(mediaPeriodId) : mediaPeriodId;
    }

    public void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource, Timeline timeline) {
        Timeline timeline2;
        if (this.loopCount != Integer.MAX_VALUE) {
            timeline2 = new LoopingTimeline(timeline, this.loopCount);
        } else {
            timeline2 = new InfinitelyLoopingTimeline(timeline);
        }
        refreshSourceInfo(timeline2);
    }
}
