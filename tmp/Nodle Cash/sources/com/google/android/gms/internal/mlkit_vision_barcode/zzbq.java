package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;

class zzbq extends zzbo implements List {
    final /* synthetic */ zzbr zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbq(zzbr zzbr, Object obj, @CheckForNull List list, zzbo zzbo) {
        super(zzbr, obj, list, zzbo);
        this.zzf = zzbr;
    }

    public final void add(int i3, Object obj) {
        zzb();
        boolean isEmpty = this.zzb.isEmpty();
        ((List) this.zzb).add(i3, obj);
        zzbr zzbr = this.zzf;
        zzbr.zzb = zzbr.zzb + 1;
        if (isEmpty) {
            zza();
        }
    }

    public final boolean addAll(int i3, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.zzb).addAll(i3, collection);
        if (addAll) {
            int size2 = this.zzb.size();
            zzbr zzbr = this.zzf;
            zzbr.zzb = zzbr.zzb + (size2 - size);
            if (size == 0) {
                zza();
                return true;
            }
        }
        return addAll;
    }

    public final Object get(int i3) {
        zzb();
        return ((List) this.zzb).get(i3);
    }

    public final int indexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).indexOf(obj);
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).lastIndexOf(obj);
    }

    public final ListIterator listIterator() {
        zzb();
        return new zzbp(this);
    }

    public final Object remove(int i3) {
        zzb();
        Object remove = ((List) this.zzb).remove(i3);
        zzbr zzbr = this.zzf;
        zzbr.zzb = zzbr.zzb - 1;
        zzc();
        return remove;
    }

    public final Object set(int i3, Object obj) {
        zzb();
        return ((List) this.zzb).set(i3, obj);
    }

    public final List subList(int i3, int i4) {
        zzb();
        List subList = ((List) this.zzb).subList(i3, i4);
        zzbo zzbo = this.zzc;
        if (zzbo == null) {
            zzbo = this;
        }
        return this.zzf.zzm(this.zza, subList, zzbo);
    }

    public final ListIterator listIterator(int i3) {
        zzb();
        return new zzbp(this, i3);
    }
}
