package com.fasterxml.jackson.databind.util;

public interface LookupCache<K, V> {
    void clear();

    V get(Object obj);

    V put(K k2, V v2);

    V putIfAbsent(K k2, V v2);

    int size();
}
