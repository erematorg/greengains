package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
abstract class AbstractListMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 6588350623831699109L;

    public AbstractListMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    public abstract List<V> createCollection();

    public boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    public boolean put(@ParametricNullness K k2, @ParametricNullness V v2) {
        return super.put(k2, v2);
    }

    public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableList((List) collection);
    }

    public Collection<V> wrapCollection(@ParametricNullness K k2, Collection<V> collection) {
        return wrapList(k2, (List) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
    }

    public List<V> createUnmodifiableEmptyCollection() {
        return Collections.emptyList();
    }

    public List<V> get(@ParametricNullness K k2) {
        return (List) super.get(k2);
    }

    @CanIgnoreReturnValue
    public List<V> removeAll(@CheckForNull Object obj) {
        return (List) super.removeAll(obj);
    }

    @CanIgnoreReturnValue
    public List<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
        return (List) super.replaceValues(k2, iterable);
    }
}
