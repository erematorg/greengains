package com.google.common.hash;

import androidx.constraintlayout.core.state.b;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();

    /* renamed from: K0  reason: collision with root package name */
    private static final long f6911K0 = -4348849565147123417L;
    private static final long K1 = -5435081209227447693L;
    private static final long K2 = -7286425919675154353L;

    @VisibleForTesting
    public static long fingerprint(byte[] bArr, int i3, int i4) {
        return i4 <= 32 ? i4 <= 16 ? hashLength0to16(bArr, i3, i4) : hashLength17to32(bArr, i3, i4) : i4 <= 64 ? hashLength33To64(bArr, i3, i4) : hashLength65Plus(bArr, i3, i4);
    }

    private static long hashLength0to16(byte[] bArr, int i3, int i4) {
        if (i4 >= 8) {
            long j2 = (((long) i4) * 2) + K2;
            long load64 = LittleEndianByteArray.load64(bArr, i3) + K2;
            long load642 = LittleEndianByteArray.load64(bArr, (i3 + i4) - 8);
            return hashLength16((Long.rotateRight(load642, 37) * j2) + load64, (Long.rotateRight(load64, 25) + load642) * j2, j2);
        } else if (i4 >= 4) {
            return hashLength16(((long) i4) + ((((long) LittleEndianByteArray.load32(bArr, i3)) & 4294967295L) << 3), ((long) LittleEndianByteArray.load32(bArr, (i3 + i4) - 4)) & 4294967295L, ((long) (i4 * 2)) + K2);
        } else if (i4 <= 0) {
            return K2;
        } else {
            return shiftMix((((long) ((bArr[i3] & 255) + ((bArr[(i4 >> 1) + i3] & 255) << 8))) * K2) ^ (((long) (i4 + ((bArr[(i4 - 1) + i3] & 255) << 2))) * f6911K0)) * K2;
        }
    }

    private static long hashLength16(long j2, long j3, long j4) {
        long j5 = (j2 ^ j3) * j4;
        long j6 = ((j5 ^ (j5 >>> 47)) ^ j3) * j4;
        return (j6 ^ (j6 >>> 47)) * j4;
    }

    private static long hashLength17to32(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = bArr;
        int i5 = i4;
        long j2 = (((long) i5) * 2) + K2;
        long load64 = LittleEndianByteArray.load64(bArr, i3) * K1;
        long load642 = LittleEndianByteArray.load64(bArr2, i3 + 8);
        int i6 = i3 + i5;
        long load643 = LittleEndianByteArray.load64(bArr2, i6 - 8) * j2;
        return hashLength16((LittleEndianByteArray.load64(bArr2, i6 - 16) * K2) + Long.rotateRight(load643, 30) + Long.rotateRight(load64 + load642, 43), load643 + Long.rotateRight(load642 + K2, 18) + load64, j2);
    }

    private static long hashLength33To64(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = bArr;
        int i5 = i4;
        long j2 = (((long) i5) * 2) + K2;
        long load64 = LittleEndianByteArray.load64(bArr, i3) * K2;
        long load642 = LittleEndianByteArray.load64(bArr2, i3 + 8);
        int i6 = i3 + i5;
        long load643 = LittleEndianByteArray.load64(bArr2, i6 - 8) * j2;
        long rotateRight = Long.rotateRight(load643, 30) + Long.rotateRight(load64 + load642, 43) + (LittleEndianByteArray.load64(bArr2, i6 - 16) * K2);
        long hashLength16 = hashLength16(rotateRight, load643 + Long.rotateRight(load642 + K2, 18) + load64, j2);
        long load644 = LittleEndianByteArray.load64(bArr2, i3 + 16) * j2;
        long load645 = LittleEndianByteArray.load64(bArr2, i3 + 24);
        long load646 = (rotateRight + LittleEndianByteArray.load64(bArr2, i6 - 32)) * j2;
        return hashLength16(((hashLength16 + LittleEndianByteArray.load64(bArr2, i6 - 24)) * j2) + Long.rotateRight(load646, 30) + Long.rotateRight(load644 + load645, 43), Long.rotateRight(load645 + load64, 18) + load644 + load646, j2);
    }

    private static long hashLength65Plus(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = bArr;
        long j2 = (long) 81;
        long j3 = (j2 * K1) + 113;
        long shiftMix = shiftMix((j3 * K2) + 113) * K2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long load64 = (j2 * K2) + LittleEndianByteArray.load64(bArr, i3);
        int i5 = i4 - 1;
        int i6 = i3;
        int z2 = b.z(i5, 64, 64, i6);
        int i7 = i5 & 63;
        int i8 = z2 + i7;
        int i9 = i8 - 63;
        while (true) {
            long rotateRight = Long.rotateRight(load64 + j3 + jArr[0] + LittleEndianByteArray.load64(bArr2, i6 + 8), 37) * K1;
            long rotateRight2 = Long.rotateRight(j3 + jArr[1] + LittleEndianByteArray.load64(bArr2, i6 + 48), 42) * K1;
            long j4 = rotateRight ^ jArr2[1];
            long load642 = jArr[0] + LittleEndianByteArray.load64(bArr2, i6 + 40) + rotateRight2;
            long rotateRight3 = Long.rotateRight(shiftMix + jArr2[0], 33) * K1;
            int i10 = i7;
            weakHashLength32WithSeeds(bArr, i6, jArr[1] * K1, j4 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i6 + 32, rotateRight3 + jArr2[1], load642 + LittleEndianByteArray.load64(bArr2, i6 + 16), jArr2);
            i6 += 64;
            if (i6 == z2) {
                long j5 = K1 + ((j4 & 255) << 1);
                long j6 = jArr2[0] + ((long) i10);
                jArr2[0] = j6;
                long j7 = jArr[0] + j6;
                jArr[0] = j7;
                jArr2[0] = jArr2[0] + j7;
                long rotateRight4 = (Long.rotateRight(((rotateRight3 + load642) + jArr[0]) + LittleEndianByteArray.load64(bArr2, i8 - 55), 37) * j5) ^ (jArr2[1] * 9);
                long load643 = (jArr[0] * 9) + LittleEndianByteArray.load64(bArr2, i8 - 23) + (Long.rotateRight(load642 + jArr[1] + LittleEndianByteArray.load64(bArr2, i8 - 15), 42) * j5);
                long rotateRight5 = Long.rotateRight(j4 + jArr2[0], 33) * j5;
                weakHashLength32WithSeeds(bArr, i9, jArr[1] * j5, rotateRight4 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr, i8 - 31, rotateRight5 + jArr2[1], LittleEndianByteArray.load64(bArr2, i8 - 47) + load643, jArr2);
                long j8 = j5;
                return hashLength16(b.e(shiftMix(load643), f6911K0, hashLength16(jArr[0], jArr2[0], j8), rotateRight4), hashLength16(jArr[1], jArr2[1], j8) + rotateRight5, j8);
            }
            i7 = i10;
            shiftMix = j4;
            j3 = load642;
            load64 = rotateRight3;
        }
    }

    private static long shiftMix(long j2) {
        return j2 ^ (j2 >>> 47);
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i3, long j2, long j3, long[] jArr) {
        long load64 = LittleEndianByteArray.load64(bArr, i3);
        long load642 = LittleEndianByteArray.load64(bArr, i3 + 8);
        long load643 = LittleEndianByteArray.load64(bArr, i3 + 16);
        long load644 = LittleEndianByteArray.load64(bArr, i3 + 24);
        long j4 = j2 + load64;
        long rotateRight = Long.rotateRight(j3 + j4 + load644, 21);
        long j5 = load642 + j4 + load643;
        jArr[0] = j5 + load644;
        jArr[1] = Long.rotateRight(j5, 44) + rotateRight + j4;
    }

    public int bits() {
        return 64;
    }

    public HashCode hashBytes(byte[] bArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, i3, i4));
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }
}
