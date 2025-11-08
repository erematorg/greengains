package com.appsamurai.storyly.exoplayer2.extractor.extractor.wav;

import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;

final class WavSeekMap implements SeekMap {
    private final long blockCount;
    private final long durationUs;
    private final long firstBlockPosition;
    private final int framesPerBlock;
    private final WavFormat wavFormat;

    public WavSeekMap(WavFormat wavFormat2, int i3, long j2, long j3) {
        this.wavFormat = wavFormat2;
        this.framesPerBlock = i3;
        this.firstBlockPosition = j2;
        long j4 = (j3 - j2) / ((long) wavFormat2.blockSize);
        this.blockCount = j4;
        this.durationUs = blockIndexToTimeUs(j4);
    }

    private long blockIndexToTimeUs(long j2) {
        return Util.scaleLargeTimestamp(j2 * ((long) this.framesPerBlock), 1000000, (long) this.wavFormat.frameRateHz);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        long constrainValue = Util.constrainValue((((long) this.wavFormat.frameRateHz) * j2) / (((long) this.framesPerBlock) * 1000000), 0, this.blockCount - 1);
        long j3 = (((long) this.wavFormat.blockSize) * constrainValue) + this.firstBlockPosition;
        long blockIndexToTimeUs = blockIndexToTimeUs(constrainValue);
        SeekPoint seekPoint = new SeekPoint(blockIndexToTimeUs, j3);
        if (blockIndexToTimeUs >= j2 || constrainValue == this.blockCount - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        long j4 = constrainValue + 1;
        long j5 = this.firstBlockPosition;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(blockIndexToTimeUs(j4), (((long) this.wavFormat.blockSize) * j4) + j5));
    }

    public boolean isSeekable() {
        return true;
    }
}
