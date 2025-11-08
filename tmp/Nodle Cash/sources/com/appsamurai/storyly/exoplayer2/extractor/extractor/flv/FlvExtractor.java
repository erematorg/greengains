package com.appsamurai.storyly.exoplayer2.extractor.extractor.flv;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.a;
import java.io.IOException;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class FlvExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(3);
    private static final int FLV_HEADER_SIZE = 9;
    private static final int FLV_TAG = 4607062;
    private static final int FLV_TAG_HEADER_SIZE = 11;
    private static final int STATE_READING_FLV_HEADER = 1;
    private static final int STATE_READING_TAG_DATA = 4;
    private static final int STATE_READING_TAG_HEADER = 3;
    private static final int STATE_SKIPPING_TO_TAG_HEADER = 2;
    private static final int TAG_TYPE_AUDIO = 8;
    private static final int TAG_TYPE_SCRIPT_DATA = 18;
    private static final int TAG_TYPE_VIDEO = 9;
    private AudioTagPayloadReader audioReader;
    private int bytesToNextTagHeader;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray headerBuffer = new ParsableByteArray(9);
    private long mediaTagTimestampOffsetUs;
    private final ScriptTagPayloadReader metadataReader = new ScriptTagPayloadReader();
    private boolean outputFirstSample;
    private boolean outputSeekMap;
    private final ParsableByteArray scratch = new ParsableByteArray(4);
    private int state = 1;
    private final ParsableByteArray tagData = new ParsableByteArray();
    private int tagDataSize;
    private final ParsableByteArray tagHeaderBuffer = new ParsableByteArray(11);
    private long tagTimestampUs;
    private int tagType;
    private VideoTagPayloadReader videoReader;

    @RequiresNonNull({"extractorOutput"})
    private void ensureReadyForMediaOutput() {
        if (!this.outputSeekMap) {
            this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
            this.outputSeekMap = true;
        }
    }

    private long getCurrentTimestampUs() {
        if (this.outputFirstSample) {
            return this.mediaTagTimestampOffsetUs + this.tagTimestampUs;
        }
        if (this.metadataReader.getDurationUs() == C.TIME_UNSET) {
            return 0;
        }
        return this.tagTimestampUs;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new FlvExtractor()};
    }

    private ParsableByteArray prepareTagData(ExtractorInput extractorInput) throws IOException {
        if (this.tagDataSize > this.tagData.capacity()) {
            ParsableByteArray parsableByteArray = this.tagData;
            parsableByteArray.reset(new byte[Math.max(parsableByteArray.capacity() * 2, this.tagDataSize)], 0);
        } else {
            this.tagData.setPosition(0);
        }
        this.tagData.setLimit(this.tagDataSize);
        extractorInput.readFully(this.tagData.getData(), 0, this.tagDataSize);
        return this.tagData;
    }

    @RequiresNonNull({"extractorOutput"})
    private boolean readFlvHeader(ExtractorInput extractorInput) throws IOException {
        boolean z2 = false;
        if (!extractorInput.readFully(this.headerBuffer.getData(), 0, 9, true)) {
            return false;
        }
        this.headerBuffer.setPosition(0);
        this.headerBuffer.skipBytes(4);
        int readUnsignedByte = this.headerBuffer.readUnsignedByte();
        boolean z3 = (readUnsignedByte & 4) != 0;
        if ((readUnsignedByte & 1) != 0) {
            z2 = true;
        }
        if (z3 && this.audioReader == null) {
            this.audioReader = new AudioTagPayloadReader(this.extractorOutput.track(8, 1));
        }
        if (z2 && this.videoReader == null) {
            this.videoReader = new VideoTagPayloadReader(this.extractorOutput.track(9, 2));
        }
        this.extractorOutput.endTracks();
        this.bytesToNextTagHeader = this.headerBuffer.readInt() - 5;
        this.state = 2;
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008b  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"extractorOutput"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readTagData(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r10) throws java.io.IOException {
        /*
            r9 = this;
            long r0 = r9.getCurrentTimestampUs()
            int r2 = r9.tagType
            r3 = 8
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = 1
            if (r2 != r3) goto L_0x0023
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.AudioTagPayloadReader r3 = r9.audioReader
            if (r3 == 0) goto L_0x0023
            r9.ensureReadyForMediaOutput()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.AudioTagPayloadReader r2 = r9.audioReader
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
        L_0x0021:
            r0 = r6
            goto L_0x0075
        L_0x0023:
            r3 = 9
            if (r2 != r3) goto L_0x0039
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.VideoTagPayloadReader r3 = r9.videoReader
            if (r3 == 0) goto L_0x0039
            r9.ensureReadyForMediaOutput()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.VideoTagPayloadReader r2 = r9.videoReader
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
            goto L_0x0021
        L_0x0039:
            r3 = 18
            if (r2 != r3) goto L_0x006e
            boolean r2 = r9.outputSeekMap
            if (r2 != 0) goto L_0x006e
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.ScriptTagPayloadReader r2 = r9.metadataReader
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r10 = r9.prepareTagData(r10)
            boolean r10 = r2.consume(r10, r0)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.ScriptTagPayloadReader r0 = r9.metadataReader
            long r0 = r0.getDurationUs()
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x0021
            com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput r2 = r9.extractorOutput
            com.appsamurai.storyly.exoplayer2.extractor.extractor.IndexSeekMap r3 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.IndexSeekMap
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.ScriptTagPayloadReader r7 = r9.metadataReader
            long[] r7 = r7.getKeyFrameTagPositions()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.ScriptTagPayloadReader r8 = r9.metadataReader
            long[] r8 = r8.getKeyFrameTimesUs()
            r3.<init>(r7, r8, r0)
            r2.seekMap(r3)
            r9.outputSeekMap = r6
            goto L_0x0021
        L_0x006e:
            int r0 = r9.tagDataSize
            r10.skipFully(r0)
            r10 = 0
            r0 = r10
        L_0x0075:
            boolean r1 = r9.outputFirstSample
            if (r1 != 0) goto L_0x008f
            if (r10 == 0) goto L_0x008f
            r9.outputFirstSample = r6
            com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.ScriptTagPayloadReader r10 = r9.metadataReader
            long r1 = r10.getDurationUs()
            int r10 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x008b
            long r1 = r9.tagTimestampUs
            long r1 = -r1
            goto L_0x008d
        L_0x008b:
            r1 = 0
        L_0x008d:
            r9.mediaTagTimestampOffsetUs = r1
        L_0x008f:
            r10 = 4
            r9.bytesToNextTagHeader = r10
            r10 = 2
            r9.state = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.flv.FlvExtractor.readTagData(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput):boolean");
    }

    private boolean readTagHeader(ExtractorInput extractorInput) throws IOException {
        if (!extractorInput.readFully(this.tagHeaderBuffer.getData(), 0, 11, true)) {
            return false;
        }
        this.tagHeaderBuffer.setPosition(0);
        this.tagType = this.tagHeaderBuffer.readUnsignedByte();
        this.tagDataSize = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = (long) this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = (((long) (this.tagHeaderBuffer.readUnsignedByte() << 24)) | this.tagTimestampUs) * 1000;
        this.tagHeaderBuffer.skipBytes(3);
        this.state = 4;
        return true;
    }

    private void skipToTagHeader(ExtractorInput extractorInput) throws IOException {
        extractorInput.skipFully(this.bytesToNextTagHeader);
        this.bytesToNextTagHeader = 0;
        this.state = 3;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        Assertions.checkStateNotNull(this.extractorOutput);
        while (true) {
            int i3 = this.state;
            if (i3 != 1) {
                if (i3 == 2) {
                    skipToTagHeader(extractorInput);
                } else if (i3 != 3) {
                    if (i3 != 4) {
                        throw new IllegalStateException();
                    } else if (readTagData(extractorInput)) {
                        return 0;
                    }
                } else if (!readTagHeader(extractorInput)) {
                    return -1;
                }
            } else if (!readFlvHeader(extractorInput)) {
                return -1;
            }
        }
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        if (j2 == 0) {
            this.state = 1;
            this.outputFirstSample = false;
        } else {
            this.state = 3;
        }
        this.bytesToNextTagHeader = 0;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 3);
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() != FLV_TAG) {
            return false;
        }
        extractorInput.peekFully(this.scratch.getData(), 0, 2);
        this.scratch.setPosition(0);
        if ((this.scratch.readUnsignedShort() & ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) != 0) {
            return false;
        }
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        int readInt = this.scratch.readInt();
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(readInt);
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        return this.scratch.readInt() == 0;
    }
}
