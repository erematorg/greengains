package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzct;
import com.google.android.gms.internal.measurement.zzcw;
import org.checkerframework.dataflow.qual.Pure;

public final class zznm extends zznr {
    private final AlarmManager zza = ((AlarmManager) zza().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private zzax zzb;
    private Integer zzc;

    public zznm(zznv zznv) {
        super(zznv);
    }

    private final int zzv() {
        if (this.zzc == null) {
            String packageName = zza().getPackageName();
            this.zzc = Integer.valueOf(("measurement" + packageName).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzw() {
        Context zza2 = zza();
        return zzct.zza(zza2, 0, new Intent().setClassName(zza2, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), zzct.zza);
    }

    private final zzax zzx() {
        if (this.zzb == null) {
            this.zzb = new zznp(this, this.zzg.zzk());
        }
        return this.zzb;
    }

    @TargetApi(24)
    private final void zzy() {
        JobScheduler jobScheduler = (JobScheduler) zza().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzv());
        }
    }

    public final /* bridge */ /* synthetic */ zzol g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final boolean zzc() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzw());
        }
        zzy();
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzv zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzam zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzhg zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzms zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zznq zzo() {
        return super.zzo();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final void zzu() {
        zzal();
        zzj().zzp().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzw());
        }
        zzx().zza();
        zzy();
    }

    public final void zza(long j2) {
        zzal();
        Context zza2 = zza();
        if (!zzop.zza(zza2)) {
            zzj().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzop.zza(zza2, false)) {
            zzj().zzc().zza("Service not registered/enabled");
        }
        zzu();
        zzj().zzp().zza("Scheduling upload, millis", Long.valueOf(j2));
        zzb().elapsedRealtime();
        if (j2 < Math.max(0, zzbj.zzy.zza(null).longValue()) && !zzx().zzc()) {
            zzx().zza(j2);
        }
        Context zza3 = zza();
        ComponentName componentName = new ComponentName(zza3, "com.google.android.gms.measurement.AppMeasurementJobService");
        int zzv = zzv();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
        zzcw.zza(zza3, new JobInfo.Builder(zzv, componentName).setMinimumLatency(j2).setOverrideDeadline(j2 << 1).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }
}
