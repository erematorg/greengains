package com.appsamurai.storyly.exoplayer2.common.util;

import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CodecSpecificDataUtil {
    private static final int EXTENDED_PAR = 15;
    private static final String[] HEVC_GENERAL_PROFILE_SPACE_STRINGS = {"", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C"};
    private static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static final int RECTANGULAR = 0;
    private static final int VISUAL_OBJECT_LAYER = 1;
    private static final int VISUAL_OBJECT_LAYER_START = 32;

    private CodecSpecificDataUtil() {
    }

    public static String buildAvcCodecString(int i3, int i4, int i5) {
        return String.format("avc1.%02X%02X%02X", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    public static List<byte[]> buildCea708InitializationData(boolean z2) {
        return Collections.singletonList(z2 ? new byte[]{1} : new byte[]{0});
    }

    public static String buildHevcCodecString(int i3, boolean z2, int i4, int i5, int[] iArr, int i6) {
        StringBuilder sb = new StringBuilder(Util.formatInvariant("hvc1.%s%d.%X.%c%d", HEVC_GENERAL_PROFILE_SPACE_STRINGS[i3], Integer.valueOf(i4), Integer.valueOf(i5), Character.valueOf(z2 ? 'H' : 'L'), Integer.valueOf(i6)));
        int length = iArr.length;
        while (length > 0 && iArr[length - 1] == 0) {
            length--;
        }
        for (int i7 = 0; i7 < length; i7++) {
            sb.append(String.format(".%02X", new Object[]{Integer.valueOf(iArr[i7])}));
        }
        return sb.toString();
    }

    public static byte[] buildNalUnit(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = NAL_START_CODE;
        byte[] bArr3 = new byte[(bArr2.length + i4)];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i3, bArr3, bArr2.length, i4);
        return bArr3;
    }

    private static int findNalStartCode(byte[] bArr, int i3) {
        int length = bArr.length - NAL_START_CODE.length;
        while (i3 <= length) {
            if (isNalStartCode(bArr, i3)) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public static Pair<Integer, Integer> getVideoResolutionFromMpeg4VideoConfig(byte[] bArr) {
        boolean z2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i4 + 3;
            if (i5 < bArr.length) {
                if (parsableByteArray.readUnsignedInt24() == 1 && (bArr[i5] & 240) == 32) {
                    z2 = true;
                    break;
                }
                parsableByteArray.setPosition(parsableByteArray.getPosition() - 2);
                i4++;
            } else {
                z2 = false;
                break;
            }
        }
        Assertions.checkArgument(z2, "Invalid input: VOL not found.");
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.skipBits((i4 + 4) * 8);
        parsableBitArray.skipBits(1);
        parsableBitArray.skipBits(8);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(4);
            parsableBitArray.skipBits(3);
        }
        if (parsableBitArray.readBits(4) == 15) {
            parsableBitArray.skipBits(8);
            parsableBitArray.skipBits(8);
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(2);
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(79);
            }
        }
        Assertions.checkArgument(parsableBitArray.readBits(2) == 0, "Only supports rectangular video object layer shape.");
        Assertions.checkArgument(parsableBitArray.readBit());
        int readBits = parsableBitArray.readBits(16);
        Assertions.checkArgument(parsableBitArray.readBit());
        if (parsableBitArray.readBit()) {
            Assertions.checkArgument(readBits > 0);
            for (int i6 = readBits - 1; i6 > 0; i6 >>= 1) {
                i3++;
            }
            parsableBitArray.skipBits(i3);
        }
        Assertions.checkArgument(parsableBitArray.readBit());
        int readBits2 = parsableBitArray.readBits(13);
        Assertions.checkArgument(parsableBitArray.readBit());
        int readBits3 = parsableBitArray.readBits(13);
        Assertions.checkArgument(parsableBitArray.readBit());
        parsableBitArray.skipBits(1);
        return Pair.create(Integer.valueOf(readBits2), Integer.valueOf(readBits3));
    }

    private static boolean isNalStartCode(byte[] bArr, int i3) {
        if (bArr.length - i3 <= NAL_START_CODE.length) {
            return false;
        }
        int i4 = 0;
        while (true) {
            byte[] bArr2 = NAL_START_CODE;
            if (i4 >= bArr2.length) {
                return true;
            }
            if (bArr[i3 + i4] != bArr2[i4]) {
                return false;
            }
            i4++;
        }
    }

    public static Pair<Integer, Integer> parseAlacAudioSpecificConfig(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.setPosition(9);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(20);
        return Pair.create(Integer.valueOf(parsableByteArray.readUnsignedIntToInt()), Integer.valueOf(readUnsignedByte));
    }

    public static boolean parseCea708InitializationData(List<byte[]> list) {
        return list.size() == 1 && list.get(0).length == 1 && list.get(0)[0] == 1;
    }

    @Nullable
    public static byte[][] splitNalUnits(byte[] bArr) {
        if (!isNalStartCode(bArr, 0)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        do {
            arrayList.add(Integer.valueOf(i3));
            i3 = findNalStartCode(bArr, i3 + NAL_START_CODE.length);
        } while (i3 != -1);
        byte[][] bArr2 = new byte[arrayList.size()][];
        int i4 = 0;
        while (i4 < arrayList.size()) {
            int intValue = ((Integer) arrayList.get(i4)).intValue();
            int intValue2 = (i4 < arrayList.size() + -1 ? ((Integer) arrayList.get(i4 + 1)).intValue() : bArr.length) - intValue;
            byte[] bArr3 = new byte[intValue2];
            System.arraycopy(bArr, intValue, bArr3, 0, intValue2);
            bArr2[i4] = bArr3;
            i4++;
        }
        return bArr2;
    }
}
