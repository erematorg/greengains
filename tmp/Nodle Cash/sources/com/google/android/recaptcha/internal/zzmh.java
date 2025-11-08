package com.google.android.recaptcha.internal;

import android.util.Base64;
import com.google.common.base.Ascii;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class zzmh {
    protected static final Charset zza = StandardCharsets.UTF_16;

    public static int zza(int i3, int i4) {
        if (i3 % 2 != 0) {
            return (i3 | i4) - (i3 & i4);
        }
        return ((~i3) & i4) | ((~i4) & i3);
    }

    public static String zzb(String str, byte[] bArr, zzmi zzmi) {
        byte[] bArr2 = bArr;
        int i3 = 0;
        byte[] decode = Base64.decode(str, 0);
        char c3 = 12;
        byte[] bArr3 = new byte[12];
        int length = decode.length - 12;
        byte[] bArr4 = new byte[length];
        System.arraycopy(decode, 0, bArr3, 0, 12);
        System.arraycopy(decode, 12, bArr4, 0, length);
        int[] iArr = {511133343, 1277647508, 107287496, 338123662};
        if (bArr2.length == 32) {
            int i4 = 16;
            int[] iArr2 = new int[16];
            for (int i5 = 0; i5 < 4; i5++) {
                iArr2[i5] = zza(iArr[i5], 2131181306);
            }
            for (int i6 = 4; i6 < 12; i6++) {
                iArr2[i6] = zze(bArr2, (i6 - 4) * 4);
            }
            iArr2[12] = 1;
            for (int i7 = 13; i7 < 16; i7++) {
                iArr2[i7] = zze(bArr3, (i7 - 13) * 4);
            }
            int[] iArr3 = new int[16];
            System.arraycopy(iArr2, 0, iArr3, 0, 16);
            byte[] bArr5 = new byte[length];
            int i8 = 1;
            int i9 = length;
            int i10 = 0;
            while (i9 > 0) {
                System.arraycopy(iArr3, i3, iArr2, i3, i4);
                iArr2[c3] = i8;
                int i11 = i3;
                while (i11 < 10) {
                    int i12 = i11;
                    int[] iArr4 = iArr;
                    int i13 = i9;
                    byte[] bArr6 = bArr;
                    byte[] bArr7 = bArr5;
                    byte[] bArr8 = bArr3;
                    int[] iArr5 = iArr3;
                    int i14 = i8;
                    int[] iArr6 = iArr2;
                    int i15 = i4;
                    int[] iArr7 = iArr5;
                    zzc(0, 4, 8, 12, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    zzc(1, 5, 9, 13, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    zzc(2, 6, 10, 14, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    zzc(3, 7, 11, 15, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    zzc(0, 5, 10, 15, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    zzc(1, 6, 11, 12, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    zzc(2, 7, 8, 13, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    zzc(3, 4, 9, 14, iArr4, bArr6, bArr8, i14, iArr2, iArr7);
                    i11 = i12 + 1;
                    i4 = i15;
                    i9 = i13;
                    bArr5 = bArr7;
                    iArr3 = iArr5;
                    byte[] bArr9 = bArr;
                }
                int i16 = i9;
                byte[] bArr10 = bArr5;
                int[] iArr8 = iArr3;
                int[] iArr9 = iArr2;
                int i17 = i4;
                byte[] bArr11 = new byte[64];
                for (int i18 = 0; i18 < i17; i18++) {
                    int i19 = iArr9[i18];
                    int i20 = i18 * 4;
                    bArr11[i20] = (byte) (i19 & 255);
                    bArr11[i20 + 1] = (byte) ((i19 >> 8) & 255);
                    bArr11[i20 + 2] = (byte) ((i19 >> 16) & 255);
                    bArr11[i20 + 3] = (byte) ((i19 >> 24) & 255);
                }
                for (int i21 = 0; i21 < Math.min(64, i16); i21++) {
                    int i22 = i10 + i21;
                    bArr10[i22] = (byte) zza(bArr11[i21], bArr4[i22]);
                }
                i8++;
                i9 = i16 - 64;
                i10 += 64;
                i4 = i17;
                bArr5 = bArr10;
                iArr3 = iArr8;
                iArr2 = iArr9;
                i3 = 0;
                c3 = 12;
                byte[] bArr12 = bArr;
            }
            return new String(bArr5, zza);
        }
        throw new IllegalArgumentException();
    }

    public static final void zzc(int i3, int i4, int i5, int i6, int[] iArr, byte[] bArr, byte[] bArr2, int i7, int[] iArr2, int[] iArr3) {
        zzd(i3, i4, i6, 16, iArr, bArr, bArr2, i7, iArr2, iArr3);
        zzd(i5, i6, i4, 12, iArr, bArr, bArr2, i7, iArr2, iArr3);
        zzd(i3, i4, i6, 8, iArr, bArr, bArr2, i7, iArr2, iArr3);
        zzd(i5, i6, i4, 7, iArr, bArr, bArr2, i7, iArr2, iArr3);
    }

    public static final void zzd(int i3, int i4, int i5, int i6, int[] iArr, byte[] bArr, byte[] bArr2, int i7, int[] iArr2, int[] iArr3) {
        int i8 = iArr2[i3] + iArr2[i4];
        iArr2[i3] = i8;
        int zza2 = zza(iArr2[i5], i8);
        iArr2[i5] = (zza2 << i6) | (zza2 >>> (32 - i6));
    }

    private static final int zze(byte[] bArr, int i3) {
        int i4 = (bArr[i3 + 1] & 255) << 8;
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | i4 | (bArr[i3] & 255) | ((bArr[i3 + 2] & 255) << 16);
    }
}
