package com.google.common.collect;

import WrappedCollection.WrappedIterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    /* access modifiers changed from: private */
    public transient Map<K, Collection<V>> map;
    private transient int totalSize;

    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> submap;

        public class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
            public AsMapEntries() {
            }

            public boolean contains(@CheckForNull Object obj) {
                return Collections2.safeContains(AsMap.this.submap.entrySet(), obj);
            }

            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator();
            }

            public Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            public boolean remove(@CheckForNull Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                Objects.requireNonNull(entry);
                AbstractMapBasedMultimap.this.removeValuesForKey(entry.getKey());
                return true;
            }
        }

        public class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {
            @CheckForNull
            Collection<V> collection;
            final Iterator<Map.Entry<K, Collection<V>>> delegateIterator;

            public AsMapIterator() {
                this.delegateIterator = AsMap.this.submap.entrySet().iterator();
            }

            public boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            public void remove() {
                Preconditions.checkState(this.collection != null, "no calls to next() since the last call to remove()");
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, this.collection.size());
                this.collection.clear();
                this.collection = null;
            }

            public Map.Entry<K, Collection<V>> next() {
                Map.Entry next = this.delegateIterator.next();
                this.collection = (Collection) next.getValue();
                return AsMap.this.wrapEntry(next);
            }
        }

        public AsMap(Map<K, Collection<V>> map) {
            this.submap = map;
        }

        public void clear() {
            if (this.submap == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.clear(new AsMapIterator());
            }
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return Maps.safeContainsKey(this.submap, obj);
        }

        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries();
        }

        public boolean equals(@CheckForNull Object obj) {
            return this == obj || this.submap.equals(obj);
        }

        public int hashCode() {
            return this.submap.hashCode();
        }

        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        public int size() {
            return this.submap.size();
        }

        public String toString() {
            return this.submap.toString();
        }

        public Map.Entry<K, Collection<V>> wrapEntry(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, entry.getValue()));
        }

        @CheckForNull
        public Collection<V> get(@CheckForNull Object obj) {
            Collection collection = (Collection) Maps.safeGet(this.submap, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(obj, collection);
        }

        @CheckForNull
        public Collection<V> remove(@CheckForNull Object obj) {
            Collection remove = this.submap.remove(obj);
            if (remove == null) {
                return null;
            }
            Collection<V> createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll(remove);
            AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, remove.size());
            remove.clear();
            return createCollection;
        }
    }

    public abstract class Itr<T> implements Iterator<T> {
        @CheckForNull
        Collection<V> collection = null;
        @CheckForNull
        K key = null;
        final Iterator<Map.Entry<K, Collection<V>>> keyIterator;
        Iterator<V> valueIterator = Iterators.emptyModifiableIterator();

        public Itr() {
            this.keyIterator = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.keyIterator.hasNext() || this.valueIterator.hasNext();
        }

        @ParametricNullness
        public T next() {
            if (!this.valueIterator.hasNext()) {
                Map.Entry next = this.keyIterator.next();
                this.key = next.getKey();
                Collection<V> collection2 = (Collection) next.getValue();
                this.collection = collection2;
                this.valueIterator = collection2.iterator();
            }
            return output(NullnessCasts.uncheckedCastNullableTToT(this.key), this.valueIterator.next());
        }

        public abstract T output(@ParametricNullness K k2, @ParametricNullness V v2);

        public void remove() {
            this.valueIterator.remove();
            Collection<V> collection2 = this.collection;
            Objects.requireNonNull(collection2);
            if (collection2.isEmpty()) {
                this.keyIterator.remove();
            }
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
        }
    }

    public class KeySet extends Maps.KeySet<K, Collection<V>> {
        public KeySet(Map<K, Collection<V>> map) {
            super(map);
        }

        public void clear() {
            Iterators.clear(iterator());
        }

        public boolean containsAll(Collection<?> collection) {
            return map().keySet().containsAll(collection);
        }

        public boolean equals(@CheckForNull Object obj) {
            return this == obj || map().keySet().equals(obj);
        }

        public int hashCode() {
            return map().keySet().hashCode();
        }

        public Iterator<K> iterator() {
            final Iterator it = map().entrySet().iterator();
            return new Iterator<K>() {
                @CheckForNull
                Map.Entry<K, Collection<V>> entry;

                public boolean hasNext() {
                    return it.hasNext();
                }

                @ParametricNullness
                public K next() {
                    Map.Entry<K, Collection<V>> entry2 = (Map.Entry) it.next();
                    this.entry = entry2;
                    return entry2.getKey();
                }

                public void remove() {
                    Preconditions.checkState(this.entry != null, "no calls to next() since the last call to remove()");
                    Collection value = this.entry.getValue();
                    it.remove();
                    AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, value.size());
                    value.clear();
                    this.entry = null;
                }
            };
        }

        public boolean remove(@CheckForNull Object obj) {
            int i3;
            Collection collection = (Collection) map().remove(obj);
            if (collection != null) {
                i3 = collection.size();
                collection.clear();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, i3);
            } else {
                i3 = 0;
            }
            return i3 > 0;
        }
    }

    public class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        public NavigableAsMap(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> ceilingEntry(@ParametricNullness K k2) {
            Map.Entry ceilingEntry = sortedMap().ceilingEntry(k2);
            if (ceilingEntry == null) {
                return null;
            }
            return wrapEntry(ceilingEntry);
        }

        @CheckForNull
        public K ceilingKey(@ParametricNullness K k2) {
            return sortedMap().ceilingKey(k2);
        }

        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(sortedMap().descendingMap());
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry firstEntry = sortedMap().firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return wrapEntry(firstEntry);
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> floorEntry(@ParametricNullness K k2) {
            Map.Entry floorEntry = sortedMap().floorEntry(k2);
            if (floorEntry == null) {
                return null;
            }
            return wrapEntry(floorEntry);
        }

        @CheckForNull
        public K floorKey(@ParametricNullness K k2) {
            return sortedMap().floorKey(k2);
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> higherEntry(@ParametricNullness K k2) {
            Map.Entry higherEntry = sortedMap().higherEntry(k2);
            if (higherEntry == null) {
                return null;
            }
            return wrapEntry(higherEntry);
        }

        @CheckForNull
        public K higherKey(@ParametricNullness K k2) {
            return sortedMap().higherKey(k2);
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry lastEntry = sortedMap().lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return wrapEntry(lastEntry);
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> lowerEntry(@ParametricNullness K k2) {
            Map.Entry lowerEntry = sortedMap().lowerEntry(k2);
            if (lowerEntry == null) {
                return null;
            }
            return wrapEntry(lowerEntry);
        }

        @CheckForNull
        public K lowerKey(@ParametricNullness K k2) {
            return sortedMap().lowerKey(k2);
        }

        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> pollAsMapEntry(Iterator<Map.Entry<K, Collection<V>>> it) {
            if (!it.hasNext()) {
                return null;
            }
            Map.Entry next = it.next();
            Collection createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll((Collection) next.getValue());
            it.remove();
            return Maps.immutableEntry(next.getKey(), AbstractMapBasedMultimap.this.unmodifiableCollectionSubclass(createCollection));
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return pollAsMapEntry(entrySet().iterator());
        }

        @CheckForNull
        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return pollAsMapEntry(descendingMap().entrySet().iterator());
        }

        public NavigableMap<K, Collection<V>> headMap(@ParametricNullness K k2) {
            return headMap(k2, false);
        }

        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        public NavigableMap<K, Collection<V>> subMap(@ParametricNullness K k2, @ParametricNullness K k3) {
            return subMap(k2, true, k3, false);
        }

        public NavigableMap<K, Collection<V>> tailMap(@ParametricNullness K k2) {
            return tailMap(k2, true);
        }

        public NavigableSet<K> createKeySet() {
            return new NavigableKeySet(sortedMap());
        }

        public NavigableMap<K, Collection<V>> headMap(@ParametricNullness K k2, boolean z2) {
            return new NavigableAsMap(sortedMap().headMap(k2, z2));
        }

        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        public NavigableMap<K, Collection<V>> subMap(@ParametricNullness K k2, boolean z2, @ParametricNullness K k3, boolean z3) {
            return new NavigableAsMap(sortedMap().subMap(k2, z2, k3, z3));
        }

        public NavigableMap<K, Collection<V>> tailMap(@ParametricNullness K k2, boolean z2) {
            return new NavigableAsMap(sortedMap().tailMap(k2, z2));
        }
    }

    public class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        public NavigableKeySet(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @CheckForNull
        public K ceiling(@ParametricNullness K k2) {
            return sortedMap().ceilingKey(k2);
        }

        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(sortedMap().descendingMap());
        }

        @CheckForNull
        public K floor(@ParametricNullness K k2) {
            return sortedMap().floorKey(k2);
        }

        @CheckForNull
        public K higher(@ParametricNullness K k2) {
            return sortedMap().higherKey(k2);
        }

        @CheckForNull
        public K lower(@ParametricNullness K k2) {
            return sortedMap().lowerKey(k2);
        }

        @CheckForNull
        public K pollFirst() {
            return Iterators.pollNext(iterator());
        }

        @CheckForNull
        public K pollLast() {
            return Iterators.pollNext(descendingIterator());
        }

        public NavigableSet<K> headSet(@ParametricNullness K k2) {
            return headSet(k2, false);
        }

        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        public NavigableSet<K> subSet(@ParametricNullness K k2, @ParametricNullness K k3) {
            return subSet(k2, true, k3, false);
        }

        public NavigableSet<K> tailSet(@ParametricNullness K k2) {
            return tailSet(k2, true);
        }

        public NavigableSet<K> headSet(@ParametricNullness K k2, boolean z2) {
            return new NavigableKeySet(sortedMap().headMap(k2, z2));
        }

        public NavigableSet<K> subSet(@ParametricNullness K k2, boolean z2, @ParametricNullness K k3, boolean z3) {
            return new NavigableKeySet(sortedMap().subMap(k2, z2, k3, z3));
        }

        public NavigableSet<K> tailSet(@ParametricNullness K k2, boolean z2) {
            return new NavigableKeySet(sortedMap().tailMap(k2, z2));
        }
    }

    public class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        public RandomAccessWrappedList(@ParametricNullness AbstractMapBasedMultimap abstractMapBasedMultimap, K k2, @CheckForNull List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k2, list, wrappedCollection);
        }
    }

    public class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        @CheckForNull
        SortedSet<K> sortedKeySet;

        public SortedAsMap(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        @CheckForNull
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @ParametricNullness
        public K firstKey() {
            return sortedMap().firstKey();
        }

        public SortedMap<K, Collection<V>> headMap(@ParametricNullness K k2) {
            return new SortedAsMap(sortedMap().headMap(k2));
        }

        @ParametricNullness
        public K lastKey() {
            return sortedMap().lastKey();
        }

        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) this.submap;
        }

        public SortedMap<K, Collection<V>> subMap(@ParametricNullness K k2, @ParametricNullness K k3) {
            return new SortedAsMap(sortedMap().subMap(k2, k3));
        }

        public SortedMap<K, Collection<V>> tailMap(@ParametricNullness K k2) {
            return new SortedAsMap(sortedMap().tailMap(k2));
        }

        public SortedSet<K> createKeySet() {
            return new SortedKeySet(sortedMap());
        }

        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.sortedKeySet;
            if (sortedSet != null) {
                return sortedSet;
            }
            SortedSet<K> createKeySet = createKeySet();
            this.sortedKeySet = createKeySet;
            return createKeySet;
        }
    }

    public class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        public SortedKeySet(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        @CheckForNull
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @ParametricNullness
        public K first() {
            return sortedMap().firstKey();
        }

        public SortedSet<K> headSet(@ParametricNullness K k2) {
            return new SortedKeySet(sortedMap().headMap(k2));
        }

        @ParametricNullness
        public K last() {
            return sortedMap().lastKey();
        }

        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) super.map();
        }

        public SortedSet<K> subSet(@ParametricNullness K k2, @ParametricNullness K k3) {
            return new SortedKeySet(sortedMap().subMap(k2, k3));
        }

        public SortedSet<K> tailSet(@ParametricNullness K k2) {
            return new SortedKeySet(sortedMap().tailMap(k2));
        }
    }

    public class WrappedNavigableSet extends AbstractMapBasedMultimap<K, V>.WrappedSortedSet implements NavigableSet<V> {
        public WrappedNavigableSet(@ParametricNullness K k2, NavigableSet<V> navigableSet, @CheckForNull AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k2, navigableSet, wrappedCollection);
        }

        private NavigableSet<V> wrap(NavigableSet<V> navigableSet) {
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            K k2 = this.key;
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            this = this;
            if (ancestor != null) {
                this = getAncestor();
            }
            return new WrappedNavigableSet(k2, navigableSet, this);
        }

        @CheckForNull
        public V ceiling(@ParametricNullness V v2) {
            return getSortedSetDelegate().ceiling(v2);
        }

        public Iterator<V> descendingIterator() {
            return new WrappedCollection.WrappedIterator(getSortedSetDelegate().descendingIterator());
        }

        public NavigableSet<V> descendingSet() {
            return wrap(getSortedSetDelegate().descendingSet());
        }

        @CheckForNull
        public V floor(@ParametricNullness V v2) {
            return getSortedSetDelegate().floor(v2);
        }

        public NavigableSet<V> headSet(@ParametricNullness V v2, boolean z2) {
            return wrap(getSortedSetDelegate().headSet(v2, z2));
        }

        @CheckForNull
        public V higher(@ParametricNullness V v2) {
            return getSortedSetDelegate().higher(v2);
        }

        @CheckForNull
        public V lower(@ParametricNullness V v2) {
            return getSortedSetDelegate().lower(v2);
        }

        @CheckForNull
        public V pollFirst() {
            return Iterators.pollNext(iterator());
        }

        @CheckForNull
        public V pollLast() {
            return Iterators.pollNext(descendingIterator());
        }

        public NavigableSet<V> subSet(@ParametricNullness V v2, boolean z2, @ParametricNullness V v3, boolean z3) {
            return wrap(getSortedSetDelegate().subSet(v2, z2, v3, z3));
        }

        public NavigableSet<V> tailSet(@ParametricNullness V v2, boolean z2) {
            return wrap(getSortedSetDelegate().tailSet(v2, z2));
        }

        public NavigableSet<V> getSortedSetDelegate() {
            return (NavigableSet) super.getSortedSetDelegate();
        }
    }

    public class WrappedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
        public WrappedSet(@ParametricNullness K k2, Set<V> set) {
            super(k2, set, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAllImpl = Sets.removeAllImpl((Set<?>) (Set) this.delegate, collection);
            if (removeAllImpl) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                removeIfEmpty();
            }
            return removeAllImpl;
        }
    }

    public class WrappedSortedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements SortedSet<V> {
        public WrappedSortedSet(@ParametricNullness K k2, SortedSet<V> sortedSet, @CheckForNull AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k2, sortedSet, wrappedCollection);
        }

        @CheckForNull
        public Comparator<? super V> comparator() {
            return getSortedSetDelegate().comparator();
        }

        @ParametricNullness
        public V first() {
            refreshIfEmpty();
            return getSortedSetDelegate().first();
        }

        public SortedSet<V> getSortedSetDelegate() {
            return (SortedSet) getDelegate();
        }

        public SortedSet<V> headSet(@ParametricNullness V v2) {
            refreshIfEmpty();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object key = getKey();
            SortedSet headSet = getSortedSetDelegate().headSet(v2);
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            this = this;
            if (ancestor != null) {
                this = getAncestor();
            }
            return new WrappedSortedSet(key, headSet, this);
        }

        @ParametricNullness
        public V last() {
            refreshIfEmpty();
            return getSortedSetDelegate().last();
        }

        public SortedSet<V> subSet(@ParametricNullness V v2, @ParametricNullness V v3) {
            refreshIfEmpty();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object key = getKey();
            SortedSet subSet = getSortedSetDelegate().subSet(v2, v3);
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            this = this;
            if (ancestor != null) {
                this = getAncestor();
            }
            return new WrappedSortedSet(key, subSet, this);
        }

        public SortedSet<V> tailSet(@ParametricNullness V v2) {
            refreshIfEmpty();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object key = getKey();
            SortedSet tailSet = getSortedSetDelegate().tailSet(v2);
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            this = this;
            if (ancestor != null) {
                this = getAncestor();
            }
            return new WrappedSortedSet(key, tailSet, this);
        }
    }

    public AbstractMapBasedMultimap(Map<K, Collection<V>> map2) {
        Preconditions.checkArgument(map2.isEmpty());
        this.map = map2;
    }

    public static /* synthetic */ int access$208(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i3 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i3 + 1;
        return i3;
    }

    public static /* synthetic */ int access$210(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i3 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i3 - 1;
        return i3;
    }

    public static /* synthetic */ int access$212(AbstractMapBasedMultimap abstractMapBasedMultimap, int i3) {
        int i4 = abstractMapBasedMultimap.totalSize + i3;
        abstractMapBasedMultimap.totalSize = i4;
        return i4;
    }

    public static /* synthetic */ int access$220(AbstractMapBasedMultimap abstractMapBasedMultimap, int i3) {
        int i4 = abstractMapBasedMultimap.totalSize - i3;
        abstractMapBasedMultimap.totalSize = i4;
        return i4;
    }

    private Collection<V> getOrCreateCollection(@ParametricNullness K k2) {
        Collection<V> collection = this.map.get(k2);
        if (collection != null) {
            return collection;
        }
        Collection<V> createCollection = createCollection(k2);
        this.map.put(k2, createCollection);
        return createCollection;
    }

    /* access modifiers changed from: private */
    public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
        return collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    /* access modifiers changed from: private */
    public void removeValuesForKey(@CheckForNull Object obj) {
        Collection collection = (Collection) Maps.safeRemove(this.map, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            this.totalSize -= size;
        }
    }

    public Map<K, Collection<V>> backingMap() {
        return this.map;
    }

    public void clear() {
        for (Collection<V> clear : this.map.values()) {
            clear.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    public boolean containsKey(@CheckForNull Object obj) {
        return this.map.containsKey(obj);
    }

    public Map<K, Collection<V>> createAsMap() {
        return new AsMap(this.map);
    }

    public abstract Collection<V> createCollection();

    public Collection<V> createCollection(@ParametricNullness K k2) {
        return createCollection();
    }

    public Collection<Map.Entry<K, V>> createEntries() {
        return this instanceof SetMultimap ? new AbstractMultimap.EntrySet(this) : new AbstractMultimap.Entries();
    }

    public Set<K> createKeySet() {
        return new KeySet(this.map);
    }

    public Multiset<K> createKeys() {
        return new Multimaps.Keys(this);
    }

    public final Map<K, Collection<V>> createMaybeNavigableAsMap() {
        Map<K, Collection<V>> map2 = this.map;
        return map2 instanceof NavigableMap ? new NavigableAsMap((NavigableMap) this.map) : map2 instanceof SortedMap ? new SortedAsMap((SortedMap) this.map) : new AsMap(this.map);
    }

    public final Set<K> createMaybeNavigableKeySet() {
        Map<K, Collection<V>> map2 = this.map;
        return map2 instanceof NavigableMap ? new NavigableKeySet((NavigableMap) this.map) : map2 instanceof SortedMap ? new SortedKeySet((SortedMap) this.map) : new KeySet(this.map);
    }

    public Collection<V> createUnmodifiableEmptyCollection() {
        return unmodifiableCollectionSubclass(createCollection());
    }

    public Collection<V> createValues() {
        return new AbstractMultimap.Values();
    }

    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr<Map.Entry<K, V>>(this) {
            public Map.Entry<K, V> output(@ParametricNullness K k2, @ParametricNullness V v2) {
                return Maps.immutableEntry(k2, v2);
            }
        };
    }

    public Collection<V> get(@ParametricNullness K k2) {
        Collection collection = this.map.get(k2);
        if (collection == null) {
            collection = createCollection(k2);
        }
        return wrapCollection(k2, collection);
    }

    public boolean put(@ParametricNullness K k2, @ParametricNullness V v2) {
        Collection collection = this.map.get(k2);
        if (collection == null) {
            Collection createCollection = createCollection(k2);
            if (createCollection.add(v2)) {
                this.totalSize++;
                this.map.put(k2, createCollection);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(v2)) {
            return false;
        } else {
            this.totalSize++;
            return true;
        }
    }

    public Collection<V> removeAll(@CheckForNull Object obj) {
        Collection remove = this.map.remove(obj);
        if (remove == null) {
            return createUnmodifiableEmptyCollection();
        }
        Collection createCollection = createCollection();
        createCollection.addAll(remove);
        this.totalSize -= remove.size();
        remove.clear();
        return unmodifiableCollectionSubclass(createCollection);
    }

    public Collection<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
        Iterator<? extends V> it = iterable.iterator();
        if (!it.hasNext()) {
            return removeAll(k2);
        }
        Collection orCreateCollection = getOrCreateCollection(k2);
        Collection createCollection = createCollection();
        createCollection.addAll(orCreateCollection);
        this.totalSize -= orCreateCollection.size();
        orCreateCollection.clear();
        while (it.hasNext()) {
            if (orCreateCollection.add(it.next())) {
                this.totalSize++;
            }
        }
        return unmodifiableCollectionSubclass(createCollection);
    }

    public final void setMap(Map<K, Collection<V>> map2) {
        this.map = map2;
        this.totalSize = 0;
        for (Collection next : map2.values()) {
            Preconditions.checkArgument(!next.isEmpty());
            this.totalSize = next.size() + this.totalSize;
        }
    }

    public int size() {
        return this.totalSize;
    }

    public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableCollection(collection);
    }

    public Iterator<V> valueIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr<V>(this) {
            @ParametricNullness
            public V output(@ParametricNullness K k2, @ParametricNullness V v2) {
                return v2;
            }
        };
    }

    public Collection<V> values() {
        return super.values();
    }

    public Collection<V> wrapCollection(@ParametricNullness K k2, Collection<V> collection) {
        return new WrappedCollection(k2, collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
    }

    public final List<V> wrapList(@ParametricNullness K k2, List<V> list, @CheckForNull AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
        return list instanceof RandomAccess ? new RandomAccessWrappedList(this, k2, list, wrappedCollection) : new WrappedList(k2, list, wrappedCollection);
    }

    public class WrappedCollection extends AbstractCollection<V> {
        @CheckForNull
        final AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor;
        @CheckForNull
        final Collection<V> ancestorDelegate;
        Collection<V> delegate;
        @ParametricNullness
        final K key;

        public WrappedCollection(@ParametricNullness K k2, Collection<V> collection, @CheckForNull AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            this.key = k2;
            this.delegate = collection;
            this.ancestor = wrappedCollection;
            this.ancestorDelegate = wrappedCollection == null ? null : wrappedCollection.getDelegate();
        }

        public boolean add(@ParametricNullness V v2) {
            refreshIfEmpty();
            boolean isEmpty = this.delegate.isEmpty();
            boolean add = this.delegate.add(v2);
            if (add) {
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    addToMap();
                }
            }
            return add;
        }

        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.delegate.addAll(collection);
            if (addAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                if (size == 0) {
                    addToMap();
                }
            }
            return addAll;
        }

        public void addToMap() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.addToMap();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
            }
        }

        public void clear() {
            int size = size();
            if (size != 0) {
                this.delegate.clear();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, size);
                removeIfEmpty();
            }
        }

        public boolean contains(@CheckForNull Object obj) {
            refreshIfEmpty();
            return this.delegate.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            refreshIfEmpty();
            return this.delegate.containsAll(collection);
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            refreshIfEmpty();
            return this.delegate.equals(obj);
        }

        @CheckForNull
        public AbstractMapBasedMultimap<K, V>.WrappedCollection getAncestor() {
            return this.ancestor;
        }

        public Collection<V> getDelegate() {
            return this.delegate;
        }

        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        public int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        public Iterator<V> iterator() {
            refreshIfEmpty();
            return new WrappedIterator();
        }

        public void refreshIfEmpty() {
            Collection<V> collection;
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.refreshIfEmpty();
                if (this.ancestor.getDelegate() != this.ancestorDelegate) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.delegate.isEmpty() && (collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.key)) != null) {
                this.delegate = collection;
            }
        }

        public boolean remove(@CheckForNull Object obj) {
            refreshIfEmpty();
            boolean remove = this.delegate.remove(obj);
            if (remove) {
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                removeIfEmpty();
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.delegate.removeAll(collection);
            if (removeAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                removeIfEmpty();
            }
            return removeAll;
        }

        public void removeIfEmpty() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.removeIfEmpty();
            } else if (this.delegate.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.key);
            }
        }

        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            int size = size();
            boolean retainAll = this.delegate.retainAll(collection);
            if (retainAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                removeIfEmpty();
            }
            return retainAll;
        }

        public int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        public String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }

        public class WrappedIterator implements Iterator<V> {
            final Iterator<V> delegateIterator;
            final Collection<V> originalDelegate;

            public WrappedIterator() {
                Collection<V> collection = WrappedCollection.this.delegate;
                this.originalDelegate = collection;
                this.delegateIterator = AbstractMapBasedMultimap.iteratorOrListIterator(collection);
            }

            public Iterator<V> getDelegateIterator() {
                validateIterator();
                return this.delegateIterator;
            }

            public boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            @ParametricNullness
            public V next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            public void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                WrappedCollection.this.removeIfEmpty();
            }

            public void validateIterator() {
                WrappedCollection.this.refreshIfEmpty();
                if (WrappedCollection.this.delegate != this.originalDelegate) {
                    throw new ConcurrentModificationException();
                }
            }

            public WrappedIterator(Iterator<V> it) {
                this.originalDelegate = WrappedCollection.this.delegate;
                this.delegateIterator = it;
            }
        }
    }

    public class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {

        public class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedIterator implements ListIterator<V> {
            public WrappedListIterator() {
                super();
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedList$WrappedListIterator, com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection$WrappedIterator] */
            private ListIterator<V> getDelegateListIterator() {
                return (ListIterator) getDelegateIterator();
            }

            public void add(@ParametricNullness V v2) {
                boolean isEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(v2);
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    WrappedList.this.addToMap();
                }
            }

            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            @ParametricNullness
            public V previous() {
                return getDelegateListIterator().previous();
            }

            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            public void set(@ParametricNullness V v2) {
                getDelegateListIterator().set(v2);
            }

            public WrappedListIterator(int i3) {
                super(WrappedList.this.getListDelegate().listIterator(i3));
            }
        }

        public WrappedList(@ParametricNullness K k2, List<V> list, @CheckForNull AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k2, list, wrappedCollection);
        }

        public void add(int i3, @ParametricNullness V v2) {
            refreshIfEmpty();
            boolean isEmpty = getDelegate().isEmpty();
            getListDelegate().add(i3, v2);
            AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
            if (isEmpty) {
                addToMap();
            }
        }

        public boolean addAll(int i3, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = getListDelegate().addAll(i3, collection);
            if (addAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, getDelegate().size() - size);
                if (size == 0) {
                    addToMap();
                }
            }
            return addAll;
        }

        @ParametricNullness
        public V get(int i3) {
            refreshIfEmpty();
            return getListDelegate().get(i3);
        }

        public List<V> getListDelegate() {
            return (List) getDelegate();
        }

        public int indexOf(@CheckForNull Object obj) {
            refreshIfEmpty();
            return getListDelegate().indexOf(obj);
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            refreshIfEmpty();
            return getListDelegate().lastIndexOf(obj);
        }

        public ListIterator<V> listIterator() {
            refreshIfEmpty();
            return new WrappedListIterator();
        }

        @ParametricNullness
        public V remove(int i3) {
            refreshIfEmpty();
            V remove = getListDelegate().remove(i3);
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
            removeIfEmpty();
            return remove;
        }

        @ParametricNullness
        public V set(int i3, @ParametricNullness V v2) {
            refreshIfEmpty();
            return getListDelegate().set(i3, v2);
        }

        public List<V> subList(int i3, int i4) {
            refreshIfEmpty();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object key = getKey();
            List subList = getListDelegate().subList(i3, i4);
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            this = this;
            if (ancestor != null) {
                this = getAncestor();
            }
            return abstractMapBasedMultimap.wrapList(key, subList, this);
        }

        public ListIterator<V> listIterator(int i3) {
            refreshIfEmpty();
            return new WrappedListIterator(i3);
        }
    }
}
