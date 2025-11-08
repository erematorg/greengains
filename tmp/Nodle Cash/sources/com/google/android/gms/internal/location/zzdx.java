package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

final class zzdx implements ListenerHolder.Notifier {
    final /* synthetic */ zzdy zza;

    public zzdx(zzdy zzdy) {
        this.zza = zzdy;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        LocationListener locationListener = (LocationListener) obj;
        this.zza.zzg().zzc();
    }

    public final void onNotifyListenerFailed() {
    }
}
