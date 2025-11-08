package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import androidx.collection.ScatterMapKt;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.common.base.Ascii;

@KeepForSdk
public class MurmurHash3 {
    private MurmurHash3() {
    }

    @KeepForSdk
    public static int murmurhash3_x86_32(@NonNull byte[] bArr, int i3, int i4, int i5) {
        int i6;
        int i7 = i3;
        while (true) {
            i6 = (i4 & -4) + i3;
            if (i7 >= i6) {
                break;
            }
            int i8 = ((bArr[i7] & 255) | ((bArr[i7 + 1] & 255) << 8) | ((bArr[i7 + 2] & 255) << 16) | (bArr[i7 + 3] << Ascii.CAN)) * ScatterMapKt.MurmurHashC1;
            int i9 = i5 ^ (((i8 >>> 17) | (i8 << 15)) * 461845907);
            i5 = (((i9 >>> 19) | (i9 << 13)) * 5) - 430675100;
            i7 += 4;
        }
        int i10 = i4 & 3;
        int i11 = 0;
        if (i10 != 1) {
            if (i10 != 2) {
                if (i10 == 3) {
                    i11 = (bArr[i6 + 2] & 255) << 16;
                }
                int i12 = i5 ^ i4;
                int i13 = (i12 ^ (i12 >>> 16)) * -2048144789;
                int i14 = (i13 ^ (i13 >>> 13)) * -1028477387;
                return i14 ^ (i14 >>> 16);
            }
            i11 |= (bArr[i6 + 1] & 255) << 8;
        }
        int i15 = ((bArr[i6] & 255) | i11) * ScatterMapKt.MurmurHashC1;
        i5 ^= ((i15 >>> 17) | (i15 << 15)) * 461845907;
        int i122 = i5 ^ i4;
        int i132 = (i122 ^ (i122 >>> 16)) * -2048144789;
        int i142 = (i132 ^ (i132 >>> 13)) * -1028477387;
        return i142 ^ (i142 >>> 16);
    }
}
