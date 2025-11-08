package com.google.android.gms.common;

final class zzy {
    public static int zza(int i3) {
        int[] iArr = {1, 2, 3, 4, 5, 6};
        int i4 = 0;
        while (i4 < 6) {
            int i5 = iArr[i4];
            int i6 = i5 - 1;
            if (i5 == 0) {
                throw null;
            } else if (i6 == i3) {
                return i5;
            } else {
                i4++;
            }
        }
        return 1;
    }
}
