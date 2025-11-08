package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@DoNotMock("Use CacheBuilder.newBuilder().build()")
public interface Cache<K, V> {
    ConcurrentMap<K, V> asMap();

    void cleanUp();

    @CanIgnoreReturnValue
    V get(K k2, Callable<? extends V> callable) throws ExecutionException;

    ImmutableMap<K, V> getAllPresent(Iterable<? extends Object> iterable);

    @CheckForNull
    @CanIgnoreReturnValue
    V getIfPresent(@CompatibleWith("K") Object obj);

    void invalidate(@CompatibleWith("K") Object obj);

    void invalidateAll();

    void invalidateAll(Iterable<? extends Object> iterable);

    void put(K k2, V v2);

    void putAll(Map<? extends K, ? extends V> map);

    long size();

    CacheStats stats();
}
