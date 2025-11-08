package com.google.android.gms.internal.fido;

public final class zzcl {
    public static byte[] zza(byte[]... bArr) {
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= r3) {
                break;
            }
            i4 += bArr[i3].length;
            i3++;
        }
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        for (byte[] bArr3 : bArr) {
            int length = bArr3.length;
            System.arraycopy(bArr3, 0, bArr2, i5, length);
            i5 += length;
        }
        return bArr2;
    }
}
