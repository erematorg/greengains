package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzau implements Iterator<zzaq> {
    private int zza = 0;
    private final /* synthetic */ zzas zzb;

    public zzau(zzas zzas) {
        this.zzb = zzas;
    }

    public final boolean hasNext() {
        return this.zza < this.zzb.zza.length();
    }

    public final /* synthetic */ Object next() {
        if (this.zza < this.zzb.zza.length()) {
            String zza2 = this.zzb.zza;
            int i3 = this.zza;
            this.zza = i3 + 1;
            return new zzas(String.valueOf(zza2.charAt(i3)));
        }
        throw new NoSuchElementException();
    }
}
