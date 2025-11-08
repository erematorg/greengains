package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

final class zzbi extends zzde {
    final transient Map zza;
    final /* synthetic */ zzbr zzb;

    public zzbi(zzbr zzbr, Map map) {
        this.zzb = zzbr;
        this.zza = map;
    }

    public final void clear() {
        zzbr zzbr = this.zzb;
        if (this.zza == zzbr.zza) {
            zzbr.zzs();
        } else {
            zzcx.zza(new zzbh(this));
        }
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return zzdf.zzb(this.zza, obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zza.equals(obj);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Set keySet() {
        return this.zzb.zzw();
    }

    @CheckForNull
    public final /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zza.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection zza2 = this.zzb.zza();
        zza2.addAll(collection);
        zzbr zzbr = this.zzb;
        zzbr.zzb = zzbr.zzb - collection.size();
        collection.clear();
        return zza2;
    }

    public final int size() {
        return this.zza.size();
    }

    public final String toString() {
        return this.zza.toString();
    }

    @CheckForNull
    /* renamed from: zza */
    public final Collection get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzdf.zza(this.zza, obj);
        if (collection == null) {
            return null;
        }
        return this.zzb.zzd(obj, collection);
    }

    public final Set zzb() {
        return new zzbg(this);
    }
}
