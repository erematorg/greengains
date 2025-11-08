package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzng implements Iterator {
    private int zza;
    private Iterator zzb;
    private final /* synthetic */ zzne zzc;

    private final Iterator zza() {
        if (this.zzb == null) {
            this.zzb = this.zzc.zzf.entrySet().iterator();
        }
        return this.zzb;
    }

    public final boolean hasNext() {
        int i3 = this.zza;
        return (i3 > 0 && i3 <= this.zzc.zzb) || zza().hasNext();
    }

    public final /* synthetic */ Object next() {
        if (zza().hasNext()) {
            return (Map.Entry) zza().next();
        }
        Object[] zze = this.zzc.zza;
        int i3 = this.zza - 1;
        this.zza = i3;
        return (zznk) zze[i3];
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private zzng(zzne zzne) {
        this.zzc = zzne;
        this.zza = zzne.zzb;
    }
}
