package com.google.crypto.tink.aead.internal;

import androidx.camera.camera2.internal.C0118y;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.SubtleUtil;
import com.google.crypto.tink.subtle.Validators;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class InsecureNonceAesGcmJce {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    public static final int IV_SIZE_IN_BYTES = 12;
    public static final int TAG_SIZE_IN_BYTES = 16;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() {
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
            } catch (GeneralSecurityException e3) {
                throw new IllegalStateException(e3);
            }
        }
    };
    private final SecretKey keySpec;
    private final boolean prependIv;

    public InsecureNonceAesGcmJce(byte[] bArr, boolean z2) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            Validators.validateAesKeySize(bArr.length);
            this.keySpec = new SecretKeySpec(bArr, BouncyCastleKeyManagementRepository.AES);
            this.prependIv = z2;
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }

    private static AlgorithmParameterSpec getParams(byte[] bArr) throws GeneralSecurityException {
        return getParams(bArr, 0, bArr.length);
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length == 12) {
            boolean z2 = this.prependIv;
            if (bArr2.length >= (z2 ? 28 : 16)) {
                int i3 = 0;
                if (!z2 || ByteBuffer.wrap(bArr).equals(ByteBuffer.wrap(bArr2, 0, 12))) {
                    AlgorithmParameterSpec params = getParams(bArr);
                    ThreadLocal<Cipher> threadLocal = localCipher;
                    threadLocal.get().init(2, this.keySpec, params);
                    if (!(bArr3 == null || bArr3.length == 0)) {
                        threadLocal.get().updateAAD(bArr3);
                    }
                    boolean z3 = this.prependIv;
                    if (z3) {
                        i3 = 12;
                    }
                    return threadLocal.get().doFinal(bArr2, i3, z3 ? bArr2.length - 12 : bArr2.length);
                }
                throw new GeneralSecurityException("iv does not match prepended iv");
            }
            throw new GeneralSecurityException("ciphertext too short");
        }
        throw new GeneralSecurityException("iv is wrong size");
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        } else if (bArr2.length <= 2147483619) {
            boolean z2 = this.prependIv;
            byte[] bArr4 = new byte[(z2 ? bArr2.length + 28 : bArr2.length + 16)];
            if (z2) {
                System.arraycopy(bArr, 0, bArr4, 0, 12);
            }
            AlgorithmParameterSpec params = getParams(bArr);
            ThreadLocal<Cipher> threadLocal = localCipher;
            threadLocal.get().init(1, this.keySpec, params);
            if (!(bArr3 == null || bArr3.length == 0)) {
                threadLocal.get().updateAAD(bArr3);
            }
            int doFinal = threadLocal.get().doFinal(bArr2, 0, bArr2.length, bArr4, this.prependIv ? 12 : 0);
            if (doFinal == bArr2.length + 16) {
                return bArr4;
            }
            throw new GeneralSecurityException(C0118y.c(doFinal - bArr2.length, "encryption failed; GCM tag must be 16 bytes, but got only ", " bytes"));
        } else {
            throw new GeneralSecurityException("plaintext too long");
        }
    }

    private static AlgorithmParameterSpec getParams(byte[] bArr, int i3, int i4) throws GeneralSecurityException {
        if (!SubtleUtil.isAndroid() || SubtleUtil.androidApiLevel() > 19) {
            return new GCMParameterSpec(128, bArr, i3, i4);
        }
        return new IvParameterSpec(bArr, i3, i4);
    }
}
