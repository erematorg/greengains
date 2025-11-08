package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;

final class zzgk implements Iterator {
    final /* synthetic */ zzgo zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    public /* synthetic */ zzgk(zzgo zzgo, zzgj zzgj) {
        this.zza = zzgo;
    }

    private final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    public final boolean hasNext() {
        int i3 = this.zzb + 1;
        zzgo zzgo = this.zza;
        if (i3 < zzgo.zzb) {
            return true;
        }
        if (!zzgo.zzc.isEmpty()) {
            return zza().hasNext();
        }
        return false;
    }

    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i3 = this.zzb + 1;
        this.zzb = i3;
        zzgo zzgo = this.zza;
        return i3 < zzgo.zzb ? (zzgi) zzgo.zza[i3] : (Map.Entry) zza().next();
    }

    public final void remove() {
        if (this.zzc) {
            this.zzc = false;
            this.zza.zzo();
            int i3 = this.zzb;
            zzgo zzgo = this.zza;
            if (i3 < zzgo.zzb) {
                this.zzb = i3 - 1;
                Object unused = zzgo.zzm(i3);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
