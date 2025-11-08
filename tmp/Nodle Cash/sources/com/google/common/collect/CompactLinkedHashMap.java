package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    private static final int ENDPOINT = -2;
    private final boolean accessOrder;
    private transient int firstEntry;
    private transient int lastEntry;
    @CheckForNull
    @VisibleForTesting
    transient long[] links;

    public CompactLinkedHashMap() {
        this(3);
    }

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int i3) {
        return new CompactLinkedHashMap<>(i3);
    }

    private int getPredecessor(int i3) {
        return ((int) (link(i3) >>> 32)) - 1;
    }

    private long link(int i3) {
        return requireLinks()[i3];
    }

    private long[] requireLinks() {
        long[] jArr = this.links;
        Objects.requireNonNull(jArr);
        return jArr;
    }

    private void setLink(int i3, long j2) {
        requireLinks()[i3] = j2;
    }

    private void setPredecessor(int i3, int i4) {
        setLink(i3, (link(i3) & 4294967295L) | (((long) (i4 + 1)) << 32));
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
        setLink(i3, (link(i3) & -4294967296L) | (((long) (i4 + 1)) & 4294967295L));
    }

    public void accessEntry(int i3) {
        if (this.accessOrder) {
            setSucceeds(getPredecessor(i3), getSuccessor(i3));
            setSucceeds(this.lastEntry, i3);
            setSucceeds(i3, -2);
            incrementModCount();
        }
    }

    public int adjustAfterRemove(int i3, int i4) {
        return i3 >= size() ? i4 : i3;
    }

    public int allocArrays() {
        int allocArrays = super.allocArrays();
        this.links = new long[allocArrays];
        return allocArrays;
    }

    public void clear() {
        if (!needsAllocArrays()) {
            this.firstEntry = -2;
            this.lastEntry = -2;
            long[] jArr = this.links;
            if (jArr != null) {
                Arrays.fill(jArr, 0, size(), 0);
            }
            super.clear();
        }
    }

    @CanIgnoreReturnValue
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> convertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.links = null;
        return convertToHashFloodingResistantImplementation;
    }

    public Map<K, V> createHashFloodingResistantDelegate(int i3) {
        return new LinkedHashMap(i3, 1.0f, this.accessOrder);
    }

    public int firstEntryIndex() {
        return this.firstEntry;
    }

    public int getSuccessor(int i3) {
        return ((int) link(i3)) - 1;
    }

    public void init(int i3) {
        super.init(i3);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    public void insertEntry(int i3, @ParametricNullness K k2, @ParametricNullness V v2, int i4, int i5) {
        super.insertEntry(i3, k2, v2, i4, i5);
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
        setLink(size, 0);
    }

    public void resizeEntries(int i3) {
        super.resizeEntries(i3);
        this.links = Arrays.copyOf(requireLinks(), i3);
    }

    public CompactLinkedHashMap(int i3) {
        this(i3, false);
    }

    public CompactLinkedHashMap(int i3, boolean z2) {
        super(i3);
        this.accessOrder = z2;
    }
}
