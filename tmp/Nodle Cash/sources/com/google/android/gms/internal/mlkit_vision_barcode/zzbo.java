package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.annotation.CheckForNull;

class zzbo extends AbstractCollection {
    final Object zza;
    Collection zzb;
    @CheckForNull
    final zzbo zzc;
    @CheckForNull
    final Collection zzd;
    final /* synthetic */ zzbr zze;

    public zzbo(zzbr zzbr, Object obj, @CheckForNull Collection collection, zzbo zzbo) {
        this.zze = zzbr;
        this.zza = obj;
        this.zzb = collection;
        this.zzc = zzbo;
        this.zzd = zzbo == null ? null : zzbo.zzb;
    }

    public final boolean add(Object obj) {
        zzb();
        boolean isEmpty = this.zzb.isEmpty();
        boolean add = this.zzb.add(obj);
        if (add) {
            zzbr zzbr = this.zze;
            zzbr.zzb = zzbr.zzb + 1;
            if (isEmpty) {
                zza();
                return true;
            }
        }
        return add;
    }

    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.zzb.addAll(collection);
        if (addAll) {
            int size2 = this.zzb.size();
            zzbr zzbr = this.zze;
            zzbr.zzb = zzbr.zzb + (size2 - size);
            if (size == 0) {
                zza();
                return true;
            }
        }
        return addAll;
    }

    public final void clear() {
        int size = size();
        if (size != 0) {
            this.zzb.clear();
            zzbr zzbr = this.zze;
            zzbr.zzb = zzbr.zzb - size;
            zzc();
        }
    }

    public final boolean contains(@CheckForNull Object obj) {
        zzb();
        return this.zzb.contains(obj);
    }

    public final boolean containsAll(Collection collection) {
        zzb();
        return this.zzb.containsAll(collection);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        zzb();
        return this.zzb.equals(obj);
    }

    public final int hashCode() {
        zzb();
        return this.zzb.hashCode();
    }

    public final Iterator iterator() {
        zzb();
        return new zzbn(this);
    }

    public final boolean remove(@CheckForNull Object obj) {
        zzb();
        boolean remove = this.zzb.remove(obj);
        if (remove) {
            zzbr zzbr = this.zze;
            zzbr.zzb = zzbr.zzb - 1;
            zzc();
        }
        return remove;
    }

    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.zzb.removeAll(collection);
        if (removeAll) {
            int size2 = this.zzb.size();
            zzbr zzbr = this.zze;
            zzbr.zzb = zzbr.zzb + (size2 - size);
            zzc();
        }
        return removeAll;
    }

    public final boolean retainAll(Collection collection) {
        collection.getClass();
        int size = size();
        boolean retainAll = this.zzb.retainAll(collection);
        if (retainAll) {
            int size2 = this.zzb.size();
            zzbr zzbr = this.zze;
            zzbr.zzb = zzbr.zzb + (size2 - size);
            zzc();
        }
        return retainAll;
    }

    public final int size() {
        zzb();
        return this.zzb.size();
    }

    public final String toString() {
        zzb();
        return this.zzb.toString();
    }

    public final void zza() {
        zzbo zzbo = this.zzc;
        if (zzbo != null) {
            zzbo.zza();
            return;
        }
        zzbr zzbr = this.zze;
        zzbr.zza.put(this.zza, this.zzb);
    }

    public final void zzb() {
        zzbo zzbo = this.zzc;
        if (zzbo != null) {
            zzbo.zzb();
            zzbo zzbo2 = this.zzc;
            if (zzbo2.zzb != this.zzd) {
                throw new ConcurrentModificationException();
            }
        } else if (this.zzb.isEmpty()) {
            zzbr zzbr = this.zze;
            Collection collection = (Collection) zzbr.zza.get(this.zza);
            if (collection != null) {
                this.zzb = collection;
            }
        }
    }

    public final void zzc() {
        zzbo zzbo = this.zzc;
        if (zzbo != null) {
            zzbo.zzc();
        } else if (this.zzb.isEmpty()) {
            zzbr zzbr = this.zze;
            zzbr.zza.remove(this.zza);
        }
    }
}
