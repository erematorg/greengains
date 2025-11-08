package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Hkdf {
    private Hkdf() {
    }

    public static byte[] computeEciesHkdfSymmetricKey(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, byte[] bArr4, int i3) throws GeneralSecurityException {
        return computeHkdf(str, Bytes.concat(bArr, bArr2), bArr3, bArr4, i3);
    }

    public static byte[] computeHkdf(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3) throws GeneralSecurityException {
        Mac instance = EngineFactory.MAC.getInstance(str);
        if (i3 <= instance.getMacLength() * 255) {
            if (bArr2 == null || bArr2.length == 0) {
                instance.init(new SecretKeySpec(new byte[instance.getMacLength()], str));
            } else {
                instance.init(new SecretKeySpec(bArr2, str));
            }
            byte[] bArr4 = new byte[i3];
            instance.init(new SecretKeySpec(instance.doFinal(bArr), str));
            byte[] bArr5 = new byte[0];
            int i4 = 1;
            int i5 = 0;
            while (true) {
                instance.update(bArr5);
                instance.update(bArr3);
                instance.update((byte) i4);
                bArr5 = instance.doFinal();
                if (bArr5.length + i5 < i3) {
                    System.arraycopy(bArr5, 0, bArr4, i5, bArr5.length);
                    i5 += bArr5.length;
                    i4++;
                } else {
                    System.arraycopy(bArr5, 0, bArr4, i5, i3 - i5);
                    return bArr4;
                }
            }
        } else {
            throw new GeneralSecurityException("size too large");
        }
    }
}
