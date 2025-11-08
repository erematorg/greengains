package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdt;
import com.google.android.gms.measurement.internal.zzmy;

public final class zzmu<T extends Context & zzmy> {
    private final T zza;

    public zzmu(T t2) {
        Preconditions.checkNotNull(t2);
        this.zza = t2;
    }

    private final zzgi zzc() {
        return zzhw.zza(this.zza, (zzdt) null, (Long) null).zzj();
    }

    @MainThread
    public final int zza(Intent intent, int i3, int i4) {
        zzgi zzj = zzhw.zza(this.zza, (zzdt) null, (Long) null).zzj();
        if (intent == null) {
            zzj.zzu().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzj.zzp().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i4), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zza((Runnable) new zzmw(this, i4, zzj, intent));
        }
        return 2;
    }

    @MainThread
    public final void zzb() {
        zzhw.zza(this.zza, (zzdt) null, (Long) null).zzj().zzp().zza("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final boolean zzc(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onUnbind called with null intent");
            return true;
        }
        zzc().zzp().zza("onUnbind called for intent. action", intent.getAction());
        return true;
    }

    @MainThread
    public final void zzb(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onRebind called with null intent");
            return;
        }
        zzc().zzp().zza("onRebind called. action", intent.getAction());
    }

    @MainThread
    public final IBinder zza(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzia(zznv.zza((Context) this.zza));
        }
        zzc().zzu().zza("onBind received unknown action", action);
        return null;
    }

    public final /* synthetic */ void zza(int i3, zzgi zzgi, Intent intent) {
        if (((zzmy) this.zza).zza(i3)) {
            zzgi.zzp().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i3));
            zzc().zzp().zza("Completed wakeful intent.");
            ((zzmy) this.zza).zza(intent);
        }
    }

    public final /* synthetic */ void zza(zzgi zzgi, JobParameters jobParameters) {
        zzgi.zzp().zza("AppMeasurementJobService processed last upload request.");
        ((zzmy) this.zza).zza(jobParameters, false);
    }

    @MainThread
    public final void zza() {
        zzhw.zza(this.zza, (zzdt) null, (Long) null).zzj().zzp().zza("Local AppMeasurementService is starting up");
    }

    private final void zza(Runnable runnable) {
        zznv zza2 = zznv.zza((Context) this.zza);
        zza2.zzl().zzb((Runnable) new zzmz(this, zza2, runnable));
    }

    @TargetApi(24)
    @MainThread
    public final boolean zza(JobParameters jobParameters) {
        zzgi zzj = zzhw.zza(this.zza, (zzdt) null, (Long) null).zzj();
        String string = jobParameters.getExtras().getString("action");
        zzj.zzp().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza((Runnable) new zzmx(this, zzj, jobParameters));
        return true;
    }
}
