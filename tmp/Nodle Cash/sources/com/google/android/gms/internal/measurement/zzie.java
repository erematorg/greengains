package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzie extends ContentObserver {
    private final /* synthetic */ zzic zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzie(zzic zzic, Handler handler) {
        super((Handler) null);
        this.zza = zzic;
    }

    public final void onChange(boolean z2) {
        this.zza.zzd();
    }
}
