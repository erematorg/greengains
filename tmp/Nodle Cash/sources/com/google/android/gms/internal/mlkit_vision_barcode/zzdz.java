package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.ui.autofill.a;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.StringUtils;
import sun.misc.Unsafe;

public abstract class zzdz<V> extends zzex implements zzet<V> {
    static final boolean zza;
    static final zzes zzb;
    /* access modifiers changed from: private */
    public static final zza zzc;
    private static final Object zzd = new Object();
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzd listeners;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile Object value;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzj waiters;

    abstract class zza {
        public /* synthetic */ zza(zzdy zzdy) {
        }

        public abstract zzd zza(zzdz zzdz, zzd zzd);

        public abstract zzj zzb(zzdz zzdz, zzj zzj);

        public abstract void zzc(zzj zzj, @CheckForNull zzj zzj2);

        public abstract void zzd(zzj zzj, Thread thread);

        public abstract boolean zze(zzdz zzdz, @CheckForNull zzd zzd, zzd zzd2);

        public abstract boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2);

        public abstract boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2);
    }

    final class zzb {
        @CheckForNull
        static final zzb zza;
        @CheckForNull
        static final zzb zzb;
        final boolean zzc;
        @CheckForNull
        final Throwable zzd;

        static {
            if (zzdz.zza) {
                zzb = null;
                zza = null;
                return;
            }
            zzb = new zzb(false, (Throwable) null);
            zza = new zzb(true, (Throwable) null);
        }

        public zzb(boolean z2, @CheckForNull Throwable th) {
            this.zzc = z2;
            this.zzd = th;
        }
    }

    final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") {
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        public zzc(Throwable th) {
            th.getClass();
            this.zzb = th;
        }
    }

    final class zzd {
        static final zzd zza = new zzd();
        @CheckForNull
        zzd next;
        @CheckForNull
        final Runnable zzb;
        @CheckForNull
        final Executor zzc;

        public zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        public zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzj, Thread> zza;
        final AtomicReferenceFieldUpdater<zzj, zzj> zzb;
        final AtomicReferenceFieldUpdater<? super zzdz<?>, zzj> zzc;
        final AtomicReferenceFieldUpdater<? super zzdz<?>, zzd> zzd;
        final AtomicReferenceFieldUpdater<? super zzdz<?>, Object> zze;

        public zze(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super((zzdy) null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        public final zzd zza(zzdz zzdz, zzd zzd2) {
            return this.zzd.getAndSet(zzdz, zzd2);
        }

        public final zzj zzb(zzdz zzdz, zzj zzj) {
            return this.zzc.getAndSet(zzdz, zzj);
        }

        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            this.zzb.lazySet(zzj, zzj2);
        }

        public final void zzd(zzj zzj, Thread thread) {
            this.zza.lazySet(zzj, thread);
        }

        public final boolean zze(zzdz zzdz, @CheckForNull zzd zzd2, zzd zzd3) {
            return zzea.zza(this.zzd, zzdz, zzd2, zzd3);
        }

        public final boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2) {
            return zzea.zza(this.zze, zzdz, obj, obj2);
        }

        public final boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            return zzea.zza(this.zzc, zzdz, zzj, zzj2);
        }
    }

    final class zzf<V> implements Runnable {
        final zzdz<V> zza;
        final zzet<? extends V> zzb;

        public zzf(zzdz zzdz, zzet zzet) {
            this.zza = zzdz;
            this.zzb = zzet;
        }

        public final void run() {
            if (this.zza.value == this) {
                zzet<? extends V> zzet = this.zzb;
                if (zzdz.zzc.zzf(this.zza, this, zzdz.zzq(zzet))) {
                    zzdz.zzv(this.zza, false);
                }
            }
        }
    }

    final class zzg extends zza {
        private zzg() {
            throw null;
        }

        public final zzd zza(zzdz zzdz, zzd zzd) {
            zzd zzb;
            synchronized (zzdz) {
                try {
                    zzb = zzdz.listeners;
                    if (zzb != zzd) {
                        zzdz.listeners = zzd;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return zzb;
        }

        public final zzj zzb(zzdz zzdz, zzj zzj) {
            zzj zzc;
            synchronized (zzdz) {
                try {
                    zzc = zzdz.waiters;
                    if (zzc != zzj) {
                        zzdz.waiters = zzj;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return zzc;
        }

        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            zzj.next = zzj2;
        }

        public final void zzd(zzj zzj, Thread thread) {
            zzj.thread = thread;
        }

        public final boolean zze(zzdz zzdz, @CheckForNull zzd zzd, zzd zzd2) {
            synchronized (zzdz) {
                try {
                    if (zzdz.listeners != zzd) {
                        return false;
                    }
                    zzdz.listeners = zzd2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2) {
            synchronized (zzdz) {
                try {
                    if (zzdz.value != obj) {
                        return false;
                    }
                    zzdz.value = obj2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            synchronized (zzdz) {
                try {
                    if (zzdz.waiters != zzj) {
                        return false;
                    }
                    zzdz.waiters = zzj2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public /* synthetic */ zzg(zzeb zzeb) {
            super((zzdy) null);
        }
    }

    interface zzh<V> extends zzet<V> {
    }

    final class zzi extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x005a, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0066, code lost:
            throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
            r1 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.AnonymousClass1());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
        static {
            /*
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj> r0 = com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzj.class
                sun.misc.Unsafe r1 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0007 }
                goto L_0x0012
            L_0x0007:
                com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzi$1 r1 = new com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzi$1     // Catch:{ PrivilegedActionException -> 0x005a }
                r1.<init>()     // Catch:{ PrivilegedActionException -> 0x005a }
                java.lang.Object r1 = java.security.AccessController.doPrivileged(r1)     // Catch:{ PrivilegedActionException -> 0x005a }
                sun.misc.Unsafe r1 = (sun.misc.Unsafe) r1     // Catch:{ PrivilegedActionException -> 0x005a }
            L_0x0012:
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzdz> r2 = com.google.android.gms.internal.mlkit_vision_barcode.zzdz.class
                java.lang.String r3 = "waiters"
                java.lang.reflect.Field r3 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0053 }
                long r3 = r1.objectFieldOffset(r3)     // Catch:{ NoSuchFieldException -> 0x0053 }
                zzc = r3     // Catch:{ NoSuchFieldException -> 0x0053 }
                java.lang.String r3 = "listeners"
                java.lang.reflect.Field r3 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0053 }
                long r3 = r1.objectFieldOffset(r3)     // Catch:{ NoSuchFieldException -> 0x0053 }
                zzb = r3     // Catch:{ NoSuchFieldException -> 0x0053 }
                java.lang.String r3 = "value"
                java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0053 }
                long r2 = r1.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0053 }
                zzd = r2     // Catch:{ NoSuchFieldException -> 0x0053 }
                java.lang.String r2 = "thread"
                java.lang.reflect.Field r2 = r0.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0053 }
                long r2 = r1.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0053 }
                zze = r2     // Catch:{ NoSuchFieldException -> 0x0053 }
                java.lang.String r2 = "next"
                java.lang.reflect.Field r0 = r0.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0053 }
                long r2 = r1.objectFieldOffset(r0)     // Catch:{ NoSuchFieldException -> 0x0053 }
                zzf = r2     // Catch:{ NoSuchFieldException -> 0x0053 }
                zza = r1     // Catch:{ NoSuchFieldException -> 0x0053 }
                return
            L_0x0053:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                r1.<init>(r0)
                throw r1
            L_0x005a:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.String r2 = "Could not initialize intrinsics"
                java.lang.Throwable r0 = r0.getCause()
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.<clinit>():void");
        }

        private zzi() {
            throw null;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public final com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzd zza(com.google.android.gms.internal.mlkit_vision_barcode.zzdz r3, com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzd r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzd r0 = r3.listeners
                if (r4 != r0) goto L_0x0007
                goto L_0x000d
            L_0x0007:
                boolean r1 = r2.zze(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
            L_0x000d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.zza(com.google.android.gms.internal.mlkit_vision_barcode.zzdz, com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzd):com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzd");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public final com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzj zzb(com.google.android.gms.internal.mlkit_vision_barcode.zzdz r3, com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzj r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj r0 = r3.waiters
                if (r4 != r0) goto L_0x0007
                goto L_0x000d
            L_0x0007:
                boolean r1 = r2.zzg(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
            L_0x000d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.zzb(com.google.android.gms.internal.mlkit_vision_barcode.zzdz, com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj):com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj");
        }

        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            zza.putObject(zzj, zzf, zzj2);
        }

        public final void zzd(zzj zzj, Thread thread) {
            zza.putObject(zzj, zze, thread);
        }

        public final boolean zze(zzdz zzdz, @CheckForNull zzd zzd2, zzd zzd3) {
            return zzec.zza(zza, zzdz, zzb, zzd2, zzd3);
        }

        public final boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2) {
            return zzec.zza(zza, zzdz, zzd, obj, obj2);
        }

        public final boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            return zzec.zza(zza, zzdz, zzc, zzj, zzj2);
        }

        public /* synthetic */ zzi(zzed zzed) {
            super((zzdy) null);
        }
    }

    final class zzj {
        static final zzj zza = new zzj(false);
        @CheckForNull
        volatile zzj next;
        @CheckForNull
        volatile Thread thread;

        public zzj(boolean z2) {
        }

        public zzj() {
            zzdz.zzc.zzd(this, Thread.currentThread());
        }
    }

    static {
        boolean z2;
        Throwable th;
        Throwable th2;
        zza zza2;
        Class<zzj> cls = zzj.class;
        try {
            z2 = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z2 = false;
        }
        zza = z2;
        Class<zzdz> cls2 = zzdz.class;
        zzb = new zzes(cls2);
        try {
            zza2 = new zzi((zzed) null);
            th2 = null;
            th = null;
        } catch (Error | Exception e3) {
            try {
                th = null;
                th2 = e3;
                zza2 = new zze(AtomicReferenceFieldUpdater.newUpdater(cls, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(cls, cls, "next"), AtomicReferenceFieldUpdater.newUpdater(cls2, cls, "waiters"), AtomicReferenceFieldUpdater.newUpdater(cls2, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(cls2, Object.class, "value"));
            } catch (Error | Exception e4) {
                th = e4;
                th2 = e3;
                zza2 = new zzg((zzeb) null);
            }
        }
        zzc = zza2;
        if (th != null) {
            zzes zzes = zzb;
            Logger zza3 = zzes.zza();
            Level level = Level.SEVERE;
            zza3.logp(level, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            zzes.zza().logp(level, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    /* access modifiers changed from: private */
    public static Object zzq(zzet zzet) {
        Throwable zzg2;
        if (zzet instanceof zzh) {
            Object obj = ((zzdz) zzet).value;
            if (obj instanceof zzb) {
                zzb zzb2 = (zzb) obj;
                if (zzb2.zzc) {
                    Throwable th = zzb2.zzd;
                    obj = th != null ? new zzb(false, th) : zzb.zzb;
                }
            }
            Objects.requireNonNull(obj);
            return obj;
        } else if ((zzet instanceof zzex) && (zzg2 = ((zzex) zzet).zzg()) != null) {
            return new zzc(zzg2);
        } else {
            boolean isCancelled = zzet.isCancelled();
            if ((!zza) && isCancelled) {
                zzb zzb3 = zzb.zzb;
                Objects.requireNonNull(zzb3);
                return zzb3;
            }
            try {
                Object zzr = zzr(zzet);
                return isCancelled ? new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(zzet)))) : zzr == null ? zzd : zzr;
            } catch (ExecutionException e3) {
                return isCancelled ? new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(zzet)), e3)) : new zzc(e3.getCause());
            } catch (CancellationException e4) {
                return !isCancelled ? new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(zzet)), e4)) : new zzb(false, e4);
            } catch (Error | Exception e5) {
                return new zzc(e5);
            }
        }
    }

    private static Object zzr(Future future) throws ExecutionException {
        Object obj;
        boolean z2 = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private final void zzs(StringBuilder sb) {
        try {
            Object zzr = zzr(this);
            sb.append("SUCCESS, result=[");
            if (zzr == null) {
                sb.append(AbstractJsonLexerKt.NULL);
            } else if (zzr == this) {
                sb.append("this future");
            } else {
                sb.append(zzr.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zzr)));
            }
            sb.append("]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (Exception e4) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e4.getClass());
            sb.append(" thrown from get()]");
        }
    }

    private final void zzt(StringBuilder sb) {
        String str;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzu(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                str = zzba.zza(zzf());
            } catch (Exception | StackOverflowError e3) {
                str = "Exception thrown from implementation: ".concat(String.valueOf(e3.getClass()));
            }
            if (str != null) {
                a.o(sb, ", info=[", str, "]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            zzs(sb);
        }
    }

    private final void zzu(StringBuilder sb, @CheckForNull Object obj) {
        if (obj == this) {
            try {
                sb.append("this future");
            } catch (Exception | StackOverflowError e3) {
                sb.append("Exception thrown from implementation: ");
                sb.append(e3.getClass());
            }
        } else {
            sb.append(obj);
        }
    }

    /* access modifiers changed from: private */
    public static void zzv(zzdz<V> zzdz, boolean z2) {
        zzd zzd2 = null;
        while (true) {
            for (zzj zzb2 = zzc.zzb(zzdz, zzj.zza); zzb2 != null; zzb2 = zzb2.next) {
                Thread thread = zzb2.thread;
                if (thread != null) {
                    zzb2.thread = null;
                    LockSupport.unpark(thread);
                }
            }
            zzdz.zzm();
            zzd zzd3 = zzd2;
            zzd zza2 = zzc.zza(zzdz, zzd.zza);
            zzd zzd4 = zzd3;
            while (zza2 != null) {
                zzd zzd5 = zza2.next;
                zza2.next = zzd4;
                zzd4 = zza2;
                zza2 = zzd5;
            }
            while (zzd4 != null) {
                Runnable runnable = zzd4.zzb;
                zzd zzd6 = zzd4.next;
                Objects.requireNonNull(runnable);
                Runnable runnable2 = runnable;
                if (runnable2 instanceof zzf) {
                    zzf zzf2 = (zzf) runnable2;
                    zzdz = zzf2.zza;
                    if (zzdz.value == zzf2) {
                        if (zzc.zzf(zzdz, zzf2, zzq(zzf2.zzb))) {
                            zzd2 = zzd6;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = zzd4.zzc;
                    Objects.requireNonNull(executor);
                    zzw(runnable2, executor);
                }
                zzd4 = zzd6;
            }
            return;
        }
    }

    private static void zzw(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e3) {
            zzb.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "executeListener", C0118y.f("RuntimeException while executing runnable ", String.valueOf(runnable), " with executor ", String.valueOf(executor)), e3);
        }
    }

    private final void zzx(zzj zzj2) {
        zzj2.thread = null;
        while (true) {
            zzj zzj3 = this.waiters;
            if (zzj3 != zzj.zza) {
                zzj zzj4 = null;
                while (zzj3 != null) {
                    zzj zzj5 = zzj3.next;
                    if (zzj3.thread != null) {
                        zzj4 = zzj3;
                    } else if (zzj4 != null) {
                        zzj4.next = zzj5;
                        if (zzj4.thread == null) {
                        }
                    } else if (!zzc.zzg(this, zzj3, zzj5)) {
                    }
                    zzj3 = zzj5;
                }
                return;
            }
            return;
        }
    }

    private static final Object zzy(Object obj) throws ExecutionException {
        if (obj instanceof zzb) {
            Throwable th = ((zzb) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        } else if (obj == zzd) {
            return null;
        } else {
            return obj;
        }
    }

    public final boolean cancel(boolean z2) {
        zzb zzb2;
        Object obj = this.value;
        if (!(obj instanceof zzf) && !(obj == null)) {
            return false;
        }
        if (zza) {
            zzb2 = new zzb(z2, new CancellationException("Future.cancel() was called."));
        } else {
            zzb2 = z2 ? zzb.zza : zzb.zzb;
            Objects.requireNonNull(zzb2);
        }
        boolean z3 = false;
        while (true) {
            if (zzc.zzf(this, obj, zzb2)) {
                zzv(this, z2);
                if (!(obj instanceof zzf)) {
                    break;
                }
                zzet<? extends V> zzet = ((zzf) obj).zzb;
                if (!(zzet instanceof zzh)) {
                    zzet.cancel(z2);
                    break;
                }
                this = (zzdz) zzet;
                obj = this.value;
                if (!(obj == null) && !(obj instanceof zzf)) {
                    break;
                }
                z3 = true;
            } else {
                obj = this.value;
                if (!(obj instanceof zzf)) {
                    return z3;
                }
            }
        }
        return true;
    }

    public final Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                return zzy(obj2);
            }
            zzj zzj2 = this.waiters;
            if (zzj2 != zzj.zza) {
                zzj zzj3 = new zzj();
                do {
                    zza zza2 = zzc;
                    zza2.zzc(zzj3, zzj2);
                    if (zza2.zzg(this, zzj2, zzj3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                zzx(zzj3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof zzf))));
                        return zzy(obj);
                    }
                    zzj2 = this.waiters;
                } while (zzj2 != zzj.zza);
            }
            Object obj3 = this.value;
            Objects.requireNonNull(obj3);
            return zzy(obj3);
        }
        throw new InterruptedException();
    }

    public final boolean isCancelled() {
        return this.value instanceof zzb;
    }

    public final boolean isDone() {
        Object obj = this.value;
        return (obj != null) & (!(obj instanceof zzf));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.value instanceof zzb) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zzs(sb);
        } else {
            zzt(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    @CheckForNull
    public String zzf() {
        if (this instanceof ScheduledFuture) {
            return a.j("remaining delay=[", ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS), " ms]");
        }
        return null;
    }

    @CheckForNull
    public final Throwable zzg() {
        if (!(this instanceof zzh)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    public final void zzl(Runnable runnable, Executor executor) {
        zzd zzd2;
        zzaz.zzc(executor, "Executor was null.");
        if (isDone() || (zzd2 = this.listeners) == zzd.zza) {
            zzw(runnable, executor);
        }
        zzd zzd3 = new zzd(runnable, executor);
        do {
            zzd3.next = zzd2;
            if (!zzc.zze(this, zzd2, zzd3)) {
                zzd2 = this.listeners;
            } else {
                return;
            }
        } while (zzd2 != zzd.zza);
        zzw(runnable, executor);
    }

    public void zzm() {
    }

    public final boolean zzn(Throwable th) {
        if (!zzc.zzf(this, (Object) null, new zzc(th))) {
            return false;
        }
        zzv(this, false);
        return true;
    }

    public final boolean zzo(zzet zzet) {
        zzc zzc2;
        zzet.getClass();
        Object obj = this.value;
        if (obj == null) {
            if (zzet.isDone()) {
                if (!zzc.zzf(this, (Object) null, zzq(zzet))) {
                    return false;
                }
                zzv(this, false);
                return true;
            }
            zzf zzf2 = new zzf(this, zzet);
            if (zzc.zzf(this, (Object) null, zzf2)) {
                try {
                    zzet.zzl(zzf2, zzee.INSTANCE);
                } catch (Throwable th) {
                    try {
                        zzc2 = new zzc(th);
                    } catch (Error | Exception unused) {
                        zzc2 = zzc.zza;
                    }
                    zzc.zzf(this, zzf2, zzc2);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof zzb) {
            zzet.cancel(((zzb) obj).zzc);
        }
        return false;
    }

    public final boolean zzp() {
        Object obj = this.value;
        return (obj instanceof zzb) && ((zzb) obj).zzc;
    }

    public final Object get(long j2, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long j3 = j2;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j3);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z2 = true;
            if ((obj != null) && (!(obj instanceof zzf))) {
                return zzy(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                zzj zzj2 = this.waiters;
                if (zzj2 != zzj.zza) {
                    zzj zzj3 = new zzj();
                    do {
                        zza zza2 = zzc;
                        zza2.zzc(zzj3, zzj2);
                        if (zza2.zzg(this, zzj2, zzj3)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                                        return zzy(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    zzx(zzj3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            zzx(zzj3);
                        } else {
                            zzj2 = this.waiters;
                        }
                    } while (zzj2 != zzj.zza);
                }
                Object obj3 = this.value;
                Objects.requireNonNull(obj3);
                return zzy(obj3);
            }
            while (nanos > 0) {
                Object obj4 = this.value;
                if ((obj4 != null) && (!(obj4 instanceof zzf))) {
                    return zzy(obj4);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String zzdz = toString();
            String obj5 = timeUnit.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = obj5.toLowerCase(locale);
            String str = "Waited " + j3 + StringUtils.SPACE + timeUnit.toString().toLowerCase(locale);
            if (nanos + 1000 < 0) {
                String concat = str.concat(" (plus ");
                long j4 = -nanos;
                long convert = timeUnit2.convert(j4, TimeUnit.NANOSECONDS);
                long nanos2 = j4 - timeUnit2.toNanos(convert);
                int i3 = (convert > 0 ? 1 : (convert == 0 ? 0 : -1));
                if (i3 != 0 && nanos2 <= 1000) {
                    z2 = false;
                }
                if (i3 > 0) {
                    String str2 = concat + convert + StringUtils.SPACE + lowerCase;
                    if (z2) {
                        str2 = str2.concat(",");
                    }
                    concat = str2.concat(StringUtils.SPACE);
                }
                if (z2) {
                    concat = concat + nanos2 + " nanoseconds ";
                }
                str = concat.concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(str.concat(" but future completed as timeout expired"));
            }
            throw new TimeoutException(android.support.v4.media.session.a.n(str, " for ", zzdz));
        }
        throw new InterruptedException();
    }
}
