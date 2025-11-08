package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzo;

final class zzy extends zzo {
    final /* synthetic */ GoogleMap.OnCameraIdleListener zza;

    public zzy(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.zza = onCameraIdleListener;
    }

    public final void zzb() {
        this.zza.onCameraIdle();
    }
}
