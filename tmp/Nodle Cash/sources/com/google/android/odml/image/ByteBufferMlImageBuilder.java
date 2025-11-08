package com.google.android.odml.image;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;

public class ByteBufferMlImageBuilder {
    private final ByteBuffer zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private int zze = 0;
    private Rect zzf;

    public ByteBufferMlImageBuilder(@NonNull ByteBuffer byteBuffer, int i3, int i4, int i5) {
        this.zza = byteBuffer;
        this.zzb = i3;
        this.zzc = i4;
        this.zzd = i5;
        this.zzf = new Rect(0, 0, i3, i4);
    }

    @NonNull
    public MlImage build() {
        return new MlImage(new zzf(this.zza, this.zzd), this.zze, this.zzf, 0, this.zzb, this.zzc);
    }

    @NonNull
    public ByteBufferMlImageBuilder setRotation(int i3) {
        MlImage.zzc(i3);
        this.zze = i3;
        return this;
    }
}
