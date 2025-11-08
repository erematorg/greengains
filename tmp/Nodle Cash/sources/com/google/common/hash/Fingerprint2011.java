package com.google.common.hash;

import androidx.constraintlayout.core.state.b;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
final class Fingerprint2011 extends AbstractNonStreamingHashFunction {
    static final HashFunction FINGERPRINT_2011 = new Fingerprint2011();

    /* renamed from: K0  reason: collision with root package name */
    private static final long f6912K0 = -6505348102511208375L;
    private static final long K1 = -8261664234251669945L;
    private static final long K2 = -4288712594273399085L;
    private static final long K3 = -4132994306676758123L;

    @VisibleForTesting
    public static long fingerprint(byte[] bArr, int i3, int i4) {
        long murmurHash64WithSeed = i4 <= 32 ? murmurHash64WithSeed(bArr, i3, i4, -1397348546323613475L) : i4 <= 64 ? hashLength33To64(bArr, i3, i4) : fullFingerprint(bArr, i3, i4);
        long j2 = f6912K0;
        long load64 = i4 >= 8 ? LittleEndianByteArray.load64(bArr, i3) : -6505348102511208375L;
        if (i4 >= 9) {
            j2 = LittleEndianByteArray.load64(bArr, (i3 + i4) - 8);
        }
        long hash128to64 = hash128to64(murmurHash64WithSeed + j2, load64);
        return (hash128to64 == 0 || hash128to64 == 1) ? hash128to64 - 2 : hash128to64;
    }

    private static long fullFingerprint(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = bArr;
        int i5 = i4;
        long load64 = LittleEndianByteArray.load64(bArr, i3);
        int i6 = i3 + i5;
        long load642 = LittleEndianByteArray.load64(bArr2, i6 - 16) ^ K1;
        long load643 = LittleEndianByteArray.load64(bArr2, i6 - 56) ^ f6912K0;
        long[] jArr = new long[2];
        long j2 = (long) i5;
        byte[] bArr3 = bArr;
        long[] jArr2 = new long[2];
        long[] jArr3 = jArr;
        weakHashLength32WithSeeds(bArr3, i6 - 64, j2, load642, jArr);
        weakHashLength32WithSeeds(bArr3, i6 - 32, j2 * K1, f6912K0, jArr2);
        long shiftMix = (shiftMix(jArr3[1]) * K1) + load643;
        long rotateRight = Long.rotateRight(load64 + shiftMix, 39) * K1;
        long rotateRight2 = Long.rotateRight(load642, 33) * K1;
        int i7 = i3;
        int i8 = (i5 - 1) & -64;
        while (true) {
            long rotateRight3 = Long.rotateRight(rotateRight + rotateRight2 + jArr3[0] + LittleEndianByteArray.load64(bArr2, i7 + 16), 37) * K1;
            long rotateRight4 = Long.rotateRight(rotateRight2 + jArr3[1] + LittleEndianByteArray.load64(bArr2, i7 + 48), 42) * K1;
            long j3 = rotateRight3 ^ jArr2[1];
            long j4 = rotateRight4 ^ jArr3[0];
            long rotateRight5 = Long.rotateRight(shiftMix ^ jArr2[0], 33);
            byte[] bArr4 = bArr;
            weakHashLength32WithSeeds(bArr4, i7, jArr3[1] * K1, j3 + jArr2[0], jArr3);
            weakHashLength32WithSeeds(bArr4, i7 + 32, rotateRight5 + jArr2[1], j4, jArr2);
            i7 += 64;
            i8 -= 64;
            if (i8 == 0) {
                return hash128to64(b.e(shiftMix(j4), K1, hash128to64(jArr3[0], jArr2[0]), j3), hash128to64(jArr3[1], jArr2[1]) + rotateRight5);
            }
            rotateRight2 = j4;
            shiftMix = j3;
            rotateRight = rotateRight5;
        }
    }

    @VisibleForTesting
    public static long hash128to64(long j2, long j3) {
        long j4 = (j3 ^ j2) * K3;
        long j5 = (j2 ^ (j4 ^ (j4 >>> 47))) * K3;
        return (j5 ^ (j5 >>> 47)) * K3;
    }

    private static long hashLength33To64(byte[] bArr, int i3, int i4) {
        byte[] bArr2 = bArr;
        int i5 = i4;
        long load64 = LittleEndianByteArray.load64(bArr2, i3 + 24);
        long j2 = (long) i5;
        int i6 = i3 + i5;
        int i7 = i6 - 16;
        long load642 = ((j2 + LittleEndianByteArray.load64(bArr2, i7)) * f6912K0) + LittleEndianByteArray.load64(bArr, i3);
        long rotateRight = Long.rotateRight(load642 + load64, 52);
        long rotateRight2 = Long.rotateRight(load642, 37);
        long load643 = load642 + LittleEndianByteArray.load64(bArr2, i3 + 8);
        long rotateRight3 = Long.rotateRight(load643, 7) + rotateRight2;
        int i8 = i3 + 16;
        long load644 = load643 + LittleEndianByteArray.load64(bArr2, i8);
        long j3 = load64 + load644;
        long rotateRight4 = Long.rotateRight(load644, 31) + rotateRight + rotateRight3;
        long load645 = LittleEndianByteArray.load64(bArr2, i8) + LittleEndianByteArray.load64(bArr2, i6 - 32);
        long load646 = LittleEndianByteArray.load64(bArr2, i6 - 8);
        long rotateRight5 = Long.rotateRight(load645 + load646, 52);
        long rotateRight6 = Long.rotateRight(load645, 37);
        long load647 = load645 + LittleEndianByteArray.load64(bArr2, i6 - 24);
        long load648 = load647 + LittleEndianByteArray.load64(bArr2, i7);
        return shiftMix((shiftMix(((load648 + load646 + rotateRight4) * f6912K0) + ((Long.rotateRight(load648, 31) + rotateRight5 + Long.rotateRight(load647, 7) + rotateRight6 + j3) * K2)) * f6912K0) + rotateRight4) * K2;
    }

    @VisibleForTesting
    public static long murmurHash64WithSeed(byte[] bArr, int i3, int i4, long j2) {
        int i5 = i4 & -8;
        int i6 = i4 & 7;
        long j3 = j2 ^ (((long) i4) * K3);
        for (int i7 = 0; i7 < i5; i7 += 8) {
            j3 = (j3 ^ (shiftMix(LittleEndianByteArray.load64(bArr, i3 + i7) * K3) * K3)) * K3;
        }
        if (i6 != 0) {
            j3 = (LittleEndianByteArray.load64Safely(bArr, i3 + i5, i6) ^ j3) * K3;
        }
        return shiftMix(shiftMix(j3) * K3);
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
        long rotateRight = Long.rotateRight(j3 + j4 + load644, 51);
        long j5 = load642 + j4 + load643;
        jArr[0] = j5 + load644;
        jArr[1] = Long.rotateRight(j5, 23) + rotateRight + j4;
    }

    public int bits() {
        return 64;
    }

    public HashCode hashBytes(byte[] bArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, i3, i4));
    }

    public String toString() {
        return "Hashing.fingerprint2011()";
    }
}
