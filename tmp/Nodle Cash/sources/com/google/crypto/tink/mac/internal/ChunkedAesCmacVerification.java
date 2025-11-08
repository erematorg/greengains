package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.ChunkedMacVerification;
import com.google.crypto.tink.util.Bytes;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

final class ChunkedAesCmacVerification implements ChunkedMacVerification {
    private final ChunkedAesCmacComputation aesCmacComputation;
    private final Bytes tag;

    public ChunkedAesCmacVerification(AesCmacKey aesCmacKey, byte[] bArr) throws GeneralSecurityException {
        this.aesCmacComputation = new ChunkedAesCmacComputation(aesCmacKey);
        this.tag = Bytes.copyFrom(bArr);
    }

    public void update(ByteBuffer byteBuffer) throws GeneralSecurityException {
        this.aesCmacComputation.update(byteBuffer);
    }

    public void verifyMac() throws GeneralSecurityException {
        if (!this.tag.equals(Bytes.copyFrom(this.aesCmacComputation.computeMac()))) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
