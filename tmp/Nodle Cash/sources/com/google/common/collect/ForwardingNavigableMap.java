package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V> {

    public class StandardDescendingMap extends Maps.DescendingMap<K, V> {
        public StandardDescendingMap() {
        }

        public Iterator<Map.Entry<K, V>> entryIterator() {
            return new Iterator<Map.Entry<K, V>>() {
                @CheckForNull
                private Map.Entry<K, V> nextOrNull;
                @CheckForNull
                private Map.Entry<K, V> toRemove = null;

                {
                    this.nextOrNull = StandardDescendingMap.this.forward().lastEntry();
                }

                public boolean hasNext() {
                    return this.nextOrNull != null;
                }

                public void remove() {
                    if (this.toRemove != null) {
                        StandardDescendingMap.this.forward().remove(this.toRemove.getKey());
                        this.toRemove = null;
                        return;
                    }
                    throw new IllegalStateException("no calls to next() since the last call to remove()");
                }

                public Map.Entry<K, V> next() {
                    Map.Entry<K, V> entry = this.nextOrNull;
                    if (entry != null) {
                        this.toRemove = entry;
                        this.nextOrNull = StandardDescendingMap.this.forward().lowerEntry(this.nextOrNull.getKey());
                        return entry;
                    }
                    throw new NoSuchElementException();
                }
            };
        }

        public NavigableMap<K, V> forward() {
            return ForwardingNavigableMap.this;
        }
    }

    public class StandardNavigableKeySet extends Maps.NavigableKeySet<K, V> {
        public StandardNavigableKeySet(ForwardingNavigableMap forwardingNavigableMap) {
            super(forwardingNavigableMap);
        }
    }

    @CheckForNull
    public Map.Entry<K, V> ceilingEntry(@ParametricNullness K k2) {
        return delegate().ceilingEntry(k2);
    }

    @CheckForNull
    public K ceilingKey(@ParametricNullness K k2) {
        return delegate().ceilingKey(k2);
    }

    public abstract NavigableMap<K, V> delegate();

    public NavigableSet<K> descendingKeySet() {
        return delegate().descendingKeySet();
    }

    public NavigableMap<K, V> descendingMap() {
        return delegate().descendingMap();
    }

    @CheckForNull
    public Map.Entry<K, V> firstEntry() {
        return delegate().firstEntry();
    }

    @CheckForNull
    public Map.Entry<K, V> floorEntry(@ParametricNullness K k2) {
        return delegate().floorEntry(k2);
    }

    @CheckForNull
    public K floorKey(@ParametricNullness K k2) {
        return delegate().floorKey(k2);
    }

    public NavigableMap<K, V> headMap(@ParametricNullness K k2, boolean z2) {
        return delegate().headMap(k2, z2);
    }

    @CheckForNull
    public Map.Entry<K, V> higherEntry(@ParametricNullness K k2) {
        return delegate().higherEntry(k2);
    }

    @CheckForNull
    public K higherKey(@ParametricNullness K k2) {
        return delegate().higherKey(k2);
    }

    @CheckForNull
    public Map.Entry<K, V> lastEntry() {
        return delegate().lastEntry();
    }

    @CheckForNull
    public Map.Entry<K, V> lowerEntry(@ParametricNullness K k2) {
        return delegate().lowerEntry(k2);
    }

    @CheckForNull
    public K lowerKey(@ParametricNullness K k2) {
        return delegate().lowerKey(k2);
    }

    public NavigableSet<K> navigableKeySet() {
        return delegate().navigableKeySet();
    }

    @CheckForNull
    public Map.Entry<K, V> pollFirstEntry() {
        return delegate().pollFirstEntry();
    }

    @CheckForNull
    public Map.Entry<K, V> pollLastEntry() {
        return delegate().pollLastEntry();
    }

    @CheckForNull
    public Map.Entry<K, V> standardCeilingEntry(@ParametricNullness K k2) {
        return tailMap(k2, true).firstEntry();
    }

    @CheckForNull
    public K standardCeilingKey(@ParametricNullness K k2) {
        return Maps.keyOrNull(ceilingEntry(k2));
    }

    public NavigableSet<K> standardDescendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    @CheckForNull
    public Map.Entry<K, V> standardFirstEntry() {
        return (Map.Entry) Iterables.getFirst(entrySet(), null);
    }

    public K standardFirstKey() {
        Map.Entry firstEntry = firstEntry();
        if (firstEntry != null) {
            return firstEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public Map.Entry<K, V> standardFloorEntry(@ParametricNullness K k2) {
        return headMap(k2, true).lastEntry();
    }

    @CheckForNull
    public K standardFloorKey(@ParametricNullness K k2) {
        return Maps.keyOrNull(floorEntry(k2));
    }

    public SortedMap<K, V> standardHeadMap(@ParametricNullness K k2) {
        return headMap(k2, false);
    }

    @CheckForNull
    public Map.Entry<K, V> standardHigherEntry(@ParametricNullness K k2) {
        return tailMap(k2, false).firstEntry();
    }

    @CheckForNull
    public K standardHigherKey(@ParametricNullness K k2) {
        return Maps.keyOrNull(higherEntry(k2));
    }

    @CheckForNull
    public Map.Entry<K, V> standardLastEntry() {
        return (Map.Entry) Iterables.getFirst(descendingMap().entrySet(), null);
    }

    public K standardLastKey() {
        Map.Entry lastEntry = lastEntry();
        if (lastEntry != null) {
            return lastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public Map.Entry<K, V> standardLowerEntry(@ParametricNullness K k2) {
        return headMap(k2, false).lastEntry();
    }

    @CheckForNull
    public K standardLowerKey(@ParametricNullness K k2) {
        return Maps.keyOrNull(lowerEntry(k2));
    }

    @CheckForNull
    public Map.Entry<K, V> standardPollFirstEntry() {
        return (Map.Entry) Iterators.pollNext(entrySet().iterator());
    }

    @CheckForNull
    public Map.Entry<K, V> standardPollLastEntry() {
        return (Map.Entry) Iterators.pollNext(descendingMap().entrySet().iterator());
    }

    public SortedMap<K, V> standardSubMap(@ParametricNullness K k2, @ParametricNullness K k3) {
        return subMap(k2, true, k3, false);
    }

    public SortedMap<K, V> standardTailMap(@ParametricNullness K k2) {
        return tailMap(k2, true);
    }

    public NavigableMap<K, V> subMap(@ParametricNullness K k2, boolean z2, @ParametricNullness K k3, boolean z3) {
        return delegate().subMap(k2, z2, k3, z3);
    }

    public NavigableMap<K, V> tailMap(@ParametricNullness K k2, boolean z2) {
        return delegate().tailMap(k2, z2);
    }
}
