package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3;

import android.util.Pair;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.MlltFrame;

final class MlltSeeker implements Seeker {
    private final long durationUs;
    private final long[] referencePositions;
    private final long[] referenceTimesMs;

    private MlltSeeker(long[] jArr, long[] jArr2, long j2) {
        this.referencePositions = jArr;
        this.referenceTimesMs = jArr2;
        this.durationUs = j2 == C.TIME_UNSET ? Util.msToUs(jArr2[jArr2.length - 1]) : j2;
    }

    public static MlltSeeker create(long j2, MlltFrame mlltFrame, long j3) {
        int length = mlltFrame.bytesDeviations.length;
        int i3 = length + 1;
        long[] jArr = new long[i3];
        long[] jArr2 = new long[i3];
        jArr[0] = j2;
        long j4 = 0;
        jArr2[0] = 0;
        for (int i4 = 1; i4 <= length; i4++) {
            int i5 = i4 - 1;
            j2 += (long) (mlltFrame.bytesBetweenReference + mlltFrame.bytesDeviations[i5]);
            j4 += (long) (mlltFrame.millisecondsBetweenReference + mlltFrame.millisecondsDeviations[i5]);
            jArr[i4] = j2;
            jArr2[i4] = j4;
        }
        return new MlltSeeker(jArr, jArr2, j3);
    }

    private static Pair<Long, Long> linearlyInterpolate(long j2, long[] jArr, long[] jArr2) {
        int binarySearchFloor = Util.binarySearchFloor(jArr, j2, true, true);
        long j3 = jArr[binarySearchFloor];
        long j4 = jArr2[binarySearchFloor];
        int i3 = binarySearchFloor + 1;
        if (i3 == jArr.length) {
            return Pair.create(Long.valueOf(j3), Long.valueOf(j4));
        }
        long j5 = jArr[i3];
        return Pair.create(Long.valueOf(j2), Long.valueOf(((long) ((j5 == j3 ? 0.0d : (((double) j2) - ((double) j3)) / ((double) (j5 - j3))) * ((double) (jArr2[i3] - j4)))) + j4));
    }

    public long getDataEndPosition() {
        return -1;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        Pair<Long, Long> linearlyInterpolate = linearlyInterpolate(Util.usToMs(Util.constrainValue(j2, 0, this.durationUs)), this.referenceTimesMs, this.referencePositions);
        return new SeekMap.SeekPoints(new SeekPoint(Util.msToUs(((Long) linearlyInterpolate.first).longValue()), ((Long) linearlyInterpolate.second).longValue()));
    }

    public long getTimeUs(long j2) {
        return Util.msToUs(((Long) linearlyInterpolate(j2, this.referencePositions, this.referenceTimesMs).second).longValue());
    }

    public boolean isSeekable() {
        return true;
    }
}
