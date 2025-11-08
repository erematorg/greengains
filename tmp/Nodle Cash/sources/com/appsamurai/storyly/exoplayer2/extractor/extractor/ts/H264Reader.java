package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.CodecSpecificDataUtil;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.TsPayloadReader;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class H264Reader implements ElementaryStreamReader {
    private final boolean allowNonIdrKeyframes;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs = C.TIME_UNSET;
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(8, 128);
    private final boolean[] prefixFlags = new boolean[3];
    private boolean randomAccessIndicator;
    private SampleReader sampleReader;
    private final NalUnitTargetBuffer sei = new NalUnitTargetBuffer(6, 128);
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper = new ParsableByteArray();
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(7, 128);
    private long totalBytesWritten;

    public static final class SampleReader {
        private static final int DEFAULT_BUFFER_SIZE = 128;
        private final boolean allowNonIdrKeyframes;
        private final ParsableNalUnitBitArray bitArray;
        private byte[] buffer;
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private final SparseArray<NalUnitUtil.PpsData> pps = new SparseArray<>();
        private SliceHeaderData previousSliceHeader = new SliceHeaderData();
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader = new SliceHeaderData();
        private final SparseArray<NalUnitUtil.SpsData> sps = new SparseArray<>();

        public static final class SliceHeaderData {
            private static final int SLICE_TYPE_ALL_I = 7;
            private static final int SLICE_TYPE_I = 2;
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            @Nullable
            private NalUnitUtil.SpsData spsData;

            private SliceHeaderData() {
            }

            /* access modifiers changed from: private */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
                r4 = r6.nalRefIdc;
                r5 = r7.nalRefIdc;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0046, code lost:
                r0 = r0.picOrderCountType;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x006c, code lost:
                r0 = r6.idrPicFlag;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean isFirstVclNalUnitOfPicture(com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.H264Reader.SampleReader.SliceHeaderData r7) {
                /*
                    r6 = this;
                    boolean r0 = r6.isComplete
                    r1 = 0
                    if (r0 != 0) goto L_0x0006
                    return r1
                L_0x0006:
                    boolean r0 = r7.isComplete
                    r2 = 1
                    if (r0 != 0) goto L_0x000c
                    return r2
                L_0x000c:
                    com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$SpsData r0 = r6.spsData
                    java.lang.Object r0 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkStateNotNull(r0)
                    com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$SpsData r0 = (com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil.SpsData) r0
                    com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$SpsData r3 = r7.spsData
                    java.lang.Object r3 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkStateNotNull(r3)
                    com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$SpsData r3 = (com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil.SpsData) r3
                    int r4 = r6.frameNum
                    int r5 = r7.frameNum
                    if (r4 != r5) goto L_0x007a
                    int r4 = r6.picParameterSetId
                    int r5 = r7.picParameterSetId
                    if (r4 != r5) goto L_0x007a
                    boolean r4 = r6.fieldPicFlag
                    boolean r5 = r7.fieldPicFlag
                    if (r4 != r5) goto L_0x007a
                    boolean r4 = r6.bottomFieldFlagPresent
                    if (r4 == 0) goto L_0x003c
                    boolean r4 = r7.bottomFieldFlagPresent
                    if (r4 == 0) goto L_0x003c
                    boolean r4 = r6.bottomFieldFlag
                    boolean r5 = r7.bottomFieldFlag
                    if (r4 != r5) goto L_0x007a
                L_0x003c:
                    int r4 = r6.nalRefIdc
                    int r5 = r7.nalRefIdc
                    if (r4 == r5) goto L_0x0046
                    if (r4 == 0) goto L_0x007a
                    if (r5 == 0) goto L_0x007a
                L_0x0046:
                    int r0 = r0.picOrderCountType
                    if (r0 != 0) goto L_0x005a
                    int r4 = r3.picOrderCountType
                    if (r4 != 0) goto L_0x005a
                    int r4 = r6.picOrderCntLsb
                    int r5 = r7.picOrderCntLsb
                    if (r4 != r5) goto L_0x007a
                    int r4 = r6.deltaPicOrderCntBottom
                    int r5 = r7.deltaPicOrderCntBottom
                    if (r4 != r5) goto L_0x007a
                L_0x005a:
                    if (r0 != r2) goto L_0x006c
                    int r0 = r3.picOrderCountType
                    if (r0 != r2) goto L_0x006c
                    int r0 = r6.deltaPicOrderCnt0
                    int r3 = r7.deltaPicOrderCnt0
                    if (r0 != r3) goto L_0x007a
                    int r0 = r6.deltaPicOrderCnt1
                    int r3 = r7.deltaPicOrderCnt1
                    if (r0 != r3) goto L_0x007a
                L_0x006c:
                    boolean r0 = r6.idrPicFlag
                    boolean r3 = r7.idrPicFlag
                    if (r0 != r3) goto L_0x007a
                    if (r0 == 0) goto L_0x007b
                    int r6 = r6.idrPicId
                    int r7 = r7.idrPicId
                    if (r6 == r7) goto L_0x007b
                L_0x007a:
                    r1 = r2
                L_0x007b:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.H264Reader.SampleReader.SliceHeaderData.isFirstVclNalUnitOfPicture(com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.H264Reader$SampleReader$SliceHeaderData):boolean");
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
                r1 = r1.sliceType;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean isISlice() {
                /*
                    r1 = this;
                    boolean r0 = r1.hasSliceType
                    if (r0 == 0) goto L_0x000e
                    int r1 = r1.sliceType
                    r0 = 7
                    if (r1 == r0) goto L_0x000c
                    r0 = 2
                    if (r1 != r0) goto L_0x000e
                L_0x000c:
                    r1 = 1
                    goto L_0x000f
                L_0x000e:
                    r1 = 0
                L_0x000f:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.H264Reader.SampleReader.SliceHeaderData.isISlice():boolean");
            }

            public void setAll(NalUnitUtil.SpsData spsData2, int i3, int i4, int i5, int i6, boolean z2, boolean z3, boolean z4, boolean z5, int i7, int i8, int i9, int i10, int i11) {
                this.spsData = spsData2;
                this.nalRefIdc = i3;
                this.sliceType = i4;
                this.frameNum = i5;
                this.picParameterSetId = i6;
                this.fieldPicFlag = z2;
                this.bottomFieldFlagPresent = z3;
                this.bottomFieldFlag = z4;
                this.idrPicFlag = z5;
                this.idrPicId = i7;
                this.picOrderCntLsb = i8;
                this.deltaPicOrderCntBottom = i9;
                this.deltaPicOrderCnt0 = i10;
                this.deltaPicOrderCnt1 = i11;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public void setSliceType(int i3) {
                this.sliceType = i3;
                this.hasSliceType = true;
            }
        }

        public SampleReader(TrackOutput trackOutput, boolean z2, boolean z3) {
            this.output = trackOutput;
            this.allowNonIdrKeyframes = z2;
            this.detectAccessUnits = z3;
            byte[] bArr = new byte[128];
            this.buffer = bArr;
            this.bitArray = new ParsableNalUnitBitArray(bArr, 0, 0);
            reset();
        }

        private void outputSample(int i3) {
            long j2 = this.sampleTimeUs;
            if (j2 != C.TIME_UNSET) {
                boolean z2 = this.sampleIsKeyframe;
                this.output.sampleMetadata(j2, z2 ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), i3, (TrackOutput.CryptoData) null);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:51:0x0100  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x0103  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0119  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x011f  */
        /* JADX WARNING: Removed duplicated region for block: B:74:0x0157  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void appendToNalUnit(byte[] r24, int r25, int r26) {
            /*
                r23 = this;
                r0 = r23
                r1 = r25
                boolean r2 = r0.isFilling
                if (r2 != 0) goto L_0x0009
                return
            L_0x0009:
                int r2 = r26 - r1
                byte[] r3 = r0.buffer
                int r4 = r3.length
                int r5 = r0.bufferLength
                int r6 = r5 + r2
                r7 = 2
                if (r4 >= r6) goto L_0x001d
                int r5 = r5 + r2
                int r5 = r5 * r7
                byte[] r3 = java.util.Arrays.copyOf(r3, r5)
                r0.buffer = r3
            L_0x001d:
                byte[] r3 = r0.buffer
                int r4 = r0.bufferLength
                r5 = r24
                java.lang.System.arraycopy(r5, r1, r3, r4, r2)
                int r1 = r0.bufferLength
                int r1 = r1 + r2
                r0.bufferLength = r1
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r2 = r0.bitArray
                byte[] r3 = r0.buffer
                r4 = 0
                r2.reset(r3, r4, r1)
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                r2 = 8
                boolean r1 = r1.canReadBits(r2)
                if (r1 != 0) goto L_0x003e
                return
            L_0x003e:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                r1.skipBit()
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                int r10 = r1.readBits(r7)
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                r2 = 5
                r1.skipBits(r2)
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x0058
                return
            L_0x0058:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                r1.readUnsignedExpGolombCodedInt()
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x0066
                return
            L_0x0066:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                int r11 = r1.readUnsignedExpGolombCodedInt()
                boolean r1 = r0.detectAccessUnits
                if (r1 != 0) goto L_0x0078
                r0.isFilling = r4
                com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.H264Reader$SampleReader$SliceHeaderData r0 = r0.sliceHeader
                r0.setSliceType(r11)
                return
            L_0x0078:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x0081
                return
            L_0x0081:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                int r13 = r1.readUnsignedExpGolombCodedInt()
                android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$PpsData> r1 = r0.pps
                int r1 = r1.indexOfKey(r13)
                if (r1 >= 0) goto L_0x0092
                r0.isFilling = r4
                return
            L_0x0092:
                android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$PpsData> r1 = r0.pps
                java.lang.Object r1 = r1.get(r13)
                com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$PpsData r1 = (com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil.PpsData) r1
                android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$SpsData> r3 = r0.sps
                int r5 = r1.seqParameterSetId
                java.lang.Object r3 = r3.get(r5)
                r9 = r3
                com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil$SpsData r9 = (com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil.SpsData) r9
                boolean r3 = r9.separateColorPlaneFlag
                if (r3 == 0) goto L_0x00b7
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r3 = r0.bitArray
                boolean r3 = r3.canReadBits(r7)
                if (r3 != 0) goto L_0x00b2
                return
            L_0x00b2:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r3 = r0.bitArray
                r3.skipBits(r7)
            L_0x00b7:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r3 = r0.bitArray
                int r5 = r9.frameNumLength
                boolean r3 = r3.canReadBits(r5)
                if (r3 != 0) goto L_0x00c2
                return
            L_0x00c2:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r3 = r0.bitArray
                int r5 = r9.frameNumLength
                int r12 = r3.readBits(r5)
                boolean r3 = r9.frameMbsOnlyFlag
                r5 = 1
                if (r3 != 0) goto L_0x00f9
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r3 = r0.bitArray
                boolean r3 = r3.canReadBits(r5)
                if (r3 != 0) goto L_0x00d8
                return
            L_0x00d8:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r3 = r0.bitArray
                boolean r3 = r3.readBit()
                if (r3 == 0) goto L_0x00f4
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r6 = r0.bitArray
                boolean r6 = r6.canReadBits(r5)
                if (r6 != 0) goto L_0x00e9
                return
            L_0x00e9:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r6 = r0.bitArray
                boolean r6 = r6.readBit()
                r14 = r3
                r15 = r5
                r16 = r6
                goto L_0x00fc
            L_0x00f4:
                r14 = r3
                r15 = r4
            L_0x00f6:
                r16 = r15
                goto L_0x00fc
            L_0x00f9:
                r14 = r4
                r15 = r14
                goto L_0x00f6
            L_0x00fc:
                int r3 = r0.nalUnitType
                if (r3 != r2) goto L_0x0103
                r17 = r5
                goto L_0x0105
            L_0x0103:
                r17 = r4
            L_0x0105:
                if (r17 == 0) goto L_0x0119
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r2 = r0.bitArray
                boolean r2 = r2.canReadExpGolombCodedNum()
                if (r2 != 0) goto L_0x0110
                return
            L_0x0110:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r2 = r0.bitArray
                int r2 = r2.readUnsignedExpGolombCodedInt()
                r18 = r2
                goto L_0x011b
            L_0x0119:
                r18 = r4
            L_0x011b:
                int r2 = r9.picOrderCountType
                if (r2 != 0) goto L_0x0157
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r2 = r0.bitArray
                int r3 = r9.picOrderCntLsbLength
                boolean r2 = r2.canReadBits(r3)
                if (r2 != 0) goto L_0x012a
                return
            L_0x012a:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r2 = r0.bitArray
                int r3 = r9.picOrderCntLsbLength
                int r2 = r2.readBits(r3)
                boolean r1 = r1.bottomFieldPicOrderInFramePresentFlag
                if (r1 == 0) goto L_0x0150
                if (r14 != 0) goto L_0x0150
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x0141
                return
            L_0x0141:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                int r1 = r1.readSignedExpGolombCodedInt()
                r20 = r1
                r19 = r2
                r21 = r4
            L_0x014d:
                r22 = r21
                goto L_0x0198
            L_0x0150:
                r19 = r2
                r20 = r4
            L_0x0154:
                r21 = r20
                goto L_0x014d
            L_0x0157:
                if (r2 != r5) goto L_0x0193
                boolean r2 = r9.deltaPicOrderAlwaysZeroFlag
                if (r2 != 0) goto L_0x0193
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r2 = r0.bitArray
                boolean r2 = r2.canReadExpGolombCodedNum()
                if (r2 != 0) goto L_0x0166
                return
            L_0x0166:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r2 = r0.bitArray
                int r2 = r2.readSignedExpGolombCodedInt()
                boolean r1 = r1.bottomFieldPicOrderInFramePresentFlag
                if (r1 == 0) goto L_0x018a
                if (r14 != 0) goto L_0x018a
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                boolean r1 = r1.canReadExpGolombCodedNum()
                if (r1 != 0) goto L_0x017b
                return
            L_0x017b:
                com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray r1 = r0.bitArray
                int r1 = r1.readSignedExpGolombCodedInt()
                r22 = r1
                r21 = r2
                r19 = r4
                r20 = r19
                goto L_0x0198
            L_0x018a:
                r21 = r2
                r19 = r4
                r20 = r19
                r22 = r20
                goto L_0x0198
            L_0x0193:
                r19 = r4
                r20 = r19
                goto L_0x0154
            L_0x0198:
                com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.H264Reader$SampleReader$SliceHeaderData r8 = r0.sliceHeader
                r8.setAll(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                r0.isFilling = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.ts.H264Reader.SampleReader.appendToNalUnit(byte[], int, int):void");
        }

        public boolean endNalUnit(long j2, int i3, boolean z2, boolean z3) {
            boolean z4 = false;
            if (this.nalUnitType == 9 || (this.detectAccessUnits && this.sliceHeader.isFirstVclNalUnitOfPicture(this.previousSliceHeader))) {
                if (z2 && this.readingSample) {
                    outputSample(i3 + ((int) (j2 - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }
            if (this.allowNonIdrKeyframes) {
                z3 = this.sliceHeader.isISlice();
            }
            boolean z5 = this.sampleIsKeyframe;
            int i4 = this.nalUnitType;
            if (i4 == 5 || (z3 && i4 == 1)) {
                z4 = true;
            }
            boolean z6 = z5 | z4;
            this.sampleIsKeyframe = z6;
            return z6;
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        public void putPps(NalUnitUtil.PpsData ppsData) {
            this.pps.append(ppsData.picParameterSetId, ppsData);
        }

        public void putSps(NalUnitUtil.SpsData spsData) {
            this.sps.append(spsData.seqParameterSetId, spsData);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long j2, int i3, long j3) {
            this.nalUnitType = i3;
            this.nalUnitTimeUs = j3;
            this.nalUnitStartPosition = j2;
            if (!this.allowNonIdrKeyframes || i3 != 1) {
                if (!this.detectAccessUnits) {
                    return;
                }
                if (!(i3 == 5 || i3 == 1 || i3 == 2)) {
                    return;
                }
            }
            SliceHeaderData sliceHeaderData = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = sliceHeaderData;
            sliceHeaderData.clear();
            this.bufferLength = 0;
            this.isFilling = true;
        }
    }

    public H264Reader(SeiReader seiReader2, boolean z2, boolean z3) {
        this.seiReader = seiReader2;
        this.allowNonIdrKeyframes = z2;
        this.detectAccessUnits = z3;
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        Assertions.checkStateNotNull(this.output);
        Util.castNonNull(this.sampleReader);
    }

    @RequiresNonNull({"output", "sampleReader"})
    private void endNalUnit(long j2, int i3, int i4, long j3) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.endNalUnit(i4);
            this.pps.endNalUnit(i4);
            if (!this.hasOutputFormat) {
                if (this.sps.isCompleted() && this.pps.isCompleted()) {
                    ArrayList arrayList = new ArrayList();
                    NalUnitTargetBuffer nalUnitTargetBuffer = this.sps;
                    arrayList.add(Arrays.copyOf(nalUnitTargetBuffer.nalData, nalUnitTargetBuffer.nalLength));
                    NalUnitTargetBuffer nalUnitTargetBuffer2 = this.pps;
                    arrayList.add(Arrays.copyOf(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
                    NalUnitTargetBuffer nalUnitTargetBuffer3 = this.sps;
                    NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer3.nalData, 3, nalUnitTargetBuffer3.nalLength);
                    NalUnitTargetBuffer nalUnitTargetBuffer4 = this.pps;
                    NalUnitUtil.PpsData parsePpsNalUnit = NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer4.nalData, 3, nalUnitTargetBuffer4.nalLength);
                    this.output.format(new Format.Builder().setId(this.formatId).setSampleMimeType(MimeTypes.VIDEO_H264).setCodecs(CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc)).setWidth(parseSpsNalUnit.width).setHeight(parseSpsNalUnit.height).setPixelWidthHeightRatio(parseSpsNalUnit.pixelWidthHeightRatio).setInitializationData(arrayList).build());
                    this.hasOutputFormat = true;
                    this.sampleReader.putSps(parseSpsNalUnit);
                    this.sampleReader.putPps(parsePpsNalUnit);
                    this.sps.reset();
                    this.pps.reset();
                }
            } else if (this.sps.isCompleted()) {
                NalUnitTargetBuffer nalUnitTargetBuffer5 = this.sps;
                this.sampleReader.putSps(NalUnitUtil.parseSpsNalUnit(nalUnitTargetBuffer5.nalData, 3, nalUnitTargetBuffer5.nalLength));
                this.sps.reset();
            } else if (this.pps.isCompleted()) {
                NalUnitTargetBuffer nalUnitTargetBuffer6 = this.pps;
                this.sampleReader.putPps(NalUnitUtil.parsePpsNalUnit(nalUnitTargetBuffer6.nalData, 3, nalUnitTargetBuffer6.nalLength));
                this.pps.reset();
            }
        }
        if (this.sei.endNalUnit(i4)) {
            NalUnitTargetBuffer nalUnitTargetBuffer7 = this.sei;
            this.seiWrapper.reset(this.sei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer7.nalData, nalUnitTargetBuffer7.nalLength));
            this.seiWrapper.setPosition(4);
            this.seiReader.consume(j3, this.seiWrapper);
        }
        if (this.sampleReader.endNalUnit(j2, i3, this.hasOutputFormat, this.randomAccessIndicator)) {
            this.randomAccessIndicator = false;
        }
    }

    @RequiresNonNull({"sampleReader"})
    private void nalUnitData(byte[] bArr, int i3, int i4) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.appendToNalUnit(bArr, i3, i4);
            this.pps.appendToNalUnit(bArr, i3, i4);
        }
        this.sei.appendToNalUnit(bArr, i3, i4);
        this.sampleReader.appendToNalUnit(bArr, i3, i4);
    }

    @RequiresNonNull({"sampleReader"})
    private void startNalUnit(long j2, int i3, long j3) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.startNalUnit(i3);
            this.pps.startNalUnit(i3);
        }
        this.sei.startNalUnit(i3);
        this.sampleReader.startNalUnit(j2, i3, j3);
    }

    public void consume(ParsableByteArray parsableByteArray) {
        assertTracksCreated();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] data = parsableByteArray.getData();
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(data, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                nalUnitData(data, position, limit);
                return;
            }
            int nalUnitType = NalUnitUtil.getNalUnitType(data, findNalUnit);
            int i3 = findNalUnit - position;
            if (i3 > 0) {
                nalUnitData(data, position, findNalUnit);
            }
            int i4 = limit - findNalUnit;
            long j2 = this.totalBytesWritten - ((long) i4);
            endNalUnit(j2, i4, i3 < 0 ? -i3 : 0, this.pesTimeUs);
            startNalUnit(j2, nalUnitType, this.pesTimeUs);
            position = findNalUnit + 3;
        }
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = track;
        this.sampleReader = new SampleReader(track, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    public void packetFinished() {
    }

    public void packetStarted(long j2, int i3) {
        if (j2 != C.TIME_UNSET) {
            this.pesTimeUs = j2;
        }
        this.randomAccessIndicator |= (i3 & 2) != 0;
    }

    public void seek() {
        this.totalBytesWritten = 0;
        this.randomAccessIndicator = false;
        this.pesTimeUs = C.TIME_UNSET;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        SampleReader sampleReader2 = this.sampleReader;
        if (sampleReader2 != null) {
            sampleReader2.reset();
        }
    }
}
