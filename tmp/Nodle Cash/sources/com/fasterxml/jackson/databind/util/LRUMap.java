package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

public class LRUMap<K, V> implements LookupCache<K, V>, Serializable {
    private static final long serialVersionUID = 2;
    protected final int _initialEntries;
    protected final transient PrivateMaxEntriesMap<K, V> _map;
    protected final int _maxEntries;

    public LRUMap(int i3, int i4) {
        this._initialEntries = i3;
        this._maxEntries = i4;
        this._map = new PrivateMaxEntriesMap.Builder().initialCapacity(i3).maximumCapacity((long) i4).concurrencyLevel(4).build();
    }

    public void clear() {
        this._map.clear();
    }

    public void contents(BiConsumer<K, V> biConsumer) {
        for (Map.Entry next : this._map.entrySet()) {
            biConsumer.accept(next.getKey(), next.getValue());
        }
    }

    public V get(Object obj) {
        return this._map.get(obj);
    }

    public V put(K k2, V v2) {
        return this._map.put(k2, v2);
    }

    public V putIfAbsent(K k2, V v2) {
        return this._map.putIfAbsent(k2, v2);
    }

    public Object readResolve() {
        return new LRUMap(this._initialEntries, this._maxEntries);
    }

    public int size() {
        return this._map.size();
    }
}
