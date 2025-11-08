package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzmu;
import com.google.android.gms.measurement.internal.zzmy;

public final class AppMeasurementService extends Service implements zzmy {
    private zzmu<AppMeasurementService> zza;

    private final zzmu<AppMeasurementService> zza() {
        if (this.zza == null) {
            this.zza = new zzmu<>(this);
        }
        return this.zza;
    }

    @MainThread
    @Nullable
    public final IBinder onBind(@NonNull Intent intent) {
        return zza().zza(intent);
    }

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

    @MainThread
    public final int onStartCommand(@NonNull Intent intent, int i3, int i4) {
        return zza().zza(intent, i3, i4);
    }

    @MainThread
    public final boolean onUnbind(@NonNull Intent intent) {
        return zza().zzc(intent);
    }

    public final void zza(@NonNull Intent intent) {
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }

    public final void zza(@NonNull JobParameters jobParameters, boolean z2) {
        throw new UnsupportedOperationException();
    }

    public final boolean zza(int i3) {
        return stopSelfResult(i3);
    }
}
