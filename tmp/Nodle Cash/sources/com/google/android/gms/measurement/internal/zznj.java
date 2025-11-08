package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;

final class zznj {
    final /* synthetic */ zznb zza;

    public zznj(zznb zznb) {
        this.zza = zznb;
    }

    @VisibleForTesting
    @WorkerThread
    private final void zzb(long j2, boolean z2) {
        this.zza.zzt();
        if (this.zza.zzu.zzac()) {
            this.zza.zzk().zzk.zza(j2);
            this.zza.zzj().zzp().zza("Session started, time", Long.valueOf(this.zza.zzb().elapsedRealtime()));
            long j3 = j2 / 1000;
            long j4 = j2;
            this.zza.zzm().zza("auto", "_sid", (Object) Long.valueOf(j3), j4);
            this.zza.zzk().zzl.zza(j3);
            this.zza.zzk().zzg.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", j3);
            this.zza.zzm().zza("auto", "_s", j4, bundle);
            String zza2 = this.zza.zzk().zzq.zza();
            if (!TextUtils.isEmpty(zza2)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("_ffr", zza2);
                this.zza.zzm().zza("auto", "_ssr", j2, bundle2);
            }
        }
    }

    @WorkerThread
    public final void zza() {
        this.zza.zzt();
        if (this.zza.zzk().zza(this.zza.zzb().currentTimeMillis())) {
            this.zza.zzk().zzg.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.zza.zzj().zzp().zza("Detected application was in foreground");
                zzb(this.zza.zzb().currentTimeMillis(), false);
            }
        }
    }

    @WorkerThread
    public final void zza(long j2, boolean z2) {
        this.zza.zzt();
        this.zza.zzab();
        if (this.zza.zzk().zza(j2)) {
            this.zza.zzk().zzg.zza(true);
            this.zza.zzg().zzag();
        }
        this.zza.zzk().zzk.zza(j2);
        if (this.zza.zzk().zzg.zza()) {
            zzb(j2, z2);
        }
    }
}
