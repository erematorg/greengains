package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader extends UPCEANReader {
    static final int[] FIRST_DIGIT_ENCODINGS = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private final int[] decodeMiddleCounters = new int[4];

    private static void determineFirstDigit(StringBuilder sb, int i3) throws NotFoundException {
        for (int i4 = 0; i4 < 10; i4++) {
            if (i3 == FIRST_DIGIT_ENCODINGS[i4]) {
                sb.insert(0, (char) (i4 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i3 = iArr[1];
        int i4 = 0;
        for (int i5 = 0; i5 < 6 && i3 < size; i5++) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i3, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (int i6 : iArr2) {
                i3 += i6;
            }
            if (decodeDigit >= 10) {
                i4 |= 1 << (5 - i5);
            }
        }
        determineFirstDigit(sb, i4);
        int i7 = UPCEANReader.findGuardPattern(bitArray, i3, true, UPCEANReader.MIDDLE_PATTERN)[1];
        for (int i8 = 0; i8 < 6 && i7 < size; i8++) {
            sb.append((char) (UPCEANReader.decodeDigit(bitArray, iArr2, i7, UPCEANReader.L_PATTERNS) + 48));
            for (int i9 : iArr2) {
                i7 += i9;
            }
        }
        return i7;
    }

    public BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_13;
    }
}
