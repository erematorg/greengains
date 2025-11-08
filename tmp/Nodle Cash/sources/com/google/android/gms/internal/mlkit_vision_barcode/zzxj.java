package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.GmsLogger;

final class zzxj implements zzeh {
    final /* synthetic */ zzrc zza;
    final /* synthetic */ float zzb;
    final /* synthetic */ zzxn zzc;
    final /* synthetic */ float zzd;
    final /* synthetic */ zzxk zze;

    public zzxj(zzxk zzxk, zzrc zzrc, float f2, zzxn zzxn, float f3) {
        this.zza = zzrc;
        this.zzb = f2;
        this.zzc = zzxn;
        this.zzd = f3;
        this.zze = zzxk;
    }

    public final void zza(Throwable th) {
        GmsLogger zzb2 = zzxk.zzf;
        zzb2.w("AutoZoom", "Unable to set zoom to " + this.zzd, th);
        this.zze.zzg.set(false);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        Float f2 = (Float) obj;
        if (f2.floatValue() >= 1.0f) {
            zzxk.zzg(this.zze, f2.floatValue());
            this.zze.zzq(this.zza, this.zzb, f2.floatValue(), this.zzc);
        }
        this.zze.zzg.set(false);
    }
}
