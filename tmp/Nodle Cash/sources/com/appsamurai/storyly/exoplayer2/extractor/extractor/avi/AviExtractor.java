package com.appsamurai.storyly.exoplayer2.extractor.extractor.avi;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.util.ArrayList;

public final class AviExtractor implements Extractor {
    private static final int AVIIF_KEYFRAME = 16;
    public static final int FOURCC_AVI_ = 541677121;
    public static final int FOURCC_JUNK = 1263424842;
    public static final int FOURCC_LIST = 1414744396;
    public static final int FOURCC_RIFF = 1179011410;
    public static final int FOURCC_auds = 1935963489;
    public static final int FOURCC_avih = 1751742049;
    public static final int FOURCC_hdrl = 1819436136;
    public static final int FOURCC_idx1 = 829973609;
    public static final int FOURCC_movi = 1769369453;
    public static final int FOURCC_strf = 1718776947;
    public static final int FOURCC_strh = 1752331379;
    public static final int FOURCC_strl = 1819440243;
    public static final int FOURCC_strn = 1852994675;
    public static final int FOURCC_txts = 1937012852;
    public static final int FOURCC_vids = 1935960438;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_FINDING_IDX1_HEADER = 4;
    private static final int STATE_FINDING_MOVI_HEADER = 3;
    private static final int STATE_READING_HDRL_BODY = 2;
    private static final int STATE_READING_HDRL_HEADER = 1;
    private static final int STATE_READING_IDX1_BODY = 5;
    private static final int STATE_READING_SAMPLES = 6;
    private static final int STATE_SKIPPING_TO_HDRL = 0;
    private static final String TAG = "AviExtractor";
    private AviMainHeaderChunk aviHeader;
    private final ChunkHeaderHolder chunkHeaderHolder = new ChunkHeaderHolder();
    /* access modifiers changed from: private */
    public ChunkReader[] chunkReaders = new ChunkReader[0];
    @Nullable
    private ChunkReader currentChunkReader;
    private long durationUs = C.TIME_UNSET;
    private ExtractorOutput extractorOutput = new DummyExtractorOutput();
    private int hdrlSize = -1;
    private int idx1BodySize;
    private long moviEnd = -1;
    private long moviStart = -1;
    private long pendingReposition;
    private final ParsableByteArray scratch = new ParsableByteArray(12);
    private boolean seekMapHasBeenOutput;
    private int state;

    public class AviSeekMap implements SeekMap {
        private final long durationUs;

        public AviSeekMap(long j2) {
            this.durationUs = j2;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekMap.SeekPoints getSeekPoints(long j2) {
            SeekMap.SeekPoints seekPoints = AviExtractor.this.chunkReaders[0].getSeekPoints(j2);
            for (int i3 = 1; i3 < AviExtractor.this.chunkReaders.length; i3++) {
                SeekMap.SeekPoints seekPoints2 = AviExtractor.this.chunkReaders[i3].getSeekPoints(j2);
                if (seekPoints2.first.position < seekPoints.first.position) {
                    seekPoints = seekPoints2;
                }
            }
            return seekPoints;
        }

        public boolean isSeekable() {
            return true;
        }
    }

    public static class ChunkHeaderHolder {
        public int chunkType;
        public int listType;
        public int size;

        private ChunkHeaderHolder() {
        }

        public void populateFrom(ParsableByteArray parsableByteArray) {
            this.chunkType = parsableByteArray.readLittleEndianInt();
            this.size = parsableByteArray.readLittleEndianInt();
            this.listType = 0;
        }

        public void populateWithListHeaderFrom(ParsableByteArray parsableByteArray) throws ParserException {
            populateFrom(parsableByteArray);
            if (this.chunkType == 1414744396) {
                this.listType = parsableByteArray.readLittleEndianInt();
                return;
            }
            throw ParserException.createForMalformedContainer("LIST expected, found: " + this.chunkType, (Throwable) null);
        }
    }

    private static void alignInputToEvenPosition(ExtractorInput extractorInput) throws IOException {
        if ((extractorInput.getPosition() & 1) == 1) {
            extractorInput.skipFully(1);
        }
    }

    @Nullable
    private ChunkReader getChunkReader(int i3) {
        for (ChunkReader chunkReader : this.chunkReaders) {
            if (chunkReader.handlesChunkId(i3)) {
                return chunkReader;
            }
        }
        return null;
    }

    private void parseHdrlBody(ParsableByteArray parsableByteArray) throws IOException {
        ListChunk parseFrom = ListChunk.parseFrom(FOURCC_hdrl, parsableByteArray);
        if (parseFrom.getType() == 1819436136) {
            AviMainHeaderChunk aviMainHeaderChunk = (AviMainHeaderChunk) parseFrom.getChild(AviMainHeaderChunk.class);
            if (aviMainHeaderChunk != null) {
                this.aviHeader = aviMainHeaderChunk;
                this.durationUs = ((long) aviMainHeaderChunk.totalFrames) * ((long) aviMainHeaderChunk.frameDurationUs);
                ArrayList arrayList = new ArrayList();
                UnmodifiableIterator<AviChunk> it = parseFrom.children.iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    AviChunk next = it.next();
                    if (next.getType() == 1819440243) {
                        int i4 = i3 + 1;
                        ChunkReader processStreamList = processStreamList((ListChunk) next, i3);
                        if (processStreamList != null) {
                            arrayList.add(processStreamList);
                        }
                        i3 = i4;
                    }
                }
                this.chunkReaders = (ChunkReader[]) arrayList.toArray(new ChunkReader[0]);
                this.extractorOutput.endTracks();
                return;
            }
            throw ParserException.createForMalformedContainer("AviHeader not found", (Throwable) null);
        }
        throw ParserException.createForMalformedContainer("Unexpected header list type " + parseFrom.getType(), (Throwable) null);
    }

    private void parseIdx1Body(ParsableByteArray parsableByteArray) {
        long peekSeekOffset = peekSeekOffset(parsableByteArray);
        while (parsableByteArray.bytesLeft() >= 16) {
            int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
            int readLittleEndianInt2 = parsableByteArray.readLittleEndianInt();
            long readLittleEndianInt3 = ((long) parsableByteArray.readLittleEndianInt()) + peekSeekOffset;
            parsableByteArray.readLittleEndianInt();
            ChunkReader chunkReader = getChunkReader(readLittleEndianInt);
            if (chunkReader != null) {
                if ((readLittleEndianInt2 & 16) == 16) {
                    chunkReader.appendKeyFrameToIndex(readLittleEndianInt3);
                }
                chunkReader.incrementIndexChunkCount();
            }
        }
        for (ChunkReader compactIndex : this.chunkReaders) {
            compactIndex.compactIndex();
        }
        this.seekMapHasBeenOutput = true;
        this.extractorOutput.seekMap(new AviSeekMap(this.durationUs));
    }

    private long peekSeekOffset(ParsableByteArray parsableByteArray) {
        long j2 = 0;
        if (parsableByteArray.bytesLeft() < 16) {
            return 0;
        }
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(8);
        long j3 = this.moviStart;
        if (((long) parsableByteArray.readLittleEndianInt()) <= j3) {
            j2 = j3 + 8;
        }
        parsableByteArray.setPosition(position);
        return j2;
    }

    @Nullable
    private ChunkReader processStreamList(ListChunk listChunk, int i3) {
        AviStreamHeaderChunk aviStreamHeaderChunk = (AviStreamHeaderChunk) listChunk.getChild(AviStreamHeaderChunk.class);
        StreamFormatChunk streamFormatChunk = (StreamFormatChunk) listChunk.getChild(StreamFormatChunk.class);
        if (aviStreamHeaderChunk == null) {
            Log.w(TAG, "Missing Stream Header");
            return null;
        } else if (streamFormatChunk == null) {
            Log.w(TAG, "Missing Stream Format");
            return null;
        } else {
            long durationUs2 = aviStreamHeaderChunk.getDurationUs();
            Format format = streamFormatChunk.format;
            Format.Builder buildUpon = format.buildUpon();
            buildUpon.setId(i3);
            int i4 = aviStreamHeaderChunk.suggestedBufferSize;
            if (i4 != 0) {
                buildUpon.setMaxInputSize(i4);
            }
            StreamNameChunk streamNameChunk = (StreamNameChunk) listChunk.getChild(StreamNameChunk.class);
            if (streamNameChunk != null) {
                buildUpon.setLabel(streamNameChunk.name);
            }
            int trackType = MimeTypes.getTrackType(format.sampleMimeType);
            if (trackType != 1 && trackType != 2) {
                return null;
            }
            TrackOutput track = this.extractorOutput.track(i3, trackType);
            track.format(buildUpon.build());
            ChunkReader chunkReader = new ChunkReader(i3, trackType, durationUs2, aviStreamHeaderChunk.length, track);
            this.durationUs = durationUs2;
            return chunkReader;
        }
    }

    private int readMoviChunks(ExtractorInput extractorInput) throws IOException {
        if (extractorInput.getPosition() >= this.moviEnd) {
            return -1;
        }
        ChunkReader chunkReader = this.currentChunkReader;
        if (chunkReader == null) {
            alignInputToEvenPosition(extractorInput);
            int i3 = 12;
            extractorInput.peekFully(this.scratch.getData(), 0, 12);
            this.scratch.setPosition(0);
            int readLittleEndianInt = this.scratch.readLittleEndianInt();
            if (readLittleEndianInt == 1414744396) {
                this.scratch.setPosition(8);
                if (this.scratch.readLittleEndianInt() != 1769369453) {
                    i3 = 8;
                }
                extractorInput.skipFully(i3);
                extractorInput.resetPeekPosition();
                return 0;
            }
            int readLittleEndianInt2 = this.scratch.readLittleEndianInt();
            if (readLittleEndianInt == 1263424842) {
                this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt2) + 8;
                return 0;
            }
            extractorInput.skipFully(8);
            extractorInput.resetPeekPosition();
            ChunkReader chunkReader2 = getChunkReader(readLittleEndianInt);
            if (chunkReader2 == null) {
                this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt2);
                return 0;
            }
            chunkReader2.onChunkStart(readLittleEndianInt2);
            this.currentChunkReader = chunkReader2;
        } else if (chunkReader.onChunkData(extractorInput)) {
            this.currentChunkReader = null;
        }
        return 0;
    }

    private boolean resolvePendingReposition(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        boolean z2;
        if (this.pendingReposition != -1) {
            long position = extractorInput.getPosition();
            long j2 = this.pendingReposition;
            if (j2 < position || j2 > 262144 + position) {
                positionHolder.position = j2;
                z2 = true;
                this.pendingReposition = -1;
                return z2;
            }
            extractorInput.skipFully((int) (j2 - position));
        }
        z2 = false;
        this.pendingReposition = -1;
        return z2;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.state = 0;
        this.extractorOutput = extractorOutput2;
        this.pendingReposition = -1;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        if (resolvePendingReposition(extractorInput, positionHolder)) {
            return 1;
        }
        switch (this.state) {
            case 0:
                if (sniff(extractorInput)) {
                    extractorInput.skipFully(12);
                    this.state = 1;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("AVI Header List not found", (Throwable) null);
            case 1:
                extractorInput.readFully(this.scratch.getData(), 0, 12);
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateWithListHeaderFrom(this.scratch);
                ChunkHeaderHolder chunkHeaderHolder2 = this.chunkHeaderHolder;
                if (chunkHeaderHolder2.listType == 1819436136) {
                    this.hdrlSize = chunkHeaderHolder2.size;
                    this.state = 2;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("hdrl expected, found: " + this.chunkHeaderHolder.listType, (Throwable) null);
            case 2:
                int i3 = this.hdrlSize - 4;
                ParsableByteArray parsableByteArray = new ParsableByteArray(i3);
                extractorInput.readFully(parsableByteArray.getData(), 0, i3);
                parseHdrlBody(parsableByteArray);
                this.state = 3;
                return 0;
            case 3:
                if (this.moviStart != -1) {
                    long position = extractorInput.getPosition();
                    long j2 = this.moviStart;
                    if (position != j2) {
                        this.pendingReposition = j2;
                        return 0;
                    }
                }
                extractorInput.peekFully(this.scratch.getData(), 0, 12);
                extractorInput.resetPeekPosition();
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateFrom(this.scratch);
                int readLittleEndianInt = this.scratch.readLittleEndianInt();
                int i4 = this.chunkHeaderHolder.chunkType;
                if (i4 == 1179011410) {
                    extractorInput.skipFully(12);
                    return 0;
                } else if (i4 == 1414744396 && readLittleEndianInt == 1769369453) {
                    long position2 = extractorInput.getPosition();
                    this.moviStart = position2;
                    this.moviEnd = position2 + ((long) this.chunkHeaderHolder.size) + 8;
                    if (!this.seekMapHasBeenOutput) {
                        if (((AviMainHeaderChunk) Assertions.checkNotNull(this.aviHeader)).hasIndex()) {
                            this.state = 4;
                            this.pendingReposition = this.moviEnd;
                            return 0;
                        }
                        this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                        this.seekMapHasBeenOutput = true;
                    }
                    this.pendingReposition = extractorInput.getPosition() + 12;
                    this.state = 6;
                    return 0;
                } else {
                    this.pendingReposition = extractorInput.getPosition() + ((long) this.chunkHeaderHolder.size) + 8;
                    return 0;
                }
            case 4:
                extractorInput.readFully(this.scratch.getData(), 0, 8);
                this.scratch.setPosition(0);
                int readLittleEndianInt2 = this.scratch.readLittleEndianInt();
                int readLittleEndianInt3 = this.scratch.readLittleEndianInt();
                if (readLittleEndianInt2 == 829973609) {
                    this.state = 5;
                    this.idx1BodySize = readLittleEndianInt3;
                } else {
                    this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt3);
                }
                return 0;
            case 5:
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(this.idx1BodySize);
                extractorInput.readFully(parsableByteArray2.getData(), 0, this.idx1BodySize);
                parseIdx1Body(parsableByteArray2);
                this.state = 6;
                this.pendingReposition = this.moviStart;
                return 0;
            case 6:
                return readMoviChunks(extractorInput);
            default:
                throw new AssertionError();
        }
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        this.pendingReposition = -1;
        this.currentChunkReader = null;
        for (ChunkReader seekToPosition : this.chunkReaders) {
            seekToPosition.seekToPosition(j2);
        }
        if (j2 != 0) {
            this.state = 6;
        } else if (this.chunkReaders.length == 0) {
            this.state = 0;
        } else {
            this.state = 3;
        }
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 12);
        this.scratch.setPosition(0);
        if (this.scratch.readLittleEndianInt() != 1179011410) {
            return false;
        }
        this.scratch.skipBytes(4);
        return this.scratch.readLittleEndianInt() == 541677121;
    }
}
