package com.google.crypto.tink.aead.internal;

import com.google.common.base.Ascii;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class Poly1305 {
    public static final int MAC_KEY_SIZE_IN_BYTES = 32;
    public static final int MAC_TAG_SIZE_IN_BYTES = 16;

    private Poly1305() {
    }

    public static byte[] computeMac(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            int i3 = 0;
            long load26 = load26(bArr3, 0, 0) & 67108863;
            int i4 = 3;
            long load262 = load26(bArr3, 3, 2) & 67108611;
            long load263 = load26(bArr3, 6, 4) & 67092735;
            long load264 = load26(bArr3, 9, 6) & 66076671;
            long load265 = load26(bArr3, 12, 8) & 1048575;
            long j2 = load262 * 5;
            long j3 = load263 * 5;
            long j4 = load264 * 5;
            long j5 = load265 * 5;
            byte[] bArr5 = new byte[17];
            long j6 = 0;
            int i5 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            long j10 = 0;
            while (i5 < bArr4.length) {
                copyBlockSize(bArr5, bArr4, i5);
                long load266 = j10 + load26(bArr5, i3, i3);
                long load267 = j6 + load26(bArr5, i4, 2);
                long load268 = j7 + load26(bArr5, 6, 4);
                long load269 = j8 + load26(bArr5, 9, 6);
                long load2610 = j9 + (load26(bArr5, 12, 8) | ((long) (bArr5[16] << Ascii.CAN)));
                long j11 = (load2610 * j2) + (load269 * j3) + (load268 * j4) + (load267 * j5) + (load266 * load26);
                long j12 = (load2610 * j3) + (load269 * j4) + (load268 * j5) + (load267 * load26) + (load266 * load262);
                long j13 = (load2610 * j4) + (load269 * j5) + (load268 * load26) + (load267 * load262) + (load266 * load263);
                long j14 = (load269 * load26) + (load268 * load262) + (load267 * load263) + (load266 * load264);
                long j15 = load269 * load262;
                long j16 = load2610 * load26;
                long j17 = j12 + (j11 >> 26);
                long j18 = j13 + (j17 >> 26);
                long j19 = (load2610 * j5) + j14 + (j18 >> 26);
                long j20 = j16 + j15 + (load268 * load263) + (load267 * load264) + (load266 * load265) + (j19 >> 26);
                long j21 = j20 >> 26;
                j9 = j20 & 67108863;
                long j22 = (j21 * 5) + (j11 & 67108863);
                i5 += 16;
                j7 = j18 & 67108863;
                j8 = j19 & 67108863;
                i4 = 3;
                j10 = j22 & 67108863;
                j6 = (j17 & 67108863) + (j22 >> 26);
                i3 = 0;
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
            long j35 = j32 + (j33 >> 26);
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
            long j49 = (j38 & j45) | j44;
            long load32 = ((j40 | (j34 & j45) | (j46 << 26)) & 4294967295L) + load32(bArr3, 16);
            long j50 = load32 & 4294967295L;
            long load322 = (((j46 >> 6) | (j47 << 20)) & 4294967295L) + load32(bArr3, 20) + (load32 >> 32);
            long load323 = (((j47 >> 12) | (j48 << 14)) & 4294967295L) + load32(bArr3, 24) + (load322 >> 32);
            byte[] bArr6 = new byte[16];
            toByteArray(bArr6, j50, 0);
            toByteArray(bArr6, load322 & 4294967295L, 4);
            toByteArray(bArr6, load323 & 4294967295L, 8);
            toByteArray(bArr6, ((((j48 >> 18) | (j49 << 8)) & 4294967295L) + load32(bArr3, 28) + (load323 >> 32)) & 4294967295L, 12);
            return bArr6;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }

    private static void copyBlockSize(byte[] bArr, byte[] bArr2, int i3) {
        int min = Math.min(16, bArr2.length - i3);
        System.arraycopy(bArr2, i3, bArr, 0, min);
        bArr[min] = 1;
        if (min != 16) {
            Arrays.fill(bArr, min + 1, bArr.length, (byte) 0);
        }
    }

    private static long load26(byte[] bArr, int i3, int i4) {
        return (load32(bArr, i3) >> i4) & 67108863;
    }

    private static long load32(byte[] bArr, int i3) {
        return ((long) (((bArr[i3 + 3] & 255) << Ascii.CAN) | (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16))) & 4294967295L;
    }

    private static void toByteArray(byte[] bArr, long j2, int i3) {
        int i4 = 0;
        while (i4 < 4) {
            bArr[i3 + i4] = (byte) ((int) (255 & j2));
            i4++;
            j2 >>= 8;
        }
    }

    public static void verifyMac(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(bArr, bArr2), bArr3)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
