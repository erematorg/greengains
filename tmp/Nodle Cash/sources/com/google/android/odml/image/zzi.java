package com.google.android.odml.image;

import android.media.Image;
import androidx.annotation.RequiresApi;

@RequiresApi(19)
final class zzi implements zzg {
    private final Image zza;
    private final ImageProperties zzb;

    public zzi(Image image) {
        this.zza = image;
        zzb zzb2 = new zzb();
        zzb2.zzb(3);
        int format = image.getFormat();
        zzb2.zza(format == 42 ? 1 : format == 41 ? 2 : format != 35 ? format != 256 ? 0 : 9 : 7);
        this.zzb = zzb2.zzc();
    }

    public final Image zza() {
        return this.zza;
    }

    public final ImageProperties zzb() {
        return this.zzb;
    }

    public final void zzc() {
        this.zza.close();
    }
}
