package com.google.firebase.analytics;

import com.google.android.gms.internal.measurement.zzdd;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zzb extends ThreadPoolExecutor {
    public zzb(FirebaseAnalytics firebaseAnalytics, int i3, int i4, long j2, TimeUnit timeUnit, BlockingQueue blockingQueue) {
        super(0, 1, 30, timeUnit, blockingQueue);
    }

    public final void execute(Runnable runnable) {
        super.execute(zzdd.zza().zza(runnable));
    }
}
