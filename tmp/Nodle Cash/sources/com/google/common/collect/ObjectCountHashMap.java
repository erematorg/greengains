package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
class ObjectCountHashMap<K> {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    @VisibleForTesting
    transient long[] entries;
    transient Object[] keys;
    private transient float loadFactor;
    transient int modCount;
    transient int size;
    private transient int[] table;
    private transient int threshold;
    transient int[] values;

    public class MapEntry extends Multisets.AbstractEntry<K> {
        @ParametricNullness
        final K key;
        int lastKnownIndex;

        public MapEntry(int i3) {
            this.key = ObjectCountHashMap.this.keys[i3];
            this.lastKnownIndex = i3;
        }

        public int getCount() {
            updateLastKnownIndex();
            int i3 = this.lastKnownIndex;
            if (i3 == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.values[i3];
        }

        @ParametricNullness
        public K getElement() {
            return this.key;
        }

        @CanIgnoreReturnValue
        public int setCount(int i3) {
            updateLastKnownIndex();
            int i4 = this.lastKnownIndex;
            if (i4 == -1) {
                ObjectCountHashMap.this.put(this.key, i3);
                return 0;
            }
            int[] iArr = ObjectCountHashMap.this.values;
            int i5 = iArr[i4];
            iArr[i4] = i3;
            return i5;
        }

        public void updateLastKnownIndex() {
            int i3 = this.lastKnownIndex;
            if (i3 == -1 || i3 >= ObjectCountHashMap.this.size() || !Objects.equal(this.key, ObjectCountHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = ObjectCountHashMap.this.indexOf(this.key);
            }
        }
    }

    public ObjectCountHashMap() {
        init(3, 1.0f);
    }

    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int i3) {
        return new ObjectCountHashMap<>(i3);
    }

    private static int getHash(long j2) {
        return (int) (j2 >>> 32);
    }

    private static int getNext(long j2) {
        return (int) j2;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    private static long[] newEntries(int i3) {
        long[] jArr = new long[i3];
        Arrays.fill(jArr, -1);
        return jArr;
    }

    private static int[] newTable(int i3) {
        int[] iArr = new int[i3];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private void resizeMeMaybe(int i3) {
        int length = this.entries.length;
        if (i3 > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                resizeEntries(max);
            }
        }
    }

    private void resizeTable(int i3) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i4 = ((int) (((float) i3) * this.loadFactor)) + 1;
        int[] newTable = newTable(i3);
        long[] jArr = this.entries;
        int length = newTable.length - 1;
        for (int i5 = 0; i5 < this.size; i5++) {
            int hash = getHash(jArr[i5]);
            int i6 = hash & length;
            int i7 = newTable[i6];
            newTable[i6] = i5;
            jArr[i5] = (((long) hash) << 32) | (((long) i7) & 4294967295L);
        }
        this.threshold = i4;
        this.table = newTable;
    }

    private static long swapNext(long j2, int i3) {
        return (j2 & HASH_MASK) | (4294967295L & ((long) i3));
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1);
        this.size = 0;
    }

    public boolean containsKey(@CheckForNull Object obj) {
        return indexOf(obj) != -1;
    }

    public void ensureCapacity(int i3) {
        if (i3 > this.entries.length) {
            resizeEntries(i3);
        }
        if (i3 >= this.threshold) {
            resizeTable(Math.max(2, Integer.highestOneBit(i3 - 1) << 1));
        }
    }

    public int firstIndex() {
        return this.size == 0 ? -1 : 0;
    }

    public int get(@CheckForNull Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return 0;
        }
        return this.values[indexOf];
    }

    public Multiset.Entry<K> getEntry(int i3) {
        Preconditions.checkElementIndex(i3, this.size);
        return new MapEntry(i3);
    }

    @ParametricNullness
    public K getKey(int i3) {
        Preconditions.checkElementIndex(i3, this.size);
        return this.keys[i3];
    }

    public int getValue(int i3) {
        Preconditions.checkElementIndex(i3, this.size);
        return this.values[i3];
    }

    public int indexOf(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int i3 = this.table[hashTableMask() & smearedHash];
        while (i3 != -1) {
            long j2 = this.entries[i3];
            if (getHash(j2) == smearedHash && Objects.equal(obj, this.keys[i3])) {
                return i3;
            }
            i3 = getNext(j2);
        }
        return -1;
    }

    public void init(int i3, float f2) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Initial capacity must be non-negative");
        if (f2 > 0.0f) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Illegal load factor");
        int closedTableSize = Hashing.closedTableSize(i3, (double) f2);
        this.table = newTable(closedTableSize);
        this.loadFactor = f2;
        this.keys = new Object[i3];
        this.values = new int[i3];
        this.entries = newEntries(i3);
        this.threshold = Math.max(1, (int) (((float) closedTableSize) * f2));
    }

    public void insertEntry(int i3, @ParametricNullness K k2, int i4, int i5) {
        this.entries[i3] = (((long) i5) << 32) | 4294967295L;
        this.keys[i3] = k2;
        this.values[i3] = i4;
    }

    public void moveLastEntry(int i3) {
        int size2 = size() - 1;
        if (i3 < size2) {
            Object[] objArr = this.keys;
            objArr[i3] = objArr[size2];
            int[] iArr = this.values;
            iArr[i3] = iArr[size2];
            objArr[size2] = null;
            iArr[size2] = 0;
            long[] jArr = this.entries;
            long j2 = jArr[size2];
            jArr[i3] = j2;
            jArr[size2] = -1;
            int hash = getHash(j2) & hashTableMask();
            int[] iArr2 = this.table;
            int i4 = iArr2[hash];
            if (i4 == size2) {
                iArr2[hash] = i3;
                return;
            }
            while (true) {
                long j3 = this.entries[i4];
                int next = getNext(j3);
                if (next == size2) {
                    this.entries[i4] = swapNext(j3, i3);
                    return;
                }
                i4 = next;
            }
        } else {
            this.keys[i3] = null;
            this.values[i3] = 0;
            this.entries[i3] = -1;
        }
    }

    public int nextIndex(int i3) {
        int i4 = i3 + 1;
        if (i4 < this.size) {
            return i4;
        }
        return -1;
    }

    public int nextIndexAfterRemove(int i3, int i4) {
        return i3 - 1;
    }

    @CanIgnoreReturnValue
    public int put(@ParametricNullness K k2, int i3) {
        CollectPreconditions.checkPositive(i3, "count");
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        int[] iArr = this.values;
        int smearedHash = Hashing.smearedHash(k2);
        int hashTableMask = hashTableMask() & smearedHash;
        int i4 = this.size;
        int[] iArr2 = this.table;
        int i5 = iArr2[hashTableMask];
        if (i5 == -1) {
            iArr2[hashTableMask] = i4;
        } else {
            while (true) {
                long j2 = jArr[i5];
                if (getHash(j2) != smearedHash || !Objects.equal(k2, objArr[i5])) {
                    int next = getNext(j2);
                    if (next == -1) {
                        jArr[i5] = swapNext(j2, i4);
                        break;
                    }
                    i5 = next;
                } else {
                    int i6 = iArr[i5];
                    iArr[i5] = i3;
                    return i6;
                }
            }
        }
        if (i4 != Integer.MAX_VALUE) {
            int i7 = i4 + 1;
            resizeMeMaybe(i7);
            insertEntry(i4, k2, i3, smearedHash);
            this.size = i7;
            if (i4 >= this.threshold) {
                resizeTable(this.table.length * 2);
            }
            this.modCount++;
            return 0;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    @CanIgnoreReturnValue
    public int remove(@CheckForNull Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    @CanIgnoreReturnValue
    public int removeEntry(int i3) {
        return remove(this.keys[i3], getHash(this.entries[i3]));
    }

    public void resizeEntries(int i3) {
        this.keys = Arrays.copyOf(this.keys, i3);
        this.values = Arrays.copyOf(this.values, i3);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i3);
        if (i3 > length) {
            Arrays.fill(copyOf, length, i3, -1);
        }
        this.entries = copyOf;
    }

    public void setValue(int i3, int i4) {
        Preconditions.checkElementIndex(i3, this.size);
        this.values[i3] = i4;
    }

    public int size() {
        return this.size;
    }

    private int remove(@CheckForNull Object obj, int i3) {
        int hashTableMask = hashTableMask() & i3;
        int i4 = this.table[hashTableMask];
        if (i4 == -1) {
            return 0;
        }
        int i5 = -1;
        while (true) {
            if (getHash(this.entries[i4]) != i3 || !Objects.equal(obj, this.keys[i4])) {
                int next = getNext(this.entries[i4]);
                if (next == -1) {
                    return 0;
                }
                int i6 = next;
                i5 = i4;
                i4 = i6;
            } else {
                int i7 = this.values[i4];
                if (i5 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[i4]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i5] = swapNext(jArr[i5], getNext(jArr[i4]));
                }
                moveLastEntry(i4);
                this.size--;
                this.modCount++;
                return i7;
            }
        }
    }

    public ObjectCountHashMap(ObjectCountHashMap<? extends K> objectCountHashMap) {
        init(objectCountHashMap.size(), 1.0f);
        int firstIndex = objectCountHashMap.firstIndex();
        while (firstIndex != -1) {
            put(objectCountHashMap.getKey(firstIndex), objectCountHashMap.getValue(firstIndex));
            firstIndex = objectCountHashMap.nextIndex(firstIndex);
        }
    }

    public ObjectCountHashMap(int i3) {
        this(i3, 1.0f);
    }

    public ObjectCountHashMap(int i3, float f2) {
        init(i3, f2);
    }
}
