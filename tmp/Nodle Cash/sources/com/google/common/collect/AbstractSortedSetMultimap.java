package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
abstract class AbstractSortedSetMultimap<K, V> extends AbstractSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    private static final long serialVersionUID = 430848587173315748L;

    public AbstractSortedSetMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    public abstract SortedSet<V> createCollection();

    public Collection<V> values() {
        return super.values();
    }

    public Collection<V> wrapCollection(@ParametricNullness K k2, Collection<V> collection) {
        return collection instanceof NavigableSet ? new AbstractMapBasedMultimap.WrappedNavigableSet(k2, (NavigableSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : new AbstractMapBasedMultimap.WrappedSortedSet(k2, (SortedSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
    }

    public <E> SortedSet<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        if (collection instanceof NavigableSet) {
            return Sets.unmodifiableNavigableSet((NavigableSet) collection);
        }
        return Collections.unmodifiableSortedSet((SortedSet) collection);
    }

    public SortedSet<V> createUnmodifiableEmptyCollection() {
        return unmodifiableCollectionSubclass((Collection) createCollection());
    }

    public SortedSet<V> get(@ParametricNullness K k2) {
        return (SortedSet) super.get((Object) k2);
    }

    @CanIgnoreReturnValue
    public SortedSet<V> removeAll(@CheckForNull Object obj) {
        return (SortedSet) super.removeAll(obj);
    }

    @CanIgnoreReturnValue
    public SortedSet<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
        return (SortedSet) super.replaceValues((Object) k2, (Iterable) iterable);
    }
}
