package com.google.android.gms.internal.fido;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

public abstract class zzaz extends zzav implements List, RandomAccess {
    private static final zzcc zza = new zzaw(zzbs.zza, 0);
    public static final /* synthetic */ int zzd = 0;

    public static zzaz zzh(Object[] objArr, int i3) {
        return i3 == 0 ? zzbs.zza : new zzbs(objArr, i3);
    }

    public static zzaz zzi(Object[] objArr) {
        if (objArr.length == 0) {
            return zzbs.zza;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        zzbq.zzb(objArr2, length);
        return zzh(objArr2, length);
    }

    public static zzaz zzj(Object obj) {
        Object[] objArr = {obj};
        zzbq.zzb(objArr, 1);
        return zzh(objArr, 1);
    }

    @Deprecated
    public final void add(int i3, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i3, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(@CheckForNull Object obj) {
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
                        if (zzao.zza(get(i3), list.get(i3))) {
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
                            if (!zzao.zza(it.next(), it2.next())) {
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

    public int indexOf(@CheckForNull Object obj) {
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

    public int lastIndexOf(@CheckForNull Object obj) {
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

    public int zza(Object[] objArr, int i3) {
        int size = size();
        for (int i4 = 0; i4 < size; i4++) {
            objArr[i4] = get(i4);
        }
        return size;
    }

    public final zzcb zzd() {
        return listIterator(0);
    }

    public zzaz zzf() {
        return size() <= 1 ? this : new zzax(this);
    }

    /* renamed from: zzg */
    public zzaz subList(int i3, int i4) {
        zzap.zze(i3, i4, size());
        int i5 = i4 - i3;
        return i5 == size() ? this : i5 == 0 ? zzbs.zza : new zzay(this, i3, i5);
    }

    /* renamed from: zzk */
    public final zzcc listIterator(int i3) {
        zzap.zzb(i3, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? zza : new zzaw(this, i3);
    }
}
