package com.google.common.collect;

import A.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    @CheckForNull
    @VisibleForTesting
    transient Object[] elements;
    @CheckForNull
    private transient int[] entries;
    /* access modifiers changed from: private */
    public transient int metadata;
    private transient int size;
    @CheckForNull
    private transient Object table;

    public CompactHashSet() {
        init(3);
    }

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    private Set<E> createHashFloodingResistantDelegate(int i3) {
        return new LinkedHashSet(i3, 1.0f);
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int i3) {
        return new CompactHashSet<>(i3);
    }

    /* access modifiers changed from: private */
    public E element(int i3) {
        return requireElements()[i3];
    }

    private int entry(int i3) {
        return requireEntries()[i3];
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i3 = 0; i3 < readInt; i3++) {
                add(objectInputStream.readObject());
            }
            return;
        }
        throw new InvalidObjectException(a.k("Invalid size: ", readInt));
    }

    private Object[] requireElements() {
        Object[] objArr = this.elements;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    private int[] requireEntries() {
        int[] iArr = this.entries;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    private Object requireTable() {
        Object obj = this.table;
        Objects.requireNonNull(obj);
        return obj;
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

    private void setElement(int i3, E e3) {
        requireElements()[i3] = e3;
    }

    private void setEntry(int i3, int i4) {
        requireEntries()[i3] = i4;
    }

    private void setHashTableMask(int i3) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(i3), 31);
    }

    @J2ktIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    @CanIgnoreReturnValue
    public boolean add(@ParametricNullness E e3) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        Set delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.add(e3);
        }
        int[] requireEntries = requireEntries();
        Object[] requireElements = requireElements();
        int i3 = this.size;
        int i4 = i3 + 1;
        int smearedHash = Hashing.smearedHash(e3);
        int hashTableMask = hashTableMask();
        int i5 = smearedHash & hashTableMask;
        int tableGet = CompactHashing.tableGet(requireTable(), i5);
        if (tableGet != 0) {
            int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
            int i6 = 0;
            while (true) {
                int i7 = tableGet - 1;
                int i8 = requireEntries[i7];
                if (CompactHashing.getHashPrefix(i8, hashTableMask) == hashPrefix && com.google.common.base.Objects.equal(e3, requireElements[i7])) {
                    return false;
                }
                int next = CompactHashing.getNext(i8, hashTableMask);
                i6++;
                if (next != 0) {
                    tableGet = next;
                } else if (i6 >= 9) {
                    return convertToHashFloodingResistantImplementation().add(e3);
                } else {
                    if (i4 > hashTableMask) {
                        hashTableMask = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i3);
                    } else {
                        requireEntries[i7] = CompactHashing.maskCombine(i8, i4, hashTableMask);
                    }
                }
            }
        } else if (i4 > hashTableMask) {
            hashTableMask = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i3);
        } else {
            CompactHashing.tableSet(requireTable(), i5, i4);
        }
        resizeMeMaybe(i4);
        insertEntry(i3, e3, smearedHash, hashTableMask);
        this.size = i4;
        incrementModCount();
        return true;
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
        this.elements = new Object[i3];
        return i3;
    }

    public void clear() {
        if (!needsAllocArrays()) {
            incrementModCount();
            Set delegateOrNull = delegateOrNull();
            if (delegateOrNull != null) {
                this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
                delegateOrNull.clear();
                this.table = null;
                this.size = 0;
                return;
            }
            Arrays.fill(requireElements(), 0, this.size, (Object) null);
            CompactHashing.tableClear(requireTable());
            Arrays.fill(requireEntries(), 0, this.size, 0);
            this.size = 0;
        }
    }

    public boolean contains(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.contains(obj);
        }
        int smearedHash = Hashing.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int tableGet = CompactHashing.tableGet(requireTable(), smearedHash & hashTableMask);
        if (tableGet == 0) {
            return false;
        }
        int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
        do {
            int i3 = tableGet - 1;
            int entry = entry(i3);
            if (CompactHashing.getHashPrefix(entry, hashTableMask) == hashPrefix && com.google.common.base.Objects.equal(obj, element(i3))) {
                return true;
            }
            tableGet = CompactHashing.getNext(entry, hashTableMask);
        } while (tableGet != 0);
        return false;
    }

    @CanIgnoreReturnValue
    @VisibleForTesting
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.add(element(firstEntryIndex));
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.elements = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    @CheckForNull
    @VisibleForTesting
    public Set<E> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
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

    public void insertEntry(int i3, @ParametricNullness E e3, int i4, int i5) {
        setEntry(i3, CompactHashing.maskCombine(i4, 0, i5));
        setElement(i3, e3);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @VisibleForTesting
    public boolean isUsingHashFloodingResistance() {
        return delegateOrNull() != null;
    }

    public Iterator<E> iterator() {
        Set delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.iterator() : new Iterator<E>() {
            int currentIndex;
            int expectedMetadata;
            int indexToRemove = -1;

            {
                this.expectedMetadata = CompactHashSet.this.metadata;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.metadata != this.expectedMetadata) {
                    throw new ConcurrentModificationException();
                }
            }

            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            public void incrementExpectedModCount() {
                this.expectedMetadata += 32;
            }

            @ParametricNullness
            public E next() {
                checkForConcurrentModification();
                if (hasNext()) {
                    int i3 = this.currentIndex;
                    this.indexToRemove = i3;
                    E access$100 = CompactHashSet.this.element(i3);
                    this.currentIndex = CompactHashSet.this.getSuccessor(this.currentIndex);
                    return access$100;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                incrementExpectedModCount();
                CompactHashSet compactHashSet = CompactHashSet.this;
                compactHashSet.remove(compactHashSet.element(this.indexToRemove));
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }
        };
    }

    public void moveLastEntry(int i3, int i4) {
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        Object[] requireElements = requireElements();
        int size2 = size();
        int i5 = size2 - 1;
        if (i3 < i5) {
            Object obj = requireElements[i5];
            requireElements[i3] = obj;
            requireElements[i5] = null;
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
            requireElements[i3] = null;
            requireEntries[i3] = 0;
        }
    }

    @VisibleForTesting
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    @CanIgnoreReturnValue
    public boolean remove(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        int hashTableMask = hashTableMask();
        int remove = CompactHashing.remove(obj, (Object) null, hashTableMask, requireTable(), requireEntries(), requireElements(), (Object[]) null);
        if (remove == -1) {
            return false;
        }
        moveLastEntry(remove, hashTableMask);
        this.size--;
        incrementModCount();
        return true;
    }

    public void resizeEntries(int i3) {
        this.entries = Arrays.copyOf(requireEntries(), i3);
        this.elements = Arrays.copyOf(requireElements(), i3);
    }

    public int size() {
        Set delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        Set delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.toArray() : Arrays.copyOf(requireElements(), this.size);
    }

    public void trimToSize() {
        if (!needsAllocArrays()) {
            Set delegateOrNull = delegateOrNull();
            if (delegateOrNull != null) {
                Set createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
                createHashFloodingResistantDelegate.addAll(delegateOrNull);
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

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    public CompactHashSet(int i3) {
        init(i3);
    }

    @SafeVarargs
    public static <E> CompactHashSet<E> create(E... eArr) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        if (needsAllocArrays()) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        Set delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.toArray(tArr);
        }
        return ObjectArrays.toArrayImpl(requireElements(), 0, this.size, tArr);
    }
}
