package com.google.android.gms.measurement.internal;

import java.util.Map;

public final /* synthetic */ class zzlc implements Runnable {
    private /* synthetic */ zzld zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ Exception zzc;
    private /* synthetic */ byte[] zzd;
    private /* synthetic */ Map zze;

    public /* synthetic */ zzlc(zzld zzld, int i3, Exception exc, byte[] bArr, Map map) {
        this.zza = zzld;
        this.zzb = i3;
        this.zzc = exc;
        this.zzd = bArr;
        this.zze = map;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
