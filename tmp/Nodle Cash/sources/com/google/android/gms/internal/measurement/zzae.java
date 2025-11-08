package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzae implements Iterator<zzaq> {
    private final /* synthetic */ Iterator zza;
    private final /* synthetic */ Iterator zzb;

    public zzae(zzaf zzaf, Iterator it, Iterator it2) {
        this.zza = it;
        this.zzb = it2;
    }

    public final boolean hasNext() {
        if (this.zza.hasNext()) {
            return true;
        }
        return this.zzb.hasNext();
    }

    public final /* synthetic */ Object next() {
        if (this.zza.hasNext()) {
            return new zzas(((Integer) this.zza.next()).toString());
        }
        if (this.zzb.hasNext()) {
            return new zzas((String) this.zzb.next());
        }
        throw new NoSuchElementException();
    }
}
