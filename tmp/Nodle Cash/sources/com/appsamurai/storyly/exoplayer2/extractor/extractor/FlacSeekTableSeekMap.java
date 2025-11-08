package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacStreamMetadata;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;

public final class FlacSeekTableSeekMap implements SeekMap {
    private final long firstFrameOffset;
    private final FlacStreamMetadata flacStreamMetadata;

    public FlacSeekTableSeekMap(FlacStreamMetadata flacStreamMetadata2, long j2) {
        this.flacStreamMetadata = flacStreamMetadata2;
        this.firstFrameOffset = j2;
    }

    private SeekPoint getSeekPoint(long j2, long j3) {
        return new SeekPoint((j2 * 1000000) / ((long) this.flacStreamMetadata.sampleRate), this.firstFrameOffset + j3);
    }

    public long getDurationUs() {
        return this.flacStreamMetadata.getDurationUs();
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        Assertions.checkStateNotNull(this.flacStreamMetadata.seekTable);
        FlacStreamMetadata flacStreamMetadata2 = this.flacStreamMetadata;
        FlacStreamMetadata.SeekTable seekTable = flacStreamMetadata2.seekTable;
        long[] jArr = seekTable.pointSampleNumbers;
        long[] jArr2 = seekTable.pointOffsets;
        int binarySearchFloor = Util.binarySearchFloor(jArr, flacStreamMetadata2.getSampleNumber(j2), true, false);
        long j3 = 0;
        long j4 = binarySearchFloor == -1 ? 0 : jArr[binarySearchFloor];
        if (binarySearchFloor != -1) {
            j3 = jArr2[binarySearchFloor];
        }
        SeekPoint seekPoint = getSeekPoint(j4, j3);
        if (seekPoint.timeUs == j2 || binarySearchFloor == jArr.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i3 = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, getSeekPoint(jArr[i3], jArr2[i3]));
    }

    public boolean isSeekable() {
        return true;
    }
}
