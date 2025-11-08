package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.primitives.ImmutableLongArray;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.internal.url._UrlKt;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public class AtomicDoubleArray implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLongArray longs;

    public AtomicDoubleArray(int i3) {
        this.longs = new AtomicLongArray(i3);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
        for (int i3 = 0; i3 < readInt; i3++) {
            builder.add(Double.doubleToRawLongBits(objectInputStream.readDouble()));
        }
        this.longs = new AtomicLongArray(builder.build().toArray());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int length = length();
        objectOutputStream.writeInt(length);
        for (int i3 = 0; i3 < length; i3++) {
            objectOutputStream.writeDouble(get(i3));
        }
    }

    @CanIgnoreReturnValue
    public double addAndGet(int i3, double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.longs.get(i3);
            longBitsToDouble = Double.longBitsToDouble(j2) + d2;
        } while (!this.longs.compareAndSet(i3, j2, Double.doubleToRawLongBits(longBitsToDouble)));
        return longBitsToDouble;
    }

    public final boolean compareAndSet(int i3, double d2, double d3) {
        return this.longs.compareAndSet(i3, Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public final double get(int i3) {
        return Double.longBitsToDouble(this.longs.get(i3));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(int i3, double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.longs.get(i3);
            longBitsToDouble = Double.longBitsToDouble(j2);
        } while (!this.longs.compareAndSet(i3, j2, Double.doubleToRawLongBits(longBitsToDouble + d2)));
        return longBitsToDouble;
    }

    public final double getAndSet(int i3, double d2) {
        return Double.longBitsToDouble(this.longs.getAndSet(i3, Double.doubleToRawLongBits(d2)));
    }

    public final void lazySet(int i3, double d2) {
        this.longs.lazySet(i3, Double.doubleToRawLongBits(d2));
    }

    public final int length() {
        return this.longs.length();
    }

    public final void set(int i3, double d2) {
        this.longs.set(i3, Double.doubleToRawLongBits(d2));
    }

    public String toString() {
        int length = length();
        int i3 = length - 1;
        if (i3 == -1) {
            return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder(length * 19);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int i4 = 0;
        while (true) {
            sb.append(Double.longBitsToDouble(this.longs.get(i4)));
            if (i4 == i3) {
                sb.append(AbstractJsonLexerKt.END_LIST);
                return sb.toString();
            }
            sb.append(", ");
            i4++;
        }
    }

    public final boolean weakCompareAndSet(int i3, double d2, double d3) {
        return this.longs.weakCompareAndSet(i3, Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public AtomicDoubleArray(double[] dArr) {
        int length = dArr.length;
        long[] jArr = new long[length];
        for (int i3 = 0; i3 < length; i3++) {
            jArr[i3] = Double.doubleToRawLongBits(dArr[i3]);
        }
        this.longs = new AtomicLongArray(jArr);
    }
}
