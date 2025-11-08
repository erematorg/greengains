package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
abstract class WrappingExecutorService implements ExecutorService {
    private final ExecutorService delegate;

    public WrappingExecutorService(ExecutorService executorService) {
        this.delegate = (ExecutorService) Preconditions.checkNotNull(executorService);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$wrapTask$0(Callable callable) {
        try {
            callable.call();
        } catch (Exception e3) {
            Platform.restoreInterruptIfIsInterruptedException(e3);
            Throwables.throwIfUnchecked(e3);
            throw new RuntimeException(e3);
        }
    }

    private <T> ImmutableList<Callable<T>> wrapTasks(Collection<? extends Callable<T>> collection) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Callable wrapTask : collection) {
            builder.add((Object) wrapTask(wrapTask));
        }
        return builder.build();
    }

    public final boolean awaitTermination(long j2, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j2, timeUnit);
    }

    public final void execute(Runnable runnable) {
        this.delegate.execute(wrapTask(runnable));
    }

    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.delegate.invokeAll(wrapTasks(collection));
    }

    public final <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return this.delegate.invokeAny(wrapTasks(collection));
    }

    public final boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    public final boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    public final void shutdown() {
        this.delegate.shutdown();
    }

    @CanIgnoreReturnValue
    public final List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    public final <T> Future<T> submit(Callable<T> callable) {
        return this.delegate.submit(wrapTask((Callable) Preconditions.checkNotNull(callable)));
    }

    public Runnable wrapTask(Runnable runnable) {
        return new c(wrapTask(Executors.callable(runnable, (Object) null)), 2);
    }

    public abstract <T> Callable<T> wrapTask(Callable<T> callable);

    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j2, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.invokeAll(wrapTasks(collection), j2, timeUnit);
    }

    public final <T> T invokeAny(Collection<? extends Callable<T>> collection, long j2, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.delegate.invokeAny(wrapTasks(collection), j2, timeUnit);
    }

    public final Future<?> submit(Runnable runnable) {
        return this.delegate.submit(wrapTask(runnable));
    }

    public final <T> Future<T> submit(Runnable runnable, @ParametricNullness T t2) {
        return this.delegate.submit(wrapTask(runnable), t2);
    }
}
