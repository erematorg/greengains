package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

final class zzcf extends AbstractSet {
    final /* synthetic */ zzci zza;

    public zzcf(zzci zzci) {
        this.zza = zzci;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.containsKey(obj);
    }

    public final Iterator iterator() {
        zzci zzci = this.zza;
        Map zzl = zzci.zzl();
        return zzl != null ? zzl.keySet().iterator() : new zzbz(zzci);
    }

    public final boolean remove(@CheckForNull Object obj) {
        Map zzl = this.zza.zzl();
        return zzl != null ? zzl.keySet().remove(obj) : this.zza.zzy(obj) != zzci.zzd;
    }

    public final int size() {
        return this.zza.size();
    }
}
