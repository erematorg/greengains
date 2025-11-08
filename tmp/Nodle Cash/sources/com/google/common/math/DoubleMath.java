package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class DoubleMath {
    private static final double LN_2 = Math.log(2.0d);
    @VisibleForTesting
    static final int MAX_FACTORIAL = 170;
    private static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    private static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    @VisibleForTesting
    static final double[] everySixteenthFactorial = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    /* renamed from: com.google.common.math.DoubleMath$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                java.math.RoundingMode[] r0 = java.math.RoundingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$math$RoundingMode = r0
                java.math.RoundingMode r1 = java.math.RoundingMode.UNNECESSARY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x001d }
                java.math.RoundingMode r1 = java.math.RoundingMode.FLOOR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                java.math.RoundingMode r1 = java.math.RoundingMode.CEILING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                java.math.RoundingMode r1 = java.math.RoundingMode.DOWN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x003e }
                java.math.RoundingMode r1 = java.math.RoundingMode.UP     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_EVEN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_UP     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0060 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_DOWN     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.DoubleMath.AnonymousClass1.<clinit>():void");
        }
    }

    private DoubleMath() {
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @J2ktIncompatible
    private static double checkFinite(double d2) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2));
        return d2;
    }

    public static double factorial(int i3) {
        MathPreconditions.checkNonNegative("n", i3);
        if (i3 > 170) {
            return Double.POSITIVE_INFINITY;
        }
        double d2 = 1.0d;
        for (int i4 = (i3 & -16) + 1; i4 <= i3; i4++) {
            d2 *= (double) i4;
        }
        return d2 * everySixteenthFactorial[i3 >> 4];
    }

    public static int fuzzyCompare(double d2, double d3, double d4) {
        if (fuzzyEquals(d2, d3, d4)) {
            return 0;
        }
        if (d2 < d3) {
            return -1;
        }
        if (d2 > d3) {
            return 1;
        }
        return Booleans.compare(Double.isNaN(d2), Double.isNaN(d3));
    }

    public static boolean fuzzyEquals(double d2, double d3, double d4) {
        MathPreconditions.checkNonNegative("tolerance", d4);
        return Math.copySign(d2 - d3, 1.0d) <= d4 || d2 == d3 || (Double.isNaN(d2) && Double.isNaN(d3));
    }

    @GwtIncompatible
    public static boolean isMathematicalInteger(double d2) {
        return DoubleUtils.isFinite(d2) && (d2 == 0.0d || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(d2)) <= Math.getExponent(d2));
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean isPowerOfTwo(double d2) {
        if (d2 <= 0.0d || !DoubleUtils.isFinite(d2)) {
            return false;
        }
        long significand = DoubleUtils.getSignificand(d2);
        return (significand & (significand - 1)) == 0;
    }

    public static double log2(double d2) {
        return Math.log(d2) / LN_2;
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static double mean(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0, "Cannot take mean of 0 values");
        double checkFinite = checkFinite(dArr[0]);
        long j2 = 1;
        for (int i3 = 1; i3 < dArr.length; i3++) {
            checkFinite(dArr[i3]);
            j2++;
            checkFinite += (dArr[i3] - checkFinite) / ((double) j2);
        }
        return checkFinite;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static double roundIntermediate(double d2, RoundingMode roundingMode) {
        if (DoubleUtils.isFinite(d2)) {
            switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
                case 1:
                    MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(d2));
                    return d2;
                case 2:
                    return (d2 >= 0.0d || isMathematicalInteger(d2)) ? d2 : (double) (((long) d2) - 1);
                case 3:
                    return (d2 <= 0.0d || isMathematicalInteger(d2)) ? d2 : (double) (((long) d2) + 1);
                case 4:
                    return d2;
                case 5:
                    if (isMathematicalInteger(d2)) {
                        return d2;
                    }
                    return (double) (((long) d2) + ((long) (d2 > 0.0d ? 1 : -1)));
                case 6:
                    return Math.rint(d2);
                case 7:
                    double rint = Math.rint(d2);
                    return Math.abs(d2 - rint) == 0.5d ? Math.copySign(0.5d, d2) + d2 : rint;
                case 8:
                    double rint2 = Math.rint(d2);
                    return Math.abs(d2 - rint2) == 0.5d ? d2 : rint2;
                default:
                    throw new AssertionError();
            }
        } else {
            throw new ArithmeticException("input is infinite or NaN");
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static BigInteger roundToBigInteger(double d2, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d2, roundingMode);
        boolean z2 = false;
        boolean z3 = MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d;
        if (roundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE) {
            z2 = true;
        }
        if (z3 && z2) {
            return BigInteger.valueOf((long) roundIntermediate);
        }
        BigInteger shiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(roundIntermediate)).shiftLeft(Math.getExponent(roundIntermediate) - 52);
        return roundIntermediate < 0.0d ? shiftLeft.negate() : shiftLeft;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static int roundToInt(double d2, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d2, roundingMode);
        boolean z2 = false;
        boolean z3 = roundIntermediate > -2.147483649E9d;
        if (roundIntermediate < 2.147483648E9d) {
            z2 = true;
        }
        MathPreconditions.checkInRangeForRoundingInputs(z3 & z2, d2, roundingMode);
        return (int) roundIntermediate;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long roundToLong(double d2, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d2, roundingMode);
        boolean z2 = false;
        boolean z3 = MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d;
        if (roundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE) {
            z2 = true;
        }
        MathPreconditions.checkInRangeForRoundingInputs(z3 & z2, d2, roundingMode);
        return (long) roundIntermediate;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static int log2(double d2, RoundingMode roundingMode) {
        boolean isPowerOfTwo;
        boolean z2 = false;
        Preconditions.checkArgument(d2 > 0.0d && DoubleUtils.isFinite(d2), "x must be positive and finite");
        int exponent = Math.getExponent(d2);
        if (!DoubleUtils.isNormal(d2)) {
            return log2(d2 * 4.503599627370496E15d, roundingMode) - 52;
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(d2));
                break;
            case 2:
                break;
            case 3:
                z2 = !isPowerOfTwo(d2);
                break;
            case 4:
                if (exponent < 0) {
                    z2 = true;
                }
                isPowerOfTwo = isPowerOfTwo(d2);
                break;
            case 5:
                if (exponent >= 0) {
                    z2 = true;
                }
                isPowerOfTwo = isPowerOfTwo(d2);
                break;
            case 6:
            case 7:
            case 8:
                double scaleNormalize = DoubleUtils.scaleNormalize(d2);
                if (scaleNormalize * scaleNormalize > 2.0d) {
                    z2 = true;
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        z2 &= !isPowerOfTwo;
        return z2 ? exponent + 1 : exponent;
    }

    @Deprecated
    public static double mean(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0, "Cannot take mean of 0 values");
        long j2 = 0;
        for (int i3 : iArr) {
            j2 += (long) i3;
        }
        return ((double) j2) / ((double) iArr.length);
    }

    @Deprecated
    public static double mean(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0, "Cannot take mean of 0 values");
        double d2 = (double) jArr[0];
        long j2 = 1;
        for (int i3 = 1; i3 < jArr.length; i3++) {
            j2++;
            d2 += (((double) jArr[i3]) - d2) / ((double) j2);
        }
        return d2;
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static double mean(Iterable<? extends Number> iterable) {
        return mean(iterable.iterator());
    }

    @GwtIncompatible
    @Deprecated
    @J2ktIncompatible
    public static double mean(Iterator<? extends Number> it) {
        Preconditions.checkArgument(it.hasNext(), "Cannot take mean of 0 values");
        double checkFinite = checkFinite(((Number) it.next()).doubleValue());
        long j2 = 1;
        while (it.hasNext()) {
            j2++;
            checkFinite += (checkFinite(((Number) it.next()).doubleValue()) - checkFinite) / ((double) j2);
        }
        return checkFinite;
    }
}
