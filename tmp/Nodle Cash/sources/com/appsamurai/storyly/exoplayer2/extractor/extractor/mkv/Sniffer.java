package com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv;

import androidx.collection.SieveCacheKt;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import java.io.IOException;

final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    private long readUint(ExtractorInput extractorInput) throws IOException {
        int i3 = 0;
        extractorInput.peekFully(this.scratch.getData(), 0, 1);
        byte b3 = this.scratch.getData()[0] & 255;
        if (b3 == 0) {
            return Long.MIN_VALUE;
        }
        int i4 = 128;
        int i5 = 0;
        while ((b3 & i4) == 0) {
            i4 >>= 1;
            i5++;
        }
        int i6 = b3 & (~i4);
        extractorInput.peekFully(this.scratch.getData(), 1, i5);
        while (i3 < i5) {
            i3++;
            i6 = (this.scratch.getData()[i3] & 255) + (i6 << 8);
        }
        this.peekLength = i5 + 1 + this.peekLength;
        return (long) i6;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        long readUint;
        int i3;
        long length = extractorInput.getLength();
        int i4 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        long j2 = 1024;
        if (i4 != 0 && length <= 1024) {
            j2 = length;
        }
        int i5 = (int) j2;
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        long readUnsignedInt = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (readUnsignedInt != 440786851) {
            int i6 = this.peekLength + 1;
            this.peekLength = i6;
            if (i6 == i5) {
                return false;
            }
            extractorInput.peekFully(this.scratch.getData(), 0, 1);
            readUnsignedInt = ((readUnsignedInt << 8) & -256) | ((long) (this.scratch.getData()[0] & 255));
        }
        long readUint2 = readUint(extractorInput);
        long j3 = (long) this.peekLength;
        if (readUint2 == Long.MIN_VALUE) {
            return false;
        }
        if (i4 != 0 && j3 + readUint2 >= length) {
            return false;
        }
        while (true) {
            int i7 = this.peekLength;
            long j4 = j3 + readUint2;
            if (((long) i7) >= j4) {
                return ((long) i7) == j4;
            }
            if (readUint(extractorInput) != Long.MIN_VALUE && readUint >= 0 && readUint <= SieveCacheKt.NodeLinkMask) {
                if (i3 != 0) {
                    int readUint3 = (int) (readUint = readUint(extractorInput));
                    extractorInput.advancePeekPosition(readUint3);
                    this.peekLength += readUint3;
                }
            }
        }
        return false;
    }
}
