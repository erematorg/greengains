package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class zzce implements Iterator {
    int zzb;
    int zzc;
    int zzd = -1;
    final /* synthetic */ zzci zze;

    public /* synthetic */ zzce(zzci zzci, zzcd zzcd) {
        this.zze = zzci;
        this.zzb = zzci.zzf;
        this.zzc = zzci.zze();
    }

    private final void zzb() {
        if (this.zze.zzf != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        return this.zzc >= 0;
    }

    public final Object next() {
        zzb();
        if (hasNext()) {
            int i3 = this.zzc;
            this.zzd = i3;
            Object zza = zza(i3);
            this.zzc = this.zze.zzf(this.zzc);
            return zza;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        zzb();
        zzaz.zzf(this.zzd >= 0, "no calls to next() since the last call to remove()");
        this.zzb += 32;
        int i3 = this.zzd;
        zzci zzci = this.zze;
        zzci.remove(zzci.zzg(zzci, i3));
        this.zzc--;
        this.zzd = -1;
    }

    public abstract Object zza(int i3);
}
