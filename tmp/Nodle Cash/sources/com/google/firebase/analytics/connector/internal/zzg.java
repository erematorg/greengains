package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

final class zzg implements AppMeasurementSdk.OnEventListener {
    private final /* synthetic */ zze zza;

    public zzg(zze zze) {
        this.zza = zze;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j2) {
        if (str != null && zzd.zze(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("name", str2);
            bundle2.putLong("timestampInMillis", j2);
            bundle2.putBundle("params", bundle);
            this.zza.zza.onMessageTriggered(3, bundle2);
        }
    }
}
