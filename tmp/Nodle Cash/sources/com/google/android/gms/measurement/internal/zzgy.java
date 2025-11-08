package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.support.v4.media.session.a;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.annotations.VisibleForTesting;

public final class zzgy {
    @VisibleForTesting
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;
    private final /* synthetic */ zzgu zze;

    @WorkerThread
    private final long zzb() {
        return this.zze.zzg().getLong(this.zza, 0);
    }

    @WorkerThread
    private final void zzc() {
        this.zze.zzt();
        long currentTimeMillis = this.zze.zzb().currentTimeMillis();
        SharedPreferences.Editor edit = this.zze.zzg().edit();
        edit.remove(this.zzb);
        edit.remove(this.zzc);
        edit.putLong(this.zza, currentTimeMillis);
        edit.apply();
    }

    @WorkerThread
    public final Pair<String, Long> zza() {
        long j2;
        this.zze.zzt();
        this.zze.zzt();
        long zzb2 = zzb();
        if (zzb2 == 0) {
            zzc();
            j2 = 0;
        } else {
            j2 = Math.abs(zzb2 - this.zze.zzb().currentTimeMillis());
        }
        long j3 = this.zzd;
        if (j2 < j3) {
            return null;
        }
        if (j2 > (j3 << 1)) {
            zzc();
            return null;
        }
        String string = this.zze.zzg().getString(this.zzc, (String) null);
        long j4 = this.zze.zzg().getLong(this.zzb, 0);
        zzc();
        if (string == null || j4 <= 0) {
            return zzgu.zza;
        }
        return new Pair<>(string, Long.valueOf(j4));
    }

    private zzgy(zzgu zzgu, String str, long j2) {
        this.zze = zzgu;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j2 > 0);
        this.zza = str + ":start";
        this.zzb = a.m(str, ":count");
        this.zzc = a.m(str, ":value");
        this.zzd = j2;
    }

    @WorkerThread
    public final void zza(String str, long j2) {
        this.zze.zzt();
        if (zzb() == 0) {
            zzc();
        }
        if (str == null) {
            str = "";
        }
        long j3 = this.zze.zzg().getLong(this.zzb, 0);
        if (j3 <= 0) {
            SharedPreferences.Editor edit = this.zze.zzg().edit();
            edit.putString(this.zzc, str);
            edit.putLong(this.zzb, 1);
            edit.apply();
            return;
        }
        long j4 = j3 + 1;
        boolean z2 = (this.zze.zzq().zzv().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / j4;
        SharedPreferences.Editor edit2 = this.zze.zzg().edit();
        if (z2) {
            edit2.putString(this.zzc, str);
        }
        edit2.putLong(this.zzb, j4);
        edit2.apply();
    }
}
