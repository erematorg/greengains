package com.appsamurai.storyly.exoplayer2.extractor.extractor.flac;

import com.appsamurai.storyly.exoplayer2.extractor.extractor.BinarySearchSeeker;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacFrameReader;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacStreamMetadata;
import java.io.IOException;

final class FlacBinarySearchSeeker extends BinarySearchSeeker {

    public static final class FlacTimestampSeeker implements BinarySearchSeeker.TimestampSeeker {
        private final FlacStreamMetadata flacStreamMetadata;
        private final int frameStartMarker;
        private final FlacFrameReader.SampleNumberHolder sampleNumberHolder;

        private long findNextFrame(ExtractorInput extractorInput) throws IOException {
            while (extractorInput.getPeekPosition() < extractorInput.getLength() - 6 && !FlacFrameReader.checkFrameHeaderFromPeek(extractorInput, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder)) {
                extractorInput.advancePeekPosition(1);
            }
            if (extractorInput.getPeekPosition() < extractorInput.getLength() - 6) {
                return this.sampleNumberHolder.sampleNumber;
            }
            extractorInput.advancePeekPosition((int) (extractorInput.getLength() - extractorInput.getPeekPosition()));
            return this.flacStreamMetadata.totalSamples;
        }

        public BinarySearchSeeker.TimestampSearchResult searchForTimestamp(ExtractorInput extractorInput, long j2) throws IOException {
            long position = extractorInput.getPosition();
            long findNextFrame = findNextFrame(extractorInput);
            long peekPosition = extractorInput.getPeekPosition();
            extractorInput.advancePeekPosition(Math.max(6, this.flacStreamMetadata.minFrameSize));
            long findNextFrame2 = findNextFrame(extractorInput);
            return (findNextFrame > j2 || findNextFrame2 <= j2) ? findNextFrame2 <= j2 ? BinarySearchSeeker.TimestampSearchResult.underestimatedResult(findNextFrame2, extractorInput.getPeekPosition()) : BinarySearchSeeker.TimestampSearchResult.overestimatedResult(findNextFrame, position) : BinarySearchSeeker.TimestampSearchResult.targetFoundResult(peekPosition);
        }

        private FlacTimestampSeeker(FlacStreamMetadata flacStreamMetadata2, int i3) {
            this.flacStreamMetadata = flacStreamMetadata2;
            this.frameStartMarker = i3;
            this.sampleNumberHolder = new FlacFrameReader.SampleNumberHolder();
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FlacBinarySearchSeeker(com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacStreamMetadata r17, int r18, long r19, long r21) {
        /*
            r16 = this;
            r0 = r17
            java.util.Objects.requireNonNull(r17)
            A1.a r1 = new A1.a
            r2 = 14
            r1.<init>(r0, r2)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flac.FlacBinarySearchSeeker$FlacTimestampSeeker r2 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.flac.FlacBinarySearchSeeker$FlacTimestampSeeker
            r3 = 0
            r4 = r18
            r2.<init>(r0, r4)
            long r3 = r17.getDurationUs()
            long r7 = r0.totalSamples
            long r13 = r17.getApproxBytesPerFrame()
            r5 = 6
            int r0 = r0.minFrameSize
            int r15 = java.lang.Math.max(r5, r0)
            r5 = 0
            r0 = r16
            r9 = r19
            r11 = r21
            r0.<init>(r1, r2, r3, r5, r7, r9, r11, r13, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.flac.FlacBinarySearchSeeker.<init>(com.appsamurai.storyly.exoplayer2.extractor.extractor.FlacStreamMetadata, int, long, long):void");
    }
}
