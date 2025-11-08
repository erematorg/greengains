package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.CheckForNull;

abstract class zzbr extends zzbv implements Serializable {
    /* access modifiers changed from: private */
    public final transient Map zza;
    /* access modifiers changed from: private */
    public transient int zzb;

    public zzbr(Map map) {
        zzaz.zzd(map.isEmpty());
        this.zza = map;
    }

    public static /* bridge */ /* synthetic */ void zzr(zzbr zzbr, Object obj) {
        Object obj2;
        try {
            obj2 = zzbr.zza.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            zzbr.zzb -= size;
        }
    }

    public abstract Collection zza();

    public Collection zzb() {
        throw null;
    }

    public Collection zzc(Collection collection) {
        throw null;
    }

    public Collection zzd(Object obj, Collection collection) {
        throw null;
    }

    public final int zzh() {
        return this.zzb;
    }

    public final Collection zzi() {
        return this instanceof zzdq ? new zzbu(this) : new zzbt(this);
    }

    public final Collection zzj(Object obj) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            collection = zza();
        }
        return zzd(obj, collection);
    }

    public final Collection zzk(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zza.remove(obj);
        if (collection == null) {
            return zzb();
        }
        Collection zza2 = zza();
        zza2.addAll(collection);
        this.zzb -= collection.size();
        collection.clear();
        return zzc(zza2);
    }

    public final Iterator zzl() {
        return new zzbf(this);
    }

    public final List zzm(Object obj, List list, @CheckForNull zzbo zzbo) {
        return list instanceof RandomAccess ? new zzbm(this, obj, list, zzbo) : new zzbq(this, obj, list, zzbo);
    }

    public final Map zzo() {
        return new zzbi(this, this.zza);
    }

    public final Set zzp() {
        return new zzbl(this, this.zza);
    }

    public final void zzs() {
        for (Collection clear : this.zza.values()) {
            clear.clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    public final boolean zzt(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            Collection zza2 = zza();
            if (zza2.add(obj2)) {
                this.zzb++;
                this.zza.put(obj, zza2);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(obj2)) {
            return false;
        } else {
            this.zzb++;
            return true;
        }
    }
}
