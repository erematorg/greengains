package com.google.android.recaptcha.internal;

import java.util.NoSuchElementException;

final class zzgn extends zzgp {
    final /* synthetic */ zzgw zza;
    private int zzb = 0;
    private final int zzc;

    public zzgn(zzgw zzgw) {
        this.zza = zzgw;
        this.zzc = zzgw.zzd();
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
