package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ConstantBitrateSeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.a;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(11);
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    private static final int MAX_PACKET_SIZE = 2048;
    private static final int MAX_SNIFF_BYTES = 8192;
    private static final int NUM_FRAMES_FOR_AVERAGE_FRAME_SIZE = 1000;
    private int averageFrameSize;
    private ExtractorOutput extractorOutput;
    private long firstFramePosition;
    private long firstSampleTimestampUs;
    private final int flags;
    private boolean hasCalculatedAverageFrameSize;
    private boolean hasOutputSeekMap;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private final ParsableByteArray scratch;
    private final ParsableBitArray scratchBits;
    private boolean startedPacket;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public AdtsExtractor() {
        this(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void calculateAverageFrameSize(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r10) throws java.io.IOException {
        /*
            r9 = this;
            boolean r0 = r9.hasCalculatedAverageFrameSize
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = -1
            r9.averageFrameSize = r0
            r10.resetPeekPosition()
            long r1 = r10.getPosition()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0018
            r9.peekId3Header(r10)
        L_0x0018:
            r1 = 0
            r2 = r1
        L_0x001a:
            r5 = 1
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            byte[] r6 = r6.getData()     // Catch:{ EOFException -> 0x0076 }
            r7 = 2
            boolean r6 = r10.peekFully(r6, r1, r7, r5)     // Catch:{ EOFException -> 0x0076 }
            if (r6 == 0) goto L_0x0076
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            r6.setPosition(r1)     // Catch:{ EOFException -> 0x0076 }
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            int r6 = r6.readUnsignedShort()     // Catch:{ EOFException -> 0x0076 }
            boolean r6 = com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.AdtsReader.isAdtsSyncWord(r6)     // Catch:{ EOFException -> 0x0076 }
            if (r6 != 0) goto L_0x003a
            goto L_0x0077
        L_0x003a:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r9.scratch     // Catch:{ EOFException -> 0x0076 }
            byte[] r6 = r6.getData()     // Catch:{ EOFException -> 0x0076 }
            r7 = 4
            boolean r6 = r10.peekFully(r6, r1, r7, r5)     // Catch:{ EOFException -> 0x0076 }
            if (r6 != 0) goto L_0x0048
            goto L_0x0076
        L_0x0048:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r6 = r9.scratchBits     // Catch:{ EOFException -> 0x0076 }
            r7 = 14
            r6.setPosition(r7)     // Catch:{ EOFException -> 0x0076 }
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r6 = r9.scratchBits     // Catch:{ EOFException -> 0x0076 }
            r7 = 13
            int r6 = r6.readBits(r7)     // Catch:{ EOFException -> 0x0076 }
            r7 = 6
            if (r6 <= r7) goto L_0x006c
            long r7 = (long) r6     // Catch:{ EOFException -> 0x0076 }
            long r3 = r3 + r7
            int r2 = r2 + 1
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r2 != r7) goto L_0x0063
            goto L_0x006b
        L_0x0063:
            int r6 = r6 + -6
            boolean r6 = r10.advancePeekPosition(r6, r5)     // Catch:{ EOFException -> 0x0076 }
            if (r6 != 0) goto L_0x001a
        L_0x006b:
            goto L_0x0076
        L_0x006c:
            r9.hasCalculatedAverageFrameSize = r5     // Catch:{ EOFException -> 0x0076 }
            java.lang.String r1 = "Malformed ADTS stream"
            r6 = 0
            com.appsamurai.storyly.exoplayer2.common.ParserException r1 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r1, r6)     // Catch:{ EOFException -> 0x0076 }
            throw r1     // Catch:{ EOFException -> 0x0076 }
        L_0x0076:
            r1 = r2
        L_0x0077:
            r10.resetPeekPosition()
            if (r1 <= 0) goto L_0x0082
            long r0 = (long) r1
            long r3 = r3 / r0
            int r10 = (int) r3
            r9.averageFrameSize = r10
            goto L_0x0084
        L_0x0082:
            r9.averageFrameSize = r0
        L_0x0084:
            r9.hasCalculatedAverageFrameSize = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.AdtsExtractor.calculateAverageFrameSize(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput):void");
    }

    private static int getBitrateFromFrameSize(int i3, long j2) {
        return (int) ((((long) i3) * 8000000) / j2);
    }

    private SeekMap getConstantBitrateSeekMap(long j2, boolean z2) {
        return new ConstantBitrateSeekMap(j2, this.firstFramePosition, getBitrateFromFrameSize(this.averageFrameSize, this.reader.getSampleDurationUs()), this.averageFrameSize, z2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new AdtsExtractor()};
    }

    @RequiresNonNull({"extractorOutput"})
    private void maybeOutputSeekMap(long j2, boolean z2) {
        if (!this.hasOutputSeekMap) {
            boolean z3 = false;
            boolean z4 = (this.flags & 1) != 0 && this.averageFrameSize > 0;
            if (!z4 || this.reader.getSampleDurationUs() != C.TIME_UNSET || z2) {
                if (!z4 || this.reader.getSampleDurationUs() == C.TIME_UNSET) {
                    this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
                } else {
                    ExtractorOutput extractorOutput2 = this.extractorOutput;
                    if ((this.flags & 2) != 0) {
                        z3 = true;
                    }
                    extractorOutput2.seekMap(getConstantBitrateSeekMap(j2, z3));
                }
                this.hasOutputSeekMap = true;
            }
        }
    }

    private int peekId3Header(ExtractorInput extractorInput) throws IOException {
        int i3 = 0;
        while (true) {
            extractorInput.peekFully(this.scratch.getData(), 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != 4801587) {
                break;
            }
            this.scratch.skipBytes(3);
            int readSynchSafeInt = this.scratch.readSynchSafeInt();
            i3 += readSynchSafeInt + 10;
            extractorInput.advancePeekPosition(readSynchSafeInt);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i3);
        if (this.firstFramePosition == -1) {
            this.firstFramePosition = (long) i3;
        }
        return i3;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.reader.createTracks(extractorOutput2, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput2.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        Assertions.checkStateNotNull(this.extractorOutput);
        long length = extractorInput.getLength();
        int i3 = this.flags;
        if (!((i3 & 2) == 0 && ((i3 & 1) == 0 || length == -1))) {
            calculateAverageFrameSize(extractorInput);
        }
        int read = extractorInput.read(this.packetBuffer.getData(), 0, 2048);
        boolean z2 = read == -1;
        maybeOutputSeekMap(length, z2);
        if (z2) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(read);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.packetBuffer);
        return 0;
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        this.startedPacket = false;
        this.reader.seek();
        this.firstSampleTimestampUs = j3;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        int peekId3Header = peekId3Header(extractorInput);
        int i3 = peekId3Header;
        int i4 = 0;
        int i5 = 0;
        do {
            extractorInput.peekFully(this.scratch.getData(), 0, 2);
            this.scratch.setPosition(0);
            if (!AdtsReader.isAdtsSyncWord(this.scratch.readUnsignedShort())) {
                i3++;
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i3);
            } else {
                i4++;
                if (i4 >= 4 && i5 > 188) {
                    return true;
                }
                extractorInput.peekFully(this.scratch.getData(), 0, 4);
                this.scratchBits.setPosition(14);
                int readBits = this.scratchBits.readBits(13);
                if (readBits <= 6) {
                    i3++;
                    extractorInput.resetPeekPosition();
                    extractorInput.advancePeekPosition(i3);
                } else {
                    extractorInput.advancePeekPosition(readBits - 6);
                    i5 += readBits;
                }
            }
            i4 = 0;
            i5 = 0;
        } while (i3 - peekId3Header < 8192);
        return false;
    }

    public AdtsExtractor(int i3) {
        this.flags = (i3 & 2) != 0 ? i3 | 1 : i3;
        this.reader = new AdtsReader(true);
        this.packetBuffer = new ParsableByteArray(2048);
        this.averageFrameSize = -1;
        this.firstFramePosition = -1;
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        this.scratch = parsableByteArray;
        this.scratchBits = new ParsableBitArray(parsableByteArray.getData());
    }
}
