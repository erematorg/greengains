package com.google.android.gms.internal.fido;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.NoSuchElementException;

abstract class zzar extends zzcc {
    private final int zza;
    private int zzb;

    public zzar(int i3, int i4) {
        zzap.zzb(i4, i3, FirebaseAnalytics.Param.INDEX);
        this.zza = i3;
        this.zzb = i4;
    }

    public final boolean hasNext() {
        return this.zzb < this.zza;
    }

    public final boolean hasPrevious() {
        return this.zzb > 0;
    }

    public final Object next() {
        if (hasNext()) {
            int i3 = this.zzb;
            this.zzb = i3 + 1;
            return zza(i3);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.zzb;
    }

    public final Object previous() {
        if (hasPrevious()) {
            int i3 = this.zzb - 1;
            this.zzb = i3;
            return zza(i3);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.zzb - 1;
    }

    public abstract Object zza(int i3);
}
