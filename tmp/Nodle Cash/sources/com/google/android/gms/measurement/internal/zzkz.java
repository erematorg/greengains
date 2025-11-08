package com.google.android.gms.measurement.internal;

import android.net.Uri;

final class zzkz implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ Uri zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzkw zze;

    public zzkz(zzkw zzkw, boolean z2, Uri uri, String str, String str2) {
        this.zza = z2;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzkw;
    }

    public final void run() {
        zzkw.zza(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
