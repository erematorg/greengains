package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.vision.barcode.internal.zzk;

public final /* synthetic */ class zzwk implements Runnable {
    public final /* synthetic */ zzwp zza;
    public final /* synthetic */ zzrc zzb;
    public final /* synthetic */ zzk zzc;

    public /* synthetic */ zzwk(zzwp zzwp, zzrc zzrc, zzk zzk) {
        this.zza = zzwp;
        this.zzb = zzrc;
        this.zzc = zzk;
    }

    public final void run() {
        this.zza.zzg(this.zzb, this.zzc);
    }
}
