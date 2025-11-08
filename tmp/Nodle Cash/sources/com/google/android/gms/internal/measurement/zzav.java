package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzav implements Iterator<zzaq> {
    private int zza = 0;
    private final /* synthetic */ zzas zzb;

    public zzav(zzas zzas) {
        this.zzb = zzas;
    }

    public final boolean hasNext() {
        return this.zza < this.zzb.zza.length();
    }

    public final /* synthetic */ Object next() {
        if (this.zza < this.zzb.zza.length()) {
            int i3 = this.zza;
            this.zza = i3 + 1;
            return new zzas(String.valueOf(i3));
        }
        throw new NoSuchElementException();
    }
}
