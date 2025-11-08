package com.google.common.math;

import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class IntMath {
    @VisibleForTesting
    static final int FLOOR_SQRT_MAX_INT = 46340;
    @VisibleForTesting
    static final int MAX_POWER_OF_SQRT2_UNSIGNED = -1257966797;
    @VisibleForTesting
    static final int MAX_SIGNED_POWER_OF_TWO = 1073741824;
    @VisibleForTesting
    static int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    @VisibleForTesting
    static final int[] halfPowersOf10 = {3, 31, TypedValues.AttributesType.TYPE_PATH_ROTATE, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    @VisibleForTesting
    static final int[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    /* renamed from: com.google.common.math.IntMath$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.IntMath.AnonymousClass1.<clinit>():void");
        }
    }

    private IntMath() {
    }

    public static int binomial(int i3, int i4) {
        MathPreconditions.checkNonNegative("n", i3);
        MathPreconditions.checkNonNegative("k", i4);
        int i5 = 0;
        Preconditions.checkArgument(i4 <= i3, "k (%s) > n (%s)", i4, i3);
        if (i4 > (i3 >> 1)) {
            i4 = i3 - i4;
        }
        int[] iArr = biggestBinomials;
        if (i4 >= iArr.length || i3 > iArr[i4]) {
            return Integer.MAX_VALUE;
        }
        if (i4 == 0) {
            return 1;
        }
        if (i4 == 1) {
            return i3;
        }
        long j2 = 1;
        while (i5 < i4) {
            i5++;
            j2 = (j2 * ((long) (i3 - i5))) / ((long) i5);
        }
        return (int) j2;
    }

    public static int ceilingPowerOfTwo(int i3) {
        MathPreconditions.checkPositive("x", i3);
        if (i3 <= 1073741824) {
            return 1 << (-Integer.numberOfLeadingZeros(i3 - 1));
        }
        throw new ArithmeticException(C0118y.c(i3, "ceilingPowerOfTwo(", ") not representable as an int"));
    }

    public static int checkedAdd(int i3, int i4) {
        long j2 = ((long) i3) + ((long) i4);
        int i5 = (int) j2;
        MathPreconditions.checkNoOverflow(j2 == ((long) i5), "checkedAdd", i3, i4);
        return i5;
    }

    public static int checkedMultiply(int i3, int i4) {
        long j2 = ((long) i3) * ((long) i4);
        int i5 = (int) j2;
        MathPreconditions.checkNoOverflow(j2 == ((long) i5), "checkedMultiply", i3, i4);
        return i5;
    }

    public static int checkedPow(int i3, int i4) {
        MathPreconditions.checkNonNegative("exponent", i4);
        boolean z2 = false;
        if (i3 == -2) {
            if (i4 < 32) {
                z2 = true;
            }
            MathPreconditions.checkNoOverflow(z2, "checkedPow", i3, i4);
            return (i4 & 1) == 0 ? 1 << i4 : -1 << i4;
        } else if (i3 == -1) {
            return (i4 & 1) == 0 ? 1 : -1;
        } else {
            if (i3 == 0) {
                return i4 == 0 ? 1 : 0;
            }
            if (i3 == 1) {
                return 1;
            }
            if (i3 != 2) {
                int i5 = 1;
                while (i4 != 0) {
                    if (i4 == 1) {
                        return checkedMultiply(i5, i3);
                    }
                    if ((i4 & 1) != 0) {
                        i5 = checkedMultiply(i5, i3);
                    }
                    i4 >>= 1;
                    if (i4 > 0) {
                        MathPreconditions.checkNoOverflow((-46340 <= i3) & (i3 <= FLOOR_SQRT_MAX_INT), "checkedPow", i3, i4);
                        i3 *= i3;
                    }
                }
                return i5;
            }
            if (i4 < 31) {
                z2 = true;
            }
            MathPreconditions.checkNoOverflow(z2, "checkedPow", i3, i4);
            return 1 << i4;
        }
    }

    public static int checkedSubtract(int i3, int i4) {
        long j2 = ((long) i3) - ((long) i4);
        int i5 = (int) j2;
        MathPreconditions.checkNoOverflow(j2 == ((long) i5), "checkedSubtract", i3, i4);
        return i5;
    }

    public static int divide(int i3, int i4, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        if (i4 != 0) {
            int i5 = i3 / i4;
            int i6 = i3 - (i4 * i5);
            if (i6 == 0) {
                return i5;
            }
            boolean z2 = true;
            int i7 = ((i3 ^ i4) >> 31) | 1;
            switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
                case 1:
                    if (i6 != 0) {
                        z2 = false;
                    }
                    MathPreconditions.checkRoundingUnnecessary(z2);
                    return i5;
                case 2:
                    return i5;
                case 3:
                    if (i7 >= 0) {
                        return i5;
                    }
                    break;
                case 4:
                    break;
                case 5:
                    if (i7 <= 0) {
                        return i5;
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    int abs = Math.abs(i6);
                    int abs2 = abs - (Math.abs(i4) - abs);
                    if (abs2 == 0) {
                        if (roundingMode != RoundingMode.HALF_UP) {
                            boolean z3 = roundingMode == RoundingMode.HALF_EVEN;
                            if ((i5 & 1) == 0) {
                                z2 = false;
                            }
                            if (!z3 || !z2) {
                                return i5;
                            }
                        }
                    } else if (abs2 <= 0) {
                        return i5;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            return i5 + i7;
        }
        throw new ArithmeticException("/ by zero");
    }

    public static int factorial(int i3) {
        MathPreconditions.checkNonNegative("n", i3);
        int[] iArr = factorials;
        if (i3 < iArr.length) {
            return iArr[i3];
        }
        return Integer.MAX_VALUE;
    }

    public static int floorPowerOfTwo(int i3) {
        MathPreconditions.checkPositive("x", i3);
        return Integer.highestOneBit(i3);
    }

    public static int gcd(int i3, int i4) {
        MathPreconditions.checkNonNegative("a", i3);
        MathPreconditions.checkNonNegative("b", i4);
        if (i3 == 0) {
            return i4;
        }
        if (i4 == 0) {
            return i3;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i3);
        int i5 = i3 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i4);
        int i6 = i4 >> numberOfTrailingZeros2;
        while (i5 != i6) {
            int i7 = i5 - i6;
            int i8 = (i7 >> 31) & i7;
            int i9 = (i7 - i8) - i8;
            i6 += i8;
            i5 = i9 >> Integer.numberOfTrailingZeros(i9);
        }
        return i5 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static boolean isPowerOfTwo(int i3) {
        boolean z2 = false;
        boolean z3 = i3 > 0;
        if ((i3 & (i3 - 1)) == 0) {
            z2 = true;
        }
        return z3 & z2;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static boolean isPrime(int i3) {
        return LongMath.isPrime((long) i3);
    }

    @VisibleForTesting
    public static int lessThanBranchFree(int i3, int i4) {
        return (~(~(i3 - i4))) >>> 31;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0027, code lost:
        return r3 + r0;
     */
    @com.google.common.annotations.GwtIncompatible
    @com.google.common.annotations.J2ktIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int log10(int r3, java.math.RoundingMode r4) {
        /*
            java.lang.String r0 = "x"
            com.google.common.math.MathPreconditions.checkPositive((java.lang.String) r0, (int) r3)
            int r0 = log10Floor(r3)
            int[] r1 = powersOf10
            r1 = r1[r0]
            int[] r2 = com.google.common.math.IntMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r4 = r4.ordinal()
            r4 = r2[r4]
            switch(r4) {
                case 1: goto L_0x002d;
                case 2: goto L_0x0035;
                case 3: goto L_0x0035;
                case 4: goto L_0x0028;
                case 5: goto L_0x0028;
                case 6: goto L_0x001e;
                case 7: goto L_0x001e;
                case 8: goto L_0x001e;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.AssertionError r3 = new java.lang.AssertionError
            r3.<init>()
            throw r3
        L_0x001e:
            int[] r4 = halfPowersOf10
            r4 = r4[r0]
            int r3 = lessThanBranchFree(r4, r3)
        L_0x0026:
            int r3 = r3 + r0
            return r3
        L_0x0028:
            int r3 = lessThanBranchFree(r1, r3)
            goto L_0x0026
        L_0x002d:
            if (r3 != r1) goto L_0x0031
            r3 = 1
            goto L_0x0032
        L_0x0031:
            r3 = 0
        L_0x0032:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r3)
        L_0x0035:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.IntMath.log10(int, java.math.RoundingMode):int");
    }

    private static int log10Floor(int i3) {
        byte b3 = maxLog10ForLeadingZeros[Integer.numberOfLeadingZeros(i3)];
        return b3 - lessThanBranchFree(i3, powersOf10[b3]);
    }

    public static int log2(int i3, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", i3);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(i3));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i3 - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i3);
                return lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, i3) + (31 - numberOfLeadingZeros);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i3);
    }

    public static int mean(int i3, int i4) {
        return (i3 & i4) + ((i3 ^ i4) >> 1);
    }

    public static int mod(int i3, int i4) {
        if (i4 > 0) {
            int i5 = i3 % i4;
            return i5 >= 0 ? i5 : i5 + i4;
        }
        throw new ArithmeticException(C0118y.c(i4, "Modulus ", " must be > 0"));
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static int pow(int i3, int i4) {
        MathPreconditions.checkNonNegative("exponent", i4);
        if (i3 != -2) {
            if (i3 == -1) {
                return (i4 & 1) == 0 ? 1 : -1;
            }
            if (i3 == 0) {
                return i4 == 0 ? 1 : 0;
            }
            if (i3 == 1) {
                return 1;
            }
            if (i3 != 2) {
                int i5 = 1;
                while (i4 != 0) {
                    if (i4 == 1) {
                        return i3 * i5;
                    }
                    i5 *= (i4 & 1) == 0 ? 1 : i3;
                    i3 *= i3;
                    i4 >>= 1;
                }
                return i5;
            } else if (i4 < 32) {
                return 1 << i4;
            } else {
                return 0;
            }
        } else if (i4 < 32) {
            return (i4 & 1) == 0 ? 1 << i4 : -(1 << i4);
        } else {
            return 0;
        }
    }

    public static int saturatedAdd(int i3, int i4) {
        return Ints.saturatedCast(((long) i3) + ((long) i4));
    }

    public static int saturatedMultiply(int i3, int i4) {
        return Ints.saturatedCast(((long) i3) * ((long) i4));
    }

    public static int saturatedPow(int i3, int i4) {
        MathPreconditions.checkNonNegative("exponent", i4);
        if (i3 == -2) {
            return i4 >= 32 ? (i4 & 1) + Integer.MAX_VALUE : (i4 & 1) == 0 ? 1 << i4 : -1 << i4;
        }
        if (i3 == -1) {
            return (i4 & 1) == 0 ? 1 : -1;
        }
        if (i3 == 0) {
            return i4 == 0 ? 1 : 0;
        }
        if (i3 == 1) {
            return 1;
        }
        if (i3 != 2) {
            int i5 = ((i3 >>> 31) & i4 & 1) + Integer.MAX_VALUE;
            int i6 = 1;
            while (i4 != 0) {
                if (i4 == 1) {
                    return saturatedMultiply(i6, i3);
                }
                if ((i4 & 1) != 0) {
                    i6 = saturatedMultiply(i6, i3);
                }
                i4 >>= 1;
                if (i4 > 0) {
                    if ((-46340 > i3) || (i3 > FLOOR_SQRT_MAX_INT)) {
                        return i5;
                    }
                    i3 *= i3;
                }
            }
            return i6;
        } else if (i4 >= 31) {
            return Integer.MAX_VALUE;
        } else {
            return 1 << i4;
        }
    }

    public static int saturatedSubtract(int i3, int i4) {
        return Ints.saturatedCast(((long) i3) - ((long) i4));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0022, code lost:
        return r2 + r0;
     */
    @com.google.common.annotations.GwtIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int sqrt(int r2, java.math.RoundingMode r3) {
        /*
            java.lang.String r0 = "x"
            com.google.common.math.MathPreconditions.checkNonNegative((java.lang.String) r0, (int) r2)
            int r0 = sqrtFloor(r2)
            int[] r1 = com.google.common.math.IntMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r3 = r3.ordinal()
            r3 = r1[r3]
            switch(r3) {
                case 1: goto L_0x002a;
                case 2: goto L_0x0034;
                case 3: goto L_0x0034;
                case 4: goto L_0x0023;
                case 5: goto L_0x0023;
                case 6: goto L_0x001a;
                case 7: goto L_0x001a;
                case 8: goto L_0x001a;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.AssertionError r2 = new java.lang.AssertionError
            r2.<init>()
            throw r2
        L_0x001a:
            int r3 = r0 * r0
            int r3 = r3 + r0
            int r2 = lessThanBranchFree(r3, r2)
        L_0x0021:
            int r2 = r2 + r0
            return r2
        L_0x0023:
            int r3 = r0 * r0
            int r2 = lessThanBranchFree(r3, r2)
            goto L_0x0021
        L_0x002a:
            int r3 = r0 * r0
            if (r3 != r2) goto L_0x0030
            r2 = 1
            goto L_0x0031
        L_0x0030:
            r2 = 0
        L_0x0031:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r2)
        L_0x0034:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.IntMath.sqrt(int, java.math.RoundingMode):int");
    }

    private static int sqrtFloor(int i3) {
        return (int) Math.sqrt((double) i3);
    }
}
