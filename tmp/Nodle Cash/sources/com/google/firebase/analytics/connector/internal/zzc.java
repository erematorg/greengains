package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashSet;
import java.util.Set;

public final class zzc implements zza {
    Set<String> zza = new HashSet();
    /* access modifiers changed from: private */
    public AnalyticsConnector.AnalyticsConnectorListener zzb;
    private AppMeasurementSdk zzc;
    private zzf zzd;

    public zzc(AppMeasurementSdk appMeasurementSdk, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzb = analyticsConnectorListener;
        this.zzc = appMeasurementSdk;
        zzf zzf = new zzf(this);
        this.zzd = zzf;
        this.zzc.registerOnMeasurementEventListener(zzf);
    }

    public final void zzb() {
        this.zza.clear();
    }

    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.zzb;
    }

    public final void zza(Set<String> set) {
        this.zza.clear();
        Set<String> set2 = this.zza;
        HashSet hashSet = new HashSet();
        for (String next : set) {
            if (hashSet.size() >= 50) {
                break;
            } else if (zzd.zzc(next) && zzd.zzd(next)) {
                String zzb2 = zzd.zzb(next);
                Preconditions.checkNotNull(zzb2);
                hashSet.add(zzb2);
            }
        }
        set2.addAll(hashSet);
    }
}
