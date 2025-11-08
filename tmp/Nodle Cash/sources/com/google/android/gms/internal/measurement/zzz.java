package com.google.android.gms.internal.measurement;

import com.google.common.annotations.VisibleForTesting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzz extends zzal {
    private final zzl zzk;
    @VisibleForTesting
    private final Map<String, zzal> zzl = new HashMap();

    public zzz(zzl zzl2) {
        super("require");
        this.zzk = zzl2;
    }

    public final zzaq zza(zzh zzh, List<zzaq> list) {
        zzg.zza("require", 1, list);
        String zzf = zzh.zza(list.get(0)).zzf();
        if (this.zzl.containsKey(zzf)) {
            return this.zzl.get(zzf);
        }
        zzaq zza = this.zzk.zza(zzf);
        if (zza instanceof zzal) {
            this.zzl.put(zzf, (zzal) zza);
        }
        return zza;
    }
}
