package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

final class zzhr implements Thread.UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzhp zzb;

    public zzhr(zzhp zzhp, String str) {
        this.zzb = zzhp;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzj().zzg().zza(this.zza, th);
    }
}
