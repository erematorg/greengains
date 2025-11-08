package com.appsamurai.storyly.exoplayer2.extractor.video;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.CodecSpecificDataUtil;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import java.util.ArrayList;
import java.util.List;

public final class AvcConfig {
    @Nullable
    public final String codecs;
    public final int height;
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    private AvcConfig(List<byte[]> list, int i3, int i4, int i5, float f2, @Nullable String str) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i3;
        this.width = i4;
        this.height = i5;
        this.pixelWidthHeightRatio = f2;
        this.codecs = str;
    }

    private static byte[] buildNalUnitForChild(ParsableByteArray parsableByteArray) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(readUnsignedShort);
        return CodecSpecificDataUtil.buildNalUnit(parsableByteArray.getData(), position, readUnsignedShort);
    }

    public static AvcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        String str;
        float f2;
        int i3;
        int i4;
        try {
            parsableByteArray.skipBytes(4);
            int readUnsignedByte = (parsableByteArray.readUnsignedByte() & 3) + 1;
            if (readUnsignedByte != 3) {
                ArrayList arrayList = new ArrayList();
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
                for (int i5 = 0; i5 < readUnsignedByte2; i5++) {
                    arrayList.add(buildNalUnitForChild(parsableByteArray));
                }
                int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                for (int i6 = 0; i6 < readUnsignedByte3; i6++) {
                    arrayList.add(buildNalUnitForChild(parsableByteArray));
                }
                if (readUnsignedByte2 > 0) {
                    NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit((byte[]) arrayList.get(0), readUnsignedByte, ((byte[]) arrayList.get(0)).length);
                    int i7 = parseSpsNalUnit.width;
                    int i8 = parseSpsNalUnit.height;
                    float f3 = parseSpsNalUnit.pixelWidthHeightRatio;
                    str = CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc);
                    i4 = i7;
                    i3 = i8;
                    f2 = f3;
                } else {
                    i4 = -1;
                    i3 = -1;
                    f2 = 1.0f;
                    str = null;
                }
                return new AvcConfig(arrayList, readUnsignedByte, i4, i3, f2, str);
            }
            throw new IllegalStateException();
        } catch (ArrayIndexOutOfBoundsException e3) {
            throw ParserException.createForMalformedContainer("Error parsing AVC config", e3);
        }
    }
}
