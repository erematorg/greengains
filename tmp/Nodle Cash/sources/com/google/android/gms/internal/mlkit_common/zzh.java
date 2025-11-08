package com.google.android.gms.internal.mlkit_common;

import android.content.Context;

public final class zzh {
    public static final zzh zza;
    /* access modifiers changed from: private */
    public final boolean zzb;
    /* access modifiers changed from: private */
    public final boolean zzc = false;
    private final zzaf zzd;

    static {
        zzf zzf = new zzf((zze) null);
        zzf.zzb();
        zza = zzf.zzd();
        zzf zzf2 = new zzf((zze) null);
        zzf2.zzb();
        zzf2.zza(new zzd());
        zzf2.zzd();
        zzf zzf3 = new zzf((zze) null);
        zzf3.zzc();
        zzf3.zzd();
    }

    public /* synthetic */ zzh(boolean z2, boolean z3, zzaf zzaf, zzg zzg) {
        this.zzb = z2;
        this.zzd = zzaf;
    }

    public static /* bridge */ /* synthetic */ int zzc(zzh zzh, Context context, zzj zzj) {
        zzaf zzaf = zzh.zzd;
        int size = zzaf.size();
        int i3 = 0;
        while (i3 < size) {
            int zza2 = ((zzk) zzaf.get(i3)).zza(context, zzj, zzh.zzb) - 1;
            i3++;
            if (zza2 == 1) {
                return 2;
            }
        }
        return 3;
    }
}
