package com.google.zxing.oned.rss;

public final class RSSUtils {
    private RSSUtils() {
    }

    private static int combins(int i3, int i4) {
        int i5 = i3 - i4;
        if (i5 > i4) {
            int i6 = i5;
            i5 = i4;
            i4 = i6;
        }
        int i7 = 1;
        int i8 = 1;
        while (i3 > i4) {
            i7 *= i3;
            if (i8 <= i5) {
                i7 /= i8;
                i8++;
            }
            i3--;
        }
        while (i8 <= i5) {
            i7 /= i8;
            i8++;
        }
        return i7;
    }

    public static int getRSSvalue(int[] iArr, int i3, boolean z2) {
        int[] iArr2 = iArr;
        int i4 = i3;
        int i5 = 0;
        for (int i6 : iArr2) {
            i5 += i6;
        }
        int length = iArr2.length;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            int i10 = length - 1;
            if (i7 >= i10) {
                return i8;
            }
            int i11 = 1 << i7;
            i9 |= i11;
            int i12 = 1;
            while (i12 < iArr2[i7]) {
                int i13 = i5 - i12;
                int i14 = length - i7;
                int i15 = i14 - 2;
                int combins = combins(i13 - 1, i15);
                if (z2 && i9 == 0) {
                    int i16 = i14 - 1;
                    if (i13 - i16 >= i16) {
                        combins -= combins(i13 - i14, i15);
                    }
                }
                if (i14 - 1 > 1) {
                    int i17 = i13 - i15;
                    int i18 = 0;
                    while (i17 > i4) {
                        i18 += combins((i13 - i17) - 1, i14 - 3);
                        i17--;
                        int[] iArr3 = iArr;
                    }
                    combins -= (i10 - i7) * i18;
                } else if (i13 > i4) {
                    combins--;
                }
                i8 += combins;
                i12++;
                i9 &= ~i11;
                iArr2 = iArr;
            }
            i5 -= i12;
            i7++;
            iArr2 = iArr;
        }
    }
}
