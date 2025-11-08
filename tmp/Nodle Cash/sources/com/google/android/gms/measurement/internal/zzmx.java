package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

public final /* synthetic */ class zzmx implements Runnable {
    private /* synthetic */ zzmu zza;
    private /* synthetic */ zzgi zzb;
    private /* synthetic */ JobParameters zzc;

    public /* synthetic */ zzmx(zzmu zzmu, zzgi zzgi, JobParameters jobParameters) {
        this.zza = zzmu;
        this.zzb = zzgi;
        this.zzc = jobParameters;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
