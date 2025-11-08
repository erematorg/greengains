package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.common.C;

public abstract class zzxm {
    public static final zzxm zza;

    static {
        zzm().zzm();
        zzxl zzm = zzm();
        zzm.zzi(false);
        zza = zzm.zzm();
    }

    public static zzxl zzm() {
        zzxd zzxd = new zzxd();
        zzxd.zzg(10);
        zzxd.zze(5);
        zzxd.zzf(0.25f);
        zzxd.zzd(0.8f);
        zzxd.zzi(true);
        zzxd.zzc(0.5f);
        zzxd.zzb(0.8f);
        zzxd.zzk(1500);
        zzxd.zzh(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        zzxd.zza(true);
        zzxd.zzj(0.1f);
        zzxd.zzl(0.05f);
        return zzxd;
    }

    public abstract float zza();

    public abstract float zzb();

    public abstract float zzc();

    public abstract float zzd();

    public abstract float zze();

    public abstract float zzf();

    public abstract int zzg();

    public abstract int zzh();

    public abstract long zzi();

    public abstract long zzj();

    public abstract boolean zzk();

    public abstract boolean zzl();
}
