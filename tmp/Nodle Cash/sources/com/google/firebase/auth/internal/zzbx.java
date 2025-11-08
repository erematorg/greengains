package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

final class zzbx implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ zzby zza;

    public zzbx(zzby zzby) {
        this.zza = zzby;
    }

    public final void onBackgroundStateChanged(boolean z2) {
        if (z2) {
            this.zza.zzc = true;
            this.zza.zza();
            return;
        }
        this.zza.zzc = false;
        if (this.zza.zzb()) {
            this.zza.zzb.zzc();
        }
    }
}
