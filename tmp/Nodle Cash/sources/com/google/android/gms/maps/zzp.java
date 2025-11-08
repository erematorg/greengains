package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.Polyline;

final class zzp extends zzbi {
    final /* synthetic */ GoogleMap.OnPolylineClickListener zza;

    public zzp(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.zza = onPolylineClickListener;
    }

    public final void zzb(zzap zzap) {
        this.zza.onPolylineClick(new Polyline(zzap));
    }
}
