package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

final class PlaylistTimeline extends AbstractConcatenatedTimeline {
    private final HashMap<Object, Integer> childIndexByUid = new HashMap<>();
    private final int[] firstPeriodInChildIndices;
    private final int[] firstWindowInChildIndices;
    private final int periodCount;
    private final Timeline[] timelines;
    private final Object[] uids;
    private final int windowCount;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlaylistTimeline(Collection<? extends MediaSourceInfoHolder> collection, ShuffleOrder shuffleOrder) {
        super(false, shuffleOrder);
        int i3 = 0;
        int size = collection.size();
        this.firstPeriodInChildIndices = new int[size];
        this.firstWindowInChildIndices = new int[size];
        this.timelines = new Timeline[size];
        this.uids = new Object[size];
        int i4 = 0;
        int i5 = 0;
        for (MediaSourceInfoHolder mediaSourceInfoHolder : collection) {
            this.timelines[i5] = mediaSourceInfoHolder.getTimeline();
            this.firstWindowInChildIndices[i5] = i3;
            this.firstPeriodInChildIndices[i5] = i4;
            i3 += this.timelines[i5].getWindowCount();
            i4 += this.timelines[i5].getPeriodCount();
            this.uids[i5] = mediaSourceInfoHolder.getUid();
            this.childIndexByUid.put(this.uids[i5], Integer.valueOf(i5));
            i5++;
        }
        this.windowCount = i3;
        this.periodCount = i4;
    }

    public int getChildIndexByChildUid(Object obj) {
        Integer num = this.childIndexByUid.get(obj);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public int getChildIndexByPeriodIndex(int i3) {
        return Util.binarySearchFloor(this.firstPeriodInChildIndices, i3 + 1, false, false);
    }

    public int getChildIndexByWindowIndex(int i3) {
        return Util.binarySearchFloor(this.firstWindowInChildIndices, i3 + 1, false, false);
    }

    public List<Timeline> getChildTimelines() {
        return Arrays.asList(this.timelines);
    }

    public Object getChildUidByChildIndex(int i3) {
        return this.uids[i3];
    }

    public int getFirstPeriodIndexByChildIndex(int i3) {
        return this.firstPeriodInChildIndices[i3];
    }

    public int getFirstWindowIndexByChildIndex(int i3) {
        return this.firstWindowInChildIndices[i3];
    }

    public int getPeriodCount() {
        return this.periodCount;
    }

    public Timeline getTimelineByChildIndex(int i3) {
        return this.timelines[i3];
    }

    public int getWindowCount() {
        return this.windowCount;
    }
}
