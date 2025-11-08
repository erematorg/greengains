package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;

public final /* synthetic */ class zzjv implements SharedPreferences.OnSharedPreferenceChangeListener {
    private /* synthetic */ zzjk zza;

    public /* synthetic */ zzjv(zzjk zzjk) {
        this.zza = zzjk;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
