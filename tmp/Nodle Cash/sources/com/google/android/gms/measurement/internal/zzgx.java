package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;

public final class zzgx {
    private final String zza;
    private final boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private final /* synthetic */ zzgu zze;

    public zzgx(zzgu zzgu, String str, boolean z2) {
        this.zze = zzgu;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = z2;
    }

    @WorkerThread
    public final void zza(boolean z2) {
        SharedPreferences.Editor edit = this.zze.zzg().edit();
        edit.putBoolean(this.zza, z2);
        edit.apply();
        this.zzd = z2;
    }

    @WorkerThread
    public final boolean zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzg().getBoolean(this.zza, this.zzb);
        }
        return this.zzd;
    }
}
