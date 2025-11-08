package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzbz;
import com.google.android.gms.internal.measurement.zzqn;
import com.google.firebase.messaging.Constants;

final class zzhf implements Runnable {
    private final /* synthetic */ zzbz zza;
    private final /* synthetic */ ServiceConnection zzb;
    private final /* synthetic */ zzhc zzc;

    public zzhf(zzhc zzhc, zzbz zzbz, ServiceConnection serviceConnection) {
        this.zza = zzbz;
        this.zzb = serviceConnection;
        this.zzc = zzhc;
    }

    public final void run() {
        zzhc zzhc = this.zzc;
        zzhd zzhd = zzhc.zza;
        String zza2 = zzhc.zzb;
        zzbz zzbz = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        Bundle zza3 = zzhd.zza(zza2, zzbz);
        zzhd.zza.zzl().zzt();
        zzhd.zza.zzy();
        if (zza3 != null) {
            long j2 = zza3.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j2 == 0) {
                zzhd.zza.zzj().zzu().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza3.getString(Constants.INSTALL_REFERRER);
                if (string == null || string.isEmpty()) {
                    zzhd.zza.zzj().zzg().zza("No referrer defined in Install Referrer response");
                } else {
                    zzhd.zza.zzj().zzp().zza("InstallReferrer API result", string);
                    boolean z2 = zzqn.zza() && zzhd.zza.zzf().zza(zzbj.zzct);
                    Bundle zza4 = zzhd.zza.zzt().zza(Uri.parse("?".concat(string)), z2);
                    if (zza4 == null) {
                        zzhd.zza.zzj().zzg().zza("No campaign params defined in Install Referrer result");
                    } else {
                        if (!z2) {
                            String string2 = zza4.getString("medium");
                            if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                                long j3 = zza3.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                                if (j3 == 0) {
                                    zzhd.zza.zzj().zzg().zza("Install Referrer is missing click timestamp for ad campaign");
                                } else {
                                    zza4.putLong("click_timestamp", j3);
                                }
                            }
                        } else if (zza4.containsKey("gclid") || zza4.containsKey("gbraid")) {
                            long j4 = zza3.getLong("referrer_click_timestamp_server_seconds", 0) * 1000;
                            if (j4 > 0) {
                                zza4.putLong("click_timestamp", j4);
                            }
                        }
                        if (j2 == zzhd.zza.zzn().zzd.zza()) {
                            zzhd.zza.zzj().zzp().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzhd.zza.zzac()) {
                            zzhd.zza.zzn().zzd.zza(j2);
                            zzhd.zza.zzj().zzp().zza("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            zza4.putString("_cis", "referrer API v2");
                            zzhd.zza.zzp().zza("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zza4, zza2);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzhd.zza.zza(), serviceConnection);
        }
    }
}
