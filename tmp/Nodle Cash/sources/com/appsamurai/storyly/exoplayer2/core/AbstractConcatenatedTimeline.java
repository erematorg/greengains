package com.appsamurai.storyly.exoplayer2.core;

import android.util.Pair;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.source.ShuffleOrder;

public abstract class AbstractConcatenatedTimeline extends Timeline {
    private final int childCount;
    private final boolean isAtomic;
    private final ShuffleOrder shuffleOrder;

    public AbstractConcatenatedTimeline(boolean z2, ShuffleOrder shuffleOrder2) {
        this.isAtomic = z2;
        this.shuffleOrder = shuffleOrder2;
        this.childCount = shuffleOrder2.getLength();
    }

    public static Object getChildPeriodUidFromConcatenatedUid(Object obj) {
        return ((Pair) obj).second;
    }

    public static Object getChildTimelineUidFromConcatenatedUid(Object obj) {
        return ((Pair) obj).first;
    }

    public static Object getConcatenatedUid(Object obj, Object obj2) {
        return Pair.create(obj, obj2);
    }

    private int getNextChildIndex(int i3, boolean z2) {
        if (z2) {
            return this.shuffleOrder.getNextIndex(i3);
        }
        if (i3 < this.childCount - 1) {
            return i3 + 1;
        }
        return -1;
    }

    private int getPreviousChildIndex(int i3, boolean z2) {
        if (z2) {
            return this.shuffleOrder.getPreviousIndex(i3);
        }
        if (i3 > 0) {
            return i3 - 1;
        }
        return -1;
    }

    public abstract int getChildIndexByChildUid(Object obj);

    public abstract int getChildIndexByPeriodIndex(int i3);

    public abstract int getChildIndexByWindowIndex(int i3);

    public abstract Object getChildUidByChildIndex(int i3);

    public abstract int getFirstPeriodIndexByChildIndex(int i3);

    public int getFirstWindowIndex(boolean z2) {
        if (this.childCount == 0) {
            return -1;
        }
        int i3 = 0;
        if (this.isAtomic) {
            z2 = false;
        }
        if (z2) {
            i3 = this.shuffleOrder.getFirstIndex();
        }
        while (getTimelineByChildIndex(i3).isEmpty()) {
            i3 = getNextChildIndex(i3, z2);
            if (i3 == -1) {
                return -1;
            }
        }
        return getTimelineByChildIndex(i3).getFirstWindowIndex(z2) + getFirstWindowIndexByChildIndex(i3);
    }

    public abstract int getFirstWindowIndexByChildIndex(int i3);

    public final int getIndexOfPeriod(Object obj) {
        int indexOfPeriod;
        if (!(obj instanceof Pair)) {
            return -1;
        }
        Object childTimelineUidFromConcatenatedUid = getChildTimelineUidFromConcatenatedUid(obj);
        Object childPeriodUidFromConcatenatedUid = getChildPeriodUidFromConcatenatedUid(obj);
        int childIndexByChildUid = getChildIndexByChildUid(childTimelineUidFromConcatenatedUid);
        if (childIndexByChildUid == -1 || (indexOfPeriod = getTimelineByChildIndex(childIndexByChildUid).getIndexOfPeriod(childPeriodUidFromConcatenatedUid)) == -1) {
            return -1;
        }
        return getFirstPeriodIndexByChildIndex(childIndexByChildUid) + indexOfPeriod;
    }

    public int getLastWindowIndex(boolean z2) {
        int i3 = this.childCount;
        if (i3 == 0) {
            return -1;
        }
        if (this.isAtomic) {
            z2 = false;
        }
        int lastIndex = z2 ? this.shuffleOrder.getLastIndex() : i3 - 1;
        while (getTimelineByChildIndex(lastIndex).isEmpty()) {
            lastIndex = getPreviousChildIndex(lastIndex, z2);
            if (lastIndex == -1) {
                return -1;
            }
        }
        return getTimelineByChildIndex(lastIndex).getLastWindowIndex(z2) + getFirstWindowIndexByChildIndex(lastIndex);
    }

    public int getNextWindowIndex(int i3, int i4, boolean z2) {
        int i5 = 0;
        if (this.isAtomic) {
            if (i4 == 1) {
                i4 = 2;
            }
            z2 = false;
        }
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i3);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        Timeline timelineByChildIndex = getTimelineByChildIndex(childIndexByWindowIndex);
        int i6 = i3 - firstWindowIndexByChildIndex;
        if (i4 != 2) {
            i5 = i4;
        }
        int nextWindowIndex = timelineByChildIndex.getNextWindowIndex(i6, i5, z2);
        if (nextWindowIndex != -1) {
            return firstWindowIndexByChildIndex + nextWindowIndex;
        }
        int nextChildIndex = getNextChildIndex(childIndexByWindowIndex, z2);
        while (nextChildIndex != -1 && getTimelineByChildIndex(nextChildIndex).isEmpty()) {
            nextChildIndex = getNextChildIndex(nextChildIndex, z2);
        }
        if (nextChildIndex != -1) {
            return getTimelineByChildIndex(nextChildIndex).getFirstWindowIndex(z2) + getFirstWindowIndexByChildIndex(nextChildIndex);
        } else if (i4 == 2) {
            return getFirstWindowIndex(z2);
        } else {
            return -1;
        }
    }

    public final Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
        int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i3);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByPeriodIndex);
        getTimelineByChildIndex(childIndexByPeriodIndex).getPeriod(i3 - getFirstPeriodIndexByChildIndex(childIndexByPeriodIndex), period, z2);
        period.windowIndex += firstWindowIndexByChildIndex;
        if (z2) {
            period.uid = getConcatenatedUid(getChildUidByChildIndex(childIndexByPeriodIndex), Assertions.checkNotNull(period.uid));
        }
        return period;
    }

    public final Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
        Object childTimelineUidFromConcatenatedUid = getChildTimelineUidFromConcatenatedUid(obj);
        Object childPeriodUidFromConcatenatedUid = getChildPeriodUidFromConcatenatedUid(obj);
        int childIndexByChildUid = getChildIndexByChildUid(childTimelineUidFromConcatenatedUid);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByChildUid);
        getTimelineByChildIndex(childIndexByChildUid).getPeriodByUid(childPeriodUidFromConcatenatedUid, period);
        period.windowIndex += firstWindowIndexByChildIndex;
        period.uid = obj;
        return period;
    }

    public int getPreviousWindowIndex(int i3, int i4, boolean z2) {
        int i5 = 0;
        if (this.isAtomic) {
            if (i4 == 1) {
                i4 = 2;
            }
            z2 = false;
        }
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i3);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        Timeline timelineByChildIndex = getTimelineByChildIndex(childIndexByWindowIndex);
        int i6 = i3 - firstWindowIndexByChildIndex;
        if (i4 != 2) {
            i5 = i4;
        }
        int previousWindowIndex = timelineByChildIndex.getPreviousWindowIndex(i6, i5, z2);
        if (previousWindowIndex != -1) {
            return firstWindowIndexByChildIndex + previousWindowIndex;
        }
        int previousChildIndex = getPreviousChildIndex(childIndexByWindowIndex, z2);
        while (previousChildIndex != -1 && getTimelineByChildIndex(previousChildIndex).isEmpty()) {
            previousChildIndex = getPreviousChildIndex(previousChildIndex, z2);
        }
        if (previousChildIndex != -1) {
            return getTimelineByChildIndex(previousChildIndex).getLastWindowIndex(z2) + getFirstWindowIndexByChildIndex(previousChildIndex);
        } else if (i4 == 2) {
            return getLastWindowIndex(z2);
        } else {
            return -1;
        }
    }

    public abstract Timeline getTimelineByChildIndex(int i3);

    public final Object getUidOfPeriod(int i3) {
        int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i3);
        return getConcatenatedUid(getChildUidByChildIndex(childIndexByPeriodIndex), getTimelineByChildIndex(childIndexByPeriodIndex).getUidOfPeriod(i3 - getFirstPeriodIndexByChildIndex(childIndexByPeriodIndex)));
    }

    public final Timeline.Window getWindow(int i3, Timeline.Window window, long j2) {
        int childIndexByWindowIndex = getChildIndexByWindowIndex(i3);
        int firstWindowIndexByChildIndex = getFirstWindowIndexByChildIndex(childIndexByWindowIndex);
        int firstPeriodIndexByChildIndex = getFirstPeriodIndexByChildIndex(childIndexByWindowIndex);
        getTimelineByChildIndex(childIndexByWindowIndex).getWindow(i3 - firstWindowIndexByChildIndex, window, j2);
        Object childUidByChildIndex = getChildUidByChildIndex(childIndexByWindowIndex);
        if (!Timeline.Window.SINGLE_WINDOW_UID.equals(window.uid)) {
            childUidByChildIndex = getConcatenatedUid(childUidByChildIndex, window.uid);
        }
        window.uid = childUidByChildIndex;
        window.firstPeriodIndex += firstPeriodIndexByChildIndex;
        window.lastPeriodIndex += firstPeriodIndexByChildIndex;
        return window;
    }
}
