package com.google.common.collect;

import androidx.camera.view.f;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final int ABSENT = -1;
    private static final int ENDPOINT = -2;
    @LazyInit
    private transient Set<Map.Entry<K, V>> entrySet;
    /* access modifiers changed from: private */
    public transient int firstInInsertionOrder;
    private transient int[] hashTableKToV;
    private transient int[] hashTableVToK;
    /* access modifiers changed from: private */
    @RetainedWith
    @CheckForNull
    @LazyInit
    public transient BiMap<V, K> inverse;
    @LazyInit
    private transient Set<K> keySet;
    transient K[] keys;
    private transient int lastInInsertionOrder;
    transient int modCount;
    private transient int[] nextInBucketKToV;
    private transient int[] nextInBucketVToK;
    /* access modifiers changed from: private */
    public transient int[] nextInInsertionOrder;
    private transient int[] prevInInsertionOrder;
    transient int size;
    @LazyInit
    private transient Set<V> valueSet;
    transient V[] values;

    public final class EntryForKey extends AbstractMapEntry<K, V> {
        int index;
        @ParametricNullness
        final K key;

        public EntryForKey(int i3) {
            this.key = NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.keys[i3]);
            this.index = i3;
        }

        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        @ParametricNullness
        public V getValue() {
            updateIndex();
            int i3 = this.index;
            return i3 == -1 ? NullnessCasts.unsafeNull() : NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[i3]);
        }

        @ParametricNullness
        public V setValue(@ParametricNullness V v2) {
            updateIndex();
            int i3 = this.index;
            if (i3 == -1) {
                HashBiMap.this.put(this.key, v2);
                return NullnessCasts.unsafeNull();
            }
            V uncheckedCastNullableTToT = NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[i3]);
            if (Objects.equal(uncheckedCastNullableTToT, v2)) {
                return v2;
            }
            HashBiMap.this.replaceValueInEntry(this.index, v2, false);
            return uncheckedCastNullableTToT;
        }

        public void updateIndex() {
            int i3 = this.index;
            if (i3 != -1) {
                HashBiMap hashBiMap = HashBiMap.this;
                if (i3 <= hashBiMap.size && Objects.equal(hashBiMap.keys[i3], this.key)) {
                    return;
                }
            }
            this.index = HashBiMap.this.findEntryByKey(this.key);
        }
    }

    public static final class EntryForValue<K, V> extends AbstractMapEntry<V, K> {
        final HashBiMap<K, V> biMap;
        int index;
        @ParametricNullness
        final V value;

        public EntryForValue(HashBiMap<K, V> hashBiMap, int i3) {
            this.biMap = hashBiMap;
            this.value = NullnessCasts.uncheckedCastNullableTToT(hashBiMap.values[i3]);
            this.index = i3;
        }

        private void updateIndex() {
            int i3 = this.index;
            if (i3 != -1) {
                HashBiMap<K, V> hashBiMap = this.biMap;
                if (i3 <= hashBiMap.size && Objects.equal(this.value, hashBiMap.values[i3])) {
                    return;
                }
            }
            this.index = this.biMap.findEntryByValue(this.value);
        }

        @ParametricNullness
        public V getKey() {
            return this.value;
        }

        @ParametricNullness
        public K getValue() {
            updateIndex();
            int i3 = this.index;
            return i3 == -1 ? NullnessCasts.unsafeNull() : NullnessCasts.uncheckedCastNullableTToT(this.biMap.keys[i3]);
        }

        @ParametricNullness
        public K setValue(@ParametricNullness K k2) {
            updateIndex();
            int i3 = this.index;
            if (i3 == -1) {
                this.biMap.putInverse(this.value, k2, false);
                return NullnessCasts.unsafeNull();
            }
            K uncheckedCastNullableTToT = NullnessCasts.uncheckedCastNullableTToT(this.biMap.keys[i3]);
            if (Objects.equal(uncheckedCastNullableTToT, k2)) {
                return k2;
            }
            this.biMap.replaceKeyInEntry(this.index, k2, false);
            return uncheckedCastNullableTToT;
        }
    }

    public final class EntrySet extends View<K, V, Map.Entry<K, V>> {
        public EntrySet() {
            super(HashBiMap.this);
        }

        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int findEntryByKey = HashBiMap.this.findEntryByKey(key);
            return findEntryByKey != -1 && Objects.equal(value, HashBiMap.this.values[findEntryByKey]);
        }

        @CanIgnoreReturnValue
        public boolean remove(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int smearedHash = Hashing.smearedHash(key);
            int findEntryByKey = HashBiMap.this.findEntryByKey(key, smearedHash);
            if (findEntryByKey == -1 || !Objects.equal(value, HashBiMap.this.values[findEntryByKey])) {
                return false;
            }
            HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, smearedHash);
            return true;
        }

        public Map.Entry<K, V> forEntry(int i3) {
            return new EntryForKey(i3);
        }
    }

    public static class Inverse<K, V> extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {
        private final HashBiMap<K, V> forward;
        private transient Set<Map.Entry<V, K>> inverseEntrySet;

        public Inverse(HashBiMap<K, V> hashBiMap) {
            this.forward = hashBiMap;
        }

        @GwtIncompatible("serialization")
        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            BiMap unused = this.forward.inverse = this;
        }

        public void clear() {
            this.forward.clear();
        }

        public boolean containsKey(@CheckForNull Object obj) {
            return this.forward.containsValue(obj);
        }

        public boolean containsValue(@CheckForNull Object obj) {
            return this.forward.containsKey(obj);
        }

        public Set<Map.Entry<V, K>> entrySet() {
            Set<Map.Entry<V, K>> set = this.inverseEntrySet;
            if (set != null) {
                return set;
            }
            InverseEntrySet inverseEntrySet2 = new InverseEntrySet(this.forward);
            this.inverseEntrySet = inverseEntrySet2;
            return inverseEntrySet2;
        }

        @CheckForNull
        @CanIgnoreReturnValue
        public K forcePut(@ParametricNullness V v2, @ParametricNullness K k2) {
            return this.forward.putInverse(v2, k2, true);
        }

        @CheckForNull
        public K get(@CheckForNull Object obj) {
            return this.forward.getInverse(obj);
        }

        public BiMap<K, V> inverse() {
            return this.forward;
        }

        public Set<V> keySet() {
            return this.forward.values();
        }

        @CheckForNull
        @CanIgnoreReturnValue
        public K put(@ParametricNullness V v2, @ParametricNullness K k2) {
            return this.forward.putInverse(v2, k2, false);
        }

        @CheckForNull
        @CanIgnoreReturnValue
        public K remove(@CheckForNull Object obj) {
            return this.forward.removeInverse(obj);
        }

        public int size() {
            return this.forward.size;
        }

        public Set<K> values() {
            return this.forward.keySet();
        }
    }

    public static class InverseEntrySet<K, V> extends View<K, V, Map.Entry<V, K>> {
        public InverseEntrySet(HashBiMap<K, V> hashBiMap) {
            super(hashBiMap);
        }

        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int findEntryByValue = this.biMap.findEntryByValue(key);
            return findEntryByValue != -1 && Objects.equal(this.biMap.keys[findEntryByValue], value);
        }

        public boolean remove(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int smearedHash = Hashing.smearedHash(key);
            int findEntryByValue = this.biMap.findEntryByValue(key, smearedHash);
            if (findEntryByValue == -1 || !Objects.equal(this.biMap.keys[findEntryByValue], value)) {
                return false;
            }
            this.biMap.removeEntryValueHashKnown(findEntryByValue, smearedHash);
            return true;
        }

        public Map.Entry<V, K> forEntry(int i3) {
            return new EntryForValue(this.biMap, i3);
        }
    }

    public final class KeySet extends View<K, V, K> {
        public KeySet() {
            super(HashBiMap.this);
        }

        public boolean contains(@CheckForNull Object obj) {
            return HashBiMap.this.containsKey(obj);
        }

        @ParametricNullness
        public K forEntry(int i3) {
            return NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.keys[i3]);
        }

        public boolean remove(@CheckForNull Object obj) {
            int smearedHash = Hashing.smearedHash(obj);
            int findEntryByKey = HashBiMap.this.findEntryByKey(obj, smearedHash);
            if (findEntryByKey == -1) {
                return false;
            }
            HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, smearedHash);
            return true;
        }
    }

    public final class ValueSet extends View<K, V, V> {
        public ValueSet() {
            super(HashBiMap.this);
        }

        public boolean contains(@CheckForNull Object obj) {
            return HashBiMap.this.containsValue(obj);
        }

        @ParametricNullness
        public V forEntry(int i3) {
            return NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[i3]);
        }

        public boolean remove(@CheckForNull Object obj) {
            int smearedHash = Hashing.smearedHash(obj);
            int findEntryByValue = HashBiMap.this.findEntryByValue(obj, smearedHash);
            if (findEntryByValue == -1) {
                return false;
            }
            HashBiMap.this.removeEntryValueHashKnown(findEntryByValue, smearedHash);
            return true;
        }
    }

    public static abstract class View<K, V, T> extends AbstractSet<T> {
        final HashBiMap<K, V> biMap;

        public View(HashBiMap<K, V> hashBiMap) {
            this.biMap = hashBiMap;
        }

        public void clear() {
            this.biMap.clear();
        }

        @ParametricNullness
        public abstract T forEntry(int i3);

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                private int expectedModCount;
                private int index;
                private int indexToRemove = -1;
                private int remaining;

                {
                    this.index = View.this.biMap.firstInInsertionOrder;
                    HashBiMap<K, V> hashBiMap = View.this.biMap;
                    this.expectedModCount = hashBiMap.modCount;
                    this.remaining = hashBiMap.size;
                }

                private void checkForComodification() {
                    if (View.this.biMap.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                public boolean hasNext() {
                    checkForComodification();
                    return this.index != -2 && this.remaining > 0;
                }

                @ParametricNullness
                public T next() {
                    if (hasNext()) {
                        T forEntry = View.this.forEntry(this.index);
                        this.indexToRemove = this.index;
                        this.index = View.this.biMap.nextInInsertionOrder[this.index];
                        this.remaining--;
                        return forEntry;
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    checkForComodification();
                    CollectPreconditions.checkRemove(this.indexToRemove != -1);
                    View.this.biMap.removeEntry(this.indexToRemove);
                    int i3 = this.index;
                    HashBiMap<K, V> hashBiMap = View.this.biMap;
                    if (i3 == hashBiMap.size) {
                        this.index = this.indexToRemove;
                    }
                    this.indexToRemove = -1;
                    this.expectedModCount = hashBiMap.modCount;
                }
            };
        }

        public int size() {
            return this.biMap.size;
        }
    }

    private HashBiMap(int i3) {
        init(i3);
    }

    private int bucket(int i3) {
        return (this.hashTableKToV.length - 1) & i3;
    }

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    private static int[] createFilledWithAbsent(int i3) {
        int[] iArr = new int[i3];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private void deleteFromTableKToV(int i3, int i4) {
        Preconditions.checkArgument(i3 != -1);
        int bucket = bucket(i4);
        int[] iArr = this.hashTableKToV;
        int i5 = iArr[bucket];
        if (i5 == i3) {
            int[] iArr2 = this.nextInBucketKToV;
            iArr[bucket] = iArr2[i3];
            iArr2[i3] = -1;
            return;
        }
        int i6 = this.nextInBucketKToV[i5];
        while (true) {
            int i7 = i5;
            i5 = i6;
            int i8 = i7;
            if (i5 == -1) {
                throw new AssertionError("Expected to find entry with key " + this.keys[i3]);
            } else if (i5 == i3) {
                int[] iArr3 = this.nextInBucketKToV;
                iArr3[i8] = iArr3[i3];
                iArr3[i3] = -1;
                return;
            } else {
                i6 = this.nextInBucketKToV[i5];
            }
        }
    }

    private void deleteFromTableVToK(int i3, int i4) {
        Preconditions.checkArgument(i3 != -1);
        int bucket = bucket(i4);
        int[] iArr = this.hashTableVToK;
        int i5 = iArr[bucket];
        if (i5 == i3) {
            int[] iArr2 = this.nextInBucketVToK;
            iArr[bucket] = iArr2[i3];
            iArr2[i3] = -1;
            return;
        }
        int i6 = this.nextInBucketVToK[i5];
        while (true) {
            int i7 = i5;
            i5 = i6;
            int i8 = i7;
            if (i5 == -1) {
                throw new AssertionError("Expected to find entry with value " + this.values[i3]);
            } else if (i5 == i3) {
                int[] iArr3 = this.nextInBucketVToK;
                iArr3[i8] = iArr3[i3];
                iArr3[i3] = -1;
                return;
            } else {
                i6 = this.nextInBucketVToK[i5];
            }
        }
    }

    private void ensureCapacity(int i3) {
        int[] iArr = this.nextInBucketKToV;
        if (iArr.length < i3) {
            int expandedCapacity = ImmutableCollection.Builder.expandedCapacity(iArr.length, i3);
            this.keys = Arrays.copyOf(this.keys, expandedCapacity);
            this.values = Arrays.copyOf(this.values, expandedCapacity);
            this.nextInBucketKToV = expandAndFillWithAbsent(this.nextInBucketKToV, expandedCapacity);
            this.nextInBucketVToK = expandAndFillWithAbsent(this.nextInBucketVToK, expandedCapacity);
            this.prevInInsertionOrder = expandAndFillWithAbsent(this.prevInInsertionOrder, expandedCapacity);
            this.nextInInsertionOrder = expandAndFillWithAbsent(this.nextInInsertionOrder, expandedCapacity);
        }
        if (this.hashTableKToV.length < i3) {
            int closedTableSize = Hashing.closedTableSize(i3, 1.0d);
            this.hashTableKToV = createFilledWithAbsent(closedTableSize);
            this.hashTableVToK = createFilledWithAbsent(closedTableSize);
            for (int i4 = 0; i4 < this.size; i4++) {
                int bucket = bucket(Hashing.smearedHash(this.keys[i4]));
                int[] iArr2 = this.nextInBucketKToV;
                int[] iArr3 = this.hashTableKToV;
                iArr2[i4] = iArr3[bucket];
                iArr3[bucket] = i4;
                int bucket2 = bucket(Hashing.smearedHash(this.values[i4]));
                int[] iArr4 = this.nextInBucketVToK;
                int[] iArr5 = this.hashTableVToK;
                iArr4[i4] = iArr5[bucket2];
                iArr5[bucket2] = i4;
            }
        }
    }

    private static int[] expandAndFillWithAbsent(int[] iArr, int i3) {
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, i3);
        Arrays.fill(copyOf, length, i3, -1);
        return copyOf;
    }

    private void insertIntoTableKToV(int i3, int i4) {
        Preconditions.checkArgument(i3 != -1);
        int bucket = bucket(i4);
        int[] iArr = this.nextInBucketKToV;
        int[] iArr2 = this.hashTableKToV;
        iArr[i3] = iArr2[bucket];
        iArr2[bucket] = i3;
    }

    private void insertIntoTableVToK(int i3, int i4) {
        Preconditions.checkArgument(i3 != -1);
        int bucket = bucket(i4);
        int[] iArr = this.nextInBucketVToK;
        int[] iArr2 = this.hashTableVToK;
        iArr[i3] = iArr2[bucket];
        iArr2[bucket] = i3;
    }

    private void moveEntryToIndex(int i3, int i4) {
        int i5;
        int i6;
        if (i3 != i4) {
            int i7 = this.prevInInsertionOrder[i3];
            int i8 = this.nextInInsertionOrder[i3];
            setSucceeds(i7, i4);
            setSucceeds(i4, i8);
            K[] kArr = this.keys;
            K k2 = kArr[i3];
            V[] vArr = this.values;
            V v2 = vArr[i3];
            kArr[i4] = k2;
            vArr[i4] = v2;
            int bucket = bucket(Hashing.smearedHash(k2));
            int[] iArr = this.hashTableKToV;
            int i9 = iArr[bucket];
            if (i9 == i3) {
                iArr[bucket] = i4;
            } else {
                int i10 = this.nextInBucketKToV[i9];
                while (true) {
                    int i11 = i9;
                    i9 = i10;
                    i6 = i11;
                    if (i9 == i3) {
                        break;
                    }
                    i10 = this.nextInBucketKToV[i9];
                }
                this.nextInBucketKToV[i6] = i4;
            }
            int[] iArr2 = this.nextInBucketKToV;
            iArr2[i4] = iArr2[i3];
            iArr2[i3] = -1;
            int bucket2 = bucket(Hashing.smearedHash(v2));
            int[] iArr3 = this.hashTableVToK;
            int i12 = iArr3[bucket2];
            if (i12 == i3) {
                iArr3[bucket2] = i4;
            } else {
                int i13 = this.nextInBucketVToK[i12];
                while (true) {
                    int i14 = i12;
                    i12 = i13;
                    i5 = i14;
                    if (i12 == i3) {
                        break;
                    }
                    i13 = this.nextInBucketVToK[i12];
                }
                this.nextInBucketVToK[i5] = i4;
            }
            int[] iArr4 = this.nextInBucketVToK;
            iArr4[i4] = iArr4[i3];
            iArr4[i3] = -1;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readCount = Serialization.readCount(objectInputStream);
        init(16);
        Serialization.populateMap(this, objectInputStream, readCount);
    }

    /* access modifiers changed from: private */
    public void replaceKeyInEntry(int i3, @ParametricNullness K k2, boolean z2) {
        int i4;
        Preconditions.checkArgument(i3 != -1);
        int smearedHash = Hashing.smearedHash(k2);
        int findEntryByKey = findEntryByKey(k2, smearedHash);
        int i5 = this.lastInInsertionOrder;
        if (findEntryByKey == -1) {
            i4 = -2;
        } else if (z2) {
            i5 = this.prevInInsertionOrder[findEntryByKey];
            i4 = this.nextInInsertionOrder[findEntryByKey];
            removeEntryKeyHashKnown(findEntryByKey, smearedHash);
            if (i3 == this.size) {
                i3 = findEntryByKey;
            }
        } else {
            throw new IllegalArgumentException(f.a(k2, "Key already present in map: "));
        }
        if (i5 == i3) {
            i5 = this.prevInInsertionOrder[i3];
        } else if (i5 == this.size) {
            i5 = findEntryByKey;
        }
        if (i4 == i3) {
            findEntryByKey = this.nextInInsertionOrder[i3];
        } else if (i4 != this.size) {
            findEntryByKey = i4;
        }
        setSucceeds(this.prevInInsertionOrder[i3], this.nextInInsertionOrder[i3]);
        deleteFromTableKToV(i3, Hashing.smearedHash(this.keys[i3]));
        this.keys[i3] = k2;
        insertIntoTableKToV(i3, Hashing.smearedHash(k2));
        setSucceeds(i5, i3);
        setSucceeds(i3, findEntryByKey);
    }

    /* access modifiers changed from: private */
    public void replaceValueInEntry(int i3, @ParametricNullness V v2, boolean z2) {
        Preconditions.checkArgument(i3 != -1);
        int smearedHash = Hashing.smearedHash(v2);
        int findEntryByValue = findEntryByValue(v2, smearedHash);
        if (findEntryByValue != -1) {
            if (z2) {
                removeEntryValueHashKnown(findEntryByValue, smearedHash);
                if (i3 == this.size) {
                    i3 = findEntryByValue;
                }
            } else {
                throw new IllegalArgumentException(f.a(v2, "Value already present in map: "));
            }
        }
        deleteFromTableVToK(i3, Hashing.smearedHash(this.values[i3]));
        this.values[i3] = v2;
        insertIntoTableVToK(i3, smearedHash);
    }

    private void setSucceeds(int i3, int i4) {
        if (i3 == -2) {
            this.firstInInsertionOrder = i4;
        } else {
            this.nextInInsertionOrder[i3] = i4;
        }
        if (i4 == -2) {
            this.lastInInsertionOrder = i3;
        } else {
            this.prevInInsertionOrder[i4] = i3;
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMap(this, objectOutputStream);
    }

    public void clear() {
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        Arrays.fill(this.hashTableKToV, -1);
        Arrays.fill(this.hashTableVToK, -1);
        Arrays.fill(this.nextInBucketKToV, 0, this.size, -1);
        Arrays.fill(this.nextInBucketVToK, 0, this.size, -1);
        Arrays.fill(this.prevInInsertionOrder, 0, this.size, -1);
        Arrays.fill(this.nextInInsertionOrder, 0, this.size, -1);
        this.size = 0;
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.modCount++;
    }

    public boolean containsKey(@CheckForNull Object obj) {
        return findEntryByKey(obj) != -1;
    }

    public boolean containsValue(@CheckForNull Object obj) {
        return findEntryByValue(obj) != -1;
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

    public int findEntry(@CheckForNull Object obj, int i3, int[] iArr, int[] iArr2, Object[] objArr) {
        int i4 = iArr[bucket(i3)];
        while (i4 != -1) {
            if (Objects.equal(objArr[i4], obj)) {
                return i4;
            }
            i4 = iArr2[i4];
        }
        return -1;
    }

    public int findEntryByKey(@CheckForNull Object obj) {
        return findEntryByKey(obj, Hashing.smearedHash(obj));
    }

    public int findEntryByValue(@CheckForNull Object obj) {
        return findEntryByValue(obj, Hashing.smearedHash(obj));
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V forcePut(@ParametricNullness K k2, @ParametricNullness V v2) {
        return put(k2, v2, true);
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        int findEntryByKey = findEntryByKey(obj);
        if (findEntryByKey == -1) {
            return null;
        }
        return this.values[findEntryByKey];
    }

    @CheckForNull
    public K getInverse(@CheckForNull Object obj) {
        int findEntryByValue = findEntryByValue(obj);
        if (findEntryByValue == -1) {
            return null;
        }
        return this.keys[findEntryByValue];
    }

    public void init(int i3) {
        CollectPreconditions.checkNonnegative(i3, "expectedSize");
        int closedTableSize = Hashing.closedTableSize(i3, 1.0d);
        this.size = 0;
        this.keys = new Object[i3];
        this.values = new Object[i3];
        this.hashTableKToV = createFilledWithAbsent(closedTableSize);
        this.hashTableVToK = createFilledWithAbsent(closedTableSize);
        this.nextInBucketKToV = createFilledWithAbsent(i3);
        this.nextInBucketVToK = createFilledWithAbsent(i3);
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.prevInInsertionOrder = createFilledWithAbsent(i3);
        this.nextInInsertionOrder = createFilledWithAbsent(i3);
    }

    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap != null) {
            return biMap;
        }
        Inverse inverse2 = new Inverse(this);
        this.inverse = inverse2;
        return inverse2;
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

    @CheckForNull
    @CanIgnoreReturnValue
    public V put(@ParametricNullness K k2, @ParametricNullness V v2) {
        return put(k2, v2, false);
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public K putInverse(@ParametricNullness V v2, @ParametricNullness K k2, boolean z2) {
        int smearedHash = Hashing.smearedHash(v2);
        int findEntryByValue = findEntryByValue(v2, smearedHash);
        if (findEntryByValue != -1) {
            K k3 = this.keys[findEntryByValue];
            if (Objects.equal(k3, k2)) {
                return k2;
            }
            replaceKeyInEntry(findEntryByValue, k2, z2);
            return k3;
        }
        int i3 = this.lastInInsertionOrder;
        int smearedHash2 = Hashing.smearedHash(k2);
        int findEntryByKey = findEntryByKey(k2, smearedHash2);
        if (!z2) {
            Preconditions.checkArgument(findEntryByKey == -1, "Key already present: %s", (Object) k2);
        } else if (findEntryByKey != -1) {
            i3 = this.prevInInsertionOrder[findEntryByKey];
            removeEntryKeyHashKnown(findEntryByKey, smearedHash2);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int i4 = this.size;
        kArr[i4] = k2;
        this.values[i4] = v2;
        insertIntoTableKToV(i4, smearedHash2);
        insertIntoTableVToK(this.size, smearedHash);
        int i5 = i3 == -2 ? this.firstInInsertionOrder : this.nextInInsertionOrder[i3];
        setSucceeds(i3, this.size);
        setSucceeds(this.size, i5);
        this.size++;
        this.modCount++;
        return null;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V remove(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int findEntryByKey = findEntryByKey(obj, smearedHash);
        if (findEntryByKey == -1) {
            return null;
        }
        V v2 = this.values[findEntryByKey];
        removeEntryKeyHashKnown(findEntryByKey, smearedHash);
        return v2;
    }

    public void removeEntry(int i3) {
        removeEntryKeyHashKnown(i3, Hashing.smearedHash(this.keys[i3]));
    }

    public void removeEntryKeyHashKnown(int i3, int i4) {
        removeEntry(i3, i4, Hashing.smearedHash(this.values[i3]));
    }

    public void removeEntryValueHashKnown(int i3, int i4) {
        removeEntry(i3, Hashing.smearedHash(this.keys[i3]), i4);
    }

    @CheckForNull
    public K removeInverse(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int findEntryByValue = findEntryByValue(obj, smearedHash);
        if (findEntryByValue == -1) {
            return null;
        }
        K k2 = this.keys[findEntryByValue];
        removeEntryValueHashKnown(findEntryByValue, smearedHash);
        return k2;
    }

    public int size() {
        return this.size;
    }

    public static <K, V> HashBiMap<K, V> create(int i3) {
        return new HashBiMap<>(i3);
    }

    private void removeEntry(int i3, int i4, int i5) {
        Preconditions.checkArgument(i3 != -1);
        deleteFromTableKToV(i3, i4);
        deleteFromTableVToK(i3, i5);
        setSucceeds(this.prevInInsertionOrder[i3], this.nextInInsertionOrder[i3]);
        moveEntryToIndex(this.size - 1, i3);
        K[] kArr = this.keys;
        int i6 = this.size;
        kArr[i6 - 1] = null;
        this.values[i6 - 1] = null;
        this.size = i6 - 1;
        this.modCount++;
    }

    public int findEntryByKey(@CheckForNull Object obj, int i3) {
        return findEntry(obj, i3, this.hashTableKToV, this.nextInBucketKToV, this.keys);
    }

    public int findEntryByValue(@CheckForNull Object obj, int i3) {
        return findEntry(obj, i3, this.hashTableVToK, this.nextInBucketVToK, this.values);
    }

    @CheckForNull
    public V put(@ParametricNullness K k2, @ParametricNullness V v2, boolean z2) {
        int smearedHash = Hashing.smearedHash(k2);
        int findEntryByKey = findEntryByKey(k2, smearedHash);
        if (findEntryByKey != -1) {
            V v3 = this.values[findEntryByKey];
            if (Objects.equal(v3, v2)) {
                return v2;
            }
            replaceValueInEntry(findEntryByKey, v2, z2);
            return v3;
        }
        int smearedHash2 = Hashing.smearedHash(v2);
        int findEntryByValue = findEntryByValue(v2, smearedHash2);
        if (!z2) {
            Preconditions.checkArgument(findEntryByValue == -1, "Value already present: %s", (Object) v2);
        } else if (findEntryByValue != -1) {
            removeEntryValueHashKnown(findEntryByValue, smearedHash2);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int i3 = this.size;
        kArr[i3] = k2;
        this.values[i3] = v2;
        insertIntoTableKToV(i3, smearedHash);
        insertIntoTableVToK(this.size, smearedHash2);
        setSucceeds(this.lastInInsertionOrder, this.size);
        setSucceeds(this.size, -2);
        this.size++;
        this.modCount++;
        return null;
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

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> create = create(map.size());
        create.putAll(map);
        return create;
    }
}
