package com.appsamurai.storyly.exoplayer2.common.util;

import androidx.annotation.Nullable;
import java.util.Arrays;

public final class TimedValueQueue<V> {
    private static final int INITIAL_BUFFER_SIZE = 10;
    private int first;
    private int size;
    private long[] timestamps;
    private V[] values;

    public TimedValueQueue() {
        this(10);
    }

    private void addUnchecked(long j2, V v2) {
        int i3 = this.first;
        int i4 = this.size;
        V[] vArr = this.values;
        int length = (i3 + i4) % vArr.length;
        this.timestamps[length] = j2;
        vArr[length] = v2;
        this.size = i4 + 1;
    }

    private void clearBufferOnTimeDiscontinuity(long j2) {
        int i3 = this.size;
        if (i3 > 0) {
            if (j2 <= this.timestamps[((this.first + i3) - 1) % this.values.length]) {
                clear();
            }
        }
    }

    private void doubleCapacityIfFull() {
        int length = this.values.length;
        if (this.size >= length) {
            int i3 = length * 2;
            long[] jArr = new long[i3];
            V[] newArray = newArray(i3);
            int i4 = this.first;
            int i5 = length - i4;
            System.arraycopy(this.timestamps, i4, jArr, 0, i5);
            System.arraycopy(this.values, this.first, newArray, 0, i5);
            int i6 = this.first;
            if (i6 > 0) {
                System.arraycopy(this.timestamps, 0, jArr, i5, i6);
                System.arraycopy(this.values, 0, newArray, i5, this.first);
            }
            this.timestamps = jArr;
            this.values = newArray;
            this.first = 0;
        }
    }

    private static <V> V[] newArray(int i3) {
        return new Object[i3];
    }

    @Nullable
    private V popFirst() {
        Assertions.checkState(this.size > 0);
        V[] vArr = this.values;
        int i3 = this.first;
        V v2 = vArr[i3];
        vArr[i3] = null;
        this.first = (i3 + 1) % vArr.length;
        this.size--;
        return v2;
    }

    public synchronized void add(long j2, V v2) {
        clearBufferOnTimeDiscontinuity(j2);
        doubleCapacityIfFull();
        addUnchecked(j2, v2);
    }

    public synchronized void clear() {
        this.first = 0;
        this.size = 0;
        Arrays.fill(this.values, (Object) null);
    }

    @Nullable
    public synchronized V poll(long j2) {
        return poll(j2, false);
    }

    @Nullable
    public synchronized V pollFirst() {
        return this.size == 0 ? null : popFirst();
    }

    @Nullable
    public synchronized V pollFloor(long j2) {
        return poll(j2, true);
    }

    public synchronized int size() {
        return this.size;
    }

    public TimedValueQueue(int i3) {
        this.timestamps = new long[i3];
        this.values = newArray(i3);
    }

    @Nullable
    private V poll(long j2, boolean z2) {
        V v2 = null;
        long j3 = Long.MAX_VALUE;
        while (this.size > 0) {
            long j4 = j2 - this.timestamps[this.first];
            if (j4 < 0 && (z2 || (-j4) >= j3)) {
                break;
            }
            v2 = popFirst();
            j3 = j4;
        }
        return v2;
    }
}
