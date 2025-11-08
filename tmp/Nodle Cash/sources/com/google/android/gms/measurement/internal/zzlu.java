package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzlu implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzok zzc;
    private final /* synthetic */ zzlp zzd;

    public zzlu(zzlp zzlp, zzp zzp, boolean z2, zzok zzok) {
        this.zza = zzp;
        this.zzb = z2;
        this.zzc = zzok;
        this.zzd = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzd.zzb;
        if (zza2 == null) {
            this.zzd.zzj().zzg().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zzd.zza(zza2, (AbstractSafeParcelable) this.zzb ? null : this.zzc, this.zza);
        this.zzd.zzar();
    }
}
