package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzs;

final class zzw extends zzs {
    final /* synthetic */ GoogleMap.OnCameraMoveListener zza;

    public zzw(GoogleMap googleMap, GoogleMap.OnCameraMoveListener onCameraMoveListener) {
        this.zza = onCameraMoveListener;
    }

    public final void zzb() {
        this.zza.onCameraMove();
    }
}
