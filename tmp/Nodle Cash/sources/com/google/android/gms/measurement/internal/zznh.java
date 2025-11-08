package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;

final class zznh {
    @VisibleForTesting
    protected long zza;
    final /* synthetic */ zznb zzb;
    @VisibleForTesting
    private long zzc;
    private final zzax zzd;

    public zznh(zznb zznb) {
        this.zzb = zznb;
        this.zzd = new zzng(this, zznb.zzu);
        long elapsedRealtime = zznb.zzb().elapsedRealtime();
        this.zzc = elapsedRealtime;
        this.zza = elapsedRealtime;
    }

    @VisibleForTesting
    @WorkerThread
    public final long zza(long j2) {
        long j3 = j2 - this.zza;
        this.zza = j2;
        return j3;
    }

    @WorkerThread
    public final void zzb(long j2) {
        this.zzd.zza();
    }

    @WorkerThread
    public final void zzc(long j2) {
        this.zzb.zzt();
        this.zzd.zza();
        this.zzc = j2;
        this.zza = j2;
    }

    public static /* synthetic */ void zza(zznh zznh) {
        zznh.zzb.zzt();
        zznh.zza(false, false, zznh.zzb.zzb().elapsedRealtime());
        zznh.zzb.zzc().zza(zznh.zzb.zzb().elapsedRealtime());
    }

    public final void zza() {
        this.zzd.zza();
        if (this.zzb.zze().zza(zzbj.zzdf)) {
            this.zzc = this.zzb.zzb().elapsedRealtime();
        } else {
            this.zzc = 0;
        }
        this.zza = this.zzc;
    }

    @WorkerThread
    public final boolean zza(boolean z2, boolean z3, long j2) {
        this.zzb.zzt();
        this.zzb.zzu();
        if (this.zzb.zzu.zzac()) {
            this.zzb.zzk().zzk.zza(this.zzb.zzb().currentTimeMillis());
        }
        long j3 = j2 - this.zzc;
        if (z2 || j3 >= 1000) {
            if (!z3) {
                j3 = zza(j2);
            }
            this.zzb.zzj().zzp().zza("Recording user engagement, ms", Long.valueOf(j3));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j3);
            zzop.zza(this.zzb.zzn().zza(!this.zzb.zze().zzy()), bundle, true);
            if (!z3) {
                this.zzb.zzm().zzc("auto", "_e", bundle);
            }
            this.zzc = j2;
            this.zzd.zza();
            this.zzd.zza(zzbj.zzbc.zza(null).longValue());
            return true;
        }
        this.zzb.zzj().zzp().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j3));
        return false;
    }
}
