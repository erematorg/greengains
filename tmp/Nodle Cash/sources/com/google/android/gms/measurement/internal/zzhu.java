package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdd;
import java.lang.Thread;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzhu<V> extends FutureTask<V> implements Comparable<zzhu<V>> {
    final boolean zza;
    private final long zzb;
    private final String zzc;
    private final /* synthetic */ zzhp zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzhu(zzhp zzhp, Runnable runnable, boolean z2, String str) {
        super(zzdd.zza().zza(runnable), (Object) null);
        this.zzd = zzhp;
        Preconditions.checkNotNull(str);
        long andIncrement = zzhp.zza.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z2;
        if (andIncrement == Long.MAX_VALUE) {
            zzhp.zzj().zzg().zza("Tasks index overflow");
        }
    }

    public final /* synthetic */ int compareTo(@NonNull Object obj) {
        zzhu zzhu = (zzhu) obj;
        boolean z2 = this.zza;
        if (z2 != zzhu.zza) {
            return z2 ? -1 : 1;
        }
        long j2 = this.zzb;
        long j3 = zzhu.zzb;
        if (j2 < j3) {
            return -1;
        }
        if (j2 > j3) {
            return 1;
        }
        this.zzd.zzj().zzm().zza("Two tasks share the same index. index", Long.valueOf(this.zzb));
        return 0;
    }

    public final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
        this.zzd.zzj().zzg().zza(this.zzc, th);
        if ((th instanceof zzhs) && (defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()) != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzhu(zzhp zzhp, Callable<V> callable, boolean z2, String str) {
        super(zzdd.zza().zza(callable));
        this.zzd = zzhp;
        Preconditions.checkNotNull(str);
        long andIncrement = zzhp.zza.getAndIncrement();
        this.zzb = andIncrement;
        this.zzc = str;
        this.zza = z2;
        if (andIncrement == Long.MAX_VALUE) {
            zzhp.zzj().zzg().zza("Tasks index overflow");
        }
    }
}
