package com.google.common.util.concurrent;

import com.appsamurai.storyly.exoplayer2.core.mediacodec.b;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;

    public static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        public L getAt(int i3) {
            return this.array[i3];
        }

        public int size() {
            return this.array.length;
        }

        private CompactStriped(int i3, Supplier<L> supplier) {
            super(i3);
            int i4 = 0;
            Preconditions.checkArgument(i3 <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[(this.mask + 1)];
            while (true) {
                Object[] objArr = this.array;
                if (i4 < objArr.length) {
                    objArr[i4] = supplier.get();
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    @VisibleForTesting
    public static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final int size;
        final Supplier<L> supplier;

        public LargeLazyStriped(int i3, Supplier<L> supplier2) {
            super(i3);
            int i4 = this.mask;
            this.size = i4 == -1 ? Integer.MAX_VALUE : i4 + 1;
            this.supplier = supplier2;
            this.locks = new MapMaker().weakValues().makeMap();
        }

        public L getAt(int i3) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i3, size());
            }
            L l2 = this.locks.get(Integer.valueOf(i3));
            if (l2 != null) {
                return l2;
            }
            L l3 = this.supplier.get();
            return MoreObjects.firstNonNull(this.locks.putIfAbsent(Integer.valueOf(i3), l3), l3);
        }

        public int size() {
            return this.size;
        }
    }

    public static class PaddedLock extends ReentrantLock {
        long unused1;
        long unused2;
        long unused3;

        public PaddedLock() {
            super(false);
        }
    }

    public static class PaddedSemaphore extends Semaphore {
        long unused1;
        long unused2;
        long unused3;

        public PaddedSemaphore(int i3) {
            super(i3, false);
        }
    }

    public static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int mask;

        public PowerOfTwoStriped(int i3) {
            super();
            Preconditions.checkArgument(i3 > 0, "Stripes must be positive");
            this.mask = i3 > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(i3) - 1;
        }

        public final L get(Object obj) {
            return getAt(indexFor(obj));
        }

        public final int indexFor(Object obj) {
            return this.mask & Striped.smear(obj.hashCode());
        }
    }

    @VisibleForTesting
    public static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> locks;
        final ReferenceQueue<L> queue = new ReferenceQueue<>();
        final int size;
        final Supplier<L> supplier;

        public static final class ArrayReference<L> extends WeakReference<L> {
            final int index;

            public ArrayReference(L l2, int i3, ReferenceQueue<L> referenceQueue) {
                super(l2, referenceQueue);
                this.index = i3;
            }
        }

        public SmallLazyStriped(int i3, Supplier<L> supplier2) {
            super(i3);
            int i4 = this.mask;
            int i5 = i4 == -1 ? Integer.MAX_VALUE : i4 + 1;
            this.size = i5;
            this.locks = new AtomicReferenceArray<>(i5);
            this.supplier = supplier2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:3:0x000e A[LOOP:1: B:3:0x000e->B:6:0x001a, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void drainQueue() {
            /*
                r4 = this;
            L_0x0000:
                java.lang.ref.ReferenceQueue<L> r0 = r4.queue
                java.lang.ref.Reference r0 = r0.poll()
                if (r0 == 0) goto L_0x001d
                com.google.common.util.concurrent.Striped$SmallLazyStriped$ArrayReference r0 = (com.google.common.util.concurrent.Striped.SmallLazyStriped.ArrayReference) r0
                java.util.concurrent.atomic.AtomicReferenceArray<com.google.common.util.concurrent.Striped$SmallLazyStriped$ArrayReference<? extends L>> r1 = r4.locks
                int r2 = r0.index
            L_0x000e:
                r3 = 0
                boolean r3 = r1.compareAndSet(r2, r0, r3)
                if (r3 == 0) goto L_0x0016
                goto L_0x0000
            L_0x0016:
                java.lang.Object r3 = r1.get(r2)
                if (r3 == r0) goto L_0x000e
                goto L_0x0000
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Striped.SmallLazyStriped.drainQueue():void");
        }

        public L getAt(int i3) {
            L l2;
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(i3, size());
            }
            ArrayReference<? extends L> arrayReference = this.locks.get(i3);
            L l3 = arrayReference == null ? null : arrayReference.get();
            if (l3 != null) {
                return l3;
            }
            L l4 = this.supplier.get();
            ArrayReference arrayReference2 = new ArrayReference(l4, i3, this.queue);
            do {
                AtomicReferenceArray<ArrayReference<? extends L>> atomicReferenceArray = this.locks;
                while (!atomicReferenceArray.compareAndSet(i3, arrayReference, arrayReference2)) {
                    if (atomicReferenceArray.get(i3) != arrayReference) {
                        arrayReference = this.locks.get(i3);
                        if (arrayReference == null) {
                            l2 = null;
                            continue;
                        } else {
                            l2 = arrayReference.get();
                            continue;
                        }
                    }
                }
                drainQueue();
                return l4;
            } while (l2 == null);
            return l2;
        }

        public int size() {
            return this.size;
        }
    }

    public static final class WeakSafeCondition extends ForwardingCondition {
        private final Condition delegate;
        private final WeakSafeReadWriteLock strongReference;

        public WeakSafeCondition(Condition condition, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = condition;
            this.strongReference = weakSafeReadWriteLock;
        }

        public Condition delegate() {
            return this.delegate;
        }
    }

    public static final class WeakSafeLock extends ForwardingLock {
        private final Lock delegate;
        private final WeakSafeReadWriteLock strongReference;

        public WeakSafeLock(Lock lock, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = lock;
            this.strongReference = weakSafeReadWriteLock;
        }

        public Lock delegate() {
            return this.delegate;
        }

        public Condition newCondition() {
            return new WeakSafeCondition(this.delegate.newCondition(), this.strongReference);
        }
    }

    public static final class WeakSafeReadWriteLock implements ReadWriteLock {
        private final ReadWriteLock delegate = new ReentrantReadWriteLock();

        public Lock readLock() {
            return new WeakSafeLock(this.delegate.readLock(), this);
        }

        public Lock writeLock() {
            return new WeakSafeLock(this.delegate.writeLock(), this);
        }
    }

    /* access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int i3) {
        return 1 << IntMath.log2(i3, RoundingMode.CEILING);
    }

    public static <L> Striped<L> custom(int i3, Supplier<L> supplier) {
        return new CompactStriped(i3, supplier);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Lock lambda$lazyWeakLock$0() {
        return new ReentrantLock(false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Semaphore lambda$lazyWeakSemaphore$2(int i3) {
        return new Semaphore(i3, false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Semaphore lambda$semaphore$1(int i3) {
        return new PaddedSemaphore(i3);
    }

    private static <L> Striped<L> lazy(int i3, Supplier<L> supplier) {
        return i3 < 1024 ? new SmallLazyStriped(i3, supplier) : new LargeLazyStriped(i3, supplier);
    }

    public static Striped<Lock> lazyWeakLock(int i3) {
        return lazy(i3, new o(2));
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int i3) {
        return lazy(i3, new o(0));
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int i3, int i4) {
        return lazy(i3, new b(i4, 3));
    }

    public static Striped<Lock> lock(int i3) {
        return custom(i3, new o(1));
    }

    public static Striped<ReadWriteLock> readWriteLock(int i3) {
        return custom(i3, new o(3));
    }

    public static Striped<Semaphore> semaphore(int i3, int i4) {
        return custom(i3, new b(i4, 2));
    }

    /* access modifiers changed from: private */
    public static int smear(int i3) {
        int i4 = i3 ^ ((i3 >>> 20) ^ (i3 >>> 12));
        return (i4 >>> 4) ^ ((i4 >>> 7) ^ i4);
    }

    public Iterable<L> bulkGet(Iterable<? extends Object> iterable) {
        ArrayList<E> newArrayList = Lists.newArrayList(iterable);
        if (newArrayList.isEmpty()) {
            return ImmutableList.of();
        }
        int[] iArr = new int[newArrayList.size()];
        for (int i3 = 0; i3 < newArrayList.size(); i3++) {
            iArr[i3] = indexFor(newArrayList.get(i3));
        }
        Arrays.sort(iArr);
        int i4 = iArr[0];
        newArrayList.set(0, getAt(i4));
        for (int i5 = 1; i5 < newArrayList.size(); i5++) {
            int i6 = iArr[i5];
            if (i6 == i4) {
                newArrayList.set(i5, newArrayList.get(i5 - 1));
            } else {
                newArrayList.set(i5, getAt(i6));
                i4 = i6;
            }
        }
        return Collections.unmodifiableList(newArrayList);
    }

    public abstract L get(Object obj);

    public abstract L getAt(int i3);

    public abstract int indexFor(Object obj);

    public abstract int size();

    private Striped() {
    }
}
