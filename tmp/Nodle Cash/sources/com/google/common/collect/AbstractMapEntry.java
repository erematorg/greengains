package com.google.common.collect;

import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    public boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return Objects.equal(getKey(), entry.getKey()) && Objects.equal(getValue(), entry.getValue());
    }

    @ParametricNullness
    public abstract K getKey();

    @ParametricNullness
    public abstract V getValue();

    public int hashCode() {
        Object key = getKey();
        Object value = getValue();
        int i3 = 0;
        int hashCode = key == null ? 0 : key.hashCode();
        if (value != null) {
            i3 = value.hashCode();
        }
        return hashCode ^ i3;
    }

    @ParametricNullness
    public V setValue(@ParametricNullness V v2) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return getKey() + StickyVariantProvider.KEY_VALUE_DELIMITER + getValue();
    }
}
