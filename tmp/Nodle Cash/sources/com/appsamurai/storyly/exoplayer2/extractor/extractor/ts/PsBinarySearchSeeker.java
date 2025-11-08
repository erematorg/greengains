package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.BinarySearchSeeker;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.google.common.base.Ascii;
import java.io.IOException;

final class PsBinarySearchSeeker extends BinarySearchSeeker {
    private static final int MINIMUM_SEARCH_RANGE_BYTES = 1000;
    private static final long SEEK_TOLERANCE_US = 100000;
    private static final int TIMESTAMP_SEARCH_BYTES = 20000;

    public static final class PsScrSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final ParsableByteArray packetBuffer;
        private final TimestampAdjuster scrTimestampAdjuster;

        private BinarySearchSeeker.TimestampSearchResult searchForScrValueInBuffer(ParsableByteArray parsableByteArray, long j2, long j3) {
            int i3 = -1;
            int i4 = -1;
            long j4 = -9223372036854775807L;
            while (parsableByteArray.bytesLeft() >= 4) {
                if (PsBinarySearchSeeker.peekIntAtPosition(parsableByteArray.getData(), parsableByteArray.getPosition()) != 442) {
                    parsableByteArray.skipBytes(1);
                } else {
                    parsableByteArray.skipBytes(4);
                    long readScrValueFromPack = PsDurationReader.readScrValueFromPack(parsableByteArray);
                    if (readScrValueFromPack != C.TIME_UNSET) {
                        long adjustTsTimestamp = this.scrTimestampAdjuster.adjustTsTimestamp(readScrValueFromPack);
                        if (adjustTsTimestamp > j2) {
                            return j4 == C.TIME_UNSET ? BinarySearchSeeker.TimestampSearchResult.overestimatedResult(adjustTsTimestamp, j3) : BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j3 + ((long) i4));
                        }
                        if (PsBinarySearchSeeker.SEEK_TOLERANCE_US + adjustTsTimestamp > j2) {
                            return BinarySearchSeeker.TimestampSearchResult.targetFoundResult(j3 + ((long) parsableByteArray.getPosition()));
                        }
                        i4 = parsableByteArray.getPosition();
                        j4 = adjustTsTimestamp;
                    }
                    skipToEndOfCurrentPack(parsableByteArray);
                    i3 = parsableByteArray.getPosition();
                }
            }
            return j4 != C.TIME_UNSET ? BinarySearchSeeker.TimestampSearchResult.underestimatedResult(j4, j3 + ((long) i3)) : BinarySearchSeeker.TimestampSearchResult.NO_TIMESTAMP_IN_RANGE_RESULT;
        }

        private static void skipToEndOfCurrentPack(ParsableByteArray parsableByteArray) {
            int limit = parsableByteArray.limit();
            if (parsableByteArray.bytesLeft() < 10) {
                parsableByteArray.setPosition(limit);
                return;
            }
            parsableByteArray.skipBytes(9);
            int readUnsignedByte = parsableByteArray.readUnsignedByte() & 7;
            if (parsableByteArray.bytesLeft() < readUnsignedByte) {
                parsableByteArray.setPosition(limit);
                return;
            }
            parsableByteArray.skipBytes(readUnsignedByte);
            if (parsableByteArray.bytesLeft() < 4) {
                parsableByteArray.setPosition(limit);
                return;
            }
            if (PsBinarySearchSeeker.peekIntAtPosition(parsableByteArray.getData(), parsableByteArray.getPosition()) == 443) {
                parsableByteArray.skipBytes(4);
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                if (parsableByteArray.bytesLeft() < readUnsignedShort) {
                    parsableByteArray.setPosition(limit);
                    return;
                }
                parsableByteArray.skipBytes(readUnsignedShort);
            }
            while (parsableByteArray.bytesLeft() >= 4 && (r1 = PsBinarySearchSeeker.peekIntAtPosition(parsableByteArray.getData(), parsableByteArray.getPosition())) != 442 && r1 != 441 && (r1 >>> 8) == 1) {
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.bytesLeft() < 2) {
                    parsableByteArray.setPosition(limit);
                    return;
                } else {
                    parsableByteArray.setPosition(Math.min(parsableByteArray.limit(), parsableByteArray.getPosition() + parsableByteArray.readUnsignedShort()));
                }
            }
        }

        public void onSeekFinished() {
            this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        }

        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j2) throws IOException {
            long position = extractorInput.getPosition();
            int min = (int) Math.min(20000, extractorInput.getLength() - position);
            this.packetBuffer.reset(min);
            extractorInput.peekFully(this.packetBuffer.getData(), 0, min);
            return searchForScrValueInBuffer(this.packetBuffer, j2, position);
        }

        private PsScrSeeker(TimestampAdjuster timestampAdjuster) {
            this.scrTimestampAdjuster = timestampAdjuster;
            this.packetBuffer = new ParsableByteArray();
        }
    }

    public PsBinarySearchSeeker(TimestampAdjuster timestampAdjuster, long j2, long j3) {
        super(new BinarySearchSeeker.DefaultSeekTimestampConverter(), new PsScrSeeker(timestampAdjuster), j2, 0, j2 + 1, 0, j3, 188, 1000);
    }

    /* access modifiers changed from: private */
    public static int peekIntAtPosition(byte[] bArr, int i3) {
        return (bArr[i3 + 3] & 255) | ((bArr[i3] & 255) << Ascii.CAN) | ((bArr[i3 + 1] & 255) << 16) | ((bArr[i3 + 2] & 255) << 8);
    }
}
