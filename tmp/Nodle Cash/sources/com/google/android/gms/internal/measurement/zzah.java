package com.google.android.gms.internal.measurement;

import A.a;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzah implements Iterator<zzaq> {
    private int zza = 0;
    private final /* synthetic */ zzaf zzb;

    public zzah(zzaf zzaf) {
        this.zzb = zzaf;
    }

    public final boolean hasNext() {
        return this.zza < this.zzb.zzb();
    }

    public final /* synthetic */ Object next() {
        if (this.zza < this.zzb.zzb()) {
            zzaf zzaf = this.zzb;
            int i3 = this.zza;
            this.zza = i3 + 1;
            return zzaf.zza(i3);
        }
        throw new NoSuchElementException(a.k("Out of bounds index: ", this.zza));
    }
}
