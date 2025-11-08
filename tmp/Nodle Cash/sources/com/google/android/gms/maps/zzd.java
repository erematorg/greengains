package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;

final class zzd extends zzag {
    final /* synthetic */ GoogleMap.OnInfoWindowLongClickListener zza;

    public zzd(GoogleMap googleMap, GoogleMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.zza = onInfoWindowLongClickListener;
    }

    public final void zzb(zzah zzah) {
        this.zza.onInfoWindowLongClick(new Marker(zzah));
    }
}
