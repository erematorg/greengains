package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.common.base.Ascii;

@ShowFirstParty
@KeepForSdk
public class Hex {
    private static final char[] zza = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzb = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @NonNull
    @KeepForSdk
    public static String bytesToStringLowercase(@NonNull byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length + length)];
        int i3 = 0;
        for (byte b3 : bArr) {
            char[] cArr2 = zzb;
            cArr[i3] = cArr2[(b3 & 255) >>> 4];
            cArr[i3 + 1] = cArr2[b3 & Ascii.SI];
            i3 += 2;
        }
        return new String(cArr);
    }

    @NonNull
    @KeepForSdk
    public static String bytesToStringUppercase(@NonNull byte[] bArr) {
        return bytesToStringUppercase(bArr, false);
    }

    @NonNull
    @KeepForSdk
    public static byte[] stringToBytes(@NonNull String str) throws IllegalArgumentException {
        int length = str.length();
        if (length % 2 == 0) {
            byte[] bArr = new byte[(length / 2)];
            int i3 = 0;
            while (i3 < length) {
                int i4 = i3 + 2;
                bArr[i3 / 2] = (byte) Integer.parseInt(str.substring(i3, i4), 16);
                i3 = i4;
            }
            return bArr;
        }
        throw new IllegalArgumentException("Hex string has odd number of characters");
    }

    @NonNull
    @KeepForSdk
    public static String bytesToStringUppercase(@NonNull byte[] bArr, boolean z2) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + length);
        int i3 = 0;
        while (i3 < length && (!z2 || i3 != length - 1 || (bArr[i3] & 255) != 0)) {
            char[] cArr = zza;
            sb.append(cArr[(bArr[i3] & 240) >>> 4]);
            sb.append(cArr[bArr[i3] & Ascii.SI]);
            i3++;
        }
        return sb.toString();
    }
}
