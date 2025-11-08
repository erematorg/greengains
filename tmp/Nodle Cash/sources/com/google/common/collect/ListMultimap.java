package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public interface ListMultimap<K, V> extends Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    boolean equals(@CheckForNull Object obj);

    List<V> get(@ParametricNullness K k2);

    @CanIgnoreReturnValue
    List<V> removeAll(@CheckForNull Object obj);

    @CanIgnoreReturnValue
    List<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable);
}
