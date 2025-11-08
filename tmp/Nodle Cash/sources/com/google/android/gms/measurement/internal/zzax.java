package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdh;

abstract class zzax {
    private static volatile Handler zza;
    private final zzja zzb;
    private final Runnable zzc;
    /* access modifiers changed from: private */
    public volatile long zzd;

    public zzax(zzja zzja) {
        Preconditions.checkNotNull(zzja);
        this.zzb = zzja;
        this.zzc = new zzaw(this, zzja);
    }

    private final Handler zzd() {
        Handler handler;
        if (zza != null) {
            return zza;
        }
        synchronized (zzax.class) {
            try {
                if (zza == null) {
                    zza = new zzdh(this.zzb.zza().getMainLooper());
                }
                handler = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    public abstract void zzb();

    public final boolean zzc() {
        return this.zzd != 0;
    }

    public final void zza() {
        this.zzd = 0;
        zzd().removeCallbacks(this.zzc);
    }

    public final void zza(long j2) {
        zza();
        if (j2 >= 0) {
            this.zzd = this.zzb.zzb().currentTimeMillis();
            if (!zzd().postDelayed(this.zzc, j2)) {
                this.zzb.zzj().zzg().zza("Failed to schedule delayed post. time", Long.valueOf(j2));
            }
        }
    }
}
