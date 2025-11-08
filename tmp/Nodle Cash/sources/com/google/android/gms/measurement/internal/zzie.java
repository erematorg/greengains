package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final /* synthetic */ class zzie implements Runnable {
    private /* synthetic */ zzia zza;
    private /* synthetic */ Bundle zzb;
    private /* synthetic */ String zzc;

    public /* synthetic */ zzie(zzia zzia, Bundle bundle, String str) {
        this.zza = zzia;
        this.zzb = bundle;
        this.zzc = str;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
