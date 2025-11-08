package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyTrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.GaplessInfoHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Id3Peeker;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.a;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Decoder;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.MlltFrame;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class Mp3Extractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new a(5);
    public static final int FLAG_DISABLE_ID3_METADATA = 8;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    public static final int FLAG_ENABLE_INDEX_SEEKING = 4;
    private static final int MAX_SNIFF_BYTES = 32768;
    private static final int MAX_SYNC_BYTES = 131072;
    private static final int MPEG_AUDIO_HEADER_MASK = -128000;
    private static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = new com.appsamurai.storyly.exoplayer2.core.source.chunk.a(5);
    private static final int SCRATCH_LENGTH = 10;
    private static final int SEEK_HEADER_INFO = 1231971951;
    private static final int SEEK_HEADER_UNSET = 0;
    private static final int SEEK_HEADER_VBRI = 1447187017;
    private static final int SEEK_HEADER_XING = 1483304551;
    private long basisTimeUs;
    private TrackOutput currentTrackOutput;
    private boolean disableSeeking;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private boolean isSeekInProgress;
    @Nullable
    private Metadata metadata;
    private TrackOutput realTrackOutput;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private long seekTimeUs;
    private Seeker seeker;
    private final TrackOutput skippingTrackOutput;
    private final MpegAudioUtil.Header synchronizedHeader;
    private int synchronizedHeaderData;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public Mp3Extractor() {
        this(0);
    }

    @EnsuresNonNull({"extractorOutput", "realTrackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Seeker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.MlltSeeker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.IndexSeeker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.IndexSeeker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.IndexSeeker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.IndexSeeker} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.IndexSeeker} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        if (r0 == null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        r0 = null;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Seeker computeSeeker(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r12) throws java.io.IOException {
        /*
            r11 = this;
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Seeker r0 = r11.maybeReadSeekFrame(r12)
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata r1 = r11.metadata
            long r2 = r12.getPosition()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.MlltSeeker r1 = maybeHandleSeekMetadata(r1, r2)
            boolean r2 = r11.disableSeeking
            if (r2 == 0) goto L_0x0018
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Seeker$UnseekableSeeker r11 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Seeker$UnseekableSeeker
            r11.<init>()
            return r11
        L_0x0018:
            int r2 = r11.flags
            r2 = r2 & 4
            if (r2 == 0) goto L_0x004a
            if (r1 == 0) goto L_0x002b
            long r2 = r1.getDurationUs()
            long r0 = r1.getDataEndPosition()
        L_0x0028:
            r9 = r0
            r5 = r2
            goto L_0x003f
        L_0x002b:
            if (r0 == 0) goto L_0x0036
            long r2 = r0.getDurationUs()
            long r0 = r0.getDataEndPosition()
            goto L_0x0028
        L_0x0036:
            com.appsamurai.storyly.exoplayer2.common.metadata.Metadata r0 = r11.metadata
            long r2 = getId3TlenUs(r0)
            r0 = -1
            goto L_0x0028
        L_0x003f:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.IndexSeeker r0 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.IndexSeeker
            long r7 = r12.getPosition()
            r4 = r0
            r4.<init>(r5, r7, r9)
            goto L_0x0052
        L_0x004a:
            if (r1 == 0) goto L_0x004e
            r0 = r1
            goto L_0x0052
        L_0x004e:
            if (r0 == 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r0 = 0
        L_0x0052:
            r1 = 1
            if (r0 == 0) goto L_0x0060
            boolean r2 = r0.isSeekable()
            if (r2 != 0) goto L_0x006c
            int r2 = r11.flags
            r2 = r2 & r1
            if (r2 == 0) goto L_0x006c
        L_0x0060:
            int r0 = r11.flags
            r0 = r0 & 2
            if (r0 == 0) goto L_0x0067
            goto L_0x0068
        L_0x0067:
            r1 = 0
        L_0x0068:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Seeker r0 = r11.getConstantBitrateSeeker(r12, r1)
        L_0x006c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Mp3Extractor.computeSeeker(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput):com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Seeker");
    }

    private long computeTimeUs(long j2) {
        return ((j2 * 1000000) / ((long) this.synchronizedHeader.sampleRate)) + this.basisTimeUs;
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput, boolean z2) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, z2);
    }

    private static long getId3TlenUs(@Nullable Metadata metadata2) {
        if (metadata2 == null) {
            return C.TIME_UNSET;
        }
        int length = metadata2.length();
        for (int i3 = 0; i3 < length; i3++) {
            Metadata.Entry entry = metadata2.get(i3);
            if (entry instanceof TextInformationFrame) {
                TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                if (textInformationFrame.id.equals("TLEN")) {
                    return Util.msToUs(Long.parseLong(textInformationFrame.value));
                }
            }
        }
        return C.TIME_UNSET;
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i3) {
        if (parsableByteArray.limit() >= i3 + 4) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (readInt == SEEK_HEADER_XING || readInt == SEEK_HEADER_INFO) {
                return readInt;
            }
        }
        if (parsableByteArray.limit() < 40) {
            return 0;
        }
        parsableByteArray.setPosition(36);
        if (parsableByteArray.readInt() == SEEK_HEADER_VBRI) {
            return SEEK_HEADER_VBRI;
        }
        return 0;
    }

    private static boolean headersMatch(int i3, long j2) {
        return ((long) (i3 & MPEG_AUDIO_HEADER_MASK)) == (j2 & -128000);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp3Extractor()};
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$static$1(int i3, int i4, int i5, int i6, int i7) {
        return (i4 == 67 && i5 == 79 && i6 == 77 && (i7 == 77 || i3 == 2)) || (i4 == 77 && i5 == 76 && i6 == 76 && (i7 == 84 || i3 == 2));
    }

    @Nullable
    private static MlltSeeker maybeHandleSeekMetadata(@Nullable Metadata metadata2, long j2) {
        if (metadata2 == null) {
            return null;
        }
        int length = metadata2.length();
        for (int i3 = 0; i3 < length; i3++) {
            Metadata.Entry entry = metadata2.get(i3);
            if (entry instanceof MlltFrame) {
                return MlltSeeker.create(j2, (MlltFrame) entry, getId3TlenUs(metadata2));
            }
        }
        return null;
    }

    @Nullable
    private Seeker maybeReadSeekFrame(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.synchronizedHeader.frameSize);
        extractorInput.peekFully(parsableByteArray.getData(), 0, this.synchronizedHeader.frameSize);
        MpegAudioUtil.Header header = this.synchronizedHeader;
        int i3 = 21;
        if ((header.version & 1) != 0) {
            if (header.channels != 1) {
                i3 = 36;
            }
        } else if (header.channels == 1) {
            i3 = 13;
        }
        int i4 = i3;
        int seekFrameHeader = getSeekFrameHeader(parsableByteArray, i4);
        if (seekFrameHeader == SEEK_HEADER_XING || seekFrameHeader == SEEK_HEADER_INFO) {
            XingSeeker create = XingSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
            if (create != null && !this.gaplessInfoHolder.hasGaplessInfo()) {
                extractorInput.resetPeekPosition();
                extractorInput.advancePeekPosition(i4 + 141);
                extractorInput.peekFully(this.scratch.getData(), 0, 3);
                this.scratch.setPosition(0);
                this.gaplessInfoHolder.setFromXingHeaderValue(this.scratch.readUnsignedInt24());
            }
            extractorInput.skipFully(this.synchronizedHeader.frameSize);
            return (create == null || create.isSeekable() || seekFrameHeader != SEEK_HEADER_INFO) ? create : getConstantBitrateSeeker(extractorInput, false);
        } else if (seekFrameHeader == SEEK_HEADER_VBRI) {
            VbriSeeker create2 = VbriSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
            extractorInput.skipFully(this.synchronizedHeader.frameSize);
            return create2;
        } else {
            extractorInput.resetPeekPosition();
            return null;
        }
    }

    private boolean peekEndOfStreamOrHeader(ExtractorInput extractorInput) throws IOException {
        Seeker seeker2 = this.seeker;
        if (seeker2 != null) {
            long dataEndPosition = seeker2.getDataEndPosition();
            if (dataEndPosition != -1 && extractorInput.getPeekPosition() > dataEndPosition - 4) {
                return true;
            }
        }
        try {
            return !extractorInput.peekFully(this.scratch.getData(), 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    @RequiresNonNull({"extractorOutput", "realTrackOutput"})
    private int readInternal(ExtractorInput extractorInput) throws IOException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.seeker == null) {
            Seeker computeSeeker = computeSeeker(extractorInput);
            this.seeker = computeSeeker;
            this.extractorOutput.seekMap(computeSeeker);
            this.currentTrackOutput.format(new Format.Builder().setSampleMimeType(this.synchronizedHeader.mimeType).setMaxInputSize(4096).setChannelCount(this.synchronizedHeader.channels).setSampleRate(this.synchronizedHeader.sampleRate).setEncoderDelay(this.gaplessInfoHolder.encoderDelay).setEncoderPadding(this.gaplessInfoHolder.encoderPadding).setMetadata((this.flags & 8) != 0 ? null : this.metadata).build());
            this.firstSamplePosition = extractorInput.getPosition();
        } else if (this.firstSamplePosition != 0) {
            long position = extractorInput.getPosition();
            long j2 = this.firstSamplePosition;
            if (position < j2) {
                extractorInput.skipFully((int) (j2 - position));
            }
        }
        return readSample(extractorInput);
    }

    @RequiresNonNull({"realTrackOutput", "seeker"})
    private int readSample(ExtractorInput extractorInput) throws IOException {
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (peekEndOfStreamOrHeader(extractorInput)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int readInt = this.scratch.readInt();
            if (!headersMatch(readInt, (long) this.synchronizedHeaderData) || MpegAudioUtil.getFrameSize(readInt) == -1) {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
            this.synchronizedHeader.setForHeaderData(readInt);
            if (this.basisTimeUs == C.TIME_UNSET) {
                this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                if (this.forcedFirstSampleTimestampUs != C.TIME_UNSET) {
                    this.basisTimeUs = (this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0)) + this.basisTimeUs;
                }
            }
            MpegAudioUtil.Header header = this.synchronizedHeader;
            this.sampleBytesRemaining = header.frameSize;
            Seeker seeker2 = this.seeker;
            if (seeker2 instanceof IndexSeeker) {
                IndexSeeker indexSeeker = (IndexSeeker) seeker2;
                indexSeeker.maybeAddSeekPoint(computeTimeUs(this.samplesRead + ((long) header.samplesPerFrame)), extractorInput.getPosition() + ((long) this.synchronizedHeader.frameSize));
                if (this.isSeekInProgress && indexSeeker.isTimeUsInIndex(this.seekTimeUs)) {
                    this.isSeekInProgress = false;
                    this.currentTrackOutput = this.realTrackOutput;
                }
            }
        }
        int sampleData = this.currentTrackOutput.sampleData((DataReader) extractorInput, this.sampleBytesRemaining, true);
        if (sampleData == -1) {
            return -1;
        }
        int i3 = this.sampleBytesRemaining - sampleData;
        this.sampleBytesRemaining = i3;
        if (i3 > 0) {
            return 0;
        }
        this.currentTrackOutput.sampleMetadata(computeTimeUs(this.samplesRead), 1, this.synchronizedHeader.frameSize, 0, (TrackOutput.CryptoData) null);
        this.samplesRead += (long) this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    private boolean synchronize(ExtractorInput extractorInput, boolean z2) throws IOException {
        int i3;
        int i4;
        int frameSize;
        int i5 = z2 ? 32768 : 131072;
        extractorInput.resetPeekPosition();
        if (extractorInput.getPosition() == 0) {
            Metadata peekId3Data = this.id3Peeker.peekId3Data(extractorInput, (this.flags & 8) == 0 ? null : REQUIRED_ID3_FRAME_PREDICATE);
            this.metadata = peekId3Data;
            if (peekId3Data != null) {
                this.gaplessInfoHolder.setFromMetadata(peekId3Data);
            }
            i3 = (int) extractorInput.getPeekPosition();
            if (!z2) {
                extractorInput.skipFully(i3);
            }
            i4 = 0;
        } else {
            i3 = 0;
            i4 = 0;
        }
        int i6 = i4;
        int i7 = i6;
        while (true) {
            if (!peekEndOfStreamOrHeader(extractorInput)) {
                this.scratch.setPosition(0);
                int readInt = this.scratch.readInt();
                if ((i4 == 0 || headersMatch(readInt, (long) i4)) && (frameSize = MpegAudioUtil.getFrameSize(readInt)) != -1) {
                    i6++;
                    if (i6 != 1) {
                        if (i6 == 4) {
                            break;
                        }
                    } else {
                        this.synchronizedHeader.setForHeaderData(readInt);
                        i4 = readInt;
                    }
                    extractorInput.advancePeekPosition(frameSize - 4);
                } else {
                    int i8 = i7 + 1;
                    if (i7 != i5) {
                        if (z2) {
                            extractorInput.resetPeekPosition();
                            extractorInput.advancePeekPosition(i3 + i8);
                        } else {
                            extractorInput.skipFully(1);
                        }
                        i6 = 0;
                        i7 = i8;
                        i4 = 0;
                    } else if (z2) {
                        return false;
                    } else {
                        throw ParserException.createForMalformedContainer("Searched too many bytes.", (Throwable) null);
                    }
                }
            } else if (i6 <= 0) {
                throw new EOFException();
            }
        }
        if (z2) {
            extractorInput.skipFully(i3 + i7);
        } else {
            extractorInput.resetPeekPosition();
        }
        this.synchronizedHeaderData = i4;
        return true;
    }

    public void disableSeeking() {
        this.disableSeeking = true;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        TrackOutput track = extractorOutput2.track(0, 1);
        this.realTrackOutput = track;
        this.currentTrackOutput = track;
        this.extractorOutput.endTracks();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        int readInternal = readInternal(extractorInput);
        if (readInternal == -1 && (this.seeker instanceof IndexSeeker)) {
            long computeTimeUs = computeTimeUs(this.samplesRead);
            if (this.seeker.getDurationUs() != computeTimeUs) {
                ((IndexSeeker) this.seeker).setDurationUs(computeTimeUs);
                this.extractorOutput.seekMap(this.seeker);
            }
        }
        return readInternal;
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = C.TIME_UNSET;
        this.samplesRead = 0;
        this.sampleBytesRemaining = 0;
        this.seekTimeUs = j3;
        Seeker seeker2 = this.seeker;
        if ((seeker2 instanceof IndexSeeker) && !((IndexSeeker) seeker2).isTimeUsInIndex(j3)) {
            this.isSeekInProgress = true;
            this.currentTrackOutput = this.skippingTrackOutput;
        }
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return synchronize(extractorInput, true);
    }

    public Mp3Extractor(int i3) {
        this(i3, C.TIME_UNSET);
    }

    public Mp3Extractor(int i3, long j2) {
        this.flags = (i3 & 2) != 0 ? i3 | 1 : i3;
        this.forcedFirstSampleTimestampUs = j2;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioUtil.Header();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = C.TIME_UNSET;
        this.id3Peeker = new Id3Peeker();
        DummyTrackOutput dummyTrackOutput = new DummyTrackOutput();
        this.skippingTrackOutput = dummyTrackOutput;
        this.currentTrackOutput = dummyTrackOutput;
    }
}
