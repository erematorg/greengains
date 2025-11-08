package com.appsamurai.storyly.exoplayer2.core.source;

import com.appsamurai.storyly.exoplayer2.common.Timeline;

public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline timeline2) {
        this.timeline = timeline2;
    }

    public int getFirstWindowIndex(boolean z2) {
        return this.timeline.getFirstWindowIndex(z2);
    }

    public int getIndexOfPeriod(Object obj) {
        return this.timeline.getIndexOfPeriod(obj);
    }

    public int getLastWindowIndex(boolean z2) {
        return this.timeline.getLastWindowIndex(z2);
    }

    public int getNextWindowIndex(int i3, int i4, boolean z2) {
        return this.timeline.getNextWindowIndex(i3, i4, z2);
    }

    public Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
        return this.timeline.getPeriod(i3, period, z2);
    }

    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    public int getPreviousWindowIndex(int i3, int i4, boolean z2) {
        return this.timeline.getPreviousWindowIndex(i3, i4, z2);
    }

    public Object getUidOfPeriod(int i3) {
        return this.timeline.getUidOfPeriod(i3);
    }

    public Timeline.Window getWindow(int i3, Timeline.Window window, long j2) {
        return this.timeline.getWindow(i3, window, j2);
    }

    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }
}
