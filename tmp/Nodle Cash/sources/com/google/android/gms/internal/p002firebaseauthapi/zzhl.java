package com.google.android.gms.internal.p002firebaseauthapi;

import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhl  reason: invalid package */
final class zzhl {
    private static final int[] zza = zza(new byte[]{101, CBORConstants.BYTE_STRING_1BYTE_LEN, 112, 97, 110, 100, 32, 51, 50, 45, 98, CBORConstants.BYTE_STRING_2BYTE_LEN, 116, 101, 32, 107});

    private static int zza(int i3, int i4) {
        return (i3 >>> (-i4)) | (i3 << i4);
    }

    public static int[] zzb(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        zza(iArr3, iArr);
        iArr3[12] = iArr2[0];
        iArr3[13] = iArr2[1];
        iArr3[14] = iArr2[2];
        iArr3[15] = iArr2[3];
        zza(iArr3);
        iArr3[4] = iArr3[12];
        iArr3[5] = iArr3[13];
        iArr3[6] = iArr3[14];
        iArr3[7] = iArr3[15];
        return Arrays.copyOf(iArr3, 8);
    }

    private static void zza(int[] iArr, int i3, int i4, int i5, int i6) {
        int i7 = iArr[i3] + iArr[i4];
        iArr[i3] = i7;
        int zza2 = zza(i7 ^ iArr[i6], 16);
        iArr[i6] = zza2;
        int i8 = iArr[i5] + zza2;
        iArr[i5] = i8;
        int zza3 = zza(iArr[i4] ^ i8, 12);
        iArr[i4] = zza3;
        int i9 = iArr[i3] + zza3;
        iArr[i3] = i9;
        int zza4 = zza(iArr[i6] ^ i9, 8);
        iArr[i6] = zza4;
        int i10 = iArr[i5] + zza4;
        iArr[i5] = i10;
        iArr[i4] = zza(iArr[i4] ^ i10, 7);
    }

    public static void zza(int[] iArr, int[] iArr2) {
        int[] iArr3 = zza;
        System.arraycopy(iArr3, 0, iArr, 0, iArr3.length);
        System.arraycopy(iArr2, 0, iArr, iArr3.length, 8);
    }

    public static void zza(int[] iArr) {
        int[] iArr2 = iArr;
        for (int i3 = 0; i3 < 10; i3++) {
            zza(iArr2, 0, 4, 8, 12);
            zza(iArr2, 1, 5, 9, 13);
            zza(iArr2, 2, 6, 10, 14);
            zza(iArr2, 3, 7, 11, 15);
            zza(iArr2, 0, 5, 10, 15);
            zza(iArr2, 1, 6, 11, 12);
            zza(iArr2, 2, 7, 8, 13);
            zza(iArr2, 3, 4, 9, 14);
        }
    }

    public static byte[] zza(byte[] bArr, byte[] bArr2) {
        int[] zzb = zzb(zza(bArr), zza(bArr2));
        ByteBuffer order = ByteBuffer.allocate(zzb.length << 2).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(zzb);
        return order.array();
    }

    public static int[] zza(byte[] bArr) {
        if (bArr.length % 4 == 0) {
            IntBuffer asIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
            int[] iArr = new int[asIntBuffer.remaining()];
            asIntBuffer.get(iArr);
            return iArr;
        }
        throw new IllegalArgumentException("invalid input length");
    }
}
