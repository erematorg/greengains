package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzy;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;

final class zzk extends zzaa {
    final /* synthetic */ GoogleMap.OnIndoorStateChangeListener zza;

    public zzk(GoogleMap googleMap, GoogleMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.zza = onIndoorStateChangeListener;
    }

    public final void zzb() {
        this.zza.onIndoorBuildingFocused();
    }

    public final void zzc(zzy zzy) {
        this.zza.onIndoorLevelActivated(new IndoorBuilding(zzy));
    }
}
