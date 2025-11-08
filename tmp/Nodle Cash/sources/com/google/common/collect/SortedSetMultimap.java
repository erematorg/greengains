package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public interface SortedSetMultimap<K, V> extends SetMultimap<K, V> {
    Map<K, Collection<V>> asMap();

    SortedSet<V> get(@ParametricNullness K k2);

    @CanIgnoreReturnValue
    SortedSet<V> removeAll(@CheckForNull Object obj);

    @CanIgnoreReturnValue
    SortedSet<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable);

    @CheckForNull
    Comparator<? super V> valueComparator();
}
