package com.google.android.gms.internal.maps;

import java.util.Iterator;
import javax.annotation.CheckForNull;

final class zzbr extends zzbm {
    private final transient zzbl zza;
    private final transient zzbi zzb;

    public zzbr(zzbl zzbl, zzbi zzbi) {
        this.zza = zzbl;
        this.zzb = zzbi;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public final int size() {
        return this.zza.size();
    }

    public final int zza(Object[] objArr, int i3) {
        return this.zzb.zza(objArr, 0);
    }

    public final zzbx zzd() {
        return this.zzb.listIterator(0);
    }
}
