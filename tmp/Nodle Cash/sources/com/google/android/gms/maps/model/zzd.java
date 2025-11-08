package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzaq;
import com.google.android.gms.internal.maps.zzp;
import com.google.android.gms.maps.model.FeatureLayer;

final class zzd extends zzaq {
    final /* synthetic */ FeatureLayer.StyleFactory zza;

    public zzd(FeatureLayer featureLayer, FeatureLayer.StyleFactory styleFactory) {
        this.zza = styleFactory;
    }

    @Nullable
    public final FeatureStyle zzb(zzp zzp) {
        Feature zza2 = Feature.zza(zzp);
        if (zza2 == null) {
            return null;
        }
        return this.zza.getStyle(zza2);
    }
}
