package com.appsamurai.storyly.exoplayer2.hls;

import android.util.SparseArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;

public final class TimestampAdjusterProvider {
    private final SparseArray<TimestampAdjuster> timestampAdjusters = new SparseArray<>();

    public TimestampAdjuster getAdjuster(int i3) {
        TimestampAdjuster timestampAdjuster = this.timestampAdjusters.get(i3);
        if (timestampAdjuster != null) {
            return timestampAdjuster;
        }
        TimestampAdjuster timestampAdjuster2 = new TimestampAdjuster(TimestampAdjuster.MODE_SHARED);
        this.timestampAdjusters.put(i3, timestampAdjuster2);
        return timestampAdjuster2;
    }

    public void reset() {
        this.timestampAdjusters.clear();
    }
}
