package com.google.android.odml.image;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;

public class ByteBufferExtractor {
    private ByteBufferExtractor() {
    }

    @NonNull
    public static ByteBuffer extract(@NonNull MlImage mlImage) {
        zzg zza = mlImage.zza();
        if (zza.zzb().getStorageType() == 2) {
            return ((zzf) zza).zza().asReadOnlyBuffer();
        }
        throw new IllegalArgumentException("Extract ByteBuffer from an MlImage created by objects other than Bytebuffer is not supported");
    }
}
