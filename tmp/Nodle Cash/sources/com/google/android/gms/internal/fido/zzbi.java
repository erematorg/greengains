package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

public abstract class zzbi extends zzbj implements NavigableSet, zzbz {
    final transient Comparator zza;
    @CheckForNull
    transient zzbi zzb;

    public zzbi(Comparator comparator) {
        this.zza = comparator;
    }

    public static zzbu zzq(Comparator comparator) {
        if (zzbp.zza.equals(comparator)) {
            return zzbu.zzc;
        }
        int i3 = zzaz.zzd;
        return new zzbu(zzbs.zza, comparator);
    }

    @CheckForNull
    public Object ceiling(Object obj) {
        obj.getClass();
        return zzbk.zza(zzp(obj, true), (Object) null);
    }

    public final Comparator comparator() {
        return this.zza;
    }

    public Object first() {
        return iterator().next();
    }

    @CheckForNull
    public Object floor(Object obj) {
        obj.getClass();
        return zzbm.zza(zzm(obj, true).descendingIterator(), (Object) null);
    }

    public final /* synthetic */ SortedSet headSet(Object obj) {
        obj.getClass();
        return zzm(obj, false);
    }

    @CheckForNull
    public Object higher(Object obj) {
        obj.getClass();
        return zzbk.zza(zzp(obj, false), (Object) null);
    }

    public Object last() {
        return descendingIterator().next();
    }

    @CheckForNull
    public Object lower(Object obj) {
        obj.getClass();
        return zzbm.zza(zzm(obj, false).descendingIterator(), (Object) null);
    }

    @CheckForNull
    @Deprecated
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    public final /* synthetic */ SortedSet tailSet(Object obj) {
        obj.getClass();
        return zzp(obj, true);
    }

    /* renamed from: zzd */
    public abstract zzcb iterator();

    public abstract zzbi zzf();

    /* renamed from: zzl */
    public final zzbi descendingSet() {
        zzbi zzbi = this.zzb;
        if (zzbi != null) {
            return zzbi;
        }
        zzbi zzf = zzf();
        this.zzb = zzf;
        zzf.zzb = this;
        return zzf;
    }

    public abstract zzbi zzm(Object obj, boolean z2);

    /* renamed from: zzn */
    public final zzbi subSet(Object obj, boolean z2, Object obj2, boolean z3) {
        obj.getClass();
        obj2.getClass();
        zzap.zzc(this.zza.compare(obj, obj2) <= 0);
        return zzo(obj, z2, obj2, z3);
    }

    public abstract zzbi zzo(Object obj, boolean z2, Object obj2, boolean z3);

    public abstract zzbi zzp(Object obj, boolean z2);

    /* renamed from: zzr */
    public abstract zzcb descendingIterator();

    public final /* synthetic */ NavigableSet headSet(Object obj, boolean z2) {
        obj.getClass();
        return zzm(obj, z2);
    }

    public final /* synthetic */ NavigableSet tailSet(Object obj, boolean z2) {
        obj.getClass();
        return zzp(obj, z2);
    }
}
