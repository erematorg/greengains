package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;

final class zznc {
    final /* synthetic */ zznb zza;
    private zznf zzb;

    public zznc(zznb zznb) {
        this.zza = zznb;
    }

    @WorkerThread
    public final void zza(long j2) {
        this.zzb = new zznf(this, this.zza.zzb().currentTimeMillis(), j2);
        this.zza.zzc.postDelayed(this.zzb, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    }

    @WorkerThread
    public final void zza() {
        this.zza.zzt();
        if (this.zzb != null) {
            this.zza.zzc.removeCallbacks(this.zzb);
        }
        this.zza.zzk().zzn.zza(false);
        this.zza.zza(false);
        if (this.zza.zze().zza(zzbj.zzck) && this.zza.zzm().zzau()) {
            this.zza.zzj().zzp().zza("Retrying trigger URI registration in foreground");
            this.zza.zzm().zzas();
        }
    }
}
