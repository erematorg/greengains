package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
class ObjectCountLinkedHashMap<K> extends ObjectCountHashMap<K> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;
    @VisibleForTesting
    transient long[] links;

    public ObjectCountLinkedHashMap() {
        this(3);
    }

    public static <K> ObjectCountLinkedHashMap<K> create() {
        return new ObjectCountLinkedHashMap<>();
    }

    public static <K> ObjectCountLinkedHashMap<K> createWithExpectedSize(int i3) {
        return new ObjectCountLinkedHashMap<>(i3);
    }

    private int getPredecessor(int i3) {
        return (int) (this.links[i3] >>> 32);
    }

    private int getSuccessor(int i3) {
        return (int) this.links[i3];
    }

    private void setPredecessor(int i3, int i4) {
        long[] jArr = this.links;
        jArr[i3] = (jArr[i3] & 4294967295L) | (((long) i4) << 32);
    }

    private void setSucceeds(int i3, int i4) {
        if (i3 == -2) {
            this.firstEntry = i4;
        } else {
            setSuccessor(i3, i4);
        }
        if (i4 == -2) {
            this.lastEntry = i3;
        } else {
            setPredecessor(i4, i3);
        }
    }

    private void setSuccessor(int i3, int i4) {
        long[] jArr = this.links;
        jArr[i3] = (jArr[i3] & -4294967296L) | (((long) i4) & 4294967295L);
    }

    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    public int firstIndex() {
        int i3 = this.firstEntry;
        if (i3 == -2) {
            return -1;
        }
        return i3;
    }

    public void init(int i3, float f2) {
        super.init(i3, f2);
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = new long[i3];
        this.links = jArr;
        Arrays.fill(jArr, -1);
    }

    public void insertEntry(int i3, @ParametricNullness K k2, int i4, int i5) {
        super.insertEntry(i3, k2, i4, i5);
        setSucceeds(this.lastEntry, i3);
        setSucceeds(i3, -2);
    }

    public void moveLastEntry(int i3) {
        int size = size() - 1;
        setSucceeds(getPredecessor(i3), getSuccessor(i3));
        if (i3 < size) {
            setSucceeds(getPredecessor(size), i3);
            setSucceeds(i3, getSuccessor(size));
        }
        super.moveLastEntry(i3);
    }

    public int nextIndex(int i3) {
        int successor = getSuccessor(i3);
        if (successor == -2) {
            return -1;
        }
        return successor;
    }

    public int nextIndexAfterRemove(int i3, int i4) {
        return i3 == size() ? i4 : i3;
    }

    public void resizeEntries(int i3) {
        super.resizeEntries(i3);
        long[] jArr = this.links;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i3);
        this.links = copyOf;
        Arrays.fill(copyOf, length, i3, -1);
    }

    public ObjectCountLinkedHashMap(int i3) {
        this(i3, 1.0f);
    }

    public ObjectCountLinkedHashMap(int i3, float f2) {
        super(i3, f2);
    }

    public ObjectCountLinkedHashMap(ObjectCountHashMap<K> objectCountHashMap) {
        init(objectCountHashMap.size(), 1.0f);
        int firstIndex = objectCountHashMap.firstIndex();
        while (firstIndex != -1) {
            put(objectCountHashMap.getKey(firstIndex), objectCountHashMap.getValue(firstIndex));
            firstIndex = objectCountHashMap.nextIndex(firstIndex);
        }
    }
}
