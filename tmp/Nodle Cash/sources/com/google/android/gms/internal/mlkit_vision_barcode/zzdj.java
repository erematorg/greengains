package com.google.android.gms.internal.mlkit_vision_barcode;

import A.a;

public final class zzdj {
    public static Object[] zza(Object[] objArr, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            if (objArr[i4] != null) {
                i4++;
            } else {
                throw new NullPointerException(a.k("at index ", i4));
            }
        }
        return objArr;
    }
}
