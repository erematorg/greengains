package com.google.common.hash;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.ui.autofill.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.math.DoubleMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    /* access modifiers changed from: private */
    public final BloomFilterStrategies.LockFreeBitArray bits;
    /* access modifiers changed from: private */
    public final Funnel<? super T> funnel;
    /* access modifiers changed from: private */
    public final int numHashFunctions;
    /* access modifiers changed from: private */
    public final Strategy strategy;

    public static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] data;
        final Funnel<? super T> funnel;
        final int numHashFunctions;
        final Strategy strategy;

        public SerialForm(BloomFilter<T> bloomFilter) {
            this.data = BloomFilterStrategies.LockFreeBitArray.toPlainArray(bloomFilter.bits.data);
            this.numHashFunctions = bloomFilter.numHashFunctions;
            this.funnel = bloomFilter.funnel;
            this.strategy = bloomFilter.strategy;
        }

        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    public interface Strategy extends Serializable {
        <T> boolean mightContain(@ParametricNullness T t2, Funnel<? super T> funnel, int i3, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);

        int ordinal();

        <T> boolean put(@ParametricNullness T t2, Funnel<? super T> funnel, int i3, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i3, double d2) {
        return create(funnel2, (long) i3, d2);
    }

    @VisibleForTesting
    public static long optimalNumOfBits(long j2, double d2) {
        if (d2 == 0.0d) {
            d2 = Double.MIN_VALUE;
        }
        return (long) ((Math.log(d2) * ((double) (-j2))) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    @VisibleForTesting
    public static int optimalNumOfHashFunctions(long j2, long j3) {
        return Math.max(1, (int) Math.round(Math.log(2.0d) * (((double) j3) / ((double) j2))));
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<? super T> funnel2) throws IOException {
        int i3;
        int i4;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel2, "Funnel");
        byte b3 = -1;
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte readByte = dataInputStream.readByte();
            try {
                i4 = UnsignedBytes.toInt(dataInputStream.readByte());
                try {
                    int readInt = dataInputStream.readInt();
                    BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[readByte];
                    BloomFilterStrategies.LockFreeBitArray lockFreeBitArray = new BloomFilterStrategies.LockFreeBitArray(LongMath.checkedMultiply((long) readInt, 64));
                    for (int i5 = 0; i5 < readInt; i5++) {
                        lockFreeBitArray.putData(i5, dataInputStream.readLong());
                    }
                    return new BloomFilter<>(lockFreeBitArray, i4, funnel2, bloomFilterStrategies);
                } catch (RuntimeException e3) {
                    e = e3;
                    b3 = readByte;
                    i3 = -1;
                    StringBuilder k2 = C0118y.k(b3, i4, "Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ", " numHashFunctions: ", " dataLength: ");
                    k2.append(i3);
                    throw new IOException(k2.toString(), e);
                }
            } catch (RuntimeException e4) {
                e = e4;
                i4 = -1;
                b3 = readByte;
                i3 = -1;
                StringBuilder k22 = C0118y.k(b3, i4, "Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ", " numHashFunctions: ", " dataLength: ");
                k22.append(i3);
                throw new IOException(k22.toString(), e);
            }
        } catch (RuntimeException e5) {
            e = e5;
            i3 = -1;
            i4 = -1;
            StringBuilder k222 = C0118y.k(b3, i4, "Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ", " numHashFunctions: ", " dataLength: ");
            k222.append(i3);
            throw new IOException(k222.toString(), e);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    @Deprecated
    public boolean apply(@ParametricNullness T t2) {
        return mightContain(t2);
    }

    public long approximateElementCount() {
        double bitSize = (double) this.bits.bitSize();
        return DoubleMath.roundToLong(((-Math.log1p(-(((double) this.bits.bitCount()) / bitSize))) * bitSize) / ((double) this.numHashFunctions), RoundingMode.HALF_UP);
    }

    @VisibleForTesting
    public long bitSize() {
        return this.bits.bitSize();
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        return this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.bitCount()) / ((double) bitSize()), (double) this.numHashFunctions);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && bitSize() == bloomFilter.bitSize() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public boolean mightContain(@ParametricNullness T t2) {
        return this.strategy.mightContain(t2, this.funnel, this.numHashFunctions, this.bits);
    }

    @CanIgnoreReturnValue
    public boolean put(@ParametricNullness T t2) {
        return this.strategy.put(t2, this.funnel, this.numHashFunctions, this.bits);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        int i3 = this.numHashFunctions;
        int i4 = bloomFilter.numHashFunctions;
        Preconditions.checkArgument(i3 == i4, "BloomFilters must have the same number of hash functions (%s != %s)", i3, i4);
        Preconditions.checkArgument(bitSize() == bloomFilter.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", bitSize(), bloomFilter.bitSize());
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", (Object) this.strategy, (Object) bloomFilter.strategy);
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", (Object) this.funnel, (Object) bloomFilter.funnel);
        this.bits.putAll(bloomFilter.bits);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast((long) this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast((long) this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length());
        for (int i3 = 0; i3 < this.bits.data.length(); i3++) {
            dataOutputStream.writeLong(this.bits.data.get(i3));
        }
    }

    private BloomFilter(BloomFilterStrategies.LockFreeBitArray lockFreeBitArray, int i3, Funnel<? super T> funnel2, Strategy strategy2) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 > 0, "numHashFunctions (%s) must be > 0", i3);
        Preconditions.checkArgument(i3 <= 255 ? true : z2, "numHashFunctions (%s) must be <= 255", i3);
        this.bits = (BloomFilterStrategies.LockFreeBitArray) Preconditions.checkNotNull(lockFreeBitArray);
        this.numHashFunctions = i3;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel2);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy2);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j2, double d2) {
        return create(funnel2, j2, d2, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @VisibleForTesting
    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j2, double d2, Strategy strategy2) {
        Preconditions.checkNotNull(funnel2);
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Expected insertions (%s) must be >= 0", j2);
        Preconditions.checkArgument(d2 > 0.0d, "False positive probability (%s) must be > 0.0", (Object) Double.valueOf(d2));
        if (d2 < 1.0d) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "False positive probability (%s) must be < 1.0", (Object) Double.valueOf(d2));
        Preconditions.checkNotNull(strategy2);
        if (i3 == 0) {
            j2 = 1;
        }
        long optimalNumOfBits = optimalNumOfBits(j2, d2);
        try {
            return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(optimalNumOfBits), optimalNumOfHashFunctions(j2, optimalNumOfBits), funnel2, strategy2);
        } catch (IllegalArgumentException e3) {
            throw new IllegalArgumentException(a.j("Could not create BloomFilter of ", optimalNumOfBits, " bits"), e3);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i3) {
        return create(funnel2, (long) i3);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j2) {
        return create(funnel2, j2, 0.03d);
    }
}
