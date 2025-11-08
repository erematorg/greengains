package com.google.android.gms.internal.mlkit_common;

public final /* synthetic */ class zzsd implements Runnable {
    public final /* synthetic */ zzsh zza;
    public final /* synthetic */ zzry zzb;
    public final /* synthetic */ zzmv zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzsd(zzsh zzsh, zzry zzry, zzmv zzmv, String str) {
        this.zza = zzsh;
        this.zzb = zzry;
        this.zzc = zzmv;
        this.zzd = str;
    }

    public final void run() {
        this.zza.zzb(this.zzb, this.zzc, this.zzd);
    }
}
