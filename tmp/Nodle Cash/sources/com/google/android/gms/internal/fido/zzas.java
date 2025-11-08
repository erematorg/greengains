package com.google.android.gms.internal.fido;

import A.a;

final class zzas {
    public static void zza(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(obj2)));
        } else if (obj2 == null) {
            throw new NullPointerException(a.l("null value in entry: ", obj.toString(), "=null"));
        }
    }
}
