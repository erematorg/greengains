package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@DoNotMock("Use ImmutableMultimap, HashMultimap, or another implementation")
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(@CompatibleWith("K") @CheckForNull Object obj, @CompatibleWith("V") @CheckForNull Object obj2);

    boolean containsKey(@CompatibleWith("K") @CheckForNull Object obj);

    boolean containsValue(@CompatibleWith("V") @CheckForNull Object obj);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(@CheckForNull Object obj);

    Collection<V> get(@ParametricNullness K k2);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    Multiset<K> keys();

    @CanIgnoreReturnValue
    boolean put(@ParametricNullness K k2, @ParametricNullness V v2);

    @CanIgnoreReturnValue
    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    @CanIgnoreReturnValue
    boolean putAll(@ParametricNullness K k2, Iterable<? extends V> iterable);

    @CanIgnoreReturnValue
    boolean remove(@CompatibleWith("K") @CheckForNull Object obj, @CompatibleWith("V") @CheckForNull Object obj2);

    @CanIgnoreReturnValue
    Collection<V> removeAll(@CompatibleWith("K") @CheckForNull Object obj);

    @CanIgnoreReturnValue
    Collection<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable);

    int size();

    Collection<V> values();
}
