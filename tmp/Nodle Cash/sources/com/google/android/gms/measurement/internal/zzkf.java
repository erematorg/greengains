package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzkf implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ Bundle zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ boolean zzf;
    private final /* synthetic */ boolean zzg;
    private final /* synthetic */ String zzh;
    private final /* synthetic */ zzjk zzi;

    public zzkf(zzjk zzjk, String str, String str2, long j2, Bundle bundle, boolean z2, boolean z3, boolean z4, String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = j2;
        this.zzd = bundle;
        this.zze = z2;
        this.zzf = z3;
        this.zzg = z4;
        this.zzh = str3;
        this.zzi = zzjk;
    }

    public final void run() {
        this.zzi.zza(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh);
    }
}
