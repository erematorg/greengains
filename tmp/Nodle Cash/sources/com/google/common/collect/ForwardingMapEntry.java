package com.google.common.collect;

import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingMapEntry<K, V> extends ForwardingObject implements Map.Entry<K, V> {
    public abstract Map.Entry<K, V> delegate();

    public boolean equals(@CheckForNull Object obj) {
        return delegate().equals(obj);
    }

    @ParametricNullness
    public K getKey() {
        return delegate().getKey();
    }

    @ParametricNullness
    public V getValue() {
        return delegate().getValue();
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    @ParametricNullness
    public V setValue(@ParametricNullness V v2) {
        return delegate().setValue(v2);
    }

    public boolean standardEquals(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return Objects.equal(getKey(), entry.getKey()) && Objects.equal(getValue(), entry.getValue());
    }

    public int standardHashCode() {
        Object key = getKey();
        Object value = getValue();
        int i3 = 0;
        int hashCode = key == null ? 0 : key.hashCode();
        if (value != null) {
            i3 = value.hashCode();
        }
        return hashCode ^ i3;
    }

    public String standardToString() {
        return getKey() + StickyVariantProvider.KEY_VALUE_DELIMITER + getValue();
    }
}
