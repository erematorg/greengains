package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
abstract class AbstractBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
    @GwtIncompatible
    @J2ktIncompatible
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public transient Map<K, V> delegate;
    @CheckForNull
    @LazyInit
    private transient Set<Map.Entry<K, V>> entrySet;
    @RetainedWith
    transient AbstractBiMap<V, K> inverse;
    @CheckForNull
    @LazyInit
    private transient Set<K> keySet;
    @CheckForNull
    @LazyInit
    private transient Set<V> valueSet;

    public class BiMapEntry extends ForwardingMapEntry<K, V> {
        private final Map.Entry<K, V> delegate;

        public BiMapEntry(Map.Entry<K, V> entry) {
            this.delegate = entry;
        }

        public V setValue(V v2) {
            AbstractBiMap.this.checkValue(v2);
            Preconditions.checkState(AbstractBiMap.this.entrySet().contains(this), "entry no longer in map");
            if (Objects.equal(v2, getValue())) {
                return v2;
            }
            Preconditions.checkArgument(!AbstractBiMap.this.containsValue(v2), "value already present: %s", (Object) v2);
            V value = this.delegate.setValue(v2);
            Preconditions.checkState(Objects.equal(v2, AbstractBiMap.this.get(getKey())), "entry no longer in map");
            AbstractBiMap.this.updateInverseMap(getKey(), true, value, v2);
            return value;
        }

        public Map.Entry<K, V> delegate() {
            return this.delegate;
        }
    }

    public class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
        final Set<Map.Entry<K, V>> esDelegate;

        private EntrySet() {
            this.esDelegate = AbstractBiMap.this.delegate.entrySet();
        }

        public void clear() {
            AbstractBiMap.this.clear();
        }

        public boolean contains(@CheckForNull Object obj) {
            return Maps.containsEntryImpl(delegate(), obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return standardContainsAll(collection);
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractBiMap.this.entrySetIterator();
        }

        public boolean remove(@CheckForNull Object obj) {
            if (!this.esDelegate.contains(obj) || !(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            AbstractBiMap.this.inverse.delegate.remove(entry.getValue());
            this.esDelegate.remove(entry);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return standardRemoveAll(collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return standardRetainAll(collection);
        }

        public Object[] toArray() {
            return standardToArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return standardToArray(tArr);
        }

        public Set<Map.Entry<K, V>> delegate() {
            return this.esDelegate;
        }
    }

    public static class Inverse<K, V> extends AbstractBiMap<K, V> {
        @GwtIncompatible
        @J2ktIncompatible
        private static final long serialVersionUID = 0;

        public Inverse(Map<K, V> map, AbstractBiMap<V, K> abstractBiMap) {
            super(map, abstractBiMap);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Object readObject = objectInputStream.readObject();
            java.util.Objects.requireNonNull(readObject);
            setInverse((AbstractBiMap) readObject);
        }

        @GwtIncompatible
        @J2ktIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(inverse());
        }

        @ParametricNullness
        public K checkKey(@ParametricNullness K k2) {
            return this.inverse.checkValue(k2);
        }

        @ParametricNullness
        public V checkValue(@ParametricNullness V v2) {
            return this.inverse.checkKey(v2);
        }

        public /* bridge */ /* synthetic */ Object delegate() {
            return AbstractBiMap.super.delegate();
        }

        @GwtIncompatible
        @J2ktIncompatible
        public Object readResolve() {
            return inverse().inverse();
        }

        public /* bridge */ /* synthetic */ Collection values() {
            return AbstractBiMap.super.values();
        }
    }

    public class ValueSet extends ForwardingSet<V> {
        final Set<V> valuesDelegate;

        private ValueSet() {
            this.valuesDelegate = AbstractBiMap.this.inverse.keySet();
        }

        public Iterator<V> iterator() {
            return Maps.valueIterator(AbstractBiMap.this.entrySet().iterator());
        }

        public Object[] toArray() {
            return standardToArray();
        }

        public String toString() {
            return standardToString();
        }

        public <T> T[] toArray(T[] tArr) {
            return standardToArray(tArr);
        }

        public Set<V> delegate() {
            return this.valuesDelegate;
        }
    }

    @CheckForNull
    private V putInBothMaps(@ParametricNullness K k2, @ParametricNullness V v2, boolean z2) {
        checkKey(k2);
        checkValue(v2);
        boolean containsKey = containsKey(k2);
        if (containsKey && Objects.equal(v2, get(k2))) {
            return v2;
        }
        if (z2) {
            inverse().remove(v2);
        } else {
            Preconditions.checkArgument(!containsValue(v2), "value already present: %s", (Object) v2);
        }
        V put = this.delegate.put(k2, v2);
        updateInverseMap(k2, containsKey, put, v2);
        return put;
    }

    /* access modifiers changed from: private */
    @ParametricNullness
    @CanIgnoreReturnValue
    public V removeFromBothMaps(@CheckForNull Object obj) {
        V uncheckedCastNullableTToT = NullnessCasts.uncheckedCastNullableTToT(this.delegate.remove(obj));
        removeFromInverseMap(uncheckedCastNullableTToT);
        return uncheckedCastNullableTToT;
    }

    /* access modifiers changed from: private */
    public void removeFromInverseMap(@ParametricNullness V v2) {
        this.inverse.delegate.remove(v2);
    }

    /* access modifiers changed from: private */
    public void updateInverseMap(@ParametricNullness K k2, boolean z2, @CheckForNull V v2, @ParametricNullness V v3) {
        if (z2) {
            removeFromInverseMap(NullnessCasts.uncheckedCastNullableTToT(v2));
        }
        this.inverse.delegate.put(v3, k2);
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public K checkKey(@ParametricNullness K k2) {
        return k2;
    }

    @ParametricNullness
    @CanIgnoreReturnValue
    public V checkValue(@ParametricNullness V v2) {
        return v2;
    }

    public void clear() {
        this.delegate.clear();
        this.inverse.delegate.clear();
    }

    public boolean containsValue(@CheckForNull Object obj) {
        return this.inverse.containsKey(obj);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        final Iterator<Map.Entry<K, V>> it = this.delegate.entrySet().iterator();
        return new Iterator<Map.Entry<K, V>>() {
            @CheckForNull
            Map.Entry<K, V> entry;

            public boolean hasNext() {
                return it.hasNext();
            }

            public void remove() {
                Map.Entry<K, V> entry2 = this.entry;
                if (entry2 != null) {
                    V value = entry2.getValue();
                    it.remove();
                    AbstractBiMap.this.removeFromInverseMap(value);
                    this.entry = null;
                    return;
                }
                throw new IllegalStateException("no calls to next() since the last call to remove()");
            }

            public Map.Entry<K, V> next() {
                Map.Entry<K, V> entry2 = (Map.Entry) it.next();
                this.entry = entry2;
                return new BiMapEntry(entry2);
            }
        };
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V forcePut(@ParametricNullness K k2, @ParametricNullness V v2) {
        return putInBothMaps(k2, v2, true);
    }

    public BiMap<V, K> inverse() {
        return this.inverse;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    public AbstractBiMap<V, K> makeInverse(Map<V, K> map) {
        return new Inverse(map, this);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V put(@ParametricNullness K k2, @ParametricNullness V v2) {
        return putInBothMaps(k2, v2, false);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V remove(@CheckForNull Object obj) {
        if (containsKey(obj)) {
            return removeFromBothMaps(obj);
        }
        return null;
    }

    public void setDelegates(Map<K, V> map, Map<V, K> map2) {
        boolean z2 = false;
        Preconditions.checkState(this.delegate == null);
        Preconditions.checkState(this.inverse == null);
        Preconditions.checkArgument(map.isEmpty());
        Preconditions.checkArgument(map2.isEmpty());
        if (map != map2) {
            z2 = true;
        }
        Preconditions.checkArgument(z2);
        this.delegate = map;
        this.inverse = makeInverse(map2);
    }

    public void setInverse(AbstractBiMap<V, K> abstractBiMap) {
        this.inverse = abstractBiMap;
    }

    public class KeySet extends ForwardingSet<K> {
        private KeySet() {
        }

        public void clear() {
            AbstractBiMap.this.clear();
        }

        public Iterator<K> iterator() {
            return Maps.keyIterator(AbstractBiMap.this.entrySet().iterator());
        }

        public boolean remove(@CheckForNull Object obj) {
            if (!contains(obj)) {
                return false;
            }
            Object unused = AbstractBiMap.this.removeFromBothMaps(obj);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return standardRemoveAll(collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return standardRetainAll(collection);
        }

        public Set<K> delegate() {
            return AbstractBiMap.this.delegate.keySet();
        }
    }

    public AbstractBiMap(Map<K, V> map, Map<V, K> map2) {
        setDelegates(map, map2);
    }

    public Map<K, V> delegate() {
        return this.delegate;
    }

    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set != null) {
            return set;
        }
        ValueSet valueSet2 = new ValueSet();
        this.valueSet = valueSet2;
        return valueSet2;
    }

    private AbstractBiMap(Map<K, V> map, AbstractBiMap<V, K> abstractBiMap) {
        this.delegate = map;
        this.inverse = abstractBiMap;
    }
}
