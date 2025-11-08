package com.appsamurai.storyly.exoplayer2.extractor.extractor.avi;

import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;

final class AviStreamHeaderChunk implements AviChunk {
    private static final String TAG = "AviStreamHeaderChunk";
    public final int initialFrames;
    public final int length;
    public final int rate;
    public final int scale;
    public final int streamType;
    public final int suggestedBufferSize;

    private AviStreamHeaderChunk(int i3, int i4, int i5, int i6, int i7, int i8) {
        this.streamType = i3;
        this.initialFrames = i4;
        this.scale = i5;
        this.rate = i6;
        this.length = i7;
        this.suggestedBufferSize = i8;
    }

    public static AviStreamHeaderChunk parseFrom(ParsableByteArray parsableByteArray) {
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(12);
        int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt3 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt4 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(4);
        int readLittleEndianInt5 = parsableByteArray.readLittleEndianInt();
        int readLittleEndianInt6 = parsableByteArray.readLittleEndianInt();
        parsableByteArray.skipBytes(8);
        return new AviStreamHeaderChunk(readLittleEndianInt, readLittleEndianInt2, readLittleEndianInt3, readLittleEndianInt4, readLittleEndianInt5, readLittleEndianInt6);
    }

    public long getDurationUs() {
        return Util.scaleLargeTimestamp((long) this.length, ((long) this.scale) * 1000000, (long) this.rate);
    }

    public float getFrameRate() {
        return ((float) this.rate) / ((float) this.scale);
    }

    public int getTrackType() {
        int i3 = this.streamType;
        if (i3 == 1935960438) {
            return 2;
        }
        if (i3 == 1935963489) {
            return 1;
        }
        if (i3 == 1937012852) {
            return 3;
        }
        Log.w(TAG, "Found unsupported streamType fourCC: " + Integer.toHexString(this.streamType));
        return -1;
    }

    public int getType() {
        return AviExtractor.FOURCC_strh;
    }
}
