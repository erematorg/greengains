package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;

final class zzn extends zzw {
    final /* synthetic */ GoogleMap.OnCircleClickListener zza;

    public zzn(GoogleMap googleMap, GoogleMap.OnCircleClickListener onCircleClickListener) {
        this.zza = onCircleClickListener;
    }

    public final void zzb(zzl zzl) {
        this.zza.onCircleClick(new Circle(zzl));
    }
}
