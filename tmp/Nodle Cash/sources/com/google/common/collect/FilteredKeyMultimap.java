package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
class FilteredKeyMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Predicate<? super K> keyPredicate;
    final Multimap<K, V> unfiltered;

    public static class AddRejectingList<K, V> extends ForwardingList<V> {
        @ParametricNullness
        final K key;

        public AddRejectingList(@ParametricNullness K k2) {
            this.key = k2;
        }

        public boolean add(@ParametricNullness V v2) {
            add(0, v2);
            return true;
        }

        public boolean addAll(Collection<? extends V> collection) {
            addAll(0, collection);
            return true;
        }

        public void add(int i3, @ParametricNullness V v2) {
            Preconditions.checkPositionIndex(i3, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
        }

        @CanIgnoreReturnValue
        public boolean addAll(int i3, Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            Preconditions.checkPositionIndex(i3, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
        }

        public List<V> delegate() {
            return Collections.emptyList();
        }
    }

    public class Entries extends ForwardingCollection<Map.Entry<K, V>> {
        public Entries() {
        }

        public boolean remove(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!FilteredKeyMultimap.this.unfiltered.containsKey(entry.getKey()) || !FilteredKeyMultimap.this.keyPredicate.apply(entry.getKey())) {
                return false;
            }
            return FilteredKeyMultimap.this.unfiltered.remove(entry.getKey(), entry.getValue());
        }

        public Collection<Map.Entry<K, V>> delegate() {
            return Collections2.filter(FilteredKeyMultimap.this.unfiltered.entries(), FilteredKeyMultimap.this.entryPredicate());
        }
    }

    public FilteredKeyMultimap(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        this.unfiltered = (Multimap) Preconditions.checkNotNull(multimap);
        this.keyPredicate = (Predicate) Preconditions.checkNotNull(predicate);
    }

    public void clear() {
        keySet().clear();
    }

    public boolean containsKey(@CheckForNull Object obj) {
        if (this.unfiltered.containsKey(obj)) {
            return this.keyPredicate.apply(obj);
        }
        return false;
    }

    public Map<K, Collection<V>> createAsMap() {
        return Maps.filterKeys(this.unfiltered.asMap(), this.keyPredicate);
    }

    public Collection<Map.Entry<K, V>> createEntries() {
        return new Entries();
    }

    public Set<K> createKeySet() {
        return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
    }

    public Multiset<K> createKeys() {
        return Multisets.filter(this.unfiltered.keys(), this.keyPredicate);
    }

    public Collection<V> createValues() {
        return new FilteredMultimapValues(this);
    }

    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return Maps.keyPredicateOnEntries(this.keyPredicate);
    }

    public Collection<V> get(@ParametricNullness K k2) {
        return this.keyPredicate.apply(k2) ? this.unfiltered.get(k2) : this.unfiltered instanceof SetMultimap ? new AddRejectingSet(k2) : new AddRejectingList(k2);
    }

    public Collection<V> removeAll(@CheckForNull Object obj) {
        return containsKey(obj) ? this.unfiltered.removeAll(obj) : unmodifiableEmptyCollection();
    }

    public int size() {
        int i3 = 0;
        for (Collection size : asMap().values()) {
            i3 += size.size();
        }
        return i3;
    }

    public Multimap<K, V> unfiltered() {
        return this.unfiltered;
    }

    public Collection<V> unmodifiableEmptyCollection() {
        return this.unfiltered instanceof SetMultimap ? Collections.emptySet() : Collections.emptyList();
    }

    public static class AddRejectingSet<K, V> extends ForwardingSet<V> {
        @ParametricNullness
        final K key;

        public AddRejectingSet(@ParametricNullness K k2) {
            this.key = k2;
        }

        public boolean add(@ParametricNullness V v2) {
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
        }

        public boolean addAll(Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.key);
        }

        public Set<V> delegate() {
            return Collections.emptySet();
        }
    }
}
