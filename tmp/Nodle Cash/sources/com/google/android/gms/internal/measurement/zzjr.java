package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

final class zzjr extends zzjt {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzjs zzc;

    public zzjr(zzjs zzjs) {
        this.zzc = zzjs;
        this.zzb = zzjs.zzb();
    }

    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    public final byte zza() {
        int i3 = this.zza;
        if (i3 < this.zzb) {
            this.zza = i3 + 1;
            return this.zzc.zzb(i3);
        }
        throw new NoSuchElementException();
    }
}
