package com.appsamurai.storyly.exoplayer2.extractor.video;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.CodecSpecificDataUtil;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import java.util.Collections;
import java.util.List;

public final class HevcConfig {
    private static final int SPS_NAL_UNIT_TYPE = 33;
    @Nullable
    public final String codecs;
    public final int height;
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    private HevcConfig(List<byte[]> list, int i3, int i4, int i5, float f2, @Nullable String str) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i3;
        this.width = i4;
        this.height = i5;
        this.pixelWidthHeightRatio = f2;
        this.codecs = str;
    }

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        int i3;
        int i4;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        try {
            parsableByteArray2.skipBytes(21);
            int readUnsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < readUnsignedByte2; i7++) {
                parsableByteArray2.skipBytes(1);
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                for (int i8 = 0; i8 < readUnsignedShort; i8++) {
                    int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                    i6 += readUnsignedShort2 + 4;
                    parsableByteArray2.skipBytes(readUnsignedShort2);
                }
            }
            parsableByteArray2.setPosition(position);
            byte[] bArr = new byte[i6];
            int i9 = -1;
            int i10 = -1;
            float f2 = 1.0f;
            String str = null;
            int i11 = 0;
            int i12 = 0;
            while (i11 < readUnsignedByte2) {
                int readUnsignedByte3 = parsableByteArray.readUnsignedByte() & 127;
                int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                int i13 = i5;
                while (i13 < readUnsignedShort3) {
                    int readUnsignedShort4 = parsableByteArray.readUnsignedShort();
                    byte[] bArr2 = NalUnitUtil.NAL_START_CODE;
                    int i14 = readUnsignedByte2;
                    System.arraycopy(bArr2, i5, bArr, i12, bArr2.length);
                    int length = i12 + bArr2.length;
                    System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), bArr, length, readUnsignedShort4);
                    if (readUnsignedByte3 == 33 && i13 == 0) {
                        NalUnitUtil.H265SpsData parseH265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, length, length + readUnsignedShort4);
                        int i15 = parseH265SpsNalUnit.width;
                        i10 = parseH265SpsNalUnit.height;
                        f2 = parseH265SpsNalUnit.pixelWidthHeightRatio;
                        i4 = readUnsignedByte3;
                        i3 = readUnsignedShort3;
                        i9 = i15;
                        str = CodecSpecificDataUtil.buildHevcCodecString(parseH265SpsNalUnit.generalProfileSpace, parseH265SpsNalUnit.generalTierFlag, parseH265SpsNalUnit.generalProfileIdc, parseH265SpsNalUnit.generalProfileCompatibilityFlags, parseH265SpsNalUnit.constraintBytes, parseH265SpsNalUnit.generalLevelIdc);
                    } else {
                        i4 = readUnsignedByte3;
                        i3 = readUnsignedShort3;
                    }
                    i12 = length + readUnsignedShort4;
                    parsableByteArray2.skipBytes(readUnsignedShort4);
                    i13++;
                    readUnsignedByte2 = i14;
                    readUnsignedByte3 = i4;
                    readUnsignedShort3 = i3;
                    i5 = 0;
                }
                int i16 = readUnsignedByte2;
                i11++;
                i5 = 0;
            }
            return new HevcConfig(i6 == 0 ? Collections.emptyList() : Collections.singletonList(bArr), readUnsignedByte + 1, i9, i10, f2, str);
        } catch (ArrayIndexOutOfBoundsException e3) {
            throw ParserException.createForMalformedContainer("Error parsing HEVC config", e3);
        }
    }
}
