package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzj;

final class zzt extends zzj {
    final /* synthetic */ LocationSource zza;

    public zzt(GoogleMap googleMap, LocationSource locationSource) {
        this.zza = locationSource;
    }

    public final void activate(zzaj zzaj) {
        this.zza.activate(new zzl(this, zzaj));
    }

    public final void deactivate() {
        this.zza.deactivate();
    }
}
