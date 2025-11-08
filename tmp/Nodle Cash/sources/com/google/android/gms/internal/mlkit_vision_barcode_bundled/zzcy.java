package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;

final class zzcy extends zzcz {
    final /* synthetic */ zzdf zza;
    private int zzb = 0;
    private final int zzc;

    public zzcy(zzdf zzdf) {
        this.zza = zzdf;
        this.zzc = zzdf.zzd();
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
