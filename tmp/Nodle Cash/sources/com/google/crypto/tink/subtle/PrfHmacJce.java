package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;

@Immutable
public final class PrfHmacJce implements Prf {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    static final int MIN_KEY_SIZE_IN_BYTES = 16;
    /* access modifiers changed from: private */
    public final String algorithm;
    /* access modifiers changed from: private */
    public final Key key;
    private final ThreadLocal<Mac> localMac;
    private final int maxOutputLength;

    public PrfHmacJce(String str, Key key2) throws GeneralSecurityException {
        AnonymousClass1 r02 = new ThreadLocal<Mac>() {
            public Mac initialValue() {
                try {
                    Mac instance = EngineFactory.MAC.getInstance(PrfHmacJce.this.algorithm);
                    instance.init(PrfHmacJce.this.key);
                    return instance;
                } catch (GeneralSecurityException e3) {
                    throw new IllegalStateException(e3);
                }
            }
        };
        this.localMac = r02;
        if (FIPS.isCompatible()) {
            this.algorithm = str;
            this.key = key2;
            if (key2.getEncoded().length >= 16) {
                str.getClass();
                char c3 = 65535;
                switch (str.hashCode()) {
                    case -1823053428:
                        if (str.equals("HMACSHA1")) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case 392315023:
                        if (str.equals("HMACSHA224")) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case 392315118:
                        if (str.equals("HMACSHA256")) {
                            c3 = 2;
                            break;
                        }
                        break;
                    case 392316170:
                        if (str.equals("HMACSHA384")) {
                            c3 = 3;
                            break;
                        }
                        break;
                    case 392317873:
                        if (str.equals("HMACSHA512")) {
                            c3 = 4;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        this.maxOutputLength = 20;
                        break;
                    case 1:
                        this.maxOutputLength = 28;
                        break;
                    case 2:
                        this.maxOutputLength = 32;
                        break;
                    case 3:
                        this.maxOutputLength = 48;
                        break;
                    case 4:
                        this.maxOutputLength = 64;
                        break;
                    default:
                        throw new NoSuchAlgorithmException("unknown Hmac algorithm: ".concat(str));
                }
                r02.get();
                return;
            }
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }

    public byte[] compute(byte[] bArr, int i3) throws GeneralSecurityException {
        if (i3 <= this.maxOutputLength) {
            this.localMac.get().update(bArr);
            return Arrays.copyOf(this.localMac.get().doFinal(), i3);
        }
        throw new InvalidAlgorithmParameterException("tag size too big");
    }

    public int getMaxOutputLength() {
        return this.maxOutputLength;
    }
}
