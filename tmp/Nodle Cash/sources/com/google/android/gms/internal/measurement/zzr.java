package com.google.android.gms.internal.measurement;

import androidx.core.app.NotificationCompat;
import java.util.List;

public final class zzr extends zzal {
    /* access modifiers changed from: private */
    public final zzv zzk;

    public zzr(zzv zzv) {
        super("internal.logger");
        this.zzk = zzv;
        this.zzb.put("log", new zzu(this, false, true));
        this.zzb.put(NotificationCompat.GROUP_KEY_SILENT, new zzq(this, NotificationCompat.GROUP_KEY_SILENT));
        ((zzal) this.zzb.get(NotificationCompat.GROUP_KEY_SILENT)).zza("log", (zzaq) new zzu(this, true, true));
        this.zzb.put("unmonitored", new zzt(this, "unmonitored"));
        ((zzal) this.zzb.get("unmonitored")).zza("log", (zzaq) new zzu(this, false, false));
    }

    public final zzaq zza(zzh zzh, List<zzaq> list) {
        return zzaq.zzc;
    }
}
