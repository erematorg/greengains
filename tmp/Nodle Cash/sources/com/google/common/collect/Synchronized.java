package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
final class Synchronized {

    public static class SynchronizedAsMap<K, V> extends SynchronizedMap<K, Collection<V>> {
        private static final long serialVersionUID = 0;
        @CheckForNull
        transient Set<Map.Entry<K, Collection<V>>> asMapEntrySet;
        @CheckForNull
        transient Collection<Collection<V>> asMapValues;

        public SynchronizedAsMap(Map<K, Collection<V>> map, @CheckForNull Object obj) {
            super(map, obj);
        }

        public boolean containsValue(@CheckForNull Object obj) {
            return values().contains(obj);
        }

        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            Set<Map.Entry<K, Collection<V>>> set;
            synchronized (this.mutex) {
                try {
                    if (this.asMapEntrySet == null) {
                        this.asMapEntrySet = new SynchronizedAsMapEntries(delegate().entrySet(), this.mutex);
                    }
                    set = this.asMapEntrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        public Collection<Collection<V>> values() {
            Collection<Collection<V>> collection;
            synchronized (this.mutex) {
                try {
                    if (this.asMapValues == null) {
                        this.asMapValues = new SynchronizedAsMapValues(delegate().values(), this.mutex);
                    }
                    collection = this.asMapValues;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        @CheckForNull
        public Collection<V> get(@CheckForNull Object obj) {
            Collection<V> collection;
            synchronized (this.mutex) {
                Collection collection2 = (Collection) super.get(obj);
                if (collection2 == null) {
                    collection = null;
                } else {
                    collection = Synchronized.typePreservingCollection(collection2, this.mutex);
                }
            }
            return collection;
        }
    }

    public static class SynchronizedAsMapValues<V> extends SynchronizedCollection<Collection<V>> {
        private static final long serialVersionUID = 0;

        public SynchronizedAsMapValues(Collection<Collection<V>> collection, @CheckForNull Object obj) {
            super(collection, obj);
        }

        public Iterator<Collection<V>> iterator() {
            return new TransformedIterator<Collection<V>, Collection<V>>(super.iterator()) {
                public Collection<V> transform(Collection<V> collection) {
                    return Synchronized.typePreservingCollection(collection, SynchronizedAsMapValues.this.mutex);
                }
            };
        }
    }

    @VisibleForTesting
    public static class SynchronizedBiMap<K, V> extends SynchronizedMap<K, V> implements BiMap<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        @RetainedWith
        @CheckForNull
        private transient BiMap<V, K> inverse;
        @CheckForNull
        private transient Set<V> valueSet;

        @CheckForNull
        public V forcePut(@ParametricNullness K k2, @ParametricNullness V v2) {
            V forcePut;
            synchronized (this.mutex) {
                forcePut = delegate().forcePut(k2, v2);
            }
            return forcePut;
        }

        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap;
            synchronized (this.mutex) {
                try {
                    if (this.inverse == null) {
                        this.inverse = new SynchronizedBiMap(delegate().inverse(), this.mutex, this);
                    }
                    biMap = this.inverse;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return biMap;
        }

        private SynchronizedBiMap(BiMap<K, V> biMap, @CheckForNull Object obj, @CheckForNull BiMap<V, K> biMap2) {
            super(biMap, obj);
            this.inverse = biMap2;
        }

        public Set<V> values() {
            Set<V> set;
            synchronized (this.mutex) {
                try {
                    if (this.valueSet == null) {
                        this.valueSet = Synchronized.set(delegate().values(), this.mutex);
                    }
                    set = this.valueSet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        public BiMap<K, V> delegate() {
            return (BiMap) super.delegate();
        }
    }

    @VisibleForTesting
    public static class SynchronizedCollection<E> extends SynchronizedObject implements Collection<E> {
        private static final long serialVersionUID = 0;

        public boolean add(E e3) {
            boolean add;
            synchronized (this.mutex) {
                add = delegate().add(e3);
            }
            return add;
        }

        public boolean addAll(Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(collection);
            }
            return addAll;
        }

        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public boolean contains(@CheckForNull Object obj) {
            boolean contains;
            synchronized (this.mutex) {
                contains = delegate().contains(obj);
            }
            return contains;
        }

        public boolean containsAll(Collection<?> collection) {
            boolean containsAll;
            synchronized (this.mutex) {
                containsAll = delegate().containsAll(collection);
            }
            return containsAll;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        public Iterator<E> iterator() {
            return delegate().iterator();
        }

        public boolean remove(@CheckForNull Object obj) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj);
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(collection);
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = delegate().retainAll(collection);
            }
            return retainAll;
        }

        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = delegate().toArray();
            }
            return array;
        }

        private SynchronizedCollection(Collection<E> collection, @CheckForNull Object obj) {
            super(collection, obj);
        }

        public Collection<E> delegate() {
            return (Collection) super.delegate();
        }

        public <T> T[] toArray(T[] tArr) {
            T[] array;
            synchronized (this.mutex) {
                array = delegate().toArray(tArr);
            }
            return array;
        }
    }

    @GwtIncompatible
    public static class SynchronizedEntry<K, V> extends SynchronizedObject implements Map.Entry<K, V> {
        private static final long serialVersionUID = 0;

        public SynchronizedEntry(Map.Entry<K, V> entry, @CheckForNull Object obj) {
            super(entry, obj);
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equals;
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public K getKey() {
            K key;
            synchronized (this.mutex) {
                key = delegate().getKey();
            }
            return key;
        }

        public V getValue() {
            V value;
            synchronized (this.mutex) {
                value = delegate().getValue();
            }
            return value;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        public V setValue(V v2) {
            V value;
            synchronized (this.mutex) {
                value = delegate().setValue(v2);
            }
            return value;
        }

        public Map.Entry<K, V> delegate() {
            return (Map.Entry) super.delegate();
        }
    }

    public static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {
        private static final long serialVersionUID = 0;

        public SynchronizedList(List<E> list, @CheckForNull Object obj) {
            super(list, obj);
        }

        public void add(int i3, E e3) {
            synchronized (this.mutex) {
                delegate().add(i3, e3);
            }
        }

        public boolean addAll(int i3, Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(i3, collection);
            }
            return addAll;
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public E get(int i3) {
            E e3;
            synchronized (this.mutex) {
                e3 = delegate().get(i3);
            }
            return e3;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        public int indexOf(@CheckForNull Object obj) {
            int indexOf;
            synchronized (this.mutex) {
                indexOf = delegate().indexOf(obj);
            }
            return indexOf;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int lastIndexOf;
            synchronized (this.mutex) {
                lastIndexOf = delegate().lastIndexOf(obj);
            }
            return lastIndexOf;
        }

        public ListIterator<E> listIterator() {
            return delegate().listIterator();
        }

        public E remove(int i3) {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove(i3);
            }
            return remove;
        }

        public E set(int i3, E e3) {
            E e4;
            synchronized (this.mutex) {
                e4 = delegate().set(i3, e3);
            }
            return e4;
        }

        public List<E> subList(int i3, int i4) {
            List<E> access$200;
            synchronized (this.mutex) {
                access$200 = Synchronized.list(delegate().subList(i3, i4), this.mutex);
            }
            return access$200;
        }

        public ListIterator<E> listIterator(int i3) {
            return delegate().listIterator(i3);
        }

        public List<E> delegate() {
            return (List) super.delegate();
        }
    }

    public static class SynchronizedListMultimap<K, V> extends SynchronizedMultimap<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        public SynchronizedListMultimap(ListMultimap<K, V> listMultimap, @CheckForNull Object obj) {
            super(listMultimap, obj);
        }

        public List<V> get(K k2) {
            List<V> access$200;
            synchronized (this.mutex) {
                access$200 = Synchronized.list(delegate().get((Object) k2), this.mutex);
            }
            return access$200;
        }

        public List<V> removeAll(@CheckForNull Object obj) {
            List<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        public List<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            List<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((Object) k2, (Iterable) iterable);
            }
            return replaceValues;
        }

        public ListMultimap<K, V> delegate() {
            return (ListMultimap) super.delegate();
        }
    }

    public static class SynchronizedMap<K, V> extends SynchronizedObject implements Map<K, V> {
        private static final long serialVersionUID = 0;
        @CheckForNull
        transient Set<Map.Entry<K, V>> entrySet;
        @CheckForNull
        transient Set<K> keySet;
        @CheckForNull
        transient Collection<V> values;

        public SynchronizedMap(Map<K, V> map, @CheckForNull Object obj) {
            super(map, obj);
        }

        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public boolean containsKey(@CheckForNull Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(obj);
            }
            return containsKey;
        }

        public boolean containsValue(@CheckForNull Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = Synchronized.set(delegate().entrySet(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @CheckForNull
        public V get(@CheckForNull Object obj) {
            V v2;
            synchronized (this.mutex) {
                v2 = delegate().get(obj);
            }
            return v2;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                try {
                    if (this.keySet == null) {
                        this.keySet = Synchronized.set(delegate().keySet(), this.mutex);
                    }
                    set = this.keySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        @CheckForNull
        public V put(K k2, V v2) {
            V put;
            synchronized (this.mutex) {
                put = delegate().put(k2, v2);
            }
            return put;
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.mutex) {
                delegate().putAll(map);
            }
        }

        @CheckForNull
        public V remove(@CheckForNull Object obj) {
            V remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj);
            }
            return remove;
        }

        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                try {
                    if (this.values == null) {
                        this.values = Synchronized.collection(delegate().values(), this.mutex);
                    }
                    collection = this.values;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        public Map<K, V> delegate() {
            return (Map) super.delegate();
        }
    }

    public static class SynchronizedMultimap<K, V> extends SynchronizedObject implements Multimap<K, V> {
        private static final long serialVersionUID = 0;
        @CheckForNull
        transient Map<K, Collection<V>> asMap;
        @CheckForNull
        transient Collection<Map.Entry<K, V>> entries;
        @CheckForNull
        transient Set<K> keySet;
        @CheckForNull
        transient Multiset<K> keys;
        @CheckForNull
        transient Collection<V> valuesCollection;

        public SynchronizedMultimap(Multimap<K, V> multimap, @CheckForNull Object obj) {
            super(multimap, obj);
        }

        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map;
            synchronized (this.mutex) {
                try {
                    if (this.asMap == null) {
                        this.asMap = new SynchronizedAsMap(delegate().asMap(), this.mutex);
                    }
                    map = this.asMap;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return map;
        }

        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public boolean containsEntry(@CheckForNull Object obj, @CheckForNull Object obj2) {
            boolean containsEntry;
            synchronized (this.mutex) {
                containsEntry = delegate().containsEntry(obj, obj2);
            }
            return containsEntry;
        }

        public boolean containsKey(@CheckForNull Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(obj);
            }
            return containsKey;
        }

        public boolean containsValue(@CheckForNull Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection;
            synchronized (this.mutex) {
                try {
                    if (this.entries == null) {
                        this.entries = Synchronized.typePreservingCollection(delegate().entries(), this.mutex);
                    }
                    collection = this.entries;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public Collection<V> get(@ParametricNullness K k2) {
            Collection<V> access$400;
            synchronized (this.mutex) {
                access$400 = Synchronized.typePreservingCollection(delegate().get(k2), this.mutex);
            }
            return access$400;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                try {
                    if (this.keySet == null) {
                        this.keySet = Synchronized.typePreservingSet(delegate().keySet(), this.mutex);
                    }
                    set = this.keySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        public Multiset<K> keys() {
            Multiset<K> multiset;
            synchronized (this.mutex) {
                try {
                    if (this.keys == null) {
                        this.keys = Synchronized.multiset(delegate().keys(), this.mutex);
                    }
                    multiset = this.keys;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return multiset;
        }

        public boolean put(@ParametricNullness K k2, @ParametricNullness V v2) {
            boolean put;
            synchronized (this.mutex) {
                put = delegate().put(k2, v2);
            }
            return put;
        }

        public boolean putAll(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(k2, iterable);
            }
            return putAll;
        }

        public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, obj2);
            }
            return remove;
        }

        public Collection<V> removeAll(@CheckForNull Object obj) {
            Collection<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        public Collection<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            Collection<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues(k2, iterable);
            }
            return replaceValues;
        }

        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                try {
                    if (this.valuesCollection == null) {
                        this.valuesCollection = Synchronized.collection(delegate().values(), this.mutex);
                    }
                    collection = this.valuesCollection;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return collection;
        }

        public Multimap<K, V> delegate() {
            return (Multimap) super.delegate();
        }

        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(multimap);
            }
            return putAll;
        }
    }

    public static class SynchronizedObject implements Serializable {
        @GwtIncompatible
        @J2ktIncompatible
        private static final long serialVersionUID = 0;
        final Object delegate;
        final Object mutex;

        public SynchronizedObject(Object obj, @CheckForNull Object obj2) {
            this.delegate = Preconditions.checkNotNull(obj);
            this.mutex = obj2 == null ? this : obj2;
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
            }
        }

        public Object delegate() {
            return this.delegate;
        }

        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.delegate.toString();
            }
            return obj;
        }
    }

    public static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {
        private static final long serialVersionUID = 0;

        public SynchronizedRandomAccessList(List<E> list, @CheckForNull Object obj) {
            super(list, obj);
        }
    }

    public static class SynchronizedSetMultimap<K, V> extends SynchronizedMultimap<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;
        @CheckForNull
        transient Set<Map.Entry<K, V>> entrySet;

        public SynchronizedSetMultimap(SetMultimap<K, V> setMultimap, @CheckForNull Object obj) {
            super(setMultimap, obj);
        }

        public Set<Map.Entry<K, V>> entries() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = Synchronized.set(delegate().entries(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        public Set<V> get(K k2) {
            Set<V> set;
            synchronized (this.mutex) {
                set = Synchronized.set(delegate().get((Object) k2), this.mutex);
            }
            return set;
        }

        public Set<V> removeAll(@CheckForNull Object obj) {
            Set<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        public Set<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            Set<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((Object) k2, (Iterable) iterable);
            }
            return replaceValues;
        }

        public SetMultimap<K, V> delegate() {
            return (SetMultimap) super.delegate();
        }
    }

    public static final class SynchronizedTable<R, C, V> extends SynchronizedObject implements Table<R, C, V> {
        public SynchronizedTable(Table<R, C, V> table, @CheckForNull Object obj) {
            super(table, obj);
        }

        public Set<Table.Cell<R, C, V>> cellSet() {
            Set<Table.Cell<R, C, V>> set;
            synchronized (this.mutex) {
                set = Synchronized.set(delegate().cellSet(), this.mutex);
            }
            return set;
        }

        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public Map<R, V> column(@ParametricNullness C c3) {
            Map<R, V> map;
            synchronized (this.mutex) {
                map = Synchronized.map(delegate().column(c3), this.mutex);
            }
            return map;
        }

        public Set<C> columnKeySet() {
            Set<C> set;
            synchronized (this.mutex) {
                set = Synchronized.set(delegate().columnKeySet(), this.mutex);
            }
            return set;
        }

        public Map<C, Map<R, V>> columnMap() {
            Map<C, Map<R, V>> map;
            synchronized (this.mutex) {
                map = Synchronized.map(Maps.transformValues(delegate().columnMap(), new Function<Map<R, V>, Map<R, V>>() {
                    public Map<R, V> apply(Map<R, V> map) {
                        return Synchronized.map(map, SynchronizedTable.this.mutex);
                    }
                }), this.mutex);
            }
            return map;
        }

        public boolean contains(@CheckForNull Object obj, @CheckForNull Object obj2) {
            boolean contains;
            synchronized (this.mutex) {
                contains = delegate().contains(obj, obj2);
            }
            return contains;
        }

        public boolean containsColumn(@CheckForNull Object obj) {
            boolean containsColumn;
            synchronized (this.mutex) {
                containsColumn = delegate().containsColumn(obj);
            }
            return containsColumn;
        }

        public boolean containsRow(@CheckForNull Object obj) {
            boolean containsRow;
            synchronized (this.mutex) {
                containsRow = delegate().containsRow(obj);
            }
            return containsRow;
        }

        public boolean containsValue(@CheckForNull Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @CheckForNull
        public V get(@CheckForNull Object obj, @CheckForNull Object obj2) {
            V v2;
            synchronized (this.mutex) {
                v2 = delegate().get(obj, obj2);
            }
            return v2;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @CheckForNull
        public V put(@ParametricNullness R r2, @ParametricNullness C c3, @ParametricNullness V v2) {
            V put;
            synchronized (this.mutex) {
                put = delegate().put(r2, c3, v2);
            }
            return put;
        }

        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            synchronized (this.mutex) {
                delegate().putAll(table);
            }
        }

        @CheckForNull
        public V remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
            V remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, obj2);
            }
            return remove;
        }

        public Map<C, V> row(@ParametricNullness R r2) {
            Map<C, V> map;
            synchronized (this.mutex) {
                map = Synchronized.map(delegate().row(r2), this.mutex);
            }
            return map;
        }

        public Set<R> rowKeySet() {
            Set<R> set;
            synchronized (this.mutex) {
                set = Synchronized.set(delegate().rowKeySet(), this.mutex);
            }
            return set;
        }

        public Map<R, Map<C, V>> rowMap() {
            Map<R, Map<C, V>> map;
            synchronized (this.mutex) {
                map = Synchronized.map(Maps.transformValues(delegate().rowMap(), new Function<Map<C, V>, Map<C, V>>() {
                    public Map<C, V> apply(Map<C, V> map) {
                        return Synchronized.map(map, SynchronizedTable.this.mutex);
                    }
                }), this.mutex);
            }
            return map;
        }

        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> access$500;
            synchronized (this.mutex) {
                access$500 = Synchronized.collection(delegate().values(), this.mutex);
            }
            return access$500;
        }

        public Table<R, C, V> delegate() {
            return (Table) super.delegate();
        }
    }

    private Synchronized() {
    }

    public static <K, V> BiMap<K, V> biMap(BiMap<K, V> biMap, @CheckForNull Object obj) {
        return ((biMap instanceof SynchronizedBiMap) || (biMap instanceof ImmutableBiMap)) ? biMap : new SynchronizedBiMap(biMap, obj, (BiMap) null);
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> collection(Collection<E> collection, @CheckForNull Object obj) {
        return new SynchronizedCollection(collection, obj);
    }

    public static <E> Deque<E> deque(Deque<E> deque, @CheckForNull Object obj) {
        return new SynchronizedDeque(deque, obj);
    }

    /* access modifiers changed from: private */
    public static <E> List<E> list(List<E> list, @CheckForNull Object obj) {
        return list instanceof RandomAccess ? new SynchronizedRandomAccessList(list, obj) : new SynchronizedList(list, obj);
    }

    public static <K, V> ListMultimap<K, V> listMultimap(ListMultimap<K, V> listMultimap, @CheckForNull Object obj) {
        return ((listMultimap instanceof SynchronizedListMultimap) || (listMultimap instanceof BaseImmutableMultimap)) ? listMultimap : new SynchronizedListMultimap(listMultimap, obj);
    }

    @VisibleForTesting
    public static <K, V> Map<K, V> map(Map<K, V> map, @CheckForNull Object obj) {
        return new SynchronizedMap(map, obj);
    }

    public static <K, V> Multimap<K, V> multimap(Multimap<K, V> multimap, @CheckForNull Object obj) {
        return ((multimap instanceof SynchronizedMultimap) || (multimap instanceof BaseImmutableMultimap)) ? multimap : new SynchronizedMultimap(multimap, obj);
    }

    public static <E> Multiset<E> multiset(Multiset<E> multiset, @CheckForNull Object obj) {
        return ((multiset instanceof SynchronizedMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new SynchronizedMultiset(multiset, obj);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> navigableMap) {
        return navigableMap(navigableMap, (Object) null);
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> navigableSet(NavigableSet<E> navigableSet, @CheckForNull Object obj) {
        return new SynchronizedNavigableSet(navigableSet, obj);
    }

    /* access modifiers changed from: private */
    @CheckForNull
    @GwtIncompatible
    public static <K, V> Map.Entry<K, V> nullableSynchronizedEntry(@CheckForNull Map.Entry<K, V> entry, @CheckForNull Object obj) {
        if (entry == null) {
            return null;
        }
        return new SynchronizedEntry(entry, obj);
    }

    public static <E> Queue<E> queue(Queue<E> queue, @CheckForNull Object obj) {
        return queue instanceof SynchronizedQueue ? queue : new SynchronizedQueue(queue, obj);
    }

    @VisibleForTesting
    public static <E> Set<E> set(Set<E> set, @CheckForNull Object obj) {
        return new SynchronizedSet(set, obj);
    }

    public static <K, V> SetMultimap<K, V> setMultimap(SetMultimap<K, V> setMultimap, @CheckForNull Object obj) {
        return ((setMultimap instanceof SynchronizedSetMultimap) || (setMultimap instanceof BaseImmutableMultimap)) ? setMultimap : new SynchronizedSetMultimap(setMultimap, obj);
    }

    public static <K, V> SortedMap<K, V> sortedMap(SortedMap<K, V> sortedMap, @CheckForNull Object obj) {
        return new SynchronizedSortedMap(sortedMap, obj);
    }

    /* access modifiers changed from: private */
    public static <E> SortedSet<E> sortedSet(SortedSet<E> sortedSet, @CheckForNull Object obj) {
        return new SynchronizedSortedSet(sortedSet, obj);
    }

    public static <K, V> SortedSetMultimap<K, V> sortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap, @CheckForNull Object obj) {
        return sortedSetMultimap instanceof SynchronizedSortedSetMultimap ? sortedSetMultimap : new SynchronizedSortedSetMultimap(sortedSetMultimap, obj);
    }

    public static <R, C, V> Table<R, C, V> table(Table<R, C, V> table, @CheckForNull Object obj) {
        return new SynchronizedTable(table, obj);
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> typePreservingCollection(Collection<E> collection, @CheckForNull Object obj) {
        return collection instanceof SortedSet ? sortedSet((SortedSet) collection, obj) : collection instanceof Set ? set((Set) collection, obj) : collection instanceof List ? list((List) collection, obj) : collection(collection, obj);
    }

    /* access modifiers changed from: private */
    public static <E> Set<E> typePreservingSet(Set<E> set, @CheckForNull Object obj) {
        return set instanceof SortedSet ? sortedSet((SortedSet) set, obj) : set(set, obj);
    }

    public static class SynchronizedMultiset<E> extends SynchronizedCollection<E> implements Multiset<E> {
        private static final long serialVersionUID = 0;
        @CheckForNull
        transient Set<E> elementSet;
        @CheckForNull
        transient Set<Multiset.Entry<E>> entrySet;

        public SynchronizedMultiset(Multiset<E> multiset, @CheckForNull Object obj) {
            super(multiset, obj);
        }

        public int add(@ParametricNullness E e3, int i3) {
            int add;
            synchronized (this.mutex) {
                add = delegate().add(e3, i3);
            }
            return add;
        }

        public int count(@CheckForNull Object obj) {
            int count;
            synchronized (this.mutex) {
                count = delegate().count(obj);
            }
            return count;
        }

        public Set<E> elementSet() {
            Set<E> set;
            synchronized (this.mutex) {
                try {
                    if (this.elementSet == null) {
                        this.elementSet = Synchronized.typePreservingSet(delegate().elementSet(), this.mutex);
                    }
                    set = this.elementSet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set;
            synchronized (this.mutex) {
                try {
                    if (this.entrySet == null) {
                        this.entrySet = Synchronized.typePreservingSet(delegate().entrySet(), this.mutex);
                    }
                    set = this.entrySet;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return set;
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        public int remove(@CheckForNull Object obj, int i3) {
            int remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, i3);
            }
            return remove;
        }

        public int setCount(@ParametricNullness E e3, int i3) {
            int count;
            synchronized (this.mutex) {
                count = delegate().setCount(e3, i3);
            }
            return count;
        }

        public Multiset<E> delegate() {
            return (Multiset) super.delegate();
        }

        public boolean setCount(@ParametricNullness E e3, int i3, int i4) {
            boolean count;
            synchronized (this.mutex) {
                count = delegate().setCount(e3, i3, i4);
            }
            return count;
        }
    }

    public static class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
        private static final long serialVersionUID = 0;

        public SynchronizedQueue(Queue<E> queue, @CheckForNull Object obj) {
            super(queue, obj);
        }

        public E element() {
            E element;
            synchronized (this.mutex) {
                element = delegate().element();
            }
            return element;
        }

        public boolean offer(E e3) {
            boolean offer;
            synchronized (this.mutex) {
                offer = delegate().offer(e3);
            }
            return offer;
        }

        @CheckForNull
        public E peek() {
            E peek;
            synchronized (this.mutex) {
                peek = delegate().peek();
            }
            return peek;
        }

        @CheckForNull
        public E poll() {
            E poll;
            synchronized (this.mutex) {
                poll = delegate().poll();
            }
            return poll;
        }

        public E remove() {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove();
            }
            return remove;
        }

        public Queue<E> delegate() {
            return (Queue) super.delegate();
        }
    }

    public static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {
        private static final long serialVersionUID = 0;

        public SynchronizedSet(Set<E> set, @CheckForNull Object obj) {
            super(set, obj);
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        public Set<E> delegate() {
            return (Set) super.delegate();
        }
    }

    public static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {
        private static final long serialVersionUID = 0;

        public SynchronizedSortedMap(SortedMap<K, V> sortedMap, @CheckForNull Object obj) {
            super(sortedMap, obj);
        }

        @CheckForNull
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        public K firstKey() {
            K firstKey;
            synchronized (this.mutex) {
                firstKey = delegate().firstKey();
            }
            return firstKey;
        }

        public SortedMap<K, V> headMap(K k2) {
            SortedMap<K, V> sortedMap;
            synchronized (this.mutex) {
                sortedMap = Synchronized.sortedMap(delegate().headMap(k2), this.mutex);
            }
            return sortedMap;
        }

        public K lastKey() {
            K lastKey;
            synchronized (this.mutex) {
                lastKey = delegate().lastKey();
            }
            return lastKey;
        }

        public SortedMap<K, V> subMap(K k2, K k3) {
            SortedMap<K, V> sortedMap;
            synchronized (this.mutex) {
                sortedMap = Synchronized.sortedMap(delegate().subMap(k2, k3), this.mutex);
            }
            return sortedMap;
        }

        public SortedMap<K, V> tailMap(K k2) {
            SortedMap<K, V> sortedMap;
            synchronized (this.mutex) {
                sortedMap = Synchronized.sortedMap(delegate().tailMap(k2), this.mutex);
            }
            return sortedMap;
        }

        public SortedMap<K, V> delegate() {
            return (SortedMap) super.delegate();
        }
    }

    public static class SynchronizedSortedSetMultimap<K, V> extends SynchronizedSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        public SynchronizedSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap, @CheckForNull Object obj) {
            super(sortedSetMultimap, obj);
        }

        @CheckForNull
        public Comparator<? super V> valueComparator() {
            Comparator<? super V> valueComparator;
            synchronized (this.mutex) {
                valueComparator = delegate().valueComparator();
            }
            return valueComparator;
        }

        public SortedSet<V> get(K k2) {
            SortedSet<V> access$100;
            synchronized (this.mutex) {
                access$100 = Synchronized.sortedSet(delegate().get((Object) k2), this.mutex);
            }
            return access$100;
        }

        public SortedSet<V> removeAll(@CheckForNull Object obj) {
            SortedSet<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        public SortedSet<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            SortedSet<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((Object) k2, (Iterable) iterable);
            }
            return replaceValues;
        }

        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) super.delegate();
        }
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> navigableMap, @CheckForNull Object obj) {
        return new SynchronizedNavigableMap(navigableMap, obj);
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> navigableSet(NavigableSet<E> navigableSet) {
        return navigableSet(navigableSet, (Object) null);
    }

    public static class SynchronizedAsMapEntries<K, V> extends SynchronizedSet<Map.Entry<K, Collection<V>>> {
        private static final long serialVersionUID = 0;

        public SynchronizedAsMapEntries(Set<Map.Entry<K, Collection<V>>> set, @CheckForNull Object obj) {
            super(set, obj);
        }

        public boolean contains(@CheckForNull Object obj) {
            boolean containsEntryImpl;
            synchronized (this.mutex) {
                containsEntryImpl = Maps.containsEntryImpl(delegate(), obj);
            }
            return containsEntryImpl;
        }

        public boolean containsAll(Collection<?> collection) {
            boolean containsAllImpl;
            synchronized (this.mutex) {
                containsAllImpl = Collections2.containsAllImpl(delegate(), collection);
            }
            return containsAllImpl;
        }

        public boolean equals(@CheckForNull Object obj) {
            boolean equalsImpl;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equalsImpl = Sets.equalsImpl(delegate(), obj);
            }
            return equalsImpl;
        }

        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>>(super.iterator()) {
                public Map.Entry<K, Collection<V>> transform(final Map.Entry<K, Collection<V>> entry) {
                    return new ForwardingMapEntry<K, Collection<V>>() {
                        public Map.Entry<K, Collection<V>> delegate() {
                            return entry;
                        }

                        public Collection<V> getValue() {
                            return Synchronized.typePreservingCollection((Collection) entry.getValue(), SynchronizedAsMapEntries.this.mutex);
                        }
                    };
                }
            };
        }

        public boolean remove(@CheckForNull Object obj) {
            boolean removeEntryImpl;
            synchronized (this.mutex) {
                removeEntryImpl = Maps.removeEntryImpl(delegate(), obj);
            }
            return removeEntryImpl;
        }

        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = Iterators.removeAll(delegate().iterator(), collection);
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = Iterators.retainAll(delegate().iterator(), collection);
            }
            return retainAll;
        }

        public Object[] toArray() {
            Object[] arrayImpl;
            synchronized (this.mutex) {
                arrayImpl = ObjectArrays.toArrayImpl(delegate());
            }
            return arrayImpl;
        }

        public <T> T[] toArray(T[] tArr) {
            T[] arrayImpl;
            synchronized (this.mutex) {
                arrayImpl = ObjectArrays.toArrayImpl(delegate(), tArr);
            }
            return arrayImpl;
        }
    }

    public static final class SynchronizedDeque<E> extends SynchronizedQueue<E> implements Deque<E> {
        private static final long serialVersionUID = 0;

        public SynchronizedDeque(Deque<E> deque, @CheckForNull Object obj) {
            super(deque, obj);
        }

        public void addFirst(E e3) {
            synchronized (this.mutex) {
                delegate().addFirst(e3);
            }
        }

        public void addLast(E e3) {
            synchronized (this.mutex) {
                delegate().addLast(e3);
            }
        }

        public Iterator<E> descendingIterator() {
            Iterator<E> descendingIterator;
            synchronized (this.mutex) {
                descendingIterator = delegate().descendingIterator();
            }
            return descendingIterator;
        }

        public E getFirst() {
            E first;
            synchronized (this.mutex) {
                first = delegate().getFirst();
            }
            return first;
        }

        public E getLast() {
            E last;
            synchronized (this.mutex) {
                last = delegate().getLast();
            }
            return last;
        }

        public boolean offerFirst(E e3) {
            boolean offerFirst;
            synchronized (this.mutex) {
                offerFirst = delegate().offerFirst(e3);
            }
            return offerFirst;
        }

        public boolean offerLast(E e3) {
            boolean offerLast;
            synchronized (this.mutex) {
                offerLast = delegate().offerLast(e3);
            }
            return offerLast;
        }

        @CheckForNull
        public E peekFirst() {
            E peekFirst;
            synchronized (this.mutex) {
                peekFirst = delegate().peekFirst();
            }
            return peekFirst;
        }

        @CheckForNull
        public E peekLast() {
            E peekLast;
            synchronized (this.mutex) {
                peekLast = delegate().peekLast();
            }
            return peekLast;
        }

        @CheckForNull
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @CheckForNull
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        public E pop() {
            E pop;
            synchronized (this.mutex) {
                pop = delegate().pop();
            }
            return pop;
        }

        public void push(E e3) {
            synchronized (this.mutex) {
                delegate().push(e3);
            }
        }

        public E removeFirst() {
            E removeFirst;
            synchronized (this.mutex) {
                removeFirst = delegate().removeFirst();
            }
            return removeFirst;
        }

        public boolean removeFirstOccurrence(@CheckForNull Object obj) {
            boolean removeFirstOccurrence;
            synchronized (this.mutex) {
                removeFirstOccurrence = delegate().removeFirstOccurrence(obj);
            }
            return removeFirstOccurrence;
        }

        public E removeLast() {
            E removeLast;
            synchronized (this.mutex) {
                removeLast = delegate().removeLast();
            }
            return removeLast;
        }

        public boolean removeLastOccurrence(@CheckForNull Object obj) {
            boolean removeLastOccurrence;
            synchronized (this.mutex) {
                removeLastOccurrence = delegate().removeLastOccurrence(obj);
            }
            return removeLastOccurrence;
        }

        public Deque<E> delegate() {
            return (Deque) super.delegate();
        }
    }

    @GwtIncompatible
    @VisibleForTesting
    public static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {
        private static final long serialVersionUID = 0;
        @CheckForNull
        transient NavigableSet<K> descendingKeySet;
        @CheckForNull
        transient NavigableMap<K, V> descendingMap;
        @CheckForNull
        transient NavigableSet<K> navigableKeySet;

        public SynchronizedNavigableMap(NavigableMap<K, V> navigableMap, @CheckForNull Object obj) {
            super(navigableMap, obj);
        }

        @CheckForNull
        public Map.Entry<K, V> ceilingEntry(K k2) {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().ceilingEntry(k2), this.mutex);
            }
            return access$700;
        }

        @CheckForNull
        public K ceilingKey(K k2) {
            K ceilingKey;
            synchronized (this.mutex) {
                ceilingKey = delegate().ceilingKey(k2);
            }
            return ceilingKey;
        }

        public NavigableSet<K> descendingKeySet() {
            synchronized (this.mutex) {
                try {
                    NavigableSet<K> navigableSet = this.descendingKeySet;
                    if (navigableSet != null) {
                        return navigableSet;
                    }
                    NavigableSet<K> navigableSet2 = Synchronized.navigableSet(delegate().descendingKeySet(), this.mutex);
                    this.descendingKeySet = navigableSet2;
                    return navigableSet2;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public NavigableMap<K, V> descendingMap() {
            synchronized (this.mutex) {
                try {
                    NavigableMap<K, V> navigableMap = this.descendingMap;
                    if (navigableMap != null) {
                        return navigableMap;
                    }
                    NavigableMap<K, V> navigableMap2 = Synchronized.navigableMap(delegate().descendingMap(), this.mutex);
                    this.descendingMap = navigableMap2;
                    return navigableMap2;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @CheckForNull
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().firstEntry(), this.mutex);
            }
            return access$700;
        }

        @CheckForNull
        public Map.Entry<K, V> floorEntry(K k2) {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().floorEntry(k2), this.mutex);
            }
            return access$700;
        }

        @CheckForNull
        public K floorKey(K k2) {
            K floorKey;
            synchronized (this.mutex) {
                floorKey = delegate().floorKey(k2);
            }
            return floorKey;
        }

        public NavigableMap<K, V> headMap(K k2, boolean z2) {
            NavigableMap<K, V> navigableMap;
            synchronized (this.mutex) {
                navigableMap = Synchronized.navigableMap(delegate().headMap(k2, z2), this.mutex);
            }
            return navigableMap;
        }

        @CheckForNull
        public Map.Entry<K, V> higherEntry(K k2) {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().higherEntry(k2), this.mutex);
            }
            return access$700;
        }

        @CheckForNull
        public K higherKey(K k2) {
            K higherKey;
            synchronized (this.mutex) {
                higherKey = delegate().higherKey(k2);
            }
            return higherKey;
        }

        public Set<K> keySet() {
            return navigableKeySet();
        }

        @CheckForNull
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().lastEntry(), this.mutex);
            }
            return access$700;
        }

        @CheckForNull
        public Map.Entry<K, V> lowerEntry(K k2) {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().lowerEntry(k2), this.mutex);
            }
            return access$700;
        }

        @CheckForNull
        public K lowerKey(K k2) {
            K lowerKey;
            synchronized (this.mutex) {
                lowerKey = delegate().lowerKey(k2);
            }
            return lowerKey;
        }

        public NavigableSet<K> navigableKeySet() {
            synchronized (this.mutex) {
                try {
                    NavigableSet<K> navigableSet = this.navigableKeySet;
                    if (navigableSet != null) {
                        return navigableSet;
                    }
                    NavigableSet<K> navigableSet2 = Synchronized.navigableSet(delegate().navigableKeySet(), this.mutex);
                    this.navigableKeySet = navigableSet2;
                    return navigableSet2;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @CheckForNull
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().pollFirstEntry(), this.mutex);
            }
            return access$700;
        }

        @CheckForNull
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> access$700;
            synchronized (this.mutex) {
                access$700 = Synchronized.nullableSynchronizedEntry(delegate().pollLastEntry(), this.mutex);
            }
            return access$700;
        }

        public NavigableMap<K, V> subMap(K k2, boolean z2, K k3, boolean z3) {
            NavigableMap<K, V> navigableMap;
            synchronized (this.mutex) {
                navigableMap = Synchronized.navigableMap(delegate().subMap(k2, z2, k3, z3), this.mutex);
            }
            return navigableMap;
        }

        public NavigableMap<K, V> tailMap(K k2, boolean z2) {
            NavigableMap<K, V> navigableMap;
            synchronized (this.mutex) {
                navigableMap = Synchronized.navigableMap(delegate().tailMap(k2, z2), this.mutex);
            }
            return navigableMap;
        }

        public NavigableMap<K, V> delegate() {
            return (NavigableMap) super.delegate();
        }

        public SortedMap<K, V> headMap(K k2) {
            return headMap(k2, false);
        }

        public SortedMap<K, V> subMap(K k2, K k3) {
            return subMap(k2, true, k3, false);
        }

        public SortedMap<K, V> tailMap(K k2) {
            return tailMap(k2, true);
        }
    }

    @GwtIncompatible
    @VisibleForTesting
    public static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {
        private static final long serialVersionUID = 0;
        @CheckForNull
        transient NavigableSet<E> descendingSet;

        public SynchronizedNavigableSet(NavigableSet<E> navigableSet, @CheckForNull Object obj) {
            super(navigableSet, obj);
        }

        @CheckForNull
        public E ceiling(E e3) {
            E ceiling;
            synchronized (this.mutex) {
                ceiling = delegate().ceiling(e3);
            }
            return ceiling;
        }

        public Iterator<E> descendingIterator() {
            return delegate().descendingIterator();
        }

        public NavigableSet<E> descendingSet() {
            synchronized (this.mutex) {
                try {
                    NavigableSet<E> navigableSet = this.descendingSet;
                    if (navigableSet != null) {
                        return navigableSet;
                    }
                    NavigableSet<E> navigableSet2 = Synchronized.navigableSet(delegate().descendingSet(), this.mutex);
                    this.descendingSet = navigableSet2;
                    return navigableSet2;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @CheckForNull
        public E floor(E e3) {
            E floor;
            synchronized (this.mutex) {
                floor = delegate().floor(e3);
            }
            return floor;
        }

        public NavigableSet<E> headSet(E e3, boolean z2) {
            NavigableSet<E> navigableSet;
            synchronized (this.mutex) {
                navigableSet = Synchronized.navigableSet(delegate().headSet(e3, z2), this.mutex);
            }
            return navigableSet;
        }

        @CheckForNull
        public E higher(E e3) {
            E higher;
            synchronized (this.mutex) {
                higher = delegate().higher(e3);
            }
            return higher;
        }

        @CheckForNull
        public E lower(E e3) {
            E lower;
            synchronized (this.mutex) {
                lower = delegate().lower(e3);
            }
            return lower;
        }

        @CheckForNull
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @CheckForNull
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        public NavigableSet<E> subSet(E e3, boolean z2, E e4, boolean z3) {
            NavigableSet<E> navigableSet;
            synchronized (this.mutex) {
                navigableSet = Synchronized.navigableSet(delegate().subSet(e3, z2, e4, z3), this.mutex);
            }
            return navigableSet;
        }

        public NavigableSet<E> tailSet(E e3, boolean z2) {
            NavigableSet<E> navigableSet;
            synchronized (this.mutex) {
                navigableSet = Synchronized.navigableSet(delegate().tailSet(e3, z2), this.mutex);
            }
            return navigableSet;
        }

        public SortedSet<E> headSet(E e3) {
            return headSet(e3, false);
        }

        public SortedSet<E> tailSet(E e3) {
            return tailSet(e3, true);
        }

        public NavigableSet<E> delegate() {
            return (NavigableSet) super.delegate();
        }

        public SortedSet<E> subSet(E e3, E e4) {
            return subSet(e3, true, e4, false);
        }
    }

    public static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {
        private static final long serialVersionUID = 0;

        public SynchronizedSortedSet(SortedSet<E> sortedSet, @CheckForNull Object obj) {
            super(sortedSet, obj);
        }

        @CheckForNull
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        public E first() {
            E first;
            synchronized (this.mutex) {
                first = delegate().first();
            }
            return first;
        }

        public SortedSet<E> headSet(E e3) {
            SortedSet<E> access$100;
            synchronized (this.mutex) {
                access$100 = Synchronized.sortedSet(delegate().headSet(e3), this.mutex);
            }
            return access$100;
        }

        public E last() {
            E last;
            synchronized (this.mutex) {
                last = delegate().last();
            }
            return last;
        }

        public SortedSet<E> subSet(E e3, E e4) {
            SortedSet<E> access$100;
            synchronized (this.mutex) {
                access$100 = Synchronized.sortedSet(delegate().subSet(e3, e4), this.mutex);
            }
            return access$100;
        }

        public SortedSet<E> tailSet(E e3) {
            SortedSet<E> access$100;
            synchronized (this.mutex) {
                access$100 = Synchronized.sortedSet(delegate().tailSet(e3), this.mutex);
            }
            return access$100;
        }

        public SortedSet<E> delegate() {
            return (SortedSet) super.delegate();
        }
    }
}
