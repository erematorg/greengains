package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Map;
import org.checkerframework.dataflow.qual.Pure;

public final class zza extends zzg {
    private final Map<String, Long> zza = new ArrayMap();
    private final Map<String, Integer> zzb = new ArrayMap();
    private long zzc;

    public zza(zzhw zzhw) {
        super(zzhw);
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zza zzc() {
        return super.zzc();
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

    public final /* bridge */ /* synthetic */ zzgc zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
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

    public final /* bridge */ /* synthetic */ zzjk zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlg zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzlp zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
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

    public static /* synthetic */ void zzb(zza zza2, String str, long j2) {
        zza2.zzt();
        Preconditions.checkNotEmpty(str);
        Integer num = zza2.zzb.get(str);
        if (num != null) {
            zzlh zza3 = zza2.zzn().zza(false);
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                zza2.zzb.remove(str);
                Long l2 = zza2.zza.get(str);
                if (l2 == null) {
                    zza2.zzj().zzg().zza("First ad unit exposure time was never set");
                } else {
                    zza2.zza.remove(str);
                    zza2.zza(str, j2 - l2.longValue(), zza3);
                }
                if (zza2.zzb.isEmpty()) {
                    long j3 = zza2.zzc;
                    if (j3 == 0) {
                        zza2.zzj().zzg().zza("First ad exposure time was never set");
                        return;
                    }
                    zza2.zza(j2 - j3, zza3);
                    zza2.zzc = 0;
                    return;
                }
                return;
            }
            zza2.zzb.put(str, Integer.valueOf(intValue));
            return;
        }
        zza2.zzj().zzg().zza("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    public static /* synthetic */ void zza(zza zza2, String str, long j2) {
        zza2.zzt();
        Preconditions.checkNotEmpty(str);
        if (zza2.zzb.isEmpty()) {
            zza2.zzc = j2;
        }
        Integer num = zza2.zzb.get(str);
        if (num != null) {
            zza2.zzb.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (zza2.zzb.size() >= 100) {
            zza2.zzj().zzu().zza("Too many ads visible");
        } else {
            zza2.zzb.put(str, 1);
            zza2.zza.put(str, Long.valueOf(j2));
        }
    }

    public final void zza(String str, long j2) {
        if (str == null || str.length() == 0) {
            zzj().zzg().zza("Ad unit id must be a non-empty string");
        } else {
            zzl().zzb((Runnable) new zzc(this, str, j2));
        }
    }

    @WorkerThread
    private final void zza(long j2, zzlh zzlh) {
        if (zzlh == null) {
            zzj().zzp().zza("Not logging ad exposure. No active activity");
        } else if (j2 < 1000) {
            zzj().zzp().zza("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j2));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j2);
            zzop.zza(zzlh, bundle, true);
            zzm().zzc("am", "_xa", bundle);
        }
    }

    public final void zzb(String str, long j2) {
        if (str == null || str.length() == 0) {
            zzj().zzg().zza("Ad unit id must be a non-empty string");
        } else {
            zzl().zzb((Runnable) new zzb(this, str, j2));
        }
    }

    @WorkerThread
    private final void zza(String str, long j2, zzlh zzlh) {
        if (zzlh == null) {
            zzj().zzp().zza("Not logging ad unit exposure. No active activity");
        } else if (j2 < 1000) {
            zzj().zzp().zza("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j2));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j2);
            zzop.zza(zzlh, bundle, true);
            zzm().zzc("am", "_xu", bundle);
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzb(long j2) {
        for (String put : this.zza.keySet()) {
            this.zza.put(put, Long.valueOf(j2));
        }
        if (!this.zza.isEmpty()) {
            this.zzc = j2;
        }
    }

    @WorkerThread
    public final void zza(long j2) {
        zzlh zza2 = zzn().zza(false);
        for (String next : this.zza.keySet()) {
            zza(next, j2 - this.zza.get(next).longValue(), zza2);
        }
        if (!this.zza.isEmpty()) {
            zza(j2 - this.zzc, zza2);
        }
        zzb(j2);
    }
}
