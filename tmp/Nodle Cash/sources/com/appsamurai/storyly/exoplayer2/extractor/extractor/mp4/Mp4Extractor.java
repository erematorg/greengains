package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.collection.SieveCacheKt;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.Ac4Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.GaplessInfoHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekPoint;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrueHdSampleRechunker;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.a;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.MotionPhotoMetadata;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public final class Mp4Extractor implements Extractor, SeekMap {
    public static final ExtractorsFactory FACTORY = new a(7);
    private static final int FILE_TYPE_HEIC = 2;
    private static final int FILE_TYPE_MP4 = 0;
    private static final int FILE_TYPE_QUICKTIME = 1;
    public static final int FLAG_READ_MOTION_PHOTO_METADATA = 2;
    public static final int FLAG_READ_SEF_DATA = 4;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 1;
    private static final long MAXIMUM_READ_AHEAD_BYTES_STREAM = 10485760;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private static final int STATE_READING_SEF = 3;
    private long[][] accumulatedSampleSizes;
    @Nullable
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final ArrayDeque<Atom.ContainerAtom> containerAtoms;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int fileType;
    private int firstVideoTrackIndex;
    private final int flags;
    @Nullable
    private MotionPhotoMetadata motionPhotoMetadata;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleTrackIndex;
    private final ParsableByteArray scratch;
    private final SefReader sefReader;
    private final List<Metadata.Entry> slowMotionMetadataEntries;
    private Mp4Track[] tracks;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;
        @Nullable
        public final TrueHdSampleRechunker trueHdSampleRechunker;

        public Mp4Track(Track track2, TrackSampleTable trackSampleTable, TrackOutput trackOutput2) {
            this.track = track2;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput2;
            this.trueHdSampleRechunker = MimeTypes.AUDIO_TRUEHD.equals(track2.format.sampleMimeType) ? new TrueHdSampleRechunker() : null;
        }
    }

    public Mp4Extractor() {
        this(0);
    }

    private static int brandToFileType(int i3) {
        if (i3 != 1751476579) {
            return i3 != 1903435808 ? 0 : 1;
        }
        return 2;
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] mp4TrackArr) {
        long[][] jArr = new long[mp4TrackArr.length][];
        int[] iArr = new int[mp4TrackArr.length];
        long[] jArr2 = new long[mp4TrackArr.length];
        boolean[] zArr = new boolean[mp4TrackArr.length];
        for (int i3 = 0; i3 < mp4TrackArr.length; i3++) {
            jArr[i3] = new long[mp4TrackArr[i3].sampleTable.sampleCount];
            jArr2[i3] = mp4TrackArr[i3].sampleTable.timestampsUs[0];
        }
        long j2 = 0;
        int i4 = 0;
        while (i4 < mp4TrackArr.length) {
            long j3 = Long.MAX_VALUE;
            int i5 = -1;
            for (int i6 = 0; i6 < mp4TrackArr.length; i6++) {
                if (!zArr[i6]) {
                    long j4 = jArr2[i6];
                    if (j4 <= j3) {
                        i5 = i6;
                        j3 = j4;
                    }
                }
            }
            int i7 = iArr[i5];
            long[] jArr3 = jArr[i5];
            jArr3[i7] = j2;
            TrackSampleTable trackSampleTable = mp4TrackArr[i5].sampleTable;
            j2 += (long) trackSampleTable.sizes[i7];
            int i8 = i7 + 1;
            iArr[i5] = i8;
            if (i8 < jArr3.length) {
                jArr2[i5] = trackSampleTable.timestampsUs[i8];
            } else {
                zArr[i5] = true;
                i4++;
            }
        }
        return jArr;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable trackSampleTable, long j2) {
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j2);
        return indexOfEarlierOrEqualSynchronizationSample == -1 ? trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j2) : indexOfEarlierOrEqualSynchronizationSample;
    }

    private int getTrackIndexOfNextReadSample(long j2) {
        int i3 = -1;
        int i4 = -1;
        int i5 = 0;
        long j3 = Long.MAX_VALUE;
        boolean z2 = true;
        long j4 = Long.MAX_VALUE;
        boolean z3 = true;
        long j5 = Long.MAX_VALUE;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i5 >= mp4TrackArr.length) {
                break;
            }
            Mp4Track mp4Track = mp4TrackArr[i5];
            int i6 = mp4Track.sampleIndex;
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            if (i6 != trackSampleTable.sampleCount) {
                long j6 = trackSampleTable.offsets[i6];
                long j7 = ((long[][]) Util.castNonNull(this.accumulatedSampleSizes))[i5][i6];
                long j8 = j6 - j2;
                boolean z4 = j8 < 0 || j8 >= 262144;
                if ((!z4 && z3) || (z4 == z3 && j8 < j5)) {
                    j5 = j8;
                    z3 = z4;
                    i4 = i5;
                    j4 = j7;
                }
                if (j7 < j3) {
                    z2 = z4;
                    i3 = i5;
                    j3 = j7;
                }
            }
            i5++;
        }
        return (j3 == Long.MAX_VALUE || !z2 || j4 < j3 + MAXIMUM_READ_AHEAD_BYTES_STREAM) ? i4 : i3;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Track lambda$processMoovAtom$1(Track track) {
        return track;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp4Extractor()};
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable trackSampleTable, long j2, long j3) {
        int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j2);
        return synchronizationSampleIndex == -1 ? j3 : Math.min(trackSampleTable.offsets[synchronizationSampleIndex], j3);
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(8);
        extractorInput.peekFully(this.scratch.getData(), 0, 8);
        AtomParsers.maybeSkipRemainingMetaAtomHeaderBytes(this.scratch);
        extractorInput.skipFully(this.scratch.getPosition());
        extractorInput.resetPeekPosition();
    }

    private void processAtomEnded(long j2) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j2) {
            Atom.ContainerAtom pop = this.containerAtoms.pop();
            if (pop.type == 1836019574) {
                processMoovAtom(pop);
                this.containerAtoms.clear();
                this.parserState = 2;
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(pop);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private void processEndOfStreamReadingAtomHeader() {
        Metadata metadata;
        if (this.fileType == 2 && (this.flags & 2) != 0) {
            TrackOutput track = this.extractorOutput.track(0, 4);
            if (this.motionPhotoMetadata == null) {
                metadata = null;
            } else {
                metadata = new Metadata(this.motionPhotoMetadata);
            }
            track.format(new Format.Builder().setMetadata(metadata).build());
            this.extractorOutput.endTracks();
            this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        }
    }

    private static int processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int brandToFileType = brandToFileType(parsableByteArray.readInt());
        if (brandToFileType != 0) {
            return brandToFileType;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            int brandToFileType2 = brandToFileType(parsableByteArray.readInt());
            if (brandToFileType2 != 0) {
                return brandToFileType2;
            }
        }
        return 0;
    }

    private void processMoovAtom(Atom.ContainerAtom containerAtom) throws ParserException {
        Metadata metadata;
        Metadata metadata2;
        int i3;
        List<TrackSampleTable> list;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        ArrayList arrayList = new ArrayList();
        boolean z2 = this.fileType == 1;
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Atom.LeafAtom leafAtomOfType = containerAtom2.getLeafAtomOfType(Atom.TYPE_udta);
        if (leafAtomOfType != null) {
            Pair<Metadata, Metadata> parseUdta = AtomParsers.parseUdta(leafAtomOfType);
            Metadata metadata3 = (Metadata) parseUdta.first;
            Metadata metadata4 = (Metadata) parseUdta.second;
            if (metadata3 != null) {
                gaplessInfoHolder.setFromMetadata(metadata3);
            }
            metadata2 = metadata4;
            metadata = metadata3;
        } else {
            metadata2 = null;
            metadata = null;
        }
        Atom.ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_meta);
        Metadata parseMdtaFromMeta = containerAtomOfType != null ? AtomParsers.parseMdtaFromMeta(containerAtomOfType) : null;
        List<TrackSampleTable> parseTraks = AtomParsers.parseTraks(containerAtom, gaplessInfoHolder, C.TIME_UNSET, (DrmInitData) null, (this.flags & 1) != 0, z2, new b(0));
        int size = parseTraks.size();
        long j2 = C.TIME_UNSET;
        long j3 = -9223372036854775807L;
        int i4 = 0;
        int i5 = -1;
        while (i4 < size) {
            TrackSampleTable trackSampleTable = parseTraks.get(i4);
            if (trackSampleTable.sampleCount == 0) {
                list = parseTraks;
                i3 = size;
            } else {
                Track track = trackSampleTable.track;
                list = parseTraks;
                i3 = size;
                long j4 = track.durationUs;
                if (j4 == j2) {
                    j4 = trackSampleTable.durationUs;
                }
                long max = Math.max(j3, j4);
                Mp4Track mp4Track = new Mp4Track(track, trackSampleTable, this.extractorOutput.track(i4, track.type));
                int i6 = MimeTypes.AUDIO_TRUEHD.equals(track.format.sampleMimeType) ? trackSampleTable.maximumSize * 16 : trackSampleTable.maximumSize + 30;
                Format.Builder buildUpon = track.format.buildUpon();
                buildUpon.setMaxInputSize(i6);
                if (track.type == 2 && j4 > 0) {
                    int i7 = trackSampleTable.sampleCount;
                    if (i7 > 1) {
                        buildUpon.setFrameRate(((float) i7) / (((float) j4) / 1000000.0f));
                    }
                }
                MetadataUtil.setFormatGaplessInfo(track.type, gaplessInfoHolder, buildUpon);
                MetadataUtil.setFormatMetadata(track.type, metadata, parseMdtaFromMeta, buildUpon, metadata2, this.slowMotionMetadataEntries.isEmpty() ? null : new Metadata((List<? extends Metadata.Entry>) this.slowMotionMetadataEntries));
                mp4Track.trackOutput.format(buildUpon.build());
                if (track.type == 2) {
                    if (i5 == -1) {
                        i5 = arrayList.size();
                    }
                }
                arrayList.add(mp4Track);
                j3 = max;
            }
            i4++;
            parseTraks = list;
            size = i3;
            j2 = C.TIME_UNSET;
        }
        this.firstVideoTrackIndex = i5;
        this.durationUs = j3;
        Mp4Track[] mp4TrackArr = (Mp4Track[]) arrayList.toArray(new Mp4Track[0]);
        this.tracks = mp4TrackArr;
        this.accumulatedSampleSizes = calculateAccumulatedSampleSizes(mp4TrackArr);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    private void processUnparsedAtom(long j2) {
        if (this.atomType == 1836086884) {
            int i3 = this.atomHeaderBytesRead;
            this.motionPhotoMetadata = new MotionPhotoMetadata(0, j2, C.TIME_UNSET, j2 + ((long) i3), this.atomSize - ((long) i3));
        }
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException {
        Atom.ContainerAtom peek;
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
                processEndOfStreamReadingAtomHeader();
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j2 = this.atomSize;
        if (j2 == 1) {
            extractorInput.readFully(this.atomHeader.getData(), 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j2 == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && (peek = this.containerAtoms.peek()) != null) {
                length = peek.endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize >= ((long) this.atomHeaderBytesRead)) {
            if (shouldParseContainerAtom(this.atomType)) {
                long position = extractorInput.getPosition();
                long j3 = this.atomSize;
                int i3 = this.atomHeaderBytesRead;
                long j4 = (position + j3) - ((long) i3);
                if (j3 != ((long) i3) && this.atomType == 1835365473) {
                    maybeSkipRemainingMetaAtomHeaderBytes(extractorInput);
                }
                this.containerAtoms.push(new Atom.ContainerAtom(this.atomType, j4));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(j4);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                Assertions.checkState(this.atomHeaderBytesRead == 8);
                Assertions.checkState(this.atomSize <= SieveCacheKt.NodeLinkMask);
                ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
                System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
                this.atomData = parsableByteArray;
                this.parserState = 1;
            } else {
                processUnparsedAtom(extractorInput.getPosition() - ((long) this.atomHeaderBytesRead));
                this.atomData = null;
                this.parserState = 1;
            }
            return true;
        }
        throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readAtomPayload(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r10, com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder r11) throws java.io.IOException {
        /*
            r9 = this;
            long r0 = r9.atomSize
            int r2 = r9.atomHeaderBytesRead
            long r2 = (long) r2
            long r0 = r0 - r2
            long r2 = r10.getPosition()
            long r2 = r2 + r0
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r4 = r9.atomData
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0044
            byte[] r11 = r4.getData()
            int r7 = r9.atomHeaderBytesRead
            int r0 = (int) r0
            r10.readFully(r11, r7, r0)
            int r10 = r9.atomType
            r11 = 1718909296(0x66747970, float:2.8862439E23)
            if (r10 != r11) goto L_0x0029
            int r10 = processFtypAtom(r4)
            r9.fileType = r10
            goto L_0x004f
        L_0x0029:
            java.util.ArrayDeque<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$ContainerAtom> r10 = r9.containerAtoms
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x004f
            java.util.ArrayDeque<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$ContainerAtom> r10 = r9.containerAtoms
            java.lang.Object r10 = r10.peek()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$ContainerAtom r10 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.ContainerAtom) r10
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r11 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom
            int r0 = r9.atomType
            r11.<init>(r0, r4)
            r10.add((com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.LeafAtom) r11)
            goto L_0x004f
        L_0x0044:
            r7 = 262144(0x40000, double:1.295163E-318)
            int r4 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r4 >= 0) goto L_0x0051
            int r11 = (int) r0
            r10.skipFully(r11)
        L_0x004f:
            r10 = r6
            goto L_0x0059
        L_0x0051:
            long r7 = r10.getPosition()
            long r7 = r7 + r0
            r11.position = r7
            r10 = r5
        L_0x0059:
            r9.processAtomEnded(r2)
            if (r10 == 0) goto L_0x0064
            int r9 = r9.parserState
            r10 = 2
            if (r9 == r10) goto L_0x0064
            goto L_0x0065
        L_0x0064:
            r5 = r6
        L_0x0065:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Mp4Extractor.readAtomPayload(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput, com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder):boolean");
    }

    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i3;
        PositionHolder positionHolder2;
        ExtractorInput extractorInput2 = extractorInput;
        long position = extractorInput.getPosition();
        if (this.sampleTrackIndex == -1) {
            int trackIndexOfNextReadSample = getTrackIndexOfNextReadSample(position);
            this.sampleTrackIndex = trackIndexOfNextReadSample;
            if (trackIndexOfNextReadSample == -1) {
                return -1;
            }
        }
        Mp4Track mp4Track = this.tracks[this.sampleTrackIndex];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int i4 = mp4Track.sampleIndex;
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        long j2 = trackSampleTable.offsets[i4];
        int i5 = trackSampleTable.sizes[i4];
        TrueHdSampleRechunker trueHdSampleRechunker = mp4Track.trueHdSampleRechunker;
        long j3 = (j2 - position) + ((long) this.sampleBytesRead);
        if (j3 < 0) {
            i3 = 1;
            positionHolder2 = positionHolder;
        } else if (j3 >= 262144) {
            positionHolder2 = positionHolder;
            i3 = 1;
        } else {
            if (mp4Track.track.sampleTransformation == 1) {
                j3 += 8;
                i5 -= 8;
            }
            extractorInput2.skipFully((int) j3);
            Track track = mp4Track.track;
            if (track.nalUnitLengthFieldLength == 0) {
                if (MimeTypes.AUDIO_AC4.equals(track.format.sampleMimeType)) {
                    if (this.sampleBytesWritten == 0) {
                        Ac4Util.getAc4SampleHeader(i5, this.scratch);
                        trackOutput.sampleData(this.scratch, 7);
                        this.sampleBytesWritten += 7;
                    }
                    i5 += 7;
                } else if (trueHdSampleRechunker != null) {
                    trueHdSampleRechunker.startSample(extractorInput2);
                }
                while (true) {
                    int i6 = this.sampleBytesWritten;
                    if (i6 >= i5) {
                        break;
                    }
                    int sampleData = trackOutput.sampleData((DataReader) extractorInput2, i5 - i6, false);
                    this.sampleBytesRead += sampleData;
                    this.sampleBytesWritten += sampleData;
                    this.sampleCurrentNalBytesRemaining -= sampleData;
                }
            } else {
                byte[] data = this.nalLength.getData();
                data[0] = 0;
                data[1] = 0;
                data[2] = 0;
                int i7 = mp4Track.track.nalUnitLengthFieldLength;
                int i8 = 4 - i7;
                while (this.sampleBytesWritten < i5) {
                    int i9 = this.sampleCurrentNalBytesRemaining;
                    if (i9 == 0) {
                        extractorInput2.readFully(data, i8, i7);
                        this.sampleBytesRead += i7;
                        this.nalLength.setPosition(0);
                        int readInt = this.nalLength.readInt();
                        if (readInt >= 0) {
                            this.sampleCurrentNalBytesRemaining = readInt;
                            this.nalStartCode.setPosition(0);
                            trackOutput.sampleData(this.nalStartCode, 4);
                            this.sampleBytesWritten += 4;
                            i5 += i8;
                        } else {
                            throw ParserException.createForMalformedContainer("Invalid NAL length", (Throwable) null);
                        }
                    } else {
                        int sampleData2 = trackOutput.sampleData((DataReader) extractorInput2, i9, false);
                        this.sampleBytesRead += sampleData2;
                        this.sampleBytesWritten += sampleData2;
                        this.sampleCurrentNalBytesRemaining -= sampleData2;
                    }
                }
            }
            int i10 = i5;
            TrackSampleTable trackSampleTable2 = mp4Track.sampleTable;
            long j4 = trackSampleTable2.timestampsUs[i4];
            int i11 = trackSampleTable2.flags[i4];
            if (trueHdSampleRechunker != null) {
                int i12 = i10;
                TrueHdSampleRechunker trueHdSampleRechunker2 = trueHdSampleRechunker;
                trueHdSampleRechunker.sampleMetadata(trackOutput, j4, i11, i12, 0, (TrackOutput.CryptoData) null);
                if (i4 + 1 == mp4Track.sampleTable.sampleCount) {
                    trueHdSampleRechunker2.outputPendingSampleMetadata(trackOutput, (TrackOutput.CryptoData) null);
                }
            } else {
                trackOutput.sampleMetadata(j4, i11, i10, 0, (TrackOutput.CryptoData) null);
            }
            mp4Track.sampleIndex++;
            this.sampleTrackIndex = -1;
            this.sampleBytesRead = 0;
            this.sampleBytesWritten = 0;
            this.sampleCurrentNalBytesRemaining = 0;
            return 0;
        }
        positionHolder2.position = j2;
        return i3;
    }

    private int readSefData(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int read = this.sefReader.read(extractorInput, positionHolder, this.slowMotionMetadataEntries);
        if (read == 1 && positionHolder.position == 0) {
            enterReadingAtomHeaderState();
        }
        return read;
    }

    private static boolean shouldParseContainerAtom(int i3) {
        return i3 == 1836019574 || i3 == 1953653099 || i3 == 1835297121 || i3 == 1835626086 || i3 == 1937007212 || i3 == 1701082227 || i3 == 1835365473;
    }

    private static boolean shouldParseLeafAtom(int i3) {
        return i3 == 1835296868 || i3 == 1836476516 || i3 == 1751411826 || i3 == 1937011556 || i3 == 1937011827 || i3 == 1937011571 || i3 == 1668576371 || i3 == 1701606260 || i3 == 1937011555 || i3 == 1937011578 || i3 == 1937013298 || i3 == 1937007471 || i3 == 1668232756 || i3 == 1953196132 || i3 == 1718909296 || i3 == 1969517665 || i3 == 1801812339 || i3 == 1768715124;
    }

    private void updateSampleIndex(Mp4Track mp4Track, long j2) {
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j2);
        if (indexOfEarlierOrEqualSynchronizationSample == -1) {
            indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j2);
        }
        mp4Track.sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j2) {
        return getSeekPoints(j2, -1);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public boolean isSeekable() {
        return true;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        while (true) {
            int i3 = this.parserState;
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 == 2) {
                        return readSample(extractorInput, positionHolder);
                    }
                    if (i3 == 3) {
                        return readSefData(extractorInput, positionHolder);
                    }
                    throw new IllegalStateException();
                } else if (readAtomPayload(extractorInput, positionHolder)) {
                    return 1;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        if (j2 != 0) {
            for (Mp4Track mp4Track : this.tracks) {
                updateSampleIndex(mp4Track, j3);
                TrueHdSampleRechunker trueHdSampleRechunker = mp4Track.trueHdSampleRechunker;
                if (trueHdSampleRechunker != null) {
                    trueHdSampleRechunker.reset();
                }
            }
        } else if (this.parserState != 3) {
            enterReadingAtomHeaderState();
        } else {
            this.sefReader.reset();
            this.slowMotionMetadataEntries.clear();
        }
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return Sniffer.sniffUnfragmented(extractorInput, (this.flags & 2) != 0);
    }

    public Mp4Extractor(int i3) {
        this.flags = i3;
        this.parserState = (i3 & 4) != 0 ? 3 : 0;
        this.sefReader = new SefReader();
        this.slowMotionMetadataEntries = new ArrayList();
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque<>();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.scratch = new ParsableByteArray();
        this.sampleTrackIndex = -1;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.tracks = new Mp4Track[0];
    }

    public SeekMap.SeekPoints getSeekPoints(long j2, int i3) {
        int i4;
        long j3;
        long j4;
        long j5;
        long j6;
        int indexOfLaterOrEqualSynchronizationSample;
        long j7 = j2;
        int i5 = i3;
        Mp4Track[] mp4TrackArr = this.tracks;
        if (mp4TrackArr.length == 0) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        if (i5 != -1) {
            i4 = i5;
        } else {
            i4 = this.firstVideoTrackIndex;
        }
        if (i4 != -1) {
            TrackSampleTable trackSampleTable = mp4TrackArr[i4].sampleTable;
            int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j7);
            if (synchronizationSampleIndex == -1) {
                return new SeekMap.SeekPoints(SeekPoint.START);
            }
            long j8 = trackSampleTable.timestampsUs[synchronizationSampleIndex];
            j3 = trackSampleTable.offsets[synchronizationSampleIndex];
            if (j8 >= j7 || synchronizationSampleIndex >= trackSampleTable.sampleCount - 1 || (indexOfLaterOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j7)) == -1 || indexOfLaterOrEqualSynchronizationSample == synchronizationSampleIndex) {
                j6 = -1;
                j5 = -9223372036854775807L;
            } else {
                j5 = trackSampleTable.timestampsUs[indexOfLaterOrEqualSynchronizationSample];
                j6 = trackSampleTable.offsets[indexOfLaterOrEqualSynchronizationSample];
            }
            long j9 = j6;
            j7 = j8;
            j4 = j9;
        } else {
            j3 = Long.MAX_VALUE;
            j4 = -1;
            j5 = -9223372036854775807L;
        }
        if (i5 == -1) {
            int i6 = 0;
            while (true) {
                Mp4Track[] mp4TrackArr2 = this.tracks;
                if (i6 >= mp4TrackArr2.length) {
                    break;
                }
                if (i6 != this.firstVideoTrackIndex) {
                    TrackSampleTable trackSampleTable2 = mp4TrackArr2[i6].sampleTable;
                    long maybeAdjustSeekOffset = maybeAdjustSeekOffset(trackSampleTable2, j7, j3);
                    if (j5 != C.TIME_UNSET) {
                        j4 = maybeAdjustSeekOffset(trackSampleTable2, j5, j4);
                    }
                    j3 = maybeAdjustSeekOffset;
                }
                i6++;
            }
        }
        SeekPoint seekPoint = new SeekPoint(j7, j3);
        if (j5 == C.TIME_UNSET) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(j5, j4));
    }
}
