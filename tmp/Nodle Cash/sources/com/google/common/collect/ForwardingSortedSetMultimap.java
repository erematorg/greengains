package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingSortedSetMultimap<K, V> extends ForwardingSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    public abstract SortedSetMultimap<K, V> delegate();

    @CheckForNull
    public Comparator<? super V> valueComparator() {
        return delegate().valueComparator();
    }

    public SortedSet<V> get(@ParametricNullness K k2) {
        return delegate().get((Object) k2);
    }

    public SortedSet<V> removeAll(@CheckForNull Object obj) {
        return delegate().removeAll(obj);
    }

    public SortedSet<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
        return delegate().replaceValues((Object) k2, (Iterable) iterable);
    }
}
