package com.google.android.gms.common;

import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
public final class zzd {
    public static int zza(int i3) {
        int[] iArr = {1, 2, 3};
        int i4 = 0;
        while (i4 < 3) {
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
