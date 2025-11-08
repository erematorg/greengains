package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.MainThread;
import com.google.android.gms.internal.measurement.zzrl;

public final class zzr extends BroadcastReceiver {
    private final zzhw zza;

    public zzr(zzhw zzhw) {
        this.zza = zzhw;
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            this.zza.zzj().zzu().zza("App receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            this.zza.zzj().zzu().zza("App receiver called with null action");
        } else if (!action.equals("com.google.android.gms.measurement.TRIGGERS_AVAILABLE")) {
            this.zza.zzj().zzu().zza("App receiver called with unknown action");
        } else {
            zzhw zzhw = this.zza;
            if (zzrl.zza() && zzhw.zzf().zzf((String) null, zzbj.zzch)) {
                zzhw.zzj().zzp().zza("App receiver notified triggers are available");
                zzhw.zzl().zzb((Runnable) new zzt(zzhw));
            }
        }
    }
}
