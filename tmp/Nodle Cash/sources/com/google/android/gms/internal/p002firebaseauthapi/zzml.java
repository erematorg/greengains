package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.compose.ui.spatial.RectListKt;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzml  reason: invalid package */
public final class zzml {
    private static final int[] zza = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    private static final int[] zzb = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] zzc = {RectListKt.Lower26Bits, 33554431};
    private static final int[] zzd = {26, 25};

    public static void zza(long[] jArr, long[] jArr2) {
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
        zzb(jArr3, jArr2);
        zzb(jArr12, jArr3);
        zzb(jArr11, jArr12);
        zza(jArr4, jArr11, jArr2);
        zza(jArr5, jArr4, jArr3);
        zzb(jArr11, jArr5);
        zza(jArr6, jArr11, jArr4);
        zzb(jArr11, jArr6);
        zzb(jArr12, jArr11);
        zzb(jArr11, jArr12);
        zzb(jArr12, jArr11);
        zzb(jArr11, jArr12);
        zza(jArr7, jArr11, jArr6);
        zzb(jArr11, jArr7);
        zzb(jArr12, jArr11);
        for (int i3 = 2; i3 < 10; i3 += 2) {
            zzb(jArr11, jArr12);
            zzb(jArr12, jArr11);
        }
        zza(jArr8, jArr12, jArr7);
        zzb(jArr11, jArr8);
        zzb(jArr12, jArr11);
        for (int i4 = 2; i4 < 20; i4 += 2) {
            zzb(jArr11, jArr12);
            zzb(jArr12, jArr11);
        }
        zza(jArr11, jArr12, jArr8);
        zzb(jArr12, jArr11);
        zzb(jArr11, jArr12);
        for (int i5 = 2; i5 < 10; i5 += 2) {
            zzb(jArr12, jArr11);
            zzb(jArr11, jArr12);
        }
        zza(jArr9, jArr11, jArr7);
        zzb(jArr11, jArr9);
        zzb(jArr12, jArr11);
        for (int i6 = 2; i6 < 50; i6 += 2) {
            zzb(jArr11, jArr12);
            zzb(jArr12, jArr11);
        }
        zza(jArr10, jArr12, jArr9);
        zzb(jArr12, jArr10);
        zzb(jArr11, jArr12);
        for (int i7 = 2; i7 < 100; i7 += 2) {
            zzb(jArr12, jArr11);
            zzb(jArr11, jArr12);
        }
        zza(jArr12, jArr11, jArr10);
        zzb(jArr11, jArr12);
        zzb(jArr12, jArr11);
        for (int i8 = 2; i8 < 50; i8 += 2) {
            zzb(jArr11, jArr12);
            zzb(jArr12, jArr11);
        }
        zza(jArr11, jArr12, jArr9);
        zzb(jArr12, jArr11);
        zzb(jArr11, jArr12);
        zzb(jArr12, jArr11);
        zzb(jArr11, jArr12);
        zzb(jArr12, jArr11);
        zza(jArr, jArr12, jArr5);
    }

    /* JADX WARNING: type inference failed for: r48v0, types: [long[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void zzb(long[] r48, long[] r49, long[] r50) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzml.zzb(long[], long[], long[]):void");
    }

    public static void zzc(long[] jArr, long[] jArr2) {
        zzc(jArr, jArr2, jArr);
    }

    public static void zzd(long[] jArr, long[] jArr2) {
        zzd(jArr, jArr, jArr2);
    }

    private static void zze(long[] jArr, long[] jArr2) {
        if (jArr.length != 19) {
            long[] jArr3 = new long[19];
            System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
            jArr = jArr3;
        }
        zzb(jArr);
        zza(jArr);
        System.arraycopy(jArr, 0, jArr2, 0, 10);
    }

    public static void zzc(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i3 = 0; i3 < 10; i3++) {
            jArr[i3] = jArr2[i3] - jArr3[i3];
        }
    }

    public static void zzd(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i3 = 0; i3 < 10; i3++) {
            jArr[i3] = jArr2[i3] + jArr3[i3];
        }
    }

    public static byte[] zzc(long[] jArr) {
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
                int i6 = zzd[i5 & 1];
                int i7 = -((int) (((j2 >> 31) & j2) >> i6));
                copyOf[i5] = j2 + ((long) (i7 << i6));
                i5++;
                copyOf[i5] = copyOf[i5] - ((long) i7);
            }
            long j3 = copyOf[9];
            int i8 = -((int) (((j3 >> 31) & j3) >> 25));
            copyOf[9] = j3 + ((long) (i8 << 25));
            copyOf[0] = copyOf[0] - (((long) i8) * 19);
            i4++;
        }
        long j4 = copyOf[0];
        int i9 = -((int) (((j4 >> 31) & j4) >> 26));
        copyOf[0] = j4 + ((long) (i9 << 26));
        copyOf[1] = copyOf[1] - ((long) i9);
        int i10 = 0;
        for (i3 = 2; i10 < i3; i3 = 2) {
            int i11 = 0;
            while (i11 < 9) {
                long j5 = copyOf[i11];
                int i12 = i11 & 1;
                copyOf[i11] = j5 & ((long) zzc[i12]);
                i11++;
                copyOf[i11] = copyOf[i11] + ((long) ((int) (j5 >> zzd[i12])));
            }
            i10++;
        }
        long j6 = copyOf[9];
        copyOf[9] = j6 & 33554431;
        long j7 = (((long) ((int) (j6 >> 25))) * 19) + copyOf[0];
        copyOf[0] = j7;
        int i13 = ~((((int) j7) - 67108845) >> 31);
        for (int i14 = 1; i14 < 10; i14++) {
            int i15 = ~(((int) copyOf[i14]) ^ zzc[i14 & 1]);
            int i16 = i15 & (i15 << 16);
            int i17 = i16 & (i16 << 8);
            int i18 = i17 & (i17 << 4);
            int i19 = i18 & (i18 << 2);
            i13 &= (i19 & (i19 << 1)) >> 31;
        }
        copyOf[0] = copyOf[0] - ((long) (67108845 & i13));
        long j8 = (long) (33554431 & i13);
        copyOf[1] = copyOf[1] - j8;
        for (int i20 = 2; i20 < 10; i20 += 2) {
            copyOf[i20] = copyOf[i20] - ((long) (67108863 & i13));
            int i21 = i20 + 1;
            copyOf[i21] = copyOf[i21] - j8;
        }
        for (int i22 = 0; i22 < 10; i22++) {
            copyOf[i22] = copyOf[i22] << zzb[i22];
        }
        byte[] bArr = new byte[32];
        for (int i23 = 0; i23 < 10; i23++) {
            int i24 = zza[i23];
            long j9 = copyOf[i23];
            bArr[i24] = (byte) ((int) (((long) bArr[i24]) | (j9 & 255)));
            int i25 = i24 + 1;
            bArr[i25] = (byte) ((int) (((long) bArr[i25]) | ((j9 >> 8) & 255)));
            int i26 = i24 + 2;
            bArr[i26] = (byte) ((int) (((long) bArr[i26]) | ((j9 >> 16) & 255)));
            int i27 = i24 + 3;
            bArr[i27] = (byte) ((int) (((long) bArr[i27]) | ((j9 >> 24) & 255)));
        }
        return bArr;
    }

    public static void zzb(long[] jArr) {
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

    public static void zzb(long[] jArr, long[] jArr2) {
        long j2 = jArr2[0];
        long j3 = jArr2[1];
        long j4 = jArr2[2];
        long j5 = j2;
        long d2 = a.d(j5, j4, j3 * j3, 2);
        long j6 = jArr2[3];
        long d3 = a.d(j5, j6, j3 * j4, 2);
        long j7 = jArr2[4];
        long j8 = (j2 * 2 * j7) + (j3 * 4 * j6) + (j4 * j4);
        long j9 = jArr2[5];
        long d4 = a.d(j2, j9, (j3 * j7) + (j4 * j6), 2);
        long j10 = jArr2[6];
        long j11 = jArr2[7];
        long d5 = a.d(j2, j11, (j3 * j10) + (j4 * j9) + (j6 * j7), 2);
        long j12 = jArr2[8];
        long j13 = jArr2[9];
        long d6 = a.d(j4, j13, (j6 * j12) + (j7 * j11) + (j9 * j10), 2);
        long j14 = (((((j6 * j13) + (j9 * j11)) * 2) + (j7 * j12)) * 2) + (j10 * j10);
        long d7 = a.d(j7, j13, (j9 * j12) + (j10 * j11), 2);
        long j15 = j9 * 2 * j13;
        long d8 = a.d(j10, j13, j11 * j12, 2);
        long j16 = (j11 * 4 * j13) + (j12 * j12);
        zze(new long[]{j2 * j2, j2 * 2 * j3, d2, d3, j8, d4, ((j3 * 2 * j9) + (j2 * j10) + (j4 * j7) + (j6 * j6)) * 2, d5, (((((j6 * j9) + (j3 * j11)) * 2) + (j2 * j12) + (j4 * j10)) * 2) + (j7 * j7), a.d(j2, j13, (j3 * j12) + (j4 * j11) + (j6 * j10) + (j7 * j9), 2), ((((j3 * j13) + (j6 * j11)) * 2) + (j4 * j12) + (j7 * j10) + (j9 * j9)) * 2, d6, j14, d7, (j15 + (j10 * j12) + (j11 * j11)) * 2, d8, j16, j12 * 2 * j13, 2 * j13 * j13}, jArr);
    }

    public static void zza(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[19];
        zzb(jArr4, jArr2, jArr3);
        zze(jArr4, jArr);
    }

    public static void zza(long[] jArr) {
        jArr[10] = 0;
        int i3 = 0;
        while (i3 < 10) {
            long j2 = jArr[i3];
            long j3 = j2 / 67108864;
            jArr[i3] = j2 - (j3 << 26);
            int i4 = i3 + 1;
            long j4 = jArr[i4] + j3;
            jArr[i4] = j4;
            long j5 = j4 / 33554432;
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
        long j11 = j10 / 67108864;
        jArr[0] = j10 - (j11 << 26);
        jArr[1] = jArr[1] + j11;
    }

    public static void zza(long[] jArr, long[] jArr2, long j2) {
        for (int i3 = 0; i3 < 10; i3++) {
            jArr[i3] = jArr2[i3] * j2;
        }
    }

    public static long[] zza(byte[] bArr) {
        long[] jArr = new long[10];
        for (int i3 = 0; i3 < 10; i3++) {
            int i4 = zza[i3];
            jArr[i3] = ((((((long) (bArr[i4] & 255)) | (((long) (bArr[i4 + 1] & 255)) << 8)) | (((long) (bArr[i4 + 2] & 255)) << 16)) | (((long) (bArr[i4 + 3] & 255)) << 24)) >> zzb[i3]) & ((long) zzc[i3 & 1]);
        }
        return jArr;
    }
}
