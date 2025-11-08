package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

final class zzem implements zzet {
    private static final zzes zza = new zzes(zzem.class);
    private final Object zzb;

    public zzem(Object obj) {
        this.zzb = obj;
    }

    public final boolean cancel(boolean z2) {
        return false;
    }

    public final Object get() {
        return this.zzb;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        return b.m(super.toString(), "[status=SUCCESS, result=[", this.zzb.toString(), "]]");
    }

    public final void zzl(Runnable runnable, Executor executor) {
        zzaz.zzc(executor, "Executor was null.");
        try {
            runnable.run();
        } catch (Exception e3) {
            zza.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.ImmediateFuture", "addListener", C0118y.f("RuntimeException while executing runnable ", runnable.toString(), " with executor ", String.valueOf(executor)), e3);
        }
    }

    public final Object get(long j2, TimeUnit timeUnit) throws ExecutionException {
        timeUnit.getClass();
        return this.zzb;
    }
}
