package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;

final class zzfx extends zzcz {
    final zzgb zza;
    zzdb zzb = zzb();
    final /* synthetic */ zzgd zzc;

    public zzfx(zzgd zzgd) {
        this.zzc = zzgd;
        this.zza = new zzgb(zzgd, (zzga) null);
    }

    private final zzdb zzb() {
        zzgb zzgb = this.zza;
        if (zzgb.hasNext()) {
            return zzgb.next().iterator();
        }
        return null;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final byte zza() {
        zzdb zzdb = this.zzb;
        if (zzdb != null) {
            byte zza2 = zzdb.zza();
            if (!this.zzb.hasNext()) {
                this.zzb = zzb();
            }
            return zza2;
        }
        throw new NoSuchElementException();
    }
}
