package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzav;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@KeepForSdk
public class MlKitThreadPool extends zzav {
    private static final ThreadLocal zza = new ThreadLocal();
    private final ThreadPoolExecutor zzb;

    public MlKitThreadPool() {
        ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzj(defaultThreadFactory));
        this.zzb = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public static /* synthetic */ void zzd(Runnable runnable) {
        zza.set(new ArrayDeque());
        runnable.run();
    }

    /* access modifiers changed from: private */
    public static void zze(Deque deque, Runnable runnable) {
        Preconditions.checkNotNull(deque);
        deque.add(runnable);
        if (deque.size() <= 1) {
            do {
                runnable.run();
                deque.removeFirst();
                runnable = (Runnable) deque.peekFirst();
            } while (runnable != null);
        }
    }

    public final void execute(@NonNull Runnable runnable) {
        Deque deque = (Deque) zza.get();
        if (deque == null || deque.size() > 1) {
            this.zzb.execute(new zzi(runnable));
        } else {
            zze(deque, runnable);
        }
    }

    @NonNull
    public final /* synthetic */ Object zza() {
        return this.zzb;
    }

    @NonNull
    public final ExecutorService zzb() {
        return this.zzb;
    }
}
