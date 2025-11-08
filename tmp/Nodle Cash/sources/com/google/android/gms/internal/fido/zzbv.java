package com.google.android.gms.internal.fido;

import java.io.Serializable;

final class zzbv extends zzbr implements Serializable {
    static final zzbv zza = new zzbv();

    private zzbv() {
    }

    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        comparable.getClass();
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    public final String toString() {
        return "Ordering.natural().reverse()";
    }

    public final zzbr zza() {
        return zzbp.zza;
    }
}
