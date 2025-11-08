package com.google.android.gms.measurement.internal;

import android.util.SparseArray;
import com.google.common.util.concurrent.FutureCallback;

final class zzjz implements FutureCallback<Object> {
    private final /* synthetic */ zznk zza;
    private final /* synthetic */ zzjk zzb;

    public zzjz(zzjk zzjk, zznk zznk) {
        this.zza = zznk;
        this.zzb = zzjk;
    }

    private final void zza() {
        SparseArray<Long> zzh = this.zzb.zzk().zzh();
        zznk zznk = this.zza;
        zzh.put(zznk.zzc, Long.valueOf(zznk.zzb));
        this.zzb.zzk().zza(zzh);
    }

    public final void onFailure(Throwable th) {
        this.zzb.zzt();
        this.zzb.zzh = false;
        if (!this.zzb.zze().zza(zzbj.zzcm)) {
            this.zzb.zzas();
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed with throwable", th);
            return;
        }
        int zza2 = (this.zzb.zze().zza(zzbj.zzck) ? zzjk.zza(this.zzb, th) : 2) - 1;
        if (zza2 == 0) {
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed with retriable error. Will try later. App ID, throwable", zzgi.zza(this.zzb.zzg().zzad()), zzgi.zza(th.toString()));
            this.zzb.zzi = 1;
            this.zzb.zzal().add(this.zza);
        } else if (zza2 == 1) {
            this.zzb.zzal().add(this.zza);
            if (this.zzb.zzi > 32) {
                this.zzb.zzi = 1;
                this.zzb.zzj().zzu().zza("registerTriggerAsync failed. May try later. App ID, throwable", zzgi.zza(this.zzb.zzg().zzad()), zzgi.zza(th.toString()));
                return;
            }
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed. App ID, delay in seconds, throwable", zzgi.zza(this.zzb.zzg().zzad()), zzgi.zza(String.valueOf(this.zzb.zzi)), zzgi.zza(th.toString()));
            zzjk zzjk = this.zzb;
            zzjk.zzb(zzjk, zzjk.zzi);
            zzjk zzjk2 = this.zzb;
            zzjk2.zzi = zzjk2.zzi << 1;
        } else if (zza2 == 2) {
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed. Dropping URI. App ID, Throwable", zzgi.zza(this.zzb.zzg().zzad()), th);
            zza();
            this.zzb.zzi = 1;
            this.zzb.zzas();
        }
    }

    public final void onSuccess(Object obj) {
        this.zzb.zzt();
        if (this.zzb.zze().zza(zzbj.zzcm)) {
            zza();
            this.zzb.zzh = false;
            this.zzb.zzi = 1;
            this.zzb.zzj().zzc().zza("Successfully registered trigger URI", this.zza.zza);
            this.zzb.zzas();
            return;
        }
        this.zzb.zzh = false;
        this.zzb.zzas();
        this.zzb.zzj().zzc().zza("registerTriggerAsync ran. uri", this.zza.zza);
    }
}
