package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.RegularImmutableMap;
import java.util.Map;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
final class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final RegularImmutableBiMap<Object, Object> EMPTY = new RegularImmutableBiMap<>();
    @VisibleForTesting
    final transient Object[] alternatingKeysAndValues;
    private final transient RegularImmutableBiMap<V, K> inverse;
    @CheckForNull
    private final transient Object keyHashTable;
    private final transient int keyOffset;
    private final transient int size;

    private RegularImmutableBiMap() {
        this.keyHashTable = null;
        this.alternatingKeysAndValues = new Object[0];
        this.keyOffset = 0;
        this.size = 0;
        this.inverse = this;
    }

    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new RegularImmutableMap.EntrySet(this, this.alternatingKeysAndValues, this.keyOffset, this.size);
    }

    public ImmutableSet<K> createKeySet() {
        return new RegularImmutableMap.KeySet(this, new RegularImmutableMap.KeysOrValuesAsList(this.alternatingKeysAndValues, this.keyOffset, this.size));
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        V v2 = RegularImmutableMap.get(this.keyHashTable, this.alternatingKeysAndValues, this.size, this.keyOffset, obj);
        if (v2 == null) {
            return null;
        }
        return v2;
    }

    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.size;
    }

    public ImmutableBiMap<V, K> inverse() {
        return this.inverse;
    }

    public RegularImmutableBiMap(Object[] objArr, int i3) {
        this.alternatingKeysAndValues = objArr;
        this.size = i3;
        this.keyOffset = 0;
        int chooseTableSize = i3 >= 2 ? ImmutableSet.chooseTableSize(i3) : 0;
        this.keyHashTable = RegularImmutableMap.createHashTableOrThrow(objArr, i3, chooseTableSize, 0);
        this.inverse = new RegularImmutableBiMap<>(RegularImmutableMap.createHashTableOrThrow(objArr, i3, chooseTableSize, 1), objArr, i3, this);
    }

    private RegularImmutableBiMap(@CheckForNull Object obj, Object[] objArr, int i3, RegularImmutableBiMap<V, K> regularImmutableBiMap) {
        this.keyHashTable = obj;
        this.alternatingKeysAndValues = objArr;
        this.keyOffset = 1;
        this.size = i3;
        this.inverse = regularImmutableBiMap;
    }
}
