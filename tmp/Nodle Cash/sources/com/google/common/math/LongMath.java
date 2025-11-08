package com.google.common.math;

import androidx.compose.ui.autofill.a;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedLongs;
import java.math.RoundingMode;
import jnr.ffi.provider.jffi.JNINativeInterface;
import okhttp3.internal.connection.RealConnection;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class LongMath {
    @VisibleForTesting
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    @VisibleForTesting
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    @VisibleForTesting
    static final long MAX_SIGNED_POWER_OF_TWO = 4611686018427387904L;
    private static final int SIEVE_30 = -545925251;
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, JNINativeInterface.SetDoubleArrayRegion, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    @GwtIncompatible
    @J2ktIncompatible
    @VisibleForTesting
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, 16, 16, 16, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.CR, Ascii.CR, Ascii.CR, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.VT, Ascii.VT, Ascii.VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};
    @GwtIncompatible
    @J2ktIncompatible
    @VisibleForTesting
    static final long[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, C.NANOS_PER_SECOND, RealConnection.IDLE_CONNECTION_HEALTHY_NS, 100000000000L, MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    /* renamed from: com.google.common.math.LongMath$1  reason: invalid class name */
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
                java.math.RoundingMode r1 = java.math.RoundingMode.DOWN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                java.math.RoundingMode r1 = java.math.RoundingMode.FLOOR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                java.math.RoundingMode r1 = java.math.RoundingMode.UP     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x003e }
                java.math.RoundingMode r1 = java.math.RoundingMode.CEILING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$java$math$RoundingMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_DOWN     // Catch:{ NoSuchFieldError -> 0x0049 }
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
                java.math.RoundingMode r1 = java.math.RoundingMode.HALF_EVEN     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.AnonymousClass1.<clinit>():void");
        }
    }

    public enum MillerRabinTester {
        SMALL {
            public long mulMod(long j2, long j3, long j4) {
                return (j2 * j3) % j4;
            }

            public long squareMod(long j2, long j3) {
                return (j2 * j2) % j3;
            }
        },
        LARGE {
            private long plusMod(long j2, long j3, long j4) {
                int i3 = (j2 > (j4 - j3) ? 1 : (j2 == (j4 - j3) ? 0 : -1));
                long j5 = j2 + j3;
                return i3 >= 0 ? j5 - j4 : j5;
            }

            private long times2ToThe32Mod(long j2, long j3) {
                int i3 = 32;
                do {
                    int min = Math.min(i3, Long.numberOfLeadingZeros(j2));
                    j2 = UnsignedLongs.remainder(j2 << min, j3);
                    i3 -= min;
                } while (i3 > 0);
                return j2;
            }

            public long mulMod(long j2, long j3, long j4) {
                long j5 = j4;
                long j6 = j2 >>> 32;
                long j7 = j3 >>> 32;
                long j8 = j2 & 4294967295L;
                long j9 = j3 & 4294967295L;
                long times2ToThe32Mod = (j6 * j9) + times2ToThe32Mod(j6 * j7, j5);
                if (times2ToThe32Mod < 0) {
                    times2ToThe32Mod = UnsignedLongs.remainder(times2ToThe32Mod, j5);
                }
                return plusMod(times2ToThe32Mod((j7 * j8) + times2ToThe32Mod, j5), UnsignedLongs.remainder(j8 * j9, j5), j4);
            }

            public long squareMod(long j2, long j3) {
                long j4 = j2 >>> 32;
                long j5 = j2 & 4294967295L;
                long times2ToThe32Mod = times2ToThe32Mod(j4 * j4, j3);
                long j6 = j4 * j5 * 2;
                if (j6 < 0) {
                    j6 = UnsignedLongs.remainder(j6, j3);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + j6, j3), UnsignedLongs.remainder(j5 * j5, j3), j3);
            }
        };

        private long powMod(long j2, long j3, long j4) {
            long j5 = 1;
            while (j3 != 0) {
                if ((j3 & 1) != 0) {
                    j5 = mulMod(j5, j2, j4);
                }
                j2 = squareMod(j2, j4);
                j3 >>= 1;
            }
            return j5;
        }

        public static boolean test(long j2, long j3) {
            return (j3 <= LongMath.FLOOR_SQRT_MAX_LONG ? SMALL : LARGE).testWitness(j2, j3);
        }

        private boolean testWitness(long j2, long j3) {
            long j4 = j3;
            long j5 = j4 - 1;
            int numberOfTrailingZeros = Long.numberOfTrailingZeros(j5);
            long j6 = j5 >> numberOfTrailingZeros;
            long j7 = j2 % j4;
            if (j7 == 0) {
                return true;
            }
            long powMod = powMod(j7, j6, j3);
            if (powMod == 1) {
                return true;
            }
            int i3 = 0;
            while (powMod != j5) {
                i3++;
                if (i3 == numberOfTrailingZeros) {
                    return false;
                }
                powMod = squareMod(powMod, j4);
            }
            return true;
        }

        public abstract long mulMod(long j2, long j3, long j4);

        public abstract long squareMod(long j2, long j3);
    }

    private LongMath() {
    }

    public static long binomial(int i3, int i4) {
        MathPreconditions.checkNonNegative("n", i3);
        MathPreconditions.checkNonNegative("k", i4);
        Preconditions.checkArgument(i4 <= i3, "k (%s) > n (%s)", i4, i3);
        if (i4 > (i3 >> 1)) {
            i4 = i3 - i4;
        }
        long j2 = 1;
        if (i4 == 0) {
            return 1;
        }
        if (i4 == 1) {
            return (long) i3;
        }
        long[] jArr = factorials;
        if (i3 < jArr.length) {
            return jArr[i3] / (jArr[i4] * jArr[i3 - i4]);
        }
        int[] iArr = biggestBinomials;
        if (i4 >= iArr.length || i3 > iArr[i4]) {
            return Long.MAX_VALUE;
        }
        int[] iArr2 = biggestSimpleBinomials;
        if (i4 >= iArr2.length || i3 > iArr2[i4]) {
            long j3 = (long) i3;
            int log2 = log2(j3, RoundingMode.CEILING);
            int i5 = i3 - 1;
            int i6 = log2;
            int i7 = 2;
            long j4 = j3;
            long j5 = 1;
            while (i7 <= i4) {
                i6 += log2;
                if (i6 < 63) {
                    j4 *= (long) i5;
                    j5 *= (long) i7;
                } else {
                    j2 = multiplyFraction(j2, j4, j5);
                    j4 = (long) i5;
                    j5 = (long) i7;
                    i6 = log2;
                }
                i7++;
                i5--;
            }
            return multiplyFraction(j2, j4, j5);
        }
        int i8 = i3 - 1;
        long j6 = (long) i3;
        for (int i9 = 2; i9 <= i4; i9++) {
            j6 = (j6 * ((long) i8)) / ((long) i9);
            i8--;
        }
        return j6;
    }

    public static long ceilingPowerOfTwo(long j2) {
        MathPreconditions.checkPositive("x", j2);
        if (j2 <= 4611686018427387904L) {
            return 1 << (-Long.numberOfLeadingZeros(j2 - 1));
        }
        throw new ArithmeticException(a.j("ceilingPowerOfTwo(", j2, ") is not representable as a long"));
    }

    public static long checkedAdd(long j2, long j3) {
        long j4 = j2 + j3;
        boolean z2 = false;
        boolean z3 = (j2 ^ j3) < 0;
        if ((j2 ^ j4) >= 0) {
            z2 = true;
        }
        MathPreconditions.checkNoOverflow(z3 | z2, "checkedAdd", j2, j3);
        return j4;
    }

    public static long checkedMultiply(long j2, long j3) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(~j3) + Long.numberOfLeadingZeros(j3) + Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j2);
        if (numberOfLeadingZeros > 65) {
            return j2 * j3;
        }
        MathPreconditions.checkNoOverflow(numberOfLeadingZeros >= 64, "checkedMultiply", j2, j3);
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        MathPreconditions.checkNoOverflow((i3 >= 0) | (j3 != Long.MIN_VALUE), "checkedMultiply", j2, j3);
        long j4 = j2 * j3;
        MathPreconditions.checkNoOverflow(i3 == 0 || j4 / j2 == j3, "checkedMultiply", j2, j3);
        return j4;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long checkedPow(long j2, int i3) {
        MathPreconditions.checkNonNegative("exponent", i3);
        long j3 = 1;
        if ((j2 >= -2) && (j2 <= 2)) {
            int i4 = (int) j2;
            if (i4 == -2) {
                MathPreconditions.checkNoOverflow(i3 < 64, "checkedPow", j2, (long) i3);
                return (i3 & 1) == 0 ? 1 << i3 : -1 << i3;
            } else if (i4 == -1) {
                return (i3 & 1) == 0 ? 1 : -1;
            } else {
                if (i4 == 0) {
                    return i3 == 0 ? 1 : 0;
                }
                if (i4 == 1) {
                    return 1;
                }
                if (i4 == 2) {
                    MathPreconditions.checkNoOverflow(i3 < 63, "checkedPow", j2, (long) i3);
                    return 1 << i3;
                }
                throw new AssertionError();
            }
        } else {
            long j4 = j2;
            int i5 = i3;
            while (i5 != 0) {
                if (i5 == 1) {
                    return checkedMultiply(j3, j4);
                }
                if ((i5 & 1) != 0) {
                    j3 = checkedMultiply(j3, j4);
                }
                long j5 = j3;
                int i6 = i5 >> 1;
                if (i6 > 0) {
                    MathPreconditions.checkNoOverflow(-3037000499L <= j4 && j4 <= FLOOR_SQRT_MAX_LONG, "checkedPow", j4, (long) i6);
                    j4 *= j4;
                }
                i5 = i6;
                j3 = j5;
            }
            return j3;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long checkedSubtract(long j2, long j3) {
        long j4 = j2 - j3;
        boolean z2 = false;
        boolean z3 = (j2 ^ j3) >= 0;
        if ((j2 ^ j4) >= 0) {
            z2 = true;
        }
        MathPreconditions.checkNoOverflow(z3 | z2, "checkedSubtract", j2, j3);
        return j4;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long divide(long j2, long j3, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        long j4 = j2 / j3;
        long j5 = j2 - (j3 * j4);
        int i3 = (j5 > 0 ? 1 : (j5 == 0 ? 0 : -1));
        if (i3 == 0) {
            return j4;
        }
        boolean z2 = true;
        int i4 = ((int) ((j2 ^ j3) >> 63)) | 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                if (i3 != 0) {
                    z2 = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z2);
                return j4;
            case 2:
                return j4;
            case 3:
                if (i4 >= 0) {
                    return j4;
                }
                break;
            case 4:
                break;
            case 5:
                if (i4 <= 0) {
                    return j4;
                }
                break;
            case 6:
            case 7:
            case 8:
                long abs = Math.abs(j5);
                int i5 = ((abs - (Math.abs(j3) - abs)) > 0 ? 1 : ((abs - (Math.abs(j3) - abs)) == 0 ? 0 : -1));
                if (i5 == 0) {
                    if (roundingMode != RoundingMode.HALF_UP && (roundingMode != RoundingMode.HALF_EVEN || (1 & j4) == 0)) {
                        return j4;
                    }
                } else if (i5 <= 0) {
                    return j4;
                }
                break;
            default:
                throw new AssertionError();
        }
        return j4 + ((long) i4);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long factorial(int i3) {
        MathPreconditions.checkNonNegative("n", i3);
        long[] jArr = factorials;
        if (i3 < jArr.length) {
            return jArr[i3];
        }
        return Long.MAX_VALUE;
    }

    public static boolean fitsInInt(long j2) {
        return ((long) ((int) j2)) == j2;
    }

    public static long floorPowerOfTwo(long j2) {
        MathPreconditions.checkPositive("x", j2);
        return 1 << (63 - Long.numberOfLeadingZeros(j2));
    }

    public static long gcd(long j2, long j3) {
        MathPreconditions.checkNonNegative("a", j2);
        MathPreconditions.checkNonNegative("b", j3);
        if (j2 == 0) {
            return j3;
        }
        if (j3 == 0) {
            return j2;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j3);
        long j5 = j3 >> numberOfTrailingZeros2;
        while (j4 != j5) {
            long j6 = j4 - j5;
            long j7 = (j6 >> 63) & j6;
            long j8 = (j6 - j7) - j7;
            j5 += j7;
            j4 = j8 >> Long.numberOfTrailingZeros(j8);
        }
        return j4 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static boolean isPowerOfTwo(long j2) {
        boolean z2 = false;
        boolean z3 = j2 > 0;
        if ((j2 & (j2 - 1)) == 0) {
            z2 = true;
        }
        return z3 & z2;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean isPrime(long j2) {
        if (j2 < 2) {
            MathPreconditions.checkNonNegative("n", j2);
            return false;
        } else if (j2 < 66) {
            return ((722865708377213483 >> (((int) j2) + -2)) & 1) != 0;
        } else {
            if (((1 << ((int) (j2 % 30))) & SIEVE_30) != 0 || j2 % 7 == 0 || j2 % 11 == 0 || j2 % 13 == 0) {
                return false;
            }
            if (j2 < 289) {
                return true;
            }
            for (long[] jArr : millerRabinBaseSets) {
                if (j2 <= jArr[0]) {
                    for (int i3 = 1; i3 < jArr.length; i3++) {
                        if (!MillerRabinTester.test(jArr[i3], j2)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            throw new AssertionError();
        }
    }

    @VisibleForTesting
    public static int lessThanBranchFree(long j2, long j3) {
        return (int) ((~(~(j2 - j3))) >>> 63);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0027, code lost:
        return r4 + r0;
     */
    @com.google.common.annotations.GwtIncompatible
    @com.google.common.annotations.J2ktIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int log10(long r4, java.math.RoundingMode r6) {
        /*
            java.lang.String r0 = "x"
            com.google.common.math.MathPreconditions.checkPositive((java.lang.String) r0, (long) r4)
            int r0 = log10Floor(r4)
            long[] r1 = powersOf10
            r1 = r1[r0]
            int[] r3 = com.google.common.math.LongMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r6 = r6.ordinal()
            r6 = r3[r6]
            switch(r6) {
                case 1: goto L_0x002d;
                case 2: goto L_0x0037;
                case 3: goto L_0x0037;
                case 4: goto L_0x0028;
                case 5: goto L_0x0028;
                case 6: goto L_0x001e;
                case 7: goto L_0x001e;
                case 8: goto L_0x001e;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.AssertionError r4 = new java.lang.AssertionError
            r4.<init>()
            throw r4
        L_0x001e:
            long[] r6 = halfPowersOf10
            r1 = r6[r0]
            int r4 = lessThanBranchFree(r1, r4)
        L_0x0026:
            int r4 = r4 + r0
            return r4
        L_0x0028:
            int r4 = lessThanBranchFree(r1, r4)
            goto L_0x0026
        L_0x002d:
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r4 != 0) goto L_0x0033
            r4 = 1
            goto L_0x0034
        L_0x0033:
            r4 = 0
        L_0x0034:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r4)
        L_0x0037:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.log10(long, java.math.RoundingMode):int");
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static int log10Floor(long j2) {
        byte b3 = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(j2)];
        return b3 - lessThanBranchFree(j2, powersOf10[b3]);
    }

    public static int log2(long j2, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", j2);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(j2));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j2 - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2);
                return lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, j2) + (63 - numberOfLeadingZeros);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j2);
    }

    public static long mean(long j2, long j3) {
        return (j2 & j3) + ((j2 ^ j3) >> 1);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static int mod(long j2, int i3) {
        return (int) mod(j2, (long) i3);
    }

    public static long multiplyFraction(long j2, long j3, long j4) {
        if (j2 == 1) {
            return j3 / j4;
        }
        long gcd = gcd(j2, j4);
        return (j3 / (j4 / gcd)) * (j2 / gcd);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long pow(long j2, int i3) {
        MathPreconditions.checkNonNegative("exponent", i3);
        if (-2 > j2 || j2 > 2) {
            long j3 = 1;
            while (i3 != 0) {
                if (i3 == 1) {
                    return j3 * j2;
                }
                j3 *= (i3 & 1) == 0 ? 1 : j2;
                j2 *= j2;
                i3 >>= 1;
            }
            return j3;
        }
        int i4 = (int) j2;
        if (i4 != -2) {
            if (i4 == -1) {
                return (i3 & 1) == 0 ? 1 : -1;
            }
            if (i4 == 0) {
                return i3 == 0 ? 1 : 0;
            }
            if (i4 == 1) {
                return 1;
            }
            if (i4 != 2) {
                throw new AssertionError();
            } else if (i3 < 64) {
                return 1 << i3;
            } else {
                return 0;
            }
        } else if (i3 < 64) {
            return (i3 & 1) == 0 ? 1 << i3 : -(1 << i3);
        } else {
            return 0;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static double roundToDouble(long j2, RoundingMode roundingMode) {
        double d2;
        long j3;
        long j4 = j2;
        double d3 = (double) j4;
        long j5 = (long) d3;
        int compare = j5 == Long.MAX_VALUE ? -1 : Longs.compare(j4, j5);
        int[] iArr = AnonymousClass1.$SwitchMap$java$math$RoundingMode;
        switch (iArr[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(compare == 0);
                return d3;
            case 2:
                return j4 >= 0 ? compare >= 0 ? d3 : DoubleUtils.nextDown(d3) : compare <= 0 ? d3 : Math.nextUp(d3);
            case 3:
                return compare >= 0 ? d3 : DoubleUtils.nextDown(d3);
            case 4:
                return j4 >= 0 ? compare <= 0 ? d3 : Math.nextUp(d3) : compare >= 0 ? d3 : DoubleUtils.nextDown(d3);
            case 5:
                return compare <= 0 ? d3 : Math.nextUp(d3);
            case 6:
            case 7:
            case 8:
                if (compare >= 0) {
                    d2 = Math.nextUp(d3);
                    j3 = (long) Math.ceil(d2);
                } else {
                    double nextDown = DoubleUtils.nextDown(d3);
                    long j6 = j5;
                    j5 = (long) Math.floor(nextDown);
                    d2 = d3;
                    d3 = nextDown;
                    j3 = j6;
                }
                long j7 = j4 - j5;
                long j8 = j3 - j4;
                if (j3 == Long.MAX_VALUE) {
                    j8++;
                }
                int compare2 = Longs.compare(j7, j8);
                if (compare2 < 0) {
                    return d3;
                }
                if (compare2 > 0) {
                    return d2;
                }
                int i3 = iArr[roundingMode.ordinal()];
                if (i3 == 6) {
                    return j4 >= 0 ? d3 : d2;
                }
                if (i3 == 7) {
                    return j4 >= 0 ? d2 : d3;
                }
                if (i3 == 8) {
                    return (DoubleUtils.getSignificand(d3) & 1) == 0 ? d3 : d2;
                }
                throw new AssertionError("impossible");
            default:
                throw new AssertionError("impossible");
        }
    }

    public static long saturatedAdd(long j2, long j3) {
        long j4 = j2 + j3;
        boolean z2 = false;
        boolean z3 = (j3 ^ j2) < 0;
        if ((j2 ^ j4) >= 0) {
            z2 = true;
        }
        return z3 | z2 ? j4 : ((j4 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    public static long saturatedMultiply(long j2, long j3) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(~j3) + Long.numberOfLeadingZeros(j3) + Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j2);
        if (numberOfLeadingZeros > 65) {
            return j2 * j3;
        }
        long j4 = ((j2 ^ j3) >>> 63) + Long.MAX_VALUE;
        boolean z2 = false;
        boolean z3 = numberOfLeadingZeros < 64;
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        boolean z4 = i3 < 0;
        if (j3 == Long.MIN_VALUE) {
            z2 = true;
        }
        if (z3 || (z2 & z4)) {
            return j4;
        }
        long j5 = j2 * j3;
        return (i3 == 0 || j5 / j2 == j3) ? j5 : j4;
    }

    public static long saturatedPow(long j2, int i3) {
        MathPreconditions.checkNonNegative("exponent", i3);
        long j3 = 1;
        if ((j2 >= -2) && (j2 <= 2)) {
            int i4 = (int) j2;
            if (i4 == -2) {
                return i3 >= 64 ? ((long) (i3 & 1)) + Long.MAX_VALUE : (i3 & 1) == 0 ? 1 << i3 : -1 << i3;
            }
            if (i4 == -1) {
                return (i3 & 1) == 0 ? 1 : -1;
            }
            if (i4 == 0) {
                return i3 == 0 ? 1 : 0;
            }
            if (i4 == 1) {
                return 1;
            }
            if (i4 != 2) {
                throw new AssertionError();
            } else if (i3 >= 63) {
                return Long.MAX_VALUE;
            } else {
                return 1 << i3;
            }
        } else {
            long j4 = ((j2 >>> 63) & ((long) (i3 & 1))) + Long.MAX_VALUE;
            while (i3 != 0) {
                if (i3 == 1) {
                    return saturatedMultiply(j3, j2);
                }
                if ((i3 & 1) != 0) {
                    j3 = saturatedMultiply(j3, j2);
                }
                i3 >>= 1;
                if (i3 > 0) {
                    if ((-3037000499L > j2) || (j2 > FLOOR_SQRT_MAX_LONG)) {
                        return j4;
                    }
                    j2 *= j2;
                }
            }
            return j3;
        }
    }

    public static long saturatedSubtract(long j2, long j3) {
        long j4 = j2 - j3;
        boolean z2 = false;
        boolean z3 = (j3 ^ j2) >= 0;
        if ((j2 ^ j4) >= 0) {
            z2 = true;
        }
        return z3 | z2 ? j4 : ((j4 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long sqrt(long j2, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative("x", j2);
        if (fitsInInt(j2)) {
            return (long) IntMath.sqrt((int) j2, roundingMode);
        }
        long sqrt = (long) Math.sqrt((double) j2);
        long j3 = sqrt * sqrt;
        boolean z2 = false;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                if (j3 == j2) {
                    z2 = true;
                }
                MathPreconditions.checkRoundingUnnecessary(z2);
                return sqrt;
            case 2:
            case 3:
                return j2 < j3 ? sqrt - 1 : sqrt;
            case 4:
            case 5:
                return j2 > j3 ? sqrt + 1 : sqrt;
            case 6:
            case 7:
            case 8:
                if (j2 < j3) {
                    z2 = true;
                }
                long j4 = sqrt - (z2 ? 1 : 0);
                return j4 + ((long) lessThanBranchFree((j4 * j4) + j4, j2));
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static long mod(long j2, long j3) {
        if (j3 > 0) {
            long j4 = j2 % j3;
            return j4 >= 0 ? j4 : j4 + j3;
        }
        throw new ArithmeticException("Modulus must be positive");
    }
}
