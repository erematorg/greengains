package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;

public class ConstantBitrateSeekMap implements SeekMap {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataSize;
    private final long durationUs;
    private final long firstFrameBytePosition;
    private final int frameSize;
    private final long inputLength;

    public ConstantBitrateSeekMap(long j2, long j3, int i3, int i4) {
        this(j2, j3, i3, i4, false);
    }

    private long getFramePositionForTimeUs(long j2) {
        int i3 = this.frameSize;
        long j3 = (((j2 * ((long) this.bitrate)) / 8000000) / ((long) i3)) * ((long) i3);
        long j4 = this.dataSize;
        if (j4 != -1) {
            j3 = Math.min(j3, j4 - ((long) i3));
        }
        return this.firstFrameBytePosition + Math.max(j3, 0);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        if (this.dataSize == -1 && !this.allowSeeksIfLengthUnknown) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.firstFrameBytePosition));
        }
        long framePositionForTimeUs = getFramePositionForTimeUs(j2);
        long timeUsAtPosition = getTimeUsAtPosition(framePositionForTimeUs);
        SeekPoint seekPoint = new SeekPoint(timeUsAtPosition, framePositionForTimeUs);
        if (this.dataSize != -1 && timeUsAtPosition < j2) {
            int i3 = this.frameSize;
            if (((long) i3) + framePositionForTimeUs < this.inputLength) {
                long j3 = framePositionForTimeUs + ((long) i3);
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(getTimeUsAtPosition(j3), j3));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    public long getTimeUsAtPosition(long j2) {
        return getTimeUsAtPosition(j2, this.firstFrameBytePosition, this.bitrate);
    }

    public boolean isSeekable() {
        return this.dataSize != -1 || this.allowSeeksIfLengthUnknown;
    }

    public ConstantBitrateSeekMap(long j2, long j3, int i3, int i4, boolean z2) {
        this.inputLength = j2;
        this.firstFrameBytePosition = j3;
        this.frameSize = i4 == -1 ? 1 : i4;
        this.bitrate = i3;
        this.allowSeeksIfLengthUnknown = z2;
        if (j2 == -1) {
            this.dataSize = -1;
            this.durationUs = C.TIME_UNSET;
            return;
        }
        this.dataSize = j2 - j3;
        this.durationUs = getTimeUsAtPosition(j2, j3, i3);
    }

    private static long getTimeUsAtPosition(long j2, long j3, int i3) {
        return (Math.max(0, j2 - j3) * 8000000) / ((long) i3);
    }
}
