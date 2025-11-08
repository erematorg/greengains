package com.google.android.odml.image;

import java.nio.ByteBuffer;

final class zzf implements zzg {
    private final ByteBuffer zza;
    private final ImageProperties zzb;

    public zzf(ByteBuffer byteBuffer, int i3) {
        this.zza = byteBuffer;
        zzb zzb2 = new zzb();
        zzb2.zzb(2);
        zzb2.zza(i3);
        this.zzb = zzb2.zzc();
    }

    public final ByteBuffer zza() {
        return this.zza;
    }

    public final ImageProperties zzb() {
        return this.zzb;
    }

    public final void zzc() {
    }
}
