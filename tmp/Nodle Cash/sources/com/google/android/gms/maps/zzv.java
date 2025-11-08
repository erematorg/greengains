package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzu;

final class zzv extends zzu {
    final /* synthetic */ GoogleMap.OnCameraMoveStartedListener zza;

    public zzv(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.zza = onCameraMoveStartedListener;
    }

    public final void zzb(int i3) {
        this.zza.onCameraMoveStarted(i3);
    }
}
