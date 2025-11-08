package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.model.Marker;

final class zzf extends zzh {
    final /* synthetic */ GoogleMap.InfoWindowAdapter zza;

    public zzf(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        this.zza = infoWindowAdapter;
    }

    public final IObjectWrapper zzb(zzah zzah) {
        return ObjectWrapper.wrap(this.zza.getInfoContents(new Marker(zzah)));
    }

    public final IObjectWrapper zzc(zzah zzah) {
        return ObjectWrapper.wrap(this.zza.getInfoWindow(new Marker(zzah)));
    }
}
