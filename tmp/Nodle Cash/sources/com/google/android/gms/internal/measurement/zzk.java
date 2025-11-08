package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;

public final class zzk extends zzal {
    private final zzac zzk;

    public zzk(zzac zzac) {
        super("internal.eventLogger");
        this.zzk = zzac;
    }

    public final zzaq zza(zzh zzh, List<zzaq> list) {
        zzg.zza(this.zza, 3, list);
        String zzf = zzh.zza(list.get(0)).zzf();
        long zza = (long) zzg.zza(zzh.zza(list.get(1)).zze().doubleValue());
        zzaq zza2 = zzh.zza(list.get(2));
        this.zzk.zza(zzf, zza, zza2 instanceof zzap ? zzg.zza((zzap) zza2) : new HashMap());
        return zzaq.zzc;
    }
}
