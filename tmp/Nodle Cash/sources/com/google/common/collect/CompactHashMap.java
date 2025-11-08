package com.google.common.collect;

import A.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    /* access modifiers changed from: private */
    public static final Object NOT_FOUND = new Object();
    @CheckForNull
    @VisibleForTesting
    transient int[] entries;
    @CheckForNull
    @LazyInit
    private transient Set<Map.Entry<K, V>> entrySetView;
    @CheckForNull
    @LazyInit
    private transient Set<K> keySetView;
    @CheckForNull
    @VisibleForTesting
    transient Object[] keys;
    /* access modifiers changed from: private */
    public transient int metadata;
    private transient int size;
    @CheckForNull
    private transient Object table;
    @CheckForNull
    @VisibleForTesting
    transient Object[] values;
    @CheckForNull
    @LazyInit
    private transient Collection<V> valuesView;

    public class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        public EntrySetView() {
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        public boolean contains(@CheckForNull Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().contains(obj);
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int access$500 = CompactHashMap.this.indexOf(entry.getKey());
            return access$500 != -1 && Objects.equal(CompactHashMap.this.value(access$500), entry.getValue());
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
            r0 = com.google.common.collect.CompactHashMap.access$700(r9.this$0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean remove(@javax.annotation.CheckForNull java.lang.Object r10) {
            /*
                r9 = this;
                com.google.common.collect.CompactHashMap r0 = com.google.common.collect.CompactHashMap.this
                java.util.Map r0 = r0.delegateOrNull()
                if (r0 == 0) goto L_0x0011
                java.util.Set r9 = r0.entrySet()
                boolean r9 = r9.remove(r10)
                return r9
            L_0x0011:
                boolean r0 = r10 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 == 0) goto L_0x0061
                java.util.Map$Entry r10 = (java.util.Map.Entry) r10
                com.google.common.collect.CompactHashMap r0 = com.google.common.collect.CompactHashMap.this
                boolean r0 = r0.needsAllocArrays()
                if (r0 == 0) goto L_0x0021
                return r1
            L_0x0021:
                com.google.common.collect.CompactHashMap r0 = com.google.common.collect.CompactHashMap.this
                int r0 = r0.hashTableMask()
                java.lang.Object r2 = r10.getKey()
                java.lang.Object r3 = r10.getValue()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                java.lang.Object r5 = r10.requireTable()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                int[] r6 = r10.requireEntries()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                java.lang.Object[] r7 = r10.requireKeys()
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                java.lang.Object[] r8 = r10.requireValues()
                r4 = r0
                int r10 = com.google.common.collect.CompactHashing.remove(r2, r3, r4, r5, r6, r7, r8)
                r2 = -1
                if (r10 != r2) goto L_0x0050
                return r1
            L_0x0050:
                com.google.common.collect.CompactHashMap r1 = com.google.common.collect.CompactHashMap.this
                r1.moveLastEntry(r10, r0)
                com.google.common.collect.CompactHashMap r10 = com.google.common.collect.CompactHashMap.this
                com.google.common.collect.CompactHashMap.access$1210(r10)
                com.google.common.collect.CompactHashMap r9 = com.google.common.collect.CompactHashMap.this
                r9.incrementModCount()
                r9 = 1
                return r9
            L_0x0061:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.CompactHashMap.EntrySetView.remove(java.lang.Object):boolean");
        }

        public int size() {
            return CompactHashMap.this.size();
        }
    }

    public abstract class Itr<T> implements Iterator<T> {
        int currentIndex;
        int expectedMetadata;
        int indexToRemove;

        private Itr() {
            this.expectedMetadata = CompactHashMap.this.metadata;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata != this.expectedMetadata) {
                throw new ConcurrentModificationException();
            }
        }

        @ParametricNullness
        public abstract T getOutput(int i3);

        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        public void incrementExpectedModCount() {
            this.expectedMetadata += 32;
        }

        @ParametricNullness
        public T next() {
            checkForConcurrentModification();
            if (hasNext()) {
                int i3 = this.currentIndex;
                this.indexToRemove = i3;
                T output = getOutput(i3);
                this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
                return output;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            incrementExpectedModCount();
            CompactHashMap compactHashMap = CompactHashMap.this;
            compactHashMap.remove(compactHashMap.key(this.indexToRemove));
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }
    }

    public class KeySetView extends AbstractSet<K> {
        public KeySetView() {
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        public boolean contains(@CheckForNull Object obj) {
            return CompactHashMap.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        public boolean remove(@CheckForNull Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            return delegateOrNull != null ? delegateOrNull.keySet().remove(obj) : CompactHashMap.this.removeHelper(obj) != CompactHashMap.NOT_FOUND;
        }

        public int size() {
            return CompactHashMap.this.size();
        }
    }

    public final class MapEntry extends AbstractMapEntry<K, V> {
        @ParametricNullness
        private final K key;
        private int lastKnownIndex;

        public MapEntry(int i3) {
            this.key = CompactHashMap.this.key(i3);
            this.lastKnownIndex = i3;
        }

        private void updateLastKnownIndex() {
            int i3 = this.lastKnownIndex;
            if (i3 == -1 || i3 >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.key(this.lastKnownIndex))) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        @ParametricNullness
        public V getValue() {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return NullnessCasts.uncheckedCastNullableTToT(delegateOrNull.get(this.key));
            }
            updateLastKnownIndex();
            int i3 = this.lastKnownIndex;
            return i3 == -1 ? NullnessCasts.unsafeNull() : CompactHashMap.this.value(i3);
        }

        @ParametricNullness
        public V setValue(@ParametricNullness V v2) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return NullnessCasts.uncheckedCastNullableTToT(delegateOrNull.put(this.key, v2));
            }
            updateLastKnownIndex();
            int i3 = this.lastKnownIndex;
            if (i3 == -1) {
                CompactHashMap.this.put(this.key, v2);
                return NullnessCasts.unsafeNull();
            }
            V access$600 = CompactHashMap.this.value(i3);
            CompactHashMap.this.setValue(this.lastKnownIndex, v2);
            return access$600;
        }
    }

    public class ValuesView extends AbstractCollection<V> {
        public ValuesView() {
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }

        public int size() {
            return CompactHashMap.this.size();
        }
    }

    public CompactHashMap() {
        init(3);
    }

    public static /* synthetic */ int access$1210(CompactHashMap compactHashMap) {
        int i3 = compactHashMap.size;
        compactHashMap.size = i3 - 1;
        return i3;
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int i3) {
        return new CompactHashMap<>(i3);
    }

    private int entry(int i3) {
        return requireEntries()[i3];
    }

    /* access modifiers changed from: private */
    public int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    /* access modifiers changed from: private */
    public int indexOf(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return -1;
        }
        int smearedHash = Hashing.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int tableGet = CompactHashing.tableGet(requireTable(), smearedHash & hashTableMask);
        if (tableGet == 0) {
            return -1;
        }
        int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
        do {
            int i3 = tableGet - 1;
            int entry = entry(i3);
            if (CompactHashing.getHashPrefix(entry, hashTableMask) == hashPrefix && Objects.equal(obj, key(i3))) {
                return i3;
            }
            tableGet = CompactHashing.getNext(entry, hashTableMask);
        } while (tableGet != 0);
        return -1;
    }

    /* access modifiers changed from: private */
    public K key(int i3) {
        return requireKeys()[i3];
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i3 = 0; i3 < readInt; i3++) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            }
            return;
        }
        throw new InvalidObjectException(a.k("Invalid size: ", readInt));
    }

    /* access modifiers changed from: private */
    public Object removeHelper(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return NOT_FOUND;
        }
        int hashTableMask = hashTableMask();
        int remove = CompactHashing.remove(obj, (Object) null, hashTableMask, requireTable(), requireEntries(), requireKeys(), (Object[]) null);
        if (remove == -1) {
            return NOT_FOUND;
        }
        Object value = value(remove);
        moveLastEntry(remove, hashTableMask);
        this.size--;
        incrementModCount();
        return value;
    }

    /* access modifiers changed from: private */
    public int[] requireEntries() {
        int[] iArr = this.entries;
        java.util.Objects.requireNonNull(iArr);
        return iArr;
    }

    /* access modifiers changed from: private */
    public Object[] requireKeys() {
        Object[] objArr = this.keys;
        java.util.Objects.requireNonNull(objArr);
        return objArr;
    }

    /* access modifiers changed from: private */
    public Object requireTable() {
        Object obj = this.table;
        java.util.Objects.requireNonNull(obj);
        return obj;
    }

    /* access modifiers changed from: private */
    public Object[] requireValues() {
        Object[] objArr = this.values;
        java.util.Objects.requireNonNull(objArr);
        return objArr;
    }

    private void resizeMeMaybe(int i3) {
        int min;
        int length = requireEntries().length;
        if (i3 > length && (min = Math.min(LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            resizeEntries(min);
        }
    }

    @CanIgnoreReturnValue
    private int resizeTable(int i3, int i4, int i5, int i6) {
        Object createTable = CompactHashing.createTable(i4);
        int i7 = i4 - 1;
        if (i6 != 0) {
            CompactHashing.tableSet(createTable, i5 & i7, i6 + 1);
        }
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        for (int i8 = 0; i8 <= i3; i8++) {
            int tableGet = CompactHashing.tableGet(requireTable, i8);
            while (tableGet != 0) {
                int i9 = tableGet - 1;
                int i10 = requireEntries[i9];
                int hashPrefix = CompactHashing.getHashPrefix(i10, i3) | i8;
                int i11 = hashPrefix & i7;
                int tableGet2 = CompactHashing.tableGet(createTable, i11);
                CompactHashing.tableSet(createTable, i11, tableGet);
                requireEntries[i9] = CompactHashing.maskCombine(hashPrefix, tableGet2, i7);
                tableGet = CompactHashing.getNext(i10, i3);
            }
        }
        this.table = createTable;
        setHashTableMask(i7);
        return i7;
    }

    private void setEntry(int i3, int i4) {
        requireEntries()[i3] = i4;
    }

    private void setHashTableMask(int i3) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(i3), 31);
    }

    private void setKey(int i3, K k2) {
        requireKeys()[i3] = k2;
    }

    /* access modifiers changed from: private */
    public void setValue(int i3, V v2) {
        requireValues()[i3] = v2;
    }

    /* access modifiers changed from: private */
    public V value(int i3) {
        return requireValues()[i3];
    }

    @J2ktIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator entrySetIterator = entrySetIterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) entrySetIterator.next();
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    public void accessEntry(int i3) {
    }

    public int adjustAfterRemove(int i3, int i4) {
        return i3 - 1;
    }

    @CanIgnoreReturnValue
    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int i3 = this.metadata;
        int tableSize = CompactHashing.tableSize(i3);
        this.table = CompactHashing.createTable(tableSize);
        setHashTableMask(tableSize - 1);
        this.entries = new int[i3];
        this.keys = new Object[i3];
        this.values = new Object[i3];
        return i3;
    }

    public void clear() {
        if (!needsAllocArrays()) {
            incrementModCount();
            Map delegateOrNull = delegateOrNull();
            if (delegateOrNull != null) {
                this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
                delegateOrNull.clear();
                this.table = null;
                this.size = 0;
                return;
            }
            Arrays.fill(requireKeys(), 0, this.size, (Object) null);
            Arrays.fill(requireValues(), 0, this.size, (Object) null);
            CompactHashing.tableClear(requireTable());
            Arrays.fill(requireEntries(), 0, this.size, 0);
            this.size = 0;
        }
    }

    public boolean containsKey(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.containsKey(obj) : indexOf(obj) != -1;
    }

    public boolean containsValue(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsValue(obj);
        }
        for (int i3 = 0; i3 < this.size; i3++) {
            if (Objects.equal(obj, value(i3))) {
                return true;
            }
        }
        return false;
    }

    @CanIgnoreReturnValue
    @VisibleForTesting
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.put(key(firstEntryIndex), value(firstEntryIndex));
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.keys = null;
        this.values = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    public Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    public Map<K, V> createHashFloodingResistantDelegate(int i3) {
        return new LinkedHashMap(i3, 1.0f);
    }

    public Set<K> createKeySet() {
        return new KeySetView();
    }

    public Collection<V> createValues() {
        return new ValuesView();
    }

    @CheckForNull
    @VisibleForTesting
    public Map<K, V> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set != null) {
            return set;
        }
        Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySetView = createEntrySet;
        return createEntrySet;
    }

    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        Map delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.entrySet().iterator() : new CompactHashMap<K, V>.Itr<Map.Entry<K, V>>() {
            public Map.Entry<K, V> getOutput(int i3) {
                return new MapEntry(i3);
            }
        };
    }

    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.get(obj);
        }
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        accessEntry(indexOf);
        return value(indexOf);
    }

    public int getSuccessor(int i3) {
        int i4 = i3 + 1;
        if (i4 < this.size) {
            return i4;
        }
        return -1;
    }

    public void incrementModCount() {
        this.metadata += 32;
    }

    public void init(int i3) {
        Preconditions.checkArgument(i3 >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(i3, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    public void insertEntry(int i3, @ParametricNullness K k2, @ParametricNullness V v2, int i4, int i5) {
        setEntry(i3, CompactHashing.maskCombine(i4, 0, i5));
        setKey(i3, k2);
        setValue(i3, v2);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set != null) {
            return set;
        }
        Set<K> createKeySet = createKeySet();
        this.keySetView = createKeySet;
        return createKeySet;
    }

    public Iterator<K> keySetIterator() {
        Map delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.keySet().iterator() : new CompactHashMap<K, V>.Itr<K>() {
            @ParametricNullness
            public K getOutput(int i3) {
                return CompactHashMap.this.key(i3);
            }
        };
    }

    public void moveLastEntry(int i3, int i4) {
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        Object[] requireValues = requireValues();
        int size2 = size();
        int i5 = size2 - 1;
        if (i3 < i5) {
            Object obj = requireKeys[i5];
            requireKeys[i3] = obj;
            requireValues[i3] = requireValues[i5];
            requireKeys[i5] = null;
            requireValues[i5] = null;
            requireEntries[i3] = requireEntries[i5];
            requireEntries[i5] = 0;
            int smearedHash = Hashing.smearedHash(obj) & i4;
            int tableGet = CompactHashing.tableGet(requireTable, smearedHash);
            if (tableGet == size2) {
                CompactHashing.tableSet(requireTable, smearedHash, i3 + 1);
                return;
            }
            while (true) {
                int i6 = tableGet - 1;
                int i7 = requireEntries[i6];
                int next = CompactHashing.getNext(i7, i4);
                if (next == size2) {
                    requireEntries[i6] = CompactHashing.maskCombine(i7, i3 + 1, i4);
                    return;
                }
                tableGet = next;
            }
        } else {
            requireKeys[i3] = null;
            requireValues[i3] = null;
            requireEntries[i3] = 0;
        }
    }

    @VisibleForTesting
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V put(@ParametricNullness K k2, @ParametricNullness V v2) {
        int i3;
        int resizeTable;
        if (needsAllocArrays()) {
            allocArrays();
        }
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.put(k2, v2);
        }
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        V[] requireValues = requireValues();
        int i4 = this.size;
        int i5 = i4 + 1;
        int smearedHash = Hashing.smearedHash(k2);
        int hashTableMask = hashTableMask();
        int i6 = smearedHash & hashTableMask;
        int tableGet = CompactHashing.tableGet(requireTable(), i6);
        if (tableGet != 0) {
            int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
            int i7 = 0;
            while (true) {
                int i8 = tableGet - 1;
                int i9 = requireEntries[i8];
                if (CompactHashing.getHashPrefix(i9, hashTableMask) != hashPrefix || !Objects.equal(k2, requireKeys[i8])) {
                    int next = CompactHashing.getNext(i9, hashTableMask);
                    i7++;
                    if (next != 0) {
                        tableGet = next;
                    } else if (i7 >= 9) {
                        return convertToHashFloodingResistantImplementation().put(k2, v2);
                    } else {
                        if (i5 > hashTableMask) {
                            resizeTable = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i4);
                        } else {
                            requireEntries[i8] = CompactHashing.maskCombine(i9, i5, hashTableMask);
                        }
                    }
                } else {
                    V v3 = requireValues[i8];
                    requireValues[i8] = v2;
                    accessEntry(i8);
                    return v3;
                }
            }
            i3 = hashTableMask;
            resizeMeMaybe(i5);
            insertEntry(i4, k2, v2, smearedHash, i3);
            this.size = i5;
            incrementModCount();
            return null;
        } else if (i5 > hashTableMask) {
            resizeTable = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i4);
        } else {
            CompactHashing.tableSet(requireTable(), i6, i5);
            i3 = hashTableMask;
            resizeMeMaybe(i5);
            insertEntry(i4, k2, v2, smearedHash, i3);
            this.size = i5;
            incrementModCount();
            return null;
        }
        i3 = resizeTable;
        resizeMeMaybe(i5);
        insertEntry(i4, k2, v2, smearedHash, i3);
        this.size = i5;
        incrementModCount();
        return null;
    }

    @CheckForNull
    @CanIgnoreReturnValue
    public V remove(@CheckForNull Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        V removeHelper = removeHelper(obj);
        if (removeHelper == NOT_FOUND) {
            return null;
        }
        return removeHelper;
    }

    public void resizeEntries(int i3) {
        this.entries = Arrays.copyOf(requireEntries(), i3);
        this.keys = Arrays.copyOf(requireKeys(), i3);
        this.values = Arrays.copyOf(requireValues(), i3);
    }

    public int size() {
        Map delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    public void trimToSize() {
        if (!needsAllocArrays()) {
            Map delegateOrNull = delegateOrNull();
            if (delegateOrNull != null) {
                Map createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
                createHashFloodingResistantDelegate.putAll(delegateOrNull);
                this.table = createHashFloodingResistantDelegate;
                return;
            }
            int i3 = this.size;
            if (i3 < requireEntries().length) {
                resizeEntries(i3);
            }
            int tableSize = CompactHashing.tableSize(i3);
            int hashTableMask = hashTableMask();
            if (tableSize < hashTableMask) {
                resizeTable(hashTableMask, tableSize, 0, 0);
            }
        }
    }

    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Collection<V> createValues = createValues();
        this.valuesView = createValues;
        return createValues;
    }

    public Iterator<V> valuesIterator() {
        Map delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.values().iterator() : new CompactHashMap<K, V>.Itr<V>() {
            @ParametricNullness
            public V getOutput(int i3) {
                return CompactHashMap.this.value(i3);
            }
        };
    }

    public CompactHashMap(int i3) {
        init(i3);
    }
}
