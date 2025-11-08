package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zziu implements Callable<byte[]> {
    private final /* synthetic */ zzbh zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzia zzc;

    public zziu(zzia zzia, zzbh zzbh, String str) {
        this.zza = zzbh;
        this.zzb = str;
        this.zzc = zzia;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzc.zza.zzr();
        return this.zzc.zza.zzm().zza(this.zza, this.zzb);
    }
}
