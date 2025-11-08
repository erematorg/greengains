package com.google.crypto.tink.hybrid.internal;

final class HpkeKemEncapOutput {
    private final byte[] encapsulatedKey;
    private final byte[] sharedSecret;

    public HpkeKemEncapOutput(byte[] bArr, byte[] bArr2) {
        this.sharedSecret = bArr;
        this.encapsulatedKey = bArr2;
    }

    public byte[] getEncapsulatedKey() {
        return this.encapsulatedKey;
    }

    public byte[] getSharedSecret() {
        return this.sharedSecret;
    }
}
