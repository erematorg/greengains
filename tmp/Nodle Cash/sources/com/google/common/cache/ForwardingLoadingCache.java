package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingLoadingCache<K, V> extends ForwardingCache<K, V> implements LoadingCache<K, V> {
    public V apply(K k2) {
        return delegate().apply(k2);
    }

    public abstract LoadingCache<K, V> delegate();

    @CanIgnoreReturnValue
    public V get(K k2) throws ExecutionException {
        return delegate().get(k2);
    }

    @CanIgnoreReturnValue
    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
        return delegate().getAll(iterable);
    }

    @CanIgnoreReturnValue
    public V getUnchecked(K k2) {
        return delegate().getUnchecked(k2);
    }

    public void refresh(K k2) {
        delegate().refresh(k2);
    }

    public static abstract class SimpleForwardingLoadingCache<K, V> extends ForwardingLoadingCache<K, V> {
        private final LoadingCache<K, V> delegate;

        public SimpleForwardingLoadingCache(LoadingCache<K, V> loadingCache) {
            this.delegate = (LoadingCache) Preconditions.checkNotNull(loadingCache);
        }

        public final LoadingCache<K, V> delegate() {
            return this.delegate;
        }
    }
}
