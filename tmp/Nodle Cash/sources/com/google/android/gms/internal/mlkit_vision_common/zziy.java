package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.Nullable;
import com.google.firebase.encoders.annotations.Encodable;

@Encodable
public final class zziy {
    private final zzla zza;
    private final zziv zzb;
    private final zziq zzc;

    public /* synthetic */ zziy(zziw zziw, zzix zzix) {
        this.zza = zziw.zza;
        this.zzb = zziw.zzb;
        this.zzc = zziw.zzc;
    }

    @zzai(zza = 50)
    @Nullable
    public final zziq zza() {
        return this.zzc;
    }

    @zzai(zza = 2)
    @Nullable
    public final zziv zzb() {
        return this.zzb;
    }

    @zzai(zza = 1)
    @Nullable
    public final zzla zzc() {
        return this.zza;
    }
}
