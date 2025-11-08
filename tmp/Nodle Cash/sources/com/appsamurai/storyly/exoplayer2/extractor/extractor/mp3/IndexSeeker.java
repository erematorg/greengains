package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3;

import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.util.LongArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;

final class IndexSeeker implements Seeker {
    @VisibleForTesting
    static final long MIN_TIME_BETWEEN_POINTS_US = 100000;
    private final long dataEndPosition;
    private long durationUs;
    private final LongArray positions;
    private final LongArray timesUs;

    public IndexSeeker(long j2, long j3, long j4) {
        this.durationUs = j2;
        this.dataEndPosition = j4;
        LongArray longArray = new LongArray();
        this.timesUs = longArray;
        LongArray longArray2 = new LongArray();
        this.positions = longArray2;
        longArray.add(0);
        longArray2.add(j3);
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j2, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs.get(binarySearchFloor), this.positions.get(binarySearchFloor));
        if (seekPoint.timeUs == j2 || binarySearchFloor == this.timesUs.size() - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i3 = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs.get(i3), this.positions.get(i3)));
    }

    public long getTimeUs(long j2) {
        return this.timesUs.get(Util.binarySearchFloor(this.positions, j2, true, true));
    }

    public boolean isSeekable() {
        return true;
    }

    public boolean isTimeUsInIndex(long j2) {
        LongArray longArray = this.timesUs;
        return j2 - longArray.get(longArray.size() - 1) < MIN_TIME_BETWEEN_POINTS_US;
    }

    public void maybeAddSeekPoint(long j2, long j3) {
        if (!isTimeUsInIndex(j2)) {
            this.timesUs.add(j2);
            this.positions.add(j3);
        }
    }

    public void setDurationUs(long j2) {
        this.durationUs = j2;
    }
}
