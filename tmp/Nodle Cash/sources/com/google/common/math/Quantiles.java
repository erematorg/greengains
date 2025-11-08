package com.google.common.math;

import A.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class Quantiles {

    public static final class Scale {
        private final int scale;

        public ScaleAndIndex index(int i3) {
            return new ScaleAndIndex(this.scale, i3);
        }

        public ScaleAndIndexes indexes(int... iArr) {
            return new ScaleAndIndexes(this.scale, (int[]) iArr.clone());
        }

        private Scale(int i3) {
            Preconditions.checkArgument(i3 > 0, "Quantile scale must be positive");
            this.scale = i3;
        }

        public ScaleAndIndexes indexes(Collection<Integer> collection) {
            return new ScaleAndIndexes(this.scale, Ints.toArray(collection));
        }
    }

    public static final class ScaleAndIndex {
        private final int index;
        private final int scale;

        public double compute(Collection<? extends Number> collection) {
            return computeInPlace(Doubles.toArray(collection));
        }

        public double computeInPlace(double... dArr) {
            Preconditions.checkArgument(dArr.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.containsNaN(dArr)) {
                return Double.NaN;
            }
            long length = ((long) this.index) * ((long) (dArr.length - 1));
            int divide = (int) LongMath.divide(length, (long) this.scale, RoundingMode.DOWN);
            int i3 = (int) (length - (((long) divide) * ((long) this.scale)));
            Quantiles.selectInPlace(divide, dArr, 0, dArr.length - 1);
            if (i3 == 0) {
                return dArr[divide];
            }
            int i4 = divide + 1;
            Quantiles.selectInPlace(i4, dArr, i4, dArr.length - 1);
            return Quantiles.interpolate(dArr[divide], dArr[i4], (double) i3, (double) this.scale);
        }

        private ScaleAndIndex(int i3, int i4) {
            Quantiles.checkIndex(i4, i3);
            this.scale = i3;
            this.index = i4;
        }

        public double compute(double... dArr) {
            return computeInPlace((double[]) dArr.clone());
        }

        public double compute(long... jArr) {
            return computeInPlace(Quantiles.longsToDoubles(jArr));
        }

        public double compute(int... iArr) {
            return computeInPlace(Quantiles.intsToDoubles(iArr));
        }
    }

    public static final class ScaleAndIndexes {
        private final int[] indexes;
        private final int scale;

        public Map<Integer, Double> compute(Collection<? extends Number> collection) {
            return computeInPlace(Doubles.toArray(collection));
        }

        public Map<Integer, Double> computeInPlace(double... dArr) {
            double[] dArr2 = dArr;
            int i3 = 0;
            Preconditions.checkArgument(dArr2.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.containsNaN(dArr)) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int[] iArr = this.indexes;
                int length = iArr.length;
                while (i3 < length) {
                    linkedHashMap.put(Integer.valueOf(iArr[i3]), Double.valueOf(Double.NaN));
                    i3++;
                }
                return Collections.unmodifiableMap(linkedHashMap);
            }
            int[] iArr2 = this.indexes;
            int[] iArr3 = new int[iArr2.length];
            int[] iArr4 = new int[iArr2.length];
            int[] iArr5 = new int[(iArr2.length * 2)];
            int i4 = 0;
            int i5 = 0;
            while (true) {
                int[] iArr6 = this.indexes;
                if (i4 >= iArr6.length) {
                    break;
                }
                long length2 = ((long) iArr6[i4]) * ((long) (dArr2.length - 1));
                int divide = (int) LongMath.divide(length2, (long) this.scale, RoundingMode.DOWN);
                int i6 = (int) (length2 - (((long) divide) * ((long) this.scale)));
                iArr3[i4] = divide;
                iArr4[i4] = i6;
                iArr5[i5] = divide;
                int i7 = i5 + 1;
                if (i6 != 0) {
                    iArr5[i7] = divide + 1;
                    i5 += 2;
                } else {
                    i5 = i7;
                }
                i4++;
            }
            Arrays.sort(iArr5, 0, i5);
            Quantiles.selectAllInPlace(iArr5, 0, i5 - 1, dArr, 0, dArr2.length - 1);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            while (true) {
                int[] iArr7 = this.indexes;
                if (i3 >= iArr7.length) {
                    return Collections.unmodifiableMap(linkedHashMap2);
                }
                int i8 = iArr3[i3];
                int i9 = iArr4[i3];
                if (i9 == 0) {
                    linkedHashMap2.put(Integer.valueOf(iArr7[i3]), Double.valueOf(dArr2[i8]));
                } else {
                    linkedHashMap2.put(Integer.valueOf(iArr7[i3]), Double.valueOf(Quantiles.interpolate(dArr2[i8], dArr2[i8 + 1], (double) i9, (double) this.scale)));
                }
                i3++;
            }
        }

        private ScaleAndIndexes(int i3, int[] iArr) {
            boolean z2 = false;
            for (int access$300 : iArr) {
                Quantiles.checkIndex(access$300, i3);
            }
            Preconditions.checkArgument(iArr.length > 0 ? true : z2, "Indexes must be a non empty array");
            this.scale = i3;
            this.indexes = iArr;
        }

        public Map<Integer, Double> compute(double... dArr) {
            return computeInPlace((double[]) dArr.clone());
        }

        public Map<Integer, Double> compute(long... jArr) {
            return computeInPlace(Quantiles.longsToDoubles(jArr));
        }

        public Map<Integer, Double> compute(int... iArr) {
            return computeInPlace(Quantiles.intsToDoubles(iArr));
        }
    }

    /* access modifiers changed from: private */
    public static void checkIndex(int i3, int i4) {
        if (i3 < 0 || i3 > i4) {
            throw new IllegalArgumentException(a.k("Quantile indexes must be between 0 and the scale, which is ", i4));
        }
    }

    private static int chooseNextSelection(int[] iArr, int i3, int i4, int i5, int i6) {
        if (i3 == i4) {
            return i3;
        }
        int i7 = i5 + i6;
        int i8 = i7 >>> 1;
        while (i4 > i3 + 1) {
            int i9 = (i3 + i4) >>> 1;
            int i10 = iArr[i9];
            if (i10 > i8) {
                i4 = i9;
            } else if (i10 >= i8) {
                return i9;
            } else {
                i3 = i9;
            }
        }
        return (i7 - iArr[i3]) - iArr[i4] > 0 ? i4 : i3;
    }

    /* access modifiers changed from: private */
    public static boolean containsNaN(double... dArr) {
        for (double isNaN : dArr) {
            if (Double.isNaN(isNaN)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static double interpolate(double d2, double d3, double d4, double d5) {
        if (d2 == Double.NEGATIVE_INFINITY) {
            return d3 == Double.POSITIVE_INFINITY ? Double.NaN : Double.NEGATIVE_INFINITY;
        }
        if (d3 == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        return (((d3 - d2) * d4) / d5) + d2;
    }

    /* access modifiers changed from: private */
    public static double[] intsToDoubles(int[] iArr) {
        int length = iArr.length;
        double[] dArr = new double[length];
        for (int i3 = 0; i3 < length; i3++) {
            dArr[i3] = (double) iArr[i3];
        }
        return dArr;
    }

    /* access modifiers changed from: private */
    public static double[] longsToDoubles(long[] jArr) {
        int length = jArr.length;
        double[] dArr = new double[length];
        for (int i3 = 0; i3 < length; i3++) {
            dArr[i3] = (double) jArr[i3];
        }
        return dArr;
    }

    public static ScaleAndIndex median() {
        return scale(2).index(1);
    }

    private static void movePivotToStartOfSlice(double[] dArr, int i3, int i4) {
        boolean z2 = true;
        int i5 = (i3 + i4) >>> 1;
        double d2 = dArr[i4];
        double d3 = dArr[i5];
        boolean z3 = d2 < d3;
        double d4 = dArr[i3];
        boolean z4 = d3 < d4;
        if (d2 >= d4) {
            z2 = false;
        }
        if (z3 == z4) {
            swap(dArr, i5, i3);
        } else if (z3 != z2) {
            swap(dArr, i3, i4);
        }
    }

    private static int partition(double[] dArr, int i3, int i4) {
        movePivotToStartOfSlice(dArr, i3, i4);
        double d2 = dArr[i3];
        int i5 = i4;
        while (i4 > i3) {
            if (dArr[i4] > d2) {
                swap(dArr, i5, i4);
                i5--;
            }
            i4--;
        }
        swap(dArr, i3, i5);
        return i5;
    }

    public static Scale percentiles() {
        return scale(100);
    }

    public static Scale quartiles() {
        return scale(4);
    }

    public static Scale scale(int i3) {
        return new Scale(i3);
    }

    /* access modifiers changed from: private */
    public static void selectAllInPlace(int[] iArr, int i3, int i4, double[] dArr, int i5, int i6) {
        int chooseNextSelection = chooseNextSelection(iArr, i3, i4, i5, i6);
        int i7 = iArr[chooseNextSelection];
        selectInPlace(i7, dArr, i5, i6);
        int i8 = chooseNextSelection - 1;
        while (i8 >= i3 && iArr[i8] == i7) {
            i8--;
        }
        if (i8 >= i3) {
            selectAllInPlace(iArr, i3, i8, dArr, i5, i7 - 1);
        }
        int i9 = chooseNextSelection + 1;
        while (i9 <= i4 && iArr[i9] == i7) {
            i9++;
        }
        if (i9 <= i4) {
            selectAllInPlace(iArr, i9, i4, dArr, i7 + 1, i6);
        }
    }

    /* access modifiers changed from: private */
    public static void selectInPlace(int i3, double[] dArr, int i4, int i5) {
        if (i3 == i4) {
            int i6 = i4;
            for (int i7 = i4 + 1; i7 <= i5; i7++) {
                if (dArr[i6] > dArr[i7]) {
                    i6 = i7;
                }
            }
            if (i6 != i4) {
                swap(dArr, i6, i4);
                return;
            }
            return;
        }
        while (i5 > i4) {
            int partition = partition(dArr, i4, i5);
            if (partition >= i3) {
                i5 = partition - 1;
            }
            if (partition <= i3) {
                i4 = partition + 1;
            }
        }
    }

    private static void swap(double[] dArr, int i3, int i4) {
        double d2 = dArr[i3];
        dArr[i3] = dArr[i4];
        dArr[i4] = d2;
    }
}
