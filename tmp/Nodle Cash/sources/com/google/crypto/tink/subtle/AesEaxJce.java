package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesEaxJce implements Aead {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int BLOCK_SIZE_IN_BYTES = 16;
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    static final int TAG_SIZE_IN_BYTES = 16;
    private static final ThreadLocal<Cipher> localCtrCipher = new ThreadLocal<Cipher>() {
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/CTR/NOPADDING");
            } catch (GeneralSecurityException e3) {
                throw new IllegalStateException(e3);
            }
        }
    };
    private static final ThreadLocal<Cipher> localEcbCipher = new ThreadLocal<Cipher>() {
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/ECB/NOPADDING");
            } catch (GeneralSecurityException e3) {
                throw new IllegalStateException(e3);
            }
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f7024b;
    private final int ivSizeInBytes;
    private final SecretKeySpec keySpec;

    /* renamed from: p  reason: collision with root package name */
    private final byte[] f7025p;

    public AesEaxJce(byte[] bArr, int i3) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        } else if (i3 == 12 || i3 == 16) {
            this.ivSizeInBytes = i3;
            Validators.validateAesKeySize(bArr.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
            this.keySpec = secretKeySpec;
            Cipher cipher = localEcbCipher.get();
            cipher.init(1, secretKeySpec);
            byte[] multiplyByX = multiplyByX(cipher.doFinal(new byte[16]));
            this.f7024b = multiplyByX;
            this.f7025p = multiplyByX(multiplyByX);
        } else {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
    }

    private static byte[] multiplyByX(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i3 = 0;
        while (i3 < 15) {
            int i4 = i3 + 1;
            bArr2[i3] = (byte) (((bArr[i3] << 1) ^ ((bArr[i4] & 255) >>> 7)) & 255);
            i3 = i4;
        }
        bArr2[15] = (byte) (((bArr[0] >> 7) & 135) ^ (bArr[15] << 1));
        return bArr2;
    }

    private byte[] omac(Cipher cipher, int i3, byte[] bArr, int i4, int i5) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i3;
        if (i5 == 0) {
            return cipher.doFinal(xor(bArr2, this.f7024b));
        }
        byte[] doFinal = cipher.doFinal(bArr2);
        int i6 = 0;
        while (i5 - i6 > 16) {
            for (int i7 = 0; i7 < 16; i7++) {
                doFinal[i7] = (byte) (doFinal[i7] ^ bArr[(i4 + i6) + i7]);
            }
            doFinal = cipher.doFinal(doFinal);
            i6 += 16;
        }
        return cipher.doFinal(xor(doFinal, pad(Arrays.copyOfRange(bArr, i6 + i4, i4 + i5))));
    }

    private byte[] pad(byte[] bArr) {
        if (bArr.length == 16) {
            return xor(bArr, this.f7024b);
        }
        byte[] copyOf = Arrays.copyOf(this.f7025p, 16);
        for (int i3 = 0; i3 < bArr.length; i3++) {
            copyOf[i3] = (byte) (copyOf[i3] ^ bArr[i3]);
        }
        copyOf[bArr.length] = (byte) (copyOf[bArr.length] ^ 128);
        return copyOf;
    }

    private static byte[] xor(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr3[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
        }
        return bArr3;
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = (bArr.length - this.ivSizeInBytes) - 16;
        if (length >= 0) {
            Cipher cipher = localEcbCipher.get();
            cipher.init(1, this.keySpec);
            byte[] omac = omac(cipher, 0, bArr, 0, this.ivSizeInBytes);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr3 = bArr2;
            byte[] omac2 = omac(cipher, 1, bArr3, 0, bArr3.length);
            byte[] omac3 = omac(cipher, 2, bArr, this.ivSizeInBytes, length);
            int length2 = bArr.length - 16;
            byte b3 = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                b3 = (byte) (b3 | (((bArr[length2 + i3] ^ omac2[i3]) ^ omac[i3]) ^ omac3[i3]));
            }
            if (b3 == 0) {
                Cipher cipher2 = localCtrCipher.get();
                cipher2.init(1, this.keySpec, new IvParameterSpec(omac));
                return cipher2.doFinal(bArr, this.ivSizeInBytes, length);
            }
            throw new AEADBadTagException("tag mismatch");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = bArr;
        int length = bArr3.length;
        int i3 = this.ivSizeInBytes;
        if (length <= 2147483631 - i3) {
            byte[] bArr4 = new byte[(bArr3.length + i3 + 16)];
            byte[] randBytes = Random.randBytes(i3);
            System.arraycopy(randBytes, 0, bArr4, 0, this.ivSizeInBytes);
            Cipher cipher = localEcbCipher.get();
            cipher.init(1, this.keySpec);
            byte[] omac = omac(cipher, 0, randBytes, 0, randBytes.length);
            byte[] bArr5 = bArr2 == null ? new byte[0] : bArr2;
            byte[] omac2 = omac(cipher, 1, bArr5, 0, bArr5.length);
            Cipher cipher2 = localCtrCipher.get();
            cipher2.init(1, this.keySpec, new IvParameterSpec(omac));
            cipher2.doFinal(bArr, 0, bArr3.length, bArr4, this.ivSizeInBytes);
            byte[] omac3 = omac(cipher, 2, bArr4, this.ivSizeInBytes, bArr3.length);
            int length2 = bArr3.length + this.ivSizeInBytes;
            for (int i4 = 0; i4 < 16; i4++) {
                bArr4[length2 + i4] = (byte) ((omac2[i4] ^ omac[i4]) ^ omac3[i4]);
            }
            return bArr4;
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
