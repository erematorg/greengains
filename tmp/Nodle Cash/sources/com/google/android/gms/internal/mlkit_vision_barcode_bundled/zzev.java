package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;

final class zzev implements Iterator {
    private final Iterator zza;

    public zzev(Iterator it) {
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        return entry.getValue() instanceof zzew ? new zzeu(entry, (zzet) null) : entry;
    }

    public final void remove() {
        this.zza.remove();
    }
}
