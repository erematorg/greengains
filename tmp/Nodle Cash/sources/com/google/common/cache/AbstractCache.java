package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    public static final class SimpleStatsCounter implements StatsCounter {
        private final LongAddable evictionCount = LongAddables.create();
        private final LongAddable hitCount = LongAddables.create();
        private final LongAddable loadExceptionCount = LongAddables.create();
        private final LongAddable loadSuccessCount = LongAddables.create();
        private final LongAddable missCount = LongAddables.create();
        private final LongAddable totalLoadTime = LongAddables.create();

        private static long negativeToMaxValue(long j2) {
            if (j2 >= 0) {
                return j2;
            }
            return Long.MAX_VALUE;
        }

        public void incrementBy(StatsCounter statsCounter) {
            CacheStats snapshot = statsCounter.snapshot();
            this.hitCount.add(snapshot.hitCount());
            this.missCount.add(snapshot.missCount());
            this.loadSuccessCount.add(snapshot.loadSuccessCount());
            this.loadExceptionCount.add(snapshot.loadExceptionCount());
            this.totalLoadTime.add(snapshot.totalLoadTime());
            this.evictionCount.add(snapshot.evictionCount());
        }

        public void recordEviction() {
            this.evictionCount.increment();
        }

        public void recordHits(int i3) {
            this.hitCount.add((long) i3);
        }

        public void recordLoadException(long j2) {
            this.loadExceptionCount.increment();
            this.totalLoadTime.add(j2);
        }

        public void recordLoadSuccess(long j2) {
            this.loadSuccessCount.increment();
            this.totalLoadTime.add(j2);
        }

        public void recordMisses(int i3) {
            this.missCount.add((long) i3);
        }

        public CacheStats snapshot() {
            return new CacheStats(negativeToMaxValue(this.hitCount.sum()), negativeToMaxValue(this.missCount.sum()), negativeToMaxValue(this.loadSuccessCount.sum()), negativeToMaxValue(this.loadExceptionCount.sum()), negativeToMaxValue(this.totalLoadTime.sum()), negativeToMaxValue(this.evictionCount.sum()));
        }
    }

    public interface StatsCounter {
        void recordEviction();

        void recordHits(int i3);

        void recordLoadException(long j2);

        void recordLoadSuccess(long j2);

        void recordMisses(int i3);

        CacheStats snapshot();
    }

    public ConcurrentMap<K, V> asMap() {
        throw new UnsupportedOperationException();
    }

    public void cleanUp() {
    }

    public V get(K k2, Callable<? extends V> callable) throws ExecutionException {
        throw new UnsupportedOperationException();
    }

    public ImmutableMap<K, V> getAllPresent(Iterable<? extends Object> iterable) {
        Object ifPresent;
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        for (Object next : iterable) {
            if (!newLinkedHashMap.containsKey(next) && (ifPresent = getIfPresent(next)) != null) {
                newLinkedHashMap.put(next, ifPresent);
            }
        }
        return ImmutableMap.copyOf(newLinkedHashMap);
    }

    public void invalidate(Object obj) {
        throw new UnsupportedOperationException();
    }

    public void invalidateAll(Iterable<? extends Object> iterable) {
        for (Object invalidate : iterable) {
            invalidate(invalidate);
        }
    }

    public void put(K k2, V v2) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public long size() {
        throw new UnsupportedOperationException();
    }

    public CacheStats stats() {
        throw new UnsupportedOperationException();
    }

    public void invalidateAll() {
        throw new UnsupportedOperationException();
    }
}
