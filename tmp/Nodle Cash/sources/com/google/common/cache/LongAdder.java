package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.Striped64;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
final class LongAdder extends Striped64 implements Serializable, LongAddable {
    private static final long serialVersionUID = 7249069246863182397L;

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.busy = 0;
        this.cells = null;
        this.base = objectInputStream.readLong();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    public void add(long j2) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr == null) {
            long j3 = this.base;
            if (casBase(j3, j3 + j2)) {
                return;
            }
        }
        int[] iArr = Striped64.threadHashCode.get();
        boolean z2 = true;
        if (!(iArr == null || cellArr == null || (length = cellArr.length) < 1 || (cell = cellArr[(length - 1) & iArr[0]]) == null)) {
            long j4 = cell.value;
            z2 = cell.cas(j4, j4 + j2);
            if (z2) {
                return;
            }
        }
        retryUpdate(j2, iArr, z2);
    }

    public void decrement() {
        add(-1);
    }

    public double doubleValue() {
        return (double) sum();
    }

    public float floatValue() {
        return (float) sum();
    }

    public final long fn(long j2, long j3) {
        return j2 + j3;
    }

    public void increment() {
        add(1);
    }

    public int intValue() {
        return (int) sum();
    }

    public long longValue() {
        return sum();
    }

    public void reset() {
        internalReset(0);
    }

    public long sum() {
        long j2 = this.base;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.value;
                }
            }
        }
        return j2;
    }

    public long sumThenReset() {
        long j2 = this.base;
        Striped64.Cell[] cellArr = this.cells;
        this.base = 0;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.value;
                    cell.value = 0;
                }
            }
        }
        return j2;
    }

    public String toString() {
        return Long.toString(sum());
    }
}
