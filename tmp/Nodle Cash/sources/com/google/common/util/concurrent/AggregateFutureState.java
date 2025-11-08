package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ReflectionSupport(ReflectionSupport.Level.FULL)
@ElementTypesAreNonnullByDefault
abstract class AggregateFutureState<OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log;
    private volatile int remaining;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile Set<Throwable> seenExceptions = null;

    public static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        public abstract void compareAndSetSeenExceptions(AggregateFutureState<?> aggregateFutureState, @CheckForNull Set<Throwable> set, Set<Throwable> set2);

        public abstract int decrementAndGetRemainingCount(AggregateFutureState<?> aggregateFutureState);
    }

    public static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicIntegerFieldUpdater<AggregateFutureState<?>> remainingCountUpdater;
        final AtomicReferenceFieldUpdater<AggregateFutureState<?>, Set<Throwable>> seenExceptionsUpdater;

        public SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.seenExceptionsUpdater = atomicReferenceFieldUpdater;
            this.remainingCountUpdater = atomicIntegerFieldUpdater;
        }

        /* JADX WARNING: Removed duplicated region for block: B:1:0x0002 A[LOOP:0: B:1:0x0002->B:4:0x000d, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void compareAndSetSeenExceptions(com.google.common.util.concurrent.AggregateFutureState<?> r2, @javax.annotation.CheckForNull java.util.Set<java.lang.Throwable> r3, java.util.Set<java.lang.Throwable> r4) {
            /*
                r1 = this;
                java.util.concurrent.atomic.AtomicReferenceFieldUpdater<com.google.common.util.concurrent.AggregateFutureState<?>, java.util.Set<java.lang.Throwable>> r1 = r1.seenExceptionsUpdater
            L_0x0002:
                boolean r0 = r1.compareAndSet(r2, r3, r4)
                if (r0 == 0) goto L_0x0009
                goto L_0x000f
            L_0x0009:
                java.lang.Object r0 = r1.get(r2)
                if (r0 == r3) goto L_0x0002
            L_0x000f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AggregateFutureState.SafeAtomicHelper.compareAndSetSeenExceptions(com.google.common.util.concurrent.AggregateFutureState, java.util.Set, java.util.Set):void");
        }

        public int decrementAndGetRemainingCount(AggregateFutureState<?> aggregateFutureState) {
            return this.remainingCountUpdater.decrementAndGet(aggregateFutureState);
        }
    }

    public static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        public void compareAndSetSeenExceptions(AggregateFutureState<?> aggregateFutureState, @CheckForNull Set<Throwable> set, Set<Throwable> set2) {
            synchronized (aggregateFutureState) {
                try {
                    if (aggregateFutureState.seenExceptions == set) {
                        Set unused = aggregateFutureState.seenExceptions = set2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public int decrementAndGetRemainingCount(AggregateFutureState<?> aggregateFutureState) {
            int access$306;
            synchronized (aggregateFutureState) {
                access$306 = AggregateFutureState.access$306(aggregateFutureState);
            }
            return access$306;
        }
    }

    static {
        AtomicHelper atomicHelper;
        Class<AggregateFutureState> cls = AggregateFutureState.class;
        log = Logger.getLogger(cls.getName());
        Throwable th = null;
        try {
            atomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(cls, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(cls, "remaining"));
        } catch (Error | RuntimeException e3) {
            atomicHelper = new SynchronizedAtomicHelper();
            th = e3;
        }
        ATOMIC_HELPER = atomicHelper;
        if (th != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    public AggregateFutureState(int i3) {
        this.remaining = i3;
    }

    public static /* synthetic */ int access$306(AggregateFutureState aggregateFutureState) {
        int i3 = aggregateFutureState.remaining - 1;
        aggregateFutureState.remaining = i3;
        return i3;
    }

    public abstract void addInitialException(Set<Throwable> set);

    public final void clearSeenExceptions() {
        this.seenExceptions = null;
    }

    public final int decrementRemainingAndGet() {
        return ATOMIC_HELPER.decrementAndGetRemainingCount(this);
    }

    public final Set<Throwable> getOrInitSeenExceptions() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set newConcurrentHashSet = Sets.newConcurrentHashSet();
        addInitialException(newConcurrentHashSet);
        ATOMIC_HELPER.compareAndSetSeenExceptions(this, (Set<Throwable>) null, newConcurrentHashSet);
        Set<Throwable> set2 = this.seenExceptions;
        Objects.requireNonNull(set2);
        return set2;
    }
}
