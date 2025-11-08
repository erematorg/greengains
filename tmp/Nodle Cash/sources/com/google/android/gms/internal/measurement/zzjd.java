package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;

public final /* synthetic */ class zzjd implements SharedPreferences.OnSharedPreferenceChangeListener {
    private /* synthetic */ zzja zza;

    public /* synthetic */ zzjd(zzja zzja) {
        this.zza = zzja;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
