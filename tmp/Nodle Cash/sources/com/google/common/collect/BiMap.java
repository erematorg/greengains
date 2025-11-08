package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public interface BiMap<K, V> extends Map<K, V> {
    @CheckForNull
    @CanIgnoreReturnValue
    V forcePut(@ParametricNullness K k2, @ParametricNullness V v2);

    BiMap<V, K> inverse();

    @CheckForNull
    @CanIgnoreReturnValue
    V put(@ParametricNullness K k2, @ParametricNullness V v2);

    void putAll(Map<? extends K, ? extends V> map);

    Set<V> values();
}
