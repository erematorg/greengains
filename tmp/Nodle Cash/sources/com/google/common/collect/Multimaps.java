package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Multimaps {

    public static final class AsMap<K, V> extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        /* access modifiers changed from: private */
        @Weak
        public final Multimap<K, V> multimap;

        public class EntrySet extends Maps.EntrySet<K, Collection<V>> {
            public EntrySet() {
            }

            /* access modifiers changed from: private */
            public /* synthetic */ Collection lambda$iterator$0(Object obj) {
                return AsMap.this.multimap.get(obj);
            }

            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return Maps.asMapEntryIterator(AsMap.this.multimap.keySet(), new c(this));
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
                AsMap.this.removeValuesForKey(entry.getKey());
                return true;
            }
        }

        public AsMap(Multimap<K, V> multimap2) {
            this.multimap = (Multimap) Preconditions.checkNotNull(multimap2);
        }

        public void clear() {
            this.multimap.clear();
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return this.multimap.containsKey(obj);
        }

        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new EntrySet();
        }

        public boolean isEmpty() {
            return this.multimap.isEmpty();
        }

        public Set<K> keySet() {
            return this.multimap.keySet();
        }

        public void removeValuesForKey(@CheckForNull Object obj) {
            this.multimap.keySet().remove(obj);
        }

        public int size() {
            return this.multimap.keySet().size();
        }

        @CheckForNull
        public Collection<V> get(@CheckForNull Object obj) {
            if (containsKey(obj)) {
                return this.multimap.get(obj);
            }
            return null;
        }

        @CheckForNull
        public Collection<V> remove(@CheckForNull Object obj) {
            if (containsKey(obj)) {
                return this.multimap.removeAll(obj);
            }
            return null;
        }
    }

    public static class CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {
        @GwtIncompatible
        @J2ktIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends List<V>> factory;

        public CustomListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Object readObject = objectInputStream.readObject();
            Objects.requireNonNull(readObject);
            this.factory = (Supplier) readObject;
            Object readObject2 = objectInputStream.readObject();
            Objects.requireNonNull(readObject2);
            setMap((Map) readObject2);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        public List<V> createCollection() {
            return (List) this.factory.get();
        }
    }

    public static class CustomMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
        @GwtIncompatible
        @J2ktIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends Collection<V>> factory;

        public CustomMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Object readObject = objectInputStream.readObject();
            Objects.requireNonNull(readObject);
            this.factory = (Supplier) readObject;
            Object readObject2 = objectInputStream.readObject();
            Objects.requireNonNull(readObject2);
            setMap((Map) readObject2);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        public Collection<V> createCollection() {
            return (Collection) this.factory.get();
        }

        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            return collection instanceof NavigableSet ? Sets.unmodifiableNavigableSet((NavigableSet) collection) : collection instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet) collection) : collection instanceof Set ? Collections.unmodifiableSet((Set) collection) : collection instanceof List ? Collections.unmodifiableList((List) collection) : Collections.unmodifiableCollection(collection);
        }

        public Collection<V> wrapCollection(@ParametricNullness K k2, Collection<V> collection) {
            return collection instanceof List ? wrapList(k2, (List) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : collection instanceof NavigableSet ? new AbstractMapBasedMultimap.WrappedNavigableSet(k2, (NavigableSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : collection instanceof SortedSet ? new AbstractMapBasedMultimap.WrappedSortedSet(k2, (SortedSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : collection instanceof Set ? new AbstractMapBasedMultimap.WrappedSet(k2, (Set) collection) : new AbstractMapBasedMultimap.WrappedCollection(k2, collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
        }
    }

    public static class CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {
        @GwtIncompatible
        @J2ktIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends Set<V>> factory;

        public CustomSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Object readObject = objectInputStream.readObject();
            Objects.requireNonNull(readObject);
            this.factory = (Supplier) readObject;
            Object readObject2 = objectInputStream.readObject();
            Objects.requireNonNull(readObject2);
            setMap((Map) readObject2);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            return collection instanceof NavigableSet ? Sets.unmodifiableNavigableSet((NavigableSet) collection) : collection instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet) collection) : Collections.unmodifiableSet((Set) collection);
        }

        public Collection<V> wrapCollection(@ParametricNullness K k2, Collection<V> collection) {
            return collection instanceof NavigableSet ? new AbstractMapBasedMultimap.WrappedNavigableSet(k2, (NavigableSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : collection instanceof SortedSet ? new AbstractMapBasedMultimap.WrappedSortedSet(k2, (SortedSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : new AbstractMapBasedMultimap.WrappedSet(k2, (Set) collection);
        }

        public Set<V> createCollection() {
            return (Set) this.factory.get();
        }
    }

    public static abstract class Entries<K, V> extends AbstractCollection<Map.Entry<K, V>> {
        public void clear() {
            multimap().clear();
        }

        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return multimap().containsEntry(entry.getKey(), entry.getValue());
        }

        public abstract Multimap<K, V> multimap();

        public boolean remove(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return multimap().remove(entry.getKey(), entry.getValue());
        }

        public int size() {
            return multimap().size();
        }
    }

    public static class Keys<K, V> extends AbstractMultiset<K> {
        @Weak
        final Multimap<K, V> multimap;

        public Keys(Multimap<K, V> multimap2) {
            this.multimap = multimap2;
        }

        public void clear() {
            this.multimap.clear();
        }

        public boolean contains(@CheckForNull Object obj) {
            return this.multimap.containsKey(obj);
        }

        public int count(@CheckForNull Object obj) {
            Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            return collection.size();
        }

        public int distinctElements() {
            return this.multimap.asMap().size();
        }

        public Iterator<K> elementIterator() {
            throw new AssertionError("should never be called");
        }

        public Set<K> elementSet() {
            return this.multimap.keySet();
        }

        public Iterator<Multiset.Entry<K>> entryIterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Multiset.Entry<K>>(this, this.multimap.asMap().entrySet().iterator()) {
                public Multiset.Entry<K> transform(final Map.Entry<K, Collection<V>> entry) {
                    return new Multisets.AbstractEntry<K>(this) {
                        public int getCount() {
                            return ((Collection) entry.getValue()).size();
                        }

                        @ParametricNullness
                        public K getElement() {
                            return entry.getKey();
                        }
                    };
                }
            };
        }

        public Iterator<K> iterator() {
            return Maps.keyIterator(this.multimap.entries().iterator());
        }

        public int remove(@CheckForNull Object obj, int i3) {
            CollectPreconditions.checkNonnegative(i3, "occurrences");
            if (i3 == 0) {
                return count(obj);
            }
            Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            int size = collection.size();
            if (i3 >= size) {
                collection.clear();
            } else {
                Iterator it = collection.iterator();
                for (int i4 = 0; i4 < i3; i4++) {
                    it.next();
                    it.remove();
                }
            }
            return size;
        }

        public int size() {
            return this.multimap.size();
        }
    }

    public static class MapMultimap<K, V> extends AbstractMultimap<K, V> implements SetMultimap<K, V>, Serializable {
        private static final long serialVersionUID = 7845222491160860175L;
        final Map<K, V> map;

        public MapMultimap(Map<K, V> map2) {
            this.map = (Map) Preconditions.checkNotNull(map2);
        }

        public void clear() {
            this.map.clear();
        }

        public boolean containsEntry(@CheckForNull Object obj, @CheckForNull Object obj2) {
            return this.map.entrySet().contains(Maps.immutableEntry(obj, obj2));
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return this.map.containsKey(obj);
        }

        public boolean containsValue(@CheckForNull Object obj) {
            return this.map.containsValue(obj);
        }

        public Map<K, Collection<V>> createAsMap() {
            return new AsMap(this);
        }

        public Collection<Map.Entry<K, V>> createEntries() {
            throw new AssertionError("unreachable");
        }

        public Set<K> createKeySet() {
            return this.map.keySet();
        }

        public Multiset<K> createKeys() {
            return new Keys(this);
        }

        public Collection<V> createValues() {
            return this.map.values();
        }

        public Iterator<Map.Entry<K, V>> entryIterator() {
            return this.map.entrySet().iterator();
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public boolean put(@ParametricNullness K k2, @ParametricNullness V v2) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
            return this.map.entrySet().remove(Maps.immutableEntry(obj, obj2));
        }

        public int size() {
            return this.map.size();
        }

        public Set<Map.Entry<K, V>> entries() {
            return this.map.entrySet();
        }

        public Set<V> get(@ParametricNullness final K k2) {
            return new Sets.ImprovedAbstractSet<V>() {
                public Iterator<V> iterator() {
                    return new Iterator<V>() {

                        /* renamed from: i  reason: collision with root package name */
                        int f6874i;

                        public boolean hasNext() {
                            if (this.f6874i == 0) {
                                AnonymousClass1 r12 = AnonymousClass1.this;
                                if (MapMultimap.this.map.containsKey(k2)) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        @ParametricNullness
                        public V next() {
                            if (hasNext()) {
                                this.f6874i++;
                                AnonymousClass1 r12 = AnonymousClass1.this;
                                return NullnessCasts.uncheckedCastNullableTToT(MapMultimap.this.map.get(k2));
                            }
                            throw new NoSuchElementException();
                        }

                        public void remove() {
                            boolean z2 = true;
                            if (this.f6874i != 1) {
                                z2 = false;
                            }
                            CollectPreconditions.checkRemove(z2);
                            this.f6874i = -1;
                            AnonymousClass1 r2 = AnonymousClass1.this;
                            MapMultimap.this.map.remove(k2);
                        }
                    };
                }

                public int size() {
                    return MapMultimap.this.map.containsKey(k2) ? 1 : 0;
                }
            };
        }

        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }

        public Set<V> removeAll(@CheckForNull Object obj) {
            HashSet hashSet = new HashSet(2);
            if (!this.map.containsKey(obj)) {
                return hashSet;
            }
            hashSet.add(this.map.remove(obj));
            return hashSet;
        }

        public Set<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }
    }

    public static final class TransformedEntriesListMultimap<K, V1, V2> extends TransformedEntriesMultimap<K, V1, V2> implements ListMultimap<K, V2> {
        public TransformedEntriesListMultimap(ListMultimap<K, V1> listMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(listMultimap, entryTransformer);
        }

        public List<V2> get(@ParametricNullness K k2) {
            return transform((Object) k2, (Collection) this.fromMultimap.get(k2));
        }

        public List<V2> removeAll(@CheckForNull Object obj) {
            return transform(obj, (Collection) this.fromMultimap.removeAll(obj));
        }

        public List<V2> replaceValues(@ParametricNullness K k2, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        public List<V2> transform(@ParametricNullness K k2, Collection<V1> collection) {
            return Lists.transform((List) collection, Maps.asValueToValueFunction(this.transformer, k2));
        }
    }

    public static class TransformedEntriesMultimap<K, V1, V2> extends AbstractMultimap<K, V2> {
        final Multimap<K, V1> fromMultimap;
        final Maps.EntryTransformer<? super K, ? super V1, V2> transformer;

        public TransformedEntriesMultimap(Multimap<K, V1> multimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            this.fromMultimap = (Multimap) Preconditions.checkNotNull(multimap);
            this.transformer = (Maps.EntryTransformer) Preconditions.checkNotNull(entryTransformer);
        }

        public void clear() {
            this.fromMultimap.clear();
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return this.fromMultimap.containsKey(obj);
        }

        public Map<K, Collection<V2>> createAsMap() {
            return Maps.transformEntries(this.fromMultimap.asMap(), new d(this));
        }

        public Collection<Map.Entry<K, V2>> createEntries() {
            return new AbstractMultimap.Entries();
        }

        public Set<K> createKeySet() {
            return this.fromMultimap.keySet();
        }

        public Multiset<K> createKeys() {
            return this.fromMultimap.keys();
        }

        public Collection<V2> createValues() {
            return Collections2.transform(this.fromMultimap.entries(), Maps.asEntryToValueFunction(this.transformer));
        }

        public Iterator<Map.Entry<K, V2>> entryIterator() {
            return Iterators.transform(this.fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
        }

        public Collection<V2> get(@ParametricNullness K k2) {
            return lambda$createAsMap$0(k2, this.fromMultimap.get(k2));
        }

        public boolean isEmpty() {
            return this.fromMultimap.isEmpty();
        }

        public boolean put(@ParametricNullness K k2, @ParametricNullness V2 v2) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(@ParametricNullness K k2, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
            return get(obj).remove(obj2);
        }

        public Collection<V2> removeAll(@CheckForNull Object obj) {
            return lambda$createAsMap$0(obj, this.fromMultimap.removeAll(obj));
        }

        public Collection<V2> replaceValues(@ParametricNullness K k2, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.fromMultimap.size();
        }

        /* renamed from: transform */
        public Collection<V2> lambda$createAsMap$0(@ParametricNullness K k2, Collection<V1> collection) {
            Function<? super V1, V2> asValueToValueFunction = Maps.asValueToValueFunction(this.transformer, k2);
            return collection instanceof List ? Lists.transform((List) collection, asValueToValueFunction) : Collections2.transform(collection, asValueToValueFunction);
        }

        public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
            throw new UnsupportedOperationException();
        }
    }

    public static class UnmodifiableListMultimap<K, V> extends UnmodifiableMultimap<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        public UnmodifiableListMultimap(ListMultimap<K, V> listMultimap) {
            super(listMultimap);
        }

        public List<V> get(@ParametricNullness K k2) {
            return Collections.unmodifiableList(delegate().get((Object) k2));
        }

        public List<V> removeAll(@CheckForNull Object obj) {
            throw new UnsupportedOperationException();
        }

        public List<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public ListMultimap<K, V> delegate() {
            return (ListMultimap) super.delegate();
        }
    }

    public static class UnmodifiableMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final Multimap<K, V> delegate;
        @CheckForNull
        @LazyInit
        transient Collection<Map.Entry<K, V>> entries;
        @CheckForNull
        @LazyInit
        transient Set<K> keySet;
        @CheckForNull
        @LazyInit
        transient Multiset<K> keys;
        @CheckForNull
        @LazyInit
        transient Map<K, Collection<V>> map;
        @CheckForNull
        @LazyInit
        transient Collection<V> values;

        public UnmodifiableMultimap(Multimap<K, V> multimap) {
            this.delegate = (Multimap) Preconditions.checkNotNull(multimap);
        }

        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map2 = this.map;
            if (map2 != null) {
                return map2;
            }
            Map<K, Collection<V>> unmodifiableMap = Collections.unmodifiableMap(Maps.transformValues(this.delegate.asMap(), new e(0)));
            this.map = unmodifiableMap;
            return unmodifiableMap;
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection = this.entries;
            if (collection != null) {
                return collection;
            }
            Collection<Map.Entry<K, V>> access$000 = Multimaps.unmodifiableEntries(this.delegate.entries());
            this.entries = access$000;
            return access$000;
        }

        public Collection<V> get(@ParametricNullness K k2) {
            return Multimaps.unmodifiableValueCollection(this.delegate.get(k2));
        }

        public Set<K> keySet() {
            Set<K> set = this.keySet;
            if (set != null) {
                return set;
            }
            Set<K> unmodifiableSet = Collections.unmodifiableSet(this.delegate.keySet());
            this.keySet = unmodifiableSet;
            return unmodifiableSet;
        }

        public Multiset<K> keys() {
            Multiset<K> multiset = this.keys;
            if (multiset != null) {
                return multiset;
            }
            Multiset<K> unmodifiableMultiset = Multisets.unmodifiableMultiset(this.delegate.keys());
            this.keys = unmodifiableMultiset;
            return unmodifiableMultiset;
        }

        public boolean put(@ParametricNullness K k2, @ParametricNullness V v2) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2) {
            throw new UnsupportedOperationException();
        }

        public Collection<V> removeAll(@CheckForNull Object obj) {
            throw new UnsupportedOperationException();
        }

        public Collection<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public Collection<V> values() {
            Collection<V> collection = this.values;
            if (collection != null) {
                return collection;
            }
            Collection<V> unmodifiableCollection = Collections.unmodifiableCollection(this.delegate.values());
            this.values = unmodifiableCollection;
            return unmodifiableCollection;
        }

        public Multimap<K, V> delegate() {
            return this.delegate;
        }

        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }
    }

    public static class UnmodifiableSetMultimap<K, V> extends UnmodifiableMultimap<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        public UnmodifiableSetMultimap(SetMultimap<K, V> setMultimap) {
            super(setMultimap);
        }

        public Set<Map.Entry<K, V>> entries() {
            return Maps.unmodifiableEntrySet(delegate().entries());
        }

        public Set<V> get(@ParametricNullness K k2) {
            return Collections.unmodifiableSet(delegate().get((Object) k2));
        }

        public Set<V> removeAll(@CheckForNull Object obj) {
            throw new UnsupportedOperationException();
        }

        public Set<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public SetMultimap<K, V> delegate() {
            return (SetMultimap) super.delegate();
        }
    }

    private Multimaps() {
    }

    public static <K, V> Map<K, List<V>> asMap(ListMultimap<K, V> listMultimap) {
        return listMultimap.asMap();
    }

    public static boolean equalsImpl(Multimap<?, ?> multimap, @CheckForNull Object obj) {
        if (obj == multimap) {
            return true;
        }
        if (obj instanceof Multimap) {
            return multimap.asMap().equals(((Multimap) obj).asMap());
        }
        return false;
    }

    public static <K, V> Multimap<K, V> filterEntries(Multimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        if (multimap instanceof SetMultimap) {
            return filterEntries((SetMultimap) multimap, predicate);
        }
        if (multimap instanceof FilteredMultimap) {
            return filterFiltered((FilteredMultimap) multimap, predicate);
        }
        return new FilteredEntryMultimap((Multimap) Preconditions.checkNotNull(multimap), predicate);
    }

    private static <K, V> Multimap<K, V> filterFiltered(FilteredMultimap<K, V> filteredMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryMultimap(filteredMultimap.unfiltered(), Predicates.and(filteredMultimap.entryPredicate(), predicate));
    }

    public static <K, V> Multimap<K, V> filterKeys(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        if (multimap instanceof SetMultimap) {
            return filterKeys((SetMultimap) multimap, predicate);
        }
        if (multimap instanceof ListMultimap) {
            return filterKeys((ListMultimap) multimap, predicate);
        }
        if (multimap instanceof FilteredKeyMultimap) {
            FilteredKeyMultimap filteredKeyMultimap = (FilteredKeyMultimap) multimap;
            return new FilteredKeyMultimap(filteredKeyMultimap.unfiltered, Predicates.and(filteredKeyMultimap.keyPredicate, predicate));
        } else if (multimap instanceof FilteredMultimap) {
            return filterFiltered((FilteredMultimap) multimap, Maps.keyPredicateOnEntries(predicate));
        } else {
            return new FilteredKeyMultimap(multimap, predicate);
        }
    }

    public static <K, V> Multimap<K, V> filterValues(Multimap<K, V> multimap, Predicate<? super V> predicate) {
        return filterEntries(multimap, Maps.valuePredicateOnEntries(predicate));
    }

    public static <K, V> SetMultimap<K, V> forMap(Map<K, V> map) {
        return new MapMultimap(map);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterable<V> iterable, Function<? super V, K> function) {
        return index(iterable.iterator(), function);
    }

    @CanIgnoreReturnValue
    public static <K, V, M extends Multimap<K, V>> M invertFrom(Multimap<? extends V, ? extends K> multimap, M m3) {
        Preconditions.checkNotNull(m3);
        for (Map.Entry next : multimap.entries()) {
            m3.put(next.getValue(), next.getKey());
        }
        return m3;
    }

    public static <K, V> ListMultimap<K, V> newListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> supplier) {
        return new CustomListMultimap(map, supplier);
    }

    public static <K, V> Multimap<K, V> newMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> supplier) {
        return new CustomMultimap(map, supplier);
    }

    public static <K, V> SetMultimap<K, V> newSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> supplier) {
        return new CustomSetMultimap(map, supplier);
    }

    public static <K, V> SortedSetMultimap<K, V> newSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> supplier) {
        return new CustomSortedSetMultimap(map, supplier);
    }

    public static <K, V> ListMultimap<K, V> synchronizedListMultimap(ListMultimap<K, V> listMultimap) {
        return Synchronized.listMultimap(listMultimap, (Object) null);
    }

    public static <K, V> Multimap<K, V> synchronizedMultimap(Multimap<K, V> multimap) {
        return Synchronized.multimap(multimap, (Object) null);
    }

    public static <K, V> SetMultimap<K, V> synchronizedSetMultimap(SetMultimap<K, V> setMultimap) {
        return Synchronized.setMultimap(setMultimap, (Object) null);
    }

    public static <K, V> SortedSetMultimap<K, V> synchronizedSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return Synchronized.sortedSetMultimap(sortedSetMultimap, (Object) null);
    }

    public static <K, V1, V2> Multimap<K, V2> transformEntries(Multimap<K, V1> multimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesMultimap(multimap, entryTransformer);
    }

    public static <K, V1, V2> Multimap<K, V2> transformValues(Multimap<K, V1> multimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return transformEntries(multimap, Maps.asEntryTransformer(function));
    }

    /* access modifiers changed from: private */
    public static <K, V> Collection<Map.Entry<K, V>> unmodifiableEntries(Collection<Map.Entry<K, V>> collection) {
        return collection instanceof Set ? Maps.unmodifiableEntrySet((Set) collection) : new Maps.UnmodifiableEntries(Collections.unmodifiableCollection(collection));
    }

    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ListMultimap<K, V> listMultimap) {
        return ((listMultimap instanceof UnmodifiableListMultimap) || (listMultimap instanceof ImmutableListMultimap)) ? listMultimap : new UnmodifiableListMultimap(listMultimap);
    }

    public static <K, V> Multimap<K, V> unmodifiableMultimap(Multimap<K, V> multimap) {
        return ((multimap instanceof UnmodifiableMultimap) || (multimap instanceof ImmutableMultimap)) ? multimap : new UnmodifiableMultimap(multimap);
    }

    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(SetMultimap<K, V> setMultimap) {
        return ((setMultimap instanceof UnmodifiableSetMultimap) || (setMultimap instanceof ImmutableSetMultimap)) ? setMultimap : new UnmodifiableSetMultimap(setMultimap);
    }

    public static <K, V> SortedSetMultimap<K, V> unmodifiableSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return sortedSetMultimap instanceof UnmodifiableSortedSetMultimap ? sortedSetMultimap : new UnmodifiableSortedSetMultimap(sortedSetMultimap);
    }

    /* access modifiers changed from: private */
    public static <V> Collection<V> unmodifiableValueCollection(Collection<V> collection) {
        return collection instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet) collection) : collection instanceof Set ? Collections.unmodifiableSet((Set) collection) : collection instanceof List ? Collections.unmodifiableList((List) collection) : Collections.unmodifiableCollection(collection);
    }

    public static class CustomSortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
        @GwtIncompatible
        @J2ktIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends SortedSet<V>> factory;
        @CheckForNull
        transient Comparator<? super V> valueComparator;

        public CustomSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
            this.valueComparator = ((SortedSet) supplier.get()).comparator();
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Object readObject = objectInputStream.readObject();
            Objects.requireNonNull(readObject);
            Supplier<? extends SortedSet<V>> supplier = (Supplier) readObject;
            this.factory = supplier;
            this.valueComparator = ((SortedSet) supplier.get()).comparator();
            Object readObject2 = objectInputStream.readObject();
            Objects.requireNonNull(readObject2);
            setMap((Map) readObject2);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        @CheckForNull
        public Comparator<? super V> valueComparator() {
            return this.valueComparator;
        }

        public SortedSet<V> createCollection() {
            return (SortedSet) this.factory.get();
        }
    }

    public static class UnmodifiableSortedSetMultimap<K, V> extends UnmodifiableSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        public UnmodifiableSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
            super(sortedSetMultimap);
        }

        @CheckForNull
        public Comparator<? super V> valueComparator() {
            return delegate().valueComparator();
        }

        public SortedSet<V> get(@ParametricNullness K k2) {
            return Collections.unmodifiableSortedSet(delegate().get((Object) k2));
        }

        public SortedSet<V> removeAll(@CheckForNull Object obj) {
            throw new UnsupportedOperationException();
        }

        public SortedSet<V> replaceValues(@ParametricNullness K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) super.delegate();
        }
    }

    public static <K, V> Map<K, Set<V>> asMap(SetMultimap<K, V> setMultimap) {
        return setMultimap.asMap();
    }

    public static <K, V> SetMultimap<K, V> filterValues(SetMultimap<K, V> setMultimap, Predicate<? super V> predicate) {
        return filterEntries(setMultimap, Maps.valuePredicateOnEntries(predicate));
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterator<V> it, Function<? super V, K> function) {
        Preconditions.checkNotNull(function);
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        while (it.hasNext()) {
            V next = it.next();
            Preconditions.checkNotNull(next, it);
            builder.put(function.apply(next), next);
        }
        return builder.build();
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformEntries(ListMultimap<K, V1> listMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesListMultimap(listMultimap, entryTransformer);
    }

    public static <K, V> Map<K, SortedSet<V>> asMap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return sortedSetMultimap.asMap();
    }

    private static <K, V> SetMultimap<K, V> filterFiltered(FilteredSetMultimap<K, V> filteredSetMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntrySetMultimap(filteredSetMultimap.unfiltered(), Predicates.and(filteredSetMultimap.entryPredicate(), predicate));
    }

    @Deprecated
    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ImmutableListMultimap<K, V> immutableListMultimap) {
        return (ListMultimap) Preconditions.checkNotNull(immutableListMultimap);
    }

    @Deprecated
    public static <K, V> Multimap<K, V> unmodifiableMultimap(ImmutableMultimap<K, V> immutableMultimap) {
        return (Multimap) Preconditions.checkNotNull(immutableMultimap);
    }

    @Deprecated
    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(ImmutableSetMultimap<K, V> immutableSetMultimap) {
        return (SetMultimap) Preconditions.checkNotNull(immutableSetMultimap);
    }

    public static <K, V> Map<K, Collection<V>> asMap(Multimap<K, V> multimap) {
        return multimap.asMap();
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformValues(ListMultimap<K, V1> listMultimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return transformEntries(listMultimap, Maps.asEntryTransformer(function));
    }

    public static <K, V> SetMultimap<K, V> filterEntries(SetMultimap<K, V> setMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        if (setMultimap instanceof FilteredSetMultimap) {
            return filterFiltered((FilteredSetMultimap) setMultimap, predicate);
        }
        return new FilteredEntrySetMultimap((SetMultimap) Preconditions.checkNotNull(setMultimap), predicate);
    }

    public static <K, V> SetMultimap<K, V> filterKeys(SetMultimap<K, V> setMultimap, Predicate<? super K> predicate) {
        if (setMultimap instanceof FilteredKeySetMultimap) {
            FilteredKeySetMultimap filteredKeySetMultimap = (FilteredKeySetMultimap) setMultimap;
            return new FilteredKeySetMultimap(filteredKeySetMultimap.unfiltered(), Predicates.and(filteredKeySetMultimap.keyPredicate, predicate));
        } else if (setMultimap instanceof FilteredSetMultimap) {
            return filterFiltered((FilteredSetMultimap) setMultimap, Maps.keyPredicateOnEntries(predicate));
        } else {
            return new FilteredKeySetMultimap(setMultimap, predicate);
        }
    }

    public static <K, V> ListMultimap<K, V> filterKeys(ListMultimap<K, V> listMultimap, Predicate<? super K> predicate) {
        if (!(listMultimap instanceof FilteredKeyListMultimap)) {
            return new FilteredKeyListMultimap(listMultimap, predicate);
        }
        FilteredKeyListMultimap filteredKeyListMultimap = (FilteredKeyListMultimap) listMultimap;
        return new FilteredKeyListMultimap(filteredKeyListMultimap.unfiltered(), Predicates.and(filteredKeyListMultimap.keyPredicate, predicate));
    }
}
