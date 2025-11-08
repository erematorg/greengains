package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;
import com.google.android.gms.maps.model.LatLng;

final class zzz extends zzam {
    final /* synthetic */ GoogleMap.OnMapClickListener zza;

    public zzz(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.zza = onMapClickListener;
    }

    public final void zzb(LatLng latLng) {
        this.zza.onMapClick(latLng);
    }
}
