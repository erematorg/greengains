package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.vision.barcode.internal.zzk;

public final /* synthetic */ class zzwn implements Runnable {
    public final /* synthetic */ zzwp zza;
    public final /* synthetic */ zzrc zzb;
    public final /* synthetic */ Object zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ zzk zze;

    public /* synthetic */ zzwn(zzwp zzwp, zzrc zzrc, Object obj, long j2, zzk zzk) {
        this.zza = zzwp;
        this.zzb = zzrc;
        this.zzc = obj;
        this.zzd = j2;
        this.zze = zzk;
    }

    public final void run() {
        this.zza.zzh(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
