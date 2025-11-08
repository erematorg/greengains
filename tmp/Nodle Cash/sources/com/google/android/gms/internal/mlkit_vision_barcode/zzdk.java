package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

final class zzdk extends zzcs {
    static final zzcs zza = new zzdk(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    public zzdk(Object[] objArr, int i3) {
        this.zzb = objArr;
        this.zzc = i3;
    }

    public final Object get(int i3) {
        zzaz.zza(i3, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zzb[i3];
        Objects.requireNonNull(obj);
        return obj;
    }

    public final int size() {
        return this.zzc;
    }

    public final int zza(Object[] objArr, int i3) {
        System.arraycopy(this.zzb, 0, objArr, i3, this.zzc);
        return i3 + this.zzc;
    }

    public final int zzb() {
        return this.zzc;
    }

    public final int zzc() {
        return 0;
    }

    public final Object[] zze() {
        return this.zzb;
    }
}
