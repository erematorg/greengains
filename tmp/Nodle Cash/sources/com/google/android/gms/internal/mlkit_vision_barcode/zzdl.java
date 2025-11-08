package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
import java.util.Objects;

final class zzdl extends zzcs {
    final /* synthetic */ zzdm zza;

    public zzdl(zzdm zzdm) {
        this.zza = zzdm;
    }

    public final /* bridge */ /* synthetic */ Object get(int i3) {
        zzaz.zza(i3, this.zza.zzc, FirebaseAnalytics.Param.INDEX);
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
