package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.zzjc;

public enum zzjd {
    STORAGE(zzjc.zza.AD_STORAGE, zzjc.zza.ANALYTICS_STORAGE),
    DMA(zzjc.zza.AD_USER_DATA);
    
    /* access modifiers changed from: private */
    public final zzjc.zza[] zzd;

    private zzjd(zzjc.zza... zzaArr) {
        this.zzd = zzaArr;
    }

    public final zzjc.zza[] zza() {
        return this.zzd;
    }
}
