package com.google.android.gms.internal.location;

import androidx.work.impl.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

public abstract class zzex extends zzeu implements List, RandomAccess {
    private static final zzfa zza = new zzev(zzey.zza, 0);

    public static zzex zzi() {
        return zzey.zza;
    }

    public static zzex zzj(Collection collection) {
        if (collection instanceof zzeu) {
            zzex zze = ((zzeu) collection).zze();
            if (!zze.zzf()) {
                return zze;
            }
            Object[] array = zze.toArray();
            return zzk(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        int i3 = 0;
        while (i3 < length) {
            if (array2[i3] != null) {
                i3++;
            } else {
                throw new NullPointerException(a.u(new StringBuilder(String.valueOf(i3).length() + 9), "at index ", i3));
            }
        }
        return zzk(array2, length);
    }

    public static zzex zzk(Object[] objArr, int i3) {
        return i3 == 0 ? zzey.zza : new zzey(objArr, i3);
    }

    @Deprecated
    public final void add(int i3, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i3, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i3 = 0;
                    while (i3 < size) {
                        if (zzeq.zza(get(i3), list.get(i3))) {
                            i3++;
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzeq.zza(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int size = size();
        int i3 = 1;
        for (int i4 = 0; i4 < size; i4++) {
            i3 = (i3 * 31) + get(i4).hashCode();
        }
        return i3;
    }

    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (obj.equals(get(i3))) {
                return i3;
            }
        }
        return -1;
    }

    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Deprecated
    public final Object remove(int i3) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object set(int i3, Object obj) {
        throw new UnsupportedOperationException();
    }

    public final zzez zza() {
        return listIterator(0);
    }

    @Deprecated
    public final zzex zze() {
        return this;
    }

    public int zzg(Object[] objArr, int i3) {
        int size = size();
        for (int i4 = 0; i4 < size; i4++) {
            objArr[i4] = get(i4);
        }
        return size;
    }

    /* renamed from: zzh */
    public zzex subList(int i3, int i4) {
        zzer.zze(i3, i4, size());
        int i5 = i4 - i3;
        return i5 == size() ? this : i5 == 0 ? zzey.zza : new zzew(this, i3, i5);
    }

    /* renamed from: zzl */
    public final zzfa listIterator(int i3) {
        zzer.zzd(i3, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? zza : new zzev(this, i3);
    }
}
