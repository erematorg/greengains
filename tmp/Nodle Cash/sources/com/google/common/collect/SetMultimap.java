package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public interface SetMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    Set<Map.Entry<K, V>> entries();

    boolean equals(@CheckForNull Object obj);

    Set<V> get(@ParametricNullness K k2);

    @CanIgnoreReturnValue
    Set<V> removeAll(@CheckForNull Object obj);

    @CanIgnoreReturnValue
    Set<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable);
}
