package com.google.android.gms.measurement.internal;

import android.text.TextUtils;

final class zzd {
    private final zzjb zza;

    public zzd(zzjb zzjb) {
        this.zza = zzjb;
    }

    public static zzd zza(String str) {
        zzjb zzjb;
        if (TextUtils.isEmpty(str) || str.length() > 1) {
            zzjb = zzjb.UNINITIALIZED;
        } else {
            zzjb = zzjc.zza(str.charAt(0));
        }
        return new zzd(zzjb);
    }

    public final String zzb() {
        return String.valueOf(zzjc.zza(this.zza));
    }

    public final zzjb zza() {
        return this.zza;
    }
}
