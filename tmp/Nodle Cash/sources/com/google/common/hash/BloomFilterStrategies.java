package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
enum BloomFilterStrategies implements BloomFilter.Strategy {
    MURMUR128_MITZ_32 {
        public <T> boolean mightContain(@ParametricNullness T t2, Funnel<? super T> funnel, int i3, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t2, funnel).asLong();
            int i4 = (int) asLong;
            int i5 = (int) (asLong >>> 32);
            for (int i6 = 1; i6 <= i3; i6++) {
                int i7 = (i6 * i5) + i4;
                if (i7 < 0) {
                    i7 = ~i7;
                }
                if (!lockFreeBitArray.get(((long) i7) % bitSize)) {
                    return false;
                }
            }
            return true;
        }

        public <T> boolean put(@ParametricNullness T t2, Funnel<? super T> funnel, int i3, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            long asLong = Hashing.murmur3_128().hashObject(t2, funnel).asLong();
            int i4 = (int) asLong;
            int i5 = (int) (asLong >>> 32);
            boolean z2 = false;
            for (int i6 = 1; i6 <= i3; i6++) {
                int i7 = (i6 * i5) + i4;
                if (i7 < 0) {
                    i7 = ~i7;
                }
                z2 |= lockFreeBitArray.set(((long) i7) % bitSize);
            }
            return z2;
        }
    },
    MURMUR128_MITZ_64 {
        private long lowerEight(byte[] bArr) {
            return Longs.fromBytes(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.fromBytes(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }

        public <T> boolean mightContain(@ParametricNullness T t2, Funnel<? super T> funnel, int i3, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t2, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            for (int i4 = 0; i4 < i3; i4++) {
                if (!lockFreeBitArray.get((Long.MAX_VALUE & lowerEight) % bitSize)) {
                    return false;
                }
                lowerEight += upperEight;
            }
            return true;
        }

        public <T> boolean put(@ParametricNullness T t2, Funnel<? super T> funnel, int i3, LockFreeBitArray lockFreeBitArray) {
            long bitSize = lockFreeBitArray.bitSize();
            byte[] bytesInternal = Hashing.murmur3_128().hashObject(t2, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            long j2 = lowerEight;
            boolean z2 = false;
            for (int i4 = 0; i4 < i3; i4++) {
                z2 |= lockFreeBitArray.set((Long.MAX_VALUE & j2) % bitSize);
                j2 += upperEight;
            }
            return z2;
        }
    };

    public static final class LockFreeBitArray {
        private static final int LONG_ADDRESSABLE_BITS = 6;
        private final LongAddable bitCount;
        final AtomicLongArray data;

        public LockFreeBitArray(long j2) {
            Preconditions.checkArgument(j2 > 0, "data length is zero!");
            this.data = new AtomicLongArray(Ints.checkedCast(LongMath.divide(j2, 64, RoundingMode.CEILING)));
            this.bitCount = LongAddables.create();
        }

        public static long[] toPlainArray(AtomicLongArray atomicLongArray) {
            int length = atomicLongArray.length();
            long[] jArr = new long[length];
            for (int i3 = 0; i3 < length; i3++) {
                jArr[i3] = atomicLongArray.get(i3);
            }
            return jArr;
        }

        public long bitCount() {
            return this.bitCount.sum();
        }

        public long bitSize() {
            return ((long) this.data.length()) * 64;
        }

        public LockFreeBitArray copy() {
            return new LockFreeBitArray(toPlainArray(this.data));
        }

        public int dataLength() {
            return this.data.length();
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof LockFreeBitArray) {
                return Arrays.equals(toPlainArray(this.data), toPlainArray(((LockFreeBitArray) obj).data));
            }
            return false;
        }

        public boolean get(long j2) {
            return ((1 << ((int) j2)) & this.data.get((int) (j2 >>> 6))) != 0;
        }

        public int hashCode() {
            return Arrays.hashCode(toPlainArray(this.data));
        }

        public void putAll(LockFreeBitArray lockFreeBitArray) {
            Preconditions.checkArgument(this.data.length() == lockFreeBitArray.data.length(), "BitArrays must be of equal length (%s != %s)", this.data.length(), lockFreeBitArray.data.length());
            for (int i3 = 0; i3 < this.data.length(); i3++) {
                putData(i3, lockFreeBitArray.data.get(i3));
            }
        }

        public void putData(int i3, long j2) {
            long j3;
            long j4;
            do {
                j3 = this.data.get(i3);
                j4 = j3 | j2;
                if (j3 == j4) {
                    return;
                }
            } while (!this.data.compareAndSet(i3, j3, j4));
            this.bitCount.add((long) (Long.bitCount(j4) - Long.bitCount(j3)));
        }

        public boolean set(long j2) {
            long j3;
            long j4;
            if (get(j2)) {
                return false;
            }
            int i3 = (int) (j2 >>> 6);
            long j5 = 1 << ((int) j2);
            do {
                j3 = this.data.get(i3);
                j4 = j3 | j5;
                if (j3 == j4) {
                    return false;
                }
            } while (!this.data.compareAndSet(i3, j3, j4));
            this.bitCount.increment();
            return true;
        }

        public LockFreeBitArray(long[] jArr) {
            Preconditions.checkArgument(jArr.length > 0, "data length is zero!");
            this.data = new AtomicLongArray(jArr);
            this.bitCount = LongAddables.create();
            long j2 = 0;
            for (long bitCount2 : jArr) {
                j2 += (long) Long.bitCount(bitCount2);
            }
            this.bitCount.add(j2);
        }
    }
}
