package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzae;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.MapCapabilities;

final class zzs extends zzak {
    final /* synthetic */ GoogleMap.OnMapCapabilitiesChangedListener zza;

    public zzs(GoogleMap googleMap, GoogleMap.OnMapCapabilitiesChangedListener onMapCapabilitiesChangedListener) {
        this.zza = onMapCapabilitiesChangedListener;
    }

    public final void zzb(zzae zzae) {
        this.zza.onMapCapabilitiesChanged(new MapCapabilities(zzae));
    }
}
