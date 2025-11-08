package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzai;
import com.google.android.gms.internal.maps.zzm;
import com.google.android.gms.maps.model.FeatureLayer;

final class zze extends zzai {
    final /* synthetic */ FeatureLayer.OnFeatureClickListener zza;

    public zze(FeatureLayer featureLayer, FeatureLayer.OnFeatureClickListener onFeatureClickListener) {
        this.zza = onFeatureClickListener;
    }

    public final void zzb(zzm zzm) {
        this.zza.onFeatureClick(new FeatureClickEvent(zzm));
    }
}
