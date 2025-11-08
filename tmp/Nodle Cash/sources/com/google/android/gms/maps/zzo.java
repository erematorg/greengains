package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzam;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polygon;

final class zzo extends zzbg {
    final /* synthetic */ GoogleMap.OnPolygonClickListener zza;

    public zzo(GoogleMap googleMap, GoogleMap.OnPolygonClickListener onPolygonClickListener) {
        this.zza = onPolygonClickListener;
    }

    public final void zzb(zzam zzam) {
        this.zza.onPolygonClick(new Polygon(zzam));
    }
}
