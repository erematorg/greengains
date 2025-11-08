package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaq  reason: invalid package */
public abstract class zzaq<E> extends zzal<E> implements List<E>, RandomAccess {
    private static final zzbg<Object> zza = new zzas(zzay.zza, 0);

    public static <E> zzaq<E> zzb(Object[] objArr, int i3) {
        if (i3 == 0) {
            return zzay.zza;
        }
        return new zzay(objArr, i3);
    }

    public static <E> zzap<E> zzg() {
        return new zzap<>();
    }

    public static <E> zzaq<E> zzh() {
        return zzay.zza;
    }

    @Deprecated
    public final void add(int i3, E e3) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i3, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == zzz.zza(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i3 = 0;
                    while (i3 < size) {
                        if (zzw.zza(get(i3), list.get(i3))) {
                            i3++;
                        }
                    }
                    return true;
                }
                int size2 = size();
                Iterator it = list.iterator();
                int i4 = 0;
                while (true) {
                    if (i4 < size2) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object obj2 = get(i4);
                        i4++;
                        if (!zzw.zza(obj2, it.next())) {
                            break;
                        }
                    } else if (!it.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int size = size();
        int i3 = 1;
        for (int i4 = 0; i4 < size; i4++) {
            i3 = ~(~(get(i4).hashCode() + (i3 * 31)));
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

    public /* synthetic */ ListIterator listIterator() {
        return (zzbg) listIterator(0);
    }

    @Deprecated
    public final E remove(int i3) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E set(int i3, E e3) {
        throw new UnsupportedOperationException();
    }

    public int zza(Object[] objArr, int i3) {
        int size = size();
        for (int i4 = 0; i4 < size; i4++) {
            objArr[i3 + i4] = get(i4);
        }
        return i3 + size;
    }

    @Deprecated
    public final zzaq<E> zzc() {
        return this;
    }

    /* renamed from: zzd */
    public final zzbd<E> iterator() {
        return (zzbg) listIterator();
    }

    public /* synthetic */ ListIterator listIterator(int i3) {
        zzz.zzb(i3, size());
        if (isEmpty()) {
            return zza;
        }
        return new zzas(this, i3);
    }

    public static <E> zzaq<E> zza(Object[] objArr) {
        return zzb(objArr, objArr.length);
    }

    private static <E> zzaq<E> zzb(Object... objArr) {
        int length = objArr.length;
        int i3 = 0;
        while (i3 < length) {
            if (objArr[i3] != null) {
                i3++;
            } else {
                throw new NullPointerException(a.k("at index ", i3));
            }
        }
        return zzb(objArr, objArr.length);
    }

    public static <E> zzaq<E> zza(Collection<? extends E> collection) {
        if (!(collection instanceof zzal)) {
            return zzb(collection.toArray());
        }
        zzaq<E> zzc = ((zzal) collection).zzc();
        if (!zzc.zze()) {
            return zzc;
        }
        Object[] array = zzc.toArray();
        return zzb(array, array.length);
    }

    public static <E> zzaq<E> zza(E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return zzb(e3, e4, e5, e6, e7, e8, e9, e10);
    }

    /* renamed from: zza */
    public zzaq<E> subList(int i3, int i4) {
        zzz.zza(i3, i4, size());
        int i5 = i4 - i3;
        if (i5 == size()) {
            return this;
        }
        if (i5 == 0) {
            return zzay.zza;
        }
        return new zzar(this, i3, i5);
    }
}
