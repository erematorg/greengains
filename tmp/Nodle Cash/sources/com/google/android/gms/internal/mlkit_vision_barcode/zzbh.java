package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

final class zzbh implements Iterator {
    final Iterator zza;
    @CheckForNull
    Collection zzb;
    final /* synthetic */ zzbi zzc;

    public zzbh(zzbi zzbi) {
        this.zzc = zzbi;
        this.zza = zzbi.zza.entrySet().iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        this.zzb = (Collection) entry.getValue();
        Object key = entry.getKey();
        return new zzco(key, this.zzc.zzb.zzd(key, (Collection) entry.getValue()));
    }

    public final void remove() {
        zzaz.zzf(this.zzb != null, "no calls to next() since the last call to remove()");
        this.zza.remove();
        zzbr zzbr = this.zzc.zzb;
        zzbr.zzb = zzbr.zzb - this.zzb.size();
        this.zzb.clear();
        this.zzb = null;
    }
}
