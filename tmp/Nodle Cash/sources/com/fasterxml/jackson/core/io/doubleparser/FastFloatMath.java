package com.fasterxml.jackson.core.io.doubleparser;

import com.fasterxml.jackson.core.io.doubleparser.FastDoubleMath;

class FastFloatMath {
    private static final int FLOAT_EXPONENT_BIAS = 127;
    private static final int FLOAT_MAX_EXPONENT_POWER_OF_TEN = 38;
    private static final int FLOAT_MAX_EXPONENT_POWER_OF_TWO = 127;
    private static final int FLOAT_MIN_EXPONENT_POWER_OF_TEN = -45;
    private static final int FLOAT_MIN_EXPONENT_POWER_OF_TWO = -126;
    private static final float[] FLOAT_POWER_OF_TEN = {1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f};
    private static final int FLOAT_SIGNIFICAND_WIDTH = 24;

    private FastFloatMath() {
    }

    public static float decFloatLiteralToFloat(boolean z2, long j2, int i3, boolean z3, int i4) {
        if (j2 == 0) {
            return z2 ? -0.0f : 0.0f;
        }
        if (z3) {
            if (FLOAT_MIN_EXPONENT_POWER_OF_TEN > i4 || i4 > 38) {
                return Float.NaN;
            }
            float tryDecToFloatWithFastAlgorithm = tryDecToFloatWithFastAlgorithm(z2, j2, i4);
            float tryDecToFloatWithFastAlgorithm2 = tryDecToFloatWithFastAlgorithm(z2, j2 + 1, i4);
            if (Float.isNaN(tryDecToFloatWithFastAlgorithm) || tryDecToFloatWithFastAlgorithm2 != tryDecToFloatWithFastAlgorithm) {
                return Float.NaN;
            }
            return tryDecToFloatWithFastAlgorithm;
        } else if (FLOAT_MIN_EXPONENT_POWER_OF_TEN > i3 || i3 > 38) {
            return Float.NaN;
        } else {
            return tryDecToFloatWithFastAlgorithm(z2, j2, i3);
        }
    }

    public static float hexFloatLiteralToFloat(boolean z2, long j2, int i3, boolean z3, int i4) {
        if (j2 == 0) {
            return z2 ? -0.0f : 0.0f;
        }
        if (z3) {
            if (FLOAT_MIN_EXPONENT_POWER_OF_TWO > i4 || i4 > 127) {
                return Float.NaN;
            }
            float tryHexToFloatWithFastAlgorithm = tryHexToFloatWithFastAlgorithm(z2, j2, i4);
            float tryHexToFloatWithFastAlgorithm2 = tryHexToFloatWithFastAlgorithm(z2, j2 + 1, i4);
            if (Double.isNaN((double) tryHexToFloatWithFastAlgorithm) || tryHexToFloatWithFastAlgorithm2 != tryHexToFloatWithFastAlgorithm) {
                return Float.NaN;
            }
            return tryHexToFloatWithFastAlgorithm;
        } else if (FLOAT_MIN_EXPONENT_POWER_OF_TWO > i3 || i3 > 127) {
            return Float.NaN;
        } else {
            return tryHexToFloatWithFastAlgorithm(z2, j2, i3);
        }
    }

    public static float tryDecToFloatWithFastAlgorithm(boolean z2, long j2, int i3) {
        long j3 = j2;
        int i4 = i3;
        if (-10 > i4 || i4 > 10 || Long.compareUnsigned(j3, 16777215) > 0) {
            int i5 = i4 + 325;
            long j4 = FastDoubleMath.MANTISSA_64[i5];
            long j5 = ((((long) i4) * 217706) >> 16) + 191;
            int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2);
            long j6 = j3 << numberOfLeadingZeros;
            FastDoubleMath.UInt128 fullMultiplication = FastDoubleMath.fullMultiplication(j6, j4);
            long j7 = fullMultiplication.low;
            long j8 = fullMultiplication.high;
            if ((j8 & 274877906943L) == 274877906943L && Long.compareUnsigned(j7 + j6, j7) < 0) {
                FastDoubleMath.UInt128 fullMultiplication2 = FastDoubleMath.fullMultiplication(j6, FastDoubleMath.MANTISSA_128[i5]);
                long j9 = fullMultiplication2.low;
                long j10 = fullMultiplication2.high + j7;
                if (Long.compareUnsigned(j10, j7) < 0) {
                    j8++;
                }
                if (j10 + 1 == 0 && (j8 & 549755813887L) == 549755813887L && j9 + ((long) Long.compareUnsigned(j6, j9)) < 0) {
                    return Float.NaN;
                }
            }
            long j11 = j8 >>> 63;
            long j12 = j8 >>> ((int) (38 + j11));
            int i6 = numberOfLeadingZeros + ((int) (j11 ^ 1));
            long j13 = j8 & 274877906943L;
            if (j13 == 274877906943L || (j13 == 0 && (3 & j12) == 1)) {
                return Float.NaN;
            }
            long j14 = (j12 + 1) >>> 1;
            if (j14 >= 16777216) {
                i6--;
                j14 = 8388608;
            }
            long j15 = j14 & -8388609;
            long j16 = j5 - ((long) i6);
            if (j16 < 1 || j16 > 254) {
                return Float.NaN;
            }
            return Float.intBitsToFloat((int) ((j16 << 23) | j15 | (z2 ? 2147483648L : 0)));
        }
        float f2 = (float) j3;
        float f3 = i4 < 0 ? f2 / FLOAT_POWER_OF_TEN[-i4] : f2 * FLOAT_POWER_OF_TEN[i4];
        return z2 ? -f3 : f3;
    }

    public static float tryHexToFloatWithFastAlgorithm(boolean z2, long j2, int i3) {
        if (j2 == 0 || i3 < -180) {
            return z2 ? -0.0f : 0.0f;
        }
        if (i3 > 127) {
            return z2 ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        }
        if (Long.compareUnsigned(j2, 9007199254740991L) > 0) {
            return Float.NaN;
        }
        float scalb = Math.scalb(1.0f, i3) * ((float) j2);
        return z2 ? -scalb : scalb;
    }
}
