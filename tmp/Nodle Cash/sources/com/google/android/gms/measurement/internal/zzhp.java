package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import org.checkerframework.dataflow.qual.Pure;

public final class zzhp extends zzix {
    /* access modifiers changed from: private */
    public static final AtomicLong zza = new AtomicLong(Long.MIN_VALUE);
    /* access modifiers changed from: private */
    @Nullable
    public zzht zzb;
    /* access modifiers changed from: private */
    @Nullable
    public zzht zzc;
    private final PriorityBlockingQueue<zzhu<?>> zzd = new PriorityBlockingQueue<>();
    private final BlockingQueue<zzhu<?>> zze = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzf = new zzhr(this, "Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzg = new zzhr(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzh = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzi = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzj;

    public zzhp(zzhw zzhw) {
        super(zzhw);
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final boolean zzg() {
        return Thread.currentThread() == this.zzb;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final boolean zzo() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final void zzr() {
        if (Thread.currentThread() != this.zzc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final void zzt() {
        if (Thread.currentThread() != this.zzb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final void zzc(Runnable runnable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(runnable);
        zza((zzhu<?>) new zzhu(this, runnable, true, "Task exception on worker thread"));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:12|13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1 = zzj().zzu();
        r1.zza("Interrupted waiting for " + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        r2 = r2.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r1 = zzj().zzu();
        r1.zza("Timed out waiting for " + r5);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x002e */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T zza(java.util.concurrent.atomic.AtomicReference<T> r2, long r3, java.lang.String r5, java.lang.Runnable r6) {
        /*
            r1 = this;
            monitor-enter(r2)
            com.google.android.gms.measurement.internal.zzhp r0 = r1.zzl()     // Catch:{ all -> 0x002c }
            r0.zzb((java.lang.Runnable) r6)     // Catch:{ all -> 0x002c }
            r2.wait(r3)     // Catch:{ InterruptedException -> 0x002e }
            monitor-exit(r2)     // Catch:{ all -> 0x002c }
            java.lang.Object r2 = r2.get()
            if (r2 != 0) goto L_0x002b
            com.google.android.gms.measurement.internal.zzgi r1 = r1.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzu()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Timed out waiting for "
            r3.<init>(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r1.zza(r3)
        L_0x002b:
            return r2
        L_0x002c:
            r1 = move-exception
            goto L_0x004a
        L_0x002e:
            com.google.android.gms.measurement.internal.zzgi r1 = r1.zzj()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzu()     // Catch:{ all -> 0x002c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002c }
            java.lang.String r4 = "Interrupted waiting for "
            r3.<init>(r4)     // Catch:{ all -> 0x002c }
            r3.append(r5)     // Catch:{ all -> 0x002c }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x002c }
            r1.zza(r3)     // Catch:{ all -> 0x002c }
            monitor-exit(r2)     // Catch:{ all -> 0x002c }
            r1 = 0
            return r1
        L_0x004a:
            monitor-exit(r2)     // Catch:{ all -> 0x002c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhp.zza(java.util.concurrent.atomic.AtomicReference, long, java.lang.String, java.lang.Runnable):java.lang.Object");
    }

    public final <V> Future<V> zzb(Callable<V> callable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(callable);
        zzhu zzhu = new zzhu(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            zzhu.run();
        } else {
            zza((zzhu<?>) zzhu);
        }
        return zzhu;
    }

    public final void zzb(Runnable runnable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(runnable);
        zza((zzhu<?>) new zzhu(this, runnable, false, "Task exception on worker thread"));
    }

    public final <V> Future<V> zza(Callable<V> callable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(callable);
        zzhu zzhu = new zzhu(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            if (!this.zzd.isEmpty()) {
                zzj().zzu().zza("Callable skipped the worker queue.");
            }
            zzhu.run();
        } else {
            zza((zzhu<?>) zzhu);
        }
        return zzhu;
    }

    private final void zza(zzhu<?> zzhu) {
        synchronized (this.zzh) {
            try {
                this.zzd.add(zzhu);
                zzht zzht = this.zzb;
                if (zzht == null) {
                    zzht zzht2 = new zzht(this, "Measurement Worker", this.zzd);
                    this.zzb = zzht2;
                    zzht2.setUncaughtExceptionHandler(this.zzf);
                    this.zzb.start();
                } else {
                    zzht.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zza(Runnable runnable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(runnable);
        zzhu zzhu = new zzhu(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzh) {
            try {
                this.zze.add(zzhu);
                zzht zzht = this.zzc;
                if (zzht == null) {
                    zzht zzht2 = new zzht(this, "Measurement Network", this.zze);
                    this.zzc = zzht2;
                    zzht2.setUncaughtExceptionHandler(this.zzg);
                    this.zzc.start();
                } else {
                    zzht.zza();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
