package com.google.zxing.qrcode.encoder;

import A.a;

final class MaskUtil {
    private static final int N1 = 3;
    private static final int N2 = 3;
    private static final int N3 = 40;
    private static final int N4 = 10;

    private MaskUtil() {
    }

    public static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z2) {
        int height = z2 ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z2 ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            byte b3 = -1;
            int i5 = 0;
            for (int i6 = 0; i6 < width; i6++) {
                byte b4 = z2 ? array[i4][i6] : array[i6][i4];
                if (b4 == b3) {
                    i5++;
                } else {
                    if (i5 >= 5) {
                        i3 += i5 - 2;
                    }
                    i5 = 1;
                    b3 = b4;
                }
            }
            if (i5 >= 5) {
                i3 = (i5 - 2) + i3;
            }
        }
        return i3;
    }

    public static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i3 = 0;
        for (int i4 = 0; i4 < height - 1; i4++) {
            byte[] bArr = array[i4];
            int i5 = 0;
            while (i5 < width - 1) {
                byte b3 = bArr[i5];
                int i6 = i5 + 1;
                if (b3 == bArr[i6]) {
                    byte[] bArr2 = array[i4 + 1];
                    if (b3 == bArr2[i5] && b3 == bArr2[i6]) {
                        i3++;
                    }
                }
                i5 = i6;
            }
        }
        return i3 * 3;
    }

    public static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            for (int i5 = 0; i5 < width; i5++) {
                byte[] bArr = array[i4];
                int i6 = i5 + 6;
                if (i6 < width && bArr[i5] == 1 && bArr[i5 + 1] == 0 && bArr[i5 + 2] == 1 && bArr[i5 + 3] == 1 && bArr[i5 + 4] == 1 && bArr[i5 + 5] == 0 && bArr[i6] == 1 && (isWhiteHorizontal(bArr, i5 - 4, i5) || isWhiteHorizontal(bArr, i5 + 7, i5 + 11))) {
                    i3++;
                }
                int i7 = i4 + 6;
                if (i7 < height && array[i4][i5] == 1 && array[i4 + 1][i5] == 0 && array[i4 + 2][i5] == 1 && array[i4 + 3][i5] == 1 && array[i4 + 4][i5] == 1 && array[i4 + 5][i5] == 0 && array[i7][i5] == 1 && (isWhiteVertical(array, i5, i4 - 4, i4) || isWhiteVertical(array, i5, i4 + 7, i4 + 11))) {
                    i3++;
                }
            }
        }
        return i3 * 40;
    }

    public static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            byte[] bArr = array[i4];
            for (int i5 = 0; i5 < width; i5++) {
                if (bArr[i5] == 1) {
                    i3++;
                }
            }
        }
        int width2 = byteMatrix.getWidth() * byteMatrix.getHeight();
        return ((Math.abs((i3 * 2) - width2) * 10) / width2) * 10;
    }

    public static boolean getDataMaskBit(int i3, int i4, int i5) {
        int i6;
        switch (i3) {
            case 0:
                i5 += i4;
                break;
            case 1:
                break;
            case 2:
                i6 = i4 % 3;
                break;
            case 3:
                i6 = (i5 + i4) % 3;
                break;
            case 4:
                i6 = ((i4 / 3) + (i5 / 2)) & 1;
                break;
            case 5:
                int i7 = i5 * i4;
                i6 = (i7 & 1) + (i7 % 3);
                break;
            case 6:
                int i8 = i5 * i4;
                i5 = (i8 % 3) + (i8 & 1);
                break;
            case 7:
                i6 = (((i5 * i4) % 3) + ((i5 + i4) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException(a.k("Invalid mask pattern: ", i3));
        }
        i6 = i5 & 1;
        return i6 == 0;
    }

    private static boolean isWhiteHorizontal(byte[] bArr, int i3, int i4) {
        if (i3 < 0 || bArr.length < i4) {
            return false;
        }
        while (i3 < i4) {
            if (bArr[i3] == 1) {
                return false;
            }
            i3++;
        }
        return true;
    }

    private static boolean isWhiteVertical(byte[][] bArr, int i3, int i4, int i5) {
        if (i4 < 0 || bArr.length < i5) {
            return false;
        }
        while (i4 < i5) {
            if (bArr[i4][i3] == 1) {
                return false;
            }
            i4++;
        }
        return true;
    }
}
