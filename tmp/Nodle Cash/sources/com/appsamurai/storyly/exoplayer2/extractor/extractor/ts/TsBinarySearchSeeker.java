package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.BinarySearchSeeker;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import java.io.IOException;

final class TsBinarySearchSeeker extends BinarySearchSeeker {
    private static final int MINIMUM_SEARCH_RANGE_BYTES = 940;
    private static final long SEEK_TOLERANCE_US = 100000;

    public static final class TsPcrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final ParsableByteArray packetBuffer = new ParsableByteArray();
        private final int pcrPid;
        private final TimestampAdjuster pcrTimestampAdjuster;
        private final int timestampSearchBytes;

        public TsPcrSeeker(int i3, TimestampAdjuster timestampAdjuster, int i4) {
            this.pcrPid = i3;
            this.pcrTimestampAdjuster = timestampAdjuster;
            this.timestampSearchBytes = i4;
        }

        private BinarySearchSeeker.TimestampSearchResult searchForPcrValueInBuffer(ParsableByteArray parsableByteArray, long j2, long j3) {
            int findSyncBytePosition;
            int i3;
            ParsableByteArray parsableByteArray2 = parsableByteArray;
            long j4 = j3;
            int limit = parsableByteArray.limit();
            long j5 = -1;
            long j6 = -1;
            long j7 = -9223372036854775807L;
            while (parsableByteArray.bytesLeft() >= 188 && (i3 = findSyncBytePosition + 188) <= limit) {
                long readPcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray2, (findSyncBytePosition = TsUtil.findSyncBytePosition(parsableByteArray.getData(), parsableByteArray.getPosition(), limit)), this.pcrPid);
                if (readPcrFromPacket != C.TIME_UNSET) {
                    long adjustTsTimestamp = this.pcrTimestampAdjuster.adjustTsTimestamp(readPcrFromPacket);
                    if (adjustTsTimestamp > j2) {
                        return j7 == C.TIME_UNSET ? BinarySearchSeeker.TimestampSearchResult.overestimatedResult(adjustTsTimestamp, j4) : BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j4 + j6);
                    }
                    if (TsBinarySearchSeeker.SEEK_TOLERANCE_US + adjustTsTimestamp > j2) {
                        return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(((long) findSyncBytePosition) + j4);
                    }
                    j6 = (long) findSyncBytePosition;
                    j7 = adjustTsTimestamp;
                }
                parsableByteArray2.setPosition(i3);
                j5 = (long) i3;
            }
            return j7 != C.TIME_UNSET ? BinarySearchSeeker.TimestampSearchResult.underestimatedResult(j7, j4 + j5) : BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }

        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j2) throws IOException {
            long position = extractorInput.getPosition();
            int min = (int) Math.min((long) this.timestampSearchBytes, extractorInput.getLength() - position);
            this.packetBuffer.reset(min);
            extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
            return searchForPcrValueInBuffer(this.packetBuffer, j2, position);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TsBinarySearchSeeker(com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster r17, long r18, long r20, int r22, int r23) {
        /*
            r16 = this;
            com.appsamurai.storyly.exoplayer2.extractor.extractor.BinarySearchSeeker$DefaultSeekTimestampConverter r1 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.BinarySearchSeeker$DefaultSeekTimestampConverter
            r1.<init>()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsBinarySearchSeeker$TsPcrSeeker r2 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsBinarySearchSeeker$TsPcrSeeker
            r0 = r17
            r3 = r22
            r4 = r23
            r2.<init>(r3, r0, r4)
            r3 = 1
            long r7 = r18 + r3
            r13 = 188(0xbc, double:9.3E-322)
            r15 = 940(0x3ac, float:1.317E-42)
            r5 = 0
            r9 = 0
            r0 = r16
            r3 = r18
            r11 = r20
            r0.<init>(r1, r2, r3, r5, r7, r9, r11, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsBinarySearchSeeker.<init>(com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster, long, long, int, int):void");
    }
}
