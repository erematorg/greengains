package com.google.common.math;

import com.appsamurai.storyly.exoplayer2.core.analytics.AnalyticsListener;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
final class DoubleUtils {
    static final int EXPONENT_BIAS = 1023;
    static final long EXPONENT_MASK = 9218868437227405312L;
    static final long IMPLICIT_BIT = 4503599627370496L;
    @VisibleForTesting
    static final long ONE_BITS = 4607182418800017408L;
    static final int SIGNIFICAND_BITS = 52;
    static final long SIGNIFICAND_MASK = 4503599627370495L;
    static final long SIGN_MASK = Long.MIN_VALUE;

    private DoubleUtils() {
    }

    public static double bigToDouble(BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        int bitLength = abs.bitLength();
        int i3 = bitLength - 1;
        if (i3 < 63) {
            return (double) bigInteger.longValue();
        }
        if (i3 > 1023) {
            return ((double) bigInteger.signum()) * Double.POSITIVE_INFINITY;
        }
        int i4 = bitLength - 54;
        long longValue = abs.shiftRight(i4).longValue();
        long j2 = longValue >> 1;
        long j3 = SIGNIFICAND_MASK & j2;
        if ((longValue & 1) != 0 && ((j2 & 1) != 0 || abs.getLowestSetBit() < i4)) {
            j3++;
        }
        return Double.longBitsToDouble(((((long) (bitLength + AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED)) << 52) + j3) | (((long) bigInteger.signum()) & Long.MIN_VALUE));
    }

    public static double ensureNonNegative(double d2) {
        Preconditions.checkArgument(!Double.isNaN(d2));
        return Math.max(d2, 0.0d);
    }

    public static long getSignificand(double d2) {
        Preconditions.checkArgument(isFinite(d2), "not a normal value");
        int exponent = Math.getExponent(d2);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2) & SIGNIFICAND_MASK;
        return exponent == -1023 ? doubleToRawLongBits << 1 : doubleToRawLongBits | IMPLICIT_BIT;
    }

    public static boolean isFinite(double d2) {
        return Math.getExponent(d2) <= 1023;
    }

    public static boolean isNormal(double d2) {
        return Math.getExponent(d2) >= -1022;
    }

    public static double nextDown(double d2) {
        return -Math.nextUp(-d2);
    }

    public static double scaleNormalize(double d2) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d2) & SIGNIFICAND_MASK) | ONE_BITS);
    }
}
