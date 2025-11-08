package com.google.zxing.pdf417.decoder;

import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

final class PDF417CodewordDecoder {
    private static final float[][] RATIOS_TABLE;

    static {
        int i3;
        int length = PDF417Common.SYMBOL_TABLE.length;
        int[] iArr = new int[2];
        iArr[1] = 8;
        iArr[0] = length;
        RATIOS_TABLE = (float[][]) Array.newInstance(Float.TYPE, iArr);
        int i4 = 0;
        while (true) {
            int[] iArr2 = PDF417Common.SYMBOL_TABLE;
            if (i4 < iArr2.length) {
                int i5 = iArr2[i4];
                int i6 = i5 & 1;
                int i7 = 0;
                while (i7 < 8) {
                    float f2 = 0.0f;
                    while (true) {
                        i3 = i5 & 1;
                        if (i3 != i6) {
                            break;
                        }
                        f2 += 1.0f;
                        i5 >>= 1;
                    }
                    RATIOS_TABLE[i4][7 - i7] = f2 / 17.0f;
                    i7++;
                    i6 = i3;
                }
                i4++;
            } else {
                return;
            }
        }
    }

    private PDF417CodewordDecoder() {
    }

    private static int getBitValue(int[] iArr) {
        long j2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            for (int i4 = 0; i4 < iArr[i3]; i4++) {
                int i5 = 1;
                long j3 = j2 << 1;
                if (i3 % 2 != 0) {
                    i5 = 0;
                }
                j2 = j3 | ((long) i5);
            }
        }
        return (int) j2;
    }

    private static int getClosestDecodedValue(int[] iArr) {
        int sum = MathUtils.sum(iArr);
        float[] fArr = new float[8];
        if (sum > 1) {
            for (int i3 = 0; i3 < 8; i3++) {
                fArr[i3] = ((float) iArr[i3]) / ((float) sum);
            }
        }
        float f2 = Float.MAX_VALUE;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            float[][] fArr2 = RATIOS_TABLE;
            if (i5 >= fArr2.length) {
                return i4;
            }
            float[] fArr3 = fArr2[i5];
            float f3 = 0.0f;
            for (int i6 = 0; i6 < 8; i6++) {
                float f4 = fArr3[i6] - fArr[i6];
                f3 += f4 * f4;
                if (f3 >= f2) {
                    break;
                }
            }
            if (f3 < f2) {
                i4 = PDF417Common.SYMBOL_TABLE[i5];
                f2 = f3;
            }
            i5++;
        }
    }

    private static int getDecodedCodewordValue(int[] iArr) {
        int bitValue = getBitValue(iArr);
        if (PDF417Common.getCodeword(bitValue) == -1) {
            return -1;
        }
        return bitValue;
    }

    public static int getDecodedValue(int[] iArr) {
        int decodedCodewordValue = getDecodedCodewordValue(sampleBitCounts(iArr));
        return decodedCodewordValue != -1 ? decodedCodewordValue : getClosestDecodedValue(iArr);
    }

    private static int[] sampleBitCounts(int[] iArr) {
        float sum = (float) MathUtils.sum(iArr);
        int[] iArr2 = new int[8];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < 17; i5++) {
            float f2 = ((((float) i5) * sum) / 17.0f) + (sum / 34.0f);
            int i6 = iArr[i4];
            if (((float) (i3 + i6)) <= f2) {
                i3 += i6;
                i4++;
            }
            iArr2[i4] = iArr2[i4] + 1;
        }
        return iArr2;
    }
}
