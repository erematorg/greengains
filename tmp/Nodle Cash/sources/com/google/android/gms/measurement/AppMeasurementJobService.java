package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.google.android.gms.measurement.internal.zzmu;
import com.google.android.gms.measurement.internal.zzmy;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements zzmy {
    private zzmu<AppMeasurementJobService> zza;

    @MainThread
    public final void onCreate() {
        super.onCreate();
        zza().zza();
    }

    @MainThread
    public final void onDestroy() {
        zza().zzb();
        super.onDestroy();
    }

    @MainThread
    public final void onRebind(@NonNull Intent intent) {
        zza().zzb(intent);
    }

    public final boolean onStartJob(@NonNull JobParameters jobParameters) {
        return zza().zza(jobParameters);
    }

    public final boolean onStopJob(@NonNull JobParameters jobParameters) {
        return false;
    }

    @MainThread
    public final boolean onUnbind(@NonNull Intent intent) {
        return zza().zzc(intent);
    }

    public final void zza(@NonNull Intent intent) {
    }

    private final zzmu<AppMeasurementJobService> zza() {
        if (this.zza == null) {
            this.zza = new zzmu<>(this);
        }
        return this.zza;
    }

    @TargetApi(24)
    public final void zza(@NonNull JobParameters jobParameters, boolean z2) {
        jobFinished(jobParameters, false);
    }

    public final boolean zza(int i3) {
        throw new UnsupportedOperationException();
    }
}
