package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzbg extends zzay {
    public zzbg() {
        this.zza.add(zzbv.AND);
        this.zza.add(zzbv.NOT);
        this.zza.add(zzbv.OR);
    }

    public final zzaq zza(String str, zzh zzh, List<zzaq> list) {
        int i3 = zzbj.zza[zzg.zza(str).ordinal()];
        if (i3 == 1) {
            zzg.zza(zzbv.AND, 2, list);
            zzaq zza = zzh.zza(list.get(0));
            return !zza.zzd().booleanValue() ? zza : zzh.zza(list.get(1));
        } else if (i3 == 2) {
            zzg.zza(zzbv.NOT, 1, list);
            return new zzag(Boolean.valueOf(!zzh.zza(list.get(0)).zzd().booleanValue()));
        } else if (i3 != 3) {
            return zza(str);
        } else {
            zzg.zza(zzbv.OR, 2, list);
            zzaq zza2 = zzh.zza(list.get(0));
            return zza2.zzd().booleanValue() ? zza2 : zzh.zza(list.get(1));
        }
    }
}
