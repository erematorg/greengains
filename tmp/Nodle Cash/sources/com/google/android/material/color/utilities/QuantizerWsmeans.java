package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class QuantizerWsmeans {
    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0d;

    public static final class Distance implements Comparable<Distance> {
        double distance = -1.0d;
        int index = -1;

        public int compareTo(Distance distance2) {
            return Double.valueOf(this.distance).compareTo(Double.valueOf(distance2.distance));
        }
    }

    private QuantizerWsmeans() {
    }

    public static Map<Integer, Integer> quantize(int[] iArr, int[] iArr2, int i3) {
        int[] iArr3;
        int i4;
        int i5;
        int i6;
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        int i7 = 1;
        Random random = new Random(272008);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        double[][] dArr = new double[iArr4.length][];
        int[] iArr6 = new int[iArr4.length];
        PointProviderLab pointProviderLab = new PointProviderLab();
        int i8 = 0;
        for (int i9 : iArr4) {
            Integer num = (Integer) linkedHashMap.get(Integer.valueOf(i9));
            if (num == null) {
                dArr[i8] = pointProviderLab.fromInt(i9);
                iArr6[i8] = i9;
                i8++;
                linkedHashMap.put(Integer.valueOf(i9), 1);
            } else {
                linkedHashMap.put(Integer.valueOf(i9), Integer.valueOf(num.intValue() + 1));
            }
        }
        int[] iArr7 = new int[i8];
        for (int i10 = 0; i10 < i8; i10++) {
            iArr7[i10] = ((Integer) linkedHashMap.get(Integer.valueOf(iArr6[i10]))).intValue();
        }
        int min = Math.min(i3, i8);
        if (iArr5.length != 0) {
            min = Math.min(min, iArr5.length);
        }
        double[][] dArr2 = new double[min][];
        int i11 = 0;
        for (int i12 = 0; i12 < iArr5.length; i12++) {
            dArr2[i12] = pointProviderLab.fromInt(iArr5[i12]);
            i11++;
        }
        int i13 = min - i11;
        if (i13 > 0) {
            for (int i14 = 0; i14 < i13; i14++) {
            }
        }
        int[] iArr8 = new int[i8];
        for (int i15 = 0; i15 < i8; i15++) {
            iArr8[i15] = random.nextInt(min);
        }
        int[][] iArr9 = new int[min][];
        for (int i16 = 0; i16 < min; i16++) {
            iArr9[i16] = new int[min];
        }
        Distance[][] distanceArr = new Distance[min][];
        for (int i17 = 0; i17 < min; i17++) {
            distanceArr[i17] = new Distance[min];
            for (int i18 = 0; i18 < min; i18++) {
                distanceArr[i17][i18] = new Distance();
            }
        }
        int[] iArr10 = new int[min];
        int i19 = 0;
        while (true) {
            if (i19 >= 10) {
                iArr3 = iArr10;
                i4 = 0;
                break;
            }
            int i20 = 0;
            while (i20 < min) {
                int i21 = i20 + 1;
                int i22 = i21;
                while (i22 < min) {
                    int[] iArr11 = iArr10;
                    double distance = pointProviderLab.distance(dArr2[i20], dArr2[i22]);
                    Distance distance2 = distanceArr[i22][i20];
                    distance2.distance = distance;
                    distance2.index = i20;
                    Distance distance3 = distanceArr[i20][i22];
                    distance3.distance = distance;
                    distance3.index = i22;
                    i7 = 1;
                    i22++;
                    iArr10 = iArr11;
                    i19 = i19;
                }
                int[] iArr12 = iArr10;
                int i23 = i19;
                Arrays.sort(distanceArr[i20]);
                for (int i24 = 0; i24 < min; i24 += i7) {
                    iArr9[i20][i24] = distanceArr[i20][i24].index;
                }
                iArr10 = iArr12;
                i19 = i23;
                i20 = i21;
            }
            int[] iArr13 = iArr10;
            int i25 = i19;
            int i26 = 0;
            int i27 = 0;
            while (i26 < i8) {
                double[] dArr3 = dArr[i26];
                int i28 = iArr8[i26];
                double distance4 = pointProviderLab.distance(dArr3, dArr2[i28]);
                int[][] iArr14 = iArr9;
                double d2 = distance4;
                int i29 = -1;
                int i30 = 0;
                while (i30 < min) {
                    Distance[][] distanceArr2 = distanceArr;
                    int i31 = i8;
                    if (distanceArr[i28][i30].distance < 4.0d * distance4) {
                        double distance5 = pointProviderLab.distance(dArr3, dArr2[i30]);
                        if (distance5 < d2) {
                            d2 = distance5;
                            i29 = i30;
                        }
                    }
                    i30++;
                    i8 = i31;
                    distanceArr = distanceArr2;
                }
                Distance[][] distanceArr3 = distanceArr;
                int i32 = i8;
                if (i29 != -1 && Math.abs(Math.sqrt(d2) - Math.sqrt(distance4)) > 3.0d) {
                    i27++;
                    iArr8[i26] = i29;
                }
                i26++;
                iArr9 = iArr14;
                i8 = i32;
                distanceArr = distanceArr3;
            }
            int[][] iArr15 = iArr9;
            Distance[][] distanceArr4 = distanceArr;
            int i33 = i8;
            if (i27 == 0 && i25 != 0) {
                i4 = 0;
                iArr3 = iArr13;
                break;
            }
            double[] dArr4 = new double[min];
            double[] dArr5 = new double[min];
            double[] dArr6 = new double[min];
            int[] iArr16 = iArr13;
            char c3 = 0;
            Arrays.fill(iArr16, 0);
            int i34 = 0;
            while (true) {
                i5 = i33;
                if (i34 >= i5) {
                    break;
                }
                int i35 = iArr8[i34];
                double[] dArr7 = dArr[i34];
                int i36 = iArr7[i34];
                iArr16[i35] = iArr16[i35] + i36;
                double d3 = dArr4[i35];
                double d4 = dArr7[c3];
                int[] iArr17 = iArr7;
                double d5 = (double) i36;
                dArr4[i35] = (d4 * d5) + d3;
                dArr5[i35] = (dArr7[1] * d5) + dArr5[i35];
                dArr6[i35] = (dArr7[2] * d5) + dArr6[i35];
                i34++;
                iArr7 = iArr17;
                iArr8 = iArr8;
                c3 = 0;
                i33 = i5;
            }
            int[] iArr18 = iArr7;
            int[] iArr19 = iArr8;
            int i37 = 0;
            while (i37 < min) {
                int i38 = iArr16[i37];
                if (i38 == 0) {
                    dArr2[i37] = new double[]{0.0d, 0.0d, 0.0d};
                    i6 = 1;
                } else {
                    double d6 = (double) i38;
                    double[] dArr8 = dArr2[i37];
                    dArr8[0] = dArr4[i37] / d6;
                    i6 = 1;
                    dArr8[1] = dArr5[i37] / d6;
                    dArr8[2] = dArr6[i37] / d6;
                }
                i37 += i6;
            }
            i19 = i25 + 1;
            iArr7 = iArr18;
            i7 = 1;
            iArr9 = iArr15;
            iArr8 = iArr19;
            distanceArr = distanceArr4;
            iArr10 = iArr16;
            i8 = i5;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (int i39 = i4; i39 < min; i39++) {
            int i40 = iArr3[i39];
            if (i40 != 0) {
                int i41 = pointProviderLab.toInt(dArr2[i39]);
                if (!linkedHashMap2.containsKey(Integer.valueOf(i41))) {
                    linkedHashMap2.put(Integer.valueOf(i41), Integer.valueOf(i40));
                }
            }
        }
        return linkedHashMap2;
    }
}
