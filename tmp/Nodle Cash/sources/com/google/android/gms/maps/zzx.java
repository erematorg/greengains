package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzq;

final class zzx extends zzq {
    final /* synthetic */ GoogleMap.OnCameraMoveCanceledListener zza;

    public zzx(GoogleMap googleMap, GoogleMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.zza = onCameraMoveCanceledListener;
    }

    public final void zzb() {
        this.zza.onCameraMoveCanceled();
    }
}
