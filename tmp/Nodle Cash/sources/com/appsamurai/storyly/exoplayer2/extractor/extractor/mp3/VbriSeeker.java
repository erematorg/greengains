package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3;

import androidx.annotation.Nullable;
import androidx.compose.animation.core.a;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;

final class VbriSeeker implements Seeker {
    private static final String TAG = "VbriSeeker";
    private final long dataEndPosition;
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    private VbriSeeker(long[] jArr, long[] jArr2, long j2, long j3) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j2;
        this.dataEndPosition = j3;
    }

    @Nullable
    public static VbriSeeker create(long j2, long j3, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int i3;
        long j4 = j2;
        MpegAudioUtil.Header header2 = header;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.skipBytes(10);
        int readInt = parsableByteArray.readInt();
        if (readInt <= 0) {
            return null;
        }
        int i4 = header2.sampleRate;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readInt, ((long) (i4 >= 32000 ? 1152 : 576)) * 1000000, (long) i4);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        long j5 = j3 + ((long) header2.frameSize);
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        int i5 = 0;
        long j6 = j3;
        while (i5 < readUnsignedShort) {
            int i6 = readUnsignedShort2;
            jArr[i5] = (((long) i5) * scaleLargeTimestamp) / ((long) readUnsignedShort);
            long j7 = j5;
            jArr2[i5] = Math.max(j6, j7);
            if (readUnsignedShort3 == 1) {
                i3 = parsableByteArray.readUnsignedByte();
            } else if (readUnsignedShort3 == 2) {
                i3 = parsableByteArray.readUnsignedShort();
            } else if (readUnsignedShort3 == 3) {
                i3 = parsableByteArray.readUnsignedInt24();
            } else if (readUnsignedShort3 != 4) {
                return null;
            } else {
                i3 = parsableByteArray.readUnsignedIntToInt();
            }
            long j8 = (long) i3;
            int i7 = i6;
            j6 += j8 * ((long) i7);
            i5++;
            jArr = jArr;
            readUnsignedShort2 = i7;
            j5 = j7;
        }
        long[] jArr3 = jArr;
        if (!(j4 == -1 || j4 == j6)) {
            StringBuilder u3 = a.u("VBRI data size mismatch: ", j4, ", ");
            u3.append(j6);
            Log.w(TAG, u3.toString());
        }
        return new VbriSeeker(jArr3, jArr2, scaleLargeTimestamp, j6);
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j2, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[binarySearchFloor], this.positions[binarySearchFloor]);
        if (seekPoint.timeUs >= j2 || binarySearchFloor == this.timesUs.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i3 = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i3], this.positions[i3]));
    }

    public long getTimeUs(long j2) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j2, true, true)];
    }

    public boolean isSeekable() {
        return true;
    }
}
