package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;

final class zzv extends zzp {
    final /* synthetic */ zzw zza;

    public zzv(zzw zzw) {
        this.zza = zzw;
    }

    public final /* bridge */ /* synthetic */ Object get(int i3) {
        zzf.zza(i3, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
        zzw zzw = this.zza;
        int i4 = i3 + i3;
        Object obj = zzw.zzb[i4];
        obj.getClass();
        Object obj2 = zzw.zzb[i4 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }
}
