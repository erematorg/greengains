package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3;

import androidx.annotation.Nullable;
import androidx.compose.animation.core.a;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;

final class XingSeeker implements Seeker {
    private static final String TAG = "XingSeeker";
    private final long dataEndPosition;
    private final long dataSize;
    private final long dataStartPosition;
    private final long durationUs;
    @Nullable
    private final long[] tableOfContents;
    private final int xingFrameSize;

    private XingSeeker(long j2, int i3, long j3) {
        this(j2, i3, j3, -1, (long[]) null);
    }

    @Nullable
    public static XingSeeker create(long j2, long j3, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int readUnsignedIntToInt;
        long j4 = j2;
        MpegAudioUtil.Header header2 = header;
        int i3 = header2.samplesPerFrame;
        int i4 = header2.sampleRate;
        int readInt = parsableByteArray.readInt();
        if ((readInt & 1) != 1 || (readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt()) == 0) {
            return null;
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readUnsignedIntToInt, ((long) i3) * 1000000, (long) i4);
        if ((readInt & 6) != 6) {
            return new XingSeeker(j3, header2.frameSize, scaleLargeTimestamp);
        }
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long[] jArr = new long[100];
        for (int i5 = 0; i5 < 100; i5++) {
            jArr[i5] = (long) parsableByteArray.readUnsignedByte();
        }
        if (j4 != -1) {
            long j5 = j3 + readUnsignedInt;
            if (j4 != j5) {
                StringBuilder u3 = a.u("XING data size mismatch: ", j4, ", ");
                u3.append(j5);
                Log.w(TAG, u3.toString());
            }
        }
        return new XingSeeker(j3, header2.frameSize, scaleLargeTimestamp, readUnsignedInt, jArr);
    }

    private long getTimeUsForTableIndex(int i3) {
        return (this.durationUs * ((long) i3)) / 100;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        if (!isSeekable()) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.dataStartPosition + ((long) this.xingFrameSize)));
        }
        long constrainValue = Util.constrainValue(j2, 0, this.durationUs);
        double d2 = (((double) constrainValue) * 100.0d) / ((double) this.durationUs);
        double d3 = 0.0d;
        if (d2 > 0.0d) {
            if (d2 >= 100.0d) {
                d3 = 256.0d;
            } else {
                int i3 = (int) d2;
                long[] jArr = (long[]) Assertions.checkStateNotNull(this.tableOfContents);
                double d4 = (double) jArr[i3];
                d3 = androidx.compose.ui.autofill.a.a(i3 == 99 ? 256.0d : (double) jArr[i3 + 1], d4, d2 - ((double) i3), d4);
            }
        }
        return new SeekMap.SeekPoints(new SeekPoint(constrainValue, this.dataStartPosition + Util.constrainValue(Math.round((d3 / 256.0d) * ((double) this.dataSize)), (long) this.xingFrameSize, this.dataSize - 1)));
    }

    public long getTimeUs(long j2) {
        long j3 = j2 - this.dataStartPosition;
        if (!isSeekable() || j3 <= ((long) this.xingFrameSize)) {
            return 0;
        }
        long[] jArr = (long[]) Assertions.checkStateNotNull(this.tableOfContents);
        double d2 = (((double) j3) * 256.0d) / ((double) this.dataSize);
        int binarySearchFloor = Util.binarySearchFloor(jArr, (long) d2, true, true);
        long timeUsForTableIndex = getTimeUsForTableIndex(binarySearchFloor);
        long j4 = jArr[binarySearchFloor];
        int i3 = binarySearchFloor + 1;
        long timeUsForTableIndex2 = getTimeUsForTableIndex(i3);
        long j5 = binarySearchFloor == 99 ? 256 : jArr[i3];
        return Math.round((j4 == j5 ? 0.0d : (d2 - ((double) j4)) / ((double) (j5 - j4))) * ((double) (timeUsForTableIndex2 - timeUsForTableIndex))) + timeUsForTableIndex;
    }

    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    private XingSeeker(long j2, int i3, long j3, long j4, @Nullable long[] jArr) {
        this.dataStartPosition = j2;
        this.xingFrameSize = i3;
        this.durationUs = j3;
        this.tableOfContents = jArr;
        this.dataSize = j4;
        this.dataEndPosition = j4 != -1 ? j2 + j4 : -1;
    }
}
