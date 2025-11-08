package com.google.android.gms.internal.fido;

import java.util.NoSuchElementException;

final class zzcq extends zzcs {
    final /* synthetic */ zzcz zza;
    private int zzb = 0;
    private final int zzc;

    public zzcq(zzcz zzcz) {
        this.zza = zzcz;
        this.zzc = zzcz.zzd();
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
