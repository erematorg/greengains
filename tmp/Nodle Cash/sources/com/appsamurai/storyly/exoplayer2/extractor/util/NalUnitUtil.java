package com.appsamurai.storyly.exoplayer2.extractor.util;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.core.r;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final int NAL_UNIT_TYPE_AUD = 9;
    public static final int NAL_UNIT_TYPE_IDR = 5;
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int NAL_UNIT_TYPE_PPS = 8;
    public static final int NAL_UNIT_TYPE_SEI = 6;
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    private static int[] scratchEscapePositions = new int[10];
    private static final Object scratchEscapePositionsLock = new Object();

    public static final class H265SpsData {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;
        public final int height;
        public final float pixelWidthHeightRatio;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(int i3, boolean z2, int i4, int i5, int[] iArr, int i6, int i7, int i8, int i9, float f2) {
            this.generalProfileSpace = i3;
            this.generalTierFlag = z2;
            this.generalProfileIdc = i4;
            this.generalProfileCompatibilityFlags = i5;
            this.constraintBytes = iArr;
            this.generalLevelIdc = i6;
            this.seqParameterSetId = i7;
            this.width = i8;
            this.height = i9;
            this.pixelWidthHeightRatio = f2;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i3, int i4, boolean z2) {
            this.picParameterSetId = i3;
            this.seqParameterSetId = i4;
            this.bottomFieldPicOrderInFramePresentFlag = z2;
        }
    }

    public static final class SpsData {
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i3, int i4, int i5, int i6, int i7, int i8, int i9, float f2, boolean z2, boolean z3, int i10, int i11, int i12, boolean z4) {
            this.profileIdc = i3;
            this.constraintsFlagsAndReservedZero2Bits = i4;
            this.levelIdc = i5;
            this.seqParameterSetId = i6;
            this.maxNumRefFrames = i7;
            this.width = i8;
            this.height = i9;
            this.pixelWidthHeightRatio = f2;
            this.separateColorPlaneFlag = z2;
            this.frameMbsOnlyFlag = z3;
            this.frameNumLength = i10;
            this.picOrderCountType = i11;
            this.picOrderCntLsbLength = i12;
            this.deltaPicOrderAlwaysZeroFlag = z4;
        }
    }

    private NalUnitUtil() {
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i3 + 1;
            if (i5 < position) {
                byte b3 = byteBuffer.get(i3) & 255;
                if (i4 == 3) {
                    if (b3 == 1 && (byteBuffer.get(i5) & 31) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i3 - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (b3 == 0) {
                    i4++;
                }
                if (b3 != 0) {
                    i4 = 0;
                }
                i3 = i5;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static int findNalUnit(byte[] bArr, int i3, int i4, boolean[] zArr) {
        int i5 = i4 - i3;
        boolean z2 = false;
        Assertions.checkState(i5 >= 0);
        if (i5 == 0) {
            return i4;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return i3 - 3;
        } else if (i5 > 1 && zArr[1] && bArr[i3] == 1) {
            clearPrefixFlags(zArr);
            return i3 - 2;
        } else if (i5 <= 2 || !zArr[2] || bArr[i3] != 0 || bArr[i3 + 1] != 1) {
            int i6 = i4 - 1;
            int i7 = i3 + 2;
            while (i7 < i6) {
                byte b3 = bArr[i7];
                if ((b3 & 254) == 0) {
                    int i8 = i7 - 2;
                    if (bArr[i8] == 0 && bArr[i7 - 1] == 0 && b3 == 1) {
                        clearPrefixFlags(zArr);
                        return i8;
                    }
                    i7 -= 2;
                }
                i7 += 3;
            }
            zArr[0] = i5 <= 2 ? !(i5 != 2 ? !zArr[1] || bArr[i6] != 1 : !(zArr[2] && bArr[i4 + -2] == 0 && bArr[i6] == 1)) : bArr[i4 + -3] == 0 && bArr[i4 + -2] == 0 && bArr[i6] == 1;
            zArr[1] = i5 <= 1 ? !(!zArr[2] || bArr[i6] != 0) : bArr[i4 + -2] == 0 && bArr[i6] == 0;
            if (bArr[i6] == 0) {
                z2 = true;
            }
            zArr[2] = z2;
            return i4;
        } else {
            clearPrefixFlags(zArr);
            return i3 - 1;
        }
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i3, int i4) {
        while (i3 < i4 - 2) {
            if (bArr[i3] == 0 && bArr[i3 + 1] == 0 && bArr[i3 + 2] == 3) {
                return i3;
            }
            i3++;
        }
        return i4;
    }

    public static int getH265NalUnitType(byte[] bArr, int i3) {
        return (bArr[i3 + 3] & 126) >> 1;
    }

    public static int getNalUnitType(byte[] bArr, int i3) {
        return bArr[i3 + 3] & 31;
    }

    public static boolean isNalUnitSei(@Nullable String str, byte b3) {
        if (!MimeTypes.VIDEO_H264.equals(str) || (b3 & 31) != 6) {
            return MimeTypes.VIDEO_H265.equals(str) && ((b3 & 126) >> 1) == 39;
        }
        return true;
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int i3, int i4) {
        return parseH265SpsNalUnitPayload(bArr, i3 + 2, i4);
    }

    public static H265SpsData parseH265SpsNalUnitPayload(byte[] bArr, int i3, int i4) {
        int i5;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i3, i4);
        parsableNalUnitBitArray.skipBits(4);
        int readBits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        int readBits2 = parsableNalUnitBitArray.readBits(2);
        boolean readBit = parsableNalUnitBitArray.readBit();
        int readBits3 = parsableNalUnitBitArray.readBits(5);
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i5 = 1;
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
        int i9 = 0;
        for (int i10 = 0; i10 < readBits; i10++) {
            if (parsableNalUnitBitArray.readBit()) {
                i9 += 89;
            }
            if (parsableNalUnitBitArray.readBit()) {
                i9 += 8;
            }
        }
        parsableNalUnitBitArray.skipBits(i9);
        if (readBits > 0) {
            parsableNalUnitBitArray.skipBits((8 - readBits) * 2);
        }
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (readUnsignedExpGolombCodedInt2 == 3) {
            parsableNalUnitBitArray.skipBit();
        }
        int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (parsableNalUnitBitArray.readBit()) {
            int readUnsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int i11 = (readUnsignedExpGolombCodedInt2 == 1 || readUnsignedExpGolombCodedInt2 == 2) ? 2 : 1;
            if (readUnsignedExpGolombCodedInt2 == 1) {
                i5 = 2;
            }
            readUnsignedExpGolombCodedInt3 -= (readUnsignedExpGolombCodedInt5 + readUnsignedExpGolombCodedInt6) * i11;
            readUnsignedExpGolombCodedInt4 -= (readUnsignedExpGolombCodedInt7 + readUnsignedExpGolombCodedInt8) * i5;
        }
        int i12 = readUnsignedExpGolombCodedInt3;
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i13 = parsableNalUnitBitArray.readBit() ? 0 : readBits; i13 <= readBits; i13++) {
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
            skipH265ScalingList(parsableNalUnitBitArray);
        }
        parsableNalUnitBitArray.skipBits(2);
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(8);
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        skipShortTermReferencePictureSets(parsableNalUnitBitArray);
        if (parsableNalUnitBitArray.readBit()) {
            for (int i14 = 0; i14 < parsableNalUnitBitArray.readUnsignedExpGolombCodedInt(); i14++) {
                parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt9 + 5);
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
                    float[] fArr = ASPECT_RATIO_IDC_VALUES;
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
                readUnsignedExpGolombCodedInt4 *= 2;
            }
        }
        return new H265SpsData(readBits2, readBit, readBits3, i6, iArr, readBits4, readUnsignedExpGolombCodedInt, i12, readUnsignedExpGolombCodedInt4, f2);
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i3, int i4) {
        return parsePpsNalUnitPayload(bArr, i3 + 1, i4);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] bArr, int i3, int i4) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i3, i4);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int i3, int i4) {
        return parseSpsNalUnitPayload(bArr, i3 + 1, i4);
    }

    public static SpsData parseSpsNalUnitPayload(byte[] bArr, int i3, int i4) {
        boolean z2;
        int i5;
        boolean z3;
        int i6;
        int i7;
        boolean z4;
        float f2;
        int i8;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i3, i4);
        int readBits = parsableNalUnitBitArray.readBits(8);
        int readBits2 = parsableNalUnitBitArray.readBits(8);
        int readBits3 = parsableNalUnitBitArray.readBits(8);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i9 = 1;
        if (readBits == 100 || readBits == 110 || readBits == 122 || readBits == 244 || readBits == 44 || readBits == 83 || readBits == 86 || readBits == 118 || readBits == 128 || readBits == 138) {
            i5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            z2 = i5 == 3 ? parsableNalUnitBitArray.readBit() : false;
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                int i10 = i5 != 3 ? 8 : 12;
                int i11 = 0;
                while (i11 < i10) {
                    if (parsableNalUnitBitArray.readBit()) {
                        skipScalingList(parsableNalUnitBitArray, i11 < 6 ? 16 : 64);
                    }
                    i11++;
                }
            }
        } else {
            i5 = 1;
            z2 = false;
        }
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        if (readUnsignedExpGolombCodedInt3 == 0) {
            i6 = i5;
            z3 = z2;
            i7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
            z4 = false;
        } else {
            if (readUnsignedExpGolombCodedInt3 == 1) {
                boolean readBit = parsableNalUnitBitArray.readBit();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                z3 = z2;
                long readUnsignedExpGolombCodedInt4 = (long) parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                i6 = i5;
                for (int i12 = 0; ((long) i12) < readUnsignedExpGolombCodedInt4; i12++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                z4 = readBit;
            } else {
                i6 = i5;
                z3 = z2;
                z4 = false;
            }
            i7 = 0;
        }
        int readUnsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        int readUnsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        boolean readBit2 = parsableNalUnitBitArray.readBit();
        int readUnsignedExpGolombCodedInt7 = (true - (readBit2 ? 1 : 0)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
        if (!readBit2) {
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBit();
        int i13 = readUnsignedExpGolombCodedInt6 * 16;
        int i14 = readUnsignedExpGolombCodedInt7 * 16;
        if (parsableNalUnitBitArray.readBit()) {
            int readUnsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (i6 == 0) {
                i8 = true - readBit2;
            } else {
                int i15 = i6;
                int i16 = i15 == 3 ? 1 : 2;
                if (i15 == 1) {
                    i9 = 2;
                }
                int i17 = (true - readBit2) * i9;
                i9 = i16;
                i8 = i17;
            }
            i13 -= (readUnsignedExpGolombCodedInt8 + readUnsignedExpGolombCodedInt9) * i9;
            i14 -= (readUnsignedExpGolombCodedInt10 + readUnsignedExpGolombCodedInt11) * i8;
        }
        int i18 = i13;
        int i19 = i14;
        float f3 = 1.0f;
        if (parsableNalUnitBitArray.readBit() && parsableNalUnitBitArray.readBit()) {
            int readBits4 = parsableNalUnitBitArray.readBits(8);
            if (readBits4 == 255) {
                int readBits5 = parsableNalUnitBitArray.readBits(16);
                int readBits6 = parsableNalUnitBitArray.readBits(16);
                if (!(readBits5 == 0 || readBits6 == 0)) {
                    f3 = ((float) readBits5) / ((float) readBits6);
                }
            } else {
                float[] fArr = ASPECT_RATIO_IDC_VALUES;
                if (readBits4 < fArr.length) {
                    f2 = fArr[readBits4];
                    return new SpsData(readBits, readBits2, readBits3, readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt5, i18, i19, f2, z3, readBit2, readUnsignedExpGolombCodedInt2, readUnsignedExpGolombCodedInt3, i7, z4);
                }
                r.a(readBits4, "Unexpected aspect_ratio_idc value: ", TAG);
            }
        }
        f2 = f3;
        return new SpsData(readBits, readBits2, readBits3, readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt5, i18, i19, f2, z3, readBit2, readUnsignedExpGolombCodedInt2, readUnsignedExpGolombCodedInt3, i7, z4);
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
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

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i3) {
        int i4 = 8;
        int i5 = 8;
        for (int i6 = 0; i6 < i3; i6++) {
            if (i4 != 0) {
                i4 = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i5) + 256) % 256;
            }
            if (i4 != 0) {
                i5 = i4;
            }
        }
    }

    private static void skipShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
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
                    if (!parsableNalUnitBitArray.readBit()) {
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

    public static int unescapeStream(byte[] bArr, int i3) {
        int i4;
        synchronized (scratchEscapePositionsLock) {
            int i5 = 0;
            int i6 = 0;
            while (i5 < i3) {
                try {
                    i5 = findNextUnescapeIndex(bArr, i5, i3);
                    if (i5 < i3) {
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i6) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                        }
                        scratchEscapePositions[i6] = i5;
                        i5 += 3;
                        i6++;
                    }
                } finally {
                }
            }
            i4 = i3 - i6;
            int i7 = 0;
            int i8 = 0;
            for (int i9 = 0; i9 < i6; i9++) {
                int i10 = scratchEscapePositions[i9] - i8;
                System.arraycopy(bArr, i8, bArr, i7, i10);
                int i11 = i7 + i10;
                int i12 = i11 + 1;
                bArr[i11] = 0;
                i7 = i11 + 2;
                bArr[i12] = 0;
                i8 += i10 + 3;
            }
            System.arraycopy(bArr, i8, bArr, i7, i4 - i7);
        }
        return i4;
    }
}
