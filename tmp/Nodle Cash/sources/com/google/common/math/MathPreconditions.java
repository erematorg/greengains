package com.google.common.math;

import A.a;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class MathPreconditions {
    private MathPreconditions() {
    }

    public static void checkInRangeForRoundingInputs(boolean z2, double d2, RoundingMode roundingMode) {
        if (!z2) {
            throw new ArithmeticException("rounded value is out of range for input " + d2 + " and rounding mode " + roundingMode);
        }
    }

    public static void checkNoOverflow(boolean z2, String str, int i3, int i4) {
        if (!z2) {
            throw new ArithmeticException(a.m(android.support.v4.media.session.a.x("overflow: ", str, "(", i3, ", "), ")", i4));
        }
    }

    @CanIgnoreReturnValue
    public static int checkNonNegative(String str, int i3) {
        if (i3 >= 0) {
            return i3;
        }
        throw new IllegalArgumentException(str + " (" + i3 + ") must be >= 0");
    }

    @CanIgnoreReturnValue
    public static int checkPositive(String str, int i3) {
        if (i3 > 0) {
            return i3;
        }
        throw new IllegalArgumentException(str + " (" + i3 + ") must be > 0");
    }

    public static void checkRoundingUnnecessary(boolean z2) {
        if (!z2) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    @CanIgnoreReturnValue
    public static long checkNonNegative(String str, long j2) {
        if (j2 >= 0) {
            return j2;
        }
        throw new IllegalArgumentException(str + " (" + j2 + ") must be >= 0");
    }

    @CanIgnoreReturnValue
    public static long checkPositive(String str, long j2) {
        if (j2 > 0) {
            return j2;
        }
        throw new IllegalArgumentException(str + " (" + j2 + ") must be > 0");
    }

    @CanIgnoreReturnValue
    public static BigInteger checkNonNegative(String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be >= 0");
    }

    @CanIgnoreReturnValue
    public static BigInteger checkPositive(String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be > 0");
    }

    @CanIgnoreReturnValue
    public static double checkNonNegative(String str, double d2) {
        if (d2 >= 0.0d) {
            return d2;
        }
        throw new IllegalArgumentException(str + " (" + d2 + ") must be >= 0");
    }

    public static void checkNoOverflow(boolean z2, String str, long j2, long j3) {
        if (!z2) {
            StringBuilder i3 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(j2, "overflow: ", str, "(");
            i3.append(", ");
            i3.append(j3);
            i3.append(")");
            throw new ArithmeticException(i3.toString());
        }
    }
}
