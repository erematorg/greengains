package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

abstract class zzbv implements zzdg {
    @CheckForNull
    private transient Collection zza;
    @CheckForNull
    private transient Set zzb;
    @CheckForNull
    private transient Map zzc;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdg)) {
            return false;
        }
        return zzv().equals(((zzdg) obj).zzv());
    }

    public final int hashCode() {
        return zzv().hashCode();
    }

    public final String toString() {
        return zzv().toString();
    }

    public abstract Collection zzi();

    public abstract Iterator zzl();

    public abstract Map zzo();

    public abstract Set zzp();

    public boolean zzt(Object obj, Object obj2) {
        throw null;
    }

    public final Collection zzu() {
        Collection collection = this.zza;
        if (collection != null) {
            return collection;
        }
        Collection zzi = zzi();
        this.zza = zzi;
        return zzi;
    }

    public final Map zzv() {
        Map map = this.zzc;
        if (map != null) {
            return map;
        }
        Map zzo = zzo();
        this.zzc = zzo;
        return zzo;
    }

    public final Set zzw() {
        Set set = this.zzb;
        if (set != null) {
            return set;
        }
        Set zzp = zzp();
        this.zzb = zzp;
        return zzp;
    }

    public final boolean zzx(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Collection zza2 = ((zzbi) zzv()).get(obj);
        return zza2 != null && zza2.contains(obj2);
    }

    public final boolean zzy(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Collection zza2 = ((zzbi) zzv()).get(obj);
        return zza2 != null && zza2.remove(obj2);
    }
}
