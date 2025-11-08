package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import java.security.GeneralSecurityException;

public class AeadOrDaead {
    private final Aead aead;
    private final DeterministicAead deterministicAead;

    public AeadOrDaead(Aead aead2) {
        this.aead = aead2;
        this.deterministicAead = null;
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Aead aead2 = this.aead;
        return aead2 != null ? aead2.decrypt(bArr, bArr2) : this.deterministicAead.decryptDeterministically(bArr, bArr2);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Aead aead2 = this.aead;
        return aead2 != null ? aead2.encrypt(bArr, bArr2) : this.deterministicAead.encryptDeterministically(bArr, bArr2);
    }

    public AeadOrDaead(DeterministicAead deterministicAead2) {
        this.aead = null;
        this.deterministicAead = deterministicAead2;
    }
}
