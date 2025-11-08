package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class AtomicLongMap<K> implements Serializable {
    @CheckForNull
    @LazyInit
    private transient Map<K, Long> asMap;
    private final ConcurrentHashMap<K, AtomicLong> map;

    private AtomicLongMap(ConcurrentHashMap<K, AtomicLong> concurrentHashMap) {
        this.map = (ConcurrentHashMap) Preconditions.checkNotNull(concurrentHashMap);
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap<>(new ConcurrentHashMap());
    }

    private Map<K, Long> createAsMap() {
        return Collections.unmodifiableMap(Maps.transformValues(this.map, new Function<AtomicLong, Long>(this) {
            public Long apply(AtomicLong atomicLong) {
                return Long.valueOf(atomicLong.get());
            }
        }));
    }

    @CanIgnoreReturnValue
    public long addAndGet(K k2, long j2) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k2);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k2, new AtomicLong(j2))) == null) {
                return j2;
            }
            while (true) {
                long j3 = atomicLong.get();
                if (j3 != 0) {
                    long j4 = j3 + j2;
                    if (atomicLong.compareAndSet(j3, j4)) {
                        return j4;
                    }
                }
            }
        } while (!this.map.replace(k2, atomicLong, new AtomicLong(j2)));
        return j2;
    }

    public Map<K, Long> asMap() {
        Map<K, Long> map2 = this.asMap;
        if (map2 != null) {
            return map2;
        }
        Map<K, Long> createAsMap = createAsMap();
        this.asMap = createAsMap;
        return createAsMap;
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @CanIgnoreReturnValue
    public long decrementAndGet(K k2) {
        return addAndGet(k2, -1);
    }

    public long get(K k2) {
        AtomicLong atomicLong = this.map.get(k2);
        if (atomicLong == null) {
            return 0;
        }
        return atomicLong.get();
    }

    @CanIgnoreReturnValue
    public long getAndAdd(K k2, long j2) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k2);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k2, new AtomicLong(j2))) == null) {
                return 0;
            }
            while (true) {
                long j3 = atomicLong.get();
                if (j3 != 0) {
                    if (atomicLong.compareAndSet(j3, j3 + j2)) {
                        return j3;
                    }
                }
            }
        } while (!this.map.replace(k2, atomicLong, new AtomicLong(j2)));
        return 0;
    }

    @CanIgnoreReturnValue
    public long getAndDecrement(K k2) {
        return getAndAdd(k2, -1);
    }

    @CanIgnoreReturnValue
    public long getAndIncrement(K k2) {
        return getAndAdd(k2, 1);
    }

    @CanIgnoreReturnValue
    public long incrementAndGet(K k2) {
        return addAndGet(k2, 1);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @CanIgnoreReturnValue
    public long put(K k2, long j2) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k2);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k2, new AtomicLong(j2))) == null) {
                return 0;
            }
            while (true) {
                long j3 = atomicLong.get();
                if (j3 != 0) {
                    if (atomicLong.compareAndSet(j3, j2)) {
                        return j3;
                    }
                }
            }
        } while (!this.map.replace(k2, atomicLong, new AtomicLong(j2)));
        return 0;
    }

    public void putAll(Map<? extends K, ? extends Long> map2) {
        for (Map.Entry next : map2.entrySet()) {
            put(next.getKey(), ((Long) next.getValue()).longValue());
        }
    }

    public long putIfAbsent(K k2, long j2) {
        AtomicLong atomicLong;
        do {
            atomicLong = this.map.get(k2);
            if (atomicLong == null && (atomicLong = this.map.putIfAbsent(k2, new AtomicLong(j2))) == null) {
                return 0;
            }
            long j3 = atomicLong.get();
            if (j3 != 0) {
                return j3;
            }
        } while (!this.map.replace(k2, atomicLong, new AtomicLong(j2)));
        return 0;
    }

    @CanIgnoreReturnValue
    public long remove(K k2) {
        long j2;
        AtomicLong atomicLong = this.map.get(k2);
        if (atomicLong == null) {
            return 0;
        }
        do {
            j2 = atomicLong.get();
            if (j2 == 0 || atomicLong.compareAndSet(j2, 0)) {
                this.map.remove(k2, atomicLong);
            }
            j2 = atomicLong.get();
            break;
        } while (atomicLong.compareAndSet(j2, 0));
        this.map.remove(k2, atomicLong);
        return j2;
    }

    public void removeAllZeros() {
        Iterator<Map.Entry<K, AtomicLong>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            AtomicLong atomicLong = (AtomicLong) it.next().getValue();
            if (atomicLong != null && atomicLong.get() == 0) {
                it.remove();
            }
        }
    }

    @CanIgnoreReturnValue
    public boolean removeIfZero(K k2) {
        return remove(k2, 0);
    }

    public boolean replace(K k2, long j2, long j3) {
        if (j2 == 0) {
            return putIfAbsent(k2, j3) == 0;
        }
        AtomicLong atomicLong = this.map.get(k2);
        if (atomicLong == null) {
            return false;
        }
        return atomicLong.compareAndSet(j2, j3);
    }

    public int size() {
        return this.map.size();
    }

    public long sum() {
        long j2 = 0;
        for (AtomicLong atomicLong : this.map.values()) {
            j2 += atomicLong.get();
        }
        return j2;
    }

    public String toString() {
        return this.map.toString();
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> map2) {
        AtomicLongMap<K> create = create();
        create.putAll(map2);
        return create;
    }

    public boolean remove(K k2, long j2) {
        AtomicLong atomicLong = this.map.get(k2);
        if (atomicLong == null) {
            return false;
        }
        long j3 = atomicLong.get();
        if (j3 != j2) {
            return false;
        }
        if (j3 != 0 && !atomicLong.compareAndSet(j3, 0)) {
            return false;
        }
        this.map.remove(k2, atomicLong);
        return true;
    }
}
