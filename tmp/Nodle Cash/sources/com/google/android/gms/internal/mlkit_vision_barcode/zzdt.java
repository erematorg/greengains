package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;

abstract class zzdt implements Iterator {
    final Iterator zza;

    public zzdt(Iterator it) {
        it.getClass();
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final Object next() {
        return zza(this.zza.next());
    }

    public final void remove() {
        this.zza.remove();
    }

    public abstract Object zza(Object obj);
}
