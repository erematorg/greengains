package com.google.crypto.tink.subtle;

import A.a;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.mac.internal.AesUtil;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesSiv implements DeterministicAead {
    private static final byte[] BLOCK_ONE = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    private static final byte[] BLOCK_ZERO = new byte[16];
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    private static final Collection<Integer> KEY_SIZES = Arrays.asList(new Integer[]{64});
    private final byte[] aesCtrKey;
    private final PrfAesCmac cmacForS2V;

    public AesSiv(byte[] bArr) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
        } else if (KEY_SIZES.contains(Integer.valueOf(bArr.length))) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
            this.aesCtrKey = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
            this.cmacForS2V = new PrfAesCmac(copyOfRange);
        } else {
            throw new InvalidKeyException(a.m(new StringBuilder("invalid key size: "), " bytes; key must have 64 bytes", bArr.length));
        }
    }

    private byte[] s2v(byte[]... bArr) throws GeneralSecurityException {
        if (bArr.length == 0) {
            return this.cmacForS2V.compute(BLOCK_ONE, 16);
        }
        byte[] compute = this.cmacForS2V.compute(BLOCK_ZERO, 16);
        for (int i3 = 0; i3 < bArr.length - 1; i3++) {
            byte[] bArr2 = bArr[i3];
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            compute = Bytes.xor(AesUtil.dbl(compute), this.cmacForS2V.compute(bArr2, 16));
        }
        byte[] bArr3 = bArr[bArr.length - 1];
        return this.cmacForS2V.compute(bArr3.length >= 16 ? Bytes.xorEnd(bArr3, compute) : Bytes.xor(AesUtil.cmacPad(bArr3), AesUtil.dbl(compute)), 16);
    }

    public byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length >= 16) {
            Cipher instance = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
            byte[] bArr3 = (byte[]) copyOfRange.clone();
            bArr3[8] = (byte) (bArr3[8] & Byte.MAX_VALUE);
            bArr3[12] = (byte) (bArr3[12] & Byte.MAX_VALUE);
            instance.init(2, new SecretKeySpec(this.aesCtrKey, BouncyCastleKeyManagementRepository.AES), new IvParameterSpec(bArr3));
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 16, bArr.length);
            byte[] doFinal = instance.doFinal(copyOfRange2);
            if (copyOfRange2.length == 0 && doFinal == null && SubtleUtil.isAndroid()) {
                doFinal = new byte[0];
            }
            if (Bytes.equal(copyOfRange, s2v(bArr2, doFinal))) {
                return doFinal;
            }
            throw new AEADBadTagException("Integrity check failed.");
        }
        throw new GeneralSecurityException("Ciphertext too short.");
    }

    public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= 2147483631) {
            Cipher instance = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
            byte[] s2v = s2v(bArr2, bArr);
            byte[] bArr3 = (byte[]) s2v.clone();
            bArr3[8] = (byte) (bArr3[8] & Byte.MAX_VALUE);
            bArr3[12] = (byte) (bArr3[12] & Byte.MAX_VALUE);
            instance.init(1, new SecretKeySpec(this.aesCtrKey, BouncyCastleKeyManagementRepository.AES), new IvParameterSpec(bArr3));
            return Bytes.concat(s2v, instance.doFinal(bArr));
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
