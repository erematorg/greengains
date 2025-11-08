package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
import java.util.Objects;

final class zzam extends zzaf {
    final /* synthetic */ zzan zza;

    public zzam(zzan zzan) {
        this.zza = zzan;
    }

    public final /* bridge */ /* synthetic */ Object get(int i3) {
        zzt.zza(i3, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        int i4 = i3 + i3;
        Object obj = this.zza.zzb[i4];
        Objects.requireNonNull(obj);
        Object obj2 = this.zza.zzb[i4 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }
}
