package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;

public final class zzds {
    public static int zza(Set set) {
        Iterator it = set.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i3 += next != null ? next.hashCode() : 0;
        }
        return i3;
    }

    public static boolean zzb(Set set, @CheckForNull Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
    }

    public static boolean zzc(Set set, Collection collection) {
        collection.getClass();
        if (collection instanceof zzdi) {
            collection = ((zzdi) collection).zza();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return zzd(set, collection.iterator());
        }
        Iterator it = set.iterator();
        collection.getClass();
        boolean z2 = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    public static boolean zzd(Set set, Iterator it) {
        boolean z2 = false;
        while (it.hasNext()) {
            z2 |= set.remove(it.next());
        }
        return z2;
    }
}
