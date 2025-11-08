package com.appsamurai.storyly.exoplayer2.common.util;

import A.a;
import java.util.Arrays;

public final class LongArray {
    private static final int DEFAULT_INITIAL_CAPACITY = 32;
    private int size;
    private long[] values;

    public LongArray() {
        this(32);
    }

    public void add(long j2) {
        int i3 = this.size;
        long[] jArr = this.values;
        if (i3 == jArr.length) {
            this.values = Arrays.copyOf(jArr, i3 * 2);
        }
        long[] jArr2 = this.values;
        int i4 = this.size;
        this.size = i4 + 1;
        jArr2[i4] = j2;
    }

    public long get(int i3) {
        if (i3 >= 0 && i3 < this.size) {
            return this.values[i3];
        }
        StringBuilder o3 = a.o(i3, "Invalid index ", ", size is ");
        o3.append(this.size);
        throw new IndexOutOfBoundsException(o3.toString());
    }

    public int size() {
        return this.size;
    }

    public long[] toArray() {
        return Arrays.copyOf(this.values, this.size);
    }

    public LongArray(int i3) {
        this.values = new long[i3];
    }
}
