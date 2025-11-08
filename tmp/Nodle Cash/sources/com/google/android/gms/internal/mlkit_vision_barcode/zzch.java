package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

final class zzch extends AbstractCollection {
    final /* synthetic */ zzci zza;

    public zzch(zzci zzci) {
        this.zza = zzci;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final Iterator iterator() {
        zzci zzci = this.zza;
        Map zzl = zzci.zzl();
        return zzl != null ? zzl.values().iterator() : new zzcb(zzci);
    }

    public final int size() {
        return this.zza.size();
    }
}
