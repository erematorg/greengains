package com.google.android.gms.internal.fido;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

final class zzbu extends zzbi {
    static final zzbu zzc = new zzbu(zzbs.zza, zzbp.zza);
    final transient zzaz zzd;

    static {
        int i3 = zzaz.zzd;
    }

    public zzbu(zzaz zzaz, Comparator comparator) {
        super(comparator);
        this.zzd = zzaz;
    }

    @CheckForNull
    public final Object ceiling(Object obj) {
        int zzt = zzt(obj, true);
        if (zzt == this.zzd.size()) {
            return null;
        }
        return this.zzd.get(zzt);
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj != null) {
            try {
                if (Collections.binarySearch(this.zzd, obj, this.zza) >= 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public final boolean containsAll(Collection collection) {
        if (collection instanceof zzbo) {
            collection = ((zzbo) collection).zza();
        }
        if (!zzca.zza(this.zza, collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        zzcc zzk = this.zzd.listIterator(0);
        Iterator it = collection.iterator();
        if (!zzk.hasNext()) {
            return false;
        }
        Object next = it.next();
        Object next2 = zzk.next();
        while (true) {
            try {
                int compare = this.zza.compare(next2, next);
                if (compare >= 0) {
                    if (compare != 0) {
                        break;
                    } else if (!it.hasNext()) {
                        return true;
                    } else {
                        next = it.next();
                    }
                } else if (!zzk.hasNext()) {
                    return false;
                } else {
                    next2 = zzk.next();
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038 A[Catch:{ ClassCastException | NoSuchElementException -> 0x004c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@javax.annotation.CheckForNull java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r7 instanceof java.util.Set
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            java.util.Set r7 = (java.util.Set) r7
            com.google.android.gms.internal.fido.zzaz r1 = r6.zzd
            int r1 = r1.size()
            int r3 = r7.size()
            if (r1 == r3) goto L_0x0019
            return r2
        L_0x0019:
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto L_0x0020
            return r0
        L_0x0020:
            java.util.Comparator r1 = r6.zza
            boolean r1 = com.google.android.gms.internal.fido.zzca.zza(r1, r7)
            if (r1 == 0) goto L_0x004d
            java.util.Iterator r7 = r7.iterator()
            com.google.android.gms.internal.fido.zzaz r1 = r6.zzd     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            com.google.android.gms.internal.fido.zzcc r1 = r1.listIterator(r2)     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
        L_0x0032:
            boolean r3 = r1.hasNext()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            if (r3 == 0) goto L_0x004b
            java.lang.Object r3 = r1.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            java.lang.Object r4 = r7.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            if (r4 == 0) goto L_0x004a
            java.util.Comparator r5 = r6.zza     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            int r3 = r5.compare(r3, r4)     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            if (r3 == 0) goto L_0x0032
        L_0x004a:
            return r2
        L_0x004b:
            return r0
        L_0x004c:
            return r2
        L_0x004d:
            boolean r6 = r6.containsAll(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzbu.equals(java.lang.Object):boolean");
    }

    public final Object first() {
        if (!isEmpty()) {
            return this.zzd.get(0);
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public final Object floor(Object obj) {
        int zzs = zzs(obj, true) - 1;
        if (zzs == -1) {
            return null;
        }
        return this.zzd.get(zzs);
    }

    @CheckForNull
    public final Object higher(Object obj) {
        int zzt = zzt(obj, false);
        if (zzt == this.zzd.size()) {
            return null;
        }
        return this.zzd.get(zzt);
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzd.listIterator(0);
    }

    public final Object last() {
        if (!isEmpty()) {
            zzaz zzaz = this.zzd;
            return zzaz.get(zzaz.size() - 1);
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public final Object lower(Object obj) {
        int zzs = zzs(obj, false) - 1;
        if (zzs == -1) {
            return null;
        }
        return this.zzd.get(zzs);
    }

    public final int size() {
        return this.zzd.size();
    }

    public final int zza(Object[] objArr, int i3) {
        return this.zzd.zza(objArr, 0);
    }

    public final int zzb() {
        return this.zzd.zzb();
    }

    public final int zzc() {
        return this.zzd.zzc();
    }

    public final zzcb zzd() {
        return this.zzd.listIterator(0);
    }

    @CheckForNull
    public final Object[] zze() {
        return this.zzd.zze();
    }

    public final zzbi zzf() {
        Comparator reverseOrder = Collections.reverseOrder(this.zza);
        return isEmpty() ? zzbi.zzq(reverseOrder) : new zzbu(this.zzd.zzf(), reverseOrder);
    }

    public final zzaz zzi() {
        return this.zzd;
    }

    public final zzbi zzm(Object obj, boolean z2) {
        return zzu(0, zzs(obj, z2));
    }

    public final zzbi zzo(Object obj, boolean z2, Object obj2, boolean z3) {
        return zzp(obj, z2).zzm(obj2, z3);
    }

    public final zzbi zzp(Object obj, boolean z2) {
        return zzu(zzt(obj, z2), this.zzd.size());
    }

    /* renamed from: zzr */
    public final zzcb descendingIterator() {
        return this.zzd.zzf().listIterator(0);
    }

    public final int zzs(Object obj, boolean z2) {
        zzaz zzaz = this.zzd;
        obj.getClass();
        int binarySearch = Collections.binarySearch(zzaz, obj, this.zza);
        return binarySearch >= 0 ? z2 ? binarySearch + 1 : binarySearch : ~binarySearch;
    }

    public final int zzt(Object obj, boolean z2) {
        zzaz zzaz = this.zzd;
        obj.getClass();
        int binarySearch = Collections.binarySearch(zzaz, obj, this.zza);
        return binarySearch >= 0 ? z2 ? binarySearch : binarySearch + 1 : ~binarySearch;
    }

    public final zzbu zzu(int i3, int i4) {
        if (i3 == 0) {
            if (i4 == this.zzd.size()) {
                return this;
            }
            i3 = 0;
        }
        return i3 < i4 ? new zzbu(this.zzd.subList(i3, i4), this.zza) : zzbi.zzq(this.zza);
    }
}
