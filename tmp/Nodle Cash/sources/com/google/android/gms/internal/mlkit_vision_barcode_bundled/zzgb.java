package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzgb implements Iterator {
    private final ArrayDeque zza;
    private zzdd zzb;

    public /* synthetic */ zzgb(zzdf zzdf, zzga zzga) {
        if (zzdf instanceof zzgd) {
            zzgd zzgd = (zzgd) zzdf;
            ArrayDeque arrayDeque = new ArrayDeque(zzgd.zzf());
            this.zza = arrayDeque;
            arrayDeque.push(zzgd);
            this.zzb = zzb(zzgd.zzd);
            return;
        }
        this.zza = null;
        this.zzb = (zzdd) zzdf;
    }

    private final zzdd zzb(zzdf zzdf) {
        while (zzdf instanceof zzgd) {
            zzgd zzgd = (zzgd) zzdf;
            this.zza.push(zzgd);
            zzdf = zzgd.zzd;
        }
        return (zzdd) zzdf;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: zza */
    public final zzdd next() {
        zzdd zzdd;
        zzdd zzdd2 = this.zzb;
        if (zzdd2 != null) {
            do {
                ArrayDeque arrayDeque = this.zza;
                zzdd = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    this.zzb = zzdd;
                } else {
                    zzdd = zzb(((zzgd) this.zza.pop()).zze);
                }
            } while (zzdd.zzd() == 0);
            this.zzb = zzdd;
            return zzdd2;
        }
        throw new NoSuchElementException();
    }
}
