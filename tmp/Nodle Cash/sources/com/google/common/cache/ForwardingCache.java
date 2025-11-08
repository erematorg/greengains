package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingCache<K, V> extends ForwardingObject implements Cache<K, V> {

    public static abstract class SimpleForwardingCache<K, V> extends ForwardingCache<K, V> {
        private final Cache<K, V> delegate;

        public SimpleForwardingCache(Cache<K, V> cache) {
            this.delegate = (Cache) Preconditions.checkNotNull(cache);
        }

        public final Cache<K, V> delegate() {
            return this.delegate;
        }
    }

    public ConcurrentMap<K, V> asMap() {
        return delegate().asMap();
    }

    public void cleanUp() {
        delegate().cleanUp();
    }

    public abstract Cache<K, V> delegate();

    public V get(K k2, Callable<? extends V> callable) throws ExecutionException {
        return delegate().get(k2, callable);
    }

    public ImmutableMap<K, V> getAllPresent(Iterable<? extends Object> iterable) {
        return delegate().getAllPresent(iterable);
    }

    @CheckForNull
    public V getIfPresent(Object obj) {
        return delegate().getIfPresent(obj);
    }

    public void invalidate(Object obj) {
        delegate().invalidate(obj);
    }

    public void invalidateAll(Iterable<? extends Object> iterable) {
        delegate().invalidateAll(iterable);
    }

    public void put(K k2, V v2) {
        delegate().put(k2, v2);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    public long size() {
        return delegate().size();
    }

    public CacheStats stats() {
        return delegate().stats();
    }

    public void invalidateAll() {
        delegate().invalidateAll();
    }
}
