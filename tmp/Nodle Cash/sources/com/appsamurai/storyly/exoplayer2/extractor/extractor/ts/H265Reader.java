package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.CodecSpecificDataUtil;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class H265Reader implements ElementaryStreamReader {
    private static final int AUD_NUT = 35;
    private static final int BLA_W_LP = 16;
    private static final int CRA_NUT = 21;
    private static final int PPS_NUT = 34;
    private static final int PREFIX_SEI_NUT = 39;
    private static final int RASL_R = 9;
    private static final int SPS_NUT = 33;
    private static final int SUFFIX_SEI_NUT = 40;
    private static final String TAG = "H265Reader";
    private static final int VPS_NUT = 32;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs = C.TIME_UNSET;
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(34, 128);
    private final boolean[] prefixFlags = new boolean[3];
    private final NalUnitTargetBuffer prefixSei = new NalUnitTargetBuffer(39, 128);
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper = new ParsableByteArray();
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(33, 128);
    private final NalUnitTargetBuffer suffixSei = new NalUnitTargetBuffer(40, 128);
    private long totalBytesWritten;
    private final NalUnitTargetBuffer vps = new NalUnitTargetBuffer(32, 128);

    public static final class SampleReader {
        private static final int FIRST_SLICE_FLAG_OFFSET = 2;
        private boolean isFirstPrefixNalUnit;
        private boolean isFirstSlice;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingPrefix;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        private static boolean isPrefixNalUnit(int i3) {
            return (32 <= i3 && i3 <= 35) || i3 == 39;
        }

        private static boolean isVclBodyNalUnit(int i3) {
            return i3 < 32 || i3 == 40;
        }

        private void outputSample(int i3) {
            long j2 = this.sampleTimeUs;
            if (j2 != C.TIME_UNSET) {
                boolean z2 = this.sampleIsKeyframe;
                this.output.sampleMetadata(j2, z2 ? 1 : 0, (int) (this.nalUnitPosition - this.samplePosition), i3, (TrackOutput.CryptoData) null);
            }
        }

        public void endNalUnit(long j2, int i3, boolean z2) {
            if (this.readingPrefix && this.isFirstSlice) {
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingPrefix = false;
            } else if (this.isFirstPrefixNalUnit || this.isFirstSlice) {
                if (z2 && this.readingSample) {
                    outputSample(i3 + ((int) (j2 - this.nalUnitPosition)));
                }
                this.samplePosition = this.nalUnitPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingSample = true;
            }
        }

        public void readNalUnitData(byte[] bArr, int i3, int i4) {
            if (this.lookingForFirstSliceFlag) {
                int i5 = this.nalUnitBytesRead;
                int i6 = (i3 + 2) - i5;
                if (i6 < i4) {
                    this.isFirstSlice = (bArr[i6] & 128) != 0;
                    this.lookingForFirstSliceFlag = false;
                    return;
                }
                this.nalUnitBytesRead = (i4 - i3) + i5;
            }
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.readingSample = false;
            this.readingPrefix = false;
        }

        public void startNalUnit(long j2, int i3, int i4, long j3, boolean z2) {
            boolean z3 = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.nalUnitTimeUs = j3;
            this.nalUnitBytesRead = 0;
            this.nalUnitPosition = j2;
            if (!isVclBodyNalUnit(i4)) {
                if (this.readingSample && !this.readingPrefix) {
                    if (z2) {
                        outputSample(i3);
                    }
                    this.readingSample = false;
                }
                if (isPrefixNalUnit(i4)) {
                    this.isFirstPrefixNalUnit = !this.readingPrefix;
                    this.readingPrefix = true;
                }
            }
            boolean z4 = i4 >= 16 && i4 <= 21;
            this.nalUnitHasKeyframeData = z4;
            if (z4 || i4 <= 9) {
                z3 = true;
            }
            this.lookingForFirstSliceFlag = z3;
        }
    }

    public H265Reader(SeiReader seiReader2) {
        this.seiReader = seiReader2;
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    @RequiresNonNull({"output", "sampleReader"})
    private void endNalUnit(long j2, int i3, int i4, long j3) {
        this.sampleReader.endNalUnit(j2, i3, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.endNalUnit(i4);
            this.sps.endNalUnit(i4);
            this.pps.endNalUnit(i4);
            if (this.vps.isCompleted() && this.sps.isCompleted() && this.pps.isCompleted()) {
                this.output.format(parseMediaFormat(this.formatId, this.vps, this.sps, this.pps));
                this.hasOutputFormat = true;
            }
        }
        if (this.prefixSei.endNalUnit(i4)) {
            NalUnitTargetBuffer nalUnitTargetBuffer = this.prefixSei;
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer.nalData, nalUnitTargetBuffer.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j3, this.seiWrapper);
        }
        if (this.suffixSei.endNalUnit(i4)) {
            NalUnitTargetBuffer nalUnitTargetBuffer2 = this.suffixSei;
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j3, this.seiWrapper);
        }
    }

    @RequiresNonNull({"sampleReader"})
    private void nalUnitData(byte[] bArr, int i3, int i4) {
        this.sampleReader.readNalUnitData(bArr, i3, i4);
        if (!this.hasOutputFormat) {
            this.vps.appendToNalUnit(bArr, i3, i4);
            this.sps.appendToNalUnit(bArr, i3, i4);
            this.pps.appendToNalUnit(bArr, i3, i4);
        }
        this.prefixSei.appendToNalUnit(bArr, i3, i4);
        this.suffixSei.appendToNalUnit(bArr, i3, i4);
    }

    private static Format parseMediaFormat(@Nullable String str, NalUnitTargetBuffer nalUnitTargetBuffer, NalUnitTargetBuffer nalUnitTargetBuffer2, NalUnitTargetBuffer nalUnitTargetBuffer3) {
        int i3;
        NalUnitTargetBuffer nalUnitTargetBuffer4 = nalUnitTargetBuffer;
        NalUnitTargetBuffer nalUnitTargetBuffer5 = nalUnitTargetBuffer2;
        NalUnitTargetBuffer nalUnitTargetBuffer6 = nalUnitTargetBuffer3;
        int i4 = nalUnitTargetBuffer4.nalLength;
        byte[] bArr = new byte[(nalUnitTargetBuffer5.nalLength + i4 + nalUnitTargetBuffer6.nalLength)];
        int i5 = 0;
        System.arraycopy(nalUnitTargetBuffer4.nalData, 0, bArr, 0, i4);
        System.arraycopy(nalUnitTargetBuffer5.nalData, 0, bArr, nalUnitTargetBuffer4.nalLength, nalUnitTargetBuffer5.nalLength);
        System.arraycopy(nalUnitTargetBuffer6.nalData, 0, bArr, nalUnitTargetBuffer4.nalLength + nalUnitTargetBuffer5.nalLength, nalUnitTargetBuffer6.nalLength);
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(nalUnitTargetBuffer5.nalData, 0, nalUnitTargetBuffer5.nalLength);
        parsableNalUnitBitArray.skipBits(44);
        int readBits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        int readBits2 = parsableNalUnitBitArray.readBits(2);
        boolean readBit = parsableNalUnitBitArray.readBit();
        int readBits3 = parsableNalUnitBitArray.readBits(5);
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i3 = 1;
            if (i7 >= 32) {
                break;
            }
            if (parsableNalUnitBitArray.readBit()) {
                i6 |= 1 << i7;
            }
            i7++;
        }
        int[] iArr = new int[6];
        for (int i8 = 0; i8 < 6; i8++) {
            iArr[i8] = parsableNalUnitBitArray.readBits(8);
        }
        int readBits4 = parsableNalUnitBitArray.readBits(8);
        for (int i9 = 0; i9 < readBits; i9++) {
            if (parsableNalUnitBitArray.readBit()) {
                i5 += 89;
            }
            if (parsableNalUnitBitArray.readBit()) {
                i5 += 8;
            }
        }
        parsableNalUnitBitArray.skipBits(i5);
        if (readBits > 0) {
            parsableNalUnitBitArray.skipBits((8 - readBits) * 2);
        }
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (readUnsignedExpGolombCodedInt == 3) {
            parsableNalUnitBitArray.skipBit();
        }
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit()) {
            int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int i10 = (readUnsignedExpGolombCodedInt == 1 || readUnsignedExpGolombCodedInt == 2) ? 2 : 1;
            if (readUnsignedExpGolombCodedInt == 1) {
                i3 = 2;
            }
            readUnsignedExpGolombCodedInt2 -= (readUnsignedExpGolombCodedInt4 + readUnsignedExpGolombCodedInt5) * i10;
            readUnsignedExpGolombCodedInt3 -= (readUnsignedExpGolombCodedInt6 + readUnsignedExpGolombCodedInt7) * i3;
        }
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i11 = parsableNalUnitBitArray.readBit() ? 0 : readBits; i11 <= readBits; i11++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        }
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit() && parsableNalUnitBitArray.readBit()) {
            skipScalingList(parsableNalUnitBitArray);
        }
        parsableNalUnitBitArray.skipBits(2);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(8);
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        skipShortTermRefPicSets(parsableNalUnitBitArray);
        if (parsableNalUnitBitArray.readBit()) {
            for (int i12 = 0; i12 < parsableNalUnitBitArray.readUnsignedExpGolombCodedInt(); i12++) {
                parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt8 + 5);
            }
        }
        parsableNalUnitBitArray.skipBits(2);
        float f2 = 1.0f;
        if (parsableNalUnitBitArray.readBit()) {
            if (parsableNalUnitBitArray.readBit()) {
                int readBits5 = parsableNalUnitBitArray.readBits(8);
                if (readBits5 == 255) {
                    int readBits6 = parsableNalUnitBitArray.readBits(16);
                    int readBits7 = parsableNalUnitBitArray.readBits(16);
                    if (!(readBits6 == 0 || readBits7 == 0)) {
                        f2 = ((float) readBits6) / ((float) readBits7);
                    }
                } else {
                    float[] fArr = NalUnitUtil.ASPECT_RATIO_IDC_VALUES;
                    if (readBits5 < fArr.length) {
                        f2 = fArr[readBits5];
                    } else {
                        r.a(readBits5, "Unexpected aspect_ratio_idc value: ", TAG);
                    }
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBits(4);
                if (parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.skipBits(24);
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                readUnsignedExpGolombCodedInt3 *= 2;
            }
        }
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.VIDEO_H265).setCodecs(CodecSpecificDataUtil.buildHevcCodecString(readBits2, readBit, readBits3, i6, iArr, readBits4)).setWidth(readUnsignedExpGolombCodedInt2).setHeight(readUnsignedExpGolombCodedInt3).setPixelWidthHeightRatio(f2).setInitializationData(Collections.singletonList(bArr)).build();
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = 0;
            while (i4 < 6) {
                int i5 = 1;
                if (!parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int min = Math.min(64, 1 << ((i3 << 1) + 4));
                    if (i3 > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i6 = 0; i6 < min; i6++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (i3 == 3) {
                    i5 = 3;
                }
                i4 += i5;
            }
        }
    }

    private static void skipShortTermRefPicSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        boolean z2 = false;
        int i3 = 0;
        for (int i4 = 0; i4 < readUnsignedExpGolombCodedInt; i4++) {
            if (i4 != 0) {
                z2 = parsableNalUnitBitArray.readBit();
            }
            if (z2) {
                parsableNalUnitBitArray.skipBit();
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                for (int i5 = 0; i5 <= i3; i5++) {
                    if (parsableNalUnitBitArray.readBit()) {
                        parsableNalUnitBitArray.skipBit();
                    }
                }
            } else {
                int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int i6 = readUnsignedExpGolombCodedInt2 + readUnsignedExpGolombCodedInt3;
                for (int i7 = 0; i7 < readUnsignedExpGolombCodedInt2; i7++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.skipBit();
                }
                for (int i8 = 0; i8 < readUnsignedExpGolombCodedInt3; i8++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.skipBit();
                }
                i3 = i6;
            }
        }
    }

    @RequiresNonNull({"sampleReader"})
    private void startNalUnit(long j2, int i3, int i4, long j3) {
        this.sampleReader.startNalUnit(j2, i3, i4, j3, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.startNalUnit(i4);
            this.sps.startNalUnit(i4);
            this.pps.startNalUnit(i4);
        }
        this.prefixSei.startNalUnit(i4);
        this.suffixSei.startNalUnit(i4);
    }

    public void consume(ParsableByteArray parsableByteArray) {
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition();
            int limit = parsableByteArray.limit();
            byte[] data = parsableByteArray.getData();
            this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            while (true) {
                if (position < limit) {
                    int findNalUnit = NalUnitUtil.findNalUnit(data, position, limit, this.prefixFlags);
                    if (findNalUnit == limit) {
                        nalUnitData(data, position, limit);
                        return;
                    }
                    int h265NalUnitType = NalUnitUtil.getH265NalUnitType(data, findNalUnit);
                    int i3 = findNalUnit - position;
                    if (i3 > 0) {
                        nalUnitData(data, position, findNalUnit);
                    }
                    int i4 = limit - findNalUnit;
                    long j2 = this.totalBytesWritten - ((long) i4);
                    int i5 = i3 < 0 ? -i3 : 0;
                    long j3 = j2;
                    int i6 = i4;
                    endNalUnit(j3, i6, i5, this.pesTimeUs);
                    startNalUnit(j3, i6, h265NalUnitType, this.pesTimeUs);
                    position = findNalUnit + 3;
                }
            }
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = track;
        this.sampleReader = new SampleReader(track);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    public void packetFinished() {
    }

    public void packetStarted(long j2, int i3) {
        if (j2 != C.TIME_UNSET) {
            this.pesTimeUs = j2;
        }
    }

    public void seek() {
        this.totalBytesWritten = 0;
        this.pesTimeUs = C.TIME_UNSET;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        SampleReader sampleReader2 = this.sampleReader;
        if (sampleReader2 != null) {
            sampleReader2.reset();
        }
    }
}
