package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.X25519;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
final class X25519HpkeKemPrivateKey implements HpkeKemPrivateKey {
    private final Bytes privateKey;
    private final Bytes publicKey;

    private X25519HpkeKemPrivateKey(byte[] bArr, byte[] bArr2) {
        this.privateKey = Bytes.copyFrom(bArr);
        this.publicKey = Bytes.copyFrom(bArr2);
    }

    public static X25519HpkeKemPrivateKey fromBytes(byte[] bArr) throws GeneralSecurityException {
        return new X25519HpkeKemPrivateKey(bArr, X25519.publicFromPrivate(bArr));
    }

    public Bytes getSerializedPrivate() {
        return this.privateKey;
    }

    public Bytes getSerializedPublic() {
        return this.publicKey;
    }
}
