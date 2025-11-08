package com.google.crypto.tink.subtle;

import androidx.compose.ui.spatial.RectListKt;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.crypto.tink.annotations.Alpha;
import java.util.Arrays;

@Alpha
final class Field25519 {
    private static final int[] EXPAND_SHIFT = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] EXPAND_START = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    static final int FIELD_LEN = 32;
    static final int LIMB_CNT = 10;
    private static final int[] MASK = {RectListKt.Lower26Bits, 33554431};
    private static final int[] SHIFT = {26, 25};
    private static final long TWO_TO_25 = 33554432;
    private static final long TWO_TO_26 = 67108864;

    private Field25519() {
    }

    public static byte[] contract(long[] jArr) {
        int i3;
        long[] copyOf = Arrays.copyOf(jArr, 10);
        int i4 = 0;
        while (true) {
            if (i4 >= 2) {
                break;
            }
            int i5 = 0;
            while (i5 < 9) {
                long j2 = copyOf[i5];
                int i6 = SHIFT[i5 & 1];
                int i7 = -((int) (((j2 >> 31) & j2) >> i6));
                copyOf[i5] = j2 + ((long) (i7 << i6));
                i5++;
                copyOf[i5] = copyOf[i5] - ((long) i7);
            }
            long j3 = copyOf[9];
            int i8 = -((int) (((j3 >> 31) & j3) >> 25));
            copyOf[9] = j3 + ((long) (i8 << 25));
            copyOf[0] = copyOf[0] - ((long) (i8 * 19));
            i4++;
        }
        long j4 = copyOf[0];
        int i9 = -((int) (((j4 >> 31) & j4) >> 26));
        copyOf[0] = j4 + ((long) (i9 << 26));
        copyOf[1] = copyOf[1] - ((long) i9);
        for (int i10 = 0; i10 < 2; i10++) {
            int i11 = 0;
            while (i11 < 9) {
                long j5 = copyOf[i11];
                int i12 = i11 & 1;
                int i13 = (int) (j5 >> SHIFT[i12]);
                copyOf[i11] = j5 & ((long) MASK[i12]);
                i11++;
                copyOf[i11] = copyOf[i11] + ((long) i13);
            }
        }
        long j6 = copyOf[9];
        copyOf[9] = j6 & 33554431;
        long j7 = copyOf[0] + ((long) (((int) (j6 >> 25)) * 19));
        copyOf[0] = j7;
        int gte = gte((int) j7, 67108845);
        for (int i14 = 1; i14 < 10; i14++) {
            gte &= eq((int) copyOf[i14], MASK[i14 & 1]);
        }
        copyOf[0] = copyOf[0] - ((long) (67108845 & gte));
        long j8 = (long) (33554431 & gte);
        copyOf[1] = copyOf[1] - j8;
        for (i3 = 2; i3 < 10; i3 += 2) {
            copyOf[i3] = copyOf[i3] - ((long) (67108863 & gte));
            int i15 = i3 + 1;
            copyOf[i15] = copyOf[i15] - j8;
        }
        for (int i16 = 0; i16 < 10; i16++) {
            copyOf[i16] = copyOf[i16] << EXPAND_SHIFT[i16];
        }
        byte[] bArr = new byte[32];
        for (int i17 = 0; i17 < 10; i17++) {
            int i18 = EXPAND_START[i17];
            long j9 = copyOf[i17];
            bArr[i18] = (byte) ((int) (((long) bArr[i18]) | (j9 & 255)));
            int i19 = i18 + 1;
            bArr[i19] = (byte) ((int) (((long) bArr[i19]) | ((j9 >> 8) & 255)));
            int i20 = i18 + 2;
            bArr[i20] = (byte) ((int) (((long) bArr[i20]) | ((j9 >> 16) & 255)));
            int i21 = i18 + 3;
            bArr[i21] = (byte) ((int) (((long) bArr[i21]) | ((j9 >> 24) & 255)));
        }
        return bArr;
    }

    private static int eq(int i3, int i4) {
        int i5 = ~(i3 ^ i4);
        int i6 = i5 & (i5 << 16);
        int i7 = i6 & (i6 << 8);
        int i8 = i7 & (i7 << 4);
        int i9 = i8 & (i8 << 2);
        return (i9 & (i9 << 1)) >> 31;
    }

    public static long[] expand(byte[] bArr) {
        long[] jArr = new long[10];
        for (int i3 = 0; i3 < 10; i3++) {
            int i4 = EXPAND_START[i3];
            jArr[i3] = ((((((long) (bArr[i4] & 255)) | (((long) (bArr[i4 + 1] & 255)) << 8)) | (((long) (bArr[i4 + 2] & 255)) << 16)) | (((long) (bArr[i4 + 3] & 255)) << 24)) >> EXPAND_SHIFT[i3]) & ((long) MASK[i3 & 1]);
        }
        return jArr;
    }

    private static int gte(int i3, int i4) {
        return ~((i3 - i4) >> 31);
    }

    public static void inverse(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[10];
        long[] jArr8 = new long[10];
        long[] jArr9 = new long[10];
        long[] jArr10 = new long[10];
        long[] jArr11 = new long[10];
        long[] jArr12 = new long[10];
        square(jArr3, jArr2);
        square(jArr12, jArr3);
        square(jArr11, jArr12);
        mult(jArr4, jArr11, jArr2);
        mult(jArr5, jArr4, jArr3);
        square(jArr11, jArr5);
        mult(jArr6, jArr11, jArr4);
        square(jArr11, jArr6);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        mult(jArr7, jArr11, jArr6);
        square(jArr11, jArr7);
        square(jArr12, jArr11);
        for (int i3 = 2; i3 < 10; i3 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr8, jArr12, jArr7);
        square(jArr11, jArr8);
        square(jArr12, jArr11);
        for (int i4 = 2; i4 < 20; i4 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr11, jArr12, jArr8);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        for (int i5 = 2; i5 < 10; i5 += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr9, jArr11, jArr7);
        square(jArr11, jArr9);
        square(jArr12, jArr11);
        for (int i6 = 2; i6 < 50; i6 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr10, jArr12, jArr9);
        square(jArr12, jArr10);
        square(jArr11, jArr12);
        for (int i7 = 2; i7 < 100; i7 += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr12, jArr11, jArr10);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        for (int i8 = 2; i8 < 50; i8 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr11, jArr12, jArr9);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        mult(jArr, jArr12, jArr5);
    }

    public static void mult(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[19];
        product(jArr4, jArr2, jArr3);
        reduce(jArr4, jArr);
    }

    /* JADX WARNING: type inference failed for: r48v0, types: [long[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void product(long[] r48, long[] r49, long[] r50) {
        /*
            r0 = 0
            r1 = r49[r0]
            r3 = r50[r0]
            long r1 = r1 * r3
            r48[r0] = r1
            r1 = r49[r0]
            r3 = 1
            r4 = r50[r3]
            long r4 = r4 * r1
            r6 = r49[r3]
            r8 = r50[r0]
            long r6 = r6 * r8
            long r6 = r6 + r4
            r48[r3] = r6
            r4 = r49[r3]
            r6 = 2
            long r10 = r4 * r6
            r18 = r50[r3]
            long r10 = r10 * r18
            r0 = 2
            r12 = r50[r0]
            long r12 = r12 * r1
            long r12 = r12 + r10
            r10 = r49[r0]
            long r10 = r10 * r8
            long r10 = r10 + r12
            r48[r0] = r10
            r20 = r50[r0]
            long r10 = r4 * r20
            r22 = r49[r0]
            long r12 = r22 * r18
            long r12 = r12 + r10
            r0 = 3
            r10 = r50[r0]
            long r10 = r10 * r1
            long r10 = r10 + r12
            r12 = r49[r0]
            long r12 = r12 * r8
            long r12 = r12 + r10
            r48[r0] = r12
            long r10 = r22 * r20
            r24 = r50[r0]
            long r12 = r4 * r24
            r26 = r49[r0]
            long r14 = r26 * r18
            long r14 = r14 + r12
            long r14 = r14 * r6
            long r14 = r14 + r10
            r0 = 4
            r10 = r50[r0]
            long r10 = r10 * r1
            long r10 = r10 + r14
            r12 = r49[r0]
            long r12 = r12 * r8
            long r12 = r12 + r10
            r48[r0] = r12
            long r10 = r22 * r24
            long r12 = r26 * r20
            long r12 = r12 + r10
            r28 = r50[r0]
            long r10 = r4 * r28
            long r10 = r10 + r12
            r30 = r49[r0]
            long r12 = r30 * r18
            long r12 = r12 + r10
            r0 = 5
            r10 = r50[r0]
            long r10 = r10 * r1
            long r10 = r10 + r12
            r12 = r49[r0]
            long r12 = r12 * r8
            long r12 = r12 + r10
            r48[r0] = r12
            long r10 = r26 * r24
            r32 = r50[r0]
            long r12 = r4 * r32
            long r14 = r12 + r10
            r34 = r49[r0]
            r10 = r34
            r12 = r18
            r16 = r6
            long r10 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.d(r10, r12, r14, r16)
            long r12 = r22 * r28
            long r12 = r12 + r10
            long r10 = r30 * r20
            long r10 = r10 + r12
            r0 = 6
            r12 = r50[r0]
            long r12 = r12 * r1
            long r12 = r12 + r10
            r10 = r49[r0]
            long r10 = r10 * r8
            long r10 = r10 + r12
            r48[r0] = r10
            long r10 = r26 * r28
            long r12 = r30 * r24
            long r12 = r12 + r10
            long r10 = r22 * r32
            long r10 = r10 + r12
            long r12 = r34 * r20
            long r12 = r12 + r10
            r36 = r50[r0]
            long r10 = r4 * r36
            long r10 = r10 + r12
            r38 = r49[r0]
            long r12 = r38 * r18
            long r12 = r12 + r10
            r0 = 7
            r10 = r50[r0]
            long r10 = r10 * r1
            long r10 = r10 + r12
            r12 = r49[r0]
            long r12 = r12 * r8
            long r12 = r12 + r10
            r48[r0] = r12
            long r10 = r30 * r28
            long r12 = r26 * r32
            long r14 = r34 * r24
            long r14 = r14 + r12
            r40 = r50[r0]
            long r12 = r4 * r40
            long r12 = r12 + r14
            r42 = r49[r0]
            long r14 = r42 * r18
            long r14 = r14 + r12
            long r14 = r14 * r6
            long r14 = r14 + r10
            long r10 = r22 * r36
            long r10 = r10 + r14
            long r12 = r38 * r20
            long r12 = r12 + r10
            r0 = 8
            r10 = r50[r0]
            long r10 = r10 * r1
            long r10 = r10 + r12
            r12 = r49[r0]
            long r12 = r12 * r8
            long r12 = r12 + r10
            r48[r0] = r12
            long r10 = r30 * r32
            long r12 = r34 * r28
            long r12 = r12 + r10
            long r10 = r26 * r36
            long r10 = r10 + r12
            long r12 = r38 * r24
            long r12 = r12 + r10
            long r10 = r22 * r40
            long r10 = r10 + r12
            long r12 = r42 * r20
            long r12 = r12 + r10
            r44 = r50[r0]
            long r10 = r4 * r44
            long r10 = r10 + r12
            r46 = r49[r0]
            long r12 = r46 * r18
            long r12 = r12 + r10
            r0 = 9
            r10 = r50[r0]
            long r1 = r1 * r10
            long r1 = r1 + r12
            r10 = r49[r0]
            long r10 = r10 * r8
            long r10 = r10 + r1
            r48[r0] = r10
            long r1 = r34 * r32
            long r8 = r26 * r40
            long r8 = r8 + r1
            long r1 = r42 * r24
            long r1 = r1 + r8
            r8 = r50[r0]
            long r4 = r4 * r8
            long r14 = r4 + r1
            r0 = r49[r0]
            r10 = r0
            r12 = r18
            long r2 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.d(r10, r12, r14, r16)
            long r4 = r30 * r36
            long r4 = r4 + r2
            long r2 = r38 * r28
            long r2 = r2 + r4
            long r4 = r22 * r44
            long r4 = r4 + r2
            long r2 = r46 * r20
            long r2 = r2 + r4
            r4 = 10
            r48[r4] = r2
            long r2 = r34 * r36
            long r4 = r38 * r32
            long r4 = r4 + r2
            long r2 = r30 * r40
            long r2 = r2 + r4
            long r4 = r42 * r28
            long r4 = r4 + r2
            long r2 = r26 * r44
            long r2 = r2 + r4
            long r4 = r46 * r24
            long r4 = r4 + r2
            long r22 = r22 * r8
            long r22 = r22 + r4
            long r20 = r20 * r0
            long r20 = r20 + r22
            r2 = 11
            r48[r2] = r20
            long r2 = r38 * r36
            long r4 = r34 * r40
            long r10 = r42 * r32
            long r10 = r10 + r4
            long r26 = r26 * r8
            long r26 = r26 + r10
            long r24 = r24 * r0
            long r24 = r24 + r26
            long r24 = r24 * r6
            long r24 = r24 + r2
            long r2 = r30 * r44
            long r2 = r2 + r24
            long r4 = r46 * r28
            long r4 = r4 + r2
            r2 = 12
            r48[r2] = r4
            long r2 = r38 * r40
            long r4 = r42 * r36
            long r4 = r4 + r2
            long r2 = r34 * r44
            long r2 = r2 + r4
            long r4 = r46 * r32
            long r4 = r4 + r2
            long r30 = r30 * r8
            long r30 = r30 + r4
            long r28 = r28 * r0
            long r28 = r28 + r30
            r2 = 13
            r48[r2] = r28
            long r2 = r42 * r40
            long r34 = r34 * r8
            long r14 = r34 + r2
            r10 = r0
            r12 = r32
            long r2 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.d(r10, r12, r14, r16)
            long r4 = r38 * r44
            long r4 = r4 + r2
            long r2 = r46 * r36
            long r2 = r2 + r4
            r4 = 14
            r48[r4] = r2
            long r2 = r42 * r44
            long r4 = r46 * r40
            long r4 = r4 + r2
            long r38 = r38 * r8
            long r38 = r38 + r4
            long r36 = r36 * r0
            long r36 = r36 + r38
            r2 = 15
            r48[r2] = r36
            long r2 = r46 * r44
            long r42 = r42 * r8
            long r40 = r40 * r0
            long r40 = r40 + r42
            long r40 = r40 * r6
            long r40 = r40 + r2
            r2 = 16
            r48[r2] = r40
            long r46 = r46 * r8
            long r44 = r44 * r0
            long r44 = r44 + r46
            r2 = 17
            r48[r2] = r44
            long r0 = r0 * r6
            long r0 = r0 * r8
            r2 = 18
            r48[r2] = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Field25519.product(long[], long[], long[]):void");
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        if (jArr.length != 19) {
            long[] jArr3 = new long[19];
            System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
            jArr = jArr3;
        }
        reduceSizeByModularReduction(jArr);
        reduceCoefficients(jArr);
        System.arraycopy(jArr, 0, jArr2, 0, 10);
    }

    public static void reduceCoefficients(long[] jArr) {
        jArr[10] = 0;
        int i3 = 0;
        while (i3 < 10) {
            long j2 = jArr[i3];
            long j3 = j2 / TWO_TO_26;
            jArr[i3] = j2 - (j3 << 26);
            int i4 = i3 + 1;
            long j4 = jArr[i4] + j3;
            jArr[i4] = j4;
            long j5 = j4 / TWO_TO_25;
            jArr[i4] = j4 - (j5 << 25);
            i3 += 2;
            jArr[i3] = jArr[i3] + j5;
        }
        long j6 = jArr[0];
        long j7 = jArr[10];
        long j8 = j6 + (j7 << 4);
        jArr[0] = j8;
        long j9 = j8 + (j7 << 1);
        jArr[0] = j9;
        long j10 = j9 + j7;
        jArr[0] = j10;
        jArr[10] = 0;
        long j11 = j10 / TWO_TO_26;
        jArr[0] = j10 - (j11 << 26);
        jArr[1] = jArr[1] + j11;
    }

    public static void reduceSizeByModularReduction(long[] jArr) {
        long j2 = jArr[8];
        long j3 = jArr[18];
        long j4 = j2 + (j3 << 4);
        jArr[8] = j4;
        long j5 = j4 + (j3 << 1);
        jArr[8] = j5;
        jArr[8] = j5 + j3;
        long j6 = jArr[7];
        long j7 = jArr[17];
        long j8 = j6 + (j7 << 4);
        jArr[7] = j8;
        long j9 = j8 + (j7 << 1);
        jArr[7] = j9;
        jArr[7] = j9 + j7;
        long j10 = jArr[6];
        long j11 = jArr[16];
        long j12 = j10 + (j11 << 4);
        jArr[6] = j12;
        long j13 = j12 + (j11 << 1);
        jArr[6] = j13;
        jArr[6] = j13 + j11;
        long j14 = jArr[5];
        long j15 = jArr[15];
        long j16 = j14 + (j15 << 4);
        jArr[5] = j16;
        long j17 = j16 + (j15 << 1);
        jArr[5] = j17;
        jArr[5] = j17 + j15;
        long j18 = jArr[4];
        long j19 = jArr[14];
        long j20 = j18 + (j19 << 4);
        jArr[4] = j20;
        long j21 = j20 + (j19 << 1);
        jArr[4] = j21;
        jArr[4] = j21 + j19;
        long j22 = jArr[3];
        long j23 = jArr[13];
        long j24 = j22 + (j23 << 4);
        jArr[3] = j24;
        long j25 = j24 + (j23 << 1);
        jArr[3] = j25;
        jArr[3] = j25 + j23;
        long j26 = jArr[2];
        long j27 = jArr[12];
        long j28 = j26 + (j27 << 4);
        jArr[2] = j28;
        long j29 = j28 + (j27 << 1);
        jArr[2] = j29;
        jArr[2] = j29 + j27;
        long j30 = jArr[1];
        long j31 = jArr[11];
        long j32 = j30 + (j31 << 4);
        jArr[1] = j32;
        long j33 = j32 + (j31 << 1);
        jArr[1] = j33;
        jArr[1] = j33 + j31;
        long j34 = jArr[0];
        long j35 = jArr[10];
        long j36 = j34 + (j35 << 4);
        jArr[0] = j36;
        long j37 = j36 + (j35 << 1);
        jArr[0] = j37;
        jArr[0] = j37 + j35;
    }

    public static void scalarProduct(long[] jArr, long[] jArr2, long j2) {
        for (int i3 = 0; i3 < 10; i3++) {
            jArr[i3] = jArr2[i3] * j2;
        }
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[19];
        squareInner(jArr3, jArr2);
        reduce(jArr3, jArr);
    }

    private static void squareInner(long[] jArr, long[] jArr2) {
        long j2 = jArr2[0];
        jArr[0] = j2 * j2;
        long j3 = jArr2[0];
        jArr[1] = j3 * 2 * jArr2[1];
        long j4 = jArr2[1];
        long j5 = j3;
        jArr[2] = a.d(j5, jArr2[2], j4 * j4, 2);
        long j6 = jArr2[2];
        long j7 = j3;
        jArr[3] = a.d(j7, jArr2[3], j4 * j6, 2);
        long j8 = jArr2[3];
        long j9 = j3 * 2 * jArr2[4];
        jArr[4] = j9 + (j4 * 4 * j8) + (j6 * j6);
        long j10 = jArr2[4];
        long j11 = jArr2[5];
        long j12 = j3;
        jArr[5] = a.d(j12, j11, (j4 * j10) + (j6 * j8), 2);
        long j13 = (jArr2[6] * j3) + (j6 * j10) + (j8 * j8);
        long j14 = jArr2[5];
        jArr[6] = a.d(j4 * 2, j14, j13, 2);
        long j15 = jArr2[6];
        long j16 = jArr2[7];
        long j17 = j3;
        jArr[7] = a.d(j17, j16, (j4 * j15) + (j6 * j14) + (j8 * j10), 2);
        long j18 = jArr2[7];
        jArr[8] = (((((j8 * j14) + (j4 * j18)) * 2) + (jArr2[8] * j3) + (j6 * j15)) * 2) + (j10 * j10);
        long j19 = jArr2[8];
        long j20 = jArr2[9];
        long j21 = j3;
        jArr[9] = a.d(j21, j20, (j4 * j19) + (j6 * j18) + (j8 * j15) + (j10 * j14), 2);
        long j22 = jArr2[9];
        jArr[10] = ((((j4 * j22) + (j8 * j18)) * 2) + (j6 * j19) + (j10 * j15) + (j14 * j14)) * 2;
        jArr[11] = a.d(j6, j22, (j8 * j19) + (j10 * j18) + (j14 * j15), 2);
        jArr[12] = (((((j8 * j22) + (j14 * j18)) * 2) + (j10 * j19)) * 2) + (j15 * j15);
        long j23 = j22;
        jArr[13] = a.d(j10, j23, (j14 * j19) + (j15 * j18), 2);
        long j24 = j14 * 2 * j22;
        jArr[14] = (j24 + (j15 * j19) + (j18 * j18)) * 2;
        jArr[15] = a.d(j15, j23, j18 * j19, 2);
        jArr[16] = (j18 * 4 * j22) + (j19 * j19);
        jArr[17] = j19 * 2 * j22;
        jArr[18] = 2 * j22 * j22;
    }

    public static void sub(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i3 = 0; i3 < 10; i3++) {
            jArr[i3] = jArr2[i3] - jArr3[i3];
        }
    }

    public static void sum(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i3 = 0; i3 < 10; i3++) {
            jArr[i3] = jArr2[i3] + jArr3[i3];
        }
    }

    public static void sub(long[] jArr, long[] jArr2) {
        sub(jArr, jArr2, jArr);
    }

    public static void sum(long[] jArr, long[] jArr2) {
        sum(jArr, jArr, jArr2);
    }
}
