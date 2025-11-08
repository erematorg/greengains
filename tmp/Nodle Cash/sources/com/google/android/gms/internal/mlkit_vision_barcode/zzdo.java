package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

final class zzdo extends zzcs {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc = 1;

    public zzdo(Object[] objArr, int i3, int i4) {
        this.zza = objArr;
        this.zzb = i3;
    }

    public final Object get(int i3) {
        zzaz.zza(i3, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zza[i3 + i3 + this.zzb];
        Objects.requireNonNull(obj);
        return obj;
    }

    public final int size() {
        return this.zzc;
    }
}
