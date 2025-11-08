package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzs;
import com.google.android.gms.internal.measurement.zzv;
import java.util.List;

final class zzhl implements zzv {
    private final /* synthetic */ zzhg zza;

    public zzhl(zzhg zzhg) {
        this.zza = zzhg;
    }

    public final void zza(zzs zzs, String str, List<String> list, boolean z2, boolean z3) {
        int i3 = zzhn.zza[zzs.ordinal()];
        zzgk zzn = i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? this.zza.zzj().zzn() : this.zza.zzj().zzp() : z2 ? this.zza.zzj().zzw() : !z3 ? this.zza.zzj().zzv() : this.zza.zzj().zzu() : z2 ? this.zza.zzj().zzm() : !z3 ? this.zza.zzj().zzh() : this.zza.zzj().zzg() : this.zza.zzj().zzc();
        int size = list.size();
        if (size == 1) {
            zzn.zza(str, list.get(0));
        } else if (size == 2) {
            zzn.zza(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzn.zza(str);
        } else {
            zzn.zza(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
