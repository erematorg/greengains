package com.google.android.gms.internal.mlkit_common;

public class zzaa {
    public static int zza(int i3, int i4) {
        if (i4 >= 0) {
            int i5 = i3 + (i3 >> 1) + 1;
            if (i5 < i4) {
                int highestOneBit = Integer.highestOneBit(i4 - 1);
                i5 = highestOneBit + highestOneBit;
            }
            if (i5 < 0) {
                return Integer.MAX_VALUE;
            }
            return i5;
        }
        throw new AssertionError("cannot store more than MAX_VALUE elements");
    }
}
