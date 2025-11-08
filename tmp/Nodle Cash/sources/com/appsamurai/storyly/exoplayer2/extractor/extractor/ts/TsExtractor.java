package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TsExtractor implements Extractor {
    private static final long AC3_FORMAT_IDENTIFIER = 1094921523;
    private static final long AC4_FORMAT_IDENTIFIER = 1094921524;
    private static final int BUFFER_SIZE = 9400;
    public static final int DEFAULT_TIMESTAMP_SEARCH_BYTES = 112800;
    private static final long E_AC3_FORMAT_IDENTIFIER = 1161904947;
    public static final ExtractorsFactory FACTORY = new a(13);
    private static final long HEVC_FORMAT_IDENTIFIER = 1212503619;
    private static final int MAX_PID_PLUS_ONE = 8192;
    public static final int MODE_HLS = 2;
    public static final int MODE_MULTI_PMT = 0;
    public static final int MODE_SINGLE_PMT = 1;
    private static final int SNIFF_TS_PACKET_COUNT = 5;
    public static final int TS_PACKET_SIZE = 188;
    private static final int TS_PAT_PID = 0;
    public static final int TS_STREAM_TYPE_AAC_ADTS = 15;
    public static final int TS_STREAM_TYPE_AAC_LATM = 17;
    public static final int TS_STREAM_TYPE_AC3 = 129;
    public static final int TS_STREAM_TYPE_AC4 = 172;
    public static final int TS_STREAM_TYPE_AIT = 257;
    public static final int TS_STREAM_TYPE_DC2_H262 = 128;
    public static final int TS_STREAM_TYPE_DTS = 138;
    public static final int TS_STREAM_TYPE_DVBSUBS = 89;
    public static final int TS_STREAM_TYPE_E_AC3 = 135;
    public static final int TS_STREAM_TYPE_H262 = 2;
    public static final int TS_STREAM_TYPE_H263 = 16;
    public static final int TS_STREAM_TYPE_H264 = 27;
    public static final int TS_STREAM_TYPE_H265 = 36;
    public static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    public static final int TS_STREAM_TYPE_ID3 = 21;
    public static final int TS_STREAM_TYPE_MPA = 3;
    public static final int TS_STREAM_TYPE_MPA_LSF = 4;
    public static final int TS_STREAM_TYPE_SPLICE_INFO = 134;
    public static final int TS_SYNC_BYTE = 71;
    private int bytesSinceLastSync;
    private final SparseIntArray continuityCounters;
    private final TsDurationReader durationReader;
    private boolean hasOutputSeekMap;
    /* access modifiers changed from: private */
    @Nullable
    public TsPayloadReader id3Reader;
    /* access modifiers changed from: private */
    public final int mode;
    /* access modifiers changed from: private */
    public ExtractorOutput output;
    /* access modifiers changed from: private */
    public final TsPayloadReader.Factory payloadReaderFactory;
    /* access modifiers changed from: private */
    public int pcrPid;
    private boolean pendingSeekToStart;
    /* access modifiers changed from: private */
    public int remainingPmts;
    /* access modifiers changed from: private */
    public final List<TimestampAdjuster> timestampAdjusters;
    private final int timestampSearchBytes;
    /* access modifiers changed from: private */
    public final SparseBooleanArray trackIds;
    /* access modifiers changed from: private */
    public final SparseBooleanArray trackPids;
    /* access modifiers changed from: private */
    public boolean tracksEnded;
    private TsBinarySearchSeeker tsBinarySearchSeeker;
    private final ParsableByteArray tsPacketBuffer;
    /* access modifiers changed from: private */
    public final SparseArray<TsPayloadReader> tsPayloadReaders;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch = new ParsableBitArray(new byte[4]);

        public PatReader() {
        }

        public void consume(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.readUnsignedByte() == 0 && (parsableByteArray.readUnsignedByte() & 128) != 0) {
                parsableByteArray.skipBytes(6);
                int bytesLeft = parsableByteArray.bytesLeft() / 4;
                for (int i3 = 0; i3 < bytesLeft; i3++) {
                    parsableByteArray.readBytes(this.patScratch, 4);
                    int readBits = this.patScratch.readBits(16);
                    this.patScratch.skipBits(3);
                    if (readBits == 0) {
                        this.patScratch.skipBits(13);
                    } else {
                        int readBits2 = this.patScratch.readBits(13);
                        if (TsExtractor.this.tsPayloadReaders.get(readBits2) == null) {
                            TsExtractor.this.tsPayloadReaders.put(readBits2, new SectionReader(new PmtReader(readBits2)));
                            TsExtractor.access$108(TsExtractor.this);
                        }
                    }
                }
                if (TsExtractor.this.mode != 2) {
                    TsExtractor.this.tsPayloadReaders.remove(0);
                }
            }
        }

        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    public class PmtReader implements SectionPayloadReader {
        private static final int TS_PMT_DESC_AC3 = 106;
        private static final int TS_PMT_DESC_AIT = 111;
        private static final int TS_PMT_DESC_DTS = 123;
        private static final int TS_PMT_DESC_DVBSUBS = 89;
        private static final int TS_PMT_DESC_DVB_EXT = 127;
        private static final int TS_PMT_DESC_DVB_EXT_AC4 = 21;
        private static final int TS_PMT_DESC_EAC3 = 122;
        private static final int TS_PMT_DESC_ISO639_LANG = 10;
        private static final int TS_PMT_DESC_REGISTRATION = 5;
        private final int pid;
        private final ParsableBitArray pmtScratch = new ParsableBitArray(new byte[5]);
        private final SparseIntArray trackIdToPidScratch = new SparseIntArray();
        private final SparseArray<TsPayloadReader> trackIdToReaderScratch = new SparseArray<>();

        public PmtReader(int i3) {
            this.pid = i3;
        }

        private TsPayloadReader.EsInfo readEsInfo(ParsableByteArray parsableByteArray, int i3) {
            int position = parsableByteArray.getPosition();
            int i4 = i3 + position;
            int i5 = -1;
            String str = null;
            ArrayList arrayList = null;
            while (parsableByteArray.getPosition() < i4) {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                int position2 = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
                if (position2 > i4) {
                    break;
                }
                if (readUnsignedByte == 5) {
                    long readUnsignedInt = parsableByteArray.readUnsignedInt();
                    if (readUnsignedInt != TsExtractor.AC3_FORMAT_IDENTIFIER) {
                        if (readUnsignedInt != TsExtractor.E_AC3_FORMAT_IDENTIFIER) {
                            if (readUnsignedInt != TsExtractor.AC4_FORMAT_IDENTIFIER) {
                                if (readUnsignedInt == TsExtractor.HEVC_FORMAT_IDENTIFIER) {
                                    i5 = 36;
                                }
                                parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                            }
                        }
                        i5 = 135;
                        parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                    }
                    i5 = 129;
                    parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                } else {
                    if (readUnsignedByte != 106) {
                        if (readUnsignedByte != 122) {
                            if (readUnsignedByte == 127) {
                                if (parsableByteArray.readUnsignedByte() != 21) {
                                }
                            } else if (readUnsignedByte == 123) {
                                i5 = 138;
                            } else if (readUnsignedByte == 10) {
                                str = parsableByteArray.readString(3).trim();
                            } else if (readUnsignedByte == 89) {
                                ArrayList arrayList2 = new ArrayList();
                                while (parsableByteArray.getPosition() < position2) {
                                    String trim = parsableByteArray.readString(3).trim();
                                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                                    byte[] bArr = new byte[4];
                                    parsableByteArray.readBytes(bArr, 0, 4);
                                    arrayList2.add(new TsPayloadReader.DvbSubtitleInfo(trim, readUnsignedByte2, bArr));
                                }
                                arrayList = arrayList2;
                                i5 = 89;
                            } else if (readUnsignedByte == 111) {
                                i5 = 257;
                            }
                            parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                        }
                        i5 = 135;
                        parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                    }
                    i5 = 129;
                    parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                }
                i5 = 172;
                parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
            }
            parsableByteArray.setPosition(i4);
            return new TsPayloadReader.EsInfo(i5, str, arrayList, Arrays.copyOfRange(parsableByteArray.getData(), position, i4));
        }

        public void consume(ParsableByteArray parsableByteArray) {
            TimestampAdjuster timestampAdjuster;
            ParsableByteArray parsableByteArray2 = parsableByteArray;
            if (parsableByteArray.readUnsignedByte() == 2) {
                if (TsExtractor.this.mode == 1 || TsExtractor.this.mode == 2 || TsExtractor.this.remainingPmts == 1) {
                    timestampAdjuster = (TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0);
                } else {
                    timestampAdjuster = new TimestampAdjuster(((TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0)).getFirstSampleTimestampUs());
                    TsExtractor.this.timestampAdjusters.add(timestampAdjuster);
                }
                if ((parsableByteArray.readUnsignedByte() & 128) != 0) {
                    parsableByteArray2.skipBytes(1);
                    int readUnsignedShort = parsableByteArray.readUnsignedShort();
                    int i3 = 3;
                    parsableByteArray2.skipBytes(3);
                    parsableByteArray2.readBytes(this.pmtScratch, 2);
                    this.pmtScratch.skipBits(3);
                    int i4 = 13;
                    int unused = TsExtractor.this.pcrPid = this.pmtScratch.readBits(13);
                    parsableByteArray2.readBytes(this.pmtScratch, 2);
                    int i5 = 4;
                    this.pmtScratch.skipBits(4);
                    parsableByteArray2.skipBytes(this.pmtScratch.readBits(12));
                    if (TsExtractor.this.mode == 2 && TsExtractor.this.id3Reader == null) {
                        TsPayloadReader.EsInfo esInfo = new TsPayloadReader.EsInfo(21, (String) null, (List<TsPayloadReader.DvbSubtitleInfo>) null, Util.EMPTY_BYTE_ARRAY);
                        TsExtractor tsExtractor = TsExtractor.this;
                        TsPayloadReader unused2 = tsExtractor.id3Reader = tsExtractor.payloadReaderFactory.createPayloadReader(21, esInfo);
                        if (TsExtractor.this.id3Reader != null) {
                            TsExtractor.this.id3Reader.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(readUnsignedShort, 21, 8192));
                        }
                    }
                    this.trackIdToReaderScratch.clear();
                    this.trackIdToPidScratch.clear();
                    int bytesLeft = parsableByteArray.bytesLeft();
                    while (bytesLeft > 0) {
                        parsableByteArray2.readBytes(this.pmtScratch, 5);
                        int readBits = this.pmtScratch.readBits(8);
                        this.pmtScratch.skipBits(i3);
                        int readBits2 = this.pmtScratch.readBits(i4);
                        this.pmtScratch.skipBits(i5);
                        int readBits3 = this.pmtScratch.readBits(12);
                        TsPayloadReader.EsInfo readEsInfo = readEsInfo(parsableByteArray2, readBits3);
                        if (readBits == 6 || readBits == 5) {
                            readBits = readEsInfo.streamType;
                        }
                        bytesLeft -= readBits3 + 5;
                        int i6 = TsExtractor.this.mode == 2 ? readBits : readBits2;
                        if (!TsExtractor.this.trackIds.get(i6)) {
                            TsPayloadReader access$500 = (TsExtractor.this.mode == 2 && readBits == 21) ? TsExtractor.this.id3Reader : TsExtractor.this.payloadReaderFactory.createPayloadReader(readBits, readEsInfo);
                            if (TsExtractor.this.mode != 2 || readBits2 < this.trackIdToPidScratch.get(i6, 8192)) {
                                this.trackIdToPidScratch.put(i6, readBits2);
                                this.trackIdToReaderScratch.put(i6, access$500);
                            }
                        }
                        i3 = 3;
                        i5 = 4;
                        i4 = 13;
                    }
                    int size = this.trackIdToPidScratch.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        int keyAt = this.trackIdToPidScratch.keyAt(i7);
                        int valueAt = this.trackIdToPidScratch.valueAt(i7);
                        TsExtractor.this.trackIds.put(keyAt, true);
                        TsExtractor.this.trackPids.put(valueAt, true);
                        TsPayloadReader valueAt2 = this.trackIdToReaderScratch.valueAt(i7);
                        if (valueAt2 != null) {
                            if (valueAt2 != TsExtractor.this.id3Reader) {
                                valueAt2.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(readUnsignedShort, keyAt, 8192));
                            }
                            TsExtractor.this.tsPayloadReaders.put(valueAt, valueAt2);
                        }
                    }
                    if (TsExtractor.this.mode != 2) {
                        TsExtractor.this.tsPayloadReaders.remove(this.pid);
                        TsExtractor tsExtractor2 = TsExtractor.this;
                        int unused3 = tsExtractor2.remainingPmts = tsExtractor2.mode == 1 ? 0 : TsExtractor.this.remainingPmts - 1;
                        if (TsExtractor.this.remainingPmts == 0) {
                            TsExtractor.this.output.endTracks();
                            boolean unused4 = TsExtractor.this.tracksEnded = true;
                        }
                    } else if (!TsExtractor.this.tracksEnded) {
                        TsExtractor.this.output.endTracks();
                        int unused5 = TsExtractor.this.remainingPmts = 0;
                        boolean unused6 = TsExtractor.this.tracksEnded = true;
                    }
                }
            }
        }

        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    public TsExtractor() {
        this(0);
    }

    public static /* synthetic */ int access$108(TsExtractor tsExtractor) {
        int i3 = tsExtractor.remainingPmts;
        tsExtractor.remainingPmts = i3 + 1;
        return i3;
    }

    private boolean fillBufferWithAtLeastOnePacket(ExtractorInput extractorInput) throws IOException {
        byte[] data = this.tsPacketBuffer.getData();
        if (9400 - this.tsPacketBuffer.getPosition() < 188) {
            int bytesLeft = this.tsPacketBuffer.bytesLeft();
            if (bytesLeft > 0) {
                System.arraycopy(data, this.tsPacketBuffer.getPosition(), data, 0, bytesLeft);
            }
            this.tsPacketBuffer.reset(data, bytesLeft);
        }
        while (this.tsPacketBuffer.bytesLeft() < 188) {
            int limit = this.tsPacketBuffer.limit();
            int read = extractorInput.read(data, limit, 9400 - limit);
            if (read == -1) {
                return false;
            }
            this.tsPacketBuffer.setLimit(limit + read);
        }
        return true;
    }

    private int findEndOfFirstTsPacketInBuffer() throws ParserException {
        int position = this.tsPacketBuffer.getPosition();
        int limit = this.tsPacketBuffer.limit();
        int findSyncBytePosition = TsUtil.findSyncBytePosition(this.tsPacketBuffer.getData(), position, limit);
        this.tsPacketBuffer.setPosition(findSyncBytePosition);
        int i3 = findSyncBytePosition + 188;
        if (i3 > limit) {
            int i4 = (findSyncBytePosition - position) + this.bytesSinceLastSync;
            this.bytesSinceLastSync = i4;
            if (this.mode == 2 && i4 > 376) {
                throw ParserException.createForMalformedContainer("Cannot find sync byte. Most likely not a Transport Stream.", (Throwable) null);
            }
        } else {
            this.bytesSinceLastSync = 0;
        }
        return i3;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new TsExtractor()};
    }

    private void maybeOutputSeekMap(long j2) {
        if (!this.hasOutputSeekMap) {
            this.hasOutputSeekMap = true;
            if (this.durationReader.getDurationUs() != C.TIME_UNSET) {
                TsBinarySearchSeeker tsBinarySearchSeeker2 = new TsBinarySearchSeeker(this.durationReader.getPcrTimestampAdjuster(), this.durationReader.getDurationUs(), j2, this.pcrPid, this.timestampSearchBytes);
                this.tsBinarySearchSeeker = tsBinarySearchSeeker2;
                this.output.seekMap(tsBinarySearchSeeker2.getSeekMap());
                return;
            }
            this.output.seekMap(new SeekMap.Unseekable(this.durationReader.getDurationUs()));
        }
    }

    private void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray<TsPayloadReader> createInitialPayloadReaders = this.payloadReaderFactory.createInitialPayloadReaders();
        int size = createInitialPayloadReaders.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.tsPayloadReaders.put(createInitialPayloadReaders.keyAt(i3), createInitialPayloadReaders.valueAt(i3));
        }
        this.tsPayloadReaders.put(0, new SectionReader(new PatReader()));
        this.id3Reader = null;
    }

    private boolean shouldConsumePacketPayload(int i3) {
        return this.mode == 2 || this.tracksEnded || !this.trackPids.get(i3, false);
    }

    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        ExtractorInput extractorInput2 = extractorInput;
        PositionHolder positionHolder2 = positionHolder;
        long length = extractorInput.getLength();
        if (this.tracksEnded) {
            if (length != -1 && this.mode != 2 && !this.durationReader.isDurationReadFinished()) {
                return this.durationReader.readDuration(extractorInput2, positionHolder2, this.pcrPid);
            }
            maybeOutputSeekMap(length);
            if (this.pendingSeekToStart) {
                this.pendingSeekToStart = false;
                seek(0, 0);
                if (extractorInput.getPosition() != 0) {
                    positionHolder2.position = 0;
                    return 1;
                }
            }
            TsBinarySearchSeeker tsBinarySearchSeeker2 = this.tsBinarySearchSeeker;
            if (tsBinarySearchSeeker2 != null && tsBinarySearchSeeker2.isSeeking()) {
                return this.tsBinarySearchSeeker.handlePendingSeek(extractorInput2, positionHolder2);
            }
        }
        if (!fillBufferWithAtLeastOnePacket(extractorInput)) {
            return -1;
        }
        int findEndOfFirstTsPacketInBuffer = findEndOfFirstTsPacketInBuffer();
        int limit = this.tsPacketBuffer.limit();
        if (findEndOfFirstTsPacketInBuffer > limit) {
            return 0;
        }
        int readInt = this.tsPacketBuffer.readInt();
        if ((8388608 & readInt) != 0) {
            this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
            return 0;
        }
        int i3 = (4194304 & readInt) != 0 ? 1 : 0;
        int i4 = (2096896 & readInt) >> 8;
        boolean z2 = (readInt & 32) != 0;
        TsPayloadReader tsPayloadReader = (readInt & 16) != 0 ? this.tsPayloadReaders.get(i4) : null;
        if (tsPayloadReader == null) {
            this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
            return 0;
        }
        if (this.mode != 2) {
            int i5 = readInt & 15;
            int i6 = this.continuityCounters.get(i4, i5 - 1);
            this.continuityCounters.put(i4, i5);
            if (i6 == i5) {
                this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
                return 0;
            } else if (i5 != ((i6 + 1) & 15)) {
                tsPayloadReader.seek();
            }
        }
        if (z2) {
            int readUnsignedByte = this.tsPacketBuffer.readUnsignedByte();
            i3 |= (this.tsPacketBuffer.readUnsignedByte() & 64) != 0 ? 2 : 0;
            this.tsPacketBuffer.skipBytes(readUnsignedByte - 1);
        }
        boolean z3 = this.tracksEnded;
        if (shouldConsumePacketPayload(i4)) {
            this.tsPacketBuffer.setLimit(findEndOfFirstTsPacketInBuffer);
            tsPayloadReader.consume(this.tsPacketBuffer, i3);
            this.tsPacketBuffer.setLimit(limit);
        }
        if (this.mode != 2 && !z3 && this.tracksEnded && length != -1) {
            this.pendingSeekToStart = true;
        }
        this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
        return 0;
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        TsBinarySearchSeeker tsBinarySearchSeeker2;
        Assertions.checkState(this.mode != 2);
        int size = this.timestampAdjusters.size();
        for (int i3 = 0; i3 < size; i3++) {
            TimestampAdjuster timestampAdjuster = this.timestampAdjusters.get(i3);
            boolean z2 = timestampAdjuster.getTimestampOffsetUs() == C.TIME_UNSET;
            if (!z2) {
                long firstSampleTimestampUs = timestampAdjuster.getFirstSampleTimestampUs();
                z2 = (firstSampleTimestampUs == C.TIME_UNSET || firstSampleTimestampUs == 0 || firstSampleTimestampUs == j3) ? false : true;
            }
            if (z2) {
                timestampAdjuster.reset(j3);
            }
        }
        if (!(j3 == 0 || (tsBinarySearchSeeker2 = this.tsBinarySearchSeeker) == null)) {
            tsBinarySearchSeeker2.setSeekTargetUs(j3);
        }
        this.tsPacketBuffer.reset(0);
        this.continuityCounters.clear();
        for (int i4 = 0; i4 < this.tsPayloadReaders.size(); i4++) {
            this.tsPayloadReaders.valueAt(i4).seek();
        }
        this.bytesSinceLastSync = 0;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        byte[] data = this.tsPacketBuffer.getData();
        extractorInput.peekFully(data, 0, 940);
        int i3 = 0;
        while (i3 < 188) {
            int i4 = 0;
            while (i4 < 5) {
                if (data[(i4 * 188) + i3] != 71) {
                    i3++;
                } else {
                    i4++;
                }
            }
            extractorInput.skipFully(i3);
            return true;
        }
        return false;
    }

    public TsExtractor(int i3) {
        this(1, i3, (int) DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    public TsExtractor(int i3, int i4, int i5) {
        this(i3, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(i4), i5);
    }

    public TsExtractor(int i3, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory) {
        this(i3, timestampAdjuster, factory, DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    public TsExtractor(int i3, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory, int i4) {
        this.payloadReaderFactory = (TsPayloadReader.Factory) Assertions.checkNotNull(factory);
        this.timestampSearchBytes = i4;
        this.mode = i3;
        if (i3 == 1 || i3 == 2) {
            this.timestampAdjusters = Collections.singletonList(timestampAdjuster);
        } else {
            ArrayList arrayList = new ArrayList();
            this.timestampAdjusters = arrayList;
            arrayList.add(timestampAdjuster);
        }
        this.tsPacketBuffer = new ParsableByteArray(new byte[BUFFER_SIZE], 0);
        this.trackIds = new SparseBooleanArray();
        this.trackPids = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray<>();
        this.continuityCounters = new SparseIntArray();
        this.durationReader = new TsDurationReader(i4);
        this.output = ExtractorOutput.PLACEHOLDER;
        this.pcrPid = -1;
        resetPayloadReaders();
    }
}
