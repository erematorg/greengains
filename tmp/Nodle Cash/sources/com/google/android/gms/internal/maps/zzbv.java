package com.google.android.gms.internal.maps;

import java.util.Iterator;
import java.util.Set;

public final class zzbv {
    public static int zza(Set set) {
        Iterator it = set.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i3 += next != null ? next.hashCode() : 0;
        }
        return i3;
    }
}
