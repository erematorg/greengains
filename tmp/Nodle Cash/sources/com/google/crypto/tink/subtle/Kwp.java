package com.google.crypto.tink.subtle;

import com.google.crypto.tink.KeyWrap;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Kwp implements KeyWrap {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int MAX_WRAP_KEY_SIZE = 4096;
    static final int MIN_WRAP_KEY_SIZE = 16;
    static final byte[] PREFIX = {-90, 89, 89, -90};
    static final int ROUNDS = 6;
    private final SecretKey aesKey;

    public Kwp(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length == 16 || bArr.length == 32) {
            this.aesKey = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
            return;
        }
        throw new GeneralSecurityException("Unsupported key length");
    }

    private byte[] computeW(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr2.length <= 8 || bArr2.length > 2147483631 || bArr.length != 8) {
            throw new GeneralSecurityException("computeW called with invalid parameters");
        }
        int wrappingSize = wrappingSize(bArr2.length);
        byte[] bArr3 = new byte[wrappingSize];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, 8, bArr2.length);
        int i3 = (wrappingSize / 8) - 1;
        Cipher instance = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        instance.init(1, this.aesKey);
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr3, 0, bArr4, 0, 8);
        for (int i4 = 0; i4 < 6; i4++) {
            int i5 = 0;
            while (i5 < i3) {
                int i6 = i5 + 1;
                int i7 = i6 * 8;
                System.arraycopy(bArr3, i7, bArr4, 8, 8);
                instance.doFinal(bArr4, 0, 16, bArr4);
                int i8 = (i4 * i3) + i5 + 1;
                for (int i9 = 0; i9 < 4; i9++) {
                    int i10 = 7 - i9;
                    bArr4[i10] = (byte) (bArr4[i10] ^ ((byte) (i8 & 255)));
                    i8 >>>= 8;
                }
                System.arraycopy(bArr4, 8, bArr3, i7, 8);
                i5 = i6;
            }
        }
        System.arraycopy(bArr4, 0, bArr3, 0, 8);
        return bArr3;
    }

    private byte[] invertW(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2 = bArr;
        if (bArr2.length < 24 || bArr2.length % 8 != 0) {
            throw new GeneralSecurityException("Incorrect data size");
        }
        byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length);
        int length = copyOf.length / 8;
        int i3 = length - 1;
        Cipher instance = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        instance.init(2, this.aesKey);
        byte[] bArr3 = new byte[16];
        System.arraycopy(copyOf, 0, bArr3, 0, 8);
        for (int i4 = 5; i4 >= 0; i4--) {
            for (int i5 = length - 2; i5 >= 0; i5--) {
                int i6 = (i5 + 1) * 8;
                System.arraycopy(copyOf, i6, bArr3, 8, 8);
                int i7 = (i4 * i3) + i5 + 1;
                for (int i8 = 0; i8 < 4; i8++) {
                    int i9 = 7 - i8;
                    bArr3[i9] = (byte) (((byte) (i7 & 255)) ^ bArr3[i9]);
                    i7 >>>= 8;
                }
                instance.doFinal(bArr3, 0, 16, bArr3);
                System.arraycopy(bArr3, 8, copyOf, i6, 8);
            }
        }
        System.arraycopy(bArr3, 0, copyOf, 0, 8);
        return copyOf;
    }

    private int wrappingSize(int i3) {
        return (7 - ((i3 + 7) % 8)) + i3 + 8;
    }

    public byte[] unwrap(byte[] bArr) throws GeneralSecurityException {
        int i3;
        if (bArr.length < wrappingSize(16)) {
            throw new GeneralSecurityException("Wrapped key size is too small");
        } else if (bArr.length > wrappingSize(4096)) {
            throw new GeneralSecurityException("Wrapped key size is too large");
        } else if (bArr.length % 8 == 0) {
            byte[] invertW = invertW(bArr);
            boolean z2 = true;
            boolean z3 = false;
            int i4 = 0;
            while (true) {
                if (i4 >= 4) {
                    break;
                }
                if (PREFIX[i4] != invertW[i4]) {
                    z2 = false;
                }
                i4++;
            }
            int i5 = 0;
            for (i3 = 4; i3 < 8; i3++) {
                i5 = (i5 << 8) + (invertW[i3] & 255);
            }
            if (wrappingSize(i5) == invertW.length) {
                for (int i6 = i5 + 8; i6 < invertW.length; i6++) {
                    if (invertW[i6] != 0) {
                        z2 = false;
                    }
                }
                z3 = z2;
            }
            if (z3) {
                return Arrays.copyOfRange(invertW, 8, i5 + 8);
            }
            throw new BadPaddingException("Invalid padding");
        } else {
            throw new GeneralSecurityException("Wrapped key size must be a multiple of 8 bytes");
        }
    }

    public byte[] wrap(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length < 16) {
            throw new GeneralSecurityException("Key size of key to wrap too small");
        } else if (bArr.length <= 4096) {
            byte[] bArr2 = new byte[8];
            byte[] bArr3 = PREFIX;
            System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
            for (int i3 = 0; i3 < 4; i3++) {
                bArr2[i3 + 4] = (byte) ((bArr.length >> ((3 - i3) * 8)) & 255);
            }
            return computeW(bArr2, bArr);
        } else {
            throw new GeneralSecurityException("Key size of key to wrap too large");
        }
    }
}
