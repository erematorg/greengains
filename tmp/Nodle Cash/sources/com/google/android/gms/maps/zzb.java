package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaw;
import com.google.android.gms.maps.model.Marker;

final class zzb extends zzaw {
    final /* synthetic */ GoogleMap.OnMarkerDragListener zza;

    public zzb(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        this.zza = onMarkerDragListener;
    }

    public final void zzb(zzah zzah) {
        this.zza.onMarkerDrag(new Marker(zzah));
    }

    public final void zzc(zzah zzah) {
        this.zza.onMarkerDragEnd(new Marker(zzah));
    }

    public final void zzd(zzah zzah) {
        this.zza.onMarkerDragStart(new Marker(zzah));
    }
}
