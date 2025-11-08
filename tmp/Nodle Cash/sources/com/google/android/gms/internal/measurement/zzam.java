package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzam implements Iterator<zzaq> {
    private final /* synthetic */ Iterator zza;

    public zzam(Iterator it) {
        this.zza = it;
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* synthetic */ Object next() {
        return new zzas((String) this.zza.next());
    }
}
