package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable = true)
@ElementTypesAreNonnullByDefault
class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    @ParametricNullness
    final K key;
    @ParametricNullness
    final V value;

    public ImmutableEntry(@ParametricNullness K k2, @ParametricNullness V v2) {
        this.key = k2;
        this.value = v2;
    }

    @ParametricNullness
    public final K getKey() {
        return this.key;
    }

    @ParametricNullness
    public final V getValue() {
        return this.value;
    }

    @ParametricNullness
    public final V setValue(@ParametricNullness V v2) {
        throw new UnsupportedOperationException();
    }
}
