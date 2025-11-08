package com.google.android.gms.internal.location;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class zzfd {
    public static boolean zza(CountDownLatch countDownLatch, long j2, TimeUnit timeUnit) {
        long nanos;
        boolean await;
        boolean z2 = false;
        try {
            nanos = timeUnit.toNanos(30);
            while (true) {
                await = countDownLatch.await(nanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            return await;
        } catch (InterruptedException unused) {
            z2 = true;
            nanos = (System.nanoTime() + nanos) - System.nanoTime();
        } catch (Throwable th) {
            if (z2) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }
}
