package com.google.android.gms.internal.auth;

import java.util.NoSuchElementException;

final class zzdw extends zzdy {
    final /* synthetic */ zzef zza;
    private int zzb = 0;
    private final int zzc;

    public zzdw(zzef zzef) {
        this.zza = zzef;
        this.zzc = zzef.zzd();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i3 = this.zzb;
        if (i3 < this.zzc) {
            this.zzb = i3 + 1;
            return this.zza.zzb(i3);
        }
        throw new NoSuchElementException();
    }
}
