package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhx  reason: invalid package */
public final class zzhx {
    private static long zza(byte[] bArr, int i3, int i4) {
        return (zza(bArr, i3) >> i4) & 67108863;
    }

    private static long zza(byte[] bArr, int i3) {
        return ((long) (((bArr[i3 + 3] & 255) << Ascii.CAN) | (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16))) & 4294967295L;
    }

    private static void zza(byte[] bArr, long j2, int i3) {
        int i4 = 0;
        while (i4 < 4) {
            bArr[i3 + i4] = (byte) ((int) (255 & j2));
            i4++;
            j2 >>= 8;
        }
    }

    public static byte[] zza(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            long zza = zza(bArr3, 0, 0) & 67108863;
            long zza2 = zza(bArr3, 3, 2) & 67108611;
            long zza3 = zza(bArr3, 6, 4) & 67092735;
            long zza4 = zza(bArr3, 9, 6) & 66076671;
            long zza5 = zza(bArr3, 12, 8) & 1048575;
            long j2 = zza2 * 5;
            long j3 = zza3 * 5;
            long j4 = zza4 * 5;
            long j5 = zza5 * 5;
            int i3 = 17;
            byte[] bArr5 = new byte[17];
            long j6 = 0;
            int i4 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            long j10 = 0;
            while (i4 < bArr4.length) {
                int min = Math.min(16, bArr4.length - i4);
                System.arraycopy(bArr4, i4, bArr5, 0, min);
                bArr5[min] = 1;
                if (min != 16) {
                    Arrays.fill(bArr5, min + 1, i3, (byte) 0);
                }
                long zza6 = j10 + zza(bArr5, 0, 0);
                long zza7 = j6 + zza(bArr5, 3, 2);
                long zza8 = j7 + zza(bArr5, 6, 4);
                long zza9 = j8 + zza(bArr5, 9, 6);
                long zza10 = j9 + (zza(bArr5, 12, 8) | ((long) (bArr5[16] << Ascii.CAN)));
                long j11 = (zza10 * j2) + (zza9 * j3) + (zza8 * j4) + (zza7 * j5) + (zza6 * zza);
                long j12 = (zza10 * j3) + (zza9 * j4) + (zza8 * j5) + (zza7 * zza) + (zza6 * zza2);
                long j13 = (zza10 * j4) + (zza9 * j5) + (zza8 * zza) + (zza7 * zza2) + (zza6 * zza3);
                long j14 = (zza9 * zza) + (zza8 * zza2) + (zza7 * zza3) + (zza6 * zza4);
                long j15 = zza9 * zza2;
                long j16 = zza10 * zza;
                long j17 = j12 + (j11 >> 26);
                long j18 = j13 + (j17 >> 26);
                long j19 = (zza10 * j5) + j14 + (j18 >> 26);
                long j20 = j16 + j15 + (zza8 * zza3) + (zza7 * zza4) + (zza6 * zza5) + (j19 >> 26);
                long j21 = j20 >> 26;
                j9 = j20 & 67108863;
                long j22 = (j21 * 5) + (j11 & 67108863);
                i4 += 16;
                j7 = j18 & 67108863;
                j8 = j19 & 67108863;
                i3 = 17;
                j10 = j22 & 67108863;
                j6 = (j17 & 67108863) + (j22 >> 26);
            }
            long j23 = j7 + (j6 >> 26);
            long j24 = j23 & 67108863;
            long j25 = j8 + (j23 >> 26);
            long j26 = j25 & 67108863;
            long j27 = j9 + (j25 >> 26);
            long j28 = j27 & 67108863;
            long j29 = ((j27 >> 26) * 5) + j10;
            long j30 = j29 >> 26;
            long j31 = j29 & 67108863;
            long j32 = (j6 & 67108863) + j30;
            long j33 = j31 + 5;
            long j34 = j33 & 67108863;
            long j35 = (j33 >> 26) + j32;
            long j36 = j24 + (j35 >> 26);
            long j37 = j26 + (j36 >> 26);
            long j38 = (j28 + (j37 >> 26)) - 67108864;
            long j39 = j38 >> 63;
            long j40 = j31 & j39;
            long j41 = j32 & j39;
            long j42 = j24 & j39;
            long j43 = j26 & j39;
            long j44 = j28 & j39;
            long j45 = ~j39;
            long j46 = j41 | (j35 & 67108863 & j45);
            long j47 = j42 | (j36 & 67108863 & j45);
            long j48 = j43 | (j37 & 67108863 & j45);
            long j49 = j44 | (j38 & j45);
            long zza11 = ((j40 | (j34 & j45) | (j46 << 26)) & 4294967295L) + zza(bArr3, 16);
            long j50 = zza11 & 4294967295L;
            long zza12 = (((j46 >> 6) | (j47 << 20)) & 4294967295L) + zza(bArr3, 20) + (zza11 >> 32);
            long j51 = zza12 & 4294967295L;
            long zza13 = (((j47 >> 12) | (j48 << 14)) & 4294967295L) + zza(bArr3, 24) + (zza12 >> 32);
            long j52 = zza13 & 4294967295L;
            byte[] bArr6 = new byte[16];
            zza(bArr6, j50, 0);
            zza(bArr6, j51, 4);
            zza(bArr6, j52, 8);
            zza(bArr6, ((((j48 >> 18) | (j49 << 8)) & 4294967295L) + zza(bArr3, 28) + (zza13 >> 32)) & 4294967295L, 12);
            return bArr6;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }
}
