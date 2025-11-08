package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhp  reason: invalid package */
abstract class zzhp {
    int[] zza;
    private final int zzb;

    public zzhp(byte[] bArr, int i3) throws InvalidKeyException {
        if (bArr.length == 32) {
            this.zza = zzhl.zza(bArr);
            this.zzb = i3;
            return;
        }
        throw new InvalidKeyException("The key length in bytes must be 32.");
    }

    public abstract int zza();

    public final ByteBuffer zza(byte[] bArr, int i3) {
        int[] zza2 = zza(zzhl.zza(bArr), i3);
        int[] iArr = (int[]) zza2.clone();
        zzhl.zza(iArr);
        for (int i4 = 0; i4 < zza2.length; i4++) {
            zza2[i4] = zza2[i4] + iArr[i4];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(zza2, 0, 16);
        return order;
    }

    public abstract int[] zza(int[] iArr, int i3);

    public void zza(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= bArr2.length) {
            zza(bArr, byteBuffer, ByteBuffer.wrap(bArr2));
            return;
        }
        throw new IllegalArgumentException("Given ByteBuffer output is too small");
    }

    private final void zza(byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws GeneralSecurityException {
        if (bArr.length == zza()) {
            int remaining = byteBuffer2.remaining();
            int i3 = remaining / 64;
            int i4 = i3 + 1;
            for (int i5 = 0; i5 < i4; i5++) {
                ByteBuffer zza2 = zza(bArr, this.zzb + i5);
                if (i5 == i3) {
                    zzwh.zza(byteBuffer, byteBuffer2, zza2, remaining % 64);
                } else {
                    zzwh.zza(byteBuffer, byteBuffer2, zza2, 64);
                }
            }
            return;
        }
        throw new GeneralSecurityException(a.k("The nonce length (in bytes) must be ", zza()));
    }

    public byte[] zza(byte[] bArr, ByteBuffer byteBuffer) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        zza(bArr, allocate, byteBuffer);
        return allocate.array();
    }
}
