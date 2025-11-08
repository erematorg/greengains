package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaq;
import com.google.android.gms.maps.model.LatLng;

final class zzaa extends zzaq {
    final /* synthetic */ GoogleMap.OnMapLongClickListener zza;

    public zzaa(GoogleMap googleMap, GoogleMap.OnMapLongClickListener onMapLongClickListener) {
        this.zza = onMapLongClickListener;
    }

    public final void zzb(LatLng latLng) {
        this.zza.onMapLongClick(latLng);
    }
}
