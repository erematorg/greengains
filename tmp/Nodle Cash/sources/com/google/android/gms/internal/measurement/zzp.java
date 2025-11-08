package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzp extends zzal {
    private final /* synthetic */ zzo zzk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzp(zzm zzm, String str, zzo zzo) {
        super(str);
        this.zzk = zzo;
    }

    public final zzaq zza(zzh zzh, List<zzaq> list) {
        zzg.zza("getValue", 2, list);
        zzaq zza = zzh.zza(list.get(0));
        zzaq zza2 = zzh.zza(list.get(1));
        String zza3 = this.zzk.zza(zza.zzf());
        return zza3 != null ? new zzas(zza3) : zza2;
    }
}
