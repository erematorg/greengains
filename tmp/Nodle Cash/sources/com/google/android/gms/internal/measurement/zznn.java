package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zznn implements Iterator {
    private int zza;
    private boolean zzb;
    private Iterator zzc;
    private final /* synthetic */ zzne zzd;

    private final Iterator zza() {
        if (this.zzc == null) {
            this.zzc = this.zzd.zzc.entrySet().iterator();
        }
        return this.zzc;
    }

    public final boolean hasNext() {
        return this.zza + 1 < this.zzd.zzb || (!this.zzd.zzc.isEmpty() && zza().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.zzb = true;
        int i3 = this.zza + 1;
        this.zza = i3;
        return i3 < this.zzd.zzb ? (zznk) this.zzd.zza[this.zza] : (Map.Entry) zza().next();
    }

    public final void remove() {
        if (this.zzb) {
            this.zzb = false;
            this.zzd.zzg();
            if (this.zza < this.zzd.zzb) {
                zzne zzne = this.zzd;
                int i3 = this.zza;
                this.zza = i3 - 1;
                Object unused = zzne.zzb(i3);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    private zznn(zzne zzne) {
        this.zzd = zzne;
        this.zza = -1;
    }
}
