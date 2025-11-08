package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.Marker;

final class zze extends zzae {
    final /* synthetic */ GoogleMap.OnInfoWindowCloseListener zza;

    public zze(GoogleMap googleMap, GoogleMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.zza = onInfoWindowCloseListener;
    }

    public final void zzb(zzah zzah) {
        this.zza.onInfoWindowClose(new Marker(zzah));
    }
}
