package com.appsamurai.storyly.exoplayer2.extractor.extractor.ts;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;

public final class TsUtil {
    private TsUtil() {
    }

    public static int findSyncBytePosition(byte[] bArr, int i3, int i4) {
        while (i3 < i4 && bArr[i3] != 71) {
            i3++;
        }
        return i3;
    }

    public static boolean isStartOfTsPacket(byte[] bArr, int i3, int i4, int i5) {
        int i6 = 0;
        for (int i7 = -4; i7 <= 4; i7++) {
            int i8 = (i7 * 188) + i5;
            if (i8 < i3 || i8 >= i4 || bArr[i8] != 71) {
                i6 = 0;
            } else {
                i6++;
                if (i6 == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public static long readPcrFromPacket(ParsableByteArray parsableByteArray, int i3, int i4) {
        parsableByteArray.setPosition(i3);
        if (parsableByteArray.bytesLeft() < 5) {
            return C.TIME_UNSET;
        }
        int readInt = parsableByteArray.readInt();
        if ((8388608 & readInt) != 0 || ((2096896 & readInt) >> 8) != i4 || (readInt & 32) == 0 || parsableByteArray.readUnsignedByte() < 7 || parsableByteArray.bytesLeft() < 7 || (parsableByteArray.readUnsignedByte() & 16) != 16) {
            return C.TIME_UNSET;
        }
        byte[] bArr = new byte[6];
        parsableByteArray.readBytes(bArr, 0, 6);
        return readPcrValueFromPcrBytes(bArr);
    }

    private static long readPcrValueFromPcrBytes(byte[] bArr) {
        return ((((long) bArr[0]) & 255) << 25) | ((((long) bArr[1]) & 255) << 17) | ((((long) bArr[2]) & 255) << 9) | ((((long) bArr[3]) & 255) << 1) | ((255 & ((long) bArr[4])) >> 7);
    }
}
