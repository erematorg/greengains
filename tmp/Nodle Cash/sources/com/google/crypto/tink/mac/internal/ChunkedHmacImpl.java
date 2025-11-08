package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.mac.ChunkedMac;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.mac.HmacKey;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public final class ChunkedHmacImpl implements ChunkedMac {
    private static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final HmacKey key;

    public ChunkedHmacImpl(HmacKey hmacKey) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            this.key = hmacKey;
            return;
        }
        throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }

    public ChunkedMacComputation createComputation() throws GeneralSecurityException {
        return new ChunkedHmacComputation(this.key);
    }

    public ChunkedMacVerification createVerification(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length < this.key.getOutputPrefix().size()) {
            throw new GeneralSecurityException("Tag too short");
        } else if (this.key.getOutputPrefix().equals(Bytes.copyFrom(bArr, 0, this.key.getOutputPrefix().size()))) {
            return new ChunkedHmacVerification(this.key, bArr);
        } else {
            throw new GeneralSecurityException("Wrong tag prefix");
        }
    }
}
