package com.google.common.util.concurrent;

import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Functions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Closeable;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@DoNotMock("Use ClosingFuture.from(Futures.immediate*Future)")
public final class ClosingFuture<V> {
    private static final Logger logger = Logger.getLogger(ClosingFuture.class.getName());
    /* access modifiers changed from: private */
    public final CloseableList closeables;
    /* access modifiers changed from: private */
    public final FluentFuture<V> future;
    private final AtomicReference<State> state;

    /* renamed from: com.google.common.util.concurrent.ClosingFuture$11  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.common.util.concurrent.ClosingFuture$State[] r0 = com.google.common.util.concurrent.ClosingFuture.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State = r0
                com.google.common.util.concurrent.ClosingFuture$State r1 = com.google.common.util.concurrent.ClosingFuture.State.SUBSUMED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.common.util.concurrent.ClosingFuture$State r1 = com.google.common.util.concurrent.ClosingFuture.State.WILL_CREATE_VALUE_AND_CLOSER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.common.util.concurrent.ClosingFuture$State r1 = com.google.common.util.concurrent.ClosingFuture.State.WILL_CLOSE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.common.util.concurrent.ClosingFuture$State r1 = com.google.common.util.concurrent.ClosingFuture.State.CLOSING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.common.util.concurrent.ClosingFuture$State r1 = com.google.common.util.concurrent.ClosingFuture.State.CLOSED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$common$util$concurrent$ClosingFuture$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.common.util.concurrent.ClosingFuture$State r1 = com.google.common.util.concurrent.ClosingFuture.State.OPEN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ClosingFuture.AnonymousClass11.<clinit>():void");
        }
    }

    public interface AsyncClosingCallable<V> {
        ClosingFuture<V> call(DeferredCloser deferredCloser) throws Exception;
    }

    public interface AsyncClosingFunction<T, U> {
        ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness T t2) throws Exception;
    }

    public static final class CloseableList extends IdentityHashMap<Closeable, Executor> implements Closeable {
        private volatile boolean closed;
        /* access modifiers changed from: private */
        public final DeferredCloser closer;
        @CheckForNull
        private volatile CountDownLatch whenClosed;

        private CloseableList() {
            this.closer = new DeferredCloser(this);
        }

        public void add(@CheckForNull Closeable closeable, Executor executor) {
            Preconditions.checkNotNull(executor);
            if (closeable != null) {
                synchronized (this) {
                    try {
                        if (!this.closed) {
                            put(closeable, executor);
                        } else {
                            ClosingFuture.closeQuietly(closeable, executor);
                        }
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
            }
        }

        public <V, U> FluentFuture<U> applyAsyncClosingFunction(AsyncClosingFunction<V, U> asyncClosingFunction, @ParametricNullness V v2) throws Exception {
            CloseableList closeableList = new CloseableList();
            try {
                ClosingFuture<U> apply = asyncClosingFunction.apply(closeableList.closer, v2);
                apply.becomeSubsumedInto(closeableList);
                return apply.future;
            } finally {
                add(closeableList, MoreExecutors.directExecutor());
            }
        }

        public <V, U> ListenableFuture<U> applyClosingFunction(ClosingFunction<? super V, U> closingFunction, @ParametricNullness V v2) throws Exception {
            CloseableList closeableList = new CloseableList();
            try {
                return Futures.immediateFuture(closingFunction.apply(closeableList.closer, v2));
            } finally {
                add(closeableList, MoreExecutors.directExecutor());
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0012, code lost:
            r0 = entrySet().iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x001e, code lost:
            if (r0.hasNext() == false) goto L_0x0036;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
            r1 = (java.util.Map.Entry) r0.next();
            com.google.common.util.concurrent.ClosingFuture.access$3200((java.io.Closeable) r1.getKey(), (java.util.concurrent.Executor) r1.getValue());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
            clear();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
            if (r3.whenClosed == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
            r3.whenClosed.countDown();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() {
            /*
                r3 = this;
                boolean r0 = r3.closed
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r3)
                boolean r0 = r3.closed     // Catch:{ all -> 0x000c }
                if (r0 == 0) goto L_0x000e
                monitor-exit(r3)     // Catch:{ all -> 0x000c }
                return
            L_0x000c:
                r0 = move-exception
                goto L_0x0043
            L_0x000e:
                r0 = 1
                r3.closed = r0     // Catch:{ all -> 0x000c }
                monitor-exit(r3)     // Catch:{ all -> 0x000c }
                java.util.Set r0 = r3.entrySet()
                java.util.Iterator r0 = r0.iterator()
            L_0x001a:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0036
                java.lang.Object r1 = r0.next()
                java.util.Map$Entry r1 = (java.util.Map.Entry) r1
                java.lang.Object r2 = r1.getKey()
                java.io.Closeable r2 = (java.io.Closeable) r2
                java.lang.Object r1 = r1.getValue()
                java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
                com.google.common.util.concurrent.ClosingFuture.closeQuietly(r2, r1)
                goto L_0x001a
            L_0x0036:
                r3.clear()
                java.util.concurrent.CountDownLatch r0 = r3.whenClosed
                if (r0 == 0) goto L_0x0042
                java.util.concurrent.CountDownLatch r3 = r3.whenClosed
                r3.countDown()
            L_0x0042:
                return
            L_0x0043:
                monitor-exit(r3)     // Catch:{ all -> 0x000c }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ClosingFuture.CloseableList.close():void");
        }

        public CountDownLatch whenClosedCountDown() {
            boolean z2 = false;
            if (this.closed) {
                return new CountDownLatch(0);
            }
            synchronized (this) {
                try {
                    if (this.closed) {
                        CountDownLatch countDownLatch = new CountDownLatch(0);
                        return countDownLatch;
                    }
                    if (this.whenClosed == null) {
                        z2 = true;
                    }
                    Preconditions.checkState(z2);
                    CountDownLatch countDownLatch2 = new CountDownLatch(1);
                    this.whenClosed = countDownLatch2;
                    return countDownLatch2;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public interface ClosingCallable<V> {
        @ParametricNullness
        V call(DeferredCloser deferredCloser) throws Exception;
    }

    public interface ClosingFunction<T, U> {
        @ParametricNullness
        U apply(DeferredCloser deferredCloser, @ParametricNullness T t2) throws Exception;
    }

    @DoNotMock("Use ClosingFuture.whenAllSucceed() or .whenAllComplete() instead.")
    public static class Combiner {
        private final boolean allMustSucceed;
        /* access modifiers changed from: private */
        public final CloseableList closeables;
        protected final ImmutableList<ClosingFuture<?>> inputs;

        public interface AsyncCombiningCallable<V> {
            ClosingFuture<V> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        public interface CombiningCallable<V> {
            @ParametricNullness
            V call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        private Futures.FutureCombiner<Object> futureCombiner() {
            return this.allMustSucceed ? Futures.whenAllSucceed(inputFutures()) : Futures.whenAllComplete(inputFutures());
        }

        private ImmutableList<FluentFuture<?>> inputFutures() {
            return FluentIterable.from(this.inputs).transform(new m(3)).toList();
        }

        public <V> ClosingFuture<V> call(final CombiningCallable<V> combiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>((ListenableFuture) futureCombiner().call(new Callable<V>() {
                @ParametricNullness
                public V call() throws Exception {
                    return new Peeker(Combiner.this.inputs).call(combiningCallable, Combiner.this.closeables);
                }

                public String toString() {
                    return combiningCallable.toString();
                }
            }, executor));
            closingFuture.closeables.add(this.closeables, MoreExecutors.directExecutor());
            return closingFuture;
        }

        public <V> ClosingFuture<V> callAsync(final AsyncCombiningCallable<V> asyncCombiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>((ListenableFuture) futureCombiner().callAsync(new AsyncCallable<V>() {
                public ListenableFuture<V> call() throws Exception {
                    return new Peeker(Combiner.this.inputs).callAsync(asyncCombiningCallable, Combiner.this.closeables);
                }

                public String toString() {
                    return asyncCombiningCallable.toString();
                }
            }, executor));
            closingFuture.closeables.add(this.closeables, MoreExecutors.directExecutor());
            return closingFuture;
        }

        private Combiner(boolean z2, Iterable<? extends ClosingFuture<?>> iterable) {
            this.closeables = new CloseableList();
            this.allMustSucceed = z2;
            this.inputs = ImmutableList.copyOf(iterable);
            for (ClosingFuture access$1000 : iterable) {
                access$1000.becomeSubsumedInto(this.closeables);
            }
        }
    }

    public static final class Combiner2<V1, V2> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;

        public interface AsyncClosingFunction2<V1, V2, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2) throws Exception;
        }

        public interface ClosingFunction2<V1, V2, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction2<V1, V2, U> closingFunction2, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction2.apply(deferredCloser, peeker.getDone(Combiner2.this.future1), peeker.getDone(Combiner2.this.future2));
                }

                public String toString() {
                    return closingFunction2.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction2<V1, V2, U> asyncClosingFunction2, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction2.apply(deferredCloser, peeker.getDone(Combiner2.this.future1), peeker.getDone(Combiner2.this.future2));
                }

                public String toString() {
                    return asyncClosingFunction2.toString();
                }
            }, executor);
        }

        private Combiner2(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2) {
            super(true, ImmutableList.of(closingFuture, closingFuture2));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
        }
    }

    public static final class Combiner3<V1, V2, V3> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;

        public interface AsyncClosingFunction3<V1, V2, V3, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3) throws Exception;
        }

        public interface ClosingFunction3<V1, V2, V3, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction3<V1, V2, V3, U> closingFunction3, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction3.apply(deferredCloser, peeker.getDone(Combiner3.this.future1), peeker.getDone(Combiner3.this.future2), peeker.getDone(Combiner3.this.future3));
                }

                public String toString() {
                    return closingFunction3.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction3<V1, V2, V3, U> asyncClosingFunction3, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction3.apply(deferredCloser, peeker.getDone(Combiner3.this.future1), peeker.getDone(Combiner3.this.future2), peeker.getDone(Combiner3.this.future3));
                }

                public String toString() {
                    return asyncClosingFunction3.toString();
                }
            }, executor);
        }

        private Combiner3(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3) {
            super(true, ImmutableList.of(closingFuture, closingFuture2, closingFuture3));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
            this.future3 = closingFuture3;
        }
    }

    public static final class Combiner4<V1, V2, V3, V4> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;
        /* access modifiers changed from: private */
        public final ClosingFuture<V4> future4;

        public interface AsyncClosingFunction4<V1, V2, V3, V4, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4) throws Exception;
        }

        public interface ClosingFunction4<V1, V2, V3, V4, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction4<V1, V2, V3, V4, U> closingFunction4, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction4.apply(deferredCloser, peeker.getDone(Combiner4.this.future1), peeker.getDone(Combiner4.this.future2), peeker.getDone(Combiner4.this.future3), peeker.getDone(Combiner4.this.future4));
                }

                public String toString() {
                    return closingFunction4.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction4<V1, V2, V3, V4, U> asyncClosingFunction4, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction4.apply(deferredCloser, peeker.getDone(Combiner4.this.future1), peeker.getDone(Combiner4.this.future2), peeker.getDone(Combiner4.this.future3), peeker.getDone(Combiner4.this.future4));
                }

                public String toString() {
                    return asyncClosingFunction4.toString();
                }
            }, executor);
        }

        private Combiner4(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4) {
            super(true, ImmutableList.of(closingFuture, closingFuture2, closingFuture3, closingFuture4));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
            this.future3 = closingFuture3;
            this.future4 = closingFuture4;
        }
    }

    public static final class Combiner5<V1, V2, V3, V4, V5> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;
        /* access modifiers changed from: private */
        public final ClosingFuture<V4> future4;
        /* access modifiers changed from: private */
        public final ClosingFuture<V5> future5;

        public interface AsyncClosingFunction5<V1, V2, V3, V4, V5, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4, @ParametricNullness V5 v5) throws Exception;
        }

        public interface ClosingFunction5<V1, V2, V3, V4, V5, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4, @ParametricNullness V5 v5) throws Exception;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction5<V1, V2, V3, V4, V5, U> closingFunction5, Executor executor) {
            return call(new Combiner.CombiningCallable<U>() {
                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction5.apply(deferredCloser, peeker.getDone(Combiner5.this.future1), peeker.getDone(Combiner5.this.future2), peeker.getDone(Combiner5.this.future3), peeker.getDone(Combiner5.this.future4), peeker.getDone(Combiner5.this.future5));
                }

                public String toString() {
                    return closingFunction5.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction5<V1, V2, V3, V4, V5, U> asyncClosingFunction5, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>() {
                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction5.apply(deferredCloser, peeker.getDone(Combiner5.this.future1), peeker.getDone(Combiner5.this.future2), peeker.getDone(Combiner5.this.future3), peeker.getDone(Combiner5.this.future4), peeker.getDone(Combiner5.this.future5));
                }

                public String toString() {
                    return asyncClosingFunction5.toString();
                }
            }, executor);
        }

        private Combiner5(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4, ClosingFuture<V5> closingFuture5) {
            super(true, ImmutableList.of(closingFuture, closingFuture2, closingFuture3, closingFuture4, closingFuture5));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
            this.future3 = closingFuture3;
            this.future4 = closingFuture4;
            this.future5 = closingFuture5;
        }
    }

    public static final class DeferredCloser {
        @RetainedWith
        private final CloseableList list;

        public DeferredCloser(CloseableList closeableList) {
            this.list = closeableList;
        }

        @ParametricNullness
        @CanIgnoreReturnValue
        public <C extends Closeable> C eventuallyClose(@ParametricNullness C c3, Executor executor) {
            Preconditions.checkNotNull(executor);
            if (c3 != null) {
                this.list.add((Closeable) c3, executor);
            }
            return c3;
        }
    }

    public static final class Peeker {
        private volatile boolean beingCalled;
        private final ImmutableList<ClosingFuture<?>> futures;

        /* access modifiers changed from: private */
        @ParametricNullness
        public <V> V call(Combiner.CombiningCallable<V> combiningCallable, CloseableList closeableList) throws Exception {
            this.beingCalled = true;
            CloseableList closeableList2 = new CloseableList();
            try {
                return combiningCallable.call(closeableList2.closer, this);
            } finally {
                closeableList.add(closeableList2, MoreExecutors.directExecutor());
                this.beingCalled = false;
            }
        }

        /* access modifiers changed from: private */
        public <V> FluentFuture<V> callAsync(Combiner.AsyncCombiningCallable<V> asyncCombiningCallable, CloseableList closeableList) throws Exception {
            this.beingCalled = true;
            CloseableList closeableList2 = new CloseableList();
            try {
                ClosingFuture<V> call = asyncCombiningCallable.call(closeableList2.closer, this);
                call.becomeSubsumedInto(closeableList);
                return call.future;
            } finally {
                closeableList.add(closeableList2, MoreExecutors.directExecutor());
                this.beingCalled = false;
            }
        }

        @ParametricNullness
        public final <D> D getDone(ClosingFuture<D> closingFuture) throws ExecutionException {
            Preconditions.checkState(this.beingCalled);
            Preconditions.checkArgument(this.futures.contains(closingFuture));
            return Futures.getDone(closingFuture.future);
        }

        private Peeker(ImmutableList<ClosingFuture<?>> immutableList) {
            this.futures = (ImmutableList) Preconditions.checkNotNull(immutableList);
        }
    }

    public enum State {
        OPEN,
        SUBSUMED,
        WILL_CLOSE,
        CLOSING,
        CLOSED,
        WILL_CREATE_VALUE_AND_CLOSER
    }

    public static final class ValueAndCloser<V> {
        private final ClosingFuture<? extends V> closingFuture;

        public ValueAndCloser(ClosingFuture<? extends V> closingFuture2) {
            this.closingFuture = (ClosingFuture) Preconditions.checkNotNull(closingFuture2);
        }

        public void closeAsync() {
            this.closingFuture.close();
        }

        @ParametricNullness
        public V get() throws ExecutionException {
            return Futures.getDone(this.closingFuture.future);
        }
    }

    public interface ValueAndCloserConsumer<V> {
        void accept(ValueAndCloser<V> valueAndCloser);
    }

    /* access modifiers changed from: private */
    public void becomeSubsumedInto(CloseableList closeableList) {
        checkAndUpdateState(State.OPEN, State.SUBSUMED);
        closeableList.add(this.closeables, MoreExecutors.directExecutor());
    }

    private <X extends Throwable, W extends V> ClosingFuture<V> catchingAsyncMoreGeneric(Class<X> cls, final AsyncClosingFunction<? super X, W> asyncClosingFunction, Executor executor) {
        Preconditions.checkNotNull(asyncClosingFunction);
        return derive(this.future.catchingAsync(cls, new AsyncFunction<X, W>() {
            public String toString() {
                return asyncClosingFunction.toString();
            }

            public ListenableFuture<W> apply(X x2) throws Exception {
                return ClosingFuture.this.closeables.applyAsyncClosingFunction(asyncClosingFunction, x2);
            }
        }, executor));
    }

    private <X extends Throwable, W extends V> ClosingFuture<V> catchingMoreGeneric(Class<X> cls, final ClosingFunction<? super X, W> closingFunction, Executor executor) {
        Preconditions.checkNotNull(closingFunction);
        return derive(this.future.catchingAsync(cls, new AsyncFunction<X, W>() {
            public String toString() {
                return closingFunction.toString();
            }

            public ListenableFuture<W> apply(X x2) throws Exception {
                return ClosingFuture.this.closeables.applyClosingFunction(closingFunction, x2);
            }
        }, executor));
    }

    /* access modifiers changed from: private */
    public void checkAndUpdateState(State state2, State state3) {
        Preconditions.checkState(compareAndUpdateState(state2, state3), "Expected state to be %s, but it was %s", (Object) state2, (Object) state3);
    }

    /* access modifiers changed from: private */
    public void close() {
        logger.log(Level.FINER, "closing {0}", this);
        this.closeables.close();
    }

    /* access modifiers changed from: private */
    public static void closeQuietly(@CheckForNull Closeable closeable, Executor executor) {
        if (closeable != null) {
            try {
                executor.execute(new c(closeable, 3));
            } catch (RejectedExecutionException e3) {
                Logger logger2 = logger;
                Level level = Level.WARNING;
                if (logger2.isLoggable(level)) {
                    logger2.log(level, String.format("while submitting close to %s; will close inline", new Object[]{executor}), e3);
                }
                closeQuietly(closeable, MoreExecutors.directExecutor());
            }
        }
    }

    private boolean compareAndUpdateState(State state2, State state3) {
        AtomicReference<State> atomicReference = this.state;
        while (!atomicReference.compareAndSet(state2, state3)) {
            if (atomicReference.get() != state2) {
                return false;
            }
        }
        return true;
    }

    private <U> ClosingFuture<U> derive(FluentFuture<U> fluentFuture) {
        ClosingFuture<U> closingFuture = new ClosingFuture<>(fluentFuture);
        becomeSubsumedInto(closingFuture.closeables);
        return closingFuture;
    }

    @Deprecated
    public static <C extends Closeable> ClosingFuture<C> eventuallyClosing(ListenableFuture<C> listenableFuture, final Executor executor) {
        Preconditions.checkNotNull(executor);
        ClosingFuture<C> closingFuture = new ClosingFuture<>(Futures.nonCancellationPropagating(listenableFuture));
        Futures.addCallback(listenableFuture, new FutureCallback<Closeable>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(@CheckForNull Closeable closeable) {
                ClosingFuture.this.closeables.closer.eventuallyClose(closeable, executor);
            }
        }, MoreExecutors.directExecutor());
        return closingFuture;
    }

    public static <V> ClosingFuture<V> from(ListenableFuture<V> listenableFuture) {
        return new ClosingFuture<>(listenableFuture);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$closeQuietly$0(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException | RuntimeException e3) {
            logger.log(Level.WARNING, "thrown by close()", e3);
        }
    }

    /* access modifiers changed from: private */
    public static <C, V extends C> void provideValueAndCloser(ValueAndCloserConsumer<C> valueAndCloserConsumer, ClosingFuture<V> closingFuture) {
        valueAndCloserConsumer.accept(new ValueAndCloser(closingFuture));
    }

    public static <V> ClosingFuture<V> submit(ClosingCallable<V> closingCallable, Executor executor) {
        return new ClosingFuture<>(closingCallable, executor);
    }

    public static <V> ClosingFuture<V> submitAsync(AsyncClosingCallable<V> asyncClosingCallable, Executor executor) {
        return new ClosingFuture<>(asyncClosingCallable, executor);
    }

    public static Combiner whenAllComplete(Iterable<? extends ClosingFuture<?>> iterable) {
        return new Combiner(false, iterable);
    }

    public static Combiner whenAllSucceed(Iterable<? extends ClosingFuture<?>> iterable) {
        return new Combiner(true, iterable);
    }

    public static <V, U> AsyncClosingFunction<V, U> withoutCloser(final AsyncFunction<V, U> asyncFunction) {
        Preconditions.checkNotNull(asyncFunction);
        return new AsyncClosingFunction<V, U>() {
            public ClosingFuture<U> apply(DeferredCloser deferredCloser, V v2) throws Exception {
                return ClosingFuture.from(AsyncFunction.this.apply(v2));
            }
        };
    }

    @CanIgnoreReturnValue
    public boolean cancel(boolean z2) {
        logger.log(Level.FINER, "cancelling {0}", this);
        boolean cancel = this.future.cancel(z2);
        if (cancel) {
            close();
        }
        return cancel;
    }

    public <X extends Throwable> ClosingFuture<V> catching(Class<X> cls, ClosingFunction<? super X, ? extends V> closingFunction, Executor executor) {
        return catchingMoreGeneric(cls, closingFunction, executor);
    }

    public <X extends Throwable> ClosingFuture<V> catchingAsync(Class<X> cls, AsyncClosingFunction<? super X, ? extends V> asyncClosingFunction, Executor executor) {
        return catchingAsyncMoreGeneric(cls, asyncClosingFunction, executor);
    }

    public void finalize() {
        if (this.state.get().equals(State.OPEN)) {
            logger.log(Level.SEVERE, "Uh oh! An open ClosingFuture has leaked and will close: {0}", this);
            finishToFuture();
        }
    }

    public FluentFuture<V> finishToFuture() {
        if (compareAndUpdateState(State.OPEN, State.WILL_CLOSE)) {
            logger.log(Level.FINER, "will close {0}", this);
            this.future.addListener(new Runnable() {
                public void run() {
                    ClosingFuture closingFuture = ClosingFuture.this;
                    State state = State.WILL_CLOSE;
                    State state2 = State.CLOSING;
                    closingFuture.checkAndUpdateState(state, state2);
                    ClosingFuture.this.close();
                    ClosingFuture.this.checkAndUpdateState(state2, State.CLOSED);
                }
            }, MoreExecutors.directExecutor());
        } else {
            switch (AnonymousClass11.$SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[this.state.get().ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot call finishToFuture() after deriving another step");
                case 2:
                    throw new IllegalStateException("Cannot call finishToFuture() after calling finishToValueAndCloser()");
                case 3:
                case 4:
                case 5:
                    throw new IllegalStateException("Cannot call finishToFuture() twice");
                case 6:
                    throw new AssertionError();
            }
        }
        return this.future;
    }

    public void finishToValueAndCloser(final ValueAndCloserConsumer<? super V> valueAndCloserConsumer, Executor executor) {
        Preconditions.checkNotNull(valueAndCloserConsumer);
        if (!compareAndUpdateState(State.OPEN, State.WILL_CREATE_VALUE_AND_CLOSER)) {
            int i3 = AnonymousClass11.$SwitchMap$com$google$common$util$concurrent$ClosingFuture$State[this.state.get().ordinal()];
            if (i3 == 1) {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() after deriving another step");
            } else if (i3 == 2) {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() twice");
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() after calling finishToFuture()");
            } else {
                throw new AssertionError(this.state);
            }
        } else {
            this.future.addListener(new Runnable() {
                public void run() {
                    ClosingFuture.provideValueAndCloser(valueAndCloserConsumer, ClosingFuture.this);
                }
            }, executor);
        }
    }

    public ListenableFuture<?> statusFuture() {
        return Futures.nonCancellationPropagating(this.future.transform(Functions.constant(null), MoreExecutors.directExecutor()));
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add(RemoteConfigConstants.ResponseFieldKey.STATE, (Object) this.state.get()).addValue((Object) this.future).toString();
    }

    public <U> ClosingFuture<U> transform(final ClosingFunction<? super V, U> closingFunction, Executor executor) {
        Preconditions.checkNotNull(closingFunction);
        return derive(this.future.transformAsync(new AsyncFunction<V, U>() {
            public ListenableFuture<U> apply(V v2) throws Exception {
                return ClosingFuture.this.closeables.applyClosingFunction(closingFunction, v2);
            }

            public String toString() {
                return closingFunction.toString();
            }
        }, executor));
    }

    public <U> ClosingFuture<U> transformAsync(final AsyncClosingFunction<? super V, U> asyncClosingFunction, Executor executor) {
        Preconditions.checkNotNull(asyncClosingFunction);
        return derive(this.future.transformAsync(new AsyncFunction<V, U>() {
            public ListenableFuture<U> apply(V v2) throws Exception {
                return ClosingFuture.this.closeables.applyAsyncClosingFunction(asyncClosingFunction, v2);
            }

            public String toString() {
                return asyncClosingFunction.toString();
            }
        }, executor));
    }

    @VisibleForTesting
    public CountDownLatch whenClosedCountDown() {
        return this.closeables.whenClosedCountDown();
    }

    private ClosingFuture(ListenableFuture<V> listenableFuture) {
        this.state = new AtomicReference<>(State.OPEN);
        this.closeables = new CloseableList();
        this.future = FluentFuture.from(listenableFuture);
    }

    public static Combiner whenAllComplete(ClosingFuture<?> closingFuture, ClosingFuture<?>... closingFutureArr) {
        return whenAllComplete(Lists.asList(closingFuture, closingFutureArr));
    }

    public static <V1, V2> Combiner2<V1, V2> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2) {
        return new Combiner2<>(closingFuture2);
    }

    public static <V1, V2, V3> Combiner3<V1, V2, V3> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3) {
        return new Combiner3<>(closingFuture2, closingFuture3);
    }

    public static <V1, V2, V3, V4> Combiner4<V1, V2, V3, V4> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4) {
        return new Combiner4(closingFuture2, closingFuture3, closingFuture4);
    }

    public static <V1, V2, V3, V4, V5> Combiner5<V1, V2, V3, V4, V5> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4, ClosingFuture<V5> closingFuture5) {
        return new Combiner5(closingFuture2, closingFuture3, closingFuture4, closingFuture5);
    }

    private ClosingFuture(final ClosingCallable<V> closingCallable, Executor executor) {
        this.state = new AtomicReference<>(State.OPEN);
        this.closeables = new CloseableList();
        Preconditions.checkNotNull(closingCallable);
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(new Callable<V>() {
            @ParametricNullness
            public V call() throws Exception {
                return closingCallable.call(ClosingFuture.this.closeables.closer);
            }

            public String toString() {
                return closingCallable.toString();
            }
        });
        executor.execute(create);
        this.future = create;
    }

    public static Combiner whenAllSucceed(ClosingFuture<?> closingFuture, ClosingFuture<?> closingFuture2, ClosingFuture<?> closingFuture3, ClosingFuture<?> closingFuture4, ClosingFuture<?> closingFuture5, ClosingFuture<?> closingFuture6, ClosingFuture<?>... closingFutureArr) {
        return whenAllSucceed(FluentIterable.of(closingFuture, closingFuture2, closingFuture3, closingFuture4, closingFuture5, closingFuture6).append((E[]) closingFutureArr));
    }

    private ClosingFuture(final AsyncClosingCallable<V> asyncClosingCallable, Executor executor) {
        this.state = new AtomicReference<>(State.OPEN);
        this.closeables = new CloseableList();
        Preconditions.checkNotNull(asyncClosingCallable);
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(new AsyncCallable<V>() {
            public ListenableFuture<V> call() throws Exception {
                CloseableList closeableList = new CloseableList();
                try {
                    ClosingFuture call = asyncClosingCallable.call(closeableList.closer);
                    call.becomeSubsumedInto(ClosingFuture.this.closeables);
                    return call.future;
                } finally {
                    ClosingFuture.this.closeables.add(closeableList, MoreExecutors.directExecutor());
                }
            }

            public String toString() {
                return asyncClosingCallable.toString();
            }
        });
        executor.execute(create);
        this.future = create;
    }
}
