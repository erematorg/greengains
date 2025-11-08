package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

final class zzdt implements ListenerHolder.Notifier {
    final /* synthetic */ LocationAvailability zza;

    public zzdt(zzdv zzdv, LocationAvailability locationAvailability) {
        this.zza = locationAvailability;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((LocationCallback) obj).onLocationAvailability(this.zza);
    }

    public final void onNotifyListenerFailed() {
    }
}
