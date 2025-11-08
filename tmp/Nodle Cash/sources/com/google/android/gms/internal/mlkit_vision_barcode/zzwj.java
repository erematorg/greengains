package com.google.android.gms.internal.mlkit_vision_barcode;

public final /* synthetic */ class zzwj implements Runnable {
    public final /* synthetic */ zzwp zza;
    public final /* synthetic */ zzwe zzb;
    public final /* synthetic */ zzrc zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzwj(zzwp zzwp, zzwe zzwe, zzrc zzrc, String str) {
        this.zza = zzwp;
        this.zzb = zzwe;
        this.zzc = zzrc;
        this.zzd = str;
    }

    public final void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
