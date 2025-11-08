package com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;
import java.io.EOFException;
import java.io.IOException;

final class DefaultOggSeeker implements OggSeeker {
    private static final int DEFAULT_OFFSET = 30000;
    private static final int MATCH_BYTE_RANGE = 100000;
    private static final int MATCH_RANGE = 72000;
    private static final int STATE_IDLE = 4;
    private static final int STATE_READ_LAST_PAGE = 1;
    private static final int STATE_SEEK = 2;
    private static final int STATE_SEEK_TO_END = 0;
    private static final int STATE_SKIP = 3;
    private long end;
    private long endGranule;
    private final OggPageHeader pageHeader;
    /* access modifiers changed from: private */
    public final long payloadEndPosition;
    /* access modifiers changed from: private */
    public final long payloadStartPosition;
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private int state;
    /* access modifiers changed from: private */
    public final StreamReader streamReader;
    private long targetGranule;
    /* access modifiers changed from: private */
    public long totalGranules;

    public final class OggSeekMap implements SeekMap {
        private OggSeekMap() {
        }

        public long getDurationUs() {
            return DefaultOggSeeker.this.streamReader.convertGranuleToTime(DefaultOggSeeker.this.totalGranules);
        }

        public SeekMap.SeekPoints getSeekPoints(long j2) {
            long convertTimeToGranule = DefaultOggSeeker.this.streamReader.convertTimeToGranule(j2);
            return new SeekMap.SeekPoints(new SeekPoint(j2, Util.constrainValue(((((DefaultOggSeeker.this.payloadEndPosition - DefaultOggSeeker.this.payloadStartPosition) * convertTimeToGranule) / DefaultOggSeeker.this.totalGranules) + DefaultOggSeeker.this.payloadStartPosition) - 30000, DefaultOggSeeker.this.payloadStartPosition, DefaultOggSeeker.this.payloadEndPosition - 1)));
        }

        public boolean isSeekable() {
            return true;
        }
    }

    public DefaultOggSeeker(StreamReader streamReader2, long j2, long j3, long j4, long j5, boolean z2) {
        Assertions.checkArgument(j2 >= 0 && j3 > j2);
        this.streamReader = streamReader2;
        this.payloadStartPosition = j2;
        this.payloadEndPosition = j3;
        if (j4 == j3 - j2 || z2) {
            this.totalGranules = j5;
            this.state = 4;
        } else {
            this.state = 0;
        }
        this.pageHeader = new OggPageHeader();
    }

    private long getNextSeekPosition(ExtractorInput extractorInput) throws IOException {
        ExtractorInput extractorInput2 = extractorInput;
        if (this.start == this.end) {
            return -1;
        }
        long position = extractorInput.getPosition();
        if (!this.pageHeader.skipToNextPage(extractorInput2, this.end)) {
            long j2 = this.start;
            if (j2 != position) {
                return j2;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.pageHeader.populate(extractorInput2, false);
        extractorInput.resetPeekPosition();
        long j3 = this.targetGranule;
        OggPageHeader oggPageHeader = this.pageHeader;
        long j4 = oggPageHeader.granulePosition;
        long j5 = j3 - j4;
        int i3 = oggPageHeader.headerSize + oggPageHeader.bodySize;
        if (0 <= j5 && j5 < 72000) {
            return -1;
        }
        int i4 = (j5 > 0 ? 1 : (j5 == 0 ? 0 : -1));
        if (i4 < 0) {
            this.end = position;
            this.endGranule = j4;
        } else {
            this.start = extractorInput.getPosition() + ((long) i3);
            this.startGranule = this.pageHeader.granulePosition;
        }
        long j6 = this.end;
        long j7 = this.start;
        if (j6 - j7 < 100000) {
            this.end = j7;
            return j7;
        }
        long position2 = extractorInput.getPosition() - (((long) i3) * (i4 <= 0 ? 2 : 1));
        long j8 = this.end;
        long j9 = this.start;
        return Util.constrainValue((((j8 - j9) * j5) / (this.endGranule - this.startGranule)) + position2, j9, j8 - 1);
    }

    private void skipToPageOfTargetGranule(ExtractorInput extractorInput) throws IOException {
        while (true) {
            this.pageHeader.skipToNextPage(extractorInput);
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            if (oggPageHeader.granulePosition > this.targetGranule) {
                extractorInput.resetPeekPosition();
                return;
            }
            extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
            this.start = extractorInput.getPosition();
            this.startGranule = this.pageHeader.granulePosition;
        }
    }

    public long read(ExtractorInput extractorInput) throws IOException {
        int i3 = this.state;
        if (i3 == 0) {
            long position = extractorInput.getPosition();
            this.positionBeforeSeekToEnd = position;
            this.state = 1;
            long j2 = this.payloadEndPosition - 65307;
            if (j2 > position) {
                return j2;
            }
        } else if (i3 != 1) {
            if (i3 == 2) {
                long nextSeekPosition = getNextSeekPosition(extractorInput);
                if (nextSeekPosition != -1) {
                    return nextSeekPosition;
                }
                this.state = 3;
            } else if (i3 != 3) {
                if (i3 == 4) {
                    return -1;
                }
                throw new IllegalStateException();
            }
            skipToPageOfTargetGranule(extractorInput);
            this.state = 4;
            return -(this.startGranule + 2);
        }
        this.totalGranules = readGranuleOfLastPage(extractorInput);
        this.state = 4;
        return this.positionBeforeSeekToEnd;
    }

    @VisibleForTesting
    public long readGranuleOfLastPage(ExtractorInput extractorInput) throws IOException {
        this.pageHeader.reset();
        if (this.pageHeader.skipToNextPage(extractorInput)) {
            this.pageHeader.populate(extractorInput, false);
            OggPageHeader oggPageHeader = this.pageHeader;
            extractorInput.skipFully(oggPageHeader.headerSize + oggPageHeader.bodySize);
            long j2 = this.pageHeader.granulePosition;
            while (true) {
                OggPageHeader oggPageHeader2 = this.pageHeader;
                if ((oggPageHeader2.type & 4) == 4 || !oggPageHeader2.skipToNextPage(extractorInput) || extractorInput.getPosition() >= this.payloadEndPosition || !this.pageHeader.populate(extractorInput, true)) {
                    break;
                }
                OggPageHeader oggPageHeader3 = this.pageHeader;
                if (!ExtractorUtil.skipFullyQuietly(extractorInput, oggPageHeader3.headerSize + oggPageHeader3.bodySize)) {
                    break;
                }
                j2 = this.pageHeader.granulePosition;
            }
            return j2;
        }
        throw new EOFException();
    }

    public void startSeek(long j2) {
        this.targetGranule = Util.constrainValue(j2, 0, this.totalGranules - 1);
        this.state = 2;
        this.start = this.payloadStartPosition;
        this.end = this.payloadEndPosition;
        this.startGranule = 0;
        this.endGranule = this.totalGranules;
    }

    @Nullable
    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) {
            return new OggSeekMap();
        }
        return null;
    }
}
