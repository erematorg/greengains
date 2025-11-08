package com.appsamurai.storyly.exoplayer2.core.source.ads;

import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.source.ForwardingTimeline;

@VisibleForTesting(otherwise = 3)
public final class SinglePeriodAdTimeline extends ForwardingTimeline {
    private final AdPlaybackState adPlaybackState;

    public SinglePeriodAdTimeline(Timeline timeline, AdPlaybackState adPlaybackState2) {
        super(timeline);
        boolean z2 = false;
        Assertions.checkState(timeline.getPeriodCount() == 1);
        Assertions.checkState(timeline.getWindowCount() == 1 ? true : z2);
        this.adPlaybackState = adPlaybackState2;
    }

    public Timeline.Period getPeriod(int i3, Timeline.Period period, boolean z2) {
        this.timeline.getPeriod(i3, period, z2);
        long j2 = period.durationUs;
        if (j2 == C.TIME_UNSET) {
            j2 = this.adPlaybackState.contentDurationUs;
        }
        Timeline.Period period2 = period;
        period2.set(period.id, period.uid, period.windowIndex, j2, period.getPositionInWindowUs(), this.adPlaybackState, period.isPlaceholder);
        return period;
    }
}
