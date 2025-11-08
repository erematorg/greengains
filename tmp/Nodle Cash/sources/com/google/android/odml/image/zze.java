package com.google.android.odml.image;

import android.graphics.Bitmap;

final class zze implements zzg {
    private final Bitmap zza;
    private final ImageProperties zzb;

    public zze(Bitmap bitmap) {
        this.zza = bitmap;
        zzb zzb2 = new zzb();
        int i3 = zzd.zza[bitmap.getConfig().ordinal()];
        zzb2.zza(i3 != 1 ? i3 != 2 ? 0 : 1 : 8);
        zzb2.zzb(1);
        this.zzb = zzb2.zzc();
    }

    public final Bitmap zza() {
        return this.zza;
    }

    public final ImageProperties zzb() {
        return this.zzb;
    }

    public final void zzc() {
        this.zza.recycle();
    }
}
