package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.2f;
    private static final float MAX_FINDER_PATTERN_RATIO = 0.89285713f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.45f;
    private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667f;
    private final int[] dataCharacterCounters;
    private final int[] decodeFinderCounters = new int[4];
    private final int[] evenCounts;
    private final float[] evenRoundingErrors;
    private final int[] oddCounts;
    private final float[] oddRoundingErrors;

    public AbstractRSSReader() {
        int[] iArr = new int[8];
        this.dataCharacterCounters = iArr;
        this.oddRoundingErrors = new float[4];
        this.evenRoundingErrors = new float[4];
        this.oddCounts = new int[(iArr.length / 2)];
        this.evenCounts = new int[(iArr.length / 2)];
    }

    @Deprecated
    public static int count(int[] iArr) {
        return MathUtils.sum(iArr);
    }

    public static void decrement(int[] iArr, float[] fArr) {
        int i3 = 0;
        float f2 = fArr[0];
        for (int i4 = 1; i4 < iArr.length; i4++) {
            float f3 = fArr[i4];
            if (f3 < f2) {
                i3 = i4;
                f2 = f3;
            }
        }
        iArr[i3] = iArr[i3] - 1;
    }

    public static void increment(int[] iArr, float[] fArr) {
        int i3 = 0;
        float f2 = fArr[0];
        for (int i4 = 1; i4 < iArr.length; i4++) {
            float f3 = fArr[i4];
            if (f3 > f2) {
                i3 = i4;
                f2 = f3;
            }
        }
        iArr[i3] = iArr[i3] + 1;
    }

    public static boolean isFinderPattern(int[] iArr) {
        int i3 = iArr[0] + iArr[1];
        float f2 = ((float) i3) / ((float) ((iArr[2] + i3) + iArr[3]));
        if (f2 < MIN_FINDER_PATTERN_RATIO || f2 > MAX_FINDER_PATTERN_RATIO) {
            return false;
        }
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        for (int i6 : iArr) {
            if (i6 > i5) {
                i5 = i6;
            }
            if (i6 < i4) {
                i4 = i6;
            }
        }
        return i5 < i4 * 10;
    }

    public static int parseFinderValue(int[] iArr, int[][] iArr2) throws NotFoundException {
        for (int i3 = 0; i3 < iArr2.length; i3++) {
            if (OneDReader.patternMatchVariance(iArr, iArr2[i3], MAX_INDIVIDUAL_VARIANCE) < 0.2f) {
                return i3;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final int[] getDataCharacterCounters() {
        return this.dataCharacterCounters;
    }

    public final int[] getDecodeFinderCounters() {
        return this.decodeFinderCounters;
    }

    public final int[] getEvenCounts() {
        return this.evenCounts;
    }

    public final float[] getEvenRoundingErrors() {
        return this.evenRoundingErrors;
    }

    public final int[] getOddCounts() {
        return this.oddCounts;
    }

    public final float[] getOddRoundingErrors() {
        return this.oddRoundingErrors;
    }
}
