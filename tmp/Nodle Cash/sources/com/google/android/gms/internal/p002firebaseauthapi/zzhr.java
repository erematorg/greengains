package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.AEADBadTagException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhr  reason: invalid package */
abstract class zzhr {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_NOT_FIPS;
    private final zzhp zzb;
    private final zzhp zzc;

    public zzhr(byte[] bArr) throws GeneralSecurityException {
        if (zza.zza()) {
            this.zzb = zza(bArr, 1);
            this.zzc = zza(bArr, 0);
            return;
        }
        throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
    }

    public abstract zzhp zza(byte[] bArr, int i3) throws InvalidKeyException;

    public void zza(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= bArr2.length + 16) {
            int position = byteBuffer.position();
            this.zzb.zza(byteBuffer, bArr, bArr2);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            if (bArr3 == null) {
                bArr3 = new byte[0];
            }
            byte[] zza2 = zzhx.zza(zza(bArr), zza(bArr3, byteBuffer));
            byteBuffer.limit(byteBuffer.limit() + 16);
            byteBuffer.put(zza2);
            return;
        }
        throw new IllegalArgumentException("Given ByteBuffer output is too small");
    }

    public byte[] zza(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= 16) {
            int position = byteBuffer.position();
            byte[] bArr3 = new byte[16];
            byteBuffer.position(byteBuffer.limit() - 16);
            byteBuffer.get(bArr3);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            try {
                if (MessageDigest.isEqual(zzhx.zza(zza(bArr), zza(bArr2, byteBuffer)), bArr3)) {
                    byteBuffer.position(position);
                    return this.zzb.zza(bArr, byteBuffer);
                }
                throw new GeneralSecurityException("invalid MAC");
            } catch (GeneralSecurityException e3) {
                throw new AEADBadTagException(e3.toString());
            }
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }

    public byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return zza(ByteBuffer.wrap(bArr2), bArr, bArr3);
    }

    private final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        ByteBuffer zza2 = this.zzc.zza(bArr, 0);
        byte[] bArr2 = new byte[32];
        zza2.get(bArr2);
        return bArr2;
    }

    private static byte[] zza(byte[] bArr, ByteBuffer byteBuffer) {
        int length = bArr.length % 16 == 0 ? bArr.length : (bArr.length + 16) - (bArr.length % 16);
        int remaining = byteBuffer.remaining();
        int i3 = remaining % 16;
        int i4 = (i3 == 0 ? remaining : (remaining + 16) - i3) + length;
        ByteBuffer order = ByteBuffer.allocate(i4 + 16).order(ByteOrder.LITTLE_ENDIAN);
        order.put(bArr);
        order.position(length);
        order.put(byteBuffer);
        order.position(i4);
        order.putLong((long) bArr.length);
        order.putLong((long) remaining);
        return order.array();
    }
}
