package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Objects;

final class zzbs extends zzbi {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    public zzbs(Object[] objArr, int i3, int i4) {
        this.zza = objArr;
        this.zzb = i3;
        this.zzc = i4;
    }

    public final Object get(int i3) {
        zzba.zza(i3, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.zza[i3 + i3 + this.zzb];
        Objects.requireNonNull(obj);
        return obj;
    }

    public final int size() {
        return this.zzc;
    }
}
