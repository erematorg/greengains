package com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv;

import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import java.io.IOException;

final class VarintReader {
    private static final int STATE_BEGIN_READING = 0;
    private static final int STATE_READ_CONTENTS = 1;
    private static final long[] VARINT_LENGTH_MASKS = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private final byte[] scratch = new byte[8];
    private int state;

    public static long assembleVarint(byte[] bArr, int i3, boolean z2) {
        long j2 = ((long) bArr[0]) & 255;
        if (z2) {
            j2 &= ~VARINT_LENGTH_MASKS[i3 - 1];
        }
        for (int i4 = 1; i4 < i3; i4++) {
            j2 = (j2 << 8) | (((long) bArr[i4]) & 255);
        }
        return j2;
    }

    public static int parseUnsignedVarintLength(int i3) {
        int i4 = 0;
        while (true) {
            long[] jArr = VARINT_LENGTH_MASKS;
            if (i4 >= jArr.length) {
                return -1;
            }
            if ((jArr[i4] & ((long) i3)) != 0) {
                return i4 + 1;
            }
            i4++;
        }
    }

    public int getLastLength() {
        return this.length;
    }

    public long readUnsignedVarint(ExtractorInput extractorInput, boolean z2, boolean z3, int i3) throws IOException {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z2)) {
                return -1;
            }
            int parseUnsignedVarintLength = parseUnsignedVarintLength(this.scratch[0] & 255);
            this.length = parseUnsignedVarintLength;
            if (parseUnsignedVarintLength != -1) {
                this.state = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        int i4 = this.length;
        if (i4 > i3) {
            this.state = 0;
            return -2;
        }
        if (i4 != 1) {
            extractorInput.readFully(this.scratch, 1, i4 - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, z3);
    }

    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}
