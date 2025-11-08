package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhg;
import com.google.common.annotations.VisibleForTesting;
import java.util.concurrent.Callable;

public final class zzf {
    @VisibleForTesting
    final zzh zza;
    @VisibleForTesting
    final zzh zzb;
    @VisibleForTesting
    private final zzbb zzc;
    @VisibleForTesting
    private final zzl zzd;

    public zzf() {
        zzbb zzbb = new zzbb();
        this.zzc = zzbb;
        zzh zzh = new zzh((zzh) null, zzbb);
        this.zzb = zzh;
        this.zza = zzh.zza();
        zzl zzl = new zzl();
        this.zzd = zzl;
        zzh.zzc("require", new zzz(zzl));
        zzl.zza("internal.platform", new zze());
        zzh.zzc("runtime.counter", new zzai(Double.valueOf(0.0d)));
    }

    public final zzaq zza(zzh zzh, zzhg.zzd... zzdArr) {
        zzaq zzaq = zzaq.zzc;
        for (zzhg.zzd zza2 : zzdArr) {
            zzaq = zzj.zza(zza2);
            zzg.zza(this.zzb);
            if ((zzaq instanceof zzat) || (zzaq instanceof zzar)) {
                zzaq = this.zzc.zza(zzh, zzaq);
            }
        }
        return zzaq;
    }

    public final void zza(String str, Callable<? extends zzal> callable) {
        this.zzd.zza(str, callable);
    }
}
