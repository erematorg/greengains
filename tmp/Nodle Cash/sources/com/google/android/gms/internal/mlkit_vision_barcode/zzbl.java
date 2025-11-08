package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

final class zzbl extends zzdc {
    final /* synthetic */ zzbr zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbl(zzbr zzbr, Map map) {
        super(map);
        this.zza = zzbr;
    }

    public final void clear() {
        zzcx.zza(iterator());
    }

    public final boolean containsAll(Collection collection) {
        return this.zzb.keySet().containsAll(collection);
    }

    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zzb.keySet().equals(obj);
    }

    public final int hashCode() {
        return this.zzb.keySet().hashCode();
    }

    public final Iterator iterator() {
        return new zzbk(this, this.zzb.entrySet().iterator());
    }

    public final boolean remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zzb.remove(obj);
        if (collection == null) {
            return false;
        }
        int size = collection.size();
        collection.clear();
        zzbr zzbr = this.zza;
        zzbr.zzb = zzbr.zzb - size;
        return size > 0;
    }
}
