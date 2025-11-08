package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzha {
    private final String zza;
    private boolean zzb;
    private String zzc;
    private final /* synthetic */ zzgu zzd;

    public zzha(zzgu zzgu, String str, String str2) {
        this.zzd = zzgu;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }

    @WorkerThread
    public final String zza() {
        if (!this.zzb) {
            this.zzb = true;
            this.zzc = this.zzd.zzg().getString(this.zza, (String) null);
        }
        return this.zzc;
    }

    @WorkerThread
    public final void zza(String str) {
        SharedPreferences.Editor edit = this.zzd.zzg().edit();
        edit.putString(this.zza, str);
        edit.apply();
        this.zzc = str;
    }
}
