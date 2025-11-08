package com.google.android.gms.internal.fido;

import A.a;
import javax.annotation.CheckForNull;

public final class zzbq {
    public static Object zza(@CheckForNull Object obj, int i3) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(a.k("at index ", i3));
    }

    public static Object[] zzb(Object[] objArr, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            zza(objArr[i4], i4);
        }
        return objArr;
    }
}
