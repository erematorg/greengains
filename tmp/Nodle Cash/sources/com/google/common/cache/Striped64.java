package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import java.util.Random;
import javax.annotation.CheckForNull;
import sun.misc.Unsafe;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class Striped64 extends Number {
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    static final Random rng = new Random();
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    volatile transient long base;
    volatile transient int busy;
    @CheckForNull
    volatile transient Cell[] cells;

    public static final class Cell {
        private static final Unsafe UNSAFE;
        private static final long valueOffset;

        /* renamed from: p0  reason: collision with root package name */
        volatile long f6855p0;

        /* renamed from: p1  reason: collision with root package name */
        volatile long f6856p1;
        volatile long p2;
        volatile long p3;
        volatile long p4;
        volatile long p5;
        volatile long p6;

        /* renamed from: q0  reason: collision with root package name */
        volatile long f6857q0;

        /* renamed from: q1  reason: collision with root package name */
        volatile long f6858q1;
        volatile long q2;
        volatile long q3;
        volatile long q4;
        volatile long q5;
        volatile long q6;
        volatile long value;

        static {
            try {
                Unsafe access$000 = Striped64.getUnsafe();
                UNSAFE = access$000;
                valueOffset = access$000.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e3) {
                throw new Error(e3);
            }
        }

        public Cell(long j2) {
            this.value = j2;
        }

        public final boolean cas(long j2, long j3) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j2, j3);
        }
    }

    static {
        try {
            Unsafe unsafe = getUnsafe();
            UNSAFE = unsafe;
            Class<Striped64> cls = Striped64.class;
            baseOffset = unsafe.objectFieldOffset(cls.getDeclaredField(TtmlNode.RUBY_BASE));
            busyOffset = unsafe.objectFieldOffset(cls.getDeclaredField("busy"));
        } catch (Exception e3) {
            throw new Error(e3);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        return (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.cache.Striped64.AnonymousClass1());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static sun.misc.Unsafe getUnsafe() {
        /*
            sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
            return r0
        L_0x0005:
            com.google.common.cache.Striped64$1 r0 = new com.google.common.cache.Striped64$1     // Catch:{ PrivilegedActionException -> 0x0011 }
            r0.<init>()     // Catch:{ PrivilegedActionException -> 0x0011 }
            java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x0011 }
            sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x0011 }
            return r0
        L_0x0011:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Could not initialize intrinsics"
            java.lang.Throwable r0 = r0.getCause()
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.Striped64.getUnsafe():sun.misc.Unsafe");
    }

    public final boolean casBase(long j2, long j3) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j2, j3);
    }

    public final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    public abstract long fn(long j2, long j3);

    public final void internalReset(long j2) {
        Cell[] cellArr = this.cells;
        this.base = j2;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = j2;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:83:0x00f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0023 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void retryUpdate(long r17, @javax.annotation.CheckForNull int[] r19, boolean r20) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r0 = 1
            r4 = 0
            if (r19 != 0) goto L_0x001b
            java.lang.ThreadLocal<int[]> r5 = threadHashCode
            int[] r6 = new int[r0]
            r5.set(r6)
            java.util.Random r5 = rng
            int r5 = r5.nextInt()
            if (r5 != 0) goto L_0x0018
            r5 = r0
        L_0x0018:
            r6[r4] = r5
            goto L_0x001f
        L_0x001b:
            r5 = r19[r4]
            r6 = r19
        L_0x001f:
            r8 = r4
            r7 = r5
            r5 = r20
        L_0x0023:
            com.google.common.cache.Striped64$Cell[] r9 = r1.cells
            if (r9 == 0) goto L_0x00b8
            int r10 = r9.length
            if (r10 <= 0) goto L_0x00b8
            int r11 = r10 + -1
            r11 = r11 & r7
            r11 = r9[r11]
            if (r11 != 0) goto L_0x0064
            int r9 = r1.busy
            if (r9 != 0) goto L_0x0062
            com.google.common.cache.Striped64$Cell r9 = new com.google.common.cache.Striped64$Cell
            r9.<init>(r2)
            int r10 = r1.busy
            if (r10 != 0) goto L_0x0062
            boolean r10 = r16.casBusy()
            if (r10 == 0) goto L_0x0062
            com.google.common.cache.Striped64$Cell[] r10 = r1.cells     // Catch:{ all -> 0x0056 }
            if (r10 == 0) goto L_0x0058
            int r11 = r10.length     // Catch:{ all -> 0x0056 }
            if (r11 <= 0) goto L_0x0058
            int r11 = r11 + -1
            r11 = r11 & r7
            r12 = r10[r11]     // Catch:{ all -> 0x0056 }
            if (r12 != 0) goto L_0x0058
            r10[r11] = r9     // Catch:{ all -> 0x0056 }
            r9 = r0
            goto L_0x0059
        L_0x0056:
            r0 = move-exception
            goto L_0x005f
        L_0x0058:
            r9 = r4
        L_0x0059:
            r1.busy = r4
            if (r9 == 0) goto L_0x0023
            goto L_0x00f1
        L_0x005f:
            r1.busy = r4
            throw r0
        L_0x0062:
            r8 = r4
            goto L_0x00ab
        L_0x0064:
            if (r5 != 0) goto L_0x0068
            r5 = r0
            goto L_0x00ab
        L_0x0068:
            long r12 = r11.value
            long r14 = r1.fn(r12, r2)
            boolean r11 = r11.cas(r12, r14)
            if (r11 == 0) goto L_0x0076
            goto L_0x00f1
        L_0x0076:
            int r11 = NCPU
            if (r10 >= r11) goto L_0x0062
            com.google.common.cache.Striped64$Cell[] r11 = r1.cells
            if (r11 == r9) goto L_0x007f
            goto L_0x0062
        L_0x007f:
            if (r8 != 0) goto L_0x0083
            r8 = r0
            goto L_0x00ab
        L_0x0083:
            int r11 = r1.busy
            if (r11 != 0) goto L_0x00ab
            boolean r11 = r16.casBusy()
            if (r11 == 0) goto L_0x00ab
            com.google.common.cache.Striped64$Cell[] r8 = r1.cells     // Catch:{ all -> 0x009f }
            if (r8 != r9) goto L_0x00a3
            int r8 = r10 << 1
            com.google.common.cache.Striped64$Cell[] r8 = new com.google.common.cache.Striped64.Cell[r8]     // Catch:{ all -> 0x009f }
            r11 = r4
        L_0x0096:
            if (r11 >= r10) goto L_0x00a1
            r12 = r9[r11]     // Catch:{ all -> 0x009f }
            r8[r11] = r12     // Catch:{ all -> 0x009f }
            int r11 = r11 + 1
            goto L_0x0096
        L_0x009f:
            r0 = move-exception
            goto L_0x00a8
        L_0x00a1:
            r1.cells = r8     // Catch:{ all -> 0x009f }
        L_0x00a3:
            r1.busy = r4
            r8 = r4
            goto L_0x0023
        L_0x00a8:
            r1.busy = r4
            throw r0
        L_0x00ab:
            int r9 = r7 << 13
            r7 = r7 ^ r9
            int r9 = r7 >>> 17
            r7 = r7 ^ r9
            int r9 = r7 << 5
            r7 = r7 ^ r9
            r6[r4] = r7
            goto L_0x0023
        L_0x00b8:
            int r10 = r1.busy
            if (r10 != 0) goto L_0x00e5
            com.google.common.cache.Striped64$Cell[] r10 = r1.cells
            if (r10 != r9) goto L_0x00e5
            boolean r10 = r16.casBusy()
            if (r10 == 0) goto L_0x00e5
            com.google.common.cache.Striped64$Cell[] r10 = r1.cells     // Catch:{ all -> 0x00da }
            if (r10 != r9) goto L_0x00dc
            r9 = 2
            com.google.common.cache.Striped64$Cell[] r9 = new com.google.common.cache.Striped64.Cell[r9]     // Catch:{ all -> 0x00da }
            r10 = r7 & 1
            com.google.common.cache.Striped64$Cell r11 = new com.google.common.cache.Striped64$Cell     // Catch:{ all -> 0x00da }
            r11.<init>(r2)     // Catch:{ all -> 0x00da }
            r9[r10] = r11     // Catch:{ all -> 0x00da }
            r1.cells = r9     // Catch:{ all -> 0x00da }
            r9 = r0
            goto L_0x00dd
        L_0x00da:
            r0 = move-exception
            goto L_0x00e2
        L_0x00dc:
            r9 = r4
        L_0x00dd:
            r1.busy = r4
            if (r9 == 0) goto L_0x0023
            goto L_0x00f1
        L_0x00e2:
            r1.busy = r4
            throw r0
        L_0x00e5:
            long r9 = r1.base
            long r11 = r1.fn(r9, r2)
            boolean r9 = r1.casBase(r9, r11)
            if (r9 == 0) goto L_0x0023
        L_0x00f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.Striped64.retryUpdate(long, int[], boolean):void");
    }
}
