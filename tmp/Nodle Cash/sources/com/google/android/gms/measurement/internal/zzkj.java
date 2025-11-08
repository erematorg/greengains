package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;

final class zzkj extends zzax {
    private final /* synthetic */ zzjk zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzkj(zzjk zzjk, zzja zzja) {
        super(zzja);
        this.zza = zzjk;
    }

    @WorkerThread
    public final void zzb() {
        if (this.zza.zzu.zzah()) {
            this.zza.zzq.zza(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        }
    }
}
