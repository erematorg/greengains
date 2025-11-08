package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.DeviceOrientation;
import com.google.android.gms.location.DeviceOrientationListener;

final class zzdp implements ListenerHolder.Notifier {
    final /* synthetic */ DeviceOrientation zza;

    public zzdp(zzdq zzdq, DeviceOrientation deviceOrientation) {
        this.zza = deviceOrientation;
    }

    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        ((DeviceOrientationListener) obj).onDeviceOrientationChanged(this.zza);
    }

    public final void onNotifyListenerFailed() {
    }
}
