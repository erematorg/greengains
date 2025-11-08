package com.google.common.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

@ElementTypesAreNonnullByDefault
public class AtomicDouble extends Number implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLong value;

    public AtomicDouble(double d2) {
        this.value = new AtomicLong(Double.doubleToRawLongBits(d2));
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.value = new AtomicLong();
        set(objectInputStream.readDouble());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeDouble(get());
    }

    @CanIgnoreReturnValue
    public final double addAndGet(double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.value.get();
            longBitsToDouble = Double.longBitsToDouble(j2) + d2;
        } while (!this.value.compareAndSet(j2, Double.doubleToRawLongBits(longBitsToDouble)));
        return longBitsToDouble;
    }

    public final boolean compareAndSet(double d2, double d3) {
        return this.value.compareAndSet(Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public double doubleValue() {
        return get();
    }

    public float floatValue() {
        return (float) get();
    }

    public final double get() {
        return Double.longBitsToDouble(this.value.get());
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.value.get();
            longBitsToDouble = Double.longBitsToDouble(j2);
        } while (!this.value.compareAndSet(j2, Double.doubleToRawLongBits(longBitsToDouble + d2)));
        return longBitsToDouble;
    }

    public final double getAndSet(double d2) {
        return Double.longBitsToDouble(this.value.getAndSet(Double.doubleToRawLongBits(d2)));
    }

    public int intValue() {
        return (int) get();
    }

    public final void lazySet(double d2) {
        this.value.lazySet(Double.doubleToRawLongBits(d2));
    }

    public long longValue() {
        return (long) get();
    }

    public final void set(double d2) {
        this.value.set(Double.doubleToRawLongBits(d2));
    }

    public String toString() {
        return Double.toString(get());
    }

    public final boolean weakCompareAndSet(double d2, double d3) {
        return this.value.weakCompareAndSet(Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public AtomicDouble() {
        this(0.0d);
    }
}
