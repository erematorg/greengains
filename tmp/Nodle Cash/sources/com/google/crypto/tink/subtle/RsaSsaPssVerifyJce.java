package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

@Immutable
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final Enums.HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssVerifyJce(RSAPublicKey rSAPublicKey, Enums.HashType hashType, Enums.HashType hashType2, int i3) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            Validators.validateSignatureHash(hashType);
            Validators.validateRsaModulusSize(rSAPublicKey.getModulus().bitLength());
            Validators.validateRsaPublicExponent(rSAPublicKey.getPublicExponent());
            this.publicKey = rSAPublicKey;
            this.sigHash = hashType;
            this.mgf1Hash = hashType2;
            this.saltLength = i3;
            return;
        }
        throw new GeneralSecurityException("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
    }

    private void emsaPssVerify(byte[] bArr, byte[] bArr2, int i3) throws GeneralSecurityException {
        byte[] bArr3 = bArr2;
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest instance = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] digest = instance.digest(bArr);
        int digestLength = instance.getDigestLength();
        int length = bArr3.length;
        if (length < this.saltLength + digestLength + 2) {
            throw new GeneralSecurityException("inconsistent");
        } else if (bArr3[bArr3.length - 1] == -68) {
            int i4 = length - digestLength;
            int i5 = i4 - 1;
            byte[] copyOf = Arrays.copyOf(bArr3, i5);
            byte[] copyOfRange = Arrays.copyOfRange(bArr3, copyOf.length, copyOf.length + digestLength);
            int i6 = 0;
            while (true) {
                int i7 = i5;
                MessageDigest messageDigest = instance;
                byte[] bArr4 = digest;
                long j2 = (((long) length) * 8) - ((long) i3);
                if (((long) i6) < j2) {
                    if (((copyOf[i6 / 8] >> (7 - (i6 % 8))) & 1) == 0) {
                        i6++;
                        i5 = i7;
                        instance = messageDigest;
                        digest = bArr4;
                    } else {
                        throw new GeneralSecurityException("inconsistent");
                    }
                } else {
                    byte[] mgf1 = SubtleUtil.mgf1(copyOfRange, i7, this.mgf1Hash);
                    int length2 = mgf1.length;
                    byte[] bArr5 = new byte[length2];
                    for (int i8 = 0; i8 < length2; i8++) {
                        bArr5[i8] = (byte) (mgf1[i8] ^ copyOf[i8]);
                    }
                    for (int i9 = 0; ((long) i9) <= j2; i9++) {
                        int i10 = i9 / 8;
                        bArr5[i10] = (byte) ((~(1 << (7 - (i9 % 8)))) & bArr5[i10]);
                    }
                    int i11 = 0;
                    while (true) {
                        int i12 = this.saltLength;
                        if (i11 < (i4 - i12) - 2) {
                            if (bArr5[i11] == 0) {
                                i11++;
                            } else {
                                throw new GeneralSecurityException("inconsistent");
                            }
                        } else if (bArr5[(i4 - i12) - 2] == 1) {
                            byte[] copyOfRange2 = Arrays.copyOfRange(bArr5, length2 - i12, length2);
                            int i13 = digestLength + 8;
                            byte[] bArr6 = new byte[(this.saltLength + i13)];
                            byte[] bArr7 = bArr4;
                            System.arraycopy(bArr7, 0, bArr6, 8, bArr7.length);
                            System.arraycopy(copyOfRange2, 0, bArr6, i13, copyOfRange2.length);
                            if (!Bytes.equal(messageDigest.digest(bArr6), copyOfRange)) {
                                throw new GeneralSecurityException("inconsistent");
                            }
                            return;
                        } else {
                            throw new GeneralSecurityException("inconsistent");
                        }
                    }
                }
            }
        } else {
            throw new GeneralSecurityException("inconsistent");
        }
    }

    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        BigInteger publicExponent = this.publicKey.getPublicExponent();
        BigInteger modulus = this.publicKey.getModulus();
        int bitLength = (modulus.bitLength() + 7) / 8;
        int bitLength2 = (modulus.bitLength() + 6) / 8;
        if (bitLength == bArr.length) {
            BigInteger bytes2Integer = SubtleUtil.bytes2Integer(bArr);
            if (bytes2Integer.compareTo(modulus) < 0) {
                emsaPssVerify(bArr2, SubtleUtil.integer2Bytes(bytes2Integer.modPow(publicExponent, modulus), bitLength2), modulus.bitLength() - 1);
                return;
            }
            throw new GeneralSecurityException("signature out of range");
        }
        throw new GeneralSecurityException("invalid signature's length");
    }
}
