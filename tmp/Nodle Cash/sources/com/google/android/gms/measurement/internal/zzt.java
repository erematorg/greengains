package com.google.android.gms.measurement.internal;

import java.util.Objects;

public final /* synthetic */ class zzt implements Runnable {
    private /* synthetic */ zzhw zza;

    public /* synthetic */ zzt(zzhw zzhw) {
        this.zza = zzhw;
    }

    public final void run() {
        zzhw zzhw = this.zza;
        if (!zzhw.zzt().zzw()) {
            zzhw.zzj().zzu().zza("registerTrigger called but app not eligible");
            return;
        }
        zzjk zzp = zzhw.zzp();
        Objects.requireNonNull(zzp);
        new Thread(new zzq(zzp)).start();
    }
}
