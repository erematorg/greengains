package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.concurrent.Callable;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public abstract class ForwardingListeningExecutorService extends ForwardingExecutorService implements ListeningExecutorService {
    public abstract ListeningExecutorService delegate();

    public <T> ListenableFuture<T> submit(Callable<T> callable) {
        return delegate().submit(callable);
    }

    public ListenableFuture<?> submit(Runnable runnable) {
        return delegate().submit(runnable);
    }

    public <T> ListenableFuture<T> submit(Runnable runnable, @ParametricNullness T t2) {
        return delegate().submit(runnable, t2);
    }
}
