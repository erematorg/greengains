package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationCallback;

final class zzdu implements ListenerHolder.Notifier {
    final /* synthetic */ zzdv zza;

    public zzdu(zzdv zzdv) {
        this.zza = zzdv;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        LocationCallback locationCallback = (LocationCallback) obj;
        this.zza.zzh().zzc();
    }

    public final void onNotifyListenerFailed() {
    }
}
