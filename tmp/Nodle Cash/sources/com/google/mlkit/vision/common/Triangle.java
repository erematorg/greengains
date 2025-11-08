package com.google.mlkit.vision.common;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_common.zzp;
import java.util.List;

public class Triangle<T> {
    private final zzp zza;

    @KeepForSdk
    public Triangle(@NonNull T t2, @NonNull T t3, @NonNull T t4) {
        this.zza = zzp.zzj(t2, t3, t4);
    }

    @NonNull
    public List<T> getAllPoints() {
        return this.zza;
    }
}
