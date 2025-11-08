package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
class CompactLinkedHashSet<E> extends CompactHashSet<E> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;
    @CheckForNull
    private transient int[] predecessor;
    @CheckForNull
    private transient int[] successor;

    public CompactLinkedHashSet() {
    }

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<>();
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int i3) {
        return new CompactLinkedHashSet<>(i3);
    }

    private int getPredecessor(int i3) {
        return requirePredecessors()[i3] - 1;
    }

    private int[] requirePredecessors() {
        int[] iArr = this.predecessor;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    private int[] requireSuccessors() {
        int[] iArr = this.successor;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    private void setPredecessor(int i3, int i4) {
        requirePredecessors()[i3] = i4 + 1;
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
        requireSuccessors()[i3] = i4 + 1;
    }

    public int adjustAfterRemove(int i3, int i4) {
        return i3 >= size() ? i4 : i3;
    }

    public int allocArrays() {
        int allocArrays = super.allocArrays();
        this.predecessor = new int[allocArrays];
        this.successor = new int[allocArrays];
        return allocArrays;
    }

    public void clear() {
        if (!needsAllocArrays()) {
            this.firstEntry = -2;
            this.lastEntry = -2;
            int[] iArr = this.predecessor;
            if (!(iArr == null || this.successor == null)) {
                Arrays.fill(iArr, 0, size(), 0);
                Arrays.fill(this.successor, 0, size(), 0);
            }
            super.clear();
        }
    }

    @CanIgnoreReturnValue
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> convertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.predecessor = null;
        this.successor = null;
        return convertToHashFloodingResistantImplementation;
    }

    public int firstEntryIndex() {
        return this.firstEntry;
    }

    public int getSuccessor(int i3) {
        return requireSuccessors()[i3] - 1;
    }

    public void init(int i3) {
        super.init(i3);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    public void insertEntry(int i3, @ParametricNullness E e3, int i4, int i5) {
        super.insertEntry(i3, e3, i4, i5);
        setSucceeds(this.lastEntry, i3);
        setSucceeds(i3, -2);
    }

    public void moveLastEntry(int i3, int i4) {
        int size = size() - 1;
        super.moveLastEntry(i3, i4);
        setSucceeds(getPredecessor(i3), getSuccessor(i3));
        if (i3 < size) {
            setSucceeds(getPredecessor(size), i3);
            setSucceeds(i3, getSuccessor(size));
        }
        requirePredecessors()[size] = 0;
        requireSuccessors()[size] = 0;
    }

    public void resizeEntries(int i3) {
        super.resizeEntries(i3);
        this.predecessor = Arrays.copyOf(requirePredecessors(), i3);
        this.successor = Arrays.copyOf(requireSuccessors(), i3);
    }

    public Object[] toArray() {
        return ObjectArrays.toArrayImpl(this);
    }

    public CompactLinkedHashSet(int i3) {
        super(i3);
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    public <T> T[] toArray(T[] tArr) {
        return ObjectArrays.toArrayImpl(this, tArr);
    }

    @SafeVarargs
    public static <E> CompactLinkedHashSet<E> create(E... eArr) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }
}
