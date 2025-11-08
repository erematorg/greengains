package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN8Reader extends UPCEANReader {
    private final int[] decodeMiddleCounters = new int[4];

    public int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i3 = iArr[1];
        for (int i4 = 0; i4 < 4 && i3 < size; i4++) {
            sb.append((char) (UPCEANReader.decodeDigit(bitArray, iArr2, i3, UPCEANReader.L_PATTERNS) + 48));
            for (int i5 : iArr2) {
                i3 += i5;
            }
        }
        int i6 = UPCEANReader.findGuardPattern(bitArray, i3, true, UPCEANReader.MIDDLE_PATTERN)[1];
        for (int i7 = 0; i7 < 4 && i6 < size; i7++) {
            sb.append((char) (UPCEANReader.decodeDigit(bitArray, iArr2, i6, UPCEANReader.L_PATTERNS) + 48));
            for (int i8 : iArr2) {
                i6 += i8;
            }
        }
        return i6;
    }

    public BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_8;
    }
}
